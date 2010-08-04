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
		jQuery(".navigationManagerForm .submit").eq(0).click();
	}
		
	function updateHash() {
		//alert(req);
		//navigationItems["req"] = req++;
		//alert(navigationItems.req);
		navigationItems['req'] = {req: req++};
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
		
}


jQuery(document).ready(function(){
     jQuery.history.init(Collab.nav.pageload);
});    