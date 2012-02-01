<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%
/**
 *
 * Base jsp file that is included by all Plans portlet jsp files. It imports necessary classess and
 * retrieves common values from query parameters/request attributes.
 *
 * version 1.1 : added variables for positions and checking if plan is complete
 * version 1.2 : added variables for plan vote
 *
 * @author janusz.p, janusz.p
 * @version 1.2
 * @since 1.0
 */
%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 <%@ taglib uri="/WEB-INF/mit-climate-collab.tld" prefix="mit" %>
<%@ include file="/html/common/init.jsp" %>
<%


    // URL's that are helpful when rendering navigation links
	String currentURL = PortalUtil.getCurrentURL(request);
	String redirect = ParamUtil.getString(request, PlanConstants.REDIRECT, currentURL);
%>
