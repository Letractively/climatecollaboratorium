function initSearchUpperBox() {
	jQuery("#searchinput").focus(function() {
		if (jQuery(this).hasClass("nofocus")) {
			jQuery(this).val('');
			jQuery(this).toggleClass("nofocus");
		}
	});
	
	jQuery("#searchinput").blur(function() {
		if (jQuery.trim(this.value) == '' && !jQuery(this).hasClass("nofocus")) {
			jQuery(this).toggleClass("nofocus");
		}
		if (jQuery(this).hasClass('nofocus')) {
			jQuery(this).val('Search');
		}
	});
	
	// submit on enter
	jQuery("#searchinput").keypress(function(e){
		if(e.which == 13){
			jQuery('#searchsubmit').click();
			return false;
	    }
	});

	
	
	
	jQuery("#searchsubmit").click(function() {
		var searchPhrase = escape(jQuery('#searchinput').val());
		if (searchPhrase == 'Search') {
			searchPhrase = '';
		}
		window.location = "/web/guest/search#search=searchPhrase:" + searchPhrase;
	});
		
}

function deferUntilLogin(fn) {
 
    if (Liferay.ThemeDisplay.isSignedIn()) {
        return true;
    } else {
    	jQuery('.popup_login').show();
    	jQuery.scrollTo(jQuery(".popup_login"));
    	//var loginregister = "/web/guest/loginregister?p_p_id=loginregister";
    	//loginregister += "&redirect=" + escape(window.location);
    	//window.location = loginregister;
    }
}

function showForgotPasswordPopup() {
	jQuery('.popup_login').hide();
	jQuery('.popup_forgotpassword').show();
}

function deferUntilLoginTargeted(loc) {

    if (Liferay.ThemeDisplay.isSignedIn()) {
        window.location = loc;
    } else {
    	jQuery('.popup_login').show();
    	jQuery.scrollTo(jQuery(".popup_login"));
    	/*
    	var loginregister = "/web/guest/loginregister?p_p_id=loginregister";
        if (loc!=null) {
          loginregister += "&redirect=" + escape(loc);
        } else {
    	    loginregister += "&redirect=" + escape(window.location);
        }
    	window.location = loginregister;
    	*/
    }

}


function insertParam(key, value)
{
    key = escape(key); value = escape(value);

    var kvp = document.location.search.substr(1).split('&');

    if (kvp.length==1 && kvp[0].length==0) {
      kvp=[];
    }
    var i=kvp.length; var x; while(i--)
    {
        x = kvp[i].split('=');

        if (x[0]==key)
        {
                x[1] = value;
                kvp[i] = x.join('=');
                break;
        }
    }

    if(i<0) {kvp[kvp.length] = [key,value].join('=');}
    //this will reload the page, it's likely better to store this until finished
    document.location.search = kvp.length>1?kvp.join('&'):kvp[0];
}

jQuery(document).ready(function() {
	initSearchUpperBox();
	
	if (jQuery(".hp_boxwin").length > 0) {
		jQuery('.hp_boxwin').cycle({
		    fx:      'custom',
		    cssBefore: {  
		        top:  -365,  
		        left: 0,  
		                width: 540,
		                height:335,
		        opacity: 0
		    }, 
		    animIn: {  
		        top: 0,  
		        left: 0,  
		                width: 540,
		                height:335,
		        opacity: 1
		    }, 
		    animOut: {  
		        top:  350,  
		        left: 0,  
		        opacity: 0
		    }, 
		            timeout: 0,
		            pager:  '.hp_boxnav',
		            pagerAnchorBuilder: function(idx, slide) {
		                    // return selector string for existing anchor 
		                    return '.hp_boxnav li:eq(' + idx + ') a';
		                }
		        });
		
	}
	

	//jQuery('.popup_reg').hide();  
		jQuery('.openreg').click(function() {  
			jQuery('.popup_reg').fadeIn(300);
			jQuery('#content').fadeOut(300);
			jQuery('#foot_wrap').fadeOut(300);
			jQuery('.hp_box').fadeOut(300);
		}); 
		jQuery('.closereg').click(function() {  
			jQuery('.popup_reg').fadeOut(300);  
			jQuery('#content').fadeIn(300);
			jQuery('#foot_wrap').fadeIn(300);
			jQuery('.hp_box').fadeIn(300);
		});
	
	var footer = jQuery("#footmenu").next();
	footer.appendTo(jQuery("#foot_wrap"));
	
	jQuery('.close').click(function() { 
		jQuery('.chooseround li:eq(0) a').triggerHandler('click'); 
		return false; 
	});

    setTimeout(function() {
      jQuery("div.contestPhaseInfo:first .details h3").text("Round 1 completed, voting begins Nov. 1  (29 final proposals)");  
    },1000);
    
    
    jQuery(".closepopuplogin a").click(function() {
    	jQuery(".popup_login_form .error-message").remove();
    	jQuery(".popup_login_form .popup_login-message").show();
    	jQuery('.popup_login, .popup_forgotpassword').hide();
    });
    
    
    jQuery("#loginPopupCreateAccount").click(function() {
    	jQuery('.popup_login').hide();
    	
    	/* show registration form */
		jQuery('.popup_reg').fadeIn(300);
		jQuery('#content').fadeOut(300);
		jQuery('#foot_wrap').fadeOut(300);
		jQuery('.hp_box').fadeOut(300);
    });
    
    jQuery("#hdr_signin").mouseover(function() {
    	jQuery("#hdr_signin").show();
    });
    
    jQuery("#hdr_signin #signInTopForm").mouseover(function() {
    	jQuery("#hdr_signin").addClass("inputFocus");
    });
    
    jQuery("#hdr_signin #signInTopForm").mouseout(function() {
    	jQuery("#hdr_signin").removeClass("inputFocus");
    });
    
    jQuery("#hdr_signin").mouseout(function(event) {
    	console.log(event);
    	// get hdr_signin height/width
    	var offset = jQuery('#hdr_signin').offset();
    	var left = offset.left;
    	var top = offset.top;
    	var bottom = top + jQuery('#hdr_signin').height();
    	var right = left + jQuery('#hdr_signin').width();
    	var pageX = event.pageX;
    	var pageY = event.pageY;
    	
    	if (left <= pageX && pageX <= right && top <= pageY && pageY <= bottom) {
    		return;
    	}
    	jQuery("#hdr_signin").hide();
    });
});

function initSelectbox() {
	if (jQuery('.selectbox1, .selectbox1-dis-dis').length > 0) {
		var selectboxOnChange = jQuery('.selectbox1').get(0).getAttribute("onchange");

		jQuery('.selectbox1').selectbox({
			inputClass: 'selectbox',
			onChangeCallback: function () {
				jQuery(".selectbox1").change();
				
				}
		});
	}
}

function onBeforeRegister() {
	jQuery('#createAccountForm').append(jQuery('<input type="hidden" value="' + window.location.toString() + '" name="redirect" />'));
}

function onBeforeLogin(formId) {
	jQuery('#' + formId).append(jQuery('<input type="hidden" value="' + window.location.toString() + '" name="redirect" />'));
}

function addRedirectBeforeSubmit(formId) {
	jQuery('#' + formId).append(jQuery('<input type="hidden" value="' + window.location.toString() + '" name="redirect" />'));
}

function processForgotPasswordForm(formId) {
	var screenName = jQuery('#' + formId + ' .screenName').val();
	if (screenName.indexOf('@') > 0) {
		jQuery('#' + formId).append('<input type="hidden" value="' + screenName + '" name="emailAddress" />');
	}
}


function updateBreadcrumb(placeholder, items) {
    var breadcrumb = [];
    for (var i = 0; i < items.length; i++) {
    	var item = items[i];
        breadcrumb[2*i] = '<img width="8" height="8" alt="" src="/collaboratorium-theme/images/arrow.gif" /> ';
        breadcrumb[2*i + 1] = '<a href="' + item.href + '" onclick="' + item.onclick + '; return false;">' + item.text + '</a>';
    }

    jQuery(placeholder).html(breadcrumb.join(''));
}

