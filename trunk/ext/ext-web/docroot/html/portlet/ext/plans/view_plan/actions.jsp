<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%
/**
 *
 *
 * File responsible for rendering a plan impacts page. Currently it contains only static HTML.
 *
 * version 1.1 : add models action
 * version 1.2 : impacts displayed by Model modules (jspf), added JS code to support runing/editing
 *    impacts
 *
 * @author janusz.p, BeBetter
 * @version 1.2
 * @since 1.0
 */
%>
<%@ include file="/html/portlet/ext/plans/init.jsp" %>

<!-- Tabs panel -->
<%@ include file="/html/portlet/ext/plans/view_plan/view_plan_tabs.jspf" %>


<div id="plans-model-impacts-container" style="position: relative; left: -2000px; padding: 10px; padding-right: 20px;">
        <portlet:actionURL windowState="NORMAL" var="submitActionURLactions">
            <portlet:param name="struts_action" value="/ext/plans/edit_plan" />
        </portlet:actionURL>
    
        <form action="${submitActionURLactions}" method="post" name="<portlet:namespace />fm" id="<portlet:namespace />editActionsForm">
            <input type="hidden" name="<%= Constants.CMD %>" value="<%= Constants.UPDATE %>" />
            <input type="hidden" name="<%= PlanConstants.UPDATE_TYPE %>" value="<%= PlanConstants.UPDATE_SCENARIO %>" />
            <input type="hidden" id="<portlet:namespace />scenarioId" name="<portlet:namespace /><%= PlanConstants.SCENARIO_ID %>" />
            <input type="hidden" name="<portlet:namespace /><%= PlanConstants.PLAN_ID %>" value="<%= plan.getPlanId() %>" />
            <input name="<portlet:namespace /><%= PlanConstants.REDIRECT %>" type="hidden" value="<%= currentURL %>" />
        </form>

<script src="/html/js/liferay/widget.js" type="text/javascript"></script>
<script type="text/javascript">

    // currently there is no dynamic association between plan and a scenario/model,
    // static reference used, please refer to requirements doc
    
   
    var scenarioId = '<%= plan.getScenarioId() %>';

    function portletModelImpactsInnerPortlet() {
        // update iframe height to wrap entire document, this prevents from displaying additional scrollbar
        var frame = document.getElementById("portlet-model-impacts");
        var frameContent =  (frame.contentDocument) ? frame.contentDocument : frame.contentWindow.document;
        jQuery(frame).attr("scrolling", "no");
        var contentDocument = jQuery(frameContent);

        frameContent.saveScenarioFunction = function(scenarioId) {
            jQuery("#<portlet:namespace />scenarioId").val(scenarioId);
            jQuery("#<portlet:namespace />editActionsForm").submit();
        }

        frameContent.updateFrameHeight = function() {
            frame.height = frameContent.body.scrollHeight + 50;
        }
            
     

        frame.height = frameContent.body.scrollHeight + 50;
        
        jQuery(frame).css("overflow", "hidden");
        jQuery(frame).css("border", "0");
        
        jQuery("#portlet-model-impacts").load(portletModelImpactsInnerPortlet);

        //alert("should show..." + jQuery("#plans-model-impacts-container").length);
        
        jQuery("#plans-model-impacts-container").css("left", "0").css("position", "normal");
        if (<%= canEditPlan %>) {
            setTimeout(function() {contentDocument.find(".editBtn").show();}, 10);
        }
    }

    
    /**
    Function responsible for attaching portletPlansModifyInnerPortlet to iframe's onload event 
    */ 
    jQuery(document).ready(function() {
        var frame = jQuery("#portlet-model-impacts").load(portletModelImpactsInnerPortlet);
    });

    Liferay.Widget({ url: "/widget/web/guest/test/-/models_WAR_modelsportlet_INSTANCE_ULd7?modelId=<%= plan.getPlanType().getModelId() %>&viewType=embedded&page=actions&planId=<%= plan.getPlanId() %><%= plan.getScenarioId() != null ? "&scenarioId=" + plan.getScenarioId() : "" %>" ,  id: "portlet-model-impacts", scrolling: "no", FRAMEBORDER: 0});
    
</script>
</div>


<%@ include file="/html/portlet/ext/plans/view_plan/bottom.jspf" %>
