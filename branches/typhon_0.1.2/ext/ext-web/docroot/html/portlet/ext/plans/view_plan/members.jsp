<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%
/**
 *
 *
 * File responsible for presentation of plan's members list. Goal is achived by embedding message board
 * portlet from plans home community page.
 *
 * After each page load inside iframe it's contents is little modified in order to remove scrollbars, portlet
 * border and title bar.
 *
 * @author janusz.p
 * @version 1.0
 */
 %>
<%@ include file="/html/portlet/ext/plans/init.jsp" %>

<!-- Tabs panel -->
<%@ include file="/html/portlet/ext/plans/view_plan/view_plan_tabs.jspf" %>
<%
List<User>users = (List<User>)renderRequest.getAttribute( PlanConstants.MEMBERS_PARAMETER );
%>
<script type="text/javascript">
 	/**
 	 * Clears all warning messages.
 	 */
 	 

</script>


<div class="plan-membership-info">
<c:choose>
<c:when test="<%=!isPlanMember%>">
You have guest access to this plan.
<a href="#" onclick="deferUntilLogin(
function() {
<portlet:namespace />showDialog('request-membership-dialog', {width: 371, height:400});
});">Request membership</a>
</c:when>
<c:when test="<%=!isPlanOwner%>">
You are a member of this plan.
</c:when>
<c:otherwise>
You are the owner of this plan.
</c:otherwise>
</c:choose>
</div>
<div class="left-content floatLeft">
<div class="title">
<h3>Members</h3>
</div>
<div class="reduced-table">
<liferay-ui:search-container delta="10">
			<liferay-ui:search-container-results>
			<%
				results = users.subList(searchContainer.getStart(),Math.min(users.size(),searchContainer.getEnd()));
				total = users.size();
				pageContext.setAttribute("results", results);
				pageContext.setAttribute("total", total);
			%>
		
    </liferay-ui:search-container-results>
			<liferay-ui:search-container-row
				className="com.liferay.portal.model.User"
				keyProperty="userId"
				modelVar="tmpUser"
			>
			<%
			String rowURL = "/web/guest/members?userId="+tmpUser.getUserId();
			String affiliation =(tmpUser.getUserId() == plan.getUserId())?"Owner":"Member";
			
		     %>
		     <liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="screen-name"
			orderable="<%= true %>"
			property="screenName"
		     />
	       <liferay-ui:search-container-column-text
					name="affiliation"
					value="<%=affiliation%>"/>
	      </liferay-ui:search-container-row>
	      <liferay-ui:search-iterator />
</liferay-ui:search-container>
</div>
</div>

<div class="middle-content floatLeft">
<div class="title">
<h3>Membership Requests</h3>
</div>
<%@ include file="/html/portlet/ext/plans/view_plan/manage_requests.jspf"%>
</div>
<div class="right-content floatLeft">

<div class="title">
<h3>Plan functions </h3>
</div>

<p>
		This Plan is
							<c:choose>
								<c:when test="<%= planPublished %>" >
									<strong class="blue">published</strong>.
								</c:when>
								<c:otherwise>
									<strong class="blue">unpublished</strong>.
		</p>							
									<c:if test="<%= isPlanComplete && canEditPlan %>" >
										<p>
											<a href="javascript:<portlet:namespace />publishPlan()">
												<liferay-ui:message key="publish-plan" />
											</a>
										</p>
									</c:if>
									<c:if test="<%= ! isPlanComplete %>">
										<p><i>
											<liferay-ui:message key="plan-incomplete" />;
											<c:if test="<%= isContentMissing %>">
 			                                   <liferay-ui:message key="plan-content-missing" />.
 		                                   </c:if>
 		                                    <c:if test="<%= areActionsMissing %>">
			                                   <liferay-ui:message key="plan-actions-missing" />.
		                                    </c:if>
										</i></p>
										
									</c:if>
								</c:otherwise>
							</c:choose>
				<p>		
               <c:if test="<%=canEditPlan%>">
					<a href="javascript:<portlet:namespace/>deletePlan()">delete</a>
               </c:if>
               </p>
</div>

<div class="delete-plan">
	 
</div>

</div>
<%@ include file="/html/portlet/ext/plans/view_plan/bottom.jspf" %>