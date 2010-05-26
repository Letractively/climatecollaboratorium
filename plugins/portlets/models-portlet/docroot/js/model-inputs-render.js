jQuery(document).ready(function() {
	icefacesEventManager.registerHandler("renderModelInputs", renderModelInputs);
});

function renderModelInputs(event) {
	alert(" mam event: " + event + "\n" + event.timestamp);
}