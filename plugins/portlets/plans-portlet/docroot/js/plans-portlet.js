function portletPlansModifyMessageBoardsInnerPortlet() {
    // update iframe height to wrap entire document, this prevents from displaying additional scrollbar
    var frame = document.getElementById("portlet_discussion");
    var frameContent =  (frame.contentDocument) ? frame.contentDocument : frame.contentWindow.document;
    jQuery(frame).attr("scrolling", "no");
    var contentDocument = jQuery(frameContent);
    
    // remove browsing categories etc from discussion
    jQuery(frameContent._19_fm1).remove();
    contentDocument.find(".portlet-borderless-bar").remove();
    contentDocument.find(".tabs.ui-tabs").remove();

    // add search box like in the prototype
    searchBoxContainer = contentDocument.find("#_19_keywords2").parent();
    searchBoxContainer.html('<div class="searchInputBox"><input class="text" type="text"/>' + 
            '<a class="searchBtn" href="javascript:;" onClick="document._19_fm2.submit()" ><span class="hidden">Search</span></a></div>' + 
            '<a class="newThread" href="javascript:;" onClick="iframe_DeferUntilLogin(function () {_19_addMessage(); });"><span class="hidden">Post New Thread</span></a><div class="clear"/>');

    contentDocument.find("th.col-1").addClass("first");
    contentDocument.find("th.col-6").addClass("last");
    contentDocument.find("th.col-7").remove();
    contentDocument.find("th.col-6").attr("colspan", "2");

    contentDocument.find(".breadcrumbs .first").remove();

    var actionTags = contentDocument.find(".lfr-trigger"); 
    actionTags.find("strong").hide();
    actionTags.append('<img align="middle" alt="action" src="/collaboratorium-theme/images/aciont_icon.png"/><div>Action</div>');
    
    
    contentDocument.find(".page-selector").remove();
    contentDocument.find(".delta-selector").remove();
    contentDocument.find(".taglib-search-iterator-page-iterator-top").remove();
    
    
    frame.height = frameContent.body.scrollHeight + 50;
    jQuery(frame).css("overflow", "hidden");

    contentDocument.find("#banner").remove();
    jQuery("#portlet_discussion").load(portletPlansModifyMessageBoardsInnerPortlet);
}


var columns = [];
function drawColumnsConfigurationScreen() {
	if (columns.length > 0) {
		return;
	}
	var columnsVisibleList = jQuery(".columnsConfigurationWidget .columnsVisible");
	var columnsNotVisibleList = jQuery(".columnsConfigurationWidget .columnsNotVisible");
	jQuery(".columnConfig").each(function() {
		var columnIdx = columns.length;
		
		var column = {
				name: jQuery(this).find(".name").text(), 
				visible: jQuery(this).find(".trigger").attr("checked"), 
				trigger: jQuery(this).find(".trigger"),
				option: jQuery("<option value='" + columnIdx + "'>" + jQuery(this).find(".name").text() + "</option>"),
				id: columnIdx
				
		};
		columns.push(column);
		
		if (column.visible) {
			column.option.appendTo(columnsVisibleList);
		}
		else {
			column.option.appendTo(columnsNotVisibleList);
		}	
	});	
	
	jQuery(".columnsConfigurationWidget .add").click(function() {
		columnsNotVisibleList.find(":selected").each(function() {
			var column = columns[jQuery(this).val()];
			column.option.appendTo(columnsVisibleList);
			column.trigger.attr("checked", "true");
		});
	});
	
	jQuery(".columnsConfigurationWidget .remove").click(function() {
		columnsVisibleList.find(":selected").each(function() {
			var column = columns[jQuery(this).val()];
			column.option.appendTo(columnsNotVisibleList);
			column.trigger.removeAttr("checked");
		});
	});
}
function initFiltersWidget() {
	
	var inputs = [];
	
	function updateSliderInputs(input, values) {
		input.inputFrom.val(input.printValue(values[0]));
		input.inputTo.val(input.printValue(values[1]));
	}
	
	function printDate(timestamp, ignoreUnit) {
		var date = new Date();
		date.setTime(timestamp);
		return (date.getMonth() < 9 ? "0" : "") + (date.getMonth() + 1) + "-" + (date.getDate() < 10 ? "0" : "") + date.getDate() + "-" + date.getFullYear();
	}
	
	function parseDate(dateStr) {
		// date format MMM dd, yyyy
		return parseInt(Date.parse(dateStr));
	}
	
	function printPercent(percent, ignoreUnit) {
		return percent + (ignoreUnit ? "" : "%");
	}
	
	function parsePercent(percentStr) {
		return parseInt(percent.replace("%", ""));
	}

	function printNumber(number, ignoreUnit) {
		return number.toFixed(0);
	}
	
	function parseNumber(number) {
		return parseInt(number);
	}
	
	function parseValue(unit) {
		if (unit == "Date") {
			return parseDate;
		}
		else if (unit == "Integer") {
			return parseNumber;
		}
		else if (unit == "Percent") {
			return parsePercent;
		}
	}
	
	function printValue(unit) {
		if (unit == "Date") {
			return printDate;
		}
		else if (unit == "Integer") {
			return printNumber;
		}
		else if (unit == "Percent") {
			return printPercent;
		}
	}
	
	jQuery(".inputWithSlider").each(function() {
		var def = jQuery(this);
		var inputId = inputs.length;
		var input = {
				id: inputId,
				inputFrom: def.find(".inputFrom"),
				inputTo: def.find(".inputTo"),
				from: def.find(".inputFrom").val(),
				to: def.find(".inputTo").val(),
				min: def.find(".min").val(),
				max: def.find(".max").val(),
				step:  def.find(".step").val(),
				type: def.find(".type").val()
		};
		input.parseValue = parseValue(input.type);
		input.printValue = printValue(input.type);

		if (jQuery.trim(input.from).length == 0) {
			input.from = input.min;
		}
		if (jQuery.trim(input.to).length == 0) {
			input.to = input.max;
		}
		
		input.min = input.parseValue(input.min);
		input.max = input.parseValue(input.max);
		input.from = input.parseValue(input.from);
		input.to = input.parseValue(input.to);
		
		updateSliderInputs(input, [input.from, input.to]);
		
		
		inputs.push(input);
		input.slider = def.find(".slider").slider("destroy");
		input.slider = def.find(".slider").slider({
			range: true,
			min: input.min,
			max: input.max,
			values: [input.from, input.to],
			step: 1,
			slide: function(event, ui) { updateSliderInputs(input, ui.values) }
		});
		
		function updateSliderOnBlur(input) {
			// get current values
			var values = input.slider.slider("option", "values");
			
			try {
				// try to parse user entered value
				from = input.parseValue(input.inputFrom.val());
				to = input.parseValue(input.inputTo.val());
			}
			catch (e) {
				// ignore
				
			}
			if (isNaN(from)) {
				from = values[0];
			}
			if (isNaN(to)) {
				to = values[1];
			}
			if (from > to) {
				from = to;
			}
			if (to < from) {
				to = from;
			}
			input.slider.slider("option", "values", [from, to]);
			updateSliderInputs(input, [from, to]);
		}
		
		input.inputFrom.blur(function() { updateSliderOnBlur(input)});
		input.inputTo.blur(function() { updateSliderOnBlur(input)});
	});
}

function showConfigureColumnsDialog() {
	var dialog = jQuery("#configure_columns_dialog").dialog({width: 770, modal: true, height: 400, draggable: false, resizable: false, dialogClass: 'configureColumnsDialog'});
	dialog.find(".ui-dialog-titlebar").hide();
}

function closeDialog(id) {
	jQuery(id).dialog("close");
}

function updateColumns() {
	jQuery(".updateColumnsSubmit").click();
	jQuery("#configure_columns_dialog").dialog("close");
}

function showFilterPlansDialog() {
	var dialog = jQuery("#filterPlansDialog").dialog({width: 580, modal: true, height: 500, draggable: false, resizable: false, dialogClass: 'filterPlansDialog'});
}