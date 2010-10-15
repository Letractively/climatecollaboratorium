<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%
/**
 *  Configure subscriptions
 *  @author joshi
 */
 %>
<%@ include file="/html/portlet/ext/wall/init.jsp" %>
<% 
Collection<CompositeSubscriptionHolder> subscriptions = (Collection<CompositeSubscriptionHolder>)request.getAttribute(ActivityConstants.SUBSCRIPTIONS_PARAMETER); 
String redirect = ParamUtil.getString(renderRequest,ActivityConstants.REDIRECT,"/web/guest/myprofile");
%>

<script type="text/javascript">
function <portlet:namespace/>submitForm() {
	document.<portlet:namespace/>fm.submit();
}


</script>

<div class="title"><h1 class="selectColumn">Remove Subscriptions</h1></div>

<div class="tableOutlay">
<liferay-portlet:actionURL windowState="<%= WindowState.NORMAL.toString() %>" var="submitActionURL">
	<portlet:param name="struts_action" value="/ext/activities/manage_subscriptions" />
	<portlet:param name="<%= Constants.CMD %>"
		value="<%= Constants.UPDATE %>" />
	<portlet:param name="<%= ActivityConstants.REDIRECT %>"
		value="<%=redirect%>" />
</liferay-portlet:actionURL>

<form action="<%= submitActionURL %>" method="post" name="<portlet:namespace />fm" >

<table border="0" cellpadding="0" cellspacing="0" id="myTable" class="taglib-search-iterator">
	
	<tr class="portlet-section-header results-header">
		<th class="first">Entity Type</th>
		<th class="first">Entity Name</th>
		
		<th class="first">Remove</th>
		
	</tr>
	<c:forEach var="tmpPlan" items="<%=subscriptions%>">
		<%
			CompositeSubscriptionHolder sub = (CompositeSubscriptionHolder) pageContext.getAttribute("tmpPlan");
			String entityName="<unknown>";
			
			if (sub.getEntityId() < 0) {
				entityName = "All";
			} else {
			try {
			if(sub.getPortletId().equals("plans"))
			{
				entityName=PlanLocalServiceUtil.getPlan(sub.getEntityId()).getName();
			}
			else
			{
				entityName=MBCategoryLocalServiceUtil.getCategory(sub.getEntityId()).getName();
			}	
			} catch (Exception e) {
                //do nothing
            }
            }
		%>
	    <tr class="portlet-section-body results-row" onmouseout="this.className = 'portlet-section-body results-row';" onmouseover="this.className = 'portlet-section-body-hover results-row hover';">
		<td valign="middle" align="left"><%=sub.getPortletId()%></td>
		<td valign="middle" align="left"><%=entityName%></td>
		<td valign="middle" align="left">
		<input type="checkbox" name="<portlet:namespace/><%=sub.getHash() %>" value="keep" checked="yes"/></td>
	</tr>
	</c:forEach>
</table>
</form>
</div>
<div class="bottomBtn">
<a href="javascript:<portlet:namespace/>submitForm()"><span>Submit</span></a>
<a href="<%=redirect%>"><span>Cancel</span></a>
</div>


