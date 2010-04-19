<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%
/**
 *
 * Base jsp file that is included by all Models portlet jsp files. It imports necessary classess and
 * retrieves common values from query parameters/request attributes.
 *
 * version 1.1: merged to modular version (moved js inclusion statements to modules/init.jspf which is included)
 *
 * @author BeBetter, janusz.p
 * @version 1.1
 * @since 1.0
 */
%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/html/portlet/init.jsp" %>
<%@ include file="/html/portlet/ext/models/modules/init.jspf" %>

<%@ page import="com.ext.portlet.debaterevision.model.DebateItem" %>
<%@ page import="com.ext.portlet.debaterevision.model.DebateCategory" %>

<%@ page import="com.ext.portlet.models.DebatesConstants" %>
<%@ page import="com.liferay.util.LocalizationUtil" %>


<%
	//String planPositionURL = (String) request.getAttribute(PlanConstants.PLAN_POSITION_URL);
	PortletPreferences preferences = renderRequest.getPreferences();

	//select positions popup changes
	/*List<MBMessage> positions = (List<MBMessage>) request.getAttribute(ModelConstants.MODEL_POSITIONS);
	 List<MBMessage> questions = (List<MBMessage>) request.getAttribute(PlanConstants.QUESTIONS);
	 Map<MBMessage, List<MBMessage>> questionPositions =
	        (Map<MBMessage, List<MBMessage>>) request.getAttribute(PlanConstants.QUESTION_POSITIONS);
	 String modelId=ParamUtil.getString(request, "sim");*/


	String portletResource = ParamUtil.getString(request, "portletResource");

	if (Validator.isNotNull(portletResource)) {
		preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
	}

	String currentLanguageId = LanguageUtil.getLanguageId(request);
	Locale currentLocale = LocaleUtil.fromLanguageId(currentLanguageId);
	Locale defaultLocale = LocaleUtil.getDefault();
	String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

	Locale[] locales = LanguageUtil.getAvailableLocales();

	String[] priorities = LocalizationUtil.getPreferencesValues(preferences, "priorities", currentLanguageId);

	int rssDelta = GetterUtil.getInteger(preferences.getValue("rss-delta", StringPool.BLANK), SearchContainer.DEFAULT_DELTA);
	String rssDisplayStyle = preferences.getValue("rss-display-style", RSSUtil.DISPLAY_STYLE_FULL_CONTENT);
	String rssFormat = preferences.getValue("rss-format", "atom10");
	boolean allowAnonymousPosting = MBUtil.isAllowAnonymousPosting(preferences);
	boolean enableFlags = GetterUtil.getBoolean(preferences.getValue("enable-flags", null), true);
	boolean enableRatings = GetterUtil.getBoolean(preferences.getValue("enable-message-ratings", null), true);

	String rssFormatType = RSSUtil.DEFAULT_TYPE;
	double rssFormatVersion = RSSUtil.DEFAULT_VERSION;

	if (rssFormat.equals("rss10")) {
		rssFormatType = RSSUtil.RSS;
		rssFormatVersion = 1.0;
	}
	else if (rssFormat.equals("rss20")) {
		rssFormatType = RSSUtil.RSS;
		rssFormatVersion = 2.0;
	}
	else if (rssFormat.equals("atom10")) {
		rssFormatType = RSSUtil.ATOM;
		rssFormatVersion = 1.0;
	}

	StringBuilder rssURLParams = new StringBuilder();

	if ((rssDelta != SearchContainer.DEFAULT_DELTA) || !rssFormatType.equals(RSSUtil.DEFAULT_TYPE)
	        || (rssFormatVersion != RSSUtil.DEFAULT_VERSION)
	        || !rssDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_FULL_CONTENT)) {
		if (rssDelta != SearchContainer.DEFAULT_DELTA) {
			rssURLParams.append("&max=");
			rssURLParams.append(rssDelta);
		}

		if (!rssFormatType.equals(RSSUtil.DEFAULT_TYPE)) {
			rssURLParams.append("&type=");
			rssURLParams.append(rssFormatType);
		}

		if (rssFormatVersion != RSSUtil.DEFAULT_VERSION) {
			rssURLParams.append("&version=");
			rssURLParams.append(rssFormatVersion);
		}

		if (!rssDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_FULL_CONTENT)) {
			rssURLParams.append("&displayStyle=");
			rssURLParams.append(rssDisplayStyle);
		}
	}

	boolean childrenMessagesTaggable = true;
	boolean includeFormTag = true;
	boolean showSearch = true;
	boolean showSearchCategory = showSearch;
	boolean showSearchThread = showSearch;

	DateFormat dateFormatDate = DateFormats.getDate(locale, timeZone);
	DateFormat dateFormatDateTime = DateFormats.getDateTime(locale, timeZone);

	NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
	String debatesIndexTab = ParamUtil.getString(request, DebatesConstants.VIEW_DEBATES_INDEX_TABS,
    	    DebatesConstants.DISCUSSION_TAB);

	//one of these days, maybe i'll figure out why i can't get preferences to refresh.
	//String modelPositionURL = (String) renderRequest.getPreferences().getValue(ModelConstants.MODEL_POSITION_URL,"");
	//String modelQuestionURL = (String) renderRequest.getPreferences().getValue(ModelConstants.MODEL_QUESTION_URL,"");

    	String modelPositionURL = ModelConstants.MODEL_POSITION_URL_ACTUAL;
	String modelQuestionURL = ModelConstants.MODEL_QUESTION_URL_ACTUAL;
    List<DebateItem> positions = (List<DebateItem>) request.getAttribute(ModelConstants.MODEL_POSITIONS);
        List<DebateItem> questions = (List<DebateItem>) request.getAttribute(ModelConstants.QUESTIONS);
        Map<DebateItem, List<DebateItem>> questionPositions =
            (Map<DebateItem, List<DebateItem>>) request.getAttribute(ModelConstants.QUESTION_POSITIONS);



          boolean showAddPositions =permissionChecker.hasPermission (themeDisplay.getPortletGroupId(), portletDisplay.getRootPortletId(),
                 portletDisplay.getResourcePK(),
                ModelConstants.ADD_POSITION);

%>



<%!
    // Position url - here so that we can switch it without having to restart the system!
    public static String modelPositionURL = "http://climatecollaboratorium.org/web/guest/debates";


    public static String constructDebateUrl(DebateItem item) {

        String result = modelPositionURL;
        try {
            result+="#debate="+item.getDebate().getDebateId()+";item="+item.getDebateItemId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

%>




<script type="text/javascript">
function <portlet:namespace />showDialog(dialogId, options) {

	jQuery("#" + dialogId).dialog({ height: options.height, width: options.width, modal: true, resizable: false, draggable: false, dialogClass: 'collab-dialog' });
	jQuery("#" + dialogId).show();
}

function <portlet:namespace/>closeDialog(dialogId) {
	jQuery("#" + dialogId).dialog("close");
}



</script>



<portlet:defineObjects />

<liferay-portlet:renderURL windowState="<%= WindowState.NORMAL.toString() %>" var="viewModelsURL">
	<portlet:param name="struts_action" value="/ext/models/view_models" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL windowState="<%=WindowState.NORMAL.toString() %>" var="runModelURL">
	<portlet:param name="struts_action" value="/ext/models/run_model" />
</liferay-portlet:renderURL>
