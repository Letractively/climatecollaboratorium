<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%
/** From view profile **/
%>


<%@ include file="/html/portlet/ext/community/init.jsp" %>
<%@ page import="com.liferay.portal.model.*" %>
<%@ page import="com.ext.portlet.Activity.ActivityConstants" %>
<%@ page import="com.liferay.portlet.social.model.SocialActivity" %>
<%@ page import="com.liferay.portlet.social.model.SocialActivityFeedEntry" %>
<%@ page import="com.liferay.portlet.social.service.SocialActivityInterpreterLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.social.service.SocialActivityLocalServiceUtil" %>


<%@ page import="com.ext.portlet.plans.service.PlanLocalServiceUtil" %>

<%@ page import="com.ext.portlet.Activity.ActivityUtil" %>
<%@ page import="com.ext.portlet.Activity.ActivityConstants" %>

<%@ page import="com.ext.portlet.Activity.model.ActivitySubscription" %>


<%@page import="java.util.TimeZone"%>
<%@page import="java.util.Locale"%>
<%@page import="com.liferay.portlet.messageboards.service.MBCategoryLocalServiceUtil"%>

<%
/** From search community **/
%>

<%@ page import="com.liferay.portal.model.*" %>
<%@ page import="com.liferay.portal.NoSuchOrganizationException" %>
<%@ page import="com.liferay.portal.NoSuchUserGroupException" %>
<%@ page import="com.liferay.portal.NoSuchUserException" %>
<%@ page import="com.liferay.portal.service.permission.OrganizationPermissionUtil" %>
<%@ page import="com.liferay.portlet.enterpriseadmin.search.OrganizationDisplayTerms" %>
<%@ page import="com.liferay.portlet.enterpriseadmin.search.OrganizationSearch" %>
<%@ page import="com.liferay.portlet.enterpriseadmin.search.OrganizationSearchTerms" %>
<%@ page import="com.liferay.portlet.enterpriseadmin.search.UserDisplayTerms" %>
<%@ page import="com.liferay.portlet.enterpriseadmin.search.UserGroupDisplayTerms" %>
<%@ page import="com.liferay.portlet.enterpriseadmin.search.UserGroupSearch" %>
<%@ page import="com.liferay.portlet.enterpriseadmin.search.UserGroupSearchTerms" %>
<%@ page import="com.liferay.portlet.enterpriseadmin.search.UserSearch" %>
<%@ page import="com.liferay.portlet.enterpriseadmin.search.UserSearchTerms" %>
<%
boolean hasUser = (null!=(User)request.getAttribute(CommunityConstants.USER_PARAMETER));

%>


<div class="community">
<div class="left floatLeft"> 
<%@ include file="/html/portlet/ext/community/search_community_inner.jspf" %>
</div>
<div class="right floatLeft">
<c:choose>
<c:when  test="<%=hasUser%>">
<%@ include file="/html/portlet/ext/community/view_profile_inner.jspf" %>
</c:when>
<c:otherwise>
<%@ include file="/html/portlet/ext/community/blank_user_profile.jspf" %>
</c:otherwise>
</c:choose>
</div>
</div>



