/* 
 * Copyright (C) 2009 TopCoder Inc., All Rights Reserved.
 *
 * Init sliders 
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */

$(function(){
	$('select#valueAA,select#valueBB').accessibleUISlider({
		width: 149,
		labels: 12
	});
	$('select#valueCC,select#valueDD').accessibleUISlider({
		width: 149,
		labels: 12
	});
	$('select#valueEE,select#valueFF').accessibleUISlider({
		width: 149,
		labels: 12
	});
	$('select#valueGG,select#valueHH').accessibleUISlider({
		width: 149,
		labels: 12
	});
	$('select#valueII,select#valueJJ').accessibleUISlider({
		width: 149,
		labels: 12
	});	   
	
	$('select#valueMAA').accessibleUISlider({
		width: 149,
		labels: 12
	});
	$('select#valueMBB').accessibleUISlider({
		width: 149,
		labels: 12
	});
	
	$('.lv1 a').click(function(){
		if($(this).attr('class') == 'folder'){
			$(this).parent().find("ul").slideUp();
			$(this).attr('class','unfolder');
		}
		else{
			$(this).parent().find("ul").slideDown();
			$(this).attr('class','folder');
		}
	});
	$('#madelDetailFolder .folderTab a').click(function(){
		if($(this).attr('class') == 'folder'){
			$(this).attr('class','unfolder');
			$(this).parent().parent().find(".tabContent").slideUp();
		}
		else{
			$(this).attr('class','folder');
			$(this).parent().parent().find(".tabContent").slideDown();
		}
	});
	
	$('#description .folderTab a').click(function(){
		if($(this).attr('class') == 'folder'){
			$(this).parent().parent().find(".tabContent").slideUp();
			$(this).attr('class','unfolder');
		}
		else{
			$(this).parent().parent().find(".tabContent").slideDown();
			$(this).attr('class','folder');
		}
	});
	$('img,div,span,h1,h2,h3,a,p').css('behavior','url(css/iepngfix.htc)');
});