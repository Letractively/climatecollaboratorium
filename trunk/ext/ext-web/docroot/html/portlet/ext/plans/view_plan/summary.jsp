<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%
/**
 *
 *
 * File responsible for rendering a plan summary.
 *
 * version 1.1: added support for Model modules (jspf pages). There is
 *   no mock data in Actions or Impacts tables anymore.
 *
 * @author janusz.p
 * @version 1.1
 * @since 1.0
 */
%>
<%@ include file="/html/portlet/ext/plans/init.jsp" %>

<!-- Include tabs panel -->
<%@ include file="/html/portlet/ext/plans/view_plan/view_plan_tabs.jspf" %>
<%
     %>
<script type="text/javascript">
	// currently there is no dynamic association between plan and a scenario/model,
	// static reference used, please refer to requirements doc

	var scenarioId = 2684;
	var modelId = '<%= plan.getPlanType().getModelId() %>';
	var scenarioId = '<%= plan.getScenarioId() %>';




</script>
<%@ include file="/html/portlet/ext/models/modules/init.jspf" %>

<!-- Link to pop up that allows user to select positions -->
<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>" var="editPositionsPopUp">
	<portlet:param name="struts_action" value="/ext/plans/select_positions" />
	<portlet:param name="<%= PlanConstants.UPDATE_TYPE %>" value="<%= PlanConstants.UPDATE_POSITIONS %>" />
	<portlet:param name="<%= PlanConstants.PLAN_ID %>" value="<%= String.valueOf(plan.getPlanId()) %>" />
</portlet:renderURL>

<!-- prepare link for editing a plan  -->
<liferay-portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="editURL">
	<portlet:param name="struts_action" value="/ext/plans/edit_plan" />
	<portlet:param name="<%= PlanConstants.REDIRECT %>" value="<%= currentURL %>" />
	<portlet:param name="<%= PlanConstants.PLAN_ID %>" value="<%= planId %>" />
	<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.UPDATE %>" />
</liferay-portlet:renderURL>



<div class="topContent">
	<div class="floatLeft leftTxt">
	     <div class="creator">Created by: <mit:climate-user userId="<%=String.valueOf(plan.getUserId())%>"/></div>
		<div class="summary"><%= planSummary %></div>
	</div>
	<div class="floatRight rightTxt">
       	<div class="corcen_top">
           	<div class="corcen_bottom">
               	<div class="corcen">
    	          	<div class="title"><h3><span class="hidden">Position</span></h3>
    	          		<c:if test="<%= canEditPlan %>" >
							<a href="javascript:;" class="edit" onClick="<portlet:namespace />showDialog('select-positions-dialog', { width: 500, height: 500 })">
								edit
							</a>
						</c:if>
					</div>
					<br />
					<div>
                        <c:forEach items="<%=positions%>" var="position">
                                 <%
                                     DebateItem p = (DebateItem)pageContext.getAttribute("position");
                                     DebateItem q = p.getDebate().getCurrentRoot();

                                 %>
                                <ul class="plan-positions">
                                <a href='<%=constructDebateUrl(q)%>'><strong><%=q.getDebateSummary()%></strong></a>
                                <ul >

                                    <li>
									<a href='<%=constructDebateUrl(p)%>'><strong><%=p.getDebateSummary()%></strong></a>
                                    </li>
                                 </ul>
                                </ul>
                         </c:forEach>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="clear"></div>
</div>

<div class="bottomContent">
    <div class="floatLeft leftTxt">
    	<%@ include file="/html/portlet/ext/models/modules/actions_summary.jspf" %>
    </div>
    <div class="floatRight rightTxt">
    	<%@ include file="/html/portlet/ext/models/modules/impacts_summary.jspf" %>
    </div>
    <div class="clear"></div>
</div>


	<div id="select-positions-dialog" style='display: none'>
		<%@ include file="/html/portlet/ext/plans/view_plan/select_positions.jspf" %>
	</div>




<%@ include file="/html/portlet/ext/plans/view_plan/bottom.jspf" %>