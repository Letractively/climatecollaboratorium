/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

    var useLocal = false;
	var inputs = new Array();


	var inputmetadata;
	var outputmetadata;


	var xmlGlobal;
	var simulationId;	
	var simName;
	var idDummy;
	var isComposite;

	var queryGlobal;

	$(document).ready(function(){
		makeSliderDisplay();
		
			               

		//if pass in scenarioId
		if(scenarioId != -1 && mode != 'actions' && mode != 'model') {
	        var scenUrl = "/simulation-servlet/rest/scenario/"+scenarioId;	        
	        $.ajax({
	            url: scenUrl,
	            type: 'GET',
	            dataType: 'xml',
	            error: function(){
	                alert('Error loading scenario for id '+ scenarioId);
	            },
	            success: function(data){
	            	buildTable(data);	            	
	            	$("#infoBox").show();
	            	//no need to save
	            	enableVisualize(true);	   
	            	
	            }
	        });             
		}
		
		//TEMPORARY and needs to be integrated later
		//hide parts depending on mode
		if(mode == 'actions') {
		    $("#modelDescriptionDiv").hide();
		    $("#outputs").hide();
		    //2 buttons
		    $("button#visualize").hide();
		    $("button#createPlan").hide();
		    //bread crumb
		    $("#path").hide();
		    $("#infoBox").hide();
		}
		if(mode == 'model') {
		    $("#inputs").hide();
		    $("#scenario-sim").hide();
		    $("#outputs").hide();
		    //2 buttons
		    $("button#visualize").hide();
		    $("button#createPlan").hide();
		    //bread crumb
		    $("#path").hide();
		    $("#infoBox").hide();
		}
		if(mode == 'impacts') {
		    $("#modelDescriptionDiv").hide();
		    $("#inputs").hide();
		    $("#scenario-sim").hide();
		    //2 buttons
		    $("button#visualize").hide();
		    $("button#createPlan").hide();
		    //bread crumb
		    $("#path").hide();
		}
	});

	function getServerInfo(){
		var urlString = window.location.href;
		var pairs = new Array();
		pairs = urlString.split('/');
		serverInfo = pairs[2];
	}

	function makeSliderDisplay()
	{
		$("div#sliders").empty();
		inputs = new Array();
		
		simulationId = parameterSimId;

		xmlGlobal = getXMLDoc(simulationId);
		inputmetadata = $(xmlGlobal).find("inputs>metadata");
		outputmetadata = $(xmlGlobal).find("outputs>metadata");

		simName = $(xmlGlobal).find("inputs + name").text();

        //render model name and description
        var simDescription = $(xmlGlobal).find("creation + description").text();
        $('#modelName').html(simName);
        $('#modelDescription').html(simDescription);

        //render create plan button
        var currentSim = $(xmlGlobal).find("simulation").get(0);
        isComposite = ($(currentSim).attr("composite") == "true");
        if(!isComposite) {
          $("#createPlan").hide();
        }

		getValsFromXML(xmlGlobal);

		addSliders();
	}

	function getXMLDoc(simId)
  	{
		if(useLocal)
			return loadXMLDoc("982");

  	  	var url = "/simulation-servlet/rest/simulation/"+simId;
	  	var http;
  		if (window.XMLHttpRequest)
  		{
  	  // code for IE7+, Firefox, Chrome, Opera, Safari
  		  http=new XMLHttpRequest();
  		}
  		else if (window.ActiveXObject)
  		{
  	  // code for IE6, IE5
  		  http=new ActiveXObject("Microsoft.XMLHTTP");
  		}
  		else
 	 	{
	  	  alert("Your browser does not support XMLHTTP!");
  		}
  		http.open("GET",url,false);
  		http.send("");
  		return http.responseXML;
  	}



	function getValsFromXML(xml){
		var counter = 0;
		var doc = xml.getElementsByTagName("name");
		var descs = $(xml).find("inputs").find("description");
		descsDummy = descs;

		for (i=0;i<doc.length;i++)
		{
			if(doc[i].parentNode.parentNode.nodeName=='inputs'){
				inputs[counter] = doc[i].childNodes[0];

				$("div#sliders").append("<div id='input"+counter+"' class='input-var'>");
				$("#input"+counter).append('<span class="expand-icon ui-icon ui-icon-triangle-1-e"></span>');
				$("#input"+counter).append("<span class='slider-label'><a href='#toggle'>"+inputs[counter].nodeValue+"</a></span>");
				$("#input"+counter).append("<input class='amount' type='text' id='amount"+counter+"'></input>");
				$("#input"+counter).append("<div class='slider' id='slider"+counter+"'></div>");

				$("#input"+counter).append("<div id='details"+counter+"' class='input-details'>"+"<strong>description: </strong>"+descs[counter].textContent+"</div>");
				$("#sliders").append("</div>");

				$("#sliders").append("<br />");
				counter++;
			}
		 }
		$(".input-details").hide();
		$(".expand-icon").click(function() {
			var sliderDetails = $(this).next().next().next().next();
			sliderDetails.toggle("normal");
			$(this).toggleClass("ui-icon-triangle-1-e");
			$(this).toggleClass("ui-icon-triangle-1-s");
		});

		$("A[href='#toggle']").click(function() {
			var sliderDetails = $(this).parent().next().next().next();
			sliderDetails.toggle("normal");
			var icon = $(this).parent().prev();
			icon.toggleClass("ui-icon-triangle-1-e");
			icon.toggleClass("ui-icon-triangle-1-s");
		});


		addShowAndHideAll();
	}

	function addShowAndHideAll()
	{
		$("A[href='#show_all']").click(function() {
	      	$(".expand-icon").each(function() {
				var sliderDetails = $(this).next().next().next().next();
				sliderDetails.show("normal");
				$(this).removeClass("ui-icon-triangle-1-s");
				$(this).removeClass("ui-icon-triangle-1-e");
				$(this).addClass("ui-icon-triangle-1-s");
			});
	    });

		$("A[href='#hide_all']").click(function() {
	      	$(".expand-icon").each(function() {
				var sliderDetails = $(this).next().next().next().next();
				sliderDetails.hide("normal");
				$(this).removeClass("ui-icon-triangle-1-s");
				$(this).removeClass("ui-icon-triangle-1-e");
				$(this).addClass("ui-icon-triangle-1-e");
			});
	    });
	}

	function addSliders(){
		var sliderString = '';
		var amountString = '';
		var counter = 0;




		for (i=0;i<inputs.length;i++)
	    {
			sliderString = '#slider'+counter;
			amountString = "#amount"+counter;
			var inputType = $(inputmetadata[i]).attr("vartype");

			if (inputType=="RANGE" || inputType=="FUZZY_DISCRETE"){
				var l_min = getInputMin(i);
				var l_max = getInputMax(i);
				var l_def = getInputDefault(i);

				if (l_def < l_min || isNaN(l_def)) {
					l_def = l_min;
				} else if (l_def > l_max) {
					l_def = l_max;
				}
                
				$(sliderString).slider({
			    	range: "min",
				    value:((l_def-l_min)/(l_max-l_min))*1000,
				    min:0,
				    max:1000,
				    slide: labelUpdate(i,l_min,l_max)
			    });
				var currDataType = getInputDatatype(i);
			    $(amountString).val(pickValue($(sliderString).slider("value"),l_min,l_max));
			} else {
				var valArray = getInputCategories(getInputCategories(i));

				$(sliderString).addClass('drop-down');

				$(sliderString).append("<select id='input-drop"+counter+"'></select>");
				// add the select tag
				for (j=0;j<valArray.length;j++){
					$('#input-drop'+counter).append("<option value='"+valArray[j]+"'>"+valArray[j]+"</option>");
				}
			}
			counter += 1;
	    }
	}

	function extract(s) {
		return s.slice(1,s.length-1);
	}

	function getInputMin(idx) {
		var result = $(inputmetadata[idx]).find("mins>data");
		if (result == null || result.text()==null) {return -100;}
		else return parseFloat(extract(result.text()));
	}

	function getInputMax(idx) {
		var result = $(inputmetadata[idx]).find("maxes>data");
		if (result == null || result.text() == null) {return -100;}
		else return parseFloat(extract(result.text()));
	}

	function getInputDefault(idx) {
		var result = $(inputmetadata[idx]).find("defaults>data");
		if (result == null|| result.text() == null) {return -100;}
		else return parseFloat(extract(result.text()));
	}

	function getInputCategories(idx) {
		var result = $(inputmetadata[idx]).find("categories>data");
		if (result == null || result.text()==null) {return "none"}
		else return result.split(extract(result.text()));
	}

	function getInputDatatype(idx) {
		var result = $(inputmetadata[idx]).find("profile>data");
		if (result == null || result.text()==null) {return "java.lang.Integer"}
		else return extract(result.text());
	}

	function labelUpdate(idx,min,max) {
		var result = function(event,ui) {
			$("#amount"+idx).val(pickValue(ui.value,min,max));
		}
		return result;
	}

	function pickValue(value,min,max) {
		var result = (value/1000.0 * (max-min))+min;

		if (result.toFixed) {
			result = result.toFixed(2);
		}
		return result;
	}




	function showInputs(){
		for (var i=0;i<inputs.length;i++){
			document.write(inputs[i].nodeValue+"<br/>");
		}
	}



	function addElement(name,value){
	  		var newElement = document.createElement('input');
			newElement.setAttribute('type','hidden');
			newElement.setAttribute('value',value);
			newElement.setAttribute('name',name);
			newElement.setAttribute('class','parameter');
			document.getElementById('param-values').appendChild(newElement);
	  	}

	function clearForm(){
		$('#param-values').empty();
	}


 	function buildForm(){
  		var amountString = "";
  		clearForm();
  	  	for (var i=0;i<inputs.length;i++){
	  		amountString = "#amount"+i;
	  		dropString = "#input-drop"+i;
	  		if (inputs[i].parentNode.parentNode.getAttribute("type")=="SCALAR"){
	  			addElement(inputs[i].nodeValue,$(amountString).val());
	  		} else {
	  			addElement(inputs[i].nodeValue,$(dropString).val());
	  		}
	  	}
  	}


  	function restoreDefaults(){
  	  	var amountString = "";
  	  	var sliderString = "";
  	  	for (var i=0;i<inputs.length;i++){
  	  	  	amountString = "#amount"+i;
  	  	  	sliderString = "#slider"+i;
  			$(amountString).val(getInputDefault(i));
  			$(sliderString).slider('value',getInputDefault(i));
  	  	}
  	}

  	function makeScenario(){
  		var url = "/simulation-servlet/rest/runsim";
  	  	var parameters = document.getElementsByClassName("parameter");

  	  	var query = new Array();
	  	var pathToExcelModel = $(xmlGlobal).find("url").text();

	  	for(var i=0; i < inputs.length; i++) {
	  		var sliderString = "#slider"+i;

	  		var currInputName = $(inputmetadata[i]).find("internalname:first").text();
	  		if($(sliderString).hasClass('drop-down')) {
	  			var currInputValue = $("#slider"+i+" option:selected").attr("value");
	  			query.push(currInputName+'='+currInputValue);
	  		}
	  		else {
	  			query.push(currInputName+'='+$("#amount"+i).val());
	  		}
	  	}
	  	queryGlobal = query;

	  	/*for (var i=0;i<parameters.length;i++)
  	  	  	query.push(parameters[i].name+'='+parameters[i].value);
	  	*/
	  	var simulationId = parameterSimId;

	  	query.push('simId=' + simulationId);
	  	//query.push(simId[0].name+'='+simulationId);

	  	query.push();
	  	query.push('path='+pathToExcelModel);
	  	var paramString = "";
	  	var nextParam;
	  	for (var i=0;i<query.length;i++)
	  	{
		  	nextParam = query[i];
		  	if (i != query.length-1)
		  	{
			  	nextParam += '&';
		  	}
		  	paramString += nextParam;
	  	}

	  	$.ajax({
			type: "POST",
			url: url,
			data: paramString,
			dataType: "xml",
			timeout: 30000,
			success: function(scenXml) {
	  			$("#infoBox").unblock();
	  			scenarioId = $(scenXml).find("scenario").attr("id");
				buildTable(scenXml);
				enableSave();
				enableVisualize();
			},
			error: function() {
				$("#infoBox").unblock();
				 $.growlUI('Error running model', 'The model may have timed out due to a system error. Please contact the system admin or try again.');
			}
		});

	  /*	var http;
  		if (window.XMLHttpRequest)
  		{
  	  // code for IE7+, Firefox, Chrome, Opera, Safari
  		  http=new XMLHttpRequest();
  		}
  		else if (window.ActiveXObject)
  		{
  	  // code for IE6, IE5
  		  http=new ActiveXObject("Microsoft.XMLHTTP");
  		}
  		else
 	 	{
	  	  alert("Your browser does not support XMLHTTP!");
  		}

  		http.open("POST",url,false);
  		http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  		http.setRequestHeader("Content-length", query.length);
  		http.setRequestHeader("Connection", "close");
   		http.send(paramString);
   		return http.responseXML; */
  	}

  	function runSim()
  	{	
  	    //demo for now
  	    if(mode == "actions") {
  	       $.growlUI('Done', 
  	       'Finished running simulation, check results in impacts tab.');
  	       return;
  	    }
  	
  	    $("#infoBox").css({'display':'block'});
  		$("#infoBox").unblock();
  		$("#infoBox").block({
  			message: '<img src="/html/portlet/ext/models/images/ajax-loader.gif"/>',
  			css: { 	color: '#000',
  					padding: "15px",
  					'-webkit-border-radius': '10px',
  					'-moz-border-radius':    '10px',
  					cursor: 'inherit'
  				},
  			overlayCSS:  {
  					backgroundColor: '#FFF',
  			       	opacity: 0.0,
  			       	cursor: 'inherit'
  			    }
  		});
  		makeScenario();
  	}


  	function buildTable(xml) {
  		$("div#infoBox").empty();
  	  	$("div#infoBox").append("<table id='outputTable' border='1' class='pretty-table_' style='background-color:white'></table>");
  	  	var variables = outputmetadata.map(function() {
  	  		var mid = $(this).find("id").text();
  	  		var variable = $(xml).find("variable:has(id:contains("+mid+"))");
  	  		return variable;
  	  	});
  	  	var outputNames = outputmetadata.find("name").map(function () {
			return $(this).text();
		});
  		$("table#outputTable").append("<tr class='outputNames'></tr>");
  	  	for (var colidx in variables.get()) {
  	  		$("table#outputTable tr.outputNames").append("<td>"+outputNames[colidx]+"</td>");

  	  		var idxed = $(outputmetadata[colidx]).is("[varcontext=INDEXED]");
  	  		var outputVals = getOutputVals(variables[colidx].find("data").text(),idxed);
  	  		for (var rowidx in outputVals) {
  	  			if (colidx == 0) {
  	  				$("table#outputTable").append("<tr class='output"+rowidx+"'></tr>");
  	  			}
  	  			$("tr.output"+rowidx).append("<td>"+outputVals[rowidx]+"</td>");
  	  		}
  	  	}
  	}



  	function getOutputVals(txt,indexed)
  	{
  	  	//strip outer brackets
  		txt = txt.substring(2,txt.length-2);
  		var elts = txt.split(/\]\[/);
  		if (indexed) {
  			for (var i in elts) {
  				var commapos = elts[i].indexOf(',');
  				if (commapos > -1) elts[i] = elts[i].substring(commapos+1);
  			}
  		}
  		return elts;
  	}

  	function saveScenario() {
  		if (scenarioId < 0) return;
  		$.ajax({
			type: "POST",
			url: "/simulation-servlet/rest/scenariostate/"+scenarioId,
			data: "state=PUBLIC",
			dataType: "xml",
			timeout: 3000,
			success: function() {
	  			$("#infoBox").unblock();
	  			$.growlUI('Success!', 'Scenario saved successfully.');
			},
			error: function() {
				 $.growlUI('Error running model', 'The model may have timed out due to a system error. Please contact the system admin or try again.');
			}
		});
  	}

    function visualize(visualizeURL) {
        location.href=visualizeURL+"&"+portletNameSpace+"scenarioId="+scenarioId;        
    }
    
    function createPlan(createPlanURL) {
        location.href=createPlanURL+"&"+plansPortletNamespace+"scenarioId=" + scenarioId + 
        	"&" + plansPortletNamespace + "modelId=" + simulationId;        
    }

	function saveSim(){
		var http;
		var state = "state=PUBLIC";
		var id = document.getElementById("scenarioid").value;
		var url = "simulation-servlet/rest/scenariostate/"+id;
  		if (window.XMLHttpRequest)
  		{
  	  // code for IE7+, Firefox, Chrome, Opera, Safari
  		  http=new XMLHttpRequest();
  		}
  		else if (window.ActiveXObject)
  		{
  	  // code for IE6, IE5
  		  http=new ActiveXObject("Microsoft.XMLHTTP");
  		}
  		else
 	 	{
	  	  alert("Your browser does not support XMLHTTP!");
  		}
  		http.open("POST",url,false);
  		http.send(state);
	}

	function getScenarioId(xml)
	{
		var scenario = xml.getElementsByTagName("scenario");
		var id = scenario[0].getAttribute("id");
		$("form.id").html("<input type='hidden' id='scenarioid' value='"+id+"'></input>");
	}

  	function login()
  	{
  		var http;
		var url = "/simulation-servlet/rest/loginuser";
  		if (window.XMLHttpRequest)
  		{
  	  // code for IE7+, Firefox, Chrome, Opera, Safari
  		  http=new XMLHttpRequest();
  		}
  		else if (window.ActiveXObject)
  		{
  	  // code for IE6, IE5
  		  http=new ActiveXObject("Microsoft.XMLHTTP");
  		}
  		else
 	 	{
	  	  alert("Your browser does not support XMLHTTP!");
  		}
  		http.open("GET",url,false);
  		http.send(null);
  	}

  	function enableSave()
  	{
  		$("button#savesim").removeAttr("disabled");
  	}

  	function enableVisualize(noSave)
  	{
  		$("button#visualize").removeAttr("disabled");
  		// if composite, also enables the create plan
  		if(isComposite) {
  		   $("button#createPlan").removeAttr("disabled");
  		   if(!noSave) {
		      saveScenario();
		   }
  		}
  	}
