/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

var pageNum = 1;
var numToDisplay = 25;
var allSims;
var totalPages;
var dummyName;
var dummyDate;

$(document).ready(function () {	
	jQuery.ajax({
			type: "GET",
			url: "/simulation-servlet/rest/simulation/",
			dataType: "xml",
			success: function(data, textStatus) {
				//alert("Success!");
		 		displayTable(data);
			},
			error: function() {
				alert("Error");
			}
		});
	
});

var displayWeights = {
     "623":0,
     "240":2     
}

function getDisplayWeight(sim) {
     var weight = displayWeights[$(sim).attr(i)];
     return weight==null?1000:weight;
}


function displayTable(xml)
{
	 var tableHtml = "<table id='simple-table'><thead><tr>"+
	 			"<th scope='col'>Model Name</th>"+
	 			"<th scope='col'>Type</th>"+
	 			"<th scope='col'>Date Uploaded</th>";
	 
	 var startIndex = (pageNum-1)*numToDisplay;
	 
	 allSims = $(xml).find("simulation");
	 allSims.sort(function(sima,simb) {
          return getDisplayWeight(sima) - getDisplayWeight(simb);
     });
	 var endIndex = startIndex + numToDisplay;
	 
	 for(var i=startIndex; i < allSims.length && i < endIndex; i++) {
		 var currSim = $(allSims).get(i);
		 var date = $(currSim).find("creation").text().replace(/T.*$/,"");
		 var currSimId = $(currSim).attr('id');
		 var type = ($(currSim).attr('composite') == 'true')? "Composite":"Atomic";
		 
		 var simName = $(currSim).find("inputs + name").text();
		 var simDesc = $(currSim).find("creation + description").text();
		  
		 tableHtml += "<tr><td>"+"<a href='"+runModelURLPrefix+currSimId+"'>"+simName+"</a></td>"+
		                "<td>"+type+"</td>"+
		 				"<td>"+date+"</td>"+
		 				"</tr>";
			 
	 }
	 $("#tableDisplay").html(tableHtml);
}