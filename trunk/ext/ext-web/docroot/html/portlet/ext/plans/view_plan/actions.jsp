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
            alert(document + " mam save scenario!" + jQuery(document).find("#<portlet:namespace />editActionsForm").length);
            jQuery("#<portlet:namespace />editActionsForm").submit();
        };
     
        
        frame.height = frameContent.body.scrollHeight + 500;
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

    Liferay.Widget({ url: "/widget/web/guest/test/-/models_WAR_modelsportlet_INSTANCE_ULd7?modelId=760&viewType=embedded&page=actions&planId=<%= plan.getPlanId() %>" ,  id: "portlet-model-impacts", scrolling: "no", FRAMEBORDER: 0});
    
</script>
</div>

<%@ include file="/html/portlet/ext/models/modules/init.jspf" %>

<script type="text/javascript">

	// currently there is no dynamic association between plan and a scenario/model,
    // static reference used, please refer to requirements doc

	var scenarioId = '<%= plan.getScenarioId() %>';
	var modelId = '<%= plan.getPlanType().getModelId() %>';
	
	var actionsReadonly = scenarioId > 0;
	var showImpactsLazy = scenarioId > 0;
	var showProgress = true;

<c:if test="<%=canEditPlan%>">

	/**
	 * If called it refreshes the page, used to cancel actions editing.
	 */
	function <portlet:namespace />cancel() {
		location.href = "<%= currentURL %>";
	}

	/**
	 * Enables editing of actions form.
	 */
	function <portlet:namespace />editActions() {
		jQuery("#action").css('margin-left', '10px');
		
		jQuery("#actions-panel").removeClass("hide-impacts");
		jQuery("#actions-panel").removeClass("hide-impacts-actions");
		jQuery("#impacts-panel").removeClass("hide-impacts");
		jQuery("#impacts-panel").removeClass("hide-impacts-impacts");
		
		jQuery("#actions-panel").addClass("show-impacts");
		jQuery("#impacts-panel").addClass("show-impacts");
		
		jQuery(".btnBox").show();
		jQuery("#<portlet:namespace />editActions").hide();
		jQuery("#<portlet:namespace />runModel").show();
		jQuery("#<portlet:namespace />save").show();
		jQuery("#<portlet:namespace />cancel").show();
		if (scenarioId > 0) {
			ModelUtils.showScenario('impactsContent');
		} else {
			jQuery(".run-the-model-to-see-inputs").show();
		}
		ModelUtils.enableModelForm();
	}

	/**
	 * Runs model with specified actions.
	 */
	function <portlet:namespace />runModel() {
		jQuery("#<portlet:namespace />runModel").attr("disabled", true);
		jQuery("#<portlet:namespace />save").attr("disabled", true);
		ModelUtils.scenarioReady(function(type) {
			jQuery("#<portlet:namespace />runModel").removeAttr("disabled");
			if (type == "success") {
				jQuery("#<portlet:namespace />save").removeAttr("disabled");
				jQuery("#<portlet:namespace />save").show();
			}
			
		});
		showImpactsLazy = false;
		ModelUtils.runModel();
		
	}

	/**
	 * Saves the scenario. Currently a mock up.
	 */
     /*
	function <portlet:namespace />save() {
		var scenario = ModelUtils.getScenario();
        editActionsForm.submit();
		ModelUtils.saveScenario(function(result) {
			if ("success"==result) {
				var editActionsForm = jQuery("#<portlet:namespace />editActionsForm");
				jQuery("#<portlet:namespace />scenarioId").val(scenario.id);
				editActionsForm.submit();
			} else {
				<portlet:namespace />cancel();
			}

		});
    	
	}*/

	/**
	 *  Edit button should be shown when model has been loaded and it's ready.
	 */
	ModelUtils.modelReady(function() {
		if (scenarioId > 0) { 
			jQuery("#<portlet:namespace />editActions").show();
		} else {
			<portlet:namespace />editActions();
		}
	});

</c:if>
	
</script>


<div class="model-information">
	<div class="model-information-container-centered">
                  <div class="hide-impacts hide-impacts-actions" id="actions-panel">
				     <%@ include file="/html/portlet/ext/models/modules/actions.jspf" %>
		        </div>
			    <div class="hide-impacts hide-impacts-impacts" id="impacts-panel">
			          <%@ include file="/html/portlet/ext/models/modules/impacts.jspf" %>
		        </div>
		<div class="clear"></div>
	</div>
</div>
<c:if test="<%=canEditPlan%>">
	<div class="run-model-actions">
		

	
		<div style='width: 100%; margin: auto'>
			<a href="javascript:;" id="<portlet:namespace />editActions" onClick="<portlet:namespace />editActions()" style="display: none" class="editBtn"><span class="hidden">Edit</span></a>
			<div class='buttons-centered'>        		
        		<a href="#" id="<portlet:namespace />runModel" class="saveChanges" style="display: none" onClick="<portlet:namespace />save(); return false;"><span class="hidden">Save changes</span></a>
        		<a href="javascript:;" id="<portlet:namespace />save" class="runModel" style="display: none" onClick="<portlet:namespace />runModel()"><span class="hidden">run model</span></a>
        		<a href="javascript:;" id="<portlet:namespace />cancel" class="cancel" style="display: none" onClick="<portlet:namespace />cancel()"><span class="hidden">cancel</span></a>
        		<div class="clear"></div>
        	</div>
        </div>
	</div>
	
</c:if>

<%@ include file="/html/portlet/ext/plans/view_plan/bottom.jspf" %>
