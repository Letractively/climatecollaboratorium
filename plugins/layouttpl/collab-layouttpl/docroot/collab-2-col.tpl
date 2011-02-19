<div id="content_wrap">
    <div id="content_bdr"></div>
    <div id="content_shade">
        <div id="content_home">
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
                <td class="lfr-column seventy" id="column-3" valign="top">
                    $processor.processColumn("column-3")
                </td>
                <td class="lfr-column thirty" id="column-4" valign="top">
                    $processor.processColumn("column-4")
                </td>
            </tr>
            <tr>
                <td class="lfr-column" colspan="2" id="column-5" valign="top">
                    $processor.processColumn("column-5")
                </td>
            </tr>
        	</table>
   <div id="footer">
                <div id="footdiv"></div>
                <div id="footmenu">
                    <ul>
                        <li><a href="/web/guest/resources/-/wiki/Main/Help">Contact</a></li>
                        <li><a href="/web/guest/feedback">Help</a></li>
                        #if ($themeDisplay.signedIn)
                            <li><a href="/c/portal/logout">Sign out</a></li>
                            <li><a href="/web/guest/member/-/member/userId/$themeDisplay.user.userId">My profile</a></li>
                        #else 
                                <li><a href="javascript:return false;" class="openreg">Register</a></li>
                                <li><a href="javascript:return false;" onclick="deferUntilLogin();">Sign In</a></li>
                        #end
                    </ul>
                </div>
            </div>
        </div> <!-- /content_home -->
    </div> <!-- /content shade -->
    <div id="footshade"><div class="wht"></div></div>
    
</div> <!-- /#content_wrap -->