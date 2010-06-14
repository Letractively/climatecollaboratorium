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

<%@ page import="com.ext.portlet.plans.service.PlanLocalServiceUtil" %>
<%@ page import="com.ext.portlet.plans.service.PlanVoteLocalServiceUtil" %>
<%@ page import="com.ext.portlet.plans.service.PlanTypeLocalServiceUtil" %>
<%@ page import="com.ext.portlet.plans.model.Plan" %>
<%@ page import="com.ext.portlet.plans.model.PlanVote" %>
<%@ page import="com.ext.portlet.plans.model.PlanType" %>
<%@ page import="com.ext.portlet.plans.PlanConstants" %>
<%@ page import="com.ext.portlet.plans.PlanConstants.Columns" %>
<%@ page import="com.ext.portlet.plans.PlanLocalServiceHelper" %>
<%@page import="com.liferay.portal.security.auth.PrincipalException"%>
<%@ page import="com.ext.portlet.plans.PlanFinder" %>
<%@ page import="com.ext.portlet.plans.NoSuchPlanException" %>
<%@ page import="com.ext.portlet.plans.model.PlansUserSettings" %>
<%@ page import="com.ext.portlet.plans.model.PlansFilter" %>
<%@ page import="com.ext.portlet.debates.DebatesConstants" %>
<%@ page import="com.ext.portlet.debates.DebatesUtil" %>
<%@ page import="com.ext.portlet.debaterevision.model.DebateItem"%>
<%@ page import="com.liferay.portal.model.MembershipRequest"%>

<%@ page import="com.liferay.portal.DuplicateGroupException" %>
<%@ page import="com.liferay.portal.GroupNameException" %>


<%@ page import="com.liferay.portlet.journal.model.JournalArticleDisplay" %>
<%@ page import="com.liferay.portlet.messageboards.model.MBCategory" %>
<%@ page import="com.liferay.portlet.messageboards.model.MBMessage" %>




<portlet:defineObjects />

<%


    // URL's that are helpful when rendering navigation links
	String currentURL = PortalUtil.getCurrentURL(request);
	String redirect = ParamUtil.getString(request, PlanConstants.REDIRECT, currentURL);

	Boolean isPlanOwner = ((Boolean) request.getAttribute(PlanConstants.IS_PLAN_OWNER));
	Boolean isPlanMember = ((Boolean) request.getAttribute(PlanConstants.IS_PLAN_MEMBER));

	Integer planMembersCount = ((Integer) request.getAttribute(PlanConstants.PLAN_MEMBERS_COUNT));
	Integer planPendingRequests = ((Integer) request.getAttribute(PlanConstants.PLAN_PENDING_REQUESTS));

	// external url's used to direct portlet user to other portlets
	String planMembersURL = (String) request.getAttribute(PlanConstants.PLAN_MEMBERS_URL);
	String planMangeMembersURL = (String) request.getAttribute(PlanConstants.PLAN_MANAGE_MEMBERS_URL);
	String planManageMembershipRequestsURL =
	    (String) request.getAttribute(PlanConstants.PLAN_MANAGE_MEMBERSHIP_REQUESTS_URL);
	String planDiscussionURL = (String) request.getAttribute(PlanConstants.PLAN_DISCUSSION_URL);
	String planModelURL = (String) request.getAttribute(PlanConstants.PLAN_MODEL_URL);
	String planActionsURL = (String) request.getAttribute(PlanConstants.PLAN_ACTIONS_URL);
	String planImpactsURL = (String) request.getAttribute(PlanConstants.PLAN_IMPACTS_URL);
	String planQuestionURL = (String) request.getAttribute(PlanConstants.PLAN_QUESTION_URL);

	String planSummary = (String) request.getAttribute(PlanConstants.PLAN_SUMMARY);

	PlanType defaultPlanType = PlanLocalServiceHelper.getDefaultPlanType();
	Long defaultPlanTypeId = defaultPlanType.getPlanTypeId();
	
	pageContext.setAttribute("planTypeId", defaultPlanTypeId);
	
	

	// plan
    Plan plan = (Plan) request.getAttribute(PlanConstants.PLAN);

	// planVote
	PlanVote planVote = (PlanVote) request.getAttribute(PlanConstants.VOTE);
	// overall votes count
	long votes = PlanVoteLocalServiceUtil.getPlanVotesCount();
	// if votes is equal to 0 then set it to 1 to prevent division by 0
	votes = votes == 0 ? 1 : votes;

	// plan fields
    String planId = new Long(BeanParamUtil.getLong(plan, request, "planId")).toString();
    String planName = BeanParamUtil.getString(plan, request, "name");
    String planCreator = BeanParamUtil.getString(plan, request, "userName");
    String planShortContent = BeanParamUtil.getString(plan, request, "shortcontent");
    String planContent = BeanParamUtil.getString(plan, request, "content");
    String planModelId = "";
    Boolean planPublished = false;
    String planScenario = BeanParamUtil.getString(plan, request, "scenarioId");
    Date planCreateDate = plan == null ? null : plan.getCreateDate();

    if (plan != null) {
        planModelId = String.valueOf(plan.getPlanType().getModelId());
        planPublished = plan.getPlanType().getPublished();
    }

    // booleans indicating what user can do with a plan
    Boolean canAddPlan = permissionChecker.hasPermission(themeDisplay.getPortletGroupId(),
            portletDisplay.getRootPortletId(), portletDisplay.getResourcePK(), PlanConstants.ADD_PLAN);
    Boolean canEditPlan = plan!=null && (
            isPlanOwner || isPlanMember ||
            permissionChecker.hasPermission(themeDisplay.getPortletGroupId(), portletDisplay.getRootPortletId(), portletDisplay.getResourcePK(), PlanConstants.EDIT_ANY_PLAN));
            
            
            
            //(permissionChecker.isOmniadmin() || (!planPublished && (isPlanOwner || isPlanMember)));
    Boolean canVote = permissionChecker.hasPermission(themeDisplay.getPortletGroupId(),
            portletDisplay.getRootPortletId(), portletDisplay.getResourcePK(), PlanConstants.VOTE_FOR_PLAN);
    Boolean isLoggedIn = ! user.isDefaultUser();


    
    // names of tabs that will be displayed in view plan tab panel
    String viewPlanTabNames = PlanConstants.SUMMARY_TAB + "," + PlanConstants.DESCRIPTION_TAB + "," +
    	PlanConstants.MODEL_TAB + "," + PlanConstants.ACTIONS_TAB + "," + PlanConstants.IMPACTS_TAB + "," +
    	PlanConstants.DISCUSSION_TAB + "," + PlanConstants.MEMBERS_TAB;
    List<DebateItem> positions = (List<DebateItem>) request.getAttribute(PlanConstants.PLAN_POSITIONS);
    List<DebateItem> questions = (List<DebateItem>) request.getAttribute(PlanConstants.QUESTIONS);
    Map<DebateItem, List<DebateItem>> questionPositions =
        (Map<DebateItem, List<DebateItem>>) request.getAttribute(PlanConstants.QUESTION_POSITIONS);



    // booleans indicating plan's completnes status
    boolean isContentMissing = planShortContent.trim().equals("");
    boolean areActionsMissing = planScenario.trim().equals("");
    boolean isPlanComplete = ! (isContentMissing || areActionsMissing);

    NumberFormat formatter = new DecimalFormat("#.##");
    DateFormat dateFormatDate = DateFormats.getDate(locale, timeZone);

%>

<%!
    // Position url - here so that we can switch it without having to restart the system!
    public static String planPositionURL = "http://climatecollaboratorium.org/web/guest/debates";


    public static String constructDebateUrl(DebateItem item) {

        String result = planPositionURL;
        try {
            result+="#debate="+item.getDebate().getDebateId()+";item="+item.getDebateItemId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

%>

<script type="text/javascript" src="/html/js/collaboratorium/loginUtils.js"></script>

<!-- Success message displayed when user adds a plan -->
<liferay-ui:success key='<%= portletConfig.getPortletName() + ".doAddPlan" %>' message="request-processed-successfully" />

<!-- Error messages -->
<liferay-ui:error exception="<%= PrincipalException.class %>" message="you-dont-have-necessary-permissions" />
<liferay-ui:error exception="<%= DuplicateGroupException.class %>" message="plan-name-already-exists" />
<liferay-ui:error exception="<%= NoSuchPlanException.class %>" message="requested-plan-desnt-exist" />

<div id="add-plan-dialog" title="Add Plan" style="display: none">
	<%@ include file="/html/portlet/ext/plans/add_plan_dialog.jspf" %>
</div>

<div id="copy-plan-dialog" style="display: none">
	<%@ include file="/html/portlet/ext/plans/copy_plan_dialog.jspf" %>
</div>

<div id="request-membership-dialog" style="display: none">
	<%@ include file="/html/portlet/ext/plans/request_membership_dialog.jspf" %>
</div>

<div id="reply-membership-dialog" style="display: none">
	<%@ include file="/html/portlet/ext/plans/reply_membership_dialog.jspf" %>
</div>


