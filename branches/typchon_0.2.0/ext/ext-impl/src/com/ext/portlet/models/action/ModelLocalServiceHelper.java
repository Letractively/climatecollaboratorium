/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.action;

import com.ext.portlet.debaterevision.DebateItemStatus;
import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.model.DebateItem;
import com.ext.portlet.debaterevision.service.DebateCategoryLocalServiceUtil;
import com.ext.portlet.debaterevision.service.DebateItemLocalServiceUtil;
import com.ext.portlet.models.ModelConstants;
import com.ext.portlet.models.model.ModelDiscussion;
import com.ext.portlet.models.model.ModelPosition;
import com.ext.portlet.models.service.ModelDiscussionLocalServiceUtil;
import com.ext.portlet.models.service.ModelPositionLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.messageboards.NoSuchCategoryException;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.service.MBCategoryLocalServiceUtil;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelLocalServiceHelper {

	/**
	 * Name of column that represents value of PlanPosition planId field.
	 */
	public static final String POSITION_MODEL_ID = "primaryKey.modelId";

	/**
	 * Name of column that represents value of PlanPosition planId field.
	 */
	public static final String POSITION_POSITION_ID = "primaryKey.positionId";

	/**
	 * Returns list of positions referenced by a model.
	 *
	 * @param modelId
	 *            ID of a plan for which positions should be retrieved
	 * @return list of MBMessage objects representing referenced positions
	 * @throws PortalException
	 *             passed up in case of framework error
	 * @throws SystemException
	 *             passed up in case of framework error
	 */
	public static List<DebateItem> getModelPositions(long modelId)
			throws PortalException, SystemException {
		// load all plan positions
		List<DebateItem> result = new ArrayList<DebateItem>();
        List<ModelPosition> positions = queryModelPositions(modelId);
		for (ModelPosition position : positions) {
			 DebateItem item = DebateItemLocalServiceUtil.getLastItem(position.getPositionId());
            if (item == null) continue;
            if (DebateItemStatus.valueOf(item.getStatus()) == DebateItemStatus.ACTIVE) {
               result.add(item);

            }
		}

		return result;
	}

    public static List<ModelPosition> queryModelPositions(long modelId) throws SystemException {
        List<ModelPosition> positions = new ArrayList<ModelPosition>();

		// create custom query and execute it
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil
				.forClass(ModelPosition.class);
		Criterion criterionPublished = RestrictionsFactoryUtil.eq(
				POSITION_MODEL_ID, modelId);
		dynamicQuery.add(criterionPublished);

		List<Object> ret = ModelPositionLocalServiceUtil
				.dynamicQuery(dynamicQuery);

		for (Object positionObj : ret) {
			ModelPosition position = (ModelPosition) positionObj;
			positions.add(position);
		}

		return positions;
    }

	/**
	 * Method adds positions and questions related to topic to the request.
	 *
	 * @param request
	 *            render request
	 * @param topicId
	 *            ID of topic from which questions and positions should be taken
	 * @throws SystemException
	 *             passed up in case of any error in framework
	 * @throws PortalException
	 *             passed up in case of any error in framework
	 */
	public static void addQuestionsAndPositions(PortletRequest request,
			long topicId) throws SystemException, PortalException {
		List<Debate> debates = DebateCategoryLocalServiceUtil.getDebates(topicId);
        List<DebateItem> questions = new ArrayList<DebateItem>();
        Map<DebateItem, List<DebateItem>> questionPositions = new HashMap<DebateItem, List<DebateItem>>();

        for (Debate d : debates) {
            questions.add(d.getCurrentRoot());
            questionPositions.put(d.getCurrentRoot(), d.getCurrentRoot().getChildren());
        }

        request.setAttribute(ModelConstants.QUESTIONS, questions);
        request.setAttribute(ModelConstants.QUESTION_POSITIONS, questionPositions);

	}

	/**
	 * Convenience method for external callers to retrieve all questions / positions associated with this model
	 * @param request
	 * @param modelid
	 * @throws PortalException
	 * @throws SystemException
	 */
	public static void addAllQuestionsAndPositions(PortletRequest request, Long modelid) throws PortalException, SystemException {
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		long defaultplid = LayoutLocalServiceUtil.getDefaultPlid(themeDisplay.getScopeGroupId(),false,"models");
		PortletPreferences prefs = PortletPreferencesLocalServiceUtil.getPreferences(themeDisplay.getCompanyId(), PortletKeys.PREFS_OWNER_ID_DEFAULT,PortletKeys.PREFS_OWNER_TYPE_LAYOUT,defaultplid,"models");
		 long topicId = Long.parseLong(prefs.getValue(ModelConstants.DEFAULT_TOPIC_ID, "-1"));
		 addQuestionsAndPositions(request,topicId);
		 request.setAttribute(ModelConstants.MODEL_POSITIONS,ModelLocalServiceHelper.getModelPositions(modelid));

	}

	/**
	 * Returns id of default topic that should be referenced by newly created
	 * plans.
	 *
	 * @param request
	 *            portlet request
	 * @return id of default topic
	 * @throws SystemException
	 *             passed up in case of framework error
	 */
	public static String getDefaultTopicId(PortletRequest request)
			throws SystemException {
		return getPortletPreference(request, ModelConstants.DEFAULT_TOPIC_ID);
	}

	/**
	 * Returns portlet preference.
	 *
	 * @param request
	 *            portlet request
	 * @param preferenceName
	 *            name of preference to retrieve
	 * @return preference value
	 * @throws SystemException
	 *             passed up in case of framework error
	 */
	private static String getPortletPreference(PortletRequest request,
			String preferenceName) throws SystemException {
		PortletPreferences preferences = request.getPreferences();
		return preferences.getValue(preferenceName, "-1");
	}

	public static MBCategory createDiscussionTopic(PortletRequest request,
			Long modelId, String modelName) throws PortalException,
			SystemException, ReadOnlyException, ValidatorException, IOException {
		PortletPreferences preferences = request.getPreferences();
		MBCategory mainCategory = null;
		Long topicId = Long.parseLong(preferences.getValue(
				ModelConstants.DEFAULT_MODEL_DISCUSSION_TOPIC_ID, "-1"));
		if (topicId > -1) {
			try {
				mainCategory = MBCategoryLocalServiceUtil.getMBCategory(topicId);
			} catch (NoSuchCategoryException e) {

				topicId = -1l;
			}
		}
		if (topicId < 0) {
			//create new top level topic, and save the prefs
			topicId = initModelDiscussionTopic(request);
			mainCategory = MBCategoryLocalServiceUtil.getMBCategory(topicId);
		}


		// throws an error if no category available
		MBCategory modelDiscussion = ModelLocalServiceHelper.createMBCategory(
				request, mainCategory.getCategoryId(), modelName);

		long associationId = CounterLocalServiceUtil
				.increment(ModelDiscussion.class.getName());
		ModelDiscussion entity = ModelDiscussionLocalServiceUtil
				.createModelDiscussion(associationId);
		entity.setModelId(modelId);
		entity.setCategoryId(modelDiscussion.getCategoryId());
		ModelDiscussionLocalServiceUtil.updateModelDiscussion(entity);
		return modelDiscussion;
	}

	public static long initModelDiscussionTopic(PortletRequest request) throws PortalException, SystemException, ReadOnlyException, ValidatorException, IOException {
		MBCategory cat = ModelLocalServiceHelper.createMBCategory(request,MBCategoryLocalServiceUtil.getSystemCategory().getCategoryId(),ModelConstants.DEFAULT_MODEL_DISCUSSION_TOPIC_NAME);
		PortletPreferences prefs = request.getPreferences();
		prefs.setValue(ModelConstants.DEFAULT_MODEL_DISCUSSION_TOPIC_ID, String.valueOf(cat.getCategoryId()));
		prefs.store();
		return cat.getCategoryId();
	}

	public static MBCategory createMBCategory(PortletRequest request,
			long parentCategoryId, String name) throws PortalException,
			SystemException {

		ServiceContext categoryServiceContext = ServiceContextFactory
				.getInstance(MBCategory.class.getName(), request);
		ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);
		long userid = themeDisplay.getUserId();
		MBCategory category = MBCategoryLocalServiceUtil.addCategory(userid, parentCategoryId,
				name, "", null, null, null, 0, false, null, null, 0, null,
				false, null, 0, false, null, null, false,
				categoryServiceContext);
		MBCategoryLocalServiceUtil.updateMBCategory(category);

		return category;

	}
}
