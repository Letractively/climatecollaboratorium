if (window.collab) {
	//
	alert(window.collab);
}
else {
	window.collab = {};
}

Collab = window.collab;

Collab.nav = new function() {
	var navigationItems = {};
	var req = 0;
	var alertUserOnExit = false;
	var queryString = false;

	/**
	 * this field should hold a set callback functions used to determine if contents of currently displayed
	 * page has been marked as "dirty" (that is - user has entered anything in edit boxes etc.)
	 * 
	 * it is set in Collab.nav.setEditorDirtyValidationCallback();
	 */
	var editorDirtyValidationCallbacks = [];
	
	this.navigate = function(token, parameters) {
		navigationItems[token] = parameters;
		updateHash();
	}
	
	this.navigateAddParams = function(token, additionalParameters) {
		if (typeof(navigationItems[token]) == 'undefined') {
			navigationItems[token] = {};
		}
		for (var key in additionalParameters) {
			navigationItems[token][key] = additionalParameters[key];
		}

		updateHash();
	}
	
	/**
	 * token and params in format of JS object:
	 * {
	 * 	token1: {param1: val1...},
	 * 	token2: {param2: val2...},
	 * 	...
	 * }
	 */
	this.navigateAddParamsMulti = function(tokenAndParams) {
		for (var token in tokenAndParams) {
			if (typeof(navigationItems[token]) == 'undefined') {
				navigationItems[token] = {};
			}
			for (var key in tokenAndParams[token]) {
				navigationItems[token][key] = tokenAndParams[token][key];
			}
		}

		updateHash();
	}
	
	this.navigateRemoveParams = function(token, paramsToBeRemoved) {
		if (typeof(navigationItems[token]) == 'undefined') {
			return;
		}
		for (var i=0; i < paramsToBeRemoved.length; i++) {
			delete navigationItems[token][paramsToBeRemoved[i]];
		}
	}
	
	
	this.pageload = function(hash) {
		//alert("pageload");
		navigationItems = parseToken(hash);
		//alert("after parsing token " + hash + "try to force navigation..." + jQuery(".navigationManagerForm .submit").length + " " + jQuery(".navigationManagerForm .navigationToken").length);
		forceNavigation();
		//alert("navigation forced? " + jQuery(".navigationManagerForm .submit").length);
	}

	function forceNavigation() {
		jQuery(".navigationManagerForm .navigationToken").eq(0).val(createToken());
		// below is ugly hack, but without it webkit based browsers don't work
		setTimeout(function() {
				jQuery(".navigationManagerForm .submit").eq(0).click();
			}, 0);
		
	}
		
	function updateHash() {
		//alert(req);
		//navigationItems["req"] = req++;
		//alert(navigationItems.req);
		//navigationItems['req'] = {req: req++};
		if (isAnyEditorDirty()) {
			// page contains dirty data, ask user if he really wants to leave
			if (! window.confirm("Do you really want to leave the page and lose all your work?")) {
				return ;
			}
		}
		window.location.hash = createToken();
	}
	
	function parseToken(token) {
		tokenNavigationMap = {};
		var paramGroups = token.split(";");
		for (var i=0; i < paramGroups.length; i++) {
			var paramGroup = paramGroups[i];
			var groupNameVals = paramGroup.split("=");
			if (groupNameVals.length != 2) {
				continue;
			}
			var groupName = groupNameVals[0];
			tokenNavigationMap[groupName] = {};
			var groupVals = groupNameVals[1].split(",");
			for (var k = 0; k < groupVals.length; k++) {
				var keyValue = groupVals[k].split(":");
				if (keyValue.length != 2) {
					continue;
				}
				tokenNavigationMap[groupName][keyValue[0]] = keyValue[1];
			}
		}
		return tokenNavigationMap;
	}
	
	function createToken() {
		var navigationToken = [];
		var addSemicolon = false;
		for (var subject in navigationItems) {
			if (addSemicolon) {
				navigationToken.push(";");
			}
			navigationToken.push(subject);
			navigationToken.push("=");
			var addComa = false;
			for (var key in navigationItems[subject]) {
				if (addComa) {
					navigationToken.push(",");
				}
				navigationToken.push(key);
				navigationToken.push(":");
				navigationToken.push(navigationItems[subject][key]);
				addComa = true;
			}
			var addSemicolon = true;
		}
		return navigationToken.join("");
	}
	
	/**
	 * Sets editorDirtyValidationCallback that will be used to check if contents
	 * of the page is dirty.
	 * 
	 * @param callback callback function that should return true when page is dirty
	 * 
	 */
	this.addEditorDirtyValidationCallback = /* void */ function(/* function */ callback) {
		for (var i=0; i < editorDirtyValidationCallbacks.length; i++) {
			if (editorDirtyValidationCallbacks[i] == callback) {
				return;
			}
		}
		editorDirtyValidationCallbacks.push(callback);
	}
	
	/**
	 * Checks if contents of the page is "dirty" (any editor has new data (entered by user).
	 * To check that editorDirtyValidationCallback is used (which can be set with 
	 * setEditorDirtyValidationCallback, if no callback is set false is returned.
	 * 
	 * @return true if contents of page is dirty false otherwise
	 */
	function /* boolean */ isAnyEditorDirty() {
		for (var i=0; i < editorDirtyValidationCallbacks.length; i++) {
			if (editorDirtyValidationCallbacks[i]()) {
				return true;
			}
		}
		return false;
	}
	

	/**
	 * Initialize on before unload event to prevent users from navigating away from edit page
	 * when he has edited something and he has mistakenly clicked a link.
	 * 
	 * This has to be set with a delay because either icefaces or any other JS lib modifies
	 * onbeforeunload and we have to override that.
	 * 
	 */ 
	jQuery(document).ready(function() {
		setTimeout(function() {
			var oldOnBeforeUnload = window.onbeforeunload;
			window.onbeforeunload = function() {
				if (isAnyEditorDirty()) {
					return "Do you really want to leave the page and lose all your work?";
				}
				if (oldOnBeforeUnload) {
					return oldOnBeforeUnload();
				}
				return null;
			}
		}, 2000);
	});
		
}

jQuery(document).ready(function() {
     jQuery.history.init(Collab.nav.pageload);
});