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
		if (typeof(event) != "undefined" && typeof(eventHandlers[event.id]) != "undefined") {
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
	
	this.sendEventToTheBackend = function(eventId, payload) {
		var event = {
				timestamp: (new Date()).getTime(),
				id: eventId, 
				payload: payload
		};
		
				
		jQuery(".eventInput").val(jQuery.toJSON(event));
		jQuery("#integrationForm .submit").click();
	}
	
};


jQuery(document).ready(function() {
	
	Ice.onAsynchronousReceive("mainContent", function() {
			var val = jQuery("#integrationForm .eventOutput").val();
			if (val == "") {
				return;
			}
			var event = eval("(" + val + ")");
			icefacesEventManager.deliverEventIfNew(event);
			//jQuery("#test").prepend("<h3>async: " + obj.payload + "</h3>" + "<h4>" + icefacesEventManager.registerHandler + "</h4>");

		});

	
});