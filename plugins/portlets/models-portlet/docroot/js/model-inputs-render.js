/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

function initEditForms() {
	/* Inputs ordering */
	if (!jQuery("#inputsOrder").length) {
    jQuery(".subInputsOrder").each(function() {
    	var ul = jQuery(this);
    	var itemsContainer = ul.next();
    	ul.html(itemsContainer.html());
    	itemsContainer.remove();
    });

    var inputsForOrdering = jQuery(".inputOrderDef").html();
    jQuery(".inputOrderDef").html('<ul id="inputsOrder" class="inputsOrder">' + inputsForOrdering + "</ul>");
    
    jQuery("#inputsOrder, .subInputsOrder").sortable({stop: function() {
    
    	var counter = 0; 
    	jQuery(".singleInputOrderDef input").each(function() {
    		this.value = counter++;
    	});
    }});
	}
	
	/* Inputs grouping */
	if (! jQuery(".availableGroupItemsa").length) {
		
		jQuery(".availableItemsCell .listItems").each(function() {
			//var container = jQuery(this);
			//ul.html("");
			//container.html("<ul class='availableGroupItems ui-widget-content ui-corner-all'>" + container.html() + "</ul>");
			
			//log.debug("creating available items ");
		});
		
		jQuery(".inputGroup .listItems").each(function() {
			//var container = jQuery(this);
			//var parentId = container.attr("class").replace(/[^\d]*/, "");
			//container.append("<ul class='groupedInputs ui-widget-header' title='" + parentId + "'>" + container.html() + "</ul>");

			//log.debug("creating grouped items ");
			
		});
		
		//, .inputGroup
		jQuery(".individualInput").each(function(){
			var parentId = 0;
			var input = jQuery(this);
			if (input.parent().hasClass("groupedInputs")) {
				parentId = input.parent().attr("title");
			}
			input.find("input").val(parentId);
		});
		
		jQuery(".individualInput").draggable({helper: "original"});
		jQuery(".groupedInputs, .availableGroupItems").droppable({
			activeClass: 'ui-state-highlight',
			drop: function(event, ui) {
				jQuery(this).find(".individualInputPlaceholder").remove();
				ui.draggable.css("top", null);
				ui.draggable.css("left", null);
				ui.draggable.appendTo(this);

				// input has been added to concrete group, set its input to group id
				var parentId = -1;
				try {
				if (ui.draggable.parent().hasClass("groupedInputs")) {
					parentId = ui.draggable.parent().attr("title");
				}
				ui.draggable.find("input").val(parentId);
				}
				catch (e) {
					log.error(e);
				}
				jQuery(this).append("<li class='individualInputPlaceholder'></li>");
				//ui.draggable.draggable();
				
		}});

		
	}
	
	if (true) {

	    //var inputsForOrdering = jQuery(".inputOrderDef").html();
	    //jQuery(".inputOrderDef").html('<ul id="inputsOrder" class="inputsOrder">' + inputsForOrdering + "</ul>");
	    function updateItemsOrder() {
	    	var counter = 0; 
	    	jQuery(".orderList li input").each(function() {
	    		this.value = counter++;
	    	});
	    }
	    	
	    jQuery(".orderList, .orderSubList").sortable({stop: function() {
	    	updateItemsOrder();
	    }});
	    
	    updateItemsOrder();
	}
    
}
