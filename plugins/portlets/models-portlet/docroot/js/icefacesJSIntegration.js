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
		log.debug("registering handler for event " + eventId);
		eventHandlers[eventId] = callback;
		eventTimestamps[eventId] = 0;
	}
	
	this.deliverEventIfNew = function(event) {
		try {
		if (typeof(event) != "undefined" && typeof(eventHandlers[event.id]) != "undefined") {
			if (eventTimestamps[event.id] < event.timestamp) {
				log.debug("delivering event: " + event.id + "\ttimestamp: " + event.timestamp);
				eventTimestamps[event.id] = event.timestamp;
				eventHandlers[event.id](event);
			}
		}
		} catch (e) {
			log.error(e);
		}
	}
	
	this.sendEventToTheBackend = function(eventId, payload) {
		var event = {
				timestamp: (new Date()).getTime(),
				id: eventId, 
				payload: payload
		};
		
		log.debug("Sending event to the backend: " + eventId + " timestamp: " + event.timestamp + "\npayload: " + payload);
		
		jQuery(".eventInput").val(jQuery.toJSON(event));
		jQuery("#integrationForm .submit").click();
	}
};


jQuery(document).ready(function() {
	Ice.onAsynchronousReceive("mainContent", function(obj, obj1, obj2) {
		try {
			var val = jQuery("#integrationForm .eventOutput").val();
			if (val == "") {
				return;
			}
			var event = eval("(" + val + ")");
			icefacesEventManager.deliverEventIfNew(event);
		}
		catch (e) {
			log.error("onAsynchronousReceive: " + e);
		}
		});
});

