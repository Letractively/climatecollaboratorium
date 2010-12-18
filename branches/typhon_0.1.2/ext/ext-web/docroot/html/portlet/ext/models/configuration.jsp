<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%
/**
 *
 * Configuration form for Plans portlet. It allows user to select default topic name
 * from which positions will be taken.
 *
 * @author janusz.p
 * @version 1.1
 * @since 1.1
 */
 %>
<%@ include file="/html/portlet/ext/models/init.jsp" %>


<%
	// list of available topics together with default topic
	List<MBCategory> mainCategories = (List<MBCategory>)request.getAttribute(DebatesConstants.MAIN_CATEGORY_CANDIDATES);
	MBCategory mainCategory = (MBCategory)request.getAttribute(DebatesConstants.MAIN_CATEGORY);

    List<DebateCategory> topics = (List<DebateCategory>) request.getAttribute(DebatesConstants.TOPICS);
    DebateCategory defaultTopic = (DebateCategory) renderRequest.getAttribute(PlanConstants.DEFAULT_TOPIC);

    Long defaultModelId =(Long) request.getAttribute(PlanConstants.DEFAULT_MODEL_ID);
    String defaultTopicName = defaultTopic==null?"No topic chosen":defaultTopic.getTitle();
%>
<script type="text/javascript">

		/**
		 * Sets default topic to requested id.
		 *
		 * @param topicId id of a topic that should be used as a default topic for plans
		 */
		function <portlet:namespace />chooseMainCategory(categoryId) {
			document.<portlet:namespace />fm.<portlet:namespace /><%= DebatesConstants.MAIN_CATEGORY %>.value = categoryId;
			document.<portlet:namespace />fm.submit();
		}



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
		 var matched = false;
		  for(var i=0; i < allSims.length; i++) {
			 var currSim = jQuery(allSims).get(i);
			 var composite = jQuery(currSim).attr('composite') == 'true';
			 var simName = jQuery(currSim).find("inputs + name").text();
			 var currSimId = jQuery(currSim).attr('id');
			 if (currSimId == defaultModelId) {
				 matched = true;
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
		if (!matched) {
			jQuery("#<portlet:namespace />defaultModelName").text("No model selected");
		}
		 jQuery("#<portlet:namespace />models").html(tableHtml);
	}



</script>

<h1><liferay-ui:message key="choose-default-topic" /></h1>
<h3><liferay-ui:message key="selected-topic" />: <em><%= defaultTopicName %></em></h3>

<liferay-portlet:actionURL portletConfiguration="true" var="submitURL"/>

<!-- List of topics with buttons allowing to select them as a default -->
<form action="<%= submitURL %>" method="post" name="<portlet:namespace />fm" >
	<input type="hidden" name="<portlet:namespace /><%= DebatesConstants.MAIN_CATEGORY %>" />
	<input type="hidden" name="<portlet:namespace /><%= PlanConstants.DEFAULT_TOPIC_ID %>" />
	<input type="hidden" name="<portlet:namespace /><%= PlanConstants.DEFAULT_MODEL_ID %>" />
	<input type="hidden" name="<portlet:namespace /><%= Constants.CMD %>" value="<%= Constants.UPDATE %>" />

<liferay-ui:search-container delta="0" emptyResultsMessage="there-are-no-topics">
	<liferay-ui:search-container-results total="<%= topics.size() %>" results="<%= topics %>" />
	<liferay-ui:search-container-row
		className="com.ext.portlet.debaterevision.model.DebateCategory"
		keyProperty="categoryId"
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

<h1><liferay-ui:message key="choose-main-category" /></h1>
<h3><liferay-ui:message key="selected-category" />: <em><%= mainCategory.getName() %></em></h3>

<liferay-portlet:actionURL portletConfiguration="true" var="submitURL"/>

<liferay-ui:search-container delta="0" emptyResultsMessage="there-are-no-topics">
	<liferay-ui:search-container-results total="<%= mainCategories.size() %>" results="<%= mainCategories %>" />
	<liferay-ui:search-container-row
		className="com.liferay.portlet.messageboards.model.MBCategory"
		keyProperty="categoryId"
		modelVar="category"
	>
		<liferay-ui:search-container-column-text
			name="Category"
			property="name"
		/>
		<liferay-ui:search-container-column-button
			name="Choose"
			align="right"
			href='<%= renderResponse.getNamespace() + "chooseMainCategory(" +category.getCategoryId() + ")" %>'
		/>
	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator/>
</liferay-ui:search-container>

<h1><liferay-ui:message key="choose-default-model" /></h1>
<h3><liferay-ui:message key="selected-model" />: <em id="<portlet:namespace />defaultModelName"></em></h3>

<div class="taglib-search-iterator" id="<portlet:namespace />models"></div>
</form>


