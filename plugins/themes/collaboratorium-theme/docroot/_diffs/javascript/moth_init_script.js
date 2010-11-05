


function initSearchUpperBox() {
	jQuery("#searchinput").focus(function() {
		if (jQuery(this).hasClass("nofocus")) {
			jQuery(this).toggleClass("nofocus");
		}
	});
	
	jQuery("#searchinput").blur(function() {
		if (jQuery.trim(this.value) == '' && !jQuery(this).hasClass("nofocus")) {
			jQuery(this).toggleClass("nofocus");
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
		window.location = "/web/guest/search#search=searchPhrase:" + searchPhrase;
	});
		
}

function deferUntilLogin(fn) {
 
    if (Liferay.ThemeDisplay.isSignedIn()) {
        return true;
    } else {
    	var loginregister = "/web/guest/loginregister?p_p_id=loginregister";
    	loginregister += "&redirect=" + escape(window.location);
    	window.location = loginregister;
    }
}

function deferUntilLoginTargeted(loc) {

    if (Liferay.ThemeDisplay.isSignedIn()) {
        window.location = loc;
    } else {
    	var loginregister = "/web/guest/loginregister?p_p_id=loginregister";
        if (loc!=null) {
          loginregister += "&redirect=" + escape(loc);
        } else {
    	    loginregister += "&redirect=" + escape(window.location);
        }
    	window.location = loginregister;
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
	if (jQuery('#boxwin').length > 0) {
		jQuery('#boxwin').cycle({ 
			fx:     'cover',
			speed:  'slow', 
			timeout: 0, 
			pager:  '#boxnav', 
				pagerAnchorBuilder: function(idx, slide) { 
					return '#boxnav li:eq(' + idx + ') a'; 
				}     
			}
		);
	}
	
	var footer = jQuery("#footmenu").next();
	footer.appendTo(jQuery("#foot_wrap"));
	
	jQuery('.close').click(function() { 
		jQuery('.chooseround li:eq(0) a').triggerHandler('click'); 
		return false; 
	});
	if (jQuery('.selectbox1').length > 0) {
		var selectboxOnChange = jQuery('.selectbox1').get(0).getAttribute("onchange");

		jQuery('.selectbox1').selectbox({
			inputClass: 'selectbox',
			onChangeCallback: function () {
				jQuery(".selectbox1").change();
				
				}
		});
	}
	

    setTimeout(function() {
      jQuery("div.contestPhaseInfo:first .details h3").text("Round 1 completed, voting begins Nov. 1  (29 final proposals)");  
    },1000);
});