<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%
/**
 *
 *
 * File responsible for rendering add/edit plan page. It provides some basic validation of entered 
 * values with Java Script.
 *
 * version 1.1 : removed ability to set model and choose published/unpublished status.
 * version 1.2 : added values taken from model to be stored in a plan, used when plan is created
 *    from Models tab
 * version 1.3: themed to be consistent with collaboratorium theme
 *
 * @author janusz.p, TCSDEVELOPER
 * @version 1.3
 */
%>
<%@ include file="/html/portlet/ext/plans/init.jsp"%>
<%

	String planContentForEdit = UnicodeFormatter.toString(planContent);
	String updateType = ParamUtil.getString(request, PlanConstants.UPDATE_TYPE);
	String cmd = ParamUtil.getString(request, Constants.CMD);
	String scenarioId = ParamUtil.getString(request, PlanConstants.SCENARIO_ID);
	String modelId = ParamUtil.getString(request, PlanConstants.MODEL_ID);

	boolean isUpdatingPlan = false;
	boolean isUpdatingDescription = updateType.equals(PlanConstants.UPDATE_DESCRIPTION);
	
	// model data
	String damageCostAvg = ParamUtil.getString(request, PlanConstants.DAMAGE_COST_AVG);
	String damageCostStdDev = ParamUtil.getString(request, PlanConstants.DAMAGE_COST_STD_DEV);
	String mitigationCostAvg = ParamUtil.getString(request, PlanConstants.DAMAGE_COST_AVG);
	String mitigationCostStdDev = ParamUtil.getString(request, PlanConstants.DAMAGE_COST_STD_DEV);
	String co2 = ParamUtil.getString(request, PlanConstants.CO2);
	String seaLevelRise = ParamUtil.getString(request, PlanConstants.SEA_LEVEL_RISE);
	String temperatureChange = ParamUtil.getString(request, PlanConstants.TEMPERATURE_CHANGE);
	String developedEmissions = ParamUtil.getString(request, PlanConstants.DEVELOPED_EMISSIONS);
	String developingAEmissions = ParamUtil.getString(request, PlanConstants.DEVELOPING_A_EMISSIONS);
	String developingBEmissions = ParamUtil.getString(request, PlanConstants.DEVELOPING_B_EMISSIONS);
	

%>

<script type="text/javascript">
	/**
	 Function called to initialize WYSIWYG editor. Returned value will be used to fill 
	 editor's textarea.

	 @return String that should be used to initialize WYSIWYG content editor.
	  
	 */ 
	function <portlet:namespace />initEditor() {
		return "<%= planContentForEdit %>";
	}

	/**
	 Function responsible for retrieval of WYSIWYG editor content and assigning it to 
	 appropriate plan edit form field.
	 
	 */
	function <portlet:namespace />getHtmlValue() {
		if (typeof(window.<portlet:namespace />editor) !== 'undefined') {
			var x = window.<portlet:namespace />editor.getHTML();
			document.<portlet:namespace />fm.<portlet:namespace />plan_content.value = x;
		}
	}

	/**
	 Function called when "Save" button is pressed. It performs validation and if
	 everything is correct retrieves content of WYSIWYG editor.

	 @return false if there are any errors in entered values, true otherwise
	 
	 */ 
	function <portlet:namespace />preSubmit() {
		<portlet:namespace />getHtmlValue();
		if (! <portlet:namespace />validate()) { 
			return false;
		}
		document.<portlet:namespace />fm.submit();
		true;
	}

	/**
	 Shows error message to the user.
	 */
	function <portlet:namespace />showError(message) {
		jQuery("#<portlet:namespace />messages").append("<span class=\"portlet-msg-error\">" + message + "</span>");
	}

	/**
	 Validates plan edit form. Validation is rather simple, function checks if user has entered name for a model
	 and if he has choosen a model.

	 @return true if form is valid, false otherwise
	 */
	function <portlet:namespace />validate() {
		// clear all error messages
		jQuery("span.portlet-msg-error").remove();
		var isValid = true; 

		<c:if test="<%= isUpdatingPlan %>">
			// check if user has entered a name
			var name = document.<portlet:namespace />fm.<portlet:namespace />name.value;
			if (name == null || jQuery.trim(name) == "") {
				<portlet:namespace />showError("<liferay-ui:message key="please-enter-a-valid-name" />");
				isValid = false;
			}
		</c:if>
		<c:if test="<%= isUpdatingDescription %>">
			// check if user has entered description
			var description = document.<portlet:namespace />fm.<portlet:namespace />plan_content.value;
			if (description == null || jQuery.trim(description) == "") {
				<portlet:namespace />showError("<liferay-ui:message key="please-enter-a-valid-description" />");
				isValid = false;
			}
		</c:if>

		// alert user if any error has been found
		if (!isValid) {
			alert("<liferay-ui:message key="you-have-entered-invalid-data" />");
		}
		return isValid;

	}
</script>


<!-- prepare form submit action URL -->
<portlet:actionURL windowState="NORMAL" var="submitActionURL">
	<portlet:param name="struts_action" value="/ext/plans/edit_plan" />
</portlet:actionURL>

<!-- placeholder for messages-->
<div id="<portlet:namespace />messages">
</div>


<div class="plan-detail"><div class="outLay"><div class="top"><div class="bottom"><div class="content">

<div class="planEditTitle">
	<h3 class="planEdit"><strong class='blue'><liferay-ui:message key='<%= cmd + "-" + updateType %>' /></strong></h3>
</div>

<div class="DetailContent_top">
<div class="DetailContent_bottom">
<div class="DetailContent">

<form action="${submitActionURL}" method="post" name="<portlet:namespace />fm" onsubmit="return <portlet:namespace />preSubmit()">
	<table class="portlet-plans-edit-form-table" width="100%">
		<c:if test="<%= isUpdatingPlan %>">
			<!--
				Name
	 		-->
			<tr>
				<td class="portlet-plans-edit-form-label-cell">	
					<span class="portlet-plans-edit-form-label">Plan name:</span>	
				</td>
				<td>
					<input type="textfield" name="<portlet:namespace />name" value="<%= planName %>"/>
				</td>
			</tr>
		</c:if>
		<c:if test="<%= isUpdatingDescription %>">
			<!--
				Plan content
	 		-->
			<tr>
				<td colspan="2">
					<div class="planContentEditor"> <liferay-ui:input-editor width="100%"/> </div>
					<input type="hidden" name="<portlet:namespace />plan_content"/>
				</td>
			</tr>
		</c:if>
		<tr>
            <td colspan="2">
   				<div class="btnBox plan-update">
    				<a href="javascript:;" class="checkBtn first" onClick="<portlet:namespace />preSubmit()" ><span class="hidden">Check</span></a>
       				<a href="javascript:;" class="cancelBtn" onClick='self.location="<%= redirect %>"'><span class="hidden">Cancel</span></a>
    			</div>
   				<div class="clear"></div>
            </td>
        </tr>
	</table>


	<!-- Prepare view plan URL that will be used to redirect user if plan addition will be successful.
		In order to allow Action handler to redirect user to correct page a PLACEHOLDER is passed as an 
		URL parameter. This placeholder will be later replaced by real planId.
	 -->
	<portlet:renderURL windowState="NORMAL" var="viewPlanURLedit">
		<portlet:param name="struts_action" value="/ext/plans/view_plan" />
		<portlet:param name="<%= PlanConstants.PLAN_ID %>" value="<%= PlanConstants.ID_PLACEHOLDER %>" />
	</portlet:renderURL> 
	
	<c:choose>
		<c:when test="<%= plan == null %>">
			<c:set var="editPlanCmd" value="<%= Constants.ADD %>" />
		</c:when>
		<c:otherwise>
			<c:set var="editPlanCmd" value="<%= Constants.EDIT %>" />
		</c:otherwise>
	</c:choose>

	<!-- Hidden inputs -->  
	<input name="<portlet:namespace /><%= PlanConstants.UPDATE_TYPE %>" type="hidden" value="<%= updateType %>" />
	<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="${editPlanCmd}" />
	<input name="<portlet:namespace /><%= PlanConstants.PLAN_ID %>" type="hidden" value="<%= planId %>" />
	<c:if test='<%= (! scenarioId.equals("")) && (! modelId.equals("")) %>'>
		<input name="<portlet:namespace /><%= PlanConstants.SCENARIO_ID %>" type="hidden" value="<%= scenarioId %>" />
		<input name="<portlet:namespace /><%= PlanConstants.MODEL_ID %>" type="hidden" value="<%= modelId %>" />
		<input name="<portlet:namespace /><%= PlanConstants.DAMAGE_COST_AVG %>" type="hidden" value="<%= damageCostAvg %>" />
		<input name="<portlet:namespace /><%= PlanConstants.DAMAGE_COST_STD_DEV %>" type="hidden" value="<%= damageCostStdDev %>" />
		<input name="<portlet:namespace /><%= PlanConstants.MITIGATION_COST_AVG %>" type="hidden" value="<%= mitigationCostAvg %>" />
		<input name="<portlet:namespace /><%= PlanConstants.MITIGATION_COST_STD_DEV %>" type="hidden" value="<%= mitigationCostStdDev %>" />
		<input name="<portlet:namespace /><%= PlanConstants.CO2 %>" type="hidden" value="<%= co2 %>" />
		<input name="<portlet:namespace /><%= PlanConstants.SEA_LEVEL_RISE %>" type="hidden" value="<%= seaLevelRise %>" />
		<input name="<portlet:namespace /><%= PlanConstants.TEMPERATURE_CHANGE %>" type="hidden" value="<%= temperatureChange %>" />
		<input name="<portlet:namespace /><%= PlanConstants.DEVELOPED_EMISSIONS %>" type="hidden" value="<%= developedEmissions %>" />
		<input name="<portlet:namespace /><%= PlanConstants.DEVELOPING_A_EMISSIONS %>" type="hidden" value="<%= developingAEmissions %>" />
		<input name="<portlet:namespace /><%= PlanConstants.DEVELOPING_B_EMISSIONS %>" type="hidden" value="<%= developingBEmissions %>" />
	</c:if>
	<c:choose>
		<c:when test="<%= isUpdatingDescription %>">
			<input name="<portlet:namespace /><%= PlanConstants.REDIRECT %>" type="hidden" value="<%= redirect %>" />
		</c:when>
		<c:otherwise>
			<input name="<portlet:namespace /><%= PlanConstants.REDIRECT %>" type="hidden" value="${viewPlanURLedit}" />
		</c:otherwise>
	</c:choose>
	
</form>
</div>
</div>
</div>
</div></div></div></div></div>
