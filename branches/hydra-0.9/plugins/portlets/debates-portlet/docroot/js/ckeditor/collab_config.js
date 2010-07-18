/*
Copyright (c) 2003-2010, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	
	//config.uiColor = '#AADC6E';
    //config.toolbar = 'MyToolbar';
	
	config.toolbar_CollabToolbar = 
		[ ['Styles', 'Format'],
	      ['Bold', 'Italic', '-', 'NumberedList', 'BulletedList', '-', 'Link', '-']
	    ];
	config.toolbar = "CollabToolbar";
	
/*
    config.toolbar_MyToolbar =
    ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
    ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
    ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
    ['Link','Unlink','Anchor'],
    ['Image','Flash','Table','HorizontalRule'];
    */

};
