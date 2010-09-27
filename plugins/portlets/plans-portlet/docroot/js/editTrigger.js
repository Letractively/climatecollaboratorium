function initiailzeEditTrigger(isLoggedIn, canEdit, isPlanEditable) {
	if (isPlanEditable) {
		var message = "Log in to edit";
		var callback = loginClickCallback;
		if (isLoggedIn) {
			if (canEdit) {
				message = "Click to edit";
				callback = editCallback;
			}
			else {
				message = "Join the team to edit";
				callback = joinTeamCallback;
			}
		}
			
		jQuery(".editTrigger .message").text(message);
		clearEditTrigger();
		jQuery(".editTriggerContainer").hover(function() {
				jQuery(".editTrigger").show();
			}, function() {
				jQuery(".editTrigger").hide();
			}
		);
		
		jQuery(".editTrigger").click(function() { callback() });
	}
}

function clearEditTrigger() {
	jQuery(".editTriggerContainer").unbind('mouseenter mouseleave');
	jQuery(".editTrigger").unbind("click");
	jQuery(".editTrigger").hide();
	
}

function loginClickCallback() {
	deferUntilLogin();
}

function joinTeamCallback() {
	Collab.nav.navigateAddParams("plans", {tab: "team"}, false);
}

function editCallback() {
	jQuery(".editStateTrigger").click();
}