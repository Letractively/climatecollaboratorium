<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%
/**
 *
 * Actions for Issues.
 * 
 * @author janusz.p
 * @version 1.0
 */
 %>
<%@ include file="/html/portlet/ext/debates/init.jsp" %>
<%
	ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
	MBCategory issue = (MBCategory) row.getObject();
	long issueId = issue.getCategoryId();
	long topicId = issue.getParentCategoryId();
	
	
			
    boolean canDelete =permissionChecker.hasPermission (themeDisplay.getPortletGroupId(),
                                 portletDisplay.getRootPortletId(),portletDisplay.getResourcePK(),DebatesConstants.DELETE_DEBATE_QUESTION);
     
			
    boolean canEdit =permissionChecker.hasPermission (themeDisplay.getPortletGroupId(),
                                 portletDisplay.getRootPortletId(),portletDisplay.getResourcePK(),DebatesConstants.UPDATE_DEBATE_QUESTION);
                              
  
 
	
	
%>

<c:if test="<%= canEdit %>">
	<liferay-portlet:renderURL var="editIssueURL">
		<portlet:param name="struts_action" value="/ext/debates/edit_issue" />
		<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.UPDATE %>" />
		<portlet:param name="<%= DebatesConstants.REDIRECT %>" value="<%= currentURL %>" />
		<portlet:param name="<%= DebatesConstants.PARENT_CATEGORY_ID %>" value="<%= Long.toString(topicId) %>" />
		<portlet:param name="categoryId" value="<%= Long.toString(issueId) %>" />
	</liferay-portlet:renderURL>
</c:if>

<c:if test="<%= canDelete %>">
	<liferay-portlet:actionURL var="deleteIssueURL">
		<portlet:param name="struts_action" value="/ext/debates/edit_issue" />
		<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
		<portlet:param name="<%= DebatesConstants.REDIRECT %>" value="<%= currentURL %>" />
		<portlet:param name="<%= DebatesConstants.TOPIC_ID %>" value="<%= Long.toString(topicId) %>" />
		<portlet:param name="categoryId" value="<%= Long.toString(issueId) %>" />
	</liferay-portlet:actionURL>
</c:if>


<liferay-ui:icon-menu>
	<c:if test="<%=canEdit %>">
		<liferay-ui:icon-delete label="true" url="${deleteIssueURL}" />
	</c:if>
	<c:if test="<%= canDelete %>">
		<liferay-ui:icon image="edit" url="${editIssueURL}" />
	</c:if>

</liferay-ui:icon-menu>

