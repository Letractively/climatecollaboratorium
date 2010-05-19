/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */
package com.ext.portlet.models.action;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ext.portlet.models.DebatesConstants;
import com.ext.portlet.models.DebatesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.messageboards.action.ViewAction;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBMessageDisplay;
import com.liferay.portlet.messageboards.model.impl.MBThreadImpl;
import com.liferay.portlet.messageboards.service.MBCategoryLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;

/**
 * Action class that is responsible for rendering debate view.
 * 
 * @author janusz.p
 * @version 1.0
 * 
 */
public class ViewDebateAction extends ViewAction {

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
    public ActionForward render(ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
            RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

        long categoryId = ParamUtil.getLong(renderRequest, DebatesConstants.BASE_CATEGORY_ID);
        if (categoryId <= 0) {
            categoryId = DebatesUtil.getBaseCategoryId(renderRequest);
        }
        MBCategory mainCategory = MBCategoryLocalServiceUtil.getCategory(categoryId);

        MBCategory discussionCategory = DebatesUtil.getSubcategory(mainCategory,
                DebatesConstants.ISSUE_DISCUSSION_CATEGORY_NAME);

        MBCategory debateCategory = DebatesUtil.getSubcategory(mainCategory,
                DebatesConstants.ISSUE_DEBATE_CATEGORY_NAME);

        MBMessage debateMessage = DebatesUtil.getDebateMessage(debateCategory.getCategoryId());

        List<MBMessage> positionMessages = DebatesUtil.getChildMessages(debateMessage);

        // retrieve list of positions
        List<MBMessageDisplay> positions = new ArrayList<MBMessageDisplay>();
        for (MBMessage positionMessage : positionMessages) {
            positions.add(MBMessageLocalServiceUtil.getMessageDisplay(positionMessage.getMessageId(),
                    MBThreadImpl.THREAD_VIEW_TREE));
        }

        renderRequest.setAttribute(DebatesConstants.DEBATE_CATEGORY, debateCategory);
        renderRequest.setAttribute(DebatesConstants.DISCUSSION_CATEGORY, discussionCategory);
        renderRequest.setAttribute(DebatesConstants.MAIN_CATEGORY, mainCategory);
        renderRequest.setAttribute(DebatesConstants.POSITIONS, positions);
        renderRequest.setAttribute(DebatesConstants.DEBATE_MESSAGE, debateMessage);

        String mainTab = ParamUtil.getString(renderRequest, DebatesConstants.VIEW_DEBATES_INDEX_TABS,
                DebatesConstants.DEBATE_INDEX_TAB);

        if (mainTab.equals(DebatesConstants.DISCUSSION_TAB)) {
            ActionForward ret = super.render(mapping, form, portletConfig, renderRequest, renderResponse);
            renderRequest.setAttribute(WebKeys.MESSAGE_BOARDS_CATEGORY, discussionCategory);
            return ret;
        }

        return mapping.findForward(DebatesConstants.VIEW_DEBATE_FORWARD);
    }

}
