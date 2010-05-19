<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%
/**
 *
 *
 * Page responsible for displaying plan comparision page. Plans are divided into two separate tables. One
 * for published plans and one for unpublished plans. Also it provides user with link to Add plan page.
 *
 * version 1.1 : added button for voting (mock)
 * version 1.2 : added support for plans tabs, voting, filter and column configuration
 * version 1.3 : themed to be consistent with collaboratorium theme
 *
 * @author janusz.p, TCSDEVELOPER
 * @version 1.3
 */
%>
<%@ include file="/html/portlet/ext/plans/init.jsp" %>
<%
	String selectedTab = ParamUtil.getString(request, PlanConstants.VIEW_PLANS_TABS, PlanConstants.PUBLISHED_PLANS_TAB);
	boolean published = selectedTab.equals(PlanConstants.PUBLISHED_PLANS_TAB);
	PlanType planType = PlanTypeLocalServiceUtil.getDefaultPlanType();
	if (published) {
	    planType = PlanTypeLocalServiceUtil.getPlanType(planType.getPublishedCounterpartId());
	}
    System.err.println("Is published?"+published);


	//PlansFilter plansFilter = PlanLocalServiceHelper.getFilter(renderRequest, planType.getPlanTypeId());
	PlansUserSettings planUserSettings = PlanLocalServiceHelper.getPlanUserSettings(renderRequest, planType.getPlanTypeId());

	int plansCount = PlanLocalServiceHelper.getPlansCount(renderRequest, planType.getPlanTypeId());
	int pagerMax = ParamUtil.getInteger(request, PlanConstants.PAGER_MAX, 50);
	int pagerStart = ParamUtil.getInteger(request, PlanConstants.PAGER_START, 0);
	int pagerNext = pagerStart + pagerMax;
	int pagerPrev = Math.max(pagerStart - pagerMax, 0);
	int pagerFirst = 0;
	int pagerLast = Math.max(plansCount - (plansCount % pagerMax), 0);

	boolean enableNext = pagerNext < plansCount;
	boolean enablePrev = pagerStart > 0;
	boolean enableLast = pagerStart < pagerLast;
	boolean enableFirst = pagerFirst < pagerStart;




	PlansUserSettings plansUserSettings = (PlansUserSettings) PlanLocalServiceHelper.getPlanUserSettings(renderRequest, planType.getPlanTypeId());
	pageContext.setAttribute("plansUserSettings", PlanLocalServiceHelper.getPlanUserSettings(renderRequest, planType.getPlanTypeId()));
	pageContext.setAttribute("published", published);
    if (published) {
        pageContext.setAttribute("shownPlansType", "published");
    } else {
        pageContext.setAttribute("shownPlansType", "unpublished");
    }

    /*
    final String[] columns = {PlanFinder.COLUMN_NAME, PlanFinder.COLUMN_CREATOR, PlanFinder.COLUMN_DESCRIPTION,
            PlanFinder.COLUMN_PUBLISH_DATE, PlanFinder.COLUMN_CO2, PlanFinder.COLUMN_VOTES, PlanFinder.COLUMN_DAMAGE,
            PlanFinder.COLUMN_MITIGATION, PlanFinder.COLUMN_DEVELOPED, PlanFinder.COLUMN_DEVELOPING_A,
            PlanFinder.COLUMN_DEVELOPING_B, PlanFinder.COLUMN_SEA_LEVEL, PlanFinder.COLUMN_TEMPERATURE,
            PlanFinder.COLUMN_GLOBAL, PlanFinder.COLUMN_CREATE_DATE };
    */
    final List<Columns> columns = Columns.getPlanTypeColumns(planType);
    

    //String sortColumn = ParamUtil.getString(request, PlanConstants.ORDER_COLUMN);
    //String sortDirection = ParamUtil.getString(request, PlanConstants.ORDER_DIRECTION);

     String sortColumn = (String)renderRequest.getAttribute(PlanConstants.ORDER_COLUMN);
     System.err.println("Sort column is "+sortColumn);

     String sortDirection = (String)renderRequest.getAttribute(PlanConstants.ORDER_DIRECTION);


    Map<Columns, String> columnOrderURL = new HashMap<Columns, String>();

    PortletURL portletURL = renderResponse.createRenderURL();
    portletURL.setParameter("struts_action", "/ext/plans/view_plans");
    portletURL.setParameter(PlanConstants.VIEW_PLANS_TABS, selectedTab);

    for(Columns column: columns) {
        String columnsortDirection = PlanFinder.ASC;
        if (column.name().equals(sortColumn) && sortDirection.equals(PlanFinder.ASC)) {
            columnsortDirection = PlanFinder.DESC;
        }
        portletURL.setParameter(PlanConstants.ORDER_DIRECTION, columnsortDirection);
        portletURL.setParameter(PlanConstants.ORDER_COLUMN, column.name());
        columnOrderURL.put(column, portletURL.toString());
    }

	int votePos = PlanLocalServiceHelper.getUserVotePosition(renderRequest, planType.getPlanTypeId(), sortColumn, sortDirection);
	int votePagerStart = ((votePos-1) / pagerMax) * pagerMax;
	boolean highlightVote = ParamUtil.getBoolean(request, PlanConstants.HIGHLIGHT_VOTE);



	int columnsCount = 0;

	for (PlanConstants.Columns col:Columns.getPlanTypeColumns(planType)) {
	     if (col.getUserSetting(plansUserSettings)) {
	          columnsCount++;
	     }
	}

	if (published && isLoggedIn) {
	    columnsCount++;
	}

	int currentColumn = 1;


%>

<!-- Prepare add plan URL -->
<liferay-portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="addPlanURL" portletName="${portletDisplay.id}">
	<portlet:param name="struts_action" value="/ext/plans/edit_plan" />
	<portlet:param name="portletResource" value="${portletDisplay.id}" />
	<portlet:param name="<%= PlanConstants.REDIRECT %>" value="<%= currentURL %>" />
	<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.ADD %>" />
</liferay-portlet:renderURL>

<portlet:renderURL windowState="NORMAL" var="findVoteURL">
	<portlet:param name="struts_action" value="/ext/plans/view_plans" />
	<portlet:param name="<%= PlanConstants.PAGER_START %>" value="<%= String.valueOf(votePagerStart) %>" />
	<portlet:param name="<%= PlanConstants.ORDER_DIRECTION %>" value="<%= sortDirection %>" />
	<portlet:param name="<%= PlanConstants.ORDER_COLUMN %>" value="<%= sortColumn %>" />
	<portlet:param name="<%= PlanConstants.HIGHLIGHT_VOTE %>" value="true" />
</portlet:renderURL>


<!-- toggle filters form url -->
<portlet:actionURL windowState="NORMAL" var="toggleFiltersURL">
	<portlet:param name="<%= PlanConstants.REDIRECT %>" value="<%= currentURL %>" />
	<portlet:param name="<%= PlanConstants.PUBLISHED %>" value="<%= String.valueOf(published) %>" />
	<portlet:param name="struts_action" value="/ext/plans/update_filters" />
	<portlet:param name="<%= PlanConstants.UPDATE_TYPE %>" value="<%= PlanConstants.TOGGLE_FILTERS %>" />
    <portlet:param name="<%= PlanConstants.PLAN_TYPE_ID %>" value="<%= String.valueOf(planType.getPlanTypeId()) %>" />
</portlet:actionURL>

<!-- prepare links used to switch tabs -->
<liferay-portlet:renderURL windowState="<%= WindowState.NORMAL.toString() %>" var="publishedTabURL">
	<portlet:param name="struts_action" value="/ext/plans/view_plans" />
	<portlet:param name="<%= PlanConstants.VIEW_PLANS_TABS %>" value="<%= PlanConstants.PUBLISHED_PLANS_TAB %>" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL windowState="<%= WindowState.NORMAL.toString() %>" var="unpublishedTabURL">
	<portlet:param name="struts_action" value="/ext/plans/view_plans" />
	<portlet:param name="<%= PlanConstants.VIEW_PLANS_TABS %>" value="<%= PlanConstants.UNPUBLISHED_PLANS_TAB %>" />
</liferay-portlet:renderURL>


<script type="text/javascript">

 	/**
 	 * Function responsible for voting/unvoting on a plan. 
 	 */
 	function <portlet:namespace />planVote(action, url) {
        //alert(Liferay.ThemeDisplay.isSignedIn());
 	 	var message = '<liferay-ui:message key="are-you-sure-you-want-to-vote-for-this-plan" />';
 	 	if (action == 'unvote') {
 	 	 	var message = '<liferay-ui:message key="are-you-sure-you-want-to-remove-your-vote" />';
 	 	}
 		if (confirm(message)) {
 		     document.hrefFm.action=url;
 		     document.hrefFm.submit();
 			return true;
 		} else {
 			self.focus();
 		}
 	}

    

</script>





<div class="plan-question"/>
<a href='/web/guest/resources/-/wiki/Main/Background+on+Copenhagen'><h2>What plan should be adopted at the UN climate talks?</h2></a>
</div>

<div class="page-context-help-container"> 
<a href='/web/guest/resources/-/wiki/Main/Plans'>What are plans?</a>
</div>

<div class="tab">
    <div class="tabs floatRight">
	<a class="finalized<%= published ? "Hover" : "" %>" href="${publishedTabURL}"><span class="hidden"><liferay-ui:message key="<%= PlanConstants.PUBLISHED_PLANS_TAB %>" /></span></a>
	<a class="development<%= !published ? "Hover" : "" %>" href="${unpublishedTabURL}"><span class="hidden"><liferay-ui:message key="<%= PlanConstants.UNPUBLISHED_PLANS_TAB %>" /></span></a>
    </div>
    <div class="planactions floatLeft">
       

		<c:if test="<%= !themeDisplay.isSignedIn() || canAddPlan %>" >
			<a href="javascript:;" onclick="deferUntilLogin(function () {
			    <portlet:namespace />showDialog('add-plan-dialog', {width: 300, height: 180});
			    });" class="createNewPlan <%= !published ? "unpublished" : "published" %>"><span class="hidden">Create a New Plan</span></a>
		</c:if>
		<c:if test="<%= published && canVote && planVote != null %>">
			<a href="${findVoteURL}" class="findMyVoteBtn"><span class="hidden">Find my Vote</span></a>
		</c:if>

    </div>

	<div class="clear"></div>
</div>

<div class="tabContent_bottom">
	<div class="tabContent">
		<div class="tabinner">
			<div class="comparsion">
				<%@ include file="/html/portlet/ext/plans/plans_table.jspf" %>
			</div>
		</div>
	</div>
</div>
