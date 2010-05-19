/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.migration.action;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.derby.impl.sql.compile.CreateTableNode;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.w3c.dom.Document;

import com.ext.portlet.Activity.ActivityConstants;
import com.ext.portlet.Activity.model.ActivitySubscription;
import com.ext.portlet.Activity.service.ActivitySubscriptionLocalServiceUtil;
import com.ext.portlet.debates.DebatesConstants;
import com.ext.portlet.debates.DebatesUtil;
import com.ext.portlet.migration.MigrationHelper;
import com.ext.portlet.migration.MigrationConstants;
import com.ext.portlet.migration.NoSuchMappingException;
import com.ext.portlet.migration.NoSuchMigrationMappingException;
import com.ext.portlet.migration.entities.AlternativeEntity;
import com.ext.portlet.migration.entities.ArgumentEntity;
import com.ext.portlet.migration.entities.MessageEntity;
import com.ext.portlet.migration.entities.MigrationManager;
import com.ext.portlet.migration.entities.QuestionEntity;
import com.ext.portlet.migration.entities.UserEntity;
import com.ext.portlet.migration.entities.VoteEntity;
import com.ext.portlet.migration.model.MigrationData;
import com.ext.portlet.migration.model.MigrationMapping;
import com.ext.portlet.migration.service.MigrationDataLocalServiceUtil;
import com.ext.portlet.migration.service.MigrationMappingLocalService;
import com.ext.portlet.migration.service.MigrationMappingLocalServiceUtil;
import com.ext.portlet.migration.service.persistence.MigrationMappingPK;
import com.ext.portlet.plans.PlanLocalServiceHelper;
import com.ext.portlet.plans.model.Plan;
import com.ext.portlet.plans.service.PlanLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ContactLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleLocalService;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBCategoryLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageServiceUtil;

public class MigrateDebates extends PortletAction {
	public final static String PLAN_QUESTION_ID = "qid1";
	
	public ActionForward render(ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {
		
		long migrationId = ParamUtil.getLong(renderRequest, "migrationId");
		
		MigrationManager manager = new MigrationManager(migrationId);
		
		renderRequest.setAttribute("questions", manager.getQuestions());
		
        return mapping.findForward(MigrationConstants.MIGRATE_DEBATES_FORWARD);
    }
	
	public void processAction(
            ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
            ActionRequest actionRequest, ActionResponse actionResponse)
    throws Exception {
		long migrationId = ParamUtil.getLong(actionRequest, "migrationId");
		MigrationManager migrationManager = new MigrationManager(migrationId);
		
		List<QuestionEntity> questions = migrationManager.getQuestions();
		
		for(QuestionEntity question: questions) {
			MessageEntity message = MessageEntity.newInstance(question, migrationManager);
			boolean migrate = ParamUtil.getBoolean(actionRequest, question.getId() + "migrate");
            boolean update = ParamUtil.getBoolean(actionRequest, question.getId() + "update");
			String migrationType = ParamUtil.getString(actionRequest, question.getId() + "migrationType");
			long parentCategoryId = ParamUtil.getLong(actionRequest, question.getId() + "parentCategoryId");
			
			if (migrate) {
				if (migrationType.equals("debate")) {
				    
				    if (MigrationHelper.hasMapping(message.getEntityType(), message.getEntityId())) {
				        long messageId = MigrationHelper.getNewIdFromMapping(message.getEntityType(), message.getEntityId());
				        MBMessage debateMessage = MBMessageLocalServiceUtil.getMBMessage(messageId);
				        
				        parentCategoryId = debateMessage.getCategoryId();
				        // update question
				        MBCategory questionCategory = MBCategoryLocalServiceUtil.getCategory(debateMessage.getCategory().getParentCategoryId());
				        questionCategory.setName(message.getSubject());
				        
				        MBCategoryLocalServiceUtil.updateMBCategory(questionCategory);
				    }
				    else {
				        MBCategory questionCategory = DebatesUtil.createMBCategory(actionRequest, parentCategoryId, message.getSubject());
					
				        MBCategory debateCategory = DebatesUtil.createMBCategory(actionRequest, questionCategory.getCategoryId(), DebatesConstants.ISSUE_DEBATE_CATEGORY_NAME);
				        DebatesUtil.createMBCategory(actionRequest, questionCategory.getCategoryId(), DebatesConstants.ISSUE_DISCUSSION_CATEGORY_NAME);
				        parentCategoryId = debateCategory.getCategoryId();
				    }
					
				}
				else if (migrationType.equals("discussion")) {
					MBCategory mainCategory = DebatesUtil.getMainCategory(actionRequest);
					MBCategory discussionCategory = DebatesUtil.getSubcategory(mainCategory.getCategoryId(), DebatesConstants.ISSUE_DISCUSSION_CATEGORY_NAME);
					parentCategoryId = discussionCategory.getCategoryId();
					
				}
				else if (migrationType.equals("thread")) {
					// do nothing
				}
				MigrationHelper.createMessage(parentCategoryId, message, null, actionRequest);
			}
			else if (update) {
			    MigrationHelper.createMessage(0, message, null, actionRequest);
			}
		}
		
        MigrationHelper.migrateVotes(migrationManager, actionRequest);	
	}
}
