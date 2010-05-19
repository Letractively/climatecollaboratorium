<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%
/**
 *
 * JSP page to show all models.
 * version 1.1: moved to ModelUtils as a framework for interaction with models service,
 *	changed layout
 *
 * version 1.2: themed to be consistent with collaboratorium theme
 *
 * @author BeBetter, janusz.p
 */
%>
<%@ include file="/html/portlet/ext/models/init.jsp" %>

<script type="text/javascript">

/**
 * Redirects user to the page at which user will be able to run selected model.
 *
 * @param modelId id of a model user wants to run
 */
function <portlet:namespace />runModel(modelId, modelName) {
	location.href = "<%=runModelURL%>&<portlet:namespace /><%=ModelConstants.MODEL_ID%>=" + modelId +
	                    "&<portlet:namespace /><%=ModelConstants.MODEL_NAME%>="+modelName;
}


var modelWeight = {
     "623":0,
     "240":10,
     "621":20,
     "583":30,
     "584":40,
     "585":50,
     "586":60,
     "587":70,
     "681":80,
     "588":90
}


function getDisplayWeight(sim) {
     var weight = modelWeight[$(sim).attr("id")];
     return weight==null?999:weight;
}

/**
 * Page ready event, responsible for retrieval and display of model list.
 */
$(document).ready(function() {
	// fetch all models
	ModelUtils.fetchAllModels(function <portlet:namespace />displayModels(models) {
		// table header
		var tableHtml = "<table width='600px' class='taglib-search-iterator' id='simple-table'>" +
			"<thead><tr>" +
			"<th class='first'><liferay-ui:message key="name" /></th>" +
		    "<th width='70%'><liferay-ui:message key="description"/></th>" +
		    "</thead></tr>\n";

		// each model in its own row
		var addClass = "";
		
		var tmpsort = [];
		for (var modelId in models) {
			 models[modelId].id = modelId;
		     tmpsort.push(modelId);
		}
        tmpsort.sort(function(ida,idb) {
             return getDisplayWeight(models[ida])-getDisplayWeight(models[idb])
        });		
		for(var i = 0; i < tmpsort.length; i++) {
			var idx = tmpsort[i];
			var model = models[idx];

			tableHtml += "<tr class='results-row " + addClass + "'><td class='description'>" +
				"<a href='javascript:;' onClick='<portlet:namespace />runModel(" + model.id +",\""+model.name+ "\"); return false;'>" + model.name + "</a></td>" +
				"<td class=\"description\">" + model.shortdescription + "</td></tr>";

			if (addClass == "") {
				addClass = "alt";
			}
			else {
				addClass = "";
			}
		}
		$("#<portlet:namespace />models").html(tableHtml);
	});
});

</script>

<%@ include file="/html/portlet/ext/models/view_models_tab.jspf" %>


<div class="tableOutlay">
	<!-- placeholder for models -->
	<div class="page-context-help-container"> 
	<a href='/web/guest/resources/-/wiki/Main/Models'>What are models?</a>
	</div>
	<div id="<portlet:namespace />models" class="model-table-container">
	
	</div>
</div>


<%@ include file="/html/portlet/ext/models/bottom.jspf" %>