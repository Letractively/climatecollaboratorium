function monitorUploadFrame() {
	jQuery('.profile_upload iframe').load(function() {
		jQuery(".signalPictureUploaded").click();
	})

}

function selectAllSubscriptions() {
	jQuery(".subscriptionSelect").attr('checked', 'true');
}