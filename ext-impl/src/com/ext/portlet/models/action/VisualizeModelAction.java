/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */
package com.ext.portlet.models.action;

import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liferay.portal.struts.PortletAction;

/**
 * <p>
 * The action for running the model. It will take it to the page for visualizing the model.
 * </p>
 * <p>
 * <strong>THREAD SAFETY:</strong> This is a struts action and it is thread safe.
 * </p>
 *
 * @author BeBetter
 * @version 1.0
 */
public class VisualizeModelAction extends PortletAction {

    /**
     * <p>
     * It renders visualizing model page.
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
        return mapping.findForward("portlet.ext.models.visualize_model");
    }

}
