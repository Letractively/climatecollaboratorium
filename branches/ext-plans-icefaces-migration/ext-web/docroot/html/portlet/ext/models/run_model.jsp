<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%
/**
 *
 * JSP page to run the model;
 *
 * version 1.1: added modifications to use new modular "framework" for
 *   handling models and scenarios (using modules from modules/ subdirectory).
 * version 1.2: themed to be consistent with collaboratorium theme
 *
 * @author BeBetter, janusz.p
 * @version 1.2
 * @since 1.0
 */
%>
<%@ include file="/html/portlet/ext/models/init.jsp" %>
<%

	 boolean showAddPlan = (ParamUtil.getString(request,"sim") == request.getAttribute(PlanConstants.DEFAULT_MODEL_ID) &&
			 permissionChecker.hasPermission (themeDisplay.getPortletGroupId(), portletDisplay.getRootPortletId(),
					 portletDisplay.getResourcePK(),
					ModelConstants.ADD_PLAN));

	%>
<script type="text/javascript">
     
	var modelId = '<%= ParamUtil.getString(request, ModelConstants.MODEL_ID) %>';
	var showProgress = true;
	var createPlanURL = '/web/guest/plans?p_p_id=plans&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&p_p_col_id=column-1&p_p_col_count=1&_plans_struts_action=%2Fext%2Fplans%2Fedit_plan&_plans_portletResource=plans&_plans_redirect=%2Fweb%2Fguest%2Fplans';
	var plansNamespace = "_plans_";

	function <portlet:namespace />runModel() {

		jQuery("#<portlet:namespace />runModel").attr("disabled", true);
		jQuery(".buttons-centered").addClass("single");
		jQuery("#<portlet:namespace />createNewPlan").hide();
		ModelUtils.scenarioReady(function(type, model, scenario) {
			jQuery("#<portlet:namespace />runModel").removeAttr("disabled");
			if (type == "success" && ${canAddPlan} == true) {
				if (model.isComposite) {
                    
					jQuery("#<portlet:namespace />createNewPlan").show();
					jQuery(".buttons-centered").removeClass("single");
				}
			}

		});
		ModelUtils.runModel();

	}

	function <portlet:namespace />createPlan() {
		$.blockUI({
			message: '<img src="/html/portlet/ext/models/images/ajax-loader.gif"/>'
		});
		ModelUtils.saveScenario(function(type) {
			if (type == "success") {
				var scenario = ModelUtils.getScenario();

				var editActionsForm = jQuery("#<portlet:namespace />createPlanForm");
				jQuery("#<portlet:namespace />scenarioId").val(scenario.id);
				editActionsForm.submit();

			}
			else {
				$.unblockUI();
			}
		});
	}

	ModelUtils.modelReady(function(type) {
		if (type == "success") {
			$("#<portlet:namespace />runModel").show();
			jQuery(".run-the-model-to-see-inputs").show();
		}
	});

	function <portlet:namespace />showDialog(dialogId, options) {
	     //, dialogClass: 'collab-dialog' });
		jQuery("#" + dialogId).dialog({ height: options.height, width: options.width, modal: true, resizable: false, draggable: false, dialogClass: 'collab-dialog' });
		jQuery("#" + dialogId).show();
	}

	function <portlet:namespace />closeDialog(dialogId) {
		jQuery("#" + dialogId).dialog("close");
	}
</script>





<%@ include file="/html/portlet/ext/models/view_models_tab.jspf" %>

<!-- end topBtn -->
<%@ include file="/html/portlet/ext/models/model_inner_tabs.jspf" %>
<div class="model-information">
	<div class="bottom">
		<div class="content">
		     <div class="top-content">
		          <div class="left-content floatLeft">
			               <%@ include file="/html/portlet/ext/models/modules/model.jspf" %>
			      </div>
			      <div class="right-content floatRight">
			           <div id="select-positions-dialog" style='display: none'>
				          <%@ include file="/html/portlet/ext/models/select_positions.jspf" %>
				       </div>
				       <%@ include file="/html/portlet/ext/models/modules/positions.jspf" %>
				  </div>
		    </div>
			<div class="clear"></div>
			<div class="bottom-content">
				<div class="left-content floatLeft">
				     <%@ include file="/html/portlet/ext/models/modules/actions.jspf" %>
		        </div>
			    <div class="right-content floatLeft">
			          <%@ include file="/html/portlet/ext/models/modules/impacts.jspf" %>
		        </div>
	        </div>

	    
	<div style='width: 100%; margin: auto'>
		<div class='buttons-centered single'>
	        <a href="javascript:;" id="<portlet:namespace />runModel" class="runModel" onClick="<portlet:namespace />runModel()"><span class="hidden">run model</span></a>
	        <a href="javascript:;" class="createNewPlan" id="<portlet:namespace />createNewPlan" style='display: none' onClick="<portlet:namespace />showDialog('add-plan-dialog', {width: 300, height: 180})" ><span class="hidden">Create A New Plan</span></a>
			<div class="clear"></div>
	    </div>
    </div>
    <c:if test="<%=!showAddPlan%>">
    <div class="note-cannot-save">
         <liferay-ui:message key="cannot-save-model-run"/>
    </div>     
    </c:if>
</div>

<div id="add-plan-dialog" style="display: none">
	<div class="addPlan">
		<div class="addPlan_top">
    		<div class="title"><h4>Add Plan</h4></div>
    	</div>
    	<div class="interior">
    	<form action="/web/guest/plans?p_p_id=plans&p_p_lifecycle=1&p_p_state=normal&p_p_mode=view&p_p_col_id=column-1&p_p_col_count=1&_plans_struts_action=%2Fext%2Fplans%2Fedit_plan" method="post" name="<portlet:namespace />fm" id="<portlet:namespace />createPlanForm">
			<input type="hidden" name="<%= Constants.CMD %>" value="<%= Constants.ADD %>" />
			<input type="hidden" name="<%= PlanConstants.UPDATE_TYPE %>" value="<%= PlanConstants.ADD_PLAN %>" />
            <input type="hidden" name="<%= PlanConstants.PLAN_TYPE_ID %>" value="${planTypeId}" />
			<input type="hidden" id="<portlet:namespace />scenarioId" name="<%= PlanConstants.SCENARIO_ID %>" />
			<input name="<%= PlanConstants.REDIRECT %>" type="hidden" value="<%= PlanConstants.PLAN_URL %>" />
			<input type="hidden" name="modelId" value="<%= ParamUtil.getString(request, ModelConstants.MODEL_ID) %>" />
			
    	
			<div class="row">
				<label><liferay-ui:message key="name" />:</label>
				<input type="text" name="name" />
				
			</div>
            <div class="note">Note: You can change the name of your plan after it's been created.</div>

			
        </form>
    	</div>
     	<div class="addPlan_bottom">
     		<div class="btnBox">
        		<a href="#" class="checkBtn" onClick="<portlet:namespace />createPlan(); return false;" ><span class="hidden">Check</span></a>
            	<a href="javascript:;" class="cancelBtn" onClick="<portlet:namespace />closeDialog('add-plan-dialog')"><span class="hidden">Cancel</span></a>
	        </div>
	     </div>
	</div>
</div>





</div>	    </div>
</div></div></div></div></div></div></div></div>

