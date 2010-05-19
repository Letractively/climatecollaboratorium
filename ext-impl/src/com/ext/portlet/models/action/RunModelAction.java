/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */
package com.ext.portlet.models.action;

import com.ext.portlet.debaterevision.model.DebateItem;
import com.ext.portlet.models.ModelConstants;
import com.ext.portlet.plans.PlanConstants;
import com.liferay.portal.struts.PortletAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.List;

/**
 * <p>
 * The action for running the model. It will take it to the page for running the model.
 * </p>
 * <p>
 * <strong>THREAD SAFETY:</strong> This is a struts action and it is thread safe.
 * </p>
 *
 * @author BeBetter
 * @version 1.0
 */
public class RunModelAction extends PortletAction {

    /**
     * <p>
     * It renders run model page.
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

    	Long topicId=Long.parseLong(preferences.getValue(PlanConstants.DEFAULT_TOPIC_ID, "-1"));
    	Long modelId = Long.parseLong(renderRequest.getParameter(ModelConstants.MODEL_ID));
        ModelLocalServiceHelper.addQuestionsAndPositions(renderRequest,topicId );
    	List<DebateItem> positions = ModelLocalServiceHelper.getModelPositions(modelId);

    	renderRequest.setAttribute(ModelConstants.MODEL_POSITIONS,
        		 positions);
//         renderRequest.getPortletSession().setAttribute(ModelConstants.MODEL_POSITIONS,
//        		 positions);
//
        return mapping.findForward("portlet.ext.models.run_model");
    }

}
