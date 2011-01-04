<div id="content">
        <div class="custom-2-column" id="content-wrapper">
            <table class="lfr-grid" id="layout-grid">
                <tr>
                
                    <td class="lfr-column" colspan="2" id="column-1" valign="top">
                        $processor.processColumn("column-1")
                    </td>
                    
                        </tr>
                        <tr>
                            <td class="lfr-column" colspan="2" id="column-2" valign="top">
                                $processor.processColumn("column-2")
                            </td>

                        </tr>
                        <tr id="column-center">
                            <td class="lfr-column" valign="top">
                                $processor.processColumn("column-3")
                            </td>
                        </tr>

                	</table>
                </div>
                	
                <div id="footer" class="lfr-column">
                    <div id="footdiv"></div>
                    <div id="footmenu">
                    <ul>
                        <li><a href="/web/guest/feedback">Contact</a></li>
                        <li><a href="/web/guest/resources/-/wiki/Main/Help">Help</a></li>
                            #if ($themeDisplay.signedIn)
                                <li><a href="/c/portal/logout">Sign out</a></li>
                                <li><a href="/web/guest/myprofile">My profile</a></li>
                            #else 
                                <li><a href="/web/guest/loginregister?p_p_id=loginregister" onclick="modifyLoginRegisterURL(this);">Register</a></li>
                                <li><a href="/web/guest/loginregister?p_p_id=loginregister" onclick="modifyLoginRegisterURL(this);">Sign In</a></li>
                            #end
                        </ul>
                    </div>

                    $processor.processColumn("column-5")
                </div> <!-- /footer -->
  </div> <!-- /content_wrap -->
