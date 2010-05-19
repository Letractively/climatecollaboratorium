<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%
/**
 *
 * Admini configuration for activity wall.
 *
 *
 * @author janusz.p
 * @version 1.0
 */
 %>
<%@ include file="/html/portlet/ext/wall/init.jsp" %>
<% 

String redirect = ParamUtil.getString(renderRequest,ActivityConstants.REDIRECT,"/web/guest/");
ActivityConstants.SubscriptionMode mode = (ActivityConstants.SubscriptionMode)renderRequest.getAttribute(ActivityConstants.SUBSCRIPTION_MODE);

%>
<liferay-portlet:actionURL portletConfiguration="true" var="submitURL">
	<portlet:param name="<%=ActivityConstants.REDIRECT%>" value="<%=redirect %>"/>
</liferay-portlet:actionURL>

<liferay-portlet:renderURL windowState="<%= WindowState.NORMAL.toString() %>" var="cancelURL">
	<portlet:param name="struts_action" value="/ext/activities/view_activities" />
	<portlet:param name="<%= Constants.CMD %>"
		value="<%= Constants.VIEW %>" />
</liferay-portlet:renderURL>

<script type="text/javascript">
function <portlet:namespace/>submitForm() {
	document.<portlet:namespace/>fm.submit();
}


</script>

<div class = "confgPersonalization">
<form action="<%= submitURL %>" method="post" name="<portlet:namespace />fm" >
<h4>Mode:</h4>
<input type="radio" name="<portlet:namespace/><%=ActivityConstants.SUBSCRIPTION_MODE%>" value="<%=ActivityConstants.SubscriptionMode.USER.toString()%>" <c:if test="<%=mode==ActivityConstants.SubscriptionMode.USER%>">checked="true"</c:if>>User<br>
<input type="radio" name="<portlet:namespace/><%=ActivityConstants.SUBSCRIPTION_MODE%>" value="<%=ActivityConstants.SubscriptionMode.PERSONAL.toString()%>" <c:if test="<%=mode==ActivityConstants.SubscriptionMode.PERSONAL%>">checked="true"</c:if>>Personal<br>
<input type="radio" name="<portlet:namespace/><%=ActivityConstants.SUBSCRIPTION_MODE%>" value="<%=ActivityConstants.SubscriptionMode.GLOBAL.toString()%>" <c:if test="<%=mode==ActivityConstants.SubscriptionMode.GLOBAL%>">checked="true"</c:if>>Global<br>
<span>Reset</span><input type="checkbox" name="<portlet:namespace/><%=ActivityConstants.RESET%>" value="true" />
</form>
</div>
<div class="bottomContent">
<a href="javascript:<portlet:namespace/>submitForm()"><span>Submit</span></a>
<a href="${cancelURL}"><span>Cancel</span></a>
</div>


