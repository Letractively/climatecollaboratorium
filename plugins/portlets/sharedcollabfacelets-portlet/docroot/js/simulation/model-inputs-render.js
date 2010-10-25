/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */





//setTimeout(function() { log.toggle(); }, 1000);
var debug = false;
var inputValues = {};
function renderModelInputs(event) {
	showSliders();
}


function modelRunSuccessful(event) {
	renderModelOutputs();
}

function disableInputs(disableInputs, editbuttonvisible,embedded) {
    if (disableInputs || !editbuttonvisible || (editbuttonvisible && embedded)) {
    jQuery(".actions input").attr('disabled', disabled);
    jQuery(".actions .slider").slider("option", "disabled", disabled);
        }
}


function isInteger(dataType) {
	if (dataType == "java.lang.Integer") return true;
	if (dataType == "java.lang.Long") return true;
}

function isDouble(dataType) {
	if (dataType == "java.lang.Double") return true;
	if (dataType == "java.lang.Float") return true;
}

function formatFieldValue(value, unit, dataType) {
	if (unit.toLowerCase().indexOf("percent") >= 0 || unit.toLowerCase().indexOf("%") >= 0) {
		//return (value * 100).toFixed(0) + "%";
		return (value * 1).toFixed(0) + "%";
	}
    else if (dataType!=null && isInteger(dataType)) {
        return (value *1).toFixed(0);
    }
	return value;
}

function parseFieldValue(value, unit) {
	if (unit.toLowerCase().indexOf("percent") >= 0 || unit.toLowerCase().indexOf("%") >= 0) {
		//return parseFloat(value.replace("%")) / 100;
		return value.replace("%", '');

    }
	return value;
}

var runSimulationOnclick = false;
function oneOfValuesChangedEvent() {
	// uncomment code bellow to enable running simulation on every change
	//jQuery(".runSimulationButton").click();
	
	if (runSimulationOnclick) {
		jQuery(".runSimulationButton").click(runSimulationOnclick);
	}
	jQuery(".runSimulationButton").effect("highlight", {}, 2000);
	jQuery(".simulationInputsStatus").addClass("valueChanged");
	jQuery(".runSimulationButton").addClass('prominantButton');
	jQuery(".runSimulationButton").removeClass('prominantButton-dis');
}

function disableRunButton() {
	if (! runSimulationOnclick) {
		runSimulationOnclick = jQuery(".runSimulationButton").attr('onclick');
	}
	jQuery(".runSimulationButton").unbind("click");
	jQuery(".runSimulationButton").attr('onclick', "return false;");
	jQuery(".runSimulationButton").addClass('prominantButton-dis');
	jQuery(".runSimulationButton").removeClass('prominantButton');
}


function showInputsErrorMsg(msg) {
	jQuery(".inputsErrors span").text(msg);
	jQuery(".inputsErrors").show();
	setTimeout(function() {jQuery(".inputsErrors").hide()}, 5000);
}
/**
 * returns true if value is valid, false otherwise, 
 * @param newVal
 * @param unit
 * @param min
 * @param max
 * @param id
 * @return
 */
function processUserInput(newVal, unit, min, max, id) {
	var oldVal = inputValues[id];
	var newValParsed = jQuery.trim(parseFieldValue(newVal, unit));
	if (! /^\s*(\+|-)?((\d+(\.\d+)?)|(\.\d+))\s*$/.test(newValParsed)) {
		showInputsErrorMsg("Invalid value entered, provide value between " + min + " and " + max);
		return false;
	}
	
	var newValFloat = parseFloat(newValParsed);
	if (newValFloat < min || newValFloat > max) {
		showInputsErrorMsg("Invalid value entered, provide value between " + min + " and " + max);
		return false;
	}
	return true;
}

function rerenderInputs() {
	jQuery(".sliderDef").removeClass("processed");
}

function showSliders() {
   
	var msg = "";
	
	try {
	if (jQuery(".sliderDef").length == 0 || jQuery(".sliderDef").hasClass("processed")) {
		return;
	}

	var SLIDER_MIN = 0;
	var SLIDER_MAX = 1000;


	jQuery(".sliderDef").each(function() {
		var min = parseFloat(jQuery(this).find(".min").text());
		var id = jQuery(this).find(".id").text();
		var max = parseFloat(jQuery(this).find(".max").text());
		var defaultVal = parseFloat(jQuery(this).find(".default").text());
		var dataType = jQuery(this).find(".dataType").text();
		var currentValue = jQuery(this).find(".fieldValue").text();
		var type = jQuery(this).find(".type").text();
		var unit = jQuery(this).find(".unit").text();
		var interval = parseFloat(jQuery(this).find(".interval").text());
		
		if (isNaN(interval)) {
			interval = 1;
		}
		else {
			if ((min % interval) != 0) {
				min = min - (min % interval);
			}
		}

		if (! isNaN(parseFloat(currentValue))) {
			defaultVal = currentValue;
		}

		var sliderMax = max;
		var sliderMin = min;
		

		if (interval == 1 && isDouble(dataType)) {
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
		valueField.val(formatFieldValue(defaultVal, unit,dataType));

        var valueBinding = jQuery(this).find('.valueBinding');

		inputValues[id] = valueField.val();
		if (type != "SLIDER") {
            valueField.change(function(e,ui) {
            	if (!processUserInput(valueField.val(), unit, min, max, id)) {
            		valueField.val(inputValues[id]);
            	}
            	else {
            		valueBinding.val(parseFieldValue(valueField.val(), unit));
            		inputValues[id] = parseFieldValue(valueField.val(), unit);
            		oneOfValuesChangedEvent();
            	}
            });
			return;
		}
		var slider = jQuery(this).find(".slider");
		var sliderStep = interval != 1 ? interval : (max-min)/(sliderMax - sliderMin);

		slider.slider('destroy');
		

		slider.addClass('sliderInit');
		slider.slider({ 
			min: sliderMin,
			max: sliderMax, 
			step: interval,
			slide: function(event, ui) {
				
				if (isInteger(dataType)) {
					valueField.val(formatFieldValue(ui.value, unit,null));
	        		inputValues[id] = formatFieldValue( (ui.value).toFixed(2), unit,null);
				}
				else if (isDouble(dataType)) {
					valueField.val(formatFieldValue( (ui.value).toFixed(2), unit,null));
	        		inputValues[id] = formatFieldValue((ui.value).toFixed(2), unit,null);
				}

            	valueBinding.val(valueField.val());
			},
			stop: function(event, ui) {
				if (jQuery(this).hasClass('sliderInit')) {
					jQuery(this).removeClass('sliderInit');
				}
				else {
					oneOfValuesChangedEvent();
				}
			}
		});


		var sliderVal = defaultVal;
		if (isDouble(dataType)) {
			sliderVal = parseInt(((defaultVal-min) / (max-min)) * (sliderMax - sliderMin));
		}
		slider.slider("option", "value", sliderVal);

		valueField.change(function(eventObject) {
			
			if (!processUserInput(valueField.val(), unit, min, max, id)) {
				// value is invalid
        		valueField.val(inputValues[id]);
        		return;
        	}
        	else {
        		// value is ok
        		valueBinding.val(parseFieldValue(valueField.val(), unit));
        		inputValues[id] = parseFieldValue(valueField.val(), unit);
        	}

			var sliderVal = parseFieldValue(valueField.val(), unit);
			valueField.val(formatFieldValue(sliderVal, unit,dataType));

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
			values[id] = parseFieldValue(val, unit);
			} catch (e) { //log.error(e)
			}
		});
		
		
		//icefacesEventManager.sendEventToTheBackend("modelRun", values);
	});
	}
	catch (e) {//log.error(e);
		if (debug) {		alert("error: e" + e);	}
	}

	jQuery(".sliderDef").eq(0).addClass("processed");

}

function convertTypedValuesToNumbers() {
	jQuery(".sliderDef").each(function() {
		try {
			var val = jQuery(this).find('.value').val();
			var valueBinding = jQuery(this).find('.valueBinding');
			var unit = jQuery(this).find('.unit').text();
		
			valueBinding.val(parseFieldValue(val, unit));
		} catch (e) { 
			if (debug) {
				alert("Error one"+e);
			}
		}
	});
}

function runSimulation() {
	showLoadingScreen();
	// get data for all model inputs

	jQuery(".sliderDef").each(function() {
		try {
		var val = jQuery(this).find('.value').val();
		var valueBinding = jQuery(this).find('.valueBinding');
		var unit = jQuery(this).find('.unit').text();
		//alert("val: " + val + "\nunit: " + unit + "\nafter parse: " + parseFieldValue(val, unit));
		valueBinding.val(parseFieldValue(val, unit));;
		} catch (e) { 
			if (debug) {
				alert("Error two"+e);
			}
		}
	});
	
}

function getOutputValue(val, unit) {
	if (unit.indexOf("%") < 0) {
		return val;
	}
	else {
		return 100*val;
	}
}

function renderSingleChart(chartDef) {
	try {
		var chartType = jQuery(chartDef).find(".chartType").val();
		if (typeof(chartType) == 'undefined' || chartType != "TIME_SERIES") {

			return;
		}
		var def = jQuery(chartDef);
		var errorMessagesPlaceholder = def.find(".chartMessagePlaceholder");
		var chartPlaceholderId = def.find(".chartPlaceholder").attr("id");
		var chartTitle = def.find(".chartTitle").val();
		var indexMin = parseInt(def.find(".indexMin").val());
		var indexMax = parseInt(def.find(".indexMax").val());
		var dataType = jQuery(chartDef).find(".dataType").val();
		

		var globalOutOfRangeMessage = jQuery(chartDef).find(".indexedOutOfRangeMessage").val();
		var globalOutOfRangePolicy = jQuery(chartDef).find(".indexedOutOfRangePolicy").val();
		var seriesWithOutOfRangeError = [];
		jQuery(chartDef).find(".serieWithOutOfRnage").each(function() {
			seriesWithOutOfRangeError.push(jQuery(chartDef).val());
		   // log.debug("Pushing series range error"+jQuery(this).val());
        });
		
		var globalInvalidMessage = jQuery(chartDef).find(".indexedInvalidMessage").val();
		var globalInvalidPolicy = jQuery(chartDef).find(".indexedInvalidPolicy").val();
		var seriesWithInvalidError = [];
		jQuery(chartDef).find(".serieWithInvalid").each(function() {
			seriesWithInvalidError.push(jQuery(this).val());
            // log.debug("Pushing series invalid error"+jQuery(this).val());
		});
		

		var errorMessages = [];
		if (jQuery.trim(globalInvalidMessage) != "" && seriesWithInvalidError.length > 0) {
			var msg = globalInvalidMessage.replace("%outputs", seriesWithInvalidError.join(", "));
			errorMessages.push(msg);
		}
		if (globalOutOfRangeMessage != "" && seriesWithOutOfRangeError.length > 0) {
			var msg = globalOutOfRangeMessage.replace("%outputs", seriesWithOutOfRangeError.join(", "));
			errorMessages.push(msg);
		}

        //log.debug("Error messages are now "+errorMessages);
		
		var xaxisTicks; 
		if (isNaN(indexMin) || isNaN(indexMax)) {
			indexMin = null;
			indexMax = null;
		}
		else {
			xaxisTicks = [];
			for (var i=indexMin; i<=indexMax; i+=10) {
				xaxisTicks.push(i);
			}
		}
		
		var values = [];
		var valuesById = {};
		var labelsById = {};
		var confIntervalById = {};
		var min = null;
		var max = null;
		
		
		var yaxis = {};
		var xaxis = {label:'Year', autoscale: false, tickOptions:{formatString:'%d'}, ticks: xaxisTicks};
		
		
		var series = [];
		def.find(".serieDef").each(function() {
			var val = eval("(" + jQuery(this).find(".value").val() + ")" );
			var label = jQuery(this).find(".label").val();
			var unit = jQuery(this).find(".unit").val();
			var id = jQuery(this).find(".id").val();
			//var error = jQuery(this).find(".error").val();

            var invalidErrorMessage = jQuery(this).find(".invalidErrorMessage").val();
			var invalidErrorPolicy = jQuery(this).find(".invalidErrorPolicy").val();

            var rangeErrorMessage = jQuery(this).find(".rangeErrorMessage").val();
			var rangeErrorPolicy = jQuery(this).find(".rangeErrorPolicy").val();


            var labelFormatString = jQuery(this).find(".labelFormatString").val();
			if (rangeErrorPolicy != 'NO_DISPLAY_WITH_MSG' && invalidErrorPolicy!= 'NO_DISPLAY_WITH_MSG') {
				min = jQuery(this).find(".min").val();
				max = jQuery(this).find(".max").val();
				
				if (dataType == 'java.lang.Integer') {
					min = parseInt(min);
					max = parseInt(max);
				}
				else {
					min = parseFloat(min);
					max = parseFloat(max);
				}
				
				if (isNaN(min) || isNaN(max)) {
					min = null;
					max = null;
				}
				var associatedId = jQuery(this).find(".associatedId").val();
				var seriesType = jQuery(this).find(".seriesType").val();

				var chartVals = [];
				
				for (var i = 0; i < val.length; i++) {
					if (isNaN(parseFloat(val[i][0])) || isNaN(parseFloat(val[i][1]))) {
						continue;
					}
					//val[i] = [parseFloat(val[i][0]), getOutputValue(parseFloat(val[i][1]), unit)];
					chartVals.push([parseFloat(val[i][0]), parseFloat(val[i][1])]);
					val[i] = [parseFloat(val[i][0]), parseFloat(val[i][1])];
				}
				valuesById[id] = chartVals;
				labelsById[id] = label;
			
				if (seriesType != 'NORMAL' && parseInt(associatedId) > 0) {
					if (typeof(confIntervalById[associatedId]) == 'undefined') {
						confIntervalById[associatedId] = [];
					}
					confIntervalById[associatedId].push(id);
				} else {
					values.push(chartVals);
					// prepare label
					if (!(!labelFormatString || jQuery.trim(labelFormatString) == "")) {
						label = labelFormatString.replace(/%label/g, label).replace(/%unit/g, unit);
					}
					series.push({showMarker: false, label: label});
				}
			}
			if (rangeErrorPolicy) {
                //log.debug("Range error policy is not null");
                if (rangeErrorPolicy != 'NORMAL' && rangeErrorPolicy != 'DISPLAY_AVAILBLE_NO_MSG' && jQuery.trim(rangeErrorMessage) != "") {

				    errorMessages.push(rangeErrorMessage);
			    }
            }

            if (invalidErrorPolicy) {
               // log.debug("invalid error policy is not null");
                if (invalidErrorPolicy != 'NORMAL' && invalidErrorPolicy != 'DISPLAY_AVAILBLE_NO_MSG' && jQuery.trim(rangeErrorMessage) != "") {

				    errorMessages.push(invalidErrorMessage);
			    }
            }
		});
		
		// 	set min/max
		
		function pickTickInterval(min, max,preferredTicks) {
			// probably here...
		     var interval = (max - min)/preferredTicks;
		    
		    var divideback = .1;
		     while (interval != Math.floor(interval)) {
		    	 //log.debug("loop: " + interval + "\tdivideback: " + divideback);
		          interval*=10;
		          divideback*=10;
		     }
		     interval=(Math.round((interval / 10)*2)/2)/divideback ;
		     return interval;
		}
	
		if (min != null && max != null) {
			yaxis.min = min;
			yaxis.max = max;

			if (max - min > 100) {
				yaxis.tickInterval = 100;
				yaxis.tickOptions = {formatString:"%d"};
			}
			else if (max - min > 5 && max - min < 20) {
				//yaxis.tickInterval = pickTickInterval(min, max, parseInt(max-min));//((label == "Atmospheric CO2 Concentration") ? 100 : 30);// pickTickInterval(min,max,5)
				yaxis.tickInterval = 1;
				yaxis.tickOptions = {formatString:"%d"};
			}
			else if (max - min > 20 && max-min < 30) {
				yaxis.tickInterval = 2;
				yaxis.tickOptions = {formatString:"%.1f"};
			}
				
			//yaxis.tickInterval = 1;
			
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

			if (typeof(valMain) == 'undefined') {
				continue;
			}
			for (var i = 0; i < valMain.length; i++) {
				//intervalVal[i] = [valMain[i][0], valConf2[1], valMain[i][1], valConf1[1]];
				intervalVal[i] = [valMain[i][0], valConf1[i][1], valConf2[i][1], valMain[i][1]];
			}
		
			values.push(intervalVal);
			series.push({showMarker: false, showLabel: false, 
				renderer: jQuery.jqplot.OHLCRenderer, color: "rgb(125, 228, 247)"});
		}

		if (values.length > 0) {
            //log.debug(chartTitle+":"+series+":"+values);
			var plot = jQuery.jqplot(chartPlaceholderId, values, 
				{ 
				series: series,
				axes:{
					xaxis: xaxis,
					yaxis: yaxis
				},
				legend : {
					show :true,
					location :'nw',
					yoffset :300,
					xoffset:0
				}
			}); 
			
		}
		
		for (var i=0; i < errorMessages.length; i++) {
			errorMessagesPlaceholder.append("<li>" + errorMessages[i] + "</li>");
		}
		if (errorMessages.length > 0) {
			//log.debug("Showing error messages: " + errorMessages);
			errorMessagesPlaceholder.show();
		}
	
	/* Legend is positioned absolutely thus it is hard to determine how long the chart will be, I'm assuming
	 * that graph will take around 320 px and I'm giving 18 px for each item in the legend.
	 */

		var height = jQuery("#" + chartPlaceholderId).height();
		if (jQuery("#" + chartPlaceholderId + " .jqplot-table-legend").length > 0) {
			height += jQuery("#" + chartPlaceholderId + " .jqplot-table-legend").height();
		}
		height +=  errorMessagesPlaceholder.height();
		
		jQuery("#" + chartPlaceholderId).parent().height(height + 30);

	} catch (e) {
		if (debug) {
			var msg = "";
			
			for (var key in e) {
				msg += key + ": " + e[key] + "\n";
			}
				
			alert("Error 3"+e);
		}
        //log.error(e);
	}
	
}
		
var counter = 0;
function renderModelOutputs() {
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
	jQuery(".outputDef").each(function() {
		//renderSingleChart(this);
	});
	
	
	/* this is rather ugly hack, used because there is no good handling of output grouping */
	//jQuery(".impactsContent").html("");
	if (jQuery(".impactsContentCharts .physicalImpact").length > 0) {
		var physicalImpacts = [];
		var tabs = "<div class='tabsContainer'><ul>";
		var contents = "";


		jQuery(".impactsContentCharts .ui-accordion").append("<div class='outputDef physicalImpactsTab' ><h3><a href='javascript:;' class='activator'>Physical impacts</a>" +  
				'<a class="folder" href="javascript:;" tabindex="-1"><span class="hidden">Folder/unFolder</span></a></h3><div>' + 
				'<ul class="tabsContainer"></ul><div class="contentsContainer"></div><div class="clear"></div></div></div>');
		
		var tabsContainer = jQuery(".physicalImpactsTab .tabsContainer");
		var contentsContainer = jQuery(".physicalImpactsTab .contentsContainer");
		
		jQuery(".impactsContentCharts .physicalImpact").each(function() {
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

		jQuery(".impactsContentCharts .physicalImpact").remove();
		

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
	jQuery(".impactsContent").css("height", jQuery(".impactsContentCharts").height());
	jQuery(".outputDef").eq(0).addClass("processed");
	jQuery(".outputDef").show();
	
	unlockImpactsScreen();
	
}

var currentTab = "CO2 Concentration";

function showTabContents(tabHeader) {
	if (! jQuery(tabHeader).hasClass("ui-state-processed")) {
		renderSingleChart(jQuery(tabHeader).next());
		jQuery(tabHeader).addClass("ui-state-processed");
	}
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
		
		currentTab = jQuery(this).find(".activator").text();

		jQuery(this).addClass("ui-state-active");
		jQuery(this).removeClass("ui-state-default");
		jQuery(this).next().slideDown(200);
		jQuery(this).attr("tabindex", "0");

		showTabContents(this);

		setTimeout( function() { jQuery(".impactsContent").css("height", jQuery(".impactsContentCharts").height()) }, 250);
		return false;
	});

	var selectedHeader = container.find("h3").get(0);
	if (currentTab) {
		container.find("h3").each(function() {
			if (currentTab == jQuery(this).find(".activator").text()) {
				selectedHeader = this;
			}
		});
	}
		
	container.find("h3").each(function() {
		jQuery(this).addClass("ui-accordion-header").addClass("ui-state-default");
		if (selectedHeader != this) {
			jQuery(this).next().hide();
		}
		else {
			jQuery(this).addClass("ui-state-active");
			jQuery(this).next().show();
			showTabContents(this);

		}
	});

	//container.find("h3").eq(0).click();

}

function showEditForm() {
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
    jQuery(".inputOrderDef").html('<ul id="inputsOrder" class="inputsOrder">' + inputsForOrdering + "</ul>");
    
    jQuery("#inputsOrder, .subInputsOrder").sortable({stop: function() {
    
    	var counter = 0; 
    	jQuery(".singleInputOrderDef input").each(function() {
    		this.value = counter++;
    	});
    }});
	}
	
	/* Inputs grouping */
	if (! jQuery(".availableGroupItemsa").length) {
		
		jQuery(".availableItemsCell .listItems").each(function() {
			//var container = jQuery(this);
			//ul.html("");
			//container.html("<ul class='availableGroupItems ui-widget-content ui-corner-all'>" + container.html() + "</ul>");
			
			//log.debug("creating available items ");
		});
		
		jQuery(".inputGroup .listItems").each(function() {
			//var container = jQuery(this);
			//var parentId = container.attr("class").replace(/[^\d]*/, "");
			//container.append("<ul class='groupedInputs ui-widget-header' title='" + parentId + "'>" + container.html() + "</ul>");

			//log.debug("creating grouped items ");
			
		});
		
		//, .inputGroup
		jQuery(".individualInput").each(function(){
			var parentId = 0;
			var input = jQuery(this);
			if (input.parent().hasClass("groupedInputs")) {
				parentId = input.parent().attr("title");
			}
			input.find("input").val(parentId);
		});
		
		jQuery(".individualInput").draggable({helper: "original"});
		jQuery(".groupedInputs, .availableGroupItems").droppable({
			activeClass: 'ui-state-highlight',
			drop: function(event, ui) {
				jQuery(this).find(".individualInputPlaceholder").remove();
				ui.draggable.css("top", null);
				ui.draggable.css("left", null);
				ui.draggable.appendTo(this);

				// input has been added to concrete group, set its input to group id
				var parentId = -1;
				try {
				if (ui.draggable.parent().hasClass("groupedInputs")) {
					parentId = ui.draggable.parent().attr("title");
				}
				ui.draggable.find("input").val(parentId);
				}
				catch (e) {
					//log.error(e);
				}
				jQuery(this).append("<li class='individualInputPlaceholder'></li>");
				//ui.draggable.draggable();
				
		}});

		
	}
	
	if (true) {

	    //var inputsForOrdering = jQuery(".inputOrderDef").html();
	    //jQuery(".inputOrderDef").html('<ul id="inputsOrder" class="inputsOrder">' + inputsForOrdering + "</ul>");
	    function updateItemsOrder() {
	    	var counter = 0; 
	    	jQuery(".orderList li input").each(function() {
	    		this.value = counter++;
	    	});
	    }
	    	
	    jQuery(".orderList, .orderSubList").sortable({stop: function() {
	    	updateItemsOrder();
	    }});
	    
	    updateItemsOrder();
	}
    
}


