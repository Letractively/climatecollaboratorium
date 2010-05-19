/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */
package com.ext.portlet.debates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

import com.ext.portlet.debates.model.DebateDiscussionMap;
import com.ext.portlet.debates.service.DebateDiscussionMapLocalServiceUtil;
import com.ext.portlet.plans.PlanLocalServiceHelper;
import com.ext.portlet.plans.model.PlanPosition;
import com.ext.portlet.plans.service.PlanPositionLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.messageboards.NoSuchCategoryException;
import com.liferay.portlet.messageboards.NoSuchThreadException;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBMessageFlag;
import com.liferay.portlet.messageboards.model.MBThread;
import com.liferay.portlet.messageboards.model.MBTreeWalker;
import com.liferay.portlet.messageboards.model.impl.MBThreadImpl;
import com.liferay.portlet.messageboards.service.MBCategoryLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBCategoryServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageFlagLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageServiceUtil;
import com.liferay.portlet.messageboards.service.MBThreadLocalServiceUtil;
import com.liferay.portlet.messageboards.service.persistence.MBThreadUtil;

/**
 * Utility class for Debates portlet.
 *
 * Handles tasks like retrieval of message flags, setting message flags,
 * retrieval of topic messages etc.
 *
 * version 1.1: added method to remove references from plans to given position
 *
 * @author janusz.p
 * @version 1.1
 * @since 1.0
 */
public class DebatesUtil {


	private static Log _log = LogFactoryUtil.getLog(DebatesUtil.class);
	
	

    /**
     * It's a utility class, it shouldn't be instantiated.
     */
    private DebatesUtil() {
    }

    /**
     * Returns list of message flags.
     *
     * @param messageId
     *            ID of message which flags should be retrieved
     * @return List of message flags
     * @throws SystemException
     *             in case of any error
     */
    public static List<MBMessageFlag> getMessageFlags(long messageId) throws SystemException {

        // create custom query and execute it
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MBMessageFlag.class);
        Criterion criterionMessageId = RestrictionsFactoryUtil.eq(DebatesConstants.MESSAGE_ID, messageId);
        dynamicQuery.add(criterionMessageId);

        List<Object> tmpFlags = MBMessageFlagLocalServiceUtil.dynamicQuery(dynamicQuery);

        // cast retrieved objects to MBMessageFlag
        List<MBMessageFlag> flags = new ArrayList<MBMessageFlag>();
        for (Object tmpFlag : tmpFlags) {
            flags.add((MBMessageFlag) tmpFlag);
        }
        return flags;
    }
    
    

    /**
     * Retrieves message type.
     *
     * @param messageId
     *            ID of message which type is to be retrieved/
     * @return message type
     * @throws SystemException
     *             in case of an error when retrieving message flags
     * @throws MessageTypeException
     *             if message has no type associated
     */
    public static int getMessageType(long messageId) throws SystemException, MessageTypeException {
        List<MBMessageFlag> flags = getMessageFlags(messageId);

        for (MBMessageFlag flag : flags) {
            for (int type : DebatesConstants.MESSAGE_TYPES) {
                if (flag.getFlag() == type) {
                    return type;
                }
            }
        }

        throw new MessageTypeException("Message "+messageId+"has no type associated.");
    }

    /**
     * Returns message type name.
     *
     * @param messageId
     *            ID of message which type is to be retrieved/
     * @return message type name
     * @throws SystemException
     *             in case of an error when retrieving message flags
     * @throws MessageTypeException
     *             if message has no type associated
     */
    public static String getMessageTypeName(long messageId) throws SystemException, MessageTypeException {
        int type = getMessageType(messageId);

        Map<Integer, String> typeNamesMap = new HashMap<Integer, String>();
        typeNamesMap.put(DebatesConstants.ISSUE_MSG_TYPE, DebatesConstants.ISSUE_MSG_TYPE_NAME);
        typeNamesMap.put(DebatesConstants.POSITION_MSG_TYPE, DebatesConstants.POSITION_MSG_TYPE_NAME);
        typeNamesMap.put(DebatesConstants.ARGUMENT_CON_MSG_TYPE, DebatesConstants.ARGUMENT_CON_MSG_TYPE_NAME);
        typeNamesMap.put(DebatesConstants.ARGUMENT_PRO_MSG_TYPE, DebatesConstants.ARGUMENT_PRO_MSG_TYPE_NAME);

        return typeNamesMap.get(type);
    }

    /**
     * Sets message type to passed value.
     *
     * @param messageId
     *            ID of message which type is to be set
     * @param messageType
     *            type that should be associated with message
     * @throws SystemException
     *             in case of any problem when retrieving message type
     * @throws PortalException
     *             in case of any problem with portal framework
     */
    public static void setMessageType(long messageId, int messageType) throws SystemException, PortalException {
        // check if message type flag has been already set
        List<MBMessageFlag> messageFlags = getMessageFlags(messageId);

        MBMessage message = MBMessageLocalServiceUtil.getMessage(messageId);

        // array of message types
        int types[] = { DebatesConstants.ISSUE_MSG_TYPE, DebatesConstants.POSITION_MSG_TYPE,
                DebatesConstants.ARGUMENT_PRO_MSG_TYPE, DebatesConstants.ARGUMENT_CON_MSG_TYPE };

        // search for message flags with Flag equal one of message types
        MBMessageFlag flag = null;
        for (MBMessageFlag messageFlag : messageFlags) {
            for (int type : types) {
                if (messageFlag.getFlag() == type) {
                    flag = messageFlag;
                }
            }
        }

        // if nothing has been found create new flag and assign it to message
        if (flag == null) {
            long messageFlagId = CounterLocalServiceUtil.increment(MBMessageFlag.class.getName());
            flag = MBMessageFlagLocalServiceUtil.createMBMessageFlag(messageFlagId);
            flag.setMessageId(messageId);
            flag.setThreadId(message.getThreadId());
            flag.setUserId(message.getUserId());
            flag.setUserUuid(message.getUserUuid());
        }

        // store flag in persistent storage
        flag.setFlag(messageType);
        flag.setModifiedDate(new Date());
        MBMessageFlagLocalServiceUtil.updateMBMessageFlag(flag);
    }

    /**
     * Returns main category of Debates portlet. Category Id is taken from
     * portlet preferences.
     *
     * @param request
     *            portlet request from which parameters should be taken.
     * @return message boards category holding Debates portlet content
     * @throws Exception passed up in case of framework error
     */
    public static MBCategory getMainCategory(PortletRequest request) throws Exception {
        PortletPreferences preferences = request.getPreferences();
        long categoryId = Long.parseLong(preferences.getValue(DebatesConstants.CATEGORY_ID, "-1"));
        MBCategory result = null;
        if (categoryId > 0) {
        	try {
        		result = MBCategoryLocalServiceUtil.getCategory(categoryId);
        		return result;
        	} catch (NoSuchCategoryException e) {
        		categoryId = -1;
        	}
        }
        if (categoryId <= 0) {
            ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
            long groupId = themeDisplay.getScopeGroupId();

            List<MBCategory> categories = MBCategoryLocalServiceUtil.getCategories(groupId, 0);

            for (MBCategory category : categories) {
                if (category.getName().equals(DebatesConstants.DEFAULT_DEBATES_CATEGORY_NAME)) {
                    categoryId = category.getCategoryId();
                }
            }
            if (categoryId <= 0) {
                result = createMBCategory(request, 0, DebatesConstants.DEFAULT_DEBATES_CATEGORY_NAME);
                categoryId =result.getCategoryId();
            }
            initializePortlet(request, categoryId);
        }

        preferences.setValue(DebatesConstants.CATEGORY_ID, Long.toString(categoryId));

        return  MBCategoryLocalServiceUtil.getCategory(categoryId);
    }

    /**
     * Returns subcategory with given name.
     *
     * @param category
     *            parent category
     * @param type
     *            name/type of subcategory that should be retrieved
     * @return subcategory with given name
     * @throws SystemException
     *             in case of any error in portal framework
     * @throws NoSuchCategoryException
     *             there is no subcategory with given name
     */
    public static MBCategory getSubcategory(MBCategory category, String type) throws SystemException,
            NoSuchCategoryException {

        List<MBCategory> categories = null;
        if (category.getCategoryId() == 0) {
            categories = MBCategoryLocalServiceUtil.getCategories(category.getGroupId());
        }
        else {
            categories = MBCategoryLocalServiceUtil.getCategories(category.getGroupId(), category.getCategoryId());
        }
        // get child categories
        for (MBCategory tmpCategory : categories) {
            if (tmpCategory.getName().equals(type)) {
                return tmpCategory;
            }
        }
        // nothing has been found
        throw new NoSuchCategoryException("Can't find category named \"" + type + "\" within category: "
                + category.getCategoryId());
    }

    /**
     * Returns subcategory with given name.
     *
     * @param categoryId
     *            ID of parent category
     * @param type
     *            name/type of subcategory that should be retrieved
     * @return subcategory with given name
     * @throws SystemException
     *             in case of any error in portal framework
     * @throws NoSuchCategoryException
     *             there is no subcategory with given name
     */
    public static MBCategory getSubcategory(long categoryId, String type) throws SystemException, PortalException {
        MBCategory category = MBCategoryLocalServiceUtil.getCategory(categoryId);
        return getSubcategory(category, type);
    }

    /**
     * Checks if given message type is valid for given parent message.
     *
     * @param parentMessageId
     *            ID of parent message
     * @param messageType
     *            message type that should be checked
     * @throws MessageTypeException
     *             if message type isn't allowed for given parent message
     * @throws SystemException
     *             in case of any error with portal framework
     */
    public static void validateMessageType(long parentMessageId, int messageType) throws MessageTypeException,
            SystemException {

        // check if parent message exists
        if (parentMessageId <= 0) {
            // if there is no parent message we can only create new issue
            if (messageType != DebatesConstants.ISSUE_MSG_TYPE) {
                throw new MessageTypeException();
            }
        } else {
            // get parent message type
            int parentMessageType = DebatesUtil.getMessageType(parentMessageId);

            int allowedTypes[] = {};
            switch (parentMessageType) {
            case DebatesConstants.ISSUE_MSG_TYPE:
                // if parent message is an issue only position is allowed as a
                // child message
                allowedTypes = new int[] { DebatesConstants.POSITION_MSG_TYPE };
                break;
            case DebatesConstants.POSITION_MSG_TYPE:
            case DebatesConstants.ARGUMENT_CON_MSG_TYPE:
            case DebatesConstants.ARGUMENT_PRO_MSG_TYPE:
                // if parent message is position or argument then arguments are
                // allowed as child messages
                allowedTypes = new int[] { DebatesConstants.ARGUMENT_CON_MSG_TYPE,
                        DebatesConstants.ARGUMENT_PRO_MSG_TYPE };
                break;
            }

            // check if passed type belongs to allowed types array
            boolean isTypeAllowed = false;
            for (int allowedType : allowedTypes) {
                if (allowedType == messageType) {
                    isTypeAllowed = true;
                }
            }

            if (!isTypeAllowed) {
                throw new MessageTypeException("Inappropriate message type: " + messageType);
            }
        }

    }

    /**
     * Returns list of child messages of given message.
     *
     * @param message
     *            parent message
     * @return list of child messages
     * @throws PortalException
     *             passed up in case of an error in portal framework
     * @throws SystemException
     *             passed up in case of an error in portal framework
     */
    public static List<MBMessage> getChildMessages(MBMessage message) throws PortalException, SystemException {
        MBTreeWalker treeWalker = MBMessageLocalServiceUtil.getMessageDisplay(message.getMessageId(),
                MBThreadImpl.THREAD_VIEW_TREE).getTreeWalker();
        
        treeWalker.getMessages();
        
        return treeWalker.getChildren(message);
        
    }
    
    
    public static List<MBMessage> getAllChildren(MBMessage message) throws PortalException, SystemException {
        MBTreeWalker treeWalker = MBMessageLocalServiceUtil.getMessageDisplay(message.getMessageId(),
                MBThreadImpl.THREAD_VIEW_TREE).getTreeWalker();
        
        return treeWalker.getMessages();
        
    }

    /**
     * Returns message representing Debate.
     *
     * @param categoryId
     *            id of debate category.
     * @return message representing Debate
     * @throws SystemException
     *             in case of any error in portal framework
     */
    public static MBMessage getDebateMessage(long categoryId) throws SystemException {
        List<MBMessage> msgs = MBMessageLocalServiceUtil.getCategoryMessages(categoryId, 0, 1);
        if (msgs.size() > 0) return msgs.get(0);
        else return null;
    }
    
    /**
     * Get the discussion thread associated with this debate post;  create the thread if it doesn't exist 
     * 
     * @param debateMessageId
     * @return
     * @throws SystemException
     * @throws PortalException 
     */
    public static MBMessage getDebateMessageThread(long debateMessageId,PortletRequest request) throws SystemException, PortalException {
    	long threadid = -1;
    	MBMessage result = null;

    	DebateDiscussionMap map = null;
    	try {
    		map = DebateDiscussionMapLocalServiceUtil.getDebateDiscussionMap(debateMessageId);
    		threadid = map.getDiscussionThreadId();
    	} catch (NoSuchDebateDiscussionMapException ex) {
    		//ignore
    	}
    	
    	if (threadid < 0) {
    		return null;
    	} else {
    		try {
	    		MBThread thread = MBThreadLocalServiceUtil.getMBThread(threadid);
	    		result = MBMessageLocalServiceUtil.getMBMessage(thread.getRootMessageId());
    		} catch (NoSuchThreadException e) {
    			DebateDiscussionMapLocalServiceUtil.deleteDebateDiscussionMap(map);
    		}
    	}
    	return result;
    }
    
    public static MBMessage createDebateMessageThread(long debateMessageId,PortletRequest request) throws SystemException,PortalException {
    	MBMessage result = getDebateMessageThread(debateMessageId,request);
    	
    	if (result!= null) {
    		return result;
    	}
    	
    	
    	MBMessage mesg = MBMessageLocalServiceUtil.getMBMessage(debateMessageId);
    
    	MBCategory discussionCategory = getSubcategory(mesg.getCategory().getParentCategoryId(),DebatesConstants.ISSUE_DISCUSSION_CATEGORY_NAME);
    	String subject = mesg.getSubject();
    	String body = mesg.getBody();
    	ServiceContext serviceContext = ServiceContextFactory.getInstance(
    			MBMessage.class.getName(), request);
    	List<ObjectValuePair<String, byte[]>> x = new ArrayList<ObjectValuePair<String, byte[]>>();
    	result = MBMessageServiceUtil.addMessage(discussionCategory.getCategoryId(), subject, body,x, false, 0, serviceContext);			
    	result.setUserId(mesg.getUserId());
        MBMessageLocalServiceUtil.updateMBMessage(result);
    	
    	setMessageType(result.getMessageId(),getMessageType(mesg.getMessageId()));
    	DebateDiscussionMap mapping = DebateDiscussionMapLocalServiceUtil.createDebateDiscussionMap(debateMessageId);
    	mapping.setDiscussionThreadId(result.getThreadId());
    	DebateDiscussionMapLocalServiceUtil.updateDebateDiscussionMap(mapping);
    	
    	
    	
    	
    	return result;
    	
    }

    /**
     * Returns type of view that should be displayed (debate index view or
     * debate summary view)
     *
     * @param request
     *            request from which portlet parameters should be retrieved
     * @return type of view that should be displayed
     * @throws Exception
     *            passed up in case of framework error
     */
    public static String getViewType(PortletRequest request) throws Exception {

        long baseCategoryId = ParamUtil.getLong(request, DebatesConstants.BASE_CATEGORY_ID, 0);

        // if there is no base category parameter try to retrieve it from
        // messageId or categoryId
        if (baseCategoryId == 0) {
            baseCategoryId = getBaseCategoryId(request);
        }

        // check if base category id is the same as id of main category
        MBCategory mainCategory = getMainCategory(request);

        if (baseCategoryId != 0 && baseCategoryId != mainCategory.getCategoryId()) {
            return DebatesConstants.DEBATE_SUMMARY_VIEW;
        }
        return DebatesConstants.DEBATE_INDEX_VIEW;
    }

    /**
     * Returns id of base category (base category is either main Debates portlet
     * category or category holding concrete debate).
     *
     * @param request
     *            request from which portlet parameters should be retrieved
     * @return id of base category
     * @throws SystemException
     *             passed up in case of an error in portal framework
     * @throws PortalException
     *             passed up in case of an error in portal framework
     */
    public static long getBaseCategoryId(PortletRequest request) throws SystemException, PortalException {
        long categoryId = ParamUtil.getLong(request, DebatesConstants.CATEGORY_ID);
        long messageId = ParamUtil.getLong(request, DebatesConstants.MESSAGE_ID);

        // if category has been set then we are in Duscussion subcategory, get
        // parent id
        if (categoryId > 0) {
            MBCategory category = MBCategoryLocalServiceUtil.getCategory(categoryId);
            return category.getParentCategoryId();
        }

        // if message is set then we are in Discussion, get parent category id
        if (messageId > 0) {
            MBMessage message = MBMessageLocalServiceUtil.getMBMessage(messageId);
            return message.getCategory().getParentCategoryId();
        }
        return 0;
    }

    /**
     * Returns list of categories representing topics.
     *
     * @param request
     *            servlet request
     * @return list of categories representing topics
     * @throws Exception
     *            passed up in case of framework error
     */
    public static List<MBCategory> getTopics(PortletRequest request) throws Exception {
        // topics are child categories in debate category
        MBCategory mainCategory = DebatesUtil.getMainCategory(request);
        MBCategory topicsCategory = DebatesUtil.getSubcategory(mainCategory,
                DebatesConstants.ISSUE_DEBATE_CATEGORY_NAME);
        List<MBCategory> categories = new ArrayList<MBCategory>(MBCategoryLocalServiceUtil.getCategories(topicsCategory.getGroupId(),
                topicsCategory.getCategoryId()));
        
        Collections.sort(categories, new Comparator<MBCategory>() {
            public int compare(MBCategory a, MBCategory b) {
                return a.getThreadCount() - b.getThreadCount();
            }
            
        });
        return categories;
    }

    /**
     * Returns topics count.
     *
     * @param request
     *            servlet request
     * @return number of topics
     * @throws Exception
     *            passed up in case of framework error
     */
    public static int getTopicsCount(PortletRequest request) throws Exception {
        // topics are child categories in debate category
        MBCategory mainCategory = DebatesUtil.getMainCategory(request);
        MBCategory topicsCategory = DebatesUtil.getSubcategory(mainCategory,
                DebatesConstants.ISSUE_DEBATE_CATEGORY_NAME);
        int categoriesCount = MBCategoryLocalServiceUtil.getCategoriesCount(topicsCategory.getGroupId(), topicsCategory
                .getCategoryId());

        return categoriesCount;
    }

    /**
     * Retrieves issues from given topic (category).
     *
     * @param request
     *            servlet request
     * @param topicId
     *            topic id from which issues are to be retrieved
     * @param start
     *            index of first issue that is to be retrieved
     * @param end
     *            index of last issue that is to be retrieved
     * @return list of issues contained within given topic
     * @throws SystemException
     *             passed up in case of an error in portal framework
     */
    public static List<MBCategory> getIssues(PortletRequest request, long topicId, int start, int end)
            throws SystemException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long groupId = themeDisplay.getScopeGroupId();

        List<MBCategory> issues = MBCategoryLocalServiceUtil.getCategories(groupId, topicId, start, end);
        return issues;
    }

    /**
     * Retrieves issues count from given topic (category).
     *
     * @param request
     *            servlet request
     * @param topicId
     *            topic id from which issues are to be retrieved
     * @return number of issues contained within given topic
     * @throws SystemException
     *             passed up in case of an error in portal framework
     */
    public static int getIssuesCount(PortletRequest request, long topicId) throws SystemException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long groupId = themeDisplay.getScopeGroupId();
        int topicsCount = MBCategoryLocalServiceUtil.getCategoriesCount(groupId, topicId);

        return topicsCount;
    }

    /**
     * Creates new message board category with given name and placed under
     * parent category.
     *
     * @param request
     *            action request
     * @param parentCategoryId
     *            id of parent category
     * @param name
     *            name of category that is to be created
     * @return created category
     * @throws PortalException
     *             passed up in case of an error in portal framework
     * @throws SystemException
     *             passed up in case of an error in portal framework
     */
    public static MBCategory createMBCategory(PortletRequest request, long parentCategoryId, String name)
            throws PortalException, SystemException {

        ServiceContext categoryServiceContext = ServiceContextFactory.getInstance(MBCategory.class.getName(), request);

        return MBCategoryServiceUtil.addCategory(parentCategoryId, name, "", null, null, null, 0, false, null, null, 0,
                null, false, null, 0, false, null, null, false, categoryServiceContext);

    }

    /**
     * Returns map with debate statistics (post numberr, positions number etc.)
     *
     * @param debate
     *            category representing debate
     * @return map with statistics
     * @throws PortalException
     *             passed up in case of an error in portal framework
     * @throws SystemException
     *             passed up in case of an error in portal framework
     */
    public static Map<String, Object> getDebateStatistics(MBCategory debate) throws SystemException, PortalException {
        MBCategory debateCategory = getSubcategory(debate.getCategoryId(), DebatesConstants.ISSUE_DEBATE_CATEGORY_NAME);
        MBCategory discussionCategory = getSubcategory(debate.getCategoryId(),
                DebatesConstants.ISSUE_DISCUSSION_CATEGORY_NAME);

        Map<String, Object> statistics = new HashMap<String, Object>();
        statistics.put(DebatesConstants.DEBATE_POSTS, debateCategory.getMessageCount() - 1);
        statistics.put(DebatesConstants.DISCUSSION_POSTS, discussionCategory.getMessageCount());

        MBMessage mainMessage = getDebateMessage(debateCategory.getCategoryId());
        List<MBMessage> positions = getChildMessages(mainMessage);
        statistics.put(DebatesConstants.POSITIONS, positions.size());

        statistics.put(DebatesConstants.DEBATE_LAST_POST_DATE, debateCategory.getLastPostDate());

        // add discussion category id for linking
        statistics.put(DebatesConstants.DISCUSSION_CATEGORY, discussionCategory.getCategoryId());

        // add id of last
        MBThread thread = MBThreadLocalServiceUtil.getThreads(debateCategory.getCategoryId(), 0, 1).get(0);
        int debateMessagesCount = MBMessageLocalServiceUtil.getThreadMessagesCount(thread.getThreadId());

        // get last debate message
        MBMessage lastMessage = MBMessageLocalServiceUtil.getThreadMessages(thread.getThreadId(),
        		debateMessagesCount - 1, debateMessagesCount).get(0);
        statistics.put(DebatesConstants.MESSAGE_ID, lastMessage.getMessageId());


        return statistics;
    }

    /**
     * Initializes portlet by creating all necessary categories.
     *
     * @param
     * 	    request portlet request
     * @param parentCategoryId id of category that should be used as a parent
     *      for debates topics
     *
     * @throws Exception passed up in case of framework error
     */
    public static void initializePortlet(PortletRequest request, long parentCategoryId) throws Exception {
        PortletPreferences preferences = request.getPreferences();

        preferences.setValue(DebatesConstants.CATEGORY_ID, Long.toString(parentCategoryId));

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        // get list of sub categories within parent category to check if it
        // contains Debates and Discussion categories
        long groupId = themeDisplay.getScopeGroupId();
        List<MBCategory> categories = MBCategoryLocalServiceUtil.getCategories(groupId, parentCategoryId);

        MBCategory discussion = null;
        MBCategory debate = null;

        for (MBCategory category : categories) {
            if (category.getName().equals(DebatesConstants.ISSUE_DEBATE_CATEGORY_NAME)) {
                debate = category;
            } else if (category.getName().equals(DebatesConstants.ISSUE_DISCUSSION_CATEGORY_NAME)) {
                discussion = category;
            }
        }

        // if any of required subcategories hasn't been found - create it
        if (discussion == null) {
            discussion = DebatesUtil.createMBCategory(request, parentCategoryId,
                    DebatesConstants.ISSUE_DISCUSSION_CATEGORY_NAME);
        }
        if (debate == null) {
            debate = DebatesUtil.createMBCategory(request, parentCategoryId,
                    DebatesConstants.ISSUE_DEBATE_CATEGORY_NAME);
        }

        preferences.setValue(DebatesConstants.DEBATE_INDEX_CATEGORY_ID, Long.toString(debate.getCategoryId()));
        preferences.setValue(DebatesConstants.DEBATE_DISCUSSION_CATEGORY_ID, Long.toString(discussion.getCategoryId()));

        preferences.store();
    }

    /**
     * Deletes all references from plans to position with given id.
     * @param messageId ID of a position.
     * @throws PortalException passed up in case of framework error
     * @throws SystemException passed up in case of framework error
     */
    public static void deletePositionReferences(long messageId) throws PortalException, SystemException {
        List < PlanPosition > planPositions = PlanLocalServiceHelper.getPlanPositionsByPositionId(messageId);
        for (PlanPosition planPosition: planPositions) {
            PlanPositionLocalServiceUtil.deletePlanPosition(planPosition);
        }
    }
    
    public static String getMessageURL(MBMessage msg) throws PortalException, SystemException {
    		MBCategory cat = msg.getCategory();
    		String result = DebatesConstants.MESSAGE_URL.replace(DebatesConstants.CATEGORY_REPLACEMENT_STRING, cat.getCategoryId()+"");
    		return result.replace(DebatesConstants.MESSAGE_REPLACEMENT_STRING, msg.getMessageId()+"");
    }
    
    public static String getCategoryURL(MBCategory cat) throws PortalException, SystemException {
		String result = DebatesConstants.MESSAGE_URL.replace(DebatesConstants.CATEGORY_REPLACEMENT_STRING, cat.getCategoryId()+"");
		return result;
    }
    
}
