<%@ page import="com.ext.portlet.debaterevision.model.DebateCategory" %>
<%@ page import="com.ext.portlet.debaterevision.service.DebateCategoryLocalServiceUtil" %>
<%--
~ Copyright (c) 2010. M.I.T. All Rights Reserved
~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
~ or the license.txt file included in this distribution for the full text of the license.
--%>

<%
/**
 *
 *
 * Configuration form for Plans portlet. It allows user to select default topic name
 * from which positions will be taken.
 *
 * @author janusz.p
 * @version 1.1
 * @since 1.1
 */
 %>
<%@ include file="/html/portlet/ext/plans/init.jsp" %>


<%
	// list of available topics together with default topic
	List<DebateCategory> topics = (List<DebateCategory>) request.getAttribute(DebatesConstants.TOPICS);
    DebateCategory defaultTopic = (DebateCategory) renderRequest.getAttribute(PlanConstants.DEFAULT_TOPIC);


    Long defaultModelId = (Long) renderRequest.getAttribute(PlanConstants.DEFAULT_MODEL_ID);
    String defaultTopicName = defaultTopic==null?"-- none selected --":defaultTopic.getTitle();
    
    String dateMin = (String) request.getAttribute(PlanConstants.DATE_MIN);
    String dateMax = (String) request.getAttribute(PlanConstants.DATE_MAX);
    
    double damageCostMin = (Double) request.getAttribute(PlanConstants.DAMAGE_COST_MIN);
    double damageCostMax = (Double) request.getAttribute(PlanConstants.DAMAGE_COST_MAX);
    
    double mitigationCostMin = (Double) request.getAttribute(PlanConstants.MITIGATION_COST_MIN);
    double mitigationCostMax = (Double) request.getAttribute(PlanConstants.MITIGATION_COST_MAX);

    double co2Min = (Double) request.getAttribute(PlanConstants.CO2_MIN);
    double co2Max = (Double) request.getAttribute(PlanConstants.CO2_MAX);
%>
<script type="text/javascript">

	/**
	 * Sets default topic to requested id.
	 *
	 * @param topicId id of a topic that should be used as a default topic for plans
	 */
	function <portlet:namespace />chooseDefaultTopic(topicId) {
		document.<portlet:namespace />fm.<portlet:namespace /><%= PlanConstants.DEFAULT_TOPIC_ID %>.value = topicId;
		document.<portlet:namespace />fm.submit();
	}	

	/**
	 * Sets default model to requested id.
	 *
	 * @param modelId id of a topic that should be used as a default topic for plans
	 */
	function <portlet:namespace />chooseDefaultModel(modelId) {
		document.<portlet:namespace />fm.<portlet:namespace /><%= PlanConstants.DEFAULT_MODEL_ID %>.value = modelId;
		document.<portlet:namespace />fm.submit();
	}


	jQuery(document).ready(function () {	
		jQuery.ajax({
				type: "GET",
				url: "/simulation-servlet/rest/simulation/",
				dataType: "xml",
				success: function(data, textStatus) {
			 		<portlet:namespace />displayModels(data);
				},
				error: function() {
					alert("<liferay-ui:message key="error" />");
				}
			});
	});

	function <portlet:namespace />displayModels(xml)
	{
		var defaultModelId = '<%= defaultModelId %>'; 
		 var tableHtml = "<table width='100%' class='taglib-search-iterator' id='simple-table'><thead><tr class='portlet-section-header results-header'>"+
		 			"<th scope='col'><liferay-ui:message key="name" /></th>"+
		 			"<th scope='col' width='20%'><liferay-ui:message key="choose"/></th>";
		 allSims = jQuery(xml).find("simulation");
		 var addClass = "";
		  for(var i=0; i < allSims.length; i++) {
			 var currSim = jQuery(allSims).get(i);
			 var composite = jQuery(currSim).attr('composite') == 'true';
			 var simName = jQuery(currSim).find("inputs + name").text();
			 var currSimId = jQuery(currSim).attr('id');
			 if (currSimId == defaultModelId) {
				 jQuery("#<portlet:namespace />defaultModelName").text(simName);
			 }
			 if (composite) {
				 tableHtml += "<tr class='portlet-section-alternate results-row " + addClass + "'><td>"+simName+"</td>"+
			 				"<td align='right'>" +
			 				    "<input type='button' onClick='<portlet:namespace />chooseDefaultModel(" + currSimId + ")' value='<liferay-ui:message key="choose" />'>" +
			 				"</td></tr>";
	 				if (addClass == "") {
		 				addClass = "alt";
	 				} 
	 				else {
		 				addClass = "";
	 				}
			 }
				 
		 }
		 jQuery("#<portlet:namespace />models").html(tableHtml);
	}
	
</script>

<h1><liferay-ui:message key="choose-default-topic" /></h1>
<h3><liferay-ui:message key="selected-topic" />: <em><%= defaultTopicName %></em></h3>

<liferay-portlet:actionURL portletConfiguration="true" var="submitURL"/>

<!-- List of topics with buttons allowing to select them as a default -->
<form action="<%= submitURL %>" method="post" name="<portlet:namespace />fm" >
	<input type="hidden" name="<portlet:namespace /><%= PlanConstants.DEFAULT_TOPIC_ID %>" />
	<input type="hidden" name="<portlet:namespace /><%= PlanConstants.DEFAULT_MODEL_ID %>" />
	<input type="hidden" name="<portlet:namespace /><%= Constants.CMD %>" value="<%= Constants.UPDATE %>" />

<liferay-ui:search-container delta="0" emptyResultsMessage="there-are-no-topics">
	<liferay-ui:search-container-results total="<%= topics.size() %>" results="<%= topics %>" />
	<liferay-ui:search-container-row
		className="com.ext.portlet.debaterevision.model.DebateCategory"
		keyProperty="debateCategoryId"
		modelVar="topic"
	>
		<liferay-ui:search-container-column-text
			name="Topic"
			property="title"
		/>
		<liferay-ui:search-container-column-button
			name="Choose"
			align="right"
			href='<%= renderResponse.getNamespace() + "chooseDefaultTopic(" + topic.getDebateCategoryPK() + ")" %>'
		/>
	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator/>
</liferay-ui:search-container>

<h1><liferay-ui:message key="choose-default-model" /></h1>
<h3><liferay-ui:message key="selected-model" />: <em id="<portlet:namespace />defaultModelName"></em></h3>

<div class="taglib-search-iterator" id="<portlet:namespace />models"></div>

<h1><liferay-ui:message key="filters-max-min-configuration" /></h1>
	<table>
		<tr>
			<td><liferay-ui:message key="date"/> <liferay-ui:message key="min" /> (in MM-DD-YYYY format)</td>
			<td><input type="text" name="<portlet:namespace /><%= PlanConstants.DATE_MIN %>" value="<%= dateMin %>" /></td>
		</tr>
		<tr>
			<td><liferay-ui:message key="date"/> <liferay-ui:message key="max" /> (in MM-DD-YYYY format)</td>
			<td><input type="text" name="<portlet:namespace /><%= PlanConstants.DATE_MAX %>" value="<%= dateMax %>" /></td>
		</tr>
		<tr>
			<td><liferay-ui:message key="damage-cost"/> <liferay-ui:message key="min" /></td>
			<td><input type="text" name="<portlet:namespace /><%= PlanConstants.DAMAGE_COST_MIN %>" value="<%= damageCostMin %>" /></td>
		</tr>
		<tr>
			<td><liferay-ui:message key="damage-cost"/> <liferay-ui:message key="max" /></td>
			<td><input type="text" name="<portlet:namespace /><%= PlanConstants.DAMAGE_COST_MAX %>" value="<%= damageCostMax %>" /></td>
		</tr>
		<tr>
			<td><liferay-ui:message key="mitigation-cost"/> <liferay-ui:message key="min" /></td>
			<td><input type="text" name="<portlet:namespace /><%= PlanConstants.MITIGATION_COST_MIN %>" value="<%= mitigationCostMin %>" /></td>
		</tr>
		<tr>
			<td><liferay-ui:message key="mitigation-cost"/> <liferay-ui:message key="max" /></td>
			<td><input type="text" name="<portlet:namespace /><%= PlanConstants.MITIGATION_COST_MAX %>" value="<%= mitigationCostMax %>" /></td>
		</tr>
		<tr>
			<td><liferay-ui:message key="co2"/> <liferay-ui:message key="min" /></td>
			<td><input type="text" name="<portlet:namespace /><%= PlanConstants.CO2_MIN %>" value="<%= co2Min %>" /></td>
		</tr>
		<tr>
			<td><liferay-ui:message key="co2"/> <liferay-ui:message key="max" /></td>
			<td><input type="text" name="<portlet:namespace /><%= PlanConstants.CO2_MAX %>" value="<%= co2Max %>" /></td>
		</tr>
	</table>
	<input type="submit" value="Save" />
</form>

