/* 
 * Copyright (C) 2009 TopCoder Inc., All Rights Reserved.
 *
 * Initialization of theme elements. 
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */

$(document).ready(function(){
	$('#radio1').click(function(){$('.switch1').show();$('.switch2').hide();});
	$('#radio2').click(function(){$('.switch2').show();$('.switch1').hide();});
	
	$('#radio3').click(function(){$('.switch3').show();$('.switch4').hide();});
	$('#radio4').click(function(){$('.switch4').show();$('.switch3').hide();});
	
	$('#radio5').click(function(){$('.switch5').show();$('.switch6').hide();});
	$('#radio6').click(function(){$('.switch6').show();$('.switch5').hide();});
	
	$('#radio7').click(function(){$('.switch7').show();$('.switch8').hide();});
	$('#radio8').click(function(){$('.switch8').show();$('.switch7').hide();});
	
	$('#radio9').click(function(){$('.switch9').show();$('.switch10').hide();});
	$('#radio10').click(function(){$('.switch10').show();$('.switch9').hide();});
	
	$('#radio11').click(function(){$('.switch11').show();$('.switch12').hide();});
	$('#radio12').click(function(){$('.switch12').show();$('.switch11').hide();});
/*	
	$('#radio13').click(function(){$('.switch13').show();$('.switch14').hide();});
	$('#radio14').click(function(){$('.switch14').show();$('.switch13').hide();});*/
	
	$('.health').click(function(){
		$('.impactsContent div').hide();
		$("#health").show();
		$('.impactsTab li').removeClass('current');
		$('.health').addClass('current');
	});
	$('.food').click(function(){
		$('.impactsContent div').hide();
		$("#food").show();
		$('.impactsTab li').removeClass('current');
		$('.food').addClass('current');
	});
	$('.water').click(function(){
		$('.impactsContent div').hide();
		$("#water").show();
		$('.impactsTab li').removeClass('current');
		$('.water').addClass('current');
	});
	
	$('.actionHidden').hide();
	$('.editBtn').click(function(){
		$('.actionHidden').show();
		$('.btnBox').show();
		$('.editBtn').hide();
		$('.plan_detail .tabActions .leftTxt').css('margin-left','15px');
	});
	//$('img,div,span,h1,h2,h3,a,p').css('behavior','url(css/iepngfix.htc)');
});