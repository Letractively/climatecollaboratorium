<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%--
  ~ Copyright (c) 2010. Joshua Introne
  ~
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the text of the license.
  --%>

<%--
  ~ Copyright (c) 2010. $author
  ~
  ~ Licensed under the MIT license. See http://www.opensource.org/licenses/mit-license.php or the license.txt file included in this distribution for the text of the license.
  --%>

<%--
  ~ Copyright (c) 2010. $author
  ~
  ~ Licensed under the MIT license. See http://www.opensource.org/licenses/mit-license.php or the license.txt file included in this distribution for the text of the license.
  --%>

<%@ include file="/html/portlet/ext/community/init.jsp" %>
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

<%@ include file="/html/portlet/ext/community/search_community_inner.jspf" %>