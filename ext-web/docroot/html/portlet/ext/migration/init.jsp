<%@ include file="/html/portlet/init.jsp" %>
<%@ page import="com.ext.portlet.migration.model.MigrationData"%>
<%@ page import="com.ext.portlet.migration.service.MigrationDataLocalServiceUtil"%>
<%@ page import="com.ext.portlet.migration.MigrationHelper"%>

<%@ page import="com.ext.portlet.migration.entities.QuestionEntity"%>



<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%
	String uploadProgressId = PwdGenerator.getPassword(PwdGenerator.KEY3, 4);
	//String currentURL = PortalUtil.getCurrentURL(request);
%>
