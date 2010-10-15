/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */
package com.ext.portlet.models.action;

import com.ext.portlet.models.DebatesConstants;
import com.ext.portlet.models.DebatesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portlet.messageboards.model.MBCategory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * <p>
 * The action for viewing all models. It will take it to the view models page.
 * </p>
 * <p>
 * <strong>THREAD SAFETY:</strong> This is a struts action and it is thread
 * safe.
 * </p>
 *
 * @author BeBetter
 * @version 1.0
 */
public class ViewModelsAction extends PortletAction {
	/**
	 * <p>
	 * It renders page for a list of models.
	 * </p>
	 *
	 * @param mapping
	 *            the action mapping
	 * @param form
	 *            the form
	 * @param portletConfig
	 *            the portlet config
	 * @param renderRequest
	 *            the render request
	 * @param renderResponse
	 *            the response request
	 * @return the action forward
	 * @throws Exception
	 *             if any error
	 */
	public ActionForward render(ActionMapping mapping, ActionForm form,
			PortletConfig portletConfig,

			RenderRequest renderRequest, RenderResponse renderResponse)
			throws Exception {

		String tabParamValue = ParamUtil.getString(renderRequest,
				"modelsTabParameter", "Models");

       
        
		if ("Models".equals(tabParamValue)) {
			return mapping.findForward("portlet.ext.models.view_models");
		} else {
			PortletPreferences preferences = renderRequest.getPreferences();


			MBCategory mainCategory = DebatesUtil
					.getMainCategory(renderRequest);

			MBCategory topicsCategory = DebatesUtil.getSubcategory(
					mainCategory, DebatesConstants.ISSUE_DEBATE_CATEGORY_NAME);

			MBCategory discussionCategory = DebatesUtil.getSubcategory(
					mainCategory,
					DebatesConstants.ISSUE_DISCUSSION_CATEGORY_NAME);

			renderRequest.setAttribute(DebatesConstants.TOPICS_CATEGORY,
					topicsCategory);
			renderRequest.setAttribute(DebatesConstants.DISCUSSION_CATEGORY,
					discussionCategory);
			return mapping.findForward("portlet.ext.modeldebates.view");
		}

	}

}
