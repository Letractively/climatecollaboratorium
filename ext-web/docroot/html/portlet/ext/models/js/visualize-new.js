/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

var useLocal = false;

var scenXml;
var dataSets = null;
var dataSetNames = null;
var seriesToGraph = null;
//if the data set is indexed or not
var dataSetIndexed = null;

var currPlot = null;

//Customized options
var chartStyle = null;
var legendOn = true;
var title = null;
var xAxisLabel = null;
var yAxisLabel = null;
var xAxisStyle = "none";
var yAxisStyle = "none";

var servResponse = null;

//for save data
var variableIds;

function buildUrl(url) {
    //it is in the same domain/port
    return "/"+url;
}

function start()
{
	getScenarioXml();
}

function getScenarioXml()
{
	if(useLocal) {
		var xml = loadXMLDoc("1120.xml");
		processData(xml);
		return false;
	}
	
	var scenUrl = buildUrl("simulation-servlet/rest/scenario/"+scenarioId);
	
	$.ajax({
	    url: scenUrl,
	    type: 'GET',
	    dataType: 'xml',
	    error: function(){
	        alert('Error loading scenario');
	    },
	    success: function(data){
	    	scenXml = data;
	    	processData(data);
	    	getSeriesNames(data);
	    }
	}); 
}

function processData(xml) 
{
	var linesToGraph = [];
	
	//variableIds is a global
	variableIds = [];
	
	$(xml).find("outputs").each(function()
	{
		$(this).find("variable").each(function () 
		{
			variableIds.push($(this).attr("id"));
		});
		
		$(this).find("data").each(function()
		{
			var regex = /\]\[/g;	// "]["
			var currSeries = $(this).text();
			currSeries = currSeries.substring(2, currSeries.length-2);
			
			var seriesArray = currSeries.split(regex);
			var polishedSeriesArray = [];
			
			for(var i=0; i < seriesArray.length; i++) {
				var dataPair = seriesArray[i].split(",");
				var x = parseFloat(dataPair[0]);
				var y = parseFloat(dataPair[1]);
				polishedSeriesArray.push([x,y]);
			}
			linesToGraph.push(polishedSeriesArray);
		});	
	});
	
	//Set globals
	dataSets = linesToGraph;
	chartStyle = "Line";

	$("#choicesHeader").html("<strong>Show</strong>");
	addSelectAllLinks();	
	
	//test
	if(useLocal) {	    
		drawCheckBoxes(dataSets);
	
		drawChartTypeButtons();
		displayCustomChartOptions('Line');
	
		updateSeriesToGraph();
		createChart();
	}
}

function getSeriesNames(xmlDoc) 
{
	var simID = $(xmlDoc).find("simulation").find("id").text();
	
	//adjust detail link
	var newURL = $("#detailLink").attr("href")+"&"+portletNameSpace+"sim="+simID+"&"+portletNameSpace+"scenarioId="+scenarioId;
	$("#detailLink").attr("href",newURL);
		
	var simURL = buildUrl("simulation-servlet/rest/simulation/" + simID);

	var index = 0;
	var simXmlDoc;
	
	$.ajax({
	    url: simURL,
	    type: 'GET',
	    dataType: 'xml',
	    //timeout: 1000,
	    error: function(){
	        alert('Error getting series names');
	    },
	    success: function(xml){
	    	dataSetNames = [];
	    	dataSetIndexed  = [];
	    	$(xml).find("outputs").each(function() 
	    	{	
	    		$(this).find("metadata").each(function()
	    		{
	    			var name= $(this).find("internalname").text();
	    			dataSetNames.push(name);
	    			
	    			//confirmed by PM: only for indexed metadata
	    			var indexValue = $(this).attr("varcontext");
	    			if(indexValue == 'INDEXED'){
	    			   dataSetIndexed.push(true);
	    			} else {
	    			   dataSetIndexed.push(false);
	    			}
	    		});
	    	}); 
	    },
	    complete: function(){	     
	    	var anyIndexedDataSet = drawCheckBoxes(dataSets);
	    	if(!anyIndexedDataSet){
	    	    return;
	    	}
	    	
	    	drawChartTypeButtons();
	    
	    	displayCustomChartOptions('Line');
	    	
	    	updateSeriesToGraph();
	    	createChart();
	    }
	});
}

function addSelectAllLinks()
{
	$("#choicesHeader").append(' ');
	$("#choicesHeader").append('<a rel="choices" href="#select_all">All</a>');
	$("#choicesHeader").append(' ');
	$("#choicesHeader").append('<a rel="choices" href="#select_none">None</a>');
	
	//Source: http://abeautifulsite.net/notebook/50
	
	// Select all
    $("A[href='#select_all']").click( function() {
        $("#choices" + " INPUT[type='checkbox']").attr('checked', true);
        updateSeriesToGraph();
        return false;
    });
   
    // Select none
    $("A[href='#select_none']").click( function() {
        $("#choices" + " INPUT[type='checkbox']").attr('checked', false);
        updateSeriesToGraph();
        return false;
    });
}

function drawCheckBoxes(linesToGraph)
{
	var num = linesToGraph.length;
	var currName;
    var countIndexedDataSets = 0;
     
	for(var i = 0; i < num; i ++) {
	    //Only show indexed dataset
	    if(!dataSetIndexed[i]){
	        continue;
	    }
	    
	    countIndexedDataSets++;
	    
		if(dataSetNames != null)
			currName=dataSetNames[i];
		else
			currName="Series "+i;
		
		$("#choices").append('<div class="seriesChoice"><input type="checkbox" class="seriesCheckbox" id="'+i+'" name="'+currName+'"></input><label for="'+i+'">'+currName+'</label></div>');
		
		//add colorpicker <span> target
		//$("#choices").append('<span id="picker'+i+'" class="colorpicker"></span>');
	}
	$("#choices").find("input").click(updateSeriesToGraph);
	
	if(countIndexedDataSets == 0) {
	    $("#choices").append("No indexed metadata");
	    $('#chartdiv').html('<div id="begin-msg">No indexed medata/dataset could be found for visulization.</div>');
	    return false;
	} else {
	    return true;
	}
	
}

function drawChartTypeButtons()
{
	$("#chartTypes").html('<input type="radio" class=chartType name="chartType" value="Line" checked />Line ');
	$("#chartTypes").append('<input type="radio" class=chartType name="chartType" value="Bar" />Bar ');
	$("#chartTypes").append('<input type="radio" class=chartType name="chartType" value="Pie" />Pie ');
	
	$("#chartTypes").find("input").click(updateTypeOfChart);
}

function displayCustomChartOptions(style)
{		
	if(style == "Line")
		displayLineOptions();
	else if(style == "Pie")
		displayPieOptions();
	else if(style == "Bar")
		displayBarOptions();
}

	function displayLineOptions()
{
	$('#chartSpecificOptions').empty();

	//set default x-renderer as "numeric"
	var xAxisMenu=document.getElementById("xRenderer");
	xAxisMenu.selectedIndex = 0;
	xAxisStyle = "none";
	
	//markers
	var markerMenu = '<form id="markerMenu"><select name="markerOption">'+
						'<option value="filledCircle">Circle (filled)</option>'+
						'<option value="circle">Circle</options>'+
						'<option value="filledDiamond">Diamond (filled)</option>'+
						'<option value="diamond">Diamond</options>'+
						'<option value="filledSquare">Square (filled)</options'+
						'<option value="square">Square</options>'+
						'<option value="none">none</options>'+
						'</select></form>';
	$('#chartSpecificOptions').append("<div class='single-option'>Marker Style "+markerMenu +"</div>");
	
	//show line
	$('#chartSpecificOptions').append('<div class="single-option"><label for="showLine">Show Line</label><input type="checkbox" checked="checked" id="showLine"></input></div>');
	
	//line width
	addLineWidthOption();

	addStackSeriesOption();
	addFillAreaOption();
}
		function addLineWidthOption()
		{
			$('#chartSpecificOptions').append('<div class="single-option">Line Width: <label for="lineWidthAuto">Use Auto</label><input type="checkbox" checked="checked" id="lineWidthAuto"></input><div class="slider" id="lineWidthSlider"></div></div>');
			
			$('#lineWidthAuto').click(function() {
				if(this.checked == true)
					$('#lineWidthSlider').slider('disable');
				else
					$('#lineWidthSlider').slider('enable');
			});
			$('#lineWidthSlider').slider({max: 10, min: 0, step: 0.5});
			$('#lineWidthSlider').slider('disable');
		}
		function addStackSeriesOption()
		{
			$('#chartSpecificOptions').append('<div class="single-option"><label for="stackSeries">Stack Series</label><input type="checkbox" id="stackSeries"></input></div>');
		}
		
		function addFillAreaOption()
		{
			$('#chartSpecificOptions').append('<div class="single-option"><label for="fillArea">Fill Area</label><input type="checkbox" id="fillArea"></input></div>');
		}
	
	
	function displayPieOptions()
	{
		$('#chartSpecificOptions').empty();
	
		addPieDiameterOption();
		addSliceMarginOption();
		addLineWidthOption();
		addFillAreaOption();

		$('#fillArea').attr('checked','checked');
	}
		function addPieDiameterOption()
		{
			$('#chartSpecificOptions').append('Chart Diameter: <label for="diamDefault">Use Auto</label><input type="checkbox" checked="true" id="diamDefault"></input><div class="slider" id="diamSlider"></div><br />');
			$('#diamSlider').slider({ max: 500, min: 100, step: 5})
			$('#diamSlider').slider('disable');
			
			$('#diamDefault').click(function() {
				if(this.checked == true)
					$('#diamSlider').slider('disable');
				else
					$('#diamSlider').slider('enable');
			});
		}
	
		function addSliceMarginOption()
		{
			$('#chartSpecificOptions').append('Slice Margin: <label for="sliceMarginDefault">Use Auto</label><input type="checkbox" checked="true" id="sliceMarginDefault"></input><div class="slider" id="sliceMarginSlider"></div><br />');
			
			$('#sliceMarginSlider').slider({ max: 30, min: 0})
			$('#sliceMarginSlider').slider('disable');
			
			$('#sliceMarginDefault').click(function() {
				if(this.checked == true)
					$('#sliceMarginSlider').slider('disable');
				else
					$('#sliceMarginSlider').slider('enable');
			});
			
		}

	function displayBarOptions()
{
	$('#chartSpecificOptions').empty();
	
	//sets the default x-axis renderer to "category", since bar charts almost always
	//look better with this option
	var xAxisMenu=document.getElementById("xRenderer");
	xAxisMenu.selectedIndex = 2;
	xAxisStyle = "category";
	
	//bar width
	$('#chartSpecificOptions').append('Bar Width: <label for="widthDefault">Use Auto</label><input type="checkbox" checked="true" id="widthDefault"></input><div class="slider" id="barWidthSlider"></div><br />');

	$('#barWidthSlider').slider({ max: 50, min: 0})
	$('#barWidthSlider').slider('disable');
	
	$('#widthDefault').click(function() {
		if(this.checked == true)
			$('#barWidthSlider').slider('disable');
		else
			$('#barWidthSlider').slider('enable');
	});
	
	
	//bar padding
	$('#chartSpecificOptions').append('Bar Padding: <label for="paddingDefault">Use Auto</label><input type="checkbox" checked="true" id="paddingDefault"></input><div class="slider" id="barPaddingSlider"></div><br />');
	
	$('#barPaddingSlider').slider({ max: 50, min: 0})
	$('#barPaddingSlider').slider('disable');
	
	$('#paddingDefault').click(function () {
		if(this.checked == true)
			$('#barPaddingSlider').slider('disable');
		else
			$('#barPaddingSlider').slider('enable');
	});
	
	
	//bar margin
	$('#chartSpecificOptions').append('Bar Margin: <label for="marginDefault">Use Auto</label><input type="checkbox" checked="true" id="marginDefault"></input><div class="slider" id="barMarginSlider"></div><br />');

	$('#barMarginSlider').slider({ max: 50, min: 0});
	$('#barMarginSlider').slider('disable');
	
	$('#marginDefault').click(function() {
		if(this.checked == true)
			$('#barMarginSlider').slider('disable');
		else
			$('#barMarginSlider').slider('enable');
	});
	

	//horizontal bars
	$('#chartSpecificOptions').append('<label for="orientHorizontal">Horizontal Bars</label><input type="checkbox" id="orientHorizontal"></input>');

	addStackSeriesOption();
}

function createChart()
{
	//base options
	var options = {
			title: {
				textAlign:"center",
				},
			highlighter: {
		        sizeAdjust: 8,
		    	},	
			cursor: {
		    	zoom: true,
		    	showTooltip: false,
		    	clickReset: false,
		    	dblClickReset: true,
				},
		    seriesDefaults: {
					fill: false,
					rendererOptions: {},
					markerOptions: {},
				},
		    axes: {
		    	xaxis: {
		    		tickOptions: {},
		    	},
		    	yaxis: {},
		    },
		    stackSeries: false,
		    
	};
	
	//set chart type
	if(chartStyle == "Bar") 
		options = barChartOptions(options);
	else if(chartStyle == "Pie")
		options = pieChartOptions(options);
	else 	//line
		options = lineChartOptions(options);

		
	//set title
	if(title != null) {
		options.title.text = title;
		options.title.show = true;
	}
	
	//axis labels
	if(xAxisLabel != null) 
		options = setXLabel(options);
	if(yAxisLabel != null) 
		options = setYLabel(options);
	
	//draw the new chart
	$("#chartdiv").empty();
	
	if(seriesToGraph.length == 0) {
		drawEmpty();
		currPlot = null;
		return;
	}
		
	currPlot = $.jqplot('chartdiv', seriesToGraph, options);
	
	if(legendOn) {
		setLegend();
		$("#chartdiv").html("");
		currPlot.redraw();
	}
}
	function drawEmpty()
	{
		$('#chartdiv').html('<div id="begin-msg">To begin graphing, select a series on the left</div>');

	}
	
	function barChartOptions(options)
	{
		options.seriesDefaults.renderer = $.jqplot.BarRenderer;
		
		//set x-axis style
		if(xAxisStyle == "category")
			options.axes.xaxis.renderer = $.jqplot.CategoryAxisRenderer;
		else if(xAxisStyle == "log")
			options.axes.xaxis.renderer = $.jqplot.LogAxisRenderer;
		else if(xAxisStyle == "date") {
			options.axes.xaxis.renderer = $.jqplot.DateAxisRenderer;
			options.axes.xaxis.tickOptions.formatString = "%Y";
		}
		
		//set y-axis style
		if(yAxisStyle == "log")
			options.axes.yaxis.renderer = $.jqplot.LogAxisRenderer;
		
		//get width
		if($('#widthDefault').attr('checked') == false) 
			options.seriesDefaults.rendererOptions.barWidth = $('#barWidthSlider').slider('option', 'value');
		
		//get padding
		if($('#paddingDefault').attr('checked') == false) 
			options.seriesDefaults.rendererOptions.barPadding = $('#barPaddingSlider').slider('option', 'value');
		
		//get bar margins
		if($('#marginDefault').attr('checked') == false)
			options.seriesDefaults.rendererOptions.barMargin = $('#barMarginSlider').slider('option', 'value');

		//determine orientation
		if($('#orientHorizontal').attr('checked') == true) 
			options.seriesDefaults.rendererOptions.barDirection = 'horizontal';
		
		options = getStackSeriesOption(options);
		
		return options;
	}

	function lineChartOptions(options)
	{
		//set marker
		$("#chartSpecificOptions option:selected").each(function () {
			var markerStyle = $(this).attr('value');
			if(markerStyle == "none") {
				options.seriesDefaults.showMarker = false;
				return;
			}
			options.seriesDefaults.markerOptions.style = markerStyle;
			return;
		});
		
		//show line
		if($('#showLine').attr('checked') == false)
			options.seriesDefaults.showLine = false;
		else {
			if($('#lineWidthAuto').attr('checked') == false)
				options.seriesDefaults.lineWidth = $('#lineWidthSlider').slider('option', 'value');
		}
		
		//set x-axis style
		if(xAxisStyle == "category")
			options.axes.xaxis.renderer = $.jqplot.CategoryAxisRenderer;
		else if(xAxisStyle == "log")
			options.axes.xaxis.renderer = $.jqplot.LogAxisRenderer;
		else if(xAxisStyle == "date") {
			options.axes.xaxis.renderer= $.jqplot.DateAxisRenderer;
			options.axes.xaxis.tickOptions.formatString = "%d";
		}
		
		//set y-axis style
		if(yAxisStyle == "log")
			options.axes.yaxis.renderer = $.jqplot.LogAxisRenderer;
		
		//fill area
		if($('#fillArea').attr('checked') == true) {
			options.seriesDefaults.fill = true;
			//if showLine = false, nothing is displayed, so it must be manually set to true
			options.seriesDefaults.showLine = true;
		}
			
		options = getStackSeriesOption(options);
		
		return options;
	}
		
		function getStackSeriesOption(options)
		{
			//stack series
			if($('#stackSeries').attr('checked') == true)
				options.stackSeries = true;
		
			return options;
		}
		
	function pieChartOptions(options)
	{
		options.seriesDefaults.renderer = $.jqplot.PieRenderer;
		
		//get diameter
		if($('#diamDefault').attr('checked') == false)
			options.seriesDefaults.rendererOptions.diameter= $('#diamSlider').slider('option', 'value');
					
		//get slice margin
		if($('#sliceMarginDefault').attr('checked') == false) 
			options.seriesDefaults.rendererOptions.sliceMargin = $('#sliceMarginSlider').slider('option', 'value');
				
		//get line width
		if($('#lineWidthAuto').attr('checked') == false)
			options.seriesDefaults.rendererOptions.lineWidth = $('#lineWidthSlider').slider('option', 'value');
		
		//get fill area
		if($('#fillArea').attr('checked') == false) {
			options.seriesDefaults.rendererOptions.fill = false;
			options.seriesDefaults.rendererOptions.shadow = false;
		}
		
		return options;
	}
	
	function setLegend()
	{
		var index = 0;
		 $("#choices").find("input:checked").each(function () {
			 var currId = $(this).attr("id");
			 var currName;
			 if(dataSetNames == null)
				 currName = "Series " + currId;
			 else
				 currName = dataSetNames[currId];
			 
			 
			 currPlot.series[index].label=currName;
			 index++;
		 });
		 currPlot.legend.show = true;
	}
	
	function setXLabel(options)
	{
		options.axes.xaxis.label = xAxisLabel;
		options.axes.xaxis.autoscale = true;
		options.axes.xaxis.labelRenderer = $.jqplot.CanvasAxisLabelRenderer;
		return options;
	}
	
	function setYLabel(options)
	{
		options.axes.yaxis.label = yAxisLabel;
		options.axes.yaxis.autoscale = true;
		options.axes.yaxis.labelRenderer = $.jqplot.CanvasAxisLabelRenderer;
		return options;
	}

function updateSeriesToGraph() 
{
    var data = [];
    var index;
    $("#choices").find("input:checked").each(function () {
        index = parseInt($(this).attr("id"));
        data.push(dataSets[index]);
    });
   
    seriesToGraph = data;
    createChart();
}

function updateTypeOfChart()
{
	var currVal = $(".chartType:checked").val();
	chartStyle = currVal;
	
	displayCustomChartOptions(chartStyle);
	createChart();
}


function updateChart()
{
	//title
	title = $("#title").val();
	
	//axis labels
	var xLabelTemp = $("#xLabel").val();
	var yLabelTemp = $('#yLabel').val();
	
	if(xLabelTemp != "")
		xAxisLabel = xLabelTemp;
	else
		xAxisLabel = null;
	
	if(yLabelTemp != "")
		yAxisLabel = yLabelTemp;
	else
		yAxisLabel = null;
	
	//legend
	if($("#legendSwitch").attr("checked") == true)
		legendOn = true;
	else
		legendOn = false;
	
	//x axis style
	$("#xAxisOptions option:selected").each(function () {
		xAxisStyle = $(this).attr('value');
		return false;
	});
	
	//y axis style
	$('#yAxisOptions option:selected').each(function() {
		yAxisStyle = $(this).attr('value');
	});
	
	createChart();
}


function savePlot()
{
	if(title == null || title == "") {
		$.blockUI({ 
            message: "Please enter a title, hit 'Update', and save again.", 
            fadeIn: 700, 
            fadeOut: 700, 
            timeout: 3000, 
            showOverlay: false, 
            centerY: false, 
            css: { 
                width: '350px', 
                top: '10px', 
                left: '', 
                right: '10px', 
                border: 'none', 
                padding: '5px', 
                backgroundColor: '#000', 
                '-webkit-border-radius': '10px', 
                '-moz-border-radius': '10px', 
                opacity: .6, 
                color: '#fff', 
            } 
        }); 
		return false;
	}
	
	var saveOptions = {
		name: title,
		type: chartStyle,
		legend: legendOn,
		xAxisLabel: xAxisLabel,
		yAxisLabel: yAxisLabel,
		xAxisRenderer: xAxisStyle,
		yAxisRenderer: yAxisStyle,
	};
	
	if(chartStyle == "Line")
		saveOptions = getLineSaveOptions(saveOptions);
	else if(chartStyle == "Bar")
		saveOptions = getBarSaveOptions(saveOptions);
	else if(chartStyle == "Pie")
		saveOptions = getPieSaveOptions(saveOptions);
	
	$.ajax({
		type: "POST",
		url: buildUrl("open-modeling-site/rest/graph/"),
		data: saveOptions,
		dataType: "xml",
		success: function(data, textStatus) {
			servResponse = data;
			var plotID = $(servResponse).find("graph").attr("id");
			saveSeries(plotID);
			
			$.growlUI('Save successful!', 'You may continue to make more graphs'); 
			$("#saveButton").attr("value","Save New");
		},
		error: function() {
			alert("Error");
		}
	});
}


function getPieSaveOptions(saveOptions)
{
	saveOptions.pieSliceMargin = currPlot.series[0].sliceMargin;
	saveOptions.pieDiameter = currPlot.series[0].diameter;
	saveOptions.fillArea = currPlot.series[0].fill;
	saveOptions.lineWidth = currPlot.series[0].lineWidth;
	
	return saveOptions;
}

function getBarSaveOptions(saveOptions)
{
	saveOptions.barWidth = currPlot.series[0].barWidth;
	saveOptions.barMargin = currPlot.series[0].barMargin; 
	saveOptions.barPadding = currPlot.series[0].barPadding;
	if(currPlot.series[0].rendererOptions.barbarDirection == "horizontal")
		saveOptions.barHorizontal = true;
	saveOptions.stackSeries = currPlot.stackSeries;
	
	return saveOptions;
}

//see <http://www.jqplot.com/docs/files/jqPlotOptions-txt.html> for the options stored in each plot
function getLineSaveOptions(saveOptions)
{
	if(currPlot.series[0].markerOptions.show == false)
		saveOptions.lineMarkerStyle = "none";
	else
		saveOptions.lineMarkerStyle = currPlot.series[0].markerOptions.style;
	
	saveOptions.lineShowLine = currPlot.series[0].showLine;
	saveOptions.lineWidth = currPlot.series[0].lineWidth;
	saveOptions.fillArea = currPlot.series[0].fill;
	if(saveOptions.fillArea == true)
		saveOptions.lineShowLine = true;
	saveOptions.stackSeries = currPlot.stackSeries;
	
	return saveOptions;
}

function saveSeries(plotID)
{
	$("#choices").find("input:checked").each(function () {
		var seriesIndex = parseInt($(this).attr("id"));
		
		$.ajax({
			type: "POST",
			url: buildUrl("open-modeling-site/rest/graph/"+plotID),
			data: ({scenarioid: scenarioId, variableid: variableIds[seriesIndex], name: $(this).attr("name"), description: "blah"}),
			error: function() {
				alert("Error with series #"+seriesIndex);
			}
		 });
	});
}


