function monitorUploadFrame() {
	jQuery('.profile_upload iframe').load(function() {
		jQuery(".signalPictureUploaded").click();
	})

}