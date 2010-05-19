<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%
/**
 *
 * Edit topic/issue form.
 *
 * @author janusz.p
 * @version 1.0
 */
 %>
<%@ include file="/html/portlet/ext/debates/init.jsp" %>

<%
	// get base parameters
	String redirect = ParamUtil.getString(request, DebatesConstants.REDIRECT);
	MBCategory category = (MBCategory)request.getAttribute(WebKeys.MESSAGE_BOARDS_CATEGORY);

	long categoryId = BeanParamUtil.getLong(category, request, "categoryId");
	
	long parentCategoryId = BeanParamUtil.getLong(category, request, "parentCategoryId", MBCategoryImpl.DEFAULT_PARENT_CATEGORY_ID);
	
	MBCategory topic = (MBCategory)request.getAttribute(DebatesConstants.TOPIC);
	long topicId = BeanParamUtil.getLong(topic, request, "categoryId");
	boolean isIssue = topic != null;
	
	// get entity type
	String editedEntityType = (String) request.getAttribute(DebatesConstants.EDITED_ENTITY_TYPE);
	if (editedEntityType == null) {
	    editedEntityType = DebatesConstants.TOPIC_ENTITY_TYPE; 
	}
%>

<script type="text/javascript">

	function <portlet:namespace />saveCategory() {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= category == null ? Constants.ADD : Constants.UPDATE %>";
		submitForm(document.<portlet:namespace />fm);
	}

</script>
<liferay-ui:error exception="<%= CaptchaTextException.class %>" message="text-verification-failed" />
<liferay-ui:error exception="<%= CategoryNameException.class %>" message="please-enter-a-valid-name" />

<portlet:actionURL var="submitURL" >
	<portlet:param name="struts_action" value='<%= "/ext/debates/edit_" + editedEntityType %>' />
</portlet:actionURL>

<form action="${submitURL}" method="post" name="<portlet:namespace />fm" onSubmit="<portlet:namespace />saveCategory(); return false;">
	<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="" />
	<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(redirect) %>" />
	<input name="<portlet:namespace />categoryId" type="hidden" value="<%= categoryId %>" />
	<input name="<portlet:namespace />topicId" type="hidden" value="<%= topicId %>" />
	<input name="<portlet:namespace />parentCategoryId" type="hidden" value="<%= parentCategoryId %>" />

	<div class="breadcrumbs">
		<span class="last"><liferay-ui:message key='<%= ((category == null) ? Constants.ADD : Constants.UPDATE) + "-" + editedEntityType %>' /></span>
		<c:if test="<%= topic != null %>">
			<br />
		 	<span class="portlet-debates-issue-edit-topic">
		 		<span class="label">
		 			<liferay-ui:message key="topic" />:
		 		</span>
		 		<%= topic.getName() %>
		 	</span>
		</c:if>
	</div>

	<table class="lfr-table">

		<tr>
			<td class="lfr-label">
				<c:choose>
					<c:when test="<%= topic != null %>">
						<liferay-ui:message key="question" />:
					</c:when>
					<c:otherwise>
						<liferay-ui:message key="name" />
					</c:otherwise>
				</c:choose>
			</td>
			<td> 
				<liferay-ui:input-field model="<%= MBCategory.class %>" bean="<%= category %>" field="name" />
			</td>
			
		</tr>
		<c:if test="<%= topic == null %>">
		  <tr>
		      <td class="lfr-label">
		          Weight
		      </td>
		      <td>
		          <liferay-ui:input-field model="<%= MBCategory.class %>" bean="<%= category %>" field="threadCount" />
		      </td>
		  </tr>
		</c:if>

	</table>
	<br />

	<input type="submit" value="<liferay-ui:message key="save" />" />
	<input type="button" value="<liferay-ui:message key="cancel" />" onClick="location.href = '<%= HtmlUtil.escape(redirect) %>';" />

</form>