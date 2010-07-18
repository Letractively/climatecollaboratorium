<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%
/**
 *
 * Configure user profile portlet.
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
%>


<%@ include file="/html/portlet/ext/community/init.jsp" %>

<% 

String redirect = ParamUtil.getString(request, CommunityConstants.REDIRECT) ;
Boolean editEnabled = Boolean.valueOf(renderRequest.getPreferences().getValue(CommunityConstants.EDIT_ENABLED_PARAMETER,"false"));


%>

<liferay-portlet:actionURL windowState="<%= WindowState.NORMAL.toString() %>" var="submitActionURL">
	<portlet:param name="struts_action" value="/ext/community/portlet_prefs" />
	<portlet:param name="<%= Constants.CMD %>"
		value="<%= Constants.UPDATE %>" />
	<portlet:param name="<%= CommunityConstants.REDIRECT %>"
		value="<%=redirect%>" />
</liferay-portlet:actionURL>

<liferay-portlet:renderURL windowState="<%= WindowState.NORMAL.toString() %>" var="viewProfileURL">
	<portlet:param name="struts_action" value="/ext/community/view_profile" />
	<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.VIEW %>" />
</liferay-portlet:renderURL>


<script type="text/javascript">

function <portlet:namespace/>submit() {
	document.<portlet:namespace/>fm.submit();
}

</script>


<div class="profile-portlet-prefererces">


<form name="<portlet:namespace/>fm" method="post" action="<%=submitActionURL%>">
<table>
	<tr><td>Preference</td><td>Value</td></tr>
	<tr><td>Enable edit</td><td><input type="checkbox" name="<portlet:namespace/><%=CommunityConstants.EDIT_ENABLED_PARAMETER%>" 
		value="true" <c:if test="${editEnabled}">checked="checked"</c:if>/></td></tr>
</table>
</form>

<div class="submit-portletpreferences">
<a href="javascript:<portlet:namespace/>submit()"><span>Submit</span></a>
<a href="<%=viewProfileURL%>"><span>Cancel</span></a>
</div>

