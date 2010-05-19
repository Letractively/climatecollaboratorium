<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%
/**
 *
 *
 * File responsible for presenting a plan model contents.
 *
 * version 1.1 : add model description. static for now
 * version 1.2 : model description displayed by Model modules (jspf)
 *
 * @author janusz.p, BeBetter
 * @version 1.2
 * @since 1.0
 */
%>


<%@ include file="/html/portlet/ext/plans/init.jsp" %>

<!-- Tabs panel -->
<%@ include file="/html/portlet/ext/plans/view_plan/view_plan_tabs.jspf" %>

<%
	pageContext.setAttribute("showSelectedModel", true);
	String modelId = String.valueOf(plan.getPlanType().getModelId());
    boolean showAddPositions = false;
%>

<%@ include file="/html/portlet/ext/models/modules/init.jspf" %>
<script type="text/javascript">
	// currently there is no dynamic association between plan and a scenario/model,
	// static reference used, please refer to requirements doc
	var modelId = '<%= plan.getPlanType().getModelId() %>';
	
	function <portlet:namespace />runModel(modelId, modelName) {
		location.href = "<%=planModelURL%>&_models_<%=ModelConstants.MODEL_NAME%>="+modelName;
      }
	
     ModelUtils.modelReady(function(type, model) {
	if (type == "success") {
		$("#modelName").html("<a href='javascript:;' onClick='<portlet:namespace />runModel(" + model.id +",\""+model.name+ "\")'>" + model.name + "</a>");
	}
});

</script>
<div class="model-information-container">
	<div class="model-information">
		<h2 class="selectedModelName">Selected Model: <strong class='blue' id='modelName'></strong> </h2>	
		<div class="left floatLeft">  
			<%@ include file="/html/portlet/ext/models/modules/model.jspf" %>
		</div>
		<div class="right floatRight">
			<%@ include file="/html/portlet/ext/models/modules/positions.jspf" %>
		</div>
	</div>
	
</div>


<%@ include file="/html/portlet/ext/plans/view_plan/bottom.jspf" %>