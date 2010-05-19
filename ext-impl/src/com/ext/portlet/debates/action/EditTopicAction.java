package com.ext.portlet.debates.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portlet.messageboards.action.EditCategoryAction;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.service.MBCategoryLocalServiceUtil;

public class EditTopicAction extends EditCategoryAction {

    @Override
    public void processAction(ActionMapping mapping, ActionForm form, PortletConfig portletConfig, 
            ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

        super.processAction(mapping, form, portletConfig, actionRequest, actionResponse);
        int weight = ParamUtil.getInteger(actionRequest, "threadCount"); 
        long categoryId = ParamUtil.getLong(actionRequest, "categoryId");
        if (categoryId > 0) {
            MBCategory category = MBCategoryLocalServiceUtil.getCategory(categoryId);
            category.setThreadCount(weight);
            MBCategoryLocalServiceUtil.updateMBCategory(category);
        }
    }

    
}
