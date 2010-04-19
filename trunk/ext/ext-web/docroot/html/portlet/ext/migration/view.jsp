<%@ include file="/html/portlet/ext/migration/init.jsp"%>

<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%
	PortletURL portletURL = renderResponse.createRenderURL();
	portletURL.setParameter("struts_action", "/ext/migration/view");
	String portletURLString = portletURL.toString();

%>
<script type="text/javascript">
	function <portlet:namespace />uploadFile() {
		document.<portlet:namespace />fm.encoding = "multipart/form-data";
		document.<portlet:namespace />fm.method = "post";
		submitForm(document.<portlet:namespace />fm, "<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="struts_action" value="/ext/migration/view" /></portlet:actionURL>");
	}
	
</script>

<form action="<%= currentURL %>" method="get" name="<portlet:namespace />fm">
	<input name="<portlet:namespace /><%= Constants.PROGRESS_ID %>" type="hidden" value="<%= uploadProgressId %>" />
	<table>
		<tr>
			<td>
				Choose location of Cognisis DB dump XML:
			</td>
			<td>
				<input class="lfr-input-text" name="<portlet:namespace />file" type="file" />
			</td>
		</tr>
		<tr>
			<td>Description:</td>
			<td><textarea name="<portlet:namespace />description" ></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="Send" onClick="<%= uploadProgressId %>.startProgress(); <portlet:namespace />uploadFile()" />
			</td>
		</tr>
	</table>
</form>


<liferay-ui:upload-progress
        id="<%= uploadProgressId %>"
        message="uploading"
        redirect="<%= portletURLString %>"
/>

<h2>Migrations:</h2>
<liferay-ui:search-container emptyResultsMessage="There are no migration files, please upload one">
	<liferay-ui:search-container-results 
			results="<%= MigrationDataLocalServiceUtil.getMigrationDatas(searchContainer.getStart(), searchContainer.getEnd()) %>"
			total="<%= MigrationDataLocalServiceUtil.getMigrationDatasCount() %>"
	/>
	
	<liferay-ui:search-container-row
		className="com.ext.portlet.migration.model.MigrationData"
		keyProperty="migrationId"
		modelVar="data"
	>
		<portlet:renderURL var="migrateDebatesURL">
			<portlet:param name="struts_action" value="/ext/migration/debates" />
			<portlet:param name="migrationId" value="${ data.migrationId }" />
		</portlet:renderURL>
		
		<liferay-ui:search-container-column-text
			name="File name"
			property="name"
			href="${ migrateDebatesURL }"
		/>
		
		<liferay-ui:search-container-column-text
			name="description"
			property="description"
			href="${ migrateDebatesURL }"
		/>
		
		<liferay-ui:search-container-column-text
			name="# Plans"
			property="plans"
			href="${ migrateDebatesURL }"
		/>
		
		<liferay-ui:search-container-column-text
			name="# Questions"
			property="questions"
			href="${ migrateDebatesURL }"
		/>
		
		<liferay-ui:search-container-column-text
			name="# Users"
			property="users"
			href="${ migrateDebatesURL }"
		/>
		
		
		
		<liferay-ui:search-container-column-text
			name="created-date"
			property="createdDate"
			href="${ migrateDebatesURL }"
		/>
		
	
	</liferay-ui:search-container-row>
	
	<liferay-ui:search-iterator />

</liferay-ui:search-container>
	
	
