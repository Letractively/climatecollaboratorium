/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.migration.action;

import java.io.BufferedReader;
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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.w3c.dom.Document;

import com.ext.portlet.Activity.ActivityConstants;
import com.ext.portlet.Activity.model.ActivitySubscription;
import com.ext.portlet.Activity.service.ActivitySubscriptionLocalServiceUtil;
import com.ext.portlet.migration.MigrationHelper;
import com.ext.portlet.migration.MigrationConstants;
import com.ext.portlet.migration.NoSuchMappingException;
import com.ext.portlet.migration.NoSuchMigrationMappingException;
import com.ext.portlet.migration.entities.AlternativeEntity;
import com.ext.portlet.migration.entities.ArgumentEntity;
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
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBMessageServiceUtil;

public class ViewMigrationForm extends PortletAction {
	public final static String PLAN_QUESTION_ID = "qid1";
	
	public ActionForward render(ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {
		
        return mapping.findForward(MigrationConstants.VIEW_MIGRATION_FORM_FORWARD);
    }
	
	public void processAction(
            ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
            ActionRequest actionRequest, ActionResponse actionResponse)
    throws Exception {
		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);
		
		String description = ParamUtil.getString(actionRequest, "description");
		File file = uploadRequest.getFile("file");
		
		Reader reader = new BufferedReader(new FileReader(file));
		char[] cbuf = new char[8192];
		StringBuilder xmlContent = new StringBuilder();
		while (reader.ready()) {
			int len = reader.read(cbuf);
			xmlContent.append(cbuf, 0, len);	
		}
		long migrationDataId = CounterLocalServiceUtil.increment(MigrationData.class.getName());
		MigrationData migrationData = MigrationDataLocalServiceUtil.createMigrationData(migrationDataId);
		
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = builder.parse(file);
		
		MigrationManager manager = new MigrationManager(file);
        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		Date now = new Date();
	    migrationData.setCreatedDate(now);
	    migrationData.setModifiedDate(now);
	    migrationData.setXml(xmlContent.toString());
	    migrationData.setDescription(description);
	    migrationData.setUserId(themeDisplay.getUserId());
	    migrationData.setName(uploadRequest.getFileName("file"));
	       
	    migrationData.setUsers(manager.getUsers().size());
	    migrationData.setArguments(manager.getArguments().size());
	    migrationData.setVotes(manager.getVotes().size());
	    migrationData.setQuestions(manager.getQuestions().size());
	    migrationData.setAlternatives(manager.getAlternatives().size());
	    migrationData.setPlans(manager.getPlans().size());
		
		MigrationHelper.migrateUsers(manager, actionRequest);
		MigrationHelper.migratePlans(manager, actionRequest);
		MigrationHelper.migrateVotes(manager, actionRequest);
				
		MigrationDataLocalServiceUtil.addMigrationData(migrationData);
	}
}
