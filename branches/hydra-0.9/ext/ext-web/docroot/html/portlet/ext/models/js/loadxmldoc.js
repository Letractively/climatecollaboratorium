/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */
function loadXMLDoc(dname)
{
var xmlDoc;
if (window.XMLHttpRequest)
  {
  xmlDoc=new window.XMLHttpRequest();
  xmlDoc.open("GET",dname,false);
  xmlDoc.send("");
  return xmlDoc.responseXML;
  }
// IE 5 and IE 6
else if (ActiveXObject("Microsoft.XMLDOM"))
  {
  xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
  xmlDoc.async=false;
  xmlDoc.load(dname);
  return xmlDoc;
  }
alert("Error loading document");
return null;
}
