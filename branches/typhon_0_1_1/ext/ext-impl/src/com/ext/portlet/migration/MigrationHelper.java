/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.migration;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import com.caucho.quercus.annotation.This;
import com.ext.portlet.Activity.ActivityConstants;
import com.ext.portlet.debates.DebatesUtil;
import com.ext.portlet.migration.entities.AlternativeEntity;
import com.ext.portlet.migration.entities.ArgumentEntity;
import com.ext.portlet.migration.entities.MessageEntity;
import com.ext.portlet.migration.entities.MigrationManager;
import com.ext.portlet.migration.entities.QuestionEntity;
import com.ext.portlet.migration.entities.UserEntity;
import com.ext.portlet.migration.entities.VoteEntity;
import com.ext.portlet.migration.model.MigrationMapping;
import com.ext.portlet.migration.service.MigrationMappingLocalService;
import com.ext.portlet.migration.service.MigrationMappingLocalServiceUtil;
import com.ext.portlet.migration.service.persistence.MigrationMappingPK;
import com.ext.portlet.plans.NoSuchPlanVoteException;
import com.ext.portlet.plans.PlanConstants;
import com.ext.portlet.plans.PlanLocalServiceHelper;
import com.ext.portlet.plans.model.Plan;
import com.ext.portlet.plans.model.PlanVote;
import com.ext.portlet.plans.service.PlanLocalServiceUtil;
import com.ext.portlet.plans.service.PlanVoteLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.liveusers.LiveUsers;
import com.liferay.portal.model.ClassName;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageServiceUtil;
import com.liferay.portlet.ratings.model.RatingsEntry;
import com.liferay.portlet.ratings.service.RatingsEntryLocalService;
import com.liferay.portlet.ratings.service.RatingsEntryLocalServiceUtil;

public class MigrationHelper {
    private MigrationHelper() {
    }

    private static String ROWS_SELECT_QUERY = "//table_data[@name='PARAM1']/row";
    private static String PARAM1 = "PARAM1";
    private static String FIELDS_SELECT_QUERY = "field";
    private static String NAME_ATTRIBUTE = "name";
    private static String DEFAULT_MODEL_ID = "623";
    
    private static String DEFAULT_PLAN_THREAD_TOPIC = "What are the pros and cons of this plan?";
    
    private static String PLAN_THREAD_ID_MAPPING_SUFFIX = "-plan-thread";

    private static Log _log = LogFactoryUtil.getLog(MigrationHelper.class);
    
    private static long[] COMMUNITY_OWNER_ROLE_ID = { 10125 };

    public static <T> List<T> deserializeEntities(Document doc, String entityName, Class<T> entityClass)
            throws XPathExpressionException, InstantiationException, IllegalAccessException, SecurityException,
            NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        XPath xpath = XPathFactory.newInstance().newXPath();

        String queryString = ROWS_SELECT_QUERY.replaceAll(PARAM1, entityName);

        NodeList rowNodes = (NodeList) xpath.evaluate(queryString, doc.getDocumentElement(), XPathConstants.NODESET);

        List<T> returnList = new ArrayList<T>();

        for (int i = 0; i < rowNodes.getLength(); i++) {
            T entity = entityClass.newInstance();
            xpath = XPathFactory.newInstance().newXPath();

            Element rowNode = (Element) rowNodes.item(i);
            NodeList fieldNodes = (NodeList) xpath.evaluate(FIELDS_SELECT_QUERY, rowNode, XPathConstants.NODESET);

            for (int j = 0; j < fieldNodes.getLength(); j++) {
                Element fieldNode = (Element) fieldNodes.item(j);

                String name = fieldNode.getAttribute(NAME_ATTRIBUTE);
                String value = fieldNode.getTextContent().trim();
                if (value.toLowerCase().equals("null")) {
                    value = null;
                }

                String setterName = "set" + (name.toUpperCase().charAt(0)) + name.substring(1);

                try {
                    Method setter = entityClass.getMethod(setterName, String.class);
                    setter.invoke(entity, value);

                } catch (NoSuchMethodException e) {
                    // ignore
                }
            }

            returnList.add(entity);
        }
        return returnList;
    }

    public static boolean hasMapping(String entityName, String id) throws PortalException, SystemException {
        MigrationMappingPK migrationMappingPK = new MigrationMappingPK(entityName, id);
        try {
            MigrationMapping mapping = MigrationMappingLocalServiceUtil.getMigrationMapping(migrationMappingPK);
        } catch (NoSuchMappingException e) {
            return false;
        }
        return true;
    }

    public static MigrationMapping getMapping(String entityName, String id) throws PortalException, SystemException {
        MigrationMappingPK migrationMappingPK = new MigrationMappingPK(entityName, id);
        return MigrationMappingLocalServiceUtil.getMigrationMapping(migrationMappingPK);
    }

    public static long getNewIdFromMapping(String entityName, String id) throws PortalException, SystemException {
        MigrationMappingPK migrationMappingPK = new MigrationMappingPK(entityName, id);
        return MigrationMappingLocalServiceUtil.getMigrationMapping(migrationMappingPK).getNewId();
    }

    public static void createMessage(long categoryId, MessageEntity messageEntity, MBMessage parentMessage,
            PortletRequest request) throws PortalException, SystemException {
        
        _log.debug(MigrationHelper.class.getName() + ".createMessage() - method enter");
        
        ServiceContext serviceContext = ServiceContextFactory.getInstance(MBMessage.class.getName(), request);
        User owner = messageEntity.getOwner();
        serviceContext.setUserId(owner.getUserId());
        
        // set message type to debates debate message in order to prevent creation of activity
        serviceContext.setAttribute(ActivityConstants.MESSAGE_TYPE, ActivityConstants.DEBATES_DEBATE_MESSAGE);
        
        List<ObjectValuePair<String, byte[]>> files = new ArrayList<ObjectValuePair<String, byte[]>>();
        MBMessage message = null;
        if (messageEntity.getSubject().trim().equals("")) {
            _log.error("Message with id " + messageEntity.getEntityId() + " has empty subject, message will be ignored");
            return;
        }
        if (hasMapping(messageEntity.getEntityType(), messageEntity.getEntityId())) {
            long messageId = getNewIdFromMapping(messageEntity.getEntityType(), messageEntity.getEntityId());
            
            message = MBMessageServiceUtil.getMessage(messageId);
        }
        else {
            if (messageEntity.isThread()) {
                message = MBMessageServiceUtil.addMessage(categoryId, messageEntity.getSubject(), messageEntity.getBody(),
                        files, false, 0.0, serviceContext);
            } else {
                message = MBMessageServiceUtil.addMessage(parentMessage.getCategoryId(), parentMessage.getThreadId(),
                        parentMessage.getMessageId(), messageEntity.getSubject(), messageEntity.getBody(), files, false,
                        0.0, serviceContext);
            }
        }

        message.setSubject(messageEntity.getSubject());
        message.setBody(messageEntity.getBody());
        message.setUserId(owner.getUserId());
        message.setUserName(owner.getScreenName());
        message.setUserUuid(owner.getUuid());
        message.setCreateDate(messageEntity.getCreateDate());
        message.setModifiedDate(messageEntity.getModifiedDate());

        DebatesUtil.setMessageType(message.getMessageId(), messageEntity.getType());

        MBMessageLocalServiceUtil.updateMBMessage(message);

        addMigrationMapping(messageEntity.getEntityType(), messageEntity.getEntityId(), message.getMessageId());

        for (MessageEntity childMessage : messageEntity.getChildren()) {
            createMessage(categoryId, childMessage, message, request);
        }

        _log.debug(MigrationHelper.class.getName() + ".createMessage() - method exit");
    }

    public static void addMigrationMapping(String entity, String oldId, long newId) throws SystemException {
        MigrationMappingPK migrationMappingPK = new MigrationMappingPK(entity, oldId);
        MigrationMapping mapping = MigrationMappingLocalServiceUtil.createMigrationMapping(migrationMappingPK);
        mapping.setNewId(newId);
        Date now = new Date();
        mapping.setCreateDate(now);
        mapping.setModifiedDate(now);
        MigrationMappingLocalServiceUtil.updateMigrationMapping(mapping);
    }

    public static void migrateUsers(MigrationManager manager, PortletRequest request) throws PortalException,
            SystemException {
        _log.debug(MigrationHelper.class.getName() + ".migrateUsers() - method enter");

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        Role defaultRole = RoleLocalServiceUtil.getRole(themeDisplay.getCompanyId(), "User");
        long[] defaultRoles = new long[] { defaultRole.getRoleId() };

        int emailId = 0;
        for (UserEntity userEntity : manager.getUsers()) {
            Date now = new Date();
            _log.debug("Migrating user " + userEntity);
            if (hasMapping(userEntity.NAME,userEntity.getId())) {
            	_log.debug("User already exists, skipping entity");
            }

            if (userEntity.getEmail() == null || userEntity.getEmail().trim().equals("")) {
                userEntity.setEmail(emailId + "mail@mit.edu");
            }

            User user = null;
            try {
                user = UserLocalServiceUtil.getUserByScreenName(themeDisplay.getCompanyId(), userEntity.getName());
            } catch (NoSuchUserException e) {
                // ignore
            }
            try {
                user = UserLocalServiceUtil.getUserByEmailAddress(themeDisplay.getCompanyId(), userEntity.getEmail());
            } catch (NoSuchUserException e) {
                // ignore
            }
            if (user == null) {

                String screenName = userEntity.getName().replaceAll("_", "-");

                ServiceContext serviceContext = ServiceContextFactory.getInstance(User.class.getName(), request);
                user = UserLocalServiceUtil.addUser(themeDisplay.getUserId(), themeDisplay.getCompanyId(), false,
                        userEntity.getPassword(), userEntity.getPassword(), false, screenName, userEntity.getEmail(),
                        "", themeDisplay.getLocale(), userEntity.getName(), userEntity.getName(), userEntity.getName(),
                        0, 0, true, 1, 1, 1970, "", new long[0], new long[0], defaultRoles, new long[0], false,
                        serviceContext);

            } else {
                _log.debug("User with given name or email already exists, only mapping will be added");
            }

            if (!hasMapping(userEntity.NAME, userEntity.getId())) {
                addMigrationMapping(userEntity.NAME, userEntity.getId(), user.getUserId());
            }
        }
        _log.debug(MigrationHelper.class.getName() + ".migrateUsers() - method exit");
    }

    public static void migratePlans(MigrationManager manager, ActionRequest actionRequest) throws PortalException,
            SystemException {
        _log.debug(MigrationHelper.class.getName() + ".migratePlans() - method enter");
        MBMessage planThread = null;
        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
        long currentUserId = themeDisplay.getUserId();

        for (AlternativeEntity planEntity : manager.getPlans()) {
            _log.debug("Handling plan: " + planEntity);
            MessageEntity planMainMessage = MessageEntity.newInstance(planEntity, manager);
            User planCreator = UserLocalServiceUtil.getUser(getNewIdFromMapping(UserEntity.NAME, planEntity.getUser()));
            Plan plan = null;
            if (hasMapping(AlternativeEntity.NAME, planEntity.getId())) {
             //  _log.debug("Plan already exists; skipping");
              //  continue;
                plan = PlanLocalServiceUtil.getPlan(getNewIdFromMapping(AlternativeEntity.NAME, planEntity.getId()));
                
                long threadId = getNewIdFromMapping(AlternativeEntity.NAME, planEntity.getId() + PLAN_THREAD_ID_MAPPING_SUFFIX);
                planThread = MBMessageLocalServiceUtil.getMBMessage(threadId);
            }
            else {
                _log.debug("New plan will be created, subject: " + planEntity.getInformation());
                long climatePlanId = CounterLocalServiceUtil.increment(Plan.class.getName());

                plan = PlanLocalServiceUtil.createPlan(climatePlanId);
                plan.setName(planMainMessage.getSubject());
                plan.setShortcontent(planMainMessage.getBody());
                PlanLocalServiceHelper.initializePlan(plan, actionRequest);

                long scenarioId = Long.parseLong(planEntity.getUserContent().replaceAll("Scenario=", ""));
                //plan.setModelId(DEFAULT_MODEL_ID);
                plan.setScenarioId(String.valueOf(scenarioId));
                /*
                plan.setCO2(0.0);
                plan.setDamageCostAvg(0.0);
                plan.setDamageCostStdDev(0.0);
                plan.setDevelopedEmissions(0.0);
                plan.setDevelopingAEmissions(0.0);
                plan.setDevelopingBEmissions(0.0);
                plan.setGlobalEmissions(0.0);
                plan.setMitigationCostAvg(0.0);
                plan.setMitigationCostStdDev(0.0);
                plan.setModifiedDate(new Date(Long.parseLong(planEntity.getUpdated())));
                plan.setSeaLevelRise(0);
                plan.setTemperatureChange(0.0);
*/
                plan.setCreateDate(new Date(Long.parseLong(planEntity.getCreated())));
                
                ServiceContext serviceContext = ServiceContextFactory.getInstance(MBMessage.class.getName(), actionRequest);
                // set message type to debates debate message in order to prevent creation of activity
                serviceContext.setAttribute(ActivityConstants.MESSAGE_TYPE, ActivityConstants.DEBATES_DEBATE_MESSAGE);
                serviceContext.setUserId(planCreator.getUserId());
                List<ObjectValuePair<String, byte[]>> files = new ArrayList<ObjectValuePair<String, byte[]>>();
                
                planThread = MBMessageServiceUtil.addMessage(plan.getMBCategoryId(), DEFAULT_PLAN_THREAD_TOPIC, 
                        DEFAULT_PLAN_THREAD_TOPIC, files, false, 0.0, serviceContext);
                
                addMigrationMapping(AlternativeEntity.NAME, planEntity.getId() + PLAN_THREAD_ID_MAPPING_SUFFIX, planThread.getMessageId());
            }
            
            for (MessageEntity planChildMessage: planMainMessage.getChildren()) {
                createMessage(plan.getMBCategoryId(), planChildMessage, planThread, actionRequest);
            }
        
            // get plan author

            plan.setUserId(planCreator.getUserId());
            plan.setUserName(planCreator.getFullName());
            plan.setUserScreenName(planCreator.getScreenName());
            
            if (planCreator.getUserId() != currentUserId) {
                try {
                    UserLocalServiceUtil.addGroupUsers(plan.getChildGroupId(), new long[] {planCreator.getUserId()});
                    UserGroupRoleLocalServiceUtil.addUserGroupRoles(planCreator.getUserId(), plan.getChildGroupId(), COMMUNITY_OWNER_ROLE_ID);
                } catch (Exception e) {
                    _log.error(e);
                }
            }
            
            

            PlanLocalServiceUtil.updatePlan(plan);

            // create mapping for created/updated plan
            addMigrationMapping(AlternativeEntity.NAME, planEntity.getId(), plan.getPlanId());
            _log.debug("plan added: " + plan);
        }

        _log.debug(MigrationHelper.class.getName() + ".migratePlans() - method exit");
    }
    
    public static void migrateVotes(MigrationManager manager, ActionRequest actionRequest) throws PortalException,
        SystemException {
        _log.debug(MigrationHelper.class.getName() + ".migrateVotes() - method enter");
        
        for (VoteEntity vote: manager.getVotes()) {
            if (vote.getAlternative() != null && !vote.getAlternative().trim().equals("") &&
                    vote.getUser() != null && ! vote.getUser().trim().equals("")) {
                if (! hasMapping(UserEntity.NAME, vote.getUser()) || 
                        ! hasMapping(AlternativeEntity.NAME, vote.getAlternative())) {
                    _log.debug("can't find mapping for user or alternative referenced from vote");
                    continue;
                }
                long userId = getNewIdFromMapping(UserEntity.NAME, vote.getUser());
                long entityId = getNewIdFromMapping(AlternativeEntity.NAME, vote.getAlternative());
                if (userId == 0 || entityId == 0) {
                    continue;
                }
                
                AlternativeEntity alternative = manager.getAlternativeById().get(vote.getAlternative());
                if (alternative.isPlan()) {
                    _log.debug("Adding vote for plan");
                    try {
                        PlanVote planVote = PlanVoteLocalServiceUtil.getPlanVote(userId);
                        PlanVoteLocalServiceUtil.deletePlanVote(userId);
                    } catch (NoSuchPlanVoteException e) {
                        // ignore
                    }
                    PlanVote planVote = PlanVoteLocalServiceUtil.createPlanVote(userId);
                    planVote.setPlanId(entityId);
                    PlanVoteLocalServiceUtil.addPlanVote(planVote);
                } 
                else {
                    _log.debug("Adding vote for position");
                    RatingsEntry entry = null;
                    MBMessage message = MBMessageLocalServiceUtil.getMBMessage(entityId);
                    try {
                        entry = RatingsEntryLocalServiceUtil.getEntry(userId, MBMessage.class.getName(), message.getMessageId());
                    } catch (Exception e) {
                    }
                    if (entry == null) {
                        long entryId = CounterLocalServiceUtil.increment(RatingsEntry.class.getName());
                        entry = RatingsEntryLocalServiceUtil.createRatingsEntry(entryId);
                        entry.setClassPK(entityId);
                        entry.setUserId(userId);
                        ClassName className = ClassNameLocalServiceUtil.getClassName(MBMessage.class.getName());
                        entry.setClassNameId(className.getClassNameId());
                        RatingsEntryLocalServiceUtil.addRatingsEntry(entry);
                    }
                }
            }
            
        }
        
        _log.debug(MigrationHelper.class.getName() + ".migrateVotes() - method exit");
    }

}
