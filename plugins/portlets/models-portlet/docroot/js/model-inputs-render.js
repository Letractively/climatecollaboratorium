Ice.onSendReceive("mainContent",function() {}, function() {
	log.debug("onReceive");
	showSliders();
	renderModelOutputs();
	showEditForm();
});

jQuery(document).ready(function() {
	icefacesEventManager.registerHandler("renderModelInputs", renderModelInputs);
	icefacesEventManager.registerHandler("modelRunSuccessful", modelRunSuccessful);
});

function renderModelInputs(event) {
	log.debug("render model inputs event");
	showSliders();
}


function modelRunSuccessful(event) {
	log.debug("model run successful");
	renderModelOutputs();
}


function showSliders() {
	var msg = "";
	
	if (jQuery(".sliderDef").length == 0 || jQuery(".sliderDef").hasClass("processed")) {
		return;
	}

	var SLIDER_MIN = 0;
	var SLIDER_MAX = 1000;

	function isInteger(dataType) {
		if (dataType == "java.lang.Integer") return true;
		if (dataType == "java.lang.Long") return true;
	}

	function isDouble(dataType) {
		if (dataType == "java.lang.Double") return true;
		if (dataType == "java.lang.Float") return true;
	}

	jQuery(".sliderDef").each(function() {
		var min = parseFloat(jQuery(this).find(".min").val());
		var max = parseFloat(jQuery(this).find(".max").val());
		var defaultVal = parseFloat(jQuery(this).find(".default").val());
		var dataType = jQuery(this).find(".dataType").val();
		var currentValue = jQuery(this).find(".fieldValue").val();
		if (! isNaN(parseFloat(currentValue))) {
			defaultVal = currentValue;
		}

		var sliderMax = max;
		var sliderMin = min;

		if (isDouble(dataType)) {
			sliderMax = SLIDER_MAX;
			sliderMin = SLIDER_MIN;
		} 

		if (isNaN(defaultVal)) {
			defaultVal = (min+max)/2;

			if (isInteger(dataType)) {
				defaultVal = parseInt(defaultVal);
			}
		}

		var valueField = jQuery(this).find(".value");
		var slider = jQuery(this).find(".slider");
		var sliderStep = (max-min)/(sliderMax - sliderMin);


		slider.slider('destroy');

		valueField.val(defaultVal);


		slider.slider({ 
			min: sliderMin,
			max: sliderMax, 
			slide: function(event, ui) {
			if (isInteger(dataType)) {
				valueField.val((ui.value));
			}
			else if (isDouble(dataType)) {
				valueField.val((min + sliderStep * (ui.value)).toFixed(2));
			}
		}
		});

		var sliderVal = defaultVal;
		if (isDouble(dataType)) {
			sliderVal = parseInt(((defaultVal-min) / (max-min)) * (sliderMax - sliderMin));
		}
		slider.slider("moveTo", sliderVal);

		valueField.blur(function() {
			var sliderVal = valueField.val();

			if (isDouble(dataType)) {
				sliderVal = ((sliderVal - min) / (max-min)) * (sliderMax - sliderMin);
			}

			slider.slider("moveTo", sliderVal);
			return true;
		});
	});

	jQuery("#runModel").unbind();
	jQuery("#runModel").click(function() {
		// get data for all model inputs
		var values = new Object();

		jQuery(".sliderDef").each(function() {
			var id = jQuery(this).find('.id').val();
			var val = jQuery(this).find('.value').val();
			values[id] = val;
		});
		
		icefacesEventManager.sendEventToTheBackend("modelRun", values);
	});
	

	jQuery(".sliderDef").eq(0).addClass("processed");

}

function renderModelOutputs() {

	log.profile("renderModelOutputs");
	/* Check if outputs have been already processed, if they have been then there is no need
	 * to rerender graphs.
	 */
	if (jQuery(".outputDef").length == 0 || jQuery(".outputDef").hasClass("processed")) {
		//log.debug("model outputs already processed");
		return;
	}
	
	/*
	 * Render graphs
	 */
	log.debug("ploting outputs");
	jQuery(".outputDef").each(function() {
		try {

			var chartType = jQuery(this).find(".chartType").val();
			if (typeof(chartType) == 'undefined' || chartType != "TIME_SERIES") {
				return;
			}
			var def = jQuery(this);
			var chartPlaceholderId = def.find(".chartPlaceholder").attr("id");
			var chartTitle = def.find(".chartTitle").val();
			var values = [];
			var series = [];
			def.find(".serieDef").each(function() {
				var val = eval("(" + jQuery(this).find(".value").val() + ")" );
				var label = jQuery(this).find(".label").val();

				for (var i = 0; i < val.length; i++) {
					val[i] = [parseFloat(val[i][0]), parseFloat(val[i][1])];
				}
				values.push(val);
				series.push({showMarker: false, label: label});
			});

			var plot = jQuery.jqplot(chartPlaceholderId, values, 
					{title: chartTitle, 
				series: series,
				axes:{
				xaxis:{
				label:'Year',
				autoscale: true
			},
			},
			legend : {
				show :true,
				location :'nw',
				yoffset :280,
				xoffset:0
			}
					});
		}
		catch (e) {
			log.error(e);
		}
		
		/* Legend is positioned absolutely thus it is hard to determine how long the chart will be, I'm assuming
		 * that graph will take around 320 px and I'm giving 18 px for each item in the legend.
		 */

		jQuery("#" + chartPlaceholderId).parent().css("height", (320 + (18 * values.length)) + "px");
	});
	
	
	/* this is rather ugly hack, used because there is no good handling of output grouping */
	
	if (jQuery(".impactsContent .physicalImpact").length > 0) {
		var physicalImpacts = [];
		var tabs = "<div class='tabsContainer'><ul>";
		var contents = "";


		jQuery(".impactsContent").append("<div class='outputDef hidden physicalImpactsTab' ><h3><a href='javascript:;' class='activator'>Physical impacts</a>" +  
				'<a class="folder" href="javascript:;" tabindex="-1"><span class="hidden">Folder/unFolder</span></a></h3><div>' + 
				'<ul class="tabsContainer"></ul><div class="contentsContainer"></div><div class="clear"></div></div></div>');
		
		var tabsContainer = jQuery(".physicalImpactsTab .tabsContainer");
		var contentsContainer = jQuery(".physicalImpactsTab .contentsContainer");
		
		jQuery(".impactsContent .physicalImpact").each(function() {
			/*
			physicalImpacts.push({
				title: jQuery(this).find(".title").html(),
				content: jQuery(this).find(".content").html()
			});*/
			tabsContainer.append("<li title='" + jQuery(this).find(".title a").attr("rel") + "'>" + jQuery(this).find(".title").html().replace("/", "/ ") + "</li>");
			contentsContainer.append(jQuery(this).find(".content").html());
			
			/*
			contents += jQuery(this).find(".content").html();
			tabs += "<li>" + jQuery(this).find(".title").html() + "</li>";
			var content = jQuery(this).find(".content");
			jQuery(this).find(".title").click(function() { var name = jQuery(this).attr(title); jQuery("#" + name).hide(); alert(name + jQuery("#" + name).length) });
			content.hide();
			*/
			
		});

		jQuery(".impactsContent .physicalImpact").remove();
		

		contentsContainer.find(".tabContent").hide();
		tabsContainer.find("li").click(function() { 
			var name = jQuery(this).attr("title");
			jQuery(this).parent().find(".active").removeClass("active");
			
			jQuery(this).addClass("active");
			
			contentsContainer.find(".tabContent").hide(200);
			jQuery("#" + name).show(200); 
		});
		
		contentsContainer.find(".tabContent").eq(0).show();
		tabsContainer.find("li").eq(0).click();

		
		//jQuery(".physicalImpactsTab .container").tabs().addClass('ui-tabs-vertical ui-helper-clearfix').find("li").removeClass('ui-corner-top').addClass('ui-corner-left');
	}
	
	/* end of physical impacts hack */
	
	
	initAccordion();
	
	jQuery(".outputDef").eq(0).addClass("processed");
	jQuery(".outputDef").show();
	log.profile("renderModelOutputs");
	
}

function initAccordion() {
	var container = jQuery(".outputDef");
	container.find("h3").unbind();
	container.find("h3").click(function() {
		if (jQuery(this).hasClass("ui-state-active")) {
			return;
		}
		var prevActive = container.find(".ui-state-active");
		prevActive.removeClass("ui-state-active");
		prevActive.addClass("ui-state-default");
		prevActive.next().hide(400);
		prevActive.attr("tabindex", "-1");

		jQuery(this).addClass("ui-state-active");
		jQuery(this).removeClass("ui-state-default");
		jQuery(this).next().show(400);
		jQuery(this).attr("tabindex", "0");
	});

	var firstHeader = container.find("h3").get(0);
	container.find("h3").each(function() {
		jQuery(this).addClass("ui-accordion-header").addClass("ui-state-default");
		if (firstHeader != this) {
			jQuery(this).next().hide();
		}
		else {
			jQuery(this).addClass("ui-state-active");
			jQuery(this).next().show();
		}
	});

	//container.find("h3").eq(0).click();

}

function showEditForm() {
	//jQuery("#editModel").tabs();
}



/**
edit form related stuff 
*/
/*
jQuery("#showOrder").click(function() {
var items = jQuery(".sortableOutputDisplay .outputId");
var order = [];
items.each(function() {
	order.push(this.value);
});
//alert(order);

icefacesEventManager.sendEventToTheBackend("updateOutputsOrder", order);
});	jQuery(function() {
jQuery(".sortableOutputDisplay").each(function() {
	var subContent = jQuery(this).find(".subSortableOutputDisplay").html();
	jQuery(this).find("subSortableOutputDisplay").html("<ul>" + content + "</ul>");

	var content = jQuery(".sortableOutputDisplay").html();
	jQuery(this).html("<ul>" + content + "</ul>");


	jQuery(this).find("ul").sortable();
	jQuery(this).find("ul").disableSelection();

	jQuery(this).find(".subSortableOutputDisplay").sortable();
});

});



setTimeout(function() {
	jQuery("#editModel").tabs();
}, 5000);


*/

