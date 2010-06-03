<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%
/**
 *
 *
 * File responsible for presenting a plan model contents.
 *
 * version 1.1 : add model description. static for now
 * version 1.2 : model description displayed by Model modules (jspf)
 *
 * @author janusz.p, BeBetter
 * @version 1.2
 * @since 1.0
 */
%>


<%@ include file="/html/portlet/ext/plans/init.jsp" %>

<!-- Tabs panel -->
<%@ include file="/html/portlet/ext/plans/view_plan/view_plan_tabs.jspf" %>

<%
	pageContext.setAttribute("showSelectedModel", true);
	String modelId = String.valueOf(plan.getPlanType().getModelId());
    boolean showAddPositions = false;
%>

<div id="plans-model-impacts-container" style="position: relative; left: -2000px; padding: 10px; padding-right: 20px;">
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
              
        
        frame.height = frameContent.body.scrollHeight;
        jQuery(frame).css("overflow", "hidden");
        jQuery(frame).css("border", "0");
        
        jQuery("#portlet-model-impacts").load(portletModelImpactsInnerPortlet);
        frame.contentWindow.jQuery.history.init(function() {});

        //alert("should show..." + jQuery("#plans-model-impacts-container").length);
        
        jQuery("#plans-model-impacts-container").css("left", "0").css("position", "normal");
    }

    
    /**
    Function responsible for attaching portletPlansModifyInnerPortlet to iframe's onload event 
    */ 
    jQuery(document).ready(function() {
        var frame = jQuery("#portlet-model-impacts").load(portletModelImpactsInnerPortlet);
    });

    Liferay.Widget({ url: "/widget/web/guest/test/-/models_WAR_modelsportlet_INSTANCE_ULd7?modelId=<%= plan.getPlanType().getModelId() %><%= plan.getScenarioId() != null ? "&scenarioId=" + plan.getScenarioId() : "" %>&viewType=embedded&page=model" ,  id: "portlet-model-impacts", scrolling: "no", FRAMEBORDER: 0});
    
</script>
</div>


<%@ include file="/html/portlet/ext/plans/view_plan/bottom.jspf" %>