jQuery(document).ready(function() {
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
	initSearchUpperBox();
});


function initSearchUpperBox() {
	jQuery("#searchinput").focus(function() {
		if (this.value == 'Search') {
			this.value = '';
		}
	});
	
	jQuery("#searchinput").blur(function() {
		if (jQuery.trim(this.value) == '') {
			this.value = 'Search';
		}
	});
	
	// submit on enter
	jQuery("#searchinput").keypress(function(e){
		if(e.which == 13){
			$('#searchsubmit').click();
	    }
	});

	
	
	
	jQuery("#searchsubmit").click(function() {
		var searchPhrase = escape(jQuery('#searchinput').val());
		window.location = "/web/guest/search#search=searchPhrase:" + searchPhrase;
	});
		
}