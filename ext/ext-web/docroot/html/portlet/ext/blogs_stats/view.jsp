<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.ext.portlet.BlogsEntryServiceHelper"%>
<%@page import="com.ext.portlet.BlogsEntryServiceHelper.BlogsEntryStats"%>
<%
DateFormat month = new SimpleDateFormat("M");
DateFormat year = new SimpleDateFormat("yyyy");
DateFormat sdf = new SimpleDateFormat("yyyy MMMM");
%>
<%@ include file="/html/portlet/init.jsp" %>
<div class="div2"></div>
<h3>Archives</h3><br />
<ul id="blogArchives">

    
<% for (BlogsEntryStats stats: BlogsEntryServiceHelper.getEntryDates(scopeGroupId)) { %>
    <liferay-portlet:renderURL portletName="33" var="viewEntryURL">
        <liferay-portlet:param name="struts_action" value="/blogs/view" />
        <liferay-portlet:param name="filterYear" value="<%= year.format(stats.getDate()) %>" />
        <liferay-portlet:param name="filterMonth" value="<%= month.format(stats.getDate()) %>" />

</liferay-portlet:renderURL>

    <li><strong><a href="<%=viewEntryURL %>"><%= sdf.format(stats.date) %> (<%= stats.count %>)</a></strong></li>
<% } %>
</ul>
