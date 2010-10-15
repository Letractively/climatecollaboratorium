<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>


<%@ include file="/html/common/init.jsp" %>
<portlet:defineObjects/>



<script type="text/javascript">
    <%@ include file="/html/js/collaboratorium/cookies.js" %>  
    var interval = 3650; 
    
    function checkCookie() {
         var cookie = new Object();
         cookie.username = Get_Cookie(LOCKED_LOGIN_NAME);
         cookie.password = Get_Cookie(LOCKED_LOGIN_PASSWORD);
         
       
         
          if (cookie.username) {
               
              jQuery("#status").text("Locked login cookie IS set");
              jQuery("#clearlogin").removeAttr("disabled");
              jQuery("#username").attr("value", cookie.username);
              jQuery("#password").attr("value",cookie.password);
        
         } else {
	          jQuery("#status").text("Locked login cookie IS NOT set");    
	          jQuery("#clearlogin").attr("disabled","disabled");
	    
	          jQuery("#username").attr("value","");
              jQuery("#password").attr("value","");
	     }
    
    }
    
    function deleteCookie() {
         alert("delete cookie");
         //Delete_Cookie(LOCKED_LOGIN_NAME,"/");
        // Delete_Cookie(LOCKED_LOGIN_PASSWORD,"/");
         var d = new Date();
         d.setTime(0);
         Set_Cookie(LOCKED_LOGIN_NAME,jQuery("#username").attr("value"),d,"/");
        Set_Cookie(LOCKED_LOGIN_PASSWORD,jQuery("#password").attr("value"),d,"/");
            
         checkCookie();
    }
    
    function setCookie() {
            
            Set_Cookie(LOCKED_LOGIN_NAME,jQuery("#username").attr("value"),interval,"/");
            Set_Cookie(LOCKED_LOGIN_PASSWORD,jQuery("#password").attr("value"),interval,"/");
            checkCookie();
    }
    
    jQuery(document).ready(function() {
        
        checkCookie();
    });
</script>


<div id="status"></div>
  <div class="form">
    <table class="form-info">
         <tr>
              <td>Username:</td><td><input id="username" type="text" name="username"></input></td>
          </tr>
         <tr>
              <td>Password:</td><td><input id="password" type="password" name="password"></input></td>
         </tr>
      </table>
 </div>
    
 <div class="buttons">   
 <button id="setlogin" class="floatLeft" onClick="javascript:setCookie();">Set login</button>
 <button id="clearlogin" class"floatLeft" onClick="javascript:deleteCookie();">Clear login</button>
  </div>
</div>