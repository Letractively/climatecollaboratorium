function updateFrontendField(event, ui) {
	jQuery("#sliderValue").val(ui.value);
	jQuery("#integrationForm .sliderVal").val(ui.value);
	jQuery("#integrationForm .submit").click();
	
}
/**
 * Object that will be sent by ICEFaces backend will consist of following fields:
 * id: id of an event/data set, String, not null
 * timestamp: timestamp when it was generated
 * payload: payload that should be handled by JS on frontend layer
 * 
 */

var icefacesEventManager = new function() {
	var eventHandlers = {};
	var eventTimestamps = {};
	
	this.registerHandler = function(eventId, callback) {
		eventHandlers[eventId] = callback;
		eventTimestamps[eventId] = 0;
	}
	
	this.deliverEventIfNew = function(event) {
		try {
		if (typeof(eventHandlers[event.id]) != "undefined") {
			if (eventTimestamps[event.id] < event.timestamp) {
				//alert("delivering event: " + event.id + "\ttimestamp: " + event.timestamp);
				eventTimestamps[event.id] = event.timestamp;
				eventHandlers[event.id](event);
			}
		}
		} catch (e) {
			alert(e);
		}
	}
	
};

function simpleCallback(event) {
	jQuery('#graph1').html("");
	plot1 = jQuery.jqplot('graph1', [event.payload], { 
	    title:'Line Style Options', 
	    series:[ 
	        {lineWidth:2, markerOptions:{style:'dimaond'}}, 
	        {showLine:false, markerOptions:{size: 7, style:'x'}}, 
	        {markerOptions:{style:'circle'}}, 
	        {lineWidth:5, markerOptions:{style:'filledSquare', size:14}}
	    ]
	});
	
}

jQuery(document).ready(function() {
	
	jQuery("#slider").slider({
		stop: updateFrontendField
	});
	icefacesEventManager.registerHandler("graph1", simpleCallback);
	
	
	Ice.onAsynchronousReceive("mainContent", function() {
			var val = jQuery("#integrationForm .dataOutput").val();
			if (val == "") {
				return;
			}
			var event = eval("(" + val + ")");
			icefacesEventManager.deliverEventIfNew(event);
			//jQuery("#test").prepend("<h3>async: " + obj.payload + "</h3>" + "<h4>" + icefacesEventManager.registerHandler + "</h4>");
		});
	
	var tmpPoints = [];
	var a = 10;
	
	for (var i=-100; i < 100; i+= 1) {
		tmpPoints.push([i, Math.pow(i, a)]);
	}
	

	jQuery("#sliderValue").blur(function() {
		var max = jQuery("#slider").slider("option", "max");
		var min = jQuery("#slider").slider("option", "min");
		var val = jQuery("#sliderValue").val();
		var max = jQuery( "#slider" ).slider( "option", "max" );

		jQuery("#slider").slider("moveTo", jQuery("#sliderValue").val());
		return true;
	});
		
		
	

	
});