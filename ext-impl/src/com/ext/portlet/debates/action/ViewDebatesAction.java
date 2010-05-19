/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */
package com.ext.portlet.debates.action;

import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ext.portlet.debates.DebatesConstants;
import com.ext.portlet.debates.DebatesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.messageboards.action.ViewAction;
import com.liferay.portlet.messageboards.model.MBCategory;

/**
 * Action responsible for rendering debate index page.
 * @author janusz.p
 * @version 1.0
 *
 */
public class ViewDebatesAction extends ViewAction {

    /**
     * Action that forwadrs user to view debate page.
     * 
     * @param mapping
     *            action mapping
     * @param form
     *            action form
     * @param portletConfig
     *            portlet config
     * @param renderRequest
     *            render request
     * @param renderResponse
     *            render response
     * @throws Exception
     *             in case of any error
     */
    public ActionForward render(ActionMapping mapping, ActionForm form, PortletConfig portletConfig, RenderRequest renderRequest,
            RenderResponse renderResponse) throws Exception {

        MBCategory mainCategory = DebatesUtil.getMainCategory(renderRequest);
        
        MBCategory topicsCategory = DebatesUtil.getSubcategory(mainCategory,
                DebatesConstants.ISSUE_DEBATE_CATEGORY_NAME);
        
        MBCategory discussionCategory = DebatesUtil.getSubcategory(mainCategory, 
                DebatesConstants.ISSUE_DISCUSSION_CATEGORY_NAME);
        
        renderRequest.setAttribute(DebatesConstants.TOPICS_CATEGORY, topicsCategory);
        renderRequest.setAttribute(DebatesConstants.DISCUSSION_CATEGORY, discussionCategory);
        
        String mainTab = ParamUtil.getString(renderRequest, DebatesConstants.VIEW_DEBATES_INDEX_TABS, 
                DebatesConstants.DEBATE_INDEX_TAB);
        
        if (mainTab.equals(DebatesConstants.DISCUSSION_TAB)) {
            ActionForward ret = super.render(mapping, form, portletConfig, renderRequest, renderResponse);
            renderRequest.setAttribute(WebKeys.MESSAGE_BOARDS_CATEGORY, discussionCategory);
            return ret;
        }

        ActionForward forward = mapping.findForward(DebatesConstants.VIEW_ACTION_FORWARD);
        
    
       return forward;
    }
    
}
