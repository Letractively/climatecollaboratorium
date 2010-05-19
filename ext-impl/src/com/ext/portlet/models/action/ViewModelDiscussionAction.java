/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */
package com.ext.portlet.models.action;

import java.util.Collection;

import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ext.portlet.models.ModelConstants;
import com.ext.portlet.models.model.ModelDiscussion;
import com.ext.portlet.models.service.ModelDiscussionLocalService;
import com.ext.portlet.models.service.ModelDiscussionLocalServiceUtil;
import com.ext.portlet.models.service.ModelDiscussionService;
import com.ext.portlet.models.service.ModelDiscussionServiceUtil;
import com.ext.portlet.models.service.base.ModelDiscussionLocalServiceBaseImpl;
import com.ext.portlet.models.service.impl.ModelDiscussionLocalServiceImpl;
import com.ext.portlet.models.service.impl.ModelDiscussionServiceImpl;
import com.ext.portlet.models.service.persistence.ModelDiscussionUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.service.MBCategoryLocalServiceUtil;
import com.liferay.portlet.messageboards.service.persistence.MBCategoryUtil;

/**
 * <p>
 * The action for viewing a model specific discussion
 * </p>
 * <p>
 * <strong>THREAD SAFETY:</strong> This is a struts action and it is thread safe.
 * </p>
 *
 * @author joshi
 * @version 1.0
 */
public class ViewModelDiscussionAction extends PortletAction {

	 private static Log _log = LogFactoryUtil.getLog(ViewModelDiscussionAction.class);


    /**
     * <p>
     * Renders the model discussion page.
     * </p>
     *
     * @param mapping the action mapping
     * @param form the form
     * @param portletConfig the portlet config
     * @param renderRequest the render request
     * @param renderResponse the response request
     * @return the action forward
     * @throws Exception if any error
     */
    public ActionForward render(ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
        RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {
        PortletPreferences preferences = renderRequest.getPreferences();


    	Long modelId = Long.parseLong(renderRequest.getParameter(ModelConstants.MODEL_ID));
    	String modelName = renderRequest.getParameter(ModelConstants.MODEL_NAME);

    	Collection<ModelDiscussion> modelDiscussions = ModelDiscussionLocalServiceUtil.findByModelId(modelId);
    	MBCategory cat = null;
    	modelName = (modelName==null)?"Discussion for model "+modelId:modelName;
    	if (modelDiscussions.size() == 0) {
    		_log.info("No discussion for model id "+modelId);

    		cat = ModelLocalServiceHelper.createDiscussionTopic(renderRequest,modelId,modelName);
    	} else {
    		ModelDiscussion discussion = modelDiscussions.iterator().next();
    		cat =  MBCategoryLocalServiceUtil.getCategory(discussion.getCategoryId());
    	}

    	if (cat == null) {
    		_log.warn("Could not retrieve category for model "+modelId);
    		return mapping.findForward("portlet.ext.models.error");
    	}

    	renderRequest.setAttribute(ModelConstants.MODEL_DISCUSSION,cat);
        return mapping.findForward("portlet.ext.models.view_discussion");
    }

}
