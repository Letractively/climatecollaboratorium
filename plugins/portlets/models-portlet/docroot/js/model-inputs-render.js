try {
Ice.onSendReceive("mainContent",function() {}, function() {
	log.debug("onReceive");
	setTimeout(function() {
	showSliders();
	renderModelOutputs();
	initEditForms();
	}, 100);
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
	
	try {
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
	
	function formatFieldValue(value, unit) {
		log.debug(unit + "\t" + unit.toLowerCase().indexOf("percent"));
		if (unit.toLowerCase().indexOf("percent") >= 0) {
			log.debug("returning percents: " + (value*100) + "%");
			return (value * 100).toFixed(0) + "%";
		}
		return value;
	}
	
	function parseFieldValue(value, unit) {
		if (unit.toLowerCase().indexOf("percent") >= 0) {
			return parseFloat(value.replace("%")) / 100;
		}
		return value;
	}

	jQuery(".sliderDef").each(function() {
		var min = parseFloat(jQuery(this).find(".min").val());
		var max = parseFloat(jQuery(this).find(".max").val());
		var defaultVal = parseFloat(jQuery(this).find(".default").val());
		var dataType = jQuery(this).find(".dataType").val();
		var currentValue = jQuery(this).find(".fieldValue").val();
		var type = jQuery(this).find(".type").val();
		var unit = jQuery(this).find(".unit").val();
		
		log.debug("type: " + type + "\tunit: " + unit + "\tdata type: " + dataType);

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
		valueField.val(formatFieldValue(defaultVal, unit));

		if (type != "SLIDER") {
			return;
		}

		var slider = jQuery(this).find(".slider");
		var sliderStep = (max-min)/(sliderMax - sliderMin);

		slider.slider('destroy');



		slider.slider({ 
			min: sliderMin,
			max: sliderMax, 
			slide: function(event, ui) {
			if (isInteger(dataType)) {
				valueField.val(formatFieldValue(ui.value, unit));
			}
			else if (isDouble(dataType)) {
				valueField.val(formatFieldValue( (min + sliderStep * (ui.value)).toFixed(2), unit));
			}
		}
		});

		var sliderVal = defaultVal;
		if (isDouble(dataType)) {
			sliderVal = parseInt(((defaultVal-min) / (max-min)) * (sliderMax - sliderMin));
		}
		slider.slider("option", "value", sliderVal);

		valueField.blur(function() {
			var sliderVal = parseFieldValue(valueField.val(), unit);

			if (isDouble(dataType)) {
				sliderVal = ((sliderVal - min) / (max-min)) * (sliderMax - sliderMin);
			}

			slider.slider("option", "value", sliderVal);
			return true;
		});
	});

	jQuery("#runModel").unbind();
	jQuery("#runModel").click(function() {
		showLoadingScreen();
		// get data for all model inputs
		var values = new Object();

		jQuery(".sliderDef").each(function() {
			try {
			var id = jQuery(this).find('.id').val();
			var val = jQuery(this).find('.value').val();
			var unit = jQuery(this).find('.unit').val();
			log.debug("adding input value, id: " + id + "\tval: " + val + "\tunit: " + unit + "\tparsedval: " + parseFieldValue(val, unit));
			values[id] = parseFieldValue(val, unit);
			} catch (e) { log.error(e) }
		});
		
		
		icefacesEventManager.sendEventToTheBackend("modelRun", values);
	});
	}
	catch (e) {log.error(e);}

	jQuery(".sliderDef").eq(0).addClass("processed");

}

function renderModelOutputs() {
	unlockImpactsScreen();

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
			var indexMin = parseFloat(def.find(".indexMin").val());
			var indexMax = parseFloat(def.find(".indexMax").val());
			if (isNaN(indexMin) || isNaN(indexMax)) {
				indexMin = null;
				indexMax = null;
			}
			
			var values = [];
			var valuesById = {};
			var labelsById = {};
			var confIntervalById = {};
			var min = null;
			var max = null;
			var yaxis = {};
			var xaxis = {label:'Year', autoscale: true};
			
			
			var series = [];
			def.find(".serieDef").each(function() {
				var val = eval("(" + jQuery(this).find(".value").val() + ")" );
				var label = jQuery(this).find(".label").val();
				var id = jQuery(this).find(".id").val();
				min = jQuery(this).find(".min").val();
				max = jQuery(this).find(".max").val();
				if (isNaN(parseFloat(min)) || isNaN(parseFloat(max))) {
					min = null;
					max = null;
				}
				var associatedId = jQuery(this).find(".associatedId").val();

				for (var i = 0; i < val.length; i++) {
					val[i] = [parseFloat(val[i][0]), parseFloat(val[i][1])];
				}
				valuesById[id] = val;
				labelsById[id] = label;
				
				if (parseInt(associatedId) > 0) {
					if (typeof(confIntervalById[associatedId]) == 'undefined') {
						confIntervalById[associatedId] = [];
					}
					confIntervalById[associatedId].push(id);
				} else {
					values.push(val);
					series.push({showMarker: false, label: label});
				}
			});
			
			// set min/max
			
			if (min != null && max != null) {
				yaxis.min = min;
				yaxis.max = max;
			}
			if (indexMin != null && indexMax != null) {
				xaxis.min = indexMin;
				xaxis.max = indexMax;
			}
			
			
			for (var x in confIntervalById) {
				var valMain = valuesById[x];
				var valConf1 = valuesById[confIntervalById[x][0]];
				var valConf2 = valuesById[confIntervalById[x][1]];
				var intervalVal = [];
				for (var i = 0; i < valMain.length; i++) {
					//intervalVal[i] = [valMain[i][0], valConf2[1], valMain[i][1], valConf1[1]];
					intervalVal[i] = [valMain[i][0], valConf1[i][1], valConf2[i][1], valMain[i][1]];
				}
				
				values.push(intervalVal);
				series.push({showMarker: false, showLabel: true, label: "90% Confidence interval for " + labelsById[x], 
					renderer: jQuery.jqplot.OHLCRenderer, color: "rgb(125, 228, 247)"});
			}

			var plot = jQuery.jqplot(chartPlaceholderId, values, 
					{title: chartTitle, 
				series: series,
				axes:{
					xaxis: xaxis,
					yaxis: yaxis
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
			
			contentsContainer.find(".tabContent").hide();
			jQuery("#" + name).show(); 
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
		prevActive.next().slideUp();
		prevActive.attr("tabindex", "-1");

		jQuery(this).addClass("ui-state-active");
		jQuery(this).removeClass("ui-state-default");
		jQuery(this).next().slideDown(200);
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
	/*
	try {
	log.debug("HAHAHAHAHA jestem glupia funkcja" + jQuery(".sortableOutputDisplay").length );
	jQuery(".sortableOutputDisplay").each(function() {
		log.debug("sortable output display - liczba: " + jQuery(this).length);
		var subContent = jQuery(this).find(".subSortableOutputDisplay").html();
		jQuery(this).find("subSortableOutputDisplay").html("<ul>" + content + "</ul>");

		var content = jQuery(".sortableOutputDisplay").html();
		jQuery(this).html("<ul>" + content + "</ul>");


		jQuery(this).find("ul").sortable();
		jQuery(this).find("ul").disableSelection();

		jQuery(this).find(".subSortableOutputDisplay").sortable();
		
	});
	} catch(e) {log.error(e) };

	

	jQuery("#showOrder").click(function() {
		var items = jQuery(".sortableOutputDisplay .outputId");
		var order = [];
		items.each(function() {
			order.push(this.value);
		});
	});
	
	jQuery("#editModel").tabs();
	*/
}



function showLoadingScreen() {
	var impactsPlaceholder = jQuery(".impactsContent");
	if (impactsPlaceholder.length > 0) {
		impactsPlaceholder.css( {
			'display' :'block'
		});
		impactsPlaceholder.unblock();
		impactsPlaceholder.block(
						{
							message :'<img src="/html/portlet/ext/models/images/ajax-loader.gif"/>'
						});
	}
}

function unlockImpactsScreen() {
	var impactsPlaceholder = jQuery(".impactsContent");
	impactsPlaceholder.unblock();
}
	


/**
edit form related stuff 
*/

//alert(order);

//icefacesEventManager.sendEventToTheBackend("updateOutputsOrder", order);
/*
jQuery(function() {
	log.debug("HAHAHAHAHA jestem glupia funkcja" + jQuery(".sortableOutputDisplay").length );
	jQuery(".sortableOutputDisplay").each(function() {
		log.debug("sortable output display - liczba: " + jQuery(this).length);
		var subContent = jQuery(this).find(".subSortableOutputDisplay").html();
		jQuery(this).find("subSortableOutputDisplay").html("<ul>" + content + "</ul>");

		var content = jQuery(".sortableOutputDisplay").html();
		jQuery(this).html("<ul>" + content + "</ul>");


		jQuery(this).find("ul").sortable();
		jQuery(this).find("ul").disableSelection();

		jQuery(this).find(".subSortableOutputDisplay").sortable();
});

});
*/



function initEditForms() {
	/* Inputs ordering */
	if (!jQuery("#inputsOrder").length) {
    jQuery(".subInputsOrder").each(function() {
    	var ul = jQuery(this);
    	var itemsContainer = ul.next();
    	ul.html(itemsContainer.html());
    	itemsContainer.remove();
    });

    var inputsForOrdering = jQuery(".inputOrderDef").html();
    jQuery(".inputOrderDef").html('<ul id="inputsOrder">' + inputsForOrdering + "</ul>");
    
    jQuery("#inputsOrder, .subInputsOrder").sortable({stop: function() {
    
    	var counter = 0; 
    	jQuery(".singleInputOrderDef input").each(function() {
    		this.value = counter++;
    	});
    }});
	}
	
	/* Inputs grouping */
	if (true) {
		jQuery(".containerForRemoval").each(function() {
			var content = jQuery(this).html();
			var parent = jQuery(this).parent();
			jQuery(this).remove();
			parent.append(content);
		});
		
		jQuery(".individualInput").draggable({helper: "original"});
		jQuery(".groupedInputs").droppable({
			drop: function(event, ui) {
				jQuery(this).find(".placeholder").remove();
				/*
				jQuery("<li></li>").text(ui.draggable.text()).appendTo(this);
				*/
				log.debug("position przed: " + ui.draggable.css("position"));
				log.debug(ui.draggable.css("position"));
				ui.draggable.css("top", null);
				ui.draggable.css("left", null);
				ui.draggable.appendTo(this);
				//ui.draggable.draggable();
				
		}});

		
	}
    
}
} catch (e) {
	alert(e);
}
