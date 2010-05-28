jQuery(document).ready(function() {
	icefacesEventManager.registerHandler("renderModelInputs", renderModelInputs);
	icefacesEventManager.registerHandler("modelRunSuccessful", modelRunSuccessful);
});

function showSliders() {
var msg = "";
	
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
	
	msg += "\nsliderVal:" + sliderVal + "\tdefaultVal: " + defaultVal + "\n";
	

	
	valueField.blur(function() {
		var sliderVal = valueField.val();
		
		if (isDouble(dataType)) {
			sliderVal = ((sliderVal - min) / (max-min)) * (sliderMax - sliderMin);
		}

		slider.slider("moveTo", sliderVal);
		return true;
	});
});
	
	jQuery("#runModel").click(function() {
		// get data for all model inputs
		var values = new Object();

		var msg = "";
		jQuery(".sliderDef").each(function() {
			var id = jQuery(this).find('.id').val();
			var val = jQuery(this).find('.value').val();
			values[id] = val;
			msg += "\n" + id + ": " + val;
		});
		for (var x in values) {
			msg += "\n" + x + ": " + values[x];
		}
		icefacesEventManager.sendEventToTheBackend("modelRun", values);
		//alert(values + "\n" + msg);
	});
	
}

function renderModelInputs(event) {
	showSliders();
	jQuery("#runModel").click(function() {
		// get data for all model inputs
		var values = new Object();

		var msg = "";
		jQuery(".sliderDef").each(function() {
			var id = jQuery(this).find('.id').val();
			var val = jQuery(this).find('.value').val();
			values[id] = val;
			msg += "\n" + id + ": " + val;
		});
		for (var x in values) {
			msg += "\n" + x + ": " + values[x];
		}
		icefacesEventManager.sendEventToTheBackend("modelRun", values);
		//alert(values + "\n" + msg);
	});
}

function modelRunSuccessful(event) {
	showSliders();
	
	
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
		
		//alert(chartPlaceholderId + "\n" + test + "\n" + values);
		
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
		
		jQuery("#" + chartPlaceholderId).parent().css("height", (320 + (18 * values.length)) + "px");
		//
		/*
		
		var id = jQuery(this).find(".id").val();
		val = eval("(" + val + ")");
		for (var i = 0; i < val.length; i++) {
			val[i] = [parseFloat(val[i][0]), parseFloat(val[i][1])];
		}
		
		var plot1 = jQuery.jqplot("output_chart_" + id, [val], { 
		    title:'wow',
		    series:[{showMarker:false}]
		});
		*/
	}
	catch(e) {
		alert(e);
	}
	
});
}