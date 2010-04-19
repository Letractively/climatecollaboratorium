/* 
 * Copyright (C) 2009 TopCoder Inc., All Rights Reserved.
 *
 * Base JavaScript for collaboratorium theme 
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */


jQuery.noConflict(); 

jQuery(document).ready(function(){
	// z-index fix
	<!--[if lte IE 7 ]>
	var browserName=navigator.appName; 
	var browserVer=parseInt(navigator.appVersion); 
	
	if (browserName=="Microsoft Internet Explorer" && browserVer <= 7) {
		var zIndexNumber = 1000;
		jQuery('div').each(function() {
			jQuery(this).css('zIndex', zIndexNumber);
			zIndexNumber -= 1;
		});
	}

	
	positionModal = function() {
        var wWidth = window.innerWidth;
        var wHeight = window.innerHeight;

        if (wWidth == undefined) {
            wWidth = document.documentElement.clientWidth;
            wHeight = document.documentElement.clientHeight;
        }

        var boxLeft = parseInt((wWidth / 2) - (jQuery("#modal").width() / 2));
        var boxTop = parseInt((wHeight / 2) - (jQuery("#modal").height() / 2));


        // position modal
        jQuery("#modal").css({
            'margin': boxTop + 'px auto 0 ' + boxLeft + 'px'
        });

        jQuery('#modalHeadLeft h2').width(jQuery("#modal").width() - 60 + 'px');
        jQuery('#modalFoot').width(jQuery("#modal").width() - 36 + 'px');


        // scroll to top
        //jQuery('html').animate({ scrollTop: 0 }, 'slow');
    }
	loadModal = function(url) {
        jQuery("body").append('<div id="modalBackground"></div>');
        jQuery("#modalBackground").css("opacity", "0.6");
        if (jQuery("body").height() > jQuery("#modalBackground").height())
            jQuery("#modalBackground").css("height", jQuery("body").height() + "px");

        jQuery("body").append('<div id="modalcontainer"></div>');
        jQuery("#modalcontainer").append('<div id="modal"></div>');

        jQuery("#modal").hide;

        $.ajax({
            url: url,
            success: function(html) {
                jQuery("#modal")
					.html(html)
					.fadeIn('slow');

                // create the close button
                positionModal();
            },
            error: function() {
                alert("Error loading page");
                closeModal();
            }
        });

    }
	
	showModal = function(modalId) {
		jQuery("body").append('<div id="modalBackground"></div>');
        jQuery("#modalBackground").css("opacity", "0.6");
        if (jQuery("body").height() > jQuery("#modalBackground").height())
            jQuery("#modalBackground").css("height", jQuery("body").height() + "px");

        jQuery("body").append('<div id="modalcontainer"></div>');
        jQuery("#modalcontainer").append('<div id="modal"></div>');

        jQuery("#modal").html(jQuery(modalId).html()).fadeIn('slow');

        // create the close button
        positionModal();
	}

	closeModal = function() {
        jQuery("#modalcontainer").remove();
        jQuery("#modalBackground").remove();
    }
	
	
	jQuery(".closeModal").bind('click', function() {
        closeModal();
    });

	// login
	jQuery('#registerLink').click(function(){
		jQuery('#register').slideToggle();
		jQuery('#login').slideUp();
		jQuery('#availableLink').hide();
		jQuery('#login_link').removeClass('current');
	});
	jQuery('.cancelBtn').click(function(){
		jQuery('#register').slideUp();
		jQuery('#login').slideUp();
		jQuery('#login_link').removeClass('current');
	});

	jQuery('#login_link').click(function(){
		jQuery('#login').slideDown();
		jQuery('#register').slideUp();
		jQuery('#login_link').addClass('current');
	});
	
	//folder & unfloder
	jQuery('.lv1 a').click(function(){
		if(jQuery(this).attr('class') == 'folder'){
			jQuery(this).parent().find("ul").slideUp();
			jQuery(this).attr('class','unfolder');
		}
		else if(($this).attr('class') == 'unfolder') {
			jQuery(this).parent().find("ul").slideDown();
			jQuery(this).attr('class','folder');
		}
	});
	
	jQuery('#modelFloder').click(function(){
		if(jQuery(this).attr('class') == 'folder'){
			jQuery('#holderContent').hide();
			jQuery(this).attr('class','unfolder');
		}
		else{
			jQuery('#holderContent').show();
			jQuery(this).attr('class','folder');
		}
	});
	jQuery('#madelDetailFolder .folderTab a').click(function(){
		if(jQuery(this).attr('class') == 'folder'){
			jQuery(this).parent().parent().find(".tabContent").slideUp();
		}
		else{
			jQuery(this).attr('class','folder');
			jQuery(this).parent().parent().find(".tabContent").slideDown();
		}
	});
	
	//initial nav behavior
	jQuery("#mainNav .hasChildren").hover(function() {
		 jQuery("#childNav").show();
		 jQuery("#childNav span").show();
		 var pngFixElements = "#childNav span, #childNav li";
		 jQuery(pngFixElements).css('behavior','url(/collaboratorium-theme/css/iepngfix.htc)');
	},function() {
		 jQuery("#childNav").hide();
	});
	jQuery("#childNav").hover(function() {
	     jQuery("#childNav").show();
		 jQuery("#childNav span").show();
	},function() {
	     jQuery("#childNav").hide();
	});

	
	
	
	
	var pngFixElements = "span, h1, h2, h3, a, li,  .profile .header-profile, .profile .header-profile .title" + 
		", .social-activity-wall .name, #login, .userRegister_top, .userRegister_bg, .userRegister_bottom";
	jQuery(pngFixElements).css('behavior','url(/collaboratorium-theme/css/iepngfix.htc)');
	
	
	var pngFixDisabled = "#childNav span, #childNav li, #physical-impacts-tabs li, #physical-impacts-tabs li.ui-state-active";

	jQuery(pngFixDisabled).css('behavior', null);
	//jQuery('div.position-top, div.position-bg, div.position-bottom, img, h1, span, h2, h3, a, .profile .header-profile, .profile .header-profile .title, .social-activity-wall .title,.social-activity-wall .title .name, #mainNav ul li').css('behavior','url(/collaboratorium-theme/css/iepngfix.htc)');
	
});
// JavaScript Document
