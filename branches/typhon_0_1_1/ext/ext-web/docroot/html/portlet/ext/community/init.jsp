<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%
/**
 *
 * Base jsp file for the community portlet
 *
 * @author joshi
 * @version 1.0
 */
%>

<%@ include file="/html/common/init.jsp" %>

<%@ page import="com.ext.portlet.community.action.CommunityConstants" %>
<%@ page import="com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.search.Sort"%>
<%@ page import="com.ext.portlet.community.UserCreateDateComparator"%>
<%@ page import="com.ext.portlet.community.UserNumberActivitiesComparator"%>

<portlet:defineObjects />
<liferay-theme:defineObjects />

<%
String currentURL = PortalUtil.getCurrentURL(request);
%>
<%
boolean canConfigure = permissionChecker.hasPermission (themeDisplay.getPortletGroupId(), portletDisplay.getRootPortletId(),
			 portletDisplay.getResourcePK(),
			ActionKeys.CONFIGURATION);


%>
