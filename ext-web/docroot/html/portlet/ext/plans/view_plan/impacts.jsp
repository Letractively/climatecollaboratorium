<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%
/**
 *
 *
 * File responsible for rendering a plan actions page. Currently it contains only static HTML.
 *
 * version 1.1 : add impacts i.e. some results after simulation is ran
 * version 1.2 : impacts displayed by Model modules (jspf)
 *
 * @author janusz.p, BeBetter
 * @version 1.1
 * @since 1.0
 */
%>

<%@ include file="/html/portlet/ext/plans/init.jsp" %>

<!-- Tabs panel -->
<%@ include file="/html/portlet/ext/plans/view_plan/view_plan_tabs.jspf" %>

<script type="text/javascript">

	// currently there is no dynamic association between plan and a scenario/model,
	// static reference used, please refer to requirements doc
	
	var scenarioId = '<%= plan.getScenarioId() %>';
	
</script>
<%@ include file="/html/portlet/ext/models/modules/init.jspf" %>

<div class="impactsTab">
	<%@ include file="/html/portlet/ext/models/modules/impacts.jspf" %>	
</div>
<div class="clear"></div>


<%@ include file="/html/portlet/ext/plans/view_plan/bottom.jspf" %>