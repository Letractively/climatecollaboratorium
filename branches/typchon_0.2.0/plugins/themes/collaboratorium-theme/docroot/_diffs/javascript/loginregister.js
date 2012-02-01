/**
 * Modifies login/register links by adding appropriate "redirect" parameter
 * @param anchor anchor that should be modified
 * @return void
 */
function modifyLoginRegisterURL(anchor) {
    var location = anchor.href;
    if (window.location.toString().indexOf("loginregister") < 0) {
        // modify url only when not on login/register page
        anchor.href = location + "&redirect=" + escape(window.location);
    }
}


function clickFacebookButton() {
    if (Get_Cookie("fbs_"+FB_APP_ID)) {
        insertParam("fbEvent", "true");
    } else {
        jQuery("a.fb_button").not("#pseudo-fb").click();
    }
}


function deleteFBCookie() {
   Delete_Cookie("fbs_"+FB_APP_ID); 
}

function clickTwitterButton() {
	var popup = window.open("/twitter/signIn", "Sign in with Twitter", "location=1,status=1,scrollbars=1,width=500,height=400,locationbar=0");
	       
	window.twitterSignIn = function(status) {
		if (status) {
			popup.close();
			window.location.reload(true);
		}
	}
}


