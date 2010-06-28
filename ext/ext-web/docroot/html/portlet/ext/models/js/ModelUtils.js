/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

/**
 * Framework responsible for interaction with services serving models
 * (also called simulations) and scenarios. It encapsulates logic
 * required to fetch, parse and display requested entity data.
 *
 *
 * @author janusz.p, joshi
 * @version 1.1
 */

/**
 * Utility class that is responsible for interaction with services and display
 * of fetched data.
 */
var ModelUtils = new function() {
	/**
	 * Fetch list of modules URL.
	 */
	var getModelURL = "/simulation-servlet/rest/simulation/";

	/**
	 * Fetch scenario URL.
	 */
	var getScenarioURL = "/simulation-servlet/rest/scenario/";

	/**
	 * Fetch model URL.
	 */
	var runModelURL = "/simulation-servlet/rest/runsim";

	/**
	 * Save scenario URL.
	 */
	var saveScenarioURL = "/simulation-servlet/rest/scenariostate/";

	/**
	 * Fetch model URL.
	 */
	var getModelsURL = "/simulation-servlet/rest/simulation/";
	
	
	var legendInfoURL = "<a href='/web/guest/resources/-/wiki/Main/Plan+index+definitions'><img src='/collaboratorium-theme/images/qustion_icon.png' alt='question' /></a>";
	
	var warningText = "<div id='model-warning-message'>" +
            " <a href='/web/guest/resources/-/wiki/Main/Mitigation+Cost+Model+Ranges'><img src='/collaboratorium-theme/images/qustion_icon.png' alt='question' /></a> </div>"
	
	var warningFlag = false;

	/**
	 * Fetched model.
	 */
	var model = null;

	/**
	 * Fetched scenario.
	 */
	var scenario = null;

	/**
	 * Max value for a slider.
	 */
	var sliderMax = 1000;

	/**
	 * Min value for a slider.
	 */
	var sliderMin = 0;
	
	/**
	 * degree string
	 */
	var degreeStr = '&#176;C';

	/**
	 * Slider range.
	 */
	var sliderRange = sliderMax - sliderMin;

	/**
	 * Array of OutputGroups that will be used to display fetched values.
	 */
	var outputGroups = [ new ScalarOutputsGroup(),
			new DamageCostOutputsGroup(), new FreeOutputsGroup(),
			new IndexedOutputsGroup() ];

	/**
	 * Services timeout.
	 */
	var timeout = 60000;

	/**
	 * Fetched models.
	 */
	var models = new Object();

	/**
	 * Collection of callbacks that should be called after fetching a model.
	 */
	var modelReadyCallbacks = new Array();

	/**
	 * Collection of callbacks that should be called after fetching a scenario.
	 */
	var scenarioReadyCallbacks = new Array();

	/**
	 * Id of an element in which impacts should be rendered.
	 */
	var impactsPlaceholder = null;
	
	
	
	
	var shortActionsDisplay = ["Pct change in Developed FF emissions","Pct change in Developing A FF emissions",
	     "Pct change in Developing B FF emissions","Global land use emissions change","Target Sequestration"];
	
	var shortOutputDisplay = {
	       "CO2 Concentration in 2100" : [
               "Atmospheric CO2 concentration in parts per million (ppm)",
               function(scenario) {
	              var variable = scenario.variableForName("AtmosphericCO2Concentration");
	              val = variable.values[variable.values.length-1][1];
	              if (isNaN(parseFloat(variable.values[variable.values.length-1][1]))) {
	            	  val = "N/A";
	              }
	              else {
	            	  val = val.toFixed(1) + " ppm";
	              }
	              return val;
	       }],
	       "Change in GMT for 2100" : [
                "Global average temperature change in degrees Celsius (C) from pre-industrial values"
                ,function(scenario) {
	            var variable = scenario.variableForName("GlobalTempChange");
	            if (isNaN(parseFloat(variable.values[variable.values.length-1][1]))) {
	            	  val = "N/A";
	              }
	              else {
	            	  val = val.toFixed(1) + " " + degreeStr;
	              }
	              return val;
	       }],
	       "Sea Level Rise": [
               "Sea level change in millimeters (mm) from 2000",
               function(scenario) {
	            var variable = scenario.variableForName("Sea_Level_Rise_output");
	            if (isNaN(parseFloat(variable.values[variable.values.length-1][1]))) {
	            	  val = "N/A";
	              }
	              else {
	            	  val = val.toFixed(1) + " mm";
	              }
	              return val;
	       }],
	       "Range of mitigation costs": [
               " Cost of efforts to prevent climate change (e.g., by reducing emissions). Costs are shown as a % of World GDP (Gross Domestic Product).",
               function(scenario) {
	            var mitigation = [scenario.variableForName("_Change_in_GDP_vs__baseline_igsm_output")];
	            mitigation.push(scenario.variableForName("_Change_in_GDP_vs__baseline_merge_output"));
	            mitigation.push(scenario.variableForName("_Change_in_GDP_vs__baseline_minicam_output"));
	            var min = NaN;
	            var max = NaN;
	        	jQuery.each(mitigation,function(i,data) {
	        	     var val = data.values[data.values.length-1][1];
	        	     if (isNaN(min) || val < min) min = val;
	        	     if (isNaN(max) || val > max) max = val;
	        	});
	        	min *= -1;
	        	max = Math.min(0,max)*-1;

	        	var errors = jQuery.trim(jQuery(".mitigation-errors").text());
	        	var val = "From "+max.toFixed(2)+"% to "+min.toFixed(2)+"% decrease in projected baseline GDP";
	        	if (errors.length > 0) {
	        		val += ' <img class="output-errors-trigger" src="/html/icons/quick_note.png" /><div class="errors popup-info-box" style="display: none; width: 150px; position: absolute">' + errors + '</div>';
	        	}
	        		
	        	return val;
	       }],
	       "Range of damage costs": [
               "Cost of damages caused by climate change (e.g., damages from rising sea level, hurricanes, droughts, etc.). Costs are shown as a % of World GDP (Gross Domestic Product).",
               function(scenario) {
	            var maxdamage = scenario.variableForName("PercentChange5_output");
	            var mindamage = scenario.variableForName("PercentChange95_output");

	            var min = mindamage.values[mindamage.values.length-1][1];
	            var max = maxdamage.values[maxdamage.values.length-1][1];
	            min *= -1;
	        	max *= -1;
	            return "From "+max.toFixed(2)+"% to "+min.toFixed(2)+" % decrease in projected baseline GDP";
	       }]
	  };
	 
	
	var displayOrder = ["DevelopedFossilFuelEmissions","DevelopingAFossilFuelEmissions","DevelopingBFossilFuelEmissions","AtmosphericCO2Concentration","GlobalTempChange",
	          "Sea_Level_Rise_output","_Change_in_GDP_vs__baseline_igsm_output","_Change_in_GDP_vs__baseline_merge_output","_Change_in_GDP_vs__baseline_minicam_output",
	          "Mean_Percent_Change_GDP_vs__Basline_output","Percent_Change_GDP_vs__Basline_output","PercentChange95_output","PercentChange5_output"];
	          
	
	/**
	 * Input configuration for simulation (placeholder)
	 *
	 */
	var inputsViewConfig = {"623": "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><root><group id='D_Pct change in Developed FF emissions'>"
			+ "<control id='D_Pct change in Developed FF emissions' type='slider-text'/>"
			+ "<control id='D_Developed start year' type='text'/>"
			+ "<control id='D_Developed target year' type='text'/>"
			+ "</group>"
			+ "<group id='D_Pct change in Developing A FF emissions'>"
			+ "<control id='D_Pct change in Developing A FF emissions' type='slider-text'/>"
			+ "<control id='D_Developing A start year' type='text'/>"
			+ "<control id='D_Developing A target year' type='text'/>"
			+ "</group>"
			+ "<group id='D_Pct change in Developing B FF emissions'>"
			+ "<control id='D_Pct change in Developing B FF emissions' type='slider-text'/>"
			+ "<control id='D_Developing B start year' type='text'/>"
			+ "<control id='D_Developing B target year' type='text'/>"
			+ "</group>"
			+ "<group id='D_Global land use emissions change'>"
			+ "<control id='D_Global land use emissions change' type='slider-text'/>"
			+ "</group>"
			+ "<group id='D_Target Sequestration'>"
			+ "<control id='D_Target Sequestration' type='slider-text'/>"
			+ "</group></root>",
			"240": "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><root><group id='D_Pct change in Developed FF emissions'>"
			+ "<control id='D_Pct change in Developed FF emissions' type='slider-text'/>"
			+ "<control id='D_Developed start year' type='text'/>"
			+ "<control id='D_Developed target year' type='text'/>"
			+ "</group>"
			+ "<group id='D_Pct change in Developing A FF emissions'>"
			+ "<control id='D_Pct change in Developing A FF emissions' type='slider-text'/>"
			+ "<control id='D_Developing A start year' type='text'/>"
			+ "<control id='D_Developing A target year' type='text'/>"
			+ "</group>"
			+ "<group id='D_Pct change in Developing B FF emissions'>"
			+ "<control id='D_Pct change in Developing B FF emissions' type='slider-text'/>"
			+ "<control id='D_Developing B start year' type='text'/>"
			+ "<control id='D_Developing B target year' type='text'/>"
			+ "</group>"
			+ "<group id='D_Global land use emissions change'>"
			+ "<control id='D_Global land use emissions change' type='slider-text'/>"
			+ "</group>"
			+ "<group id='D_Target Sequestration'>"
			+ "<control id='D_Target Sequestration' type='slider-text'/>"
			+ "</group></root>"}
			

	/**
	 * Displays message to the user.
	 *
	 * @param message
	 *            message text
	 * @param type
	 *            type of a message ('error', 'success'), if ommited considered
	 *            to be 'success'
	 */
	function showMessage(message, type) {
		if (type == 'error') {
			$.growlUI("Error!", message);
			$("div.growlUI").addClass("error");
		} else {
			$.growlUI("Success!", message);
			$("div.growlUI").removeClass("error");
		}
	}

	/**
	 * Calls all callbacks from callbacks collection. Each callback receives
	 * type, model and scenario.
	 *
	 * @param callbacks
	 *            collection of callbacks to be called
	 * @param type
	 *            type of callback ('error', 'success')
	 */
	function callListeners(callbacks, type) {
		for ( var i = 0; i < callbacks.length; i++) {
			callbacks[i](type, model, scenario);
		}
	}

	/**
	 * Registers new callback to be called when model is ready.
	 *
	 * @param callback
	 *            a callback
	 */
	this.modelReady = function(callback) {
		modelReadyCallbacks.push(callback);
	}

	/**
	 * Registers new callback to be caled when scenario is ready
	 *
	 * @param callback
	 *            a callback
	 */
	this.scenarioReady = function(callback) {
		scenarioReadyCallbacks.push(callback);
	}

	/**
	 * Retrieves all available models.
	 *
	 * @param callback
	 *            callback function that should be called after fetching models
	 *            (can be ommited)
	 */
	this.fetchAllModels = function(callback) {
		$.ajax( {
			type :"GET",
			url :getModelsURL,
			dataType :"xml",
			success : function(dataXML, textStatus) {
				var modelDefs = $(dataXML).find("simulations > simulation");
				for ( var i = 0; i < modelDefs.length; i++) {
					var modelDef = modelDefs.eq(i);
					var model = new Model(modelDef);
					models[model.id] = model;
				}

				if (callback instanceof Function) {
					callback(models);
				}
			},
			error : function() {
				showMessage("Unable to load all models.", 'error');
			}
		});
	}

	/**
	 * Retrieves model with given ID from simulation service.
	 *
	 * @param modelId
	 *            id of a model to be retrieved
	 * @param callback
	 *            callback function that should be called after fetching model
	 *            (can be ommited)
	 *
	 */
	this.fetchModel = function(modelId, callback) {
		if (!(modelId > 0)) {
			throw "Invalid model id" + modelId;
		}

		$.ajax( {
			url :getModelURL + modelId,
			type :'GET',
			dataType :'xml',
			error : function() {
				callListeners(modelReadyCallbacks, "error");
				showMessage('Error loading model for id ' + modelId, 'error');
			},
			success : function(xmlData) {
				model = new Model($(xmlData).find("simulations > simulation")
						.eq(0));
				if (callback instanceof Function) {
					callback(model);
				}
				callListeners(modelReadyCallbacks, "success");
			}
		});
	}

	/**
	 * Getter for model.
	 *
	 * @return model
	 */
	this.getModel = function() {
		return model;
	}

	/**
	 * Getter for scenario.
	 *
	 * @return scenario
	 */
	this.getScenario = function() {
		return scenario;
	}
	


	/**
	 * Loads a scenario from given XML DOM tree.
	 *
	 * @param xmlData
	 *            XML with scenario definition
	 * @param callback
	 *            callback function that should be called after fetching
	 *            scenario (can be ommited)
	 */
	function loadScenario(xmlData, callback) {
		scenario = new Scenario($(xmlData).find("scenarios > scenario").eq(0),
				model);
		for ( var groupId = 0; groupId < outputGroups.length; groupId++) {
			outputGroups[groupId].clear();
		}

		for ( var outputId in scenario.outputs) {
			// group output variables
			for ( var groupId = 0; groupId < outputGroups.length; groupId++) {
				if (outputGroups[groupId]
						.addVariable(scenario.outputs[outputId])) {
					break;
				}
			}
		}

		if (callback instanceof Function) {
			callback(scenario, model);
		}
		callListeners(scenarioReadyCallbacks, "success");
	}

	/**
	 * Retrieves model with given ID from simulation service.
	 *
	 * @param scenarioId
	 *            id of scenario to be retrieved
	 * @param callback
	 *            callback function that should be called after fetching
	 *            scenario (can be ommited)
	 */
	this.fetchScenario = function(scenarioId, callback) {
		if (!(scenarioId > 0)) {
			throw "Invalid scenario id" + scenarioId;
		}

		$.ajax( {
			url :getScenarioURL + scenarioId,
			type :'GET',
			dataType :'xml',
			error : function() {
				showMessage('Error loading scenario for id ' + scenarioId,
						"error");
				callListeners(scenarioReadyCallbacks, "error");
			},
			success : function(xmlData) {
				var modelId = $(xmlData).find("scenario > simulation > id")
						.text();

				if (!model || modelId != model.id) {
					ModelUtils.fetchModel(modelId, function(model) {
						loadScenario(xmlData, callback);
					});
				} else {
					loadScenario(xmlData, callback);
				}
			}
		});
	}

	/**
	 * Shows loading prompt in element with id referenced by impactsPlaceholder
	 * variable.
	 */
	this.showLoadingScreen = function() {
		if (impactsPlaceholder) {
			var parentId = "#" + impactsPlaceholder;
			$(parentId).css( {
				'display' :'block'
			});
			$(parentId).unblock();
			$(parentId)
					.block(
							{
								message :'<img src="/html/portlet/ext/models/images/ajax-loader.gif"/>'
							});
		}
	}

	/**
	 * Renders scenario Impacts in element with given id.
	 *
	 * @param parentId
	 *            id of an element in which Impacts should be displayed
	 */
	this.showScenario = function(parentId) {

		$("#" + parentId).accordion('destroy');
		$("#" + parentId).empty();

		// sort output groups by weights
		var sortedGroups = outputGroups.slice();

		sortedGroups.sort( function(a, b) {
			return a.displayWeight - b.displayWeight;
		});
		for ( var i = 0; i < outputGroups.length; i++) {
			sortedGroups[i].displayFull(parentId);
			
		}
		$('#' + parentId).accordion();

	}

	/**
	 * Shows short summary of scenario impacts.
	 *
	 * @param parentId
	 *            id of an element in which impacts summary should be displayed
	 */
	this.showImpactsShort = function(parentId) {
		$('#' + parentId)
				.prepend(
						"<table id='impacts-short'><thead><tr><th>Impact</th><th>Value</th></tr></thead></table>");
          //TODO

        var even = false;
        //if (model.id == "623") {
            var parentTableId = "#impacts-short";
			jQuery.each(shortOutputDisplay,function(i,val) {
				var id = i.replace(/\s/g,"_");
				$(parentTableId)
						.append(
								"<tr class='"
										+ (even ? "even" : "")
										+ "'><td class='first'>"
										+ i
                                        +"<a href='javascript:;' onClick='ModelUtils.toggleDescription(\""
						+ id
						+ "\")' class='toggle-description'>"
						+ "<img src='/collaboratorium-theme/images/qustion_icon.png' alt='question' /></a>"
						+ "<div id='input"
						+ id
						+ "description' style='display: none' class='inputDescription'>"
						+ val[0] + "</div>"
										+ "</td><td>"
										+ val[1](scenario) + "</td></tr>");
				even = !even;
			});
			
			jQuery(".output-errors-trigger").hover(function() {
				jQuery(this).parent().find(".popup-info-box").fadeIn("medium");
		        jQuery(this).parent().addClass("note-hover");
			}, function() {
		        jQuery(this).parent().removeClass("note-hover");
		        var parent = jQuery(this).parent();
	            setTimeout(function() {
	                if (! parent.hasClass("note-hover") && !parent.hasClass("popup-hover")) {
	                    parent.find(".popup-info-box").fadeOut("medium");
	                }
	                }, 200);
			});
			
			jQuery(".popup-info-box").hover(function() {
				jQuery(this).parent().find(".popup-info-box").fadeIn("medium");
		        jQuery(this).parent().addClass("popup-hover");
			}, function() {
		        jQuery(this).parent().removeClass("popup-hover");
		        var parent = jQuery(this).parent();
	            setTimeout(function() {
	                if (! parent.hasClass("note-hover") && !parent.hasClass("popup-hover")) {
	                    parent.find(".popup-info-box").fadeOut("medium");
	                }
	                }, 200);
			});
        
        
        /*
        } else {  
			var sortedGroups = outputGroups.slice();
	
			sortedGroups.sort( function(a, b) {
				return a.displayWeight - b.displayWeight;
			});
			var even = false;
			for ( var i = 0; i < outputGroups.length; i++) {
				var addedImpactsCount = sortedGroups[i].displayShort(
						'impacts-short', even);
				if (addedImpactsCount % 2 != 0) {
					even = !even;
				}
	
			}
		}*/
	}

	/**
	 * Shows short summary of scenario actions.
	 *
	 * @param parentId
	 *            id of an element in which actions summary should be displayed
	 */
	this.showActionsShort = function(parentId) {
		$('#' + parentId)
				.append(
						"<table id='actions-short'><thead><tr><th class='first'>Action</th><th>Value</th></tr></thead></table>");
		var container = $('#actions-short');
		var even = false;
		//if (model.id == "623") {
		         jQuery.each(shortActionsDisplay,function(i,data) {
		              var input = scenario.variableForName(data);
		              container.append("<tr class='" + (even ? "even" : "")
					+ "' ><td class='first'>" + input.metadata.name
                    +"<a href='javascript:;' onClick='ModelUtils.toggleDescription("
						+ input.id
						+ ")' class='toggle-description'>"
						+ "<img src='/collaboratorium-theme/images/qustion_icon.png' alt='question' /></a>"
						+ "<div id='input"
						+ input.id
						+ "description' style='display: none' class='inputDescription'>"
						+ input.metadata.description + "</div>"
					+ "</td><td>" + formatInputValue(input.metadata,input.values[0]) + "</td></tr>");
			          even = !even;
		              
		         });
		/*} else {
		for ( var inputId in scenario.inputs) {
			var input = scenario.inputs[inputId];
			container.append("<tr class='" + (even ? "even" : "")
					+ "' ><td class='first'>" + input.metadata.name
					+ "</td><td>" + formatInputValue(input,input.values[0]) + "</td></tr>");
			even = !even;
		}}
		*/

	}

	/**
	 * Helper function responsible for parsing values retrieved from XML fetched
	 * from simulation service.
	 *
	 * @param text
	 *            text representing values
	 * @return parsed value
	 */
	this.parseValues = function(text) {
		if (text) {
			text = text.replace(/\]\[/g, "],[");
			try {
				// try to evaluate values first
				return eval(text);
			} catch (err) {
				if (text.indexOf("],[") >= 0) {
					// we have an array of arrays
					tmp = text.split("],[");
					var values = new Array();
					for ( var i = 0; i < tmp.length; i++) {
						tmp[i] = tmp[i].toString().replace(/[\[\]]/g, "");
						var comaPos = tmp[i].indexOf(",");
						values[i] = new Object();
						values[i][0] = tmp[i].substring(0, comaPos);
						values[i][1] = tmp[i].substring(comaPos + 1,
								tmp[i].length);
					}
					return values;
				} else {
					return text.replace(/[\[\]]/g, "").split(",");
				}
			}
		}
		return null;
	}

	/**
	 * Helper value that converts "slider position" into represented value.
	 *
	 * @param value
	 *            current slider position
	 * @param min
	 *            minimum value
	 * @param max
	 *            maximum value
	 * @return calculated value
	 */
	function getSliderValue(value, min, max) {
		var result = (value / sliderMax * (max - min)) + min;

		if (result.toFixed) {
			result = result.toFixed(2);
		}
		return result;
	}

	/**
	 * Updates element with value represented by a slider.
	 *
	 * @param input
	 *            Input metadata
	 * @param min
	 *            minimum value
	 * @param max
	 *            maximum value
	 */
	function updateSliderLabel(input, min, max) {
		return function(event, ui) {
			$("#amount" + input.id).val(getSliderValue(ui.value, min, max));
			$("#amountlabel" + input.id).val(formatInputValue(input,getSliderValue(ui.value, min, max)));
					
		}
	}
	
	function formatInputValue(input,value) {
	     if (input.units && input.units[0].search(/percent/i) > -1) {
	          return (value*100).toFixed(0)+"%";
	     } else {
	          return Number(value).toFixed(2);     
	     }
	
	}
	function addSlider(input,sliderId,amountId,amountlabelId) {
		var value = 0;
		var sliderString = "#"+sliderId + input.id;
		var amountString = "#"+amountId + input.id;
		var amountLabelString = "#"+amountlabelId + input.id;


		var min = sliderMin;
		var max = sliderMax;
		var def = sliderMin;

		if (input.mins) {
			min = input.mins[0];
		}
		if (input.maxes) {
			max = input.maxes[0];
		}
		if (input.defaults) {
			def = input.defaults[0];
		}
		if (scenario) {
			def = scenario.inputs[input.id].values[0];
		}

		if (def < min || isNaN(def)) {
			def = min;
		} else if (def > max) {
			def = max;
		}
		value = ((def - min) / (max - min)) * sliderRange;

		$(sliderString).slider( {
			range :"min",
			value :value,
			min :sliderMin,
			max :sliderMax,
			slide :updateSliderLabel(input, min, max)
		});
		$(amountString).val(getSliderValue(value, min, max));
		$(amountLabelString).val(formatInputValue(input,getSliderValue(value, min, max)));

	}

	/**
	 * Adds sliders to actions form.
	 */
	function addSliders() {
		if (!model) {
			throw "Can't show model form as no model has been loaded.";
		}

		for ( var i in model.inputsMetadata) {
			var input = model.inputsMetadata[i];

			if (input.vartype == "RANGE" || input.vartype == "FUZZY_DISCRETE") {
				addSlider(input,"slider","amount","amountlabel");
			} else {
				var valArray = input.categories;

				$(sliderString).addClass('drop-down');

				$(sliderString).append(
						"<select id='input-drop" + input.id + "'></select>");
				// add the select tag
				for ( var j = 0; j < input.categories; j++) {
					$('#input-drop' + input.id).append(
							"<option value='" + input.categories[j] + "'>"
									+ input.categories[j] + "</option>");
				}
			}
		}
	}

	/**
	 * Setter for impactsPlaceholder.
	 *
	 * @param parentId
	 *            new value for impactsPlaceholder
	 */
	this.setImpactsPlaceholder = function(parentId) {
		impactsPlaceholder = parentId;
	}

	this.toggleDescription = function(inputId) {
		$("#input" + inputId + "description").toggle('normal');
	}

	/**
	 * Renders model form (Actions).
	 *
	 * @param parentId
	 *            id of parent element in which form is to be rendered
	 * @param disabled
	 *            if true form will be disabled (can be ommited)
	 */
	this.showModelForm = function(parentId, disabled) {
		if (!model) {
			throw "Can't show model form as no model has been loaded.";
		}
		
		$("#" + parentId)
		.append(
				"<table id='modelInputsForm' border=\"0\" cellpadding=\"0\" cellspacing=\"0\"></table>");

		if (model.inputView) {
			buildFormFromTemplate("modelInputsForm");
		} else {

		var even = false;
		for ( var i in model.inputsMetadata) {
			var input = model.inputsMetadata[i];
			$("#modelInputsForm").append(
					"<tr id='input" + input.id + "' class='"
							+ (even ? "even" : "") + "'></tr>");
			$("#input" + input.id)
					.append(
							"<td class='first'><span>"
									+ input.name
									+ " </span> "
									+ "<a href='javascript:;' onClick='ModelUtils.toggleDescription("
									+ input.id
									+ ")' class='toggle-description'>"
									+ "<img src='/collaboratorium-theme/images/qustion_icon.png' alt='question' /></a>"
									+ "<div id='input"
									+ input.id
									+ "description' style='display: none' class='inputDescription'>"
									+ input.description + "</div></td>");
			$("#input" + input.id).append(
					"<td class='adjustment'><div class='amount-slider' id='slider"
							+ input.id + "'></div></td>");
			$("#input" + input.id).append(
					"<td><input class='date amount-input' type='text' id='amount"
					+ input.id + "' name='"+input.internalName+"'/></td>");

			even = !even;
		}

		addSliders();
		}
		if (disabled) {
			ModelUtils.disableModelForm();
		}
	}

	function buildFormFromTemplate(inputForm) {
		var groups = jQuery(model.inputView).find("group");
		if (groups.length == 0) {
			groups = jQuery(model.inputView).filter("group");
		}
		
		var maxlength = 0;
		jQuery.each(groups,function(i,group) {
		     maxlength = Math.max(maxlength,$(group).find("control").length);
		});
		
		var inputMap = new Object();

		for ( var id in model.inputsMetadata) {
			inputMap[model.inputsMetadata[id].internalName] = model.inputsMetadata[id];
		}
		var even = false;
		for ( var i = 0; i < groups.length; i++) {

			var input = inputMap[$(groups[i]).attr('id')];
			if (!input)
				continue;
			var controls = $(groups[i]).find("control");
			var groupId = addInputGroup(inputForm, input, $(groups[i]).attr(
					"type"),maxlength,even);

			for ( var j = 0; j < maxlength; j++) {
			     if (j<controls.length) {
					var input = inputMap[$(controls[j]).attr('id')];
					if (!input)
						continue;
					addInputControl(groupId, input, $(controls[j]).attr("type"));
				} else {
				     	$("#"+groupId).append(
					"<td class='control-cell'></td>");
				}
			}
			even = !even;

		}
	}

	function addInputGroup(inputForm, input, type, colspan, even) {
		var groupId = "group"+input.id;
		var groupDescriptionId = "groupdescription"+input.id;

		$("#"+inputForm).append(
				"<tr id='"+groupDescriptionId + "' class='"
						+ (even ? "even" : "") + "'></tr>");

		$("#"+groupDescriptionId).append(
				"<td class='first' colspan='"+colspan+"'><span>"
						+ input.name
						+ " </span> "
						+ "<a href='javascript:;' onClick='ModelUtils.toggleDescription("
						+ input.id
						+ ")' class='toggle-description'>"
						+ "<img src='/collaboratorium-theme/images/qustion_icon.png' alt='question' /></a>"
						+ "<div id='input"
						+ input.id
						+ "description' style='display: none' class='inputDescription'>"
						+ input.description + "</div></td>");

		$("#"+inputForm).append(
				"<tr id='"+groupId + "' class='"
						+ (even ? "even" : "") + "'></tr>");

		return groupId;
	}

	function addInputControl(groupId, input, type) {
		if (type == "slider-text") {

			$("#"+groupId).append(
					"<td class='control-cell'><table border='0' class='control-table'><tr><td><div class='amount-slider' id='slider"
							+ input.id + "'/></td><td><input class='date amount-input' type='hidden' id='amount"
							+ input.id + "' name='"+input.internalName+"'/>"+
							"<input class='date amount-input' type='text' id='amountlabel"
							+ input.id + "' name='"+input.internalName+"label'/>"+
							"</td></tr></table></td>");
//			$("#"+groupId).append(
//					"<td></td>");
			addSlider(input,"slider","amount","amountlabel");
		} else if (type == "text") {
			var id = "text-control"+input.id;
			var label = input.labels?input.labels[0]:"";
			$("#"+groupId).append(
					"<td class='control-cell'><table border='0' class='control-table'><tr><td class='control-label'>"+label+"</td><td>"+
					"<input class='date amount-input' type='text' id='amount"
							+ input.id + "' name='"+input.internalName+"'/>"+
					"</td></tr></table></td>");

			setupTextBox(input,"amount"+input.id);
		}
	}

	function setupTextBox(input,textboxId) {
		var min = 0;
		var max = 100;
		var def = 0;

		if (input.mins) {
			min = input.mins[0];
		}
		if (input.maxes) {
			max = input.maxes[0];
		}
		if (input.defaults) {
			def = input.defaults[0];
		}
		if (scenario) {
			def = scenario.inputs[input.id].values[0];
		}

		if (def < min || isNaN(def)) {
			def = min;
		} else if (def > max) {
			def = max;
		}
		$("#"+textboxId).val(def+"");

		$("#"+textboxId).blur(function() {
			var curr = $(this).val();
			if (curr > max) $(this).val(max);
			else if (curr < min) $(this).val(min);
		});
	}



	/**
	 * Enables Actions form.
	 */
	this.enableModelForm = function() {
		$('.amount-slider').slider('enable');
		$('.amount-input').removeAttr('disabled');
	}

	/**
	 * Disables Actions form.
	 */
	this.disableModelForm = function() {
		$('.amount-slider').slider('disable');
		$('.amount-input').attr('disabled', 'true');
	}

	/** Runs model */
	this.newRunModel = function() {

		var query = new Array();
		$("input").each(function() {
			query.push($(this).attr("name")+"="+$(this).val());
		});
		query.push('simId=' + model.id);
		query.push('path=' + model.url);

		var paramString = "";
		var nextParam;
		for ( var i = 0; i < query.length; i++) {
			nextParam = query[i];
			if (i != query.length - 1) {
				nextParam += '&';
			}
			paramString += nextParam;
		}

		ModelUtils.showLoadingScreen();
		$
				.ajax( {
					type :"POST",
					url :runModelURL,
					data :paramString,
					dataType :"xml",
					timeout :timeout,
					success : function(xmlData) {
						loadScenario(xmlData);
						showMessage('Model ran successfully.');
					},
					error : function() {
						callListeners(scenarioReadyCallbacks, "success");
						showMessage(
								'The model may have timed out due to a system error. Please contact the system admin or try again.',
								'error');
					}
				});
	}


	/** Runs model */
	this.runModel = function() {

		var query = new Array();

		for ( var inputId in model.inputsMetadata) {
			var input = model.inputsMetadata[inputId];
			var sliderString = "#slider" + input.id;

			if ($(sliderString).hasClass('drop-down')) {
				var currInputValue = $(
						"#slider" + input.id + " option:selected")
						.attr("value");
				query.push(input.internalName + '=' + currInputValue);
			} else {
				query.push(input.internalName + '='
						+ $("#amount" + input.id).val());
			}
		}
		queryGlobal = query;

		query.push('simId=' + model.id);
		query.push('path=' + model.url);

		var paramString = "";
		var nextParam;
		for ( var i = 0; i < query.length; i++) {
			nextParam = query[i];
			if (i != query.length - 1) {
				nextParam += '&';
			}
			paramString += nextParam;
		}

		ModelUtils.showLoadingScreen();
		$
				.ajax( {
					type :"POST",
					url :runModelURL,
					data :paramString,
					dataType :"xml",
					timeout :timeout,
					success : function(xmlData) {
						loadScenario(xmlData);
						showMessage('Model ran successfully.');
					},
					error : function() {
						callListeners(scenarioReadyCallbacks, "success");
						showMessage(
								'The model may have timed out due to a system error. Please contact the system admin or try again.',
								'error');
					}
				});
	}

	/**
	 * Saves scenario.
	 *
	 * @param callback
	 *            callback function that should be called after saving scenario
	 *            (can be ommited)
	 */
	this.saveScenario = function(callback) {
		if (!scenario) {
			throw "No scenario has been loaded.";
		}
		$
				.ajax( {
					type :"POST",
					url :saveScenarioURL + scenario.id,
					data :"state=PUBLIC",
					dataType :"xml",
					timeout :timeout,
					success : function() {
						showMessage('Scenario saved successfully.');
						if (callback instanceof Function) {
							callback("success");
						}
					},
					error : function() {
						showMessage(
								'Error while trying to save the scenario. Please contact the system admin or try again.',
								'error');
						if (callback instanceof Function) {
							callback("error");
						}
					}
				});
	}

	/**
	 * Checks if given value is in array.
	 *
	 * @param array
	 *            array with values
	 * @param value
	 *            value to be searched for
	 * @return true if value is in array
	 */
	function inArray(array, value) {
		for ( var i = 0; i < array.length; i++) {
			if (array[i] == value) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Calculates average value from passed values.
	 *
	 * @param array
	 *            array of values
	 * @return average value
	 */
	function calculateAvg(array) {
		var sum = 0;
		for ( var i = 0; i < array.length; i++) {
			sum += Number(array[i]);
		}
		return sum / array.length;

	}

	/**
	 * Calculates standard deviation from values contained in array.
	 *
	 * @param array
	 *            array of values
	 * @value standard deviation
	 */
	function calculateStdDev(array) {
		var avg = calculateAvg(array);
		var stdDev = 0;
		for ( var i = 0; i < array.length; i++) {
			stdDev += (array[i] - avg) * (array[i] - avg);
		}
		stdDev /= array.length;
		return Math.sqrt(stdDev);
	}

	/**
	 * Retrieves values describing created scenario (needed for plan creation).
	 *
	 * @return object with values from scenario
	 */
	this.getValuesForPlan = function() {
		var mitigationCostNames = [ '_Change_in_GDP_vs__baseline_igsm_output',
				'_Change_in_GDP_vs__baseline_merge_output',
				'_Change_in_GDP_vs__baseline_minicam_output' ];

		var damageCostNames = [ 'Percent_Change_GDP_vs__Basline_output',
				'Mean_Percent_Change_GDP_vs__Basline_output' ];

		var otherNames = [ 'DevelopedFossilFuelEmissions',
				'DevelopingBFossilFuelEmissions',
				'DevelopingAFossilFuelEmissions',
				'AtmosphericCO2Concentration', 'GlobalTempChange',
				'Sea_Level_Rise_output' ];

		var mitigationValues = new Array();

		var damageCostValues = new Array();

		var values = new Object();
		
		for ( var varId in scenario.outputs) {
			var name = scenario.outputs[varId].metadata.internalName;
			var val = scenario.outputs[varId].values[scenario.outputs[varId].values.length - 1][1];
			if (inArray(otherNames, name)) {
				values[name] = val;
			} else if (inArray(mitigationCostNames, name)) {
				mitigationValues.push(val);
			} else if (inArray(damageCostNames, name)) {
				damageCostValues.push(val);
			}
		}
		values['damageCostAvg'] = calculateAvg(damageCostValues);
		values['damageCostStdDev'] = calculateStdDev(damageCostValues);
		values['mitigationCostAvg'] = calculateAvg(mitigationValues);
		values['mitigationCostStdDev'] = calculateStdDev(mitigationValues);

		return values;
	}

	/**
	 * Class representing Model entity. Model data are read from jQuery object.
	 *
	 * @param modelDef
	 *            jQuery object with model definition (from XML)
	 */
	function Model(modelDef) {
		var outputsGroupMap = new Object();

		var childNodes = modelDef.children();

		this.id = modelDef.attr("id");
		this.isComposite = modelDef.attr("composite") == "true";
		this.description = childNodes.filter("description").text();
		this.shortdescription = parseShortDescription(this.description);
		
		this.name = childNodes.filter("name").text();
		this.state = childNodes.filter("state").text();
		this.createDate = childNodes.filter("creation").text();
		this.url = childNodes.filter("url").text();
		
		// IE fix
		this.inputView = inputsViewConfig[this.id];
		if (this.inputView) {
			if (window.DOMParser) {
				this.inputView = new DOMParser().parseFromString(inputsViewConfig[this.id],"text/xml");
			}
			else { // Internet Explorer 
				this.inputView = new ActiveXObject("Microsoft.XMLDOM");
				this.inputView.async="false";
				this.inputView.loadXML(inputsViewConfig[this.id]);
			} 
		}
		//this.inputView = inputsViewConfig[this.id];

		if (this.isComposite) {
			this.childModels = new Array();
			var childrenDef = modelDef.find("children childsim");

			for ( var i = 0; i < childrenDef.length; i++) {
				var childDef = childrenDef.eq(i);
				var childModel = new Object();

				childModel.type = childDef.find("type").text();
				childModel.id = childDef.find("id").text();

				this.childModels.push(childModel);
			}
		}
		
		function parseShortDescription(text) { 
		     var endindex = text.indexOf("</p>");
		     if (endindex <0) return text; 
		     else return text.substring(0,endindex)+"</p>";
		}

		/**
		 * Reads metadata (input/output).
		 *
		 * @param metadataDef
		 *            jQuery object with metadata definition
		 * @return array of Metadata objects
		 */
		function readMetadata(metadataDef) {
			var ret = new Object();
			var groupsMap = new Object();
			var groupNumber = 0;
			for ( var i = 0; i < metadataDef.length; i++) {
				var singleDef = metadataDef.eq(i);
				var metadata = new Metadata(singleDef);
				var groupingString = metadata.vartype + metadata.varcontext
						+ metadata.profile + metadata.labels + metadata.maxes
						+ metadata.mins;
				if (!(groupsMap[groupingString] >= 0)) {
					groupsMap[groupingString] = groupNumber;
					groupNumber += 1;
				}
				metadata.group = groupsMap[groupingString];
				ret[metadata.id] = metadata;
			}
			return ret;
		}

		this.outputsMetadata = readMetadata(modelDef.find("outputs > metadata"));
		this.inputsMetadata = readMetadata(modelDef.find("inputs > metadata"));
		
		this.metaDataIdForName = function(name) {
		     var result = null;
		     jQuery.each ([this.outputsMetadata,this.inputsMetadata],function(i,data) {
			     jQuery.each(data,function(i2,data2) {
			          if (data2.internalName == name) {
			               result = data2.id;
			               return false;
			          }
			     });
			     return result ==null;
			     });
		     return result;
		}
	}

	/**
	 * Class representing Scenario entity.
	 *
	 * @param scenarioDef
	 *            jQuery object with scenario definition (from XML)
	 * @param model
	 *            model referenced by scenario
	 */
	function Scenario(scenarioDef, model) {
		var childNodes = scenarioDef.children();

		this.model = model;
		this.id = scenarioDef.attr("id");
		this.name = childNodes.filter("name").text();
		this.state = childNodes.filter("state").text();
		this.simulationId = childNodes.filter("simulation > id").text();
		this.createDate = childNodes.filter("creation").text();
		
		var variableGroups = new Array();

		/**
		 * Reads variables definitions.
		 *
		 * @param variableDef
		 *            collection of variables definitions
		 * @param metadataMap
		 *            map of metadata (metadata id is a key), used to find
		 *            metadata for given variable
		 */
		function readVariables(variablesDef, metadataMap) {
			var ret = new Object();
			for ( var i = 0; i < variablesDef.length; i++) {
				var variableDef = variablesDef.eq(i);
				variable = new Object();

				variable.metadataId = variableDef.find("metadata > id").text();
				variable.metadata = metadataMap[variable.metadataId];

				variable.values = ModelUtils.parseValues(variableDef.find(
						"values > data").text());
				variable.id = variableDef.attr("id");

				ret[variable.metadataId] = variable;
				
				if (variable.metadata.internalName == "AtmosphericCO2Concentration") {
				     if (variable.values[variable.values.length-1][1] <= 450) {
				          warningFlag = true;
				     }
				}
			}
			return ret;
		}

		this.inputs = readVariables(scenarioDef.find("inputs > variable"),
				model.inputsMetadata);
		this.outputs = readVariables(scenarioDef.find("outputs > variable"),
				model.outputsMetadata);
	   
				
	     this.variableForName = function(name) {
	          var id = model.metaDataIdForName(name);
	          if (id != null) {
	               if (this.inputs[id]) return this.inputs[id];
	               else return this.outputs[id];
	          } else return null;
	     }
	}

	/**
	 * Class representing Metadata entity.
	 *
	 * @param metadataDef
	 *            jQuery object with Metadata definition
	 */
	function Metadata(metadataDef) {
		this.vartype = metadataDef.attr("vartype");
		this.index = metadataDef.attr("index") == "true";
		this.varcontext = metadataDef.attr("varcontext");
		this.defaults = ModelUtils.parseValues(metadataDef.find(
				"defaults > data").text());
		this.description = metadataDef.find("description").text();
		this.external = metadataDef.find("external").text();
		this.id = metadataDef.children("id").text();
		this.name = metadataDef.find("name").text();
		this.internalName = metadataDef.find("internalname").text();
		this.labels = ModelUtils.parseValues(metadataDef.find("labels > data")
				.text());
		this.maxes = ModelUtils.parseValues(metadataDef.find("maxes > data")
				.text());
		this.mins = ModelUtils.parseValues(metadataDef.find("mins > data")
				.text());
		this.profile = ModelUtils.parseValues(metadataDef
				.find("profile > data").text());
		this.units = ModelUtils.parseValues(metadataDef.find("units > data")
				.text());
		this.categories = ModelUtils.parseValues(metadataDef.find(
				"categories > data").text());
		this.displayWeight = jQuery.inArray(this.internalName,displayOrder);
		if (this.displayWeight < 0) {
		      displayWeight=999;    
		}
	}

	/**
	 * OutputGroup class that is responsible for rendering variables with free
	 * text.
	 */
	function FreeOutputsGroup() {
		/**
		 * Weight for determining display order.
		 */
		this.displayWeight = 200;
		/**
		 * Collection of variables belonging to "free" group.
		 */
		var variables = new Array();

		/**
		 * Name of variable with temperature change output.
		 */
		var temperatureChangeName = 'GlobalTempChange';

		/**
		 * Variable with temperature change.
		 */
		var temperatureChange = null;

		/**
		 * Checks if given variable is a "free" variable and if it is, adds it
		 * to collection.
		 *
		 * @param variable
		 *            variable that should be checked and added
		 * @return true if variable has been added, false otherwise
		 */
		this.addVariable = function(variable) {
			 if (variable.metadata.internalName == temperatureChangeName) {
				temperatureChange = variable;
				return false;
			}else if (variable.metadata.vartype == 'FREE' && variable.metadata.varcontext!='INDEX') {
				variables.push(variable);
				return true;
			} 
			return false;
		}

		/**
		 * Resets object sate.
		 */
		this.clear = function() {
			variables = new Array();
			delete temperatureChange;
		}

		/**
		 * Renders full view of a variables group.
		 *
		 * @param parentId
		 *            id of an element into which variables should be rendered
		 */
		this.displayFull = function(parentId) {
			if (variables.length == 0 || ! temperatureChange) {
				return;
			}
			
			$("#" + parentId)
					.append(
							"<h3><a href='#'>Physical Impacts</a><a href='javascript:;' class='folder'><span class='hidden'>Folder/unFolder</span></a></h3><div class='physical-impacts' id='physical-impacts'></div>");
			if (temperatureChange) {
				$("#physical-impacts").append(
						"<div class='models-temperature-change'>Temperature change by 2100: "
								+ "<span class='temp'>"
								+ temperatureChange.values[temperatureChange.values.length-1][1]
								+ degreeStr + "</span></div>");
			}
			if (variables.length > 0) {
				$("#physical-impacts").append(
						"<div id='physical-impacts-tabs'><ul></ul></div>");

				var container = $("#physical-impacts-tabs");
				var tabsContainer = container.find("ul");
				var tabtracking = new Object();

				for ( var i = 0; i < variables.length; i++) {
				     var variable = variables[i];
				    var impactTabId = tabtracking[variable.metadata.name];
				     if (!impactTabId) {
					      impactTabId = "physical-impacts-tabs-" + i;
					      tabtracking[variable.metadata.name] = impactTabId;
						  tabsContainer.append("<li><a href='#" + impactTabId + "'>"
								+ variable.metadata.name.replace("/","/ ")
								+ "</a></li>");
						container.append("<div id='" + impactTabId + "'></div>");
					}
					//for ( var degId in variable.values) {
						$("#" + impactTabId).append(
								"<h4>"+variable.metadata.labels[1]+" impacts at " + variable.values[0]
										+ degreeStr + "</h4>");
						$("#" + impactTabId).append(
								"<p>" + variable.values[1] + "</p");
					//}
				}
				container.tabs()
						.addClass('ui-tabs-vertical ui-helper-clearfix');
				container.find("li").removeClass('ui-corner-top').addClass(
						'ui-corner-left');
			}

		}

		/**
		 * Displays short display of variables group. (Free text variables
		 * aren't displayed in summary)
		 *
		 * @param parentTableId
		 *            id of a table element into which variables should be
		 *            rendered
         *
         *
		 * @return added impacts count
		 */
		this.displayShort = function(parentTableId, even) {
			return 0;
			// don't do anything, free text variables go only to full view
		}
	}

	/**
	 * OutputGroup class that is responsible for rendering variables
	 * representing matrices.
	 */
	function IndexedOutputsGroup() {

		/**
		 * Weight for ordering groups.
		 */
		this.displayWeight = 0;

		/**
		 * Collection of variables in this group.
		 */
		var variables = new Array();

		/**
		 * Checks if given variable is a "free" variable and if it is, adds it
		 * to collection.
		 *
		 * @param variable
		 *            variable that should be checked and added
		 * @return true if variable has been added, false otherwise
		 */
		this.addVariable = function(variable) {
			if (variable.metadata.vartype == "RANGE"
					&& variable.metadata.varcontext == "INDEXED") {
				variables.push(variable);
				this.displayWeight = jQuery.inArray(variable.metadata.internalName,displayOrder);
				this.displayWeight = this.displayWeight < 0 ? 999:this.displayWeight;
				variables.sort(function(a,b) {
				     return a.metadata.displayWeight - b.metadata.displayWeight;
				});
				return true;
			}
			return false;
		}

		/**
		 * Resets object sate.
		 */
		this.clear = function() {
			variables = new Array();
		}

		/**
		 * Renders full view of a variables group.
		 *
		 * @param parentId
		 *            id of an element into which variables should be rendered
		 */
		this.displayFull = function(parentId) {
			var groups = new Object();

			for ( var i = 0; i < variables.length; i++) {
				var variable = variables[i];
				if (!(groups[variable.metadata.group])) {
					groups[variable.metadata.group] = new Array();
				}
				groups[variable.metadata.group].push(variable);
			}

			for ( var groupId in groups) {
				var group = groups[groupId];
				var meta = group[0].metadata;
				
				

				var seriesSet = new Array();
				var dataSet = new Array();
				
				var xtickformat = meta.profile[0].indexOf("Integer") > 0?"%d":"%.1f";
               var ytickformat = meta.profile[1].indexOf("Integer") > 0?"%d":"%.1f";

				for ( var i = 0; i < group.length; i++) {
					variable = group[i];
					seriesSet.push( {
						label :variable.metadata.name +" ("+meta.units[1]+") "+legendInfoURL,
						showMarker :false
					});
					dataSet.push(variable.values);
				}
				$("#" + parentId)
						.append(
								"<h3><a href='#'>"
										+ meta.labels[1]
										+ "</a><a href='javascript:;' class='folder'><span class='hidden'>Folder/unFolder</span></a></h3>\n<div style='height: 360px;'><div style='height: 250px;'><div style='height: 90%; width: 90%' id='model-chart-"
										+ groupId + "'></div></div></div>");
				$.jqplot('model-chart-' + groupId, dataSet, {
					legend : {
						show :true,
						location :'nw',
						yoffset :230,
						xoffset:0
					},
					title :{show:false},
					series :seriesSet,
					axes : {
						xaxis : {
							label :meta.labels[0],
							tickOptions: {
							     formatString: xtickformat
							     
							},
							pad: 0,
							min: meta.mins[0],
							max: meta.maxes[0]
						},
						yaxis : {
							tickInterval: (meta.internalName == "AtmosphericCO2Concentration")?100:pickTickInterval(meta.mins[1],meta.maxes[1],5),
							tickOptions: {
							     formatString:ytickformat
							   
							},
						     pad: 0,
							  min: meta.mins[1],
							  max: meta.maxes[1]
						}
					},
					highlighter : {
						show :false
					},
					cursor : {
						tooltipLocation :'sw'
					}
				});
				
				if (meta.labels[1] == "Mitigation Cost") {
				     
				     if (warningFlag) {
				          $("#model-chart-"+groupId).append(warningText);
				     }
				}
			}
		}

		/**
		 * Displays short display of variables group.
		 *
		 * @param parentTableId
		 *            id of a table element into which variables should be
		 *            rendered
		 * @return added impacts count
		 */
		this.displayShort = function(parentTableId, even) {
			parentTableId = "#" + parentTableId;
			for ( var i = 0; i < variables.length; i++) {
				var variable = variables[i];
				if(!variable.values[variable.values.length - 1] || !variable.values[variable.values.length - 1][1] || isNaN(parseFloat(variable.values[variable.values.length - 1][1]))) {
					continue;
				}
				$(parentTableId)
						.append(
								"<tr class='"
										+ (even ? "even" : "")
										+ "'><td class='first'>"
										+ variable.metadata.name
										+ "</td><td>"
										+ variable.values[variable.values.length - 1][1]
												.toFixed(3) + "</td></tr>");
				even = !even;
			}
			return variables.length;
		}
	}

	/**
	 * OutputGroup class that is responsible for rendering variables related to
	 * damage cost.
	 */
	function DamageCostOutputsGroup() {
		/**
		 * Weight for ordering groups.
		 */
		this.displayWeight = 10;

		/**
		 * Variable representing mean.
		 */
		var mean = null;

		/**
		 * Variable representing base.
		 */
		var base = null;

		/**
		 * Variable representing 5%.
		 */
		var percent5 = null;

		/**
		 * Variable representing 95%.
		 */
		var percent95 = null;

		/**
		 * Name of variable with mean.
		 */
		var meanName = 'Mean_Percent_Change_GDP_vs__Basline_output';

		/**
		 * Name of variable with base.
		 */
		var baseName = 'Percent_Change_GDP_vs__Basline_output';

		/**
		 * Name of variable with 5%.
		 */
		var percent5Name = 'PercentChange5_output';

		/**
		 * Name of variable with 95%.
		 */
		var percent95Name = 'PercentChange95_output';

		/**
		 * Checks if given variable is a "free" variable and if it is, adds it
		 * to collection.
		 *
		 * @param variable
		 *            variable that should be checked and added
		 * @return true if variable has been added, false otherwise
		 */
		this.addVariable = function(variable) {
			var flag = false;
			switch (variable.metadata.internalName) {
			case meanName:
				mean = variable;
				flag = true;
				break
			case baseName:
				base = variable;
				flag=true;
				break;
			case percent5Name:
				percent5 = variable;
				flag= true;
				break;
			case percent95Name:
				percent95 = variable;
				flag= true;
				break
			}
			if (flag) {
			     this.displayWeight = jQuery.inArray(variable.metadata.internalName,displayOrder);
				this.displayWeight = this.displayWeight < 0 ? 999:this.displayWeight;
				
			
			}
			return flag;
			
		}

		/**
		 * Resets object sate.
		 */
		this.clear = function() {
			delete base;
			delete mean;
			delete percent5;
			delete percent95;
		}

		/**
		 * Renders full view of a variables group.
		 *
		 * @param parentId
		 *            id of an element into which variables should be rendered
		 */
		this.displayFull = function(parentId) {
			if (!mean || !base || !percent5 || !percent95) {
				return;
				// throw "Can't display damage cost graph, object is in invalid
				// state\tmean: " + mean + "\tbase: " + base + "\tpercent5: " +
				// percent5 + "\tpercent95: " + percent95;
			}

			var meta = base.metadata;
			var HLCDataSet = new Array();
			for ( var i = 0; i < mean.values.length; i++) {
				HLCDataSet.push( [ mean.values[i][0], percent5.values[i][1],
						percent95.values[i][1], mean.values[i][1] ]);
			}

			$("#" + parentId)
					.append(
							"<h3><a href='#'>"
									+ meta.labels[1]
									+ "</a><a href='javascript:;' class='folder'><span class='hidden'>Folder/unFolder</span></a></h3><div style='height: 360px;'><div style='height: 250px;'><div style='height: 90%; width: 90%' id='model-chart-damage-cost'></div></div></div>");

			$.jqplot('model-chart-damage-cost', [ mean.values, base.values,
					HLCDataSet ], {
				title :{show:false},
				axes : {
					xaxis : {
						
     						pad: 0,
							label :meta.labels[0],
							pad: 0,
							min: meta.mins[0],
							max: meta.maxes[0]
						
					},
					yaxis : {
					     tickInterval: pickTickInterval(meta.mins[1],meta.maxes[1],5),
					    pad: 0,
					    min: meta.mins[1],
						max: meta.maxes[1]
						
					}
				},
				highlighter : {
					show :false
				},
				cursor : {
					tooltipLocation :'sw'
				},
				legend : {
					show :true,
					location :'nw',
					yoffset :230
				},
				series : [ {
					label :mean.metadata.name+" ("+mean.metadata.units[1]+") <i>90% confidence intervals shown</i> "+legendInfoURL,
					renderer :$.jqplot.LineRenderer,
					showMarker :false
				}, {
					label :base.metadata.name+ " ("+base.metadata.units[1]+") "+legendInfoURL,
					renderer :$.jqplot.LineRenderer,
					showMarker :false
					
				}, {
				     showLabel:false,
					renderer :$.jqplot.OHLCRenderer,
					color :"rgb(125, 228, 247)",
					showMarker :false
					
				} ]
			});
		}

		/**
		 * Displays short display of variables group.
		 *
		 * @param parentTableId
		 *            id of a table element into which variables should be
		 *            rendered
		 * @return added impacts count
		 */
		this.displayShort = function(parentTableId, even) {
			parentTableId = "#" + parentTableId;
			var variables = [ mean, base ];
			for ( var i = 0; i < variables.length; i++) {
				var variable = variables[i];				
				if(!variable.values[variable.values.length - 1] || !variable.values[variable.values.length - 1][1] || isNaN(parseFloat(variable.values[variable.values.length - 1][1]))) {
					continue;
				}
				$(parentTableId)
						.append(
								"<tr class='"
										+ (even ? "even" : "")
										+ "'><td class='first'>"
										+ variable.metadata.name
										+ "</td><td>"
										+ variable.values[variable.values.length - 1][1]
												.toFixed(3) + "</td></tr>");
				even = !even;
			}
			return variables.length;
		}
	}
	
	function pickTickInterval(min, max,preferredTicks) {
	     var interval = (max - min)/preferredTicks;
	    
	    var divideback = .1;
	     while (interval != Math.floor(interval)) {
	          interval*=10;
	          divideback*=10;
	     }
	     interval=(Math.round((interval / 10)*2)/2)/divideback ;
	     return interval;
	}

	/**
	 * OutputGroup class that is responsible for rendering variables
	 * representing single scalar values.
	 */
	function ScalarOutputsGroup() {

		/**
		 * Weight for ordering groups.
		 */
		this.displayWeight = 0;

		/**
		 * Collection of variables in this group.
		 */
		var variables = new Array();

		/**
		 * Checks if given variable is a "free" variable and if it is, adds it
		 * to collection.
		 *
		 * @param variable
		 *            variable that should be checked and added
		 * @return true if variable has been added, false otherwise
		 */
		this.addVariable = function(variable) {
			if (variable.metadata.varcontext == "SCALAR"
					&& variable.metadata.vartype == "RANGE") {
				variables.push(variable);
				return true;
			}
			return false;
		}

		/**
		 * Resets object sate.
		 */
		this.clear = function() {
			variables = new Array();
		}

		/**
		 * Renders full view of a variables group.
		 *
		 * @param parentId
		 *            id of an element into which variables should be rendered
		 */
		this.displayFull = function(parentId) {
			if (variables.length == 0) {
				return;
			}
			$("#" + parentId)
					.append(
							"<h3><a href='#'>Other Impacts</a></h3><div><table id='other-impacts'>"
									+ "<thead><tr><th>Name</th><th>Value</th></tr></thead></table>");
			for ( var i = 0; i < variables.length; i++) {
				var variable = variables[i];
                var text = variable.values[variable.values.length-1][0].toFixed(1);
				$("#other-impacts").append(
						"<tr><td class='impact-name'>" + variable.metadata.name
								+ "</td><td class='impact-value'>"
								+ text + "</td></tr>");
			}
		}

		/**
		 * Displays short display of variables group.
		 *
		 * @param parentTableId
		 *            id of a table element into which variables should be
		 *            rendered
		 * @return added impacts count
		 */
		this.displayShort = function(parentTableId, even) {
			parentTableId = "#" + parentTableId;
			for ( var i = 0; i < variables.length; i++) {
				var variable = variables[i];
				$(parentTableId).append(
						"<tr class='" + (even ? "even" : "")
								+ "'><td class='first'>"
								+ variable.metadata.name + "</td><td>"
								+ variable.values + "</td></tr>");
				even = !even;
			}
			return variables.length;
		}
	}
}
