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
<%@ include file="/html/portlet/ext/community/view_profile_inner.jspf" %>



        