<div>
    $processor.processColumn("column-1")
</div>
    
    
<div id="content" class="home" style="border: 1px solid #f5f5f5; border-left: 0; border-right: 0;">
        <div class="titleArea">
            <div  class="left">&nbsp;
                $processor.processColumn("column-3")
            </div>
            <div  class="mid">&nbsp;
                $processor.processColumn("column-4")
            </div>
            <div  class="right">&nbsp;
                $processor.processColumn("column-5")
            </div>
 
           <div class="divider clear"></div>
        </div>
        
        <div class="contentArea">
            <div  class="left lrContentPlaceholder lfr-column">
                $processor.processColumn("column-6")
            </div>
            <div  class="mid lrContentPlaceholder lfr-column">
                $processor.processColumn("column-7")
            </div>
            <div  class="right lrContentPlaceholder lfr-column">
                $processor.processColumn("column-8")
            </div>
        
            <div class="clear"></div>
        </div>
        
            <div id="footer">
                <div id="footdiv"></div>
                <div id="footmenu">
                    <ul>
                        <li><a href="/web/guest/feedback">Contact</a></li>
                        <li><a href="/web/guest/resources/-/wiki/Main/Help">Help</a></li>
                        #if ($themeDisplay.signedIn)
                            <li><a href="/c/portal/logout">Sign out</a></li>
                            <li><a href="/web/guest/member/-/member/userId/$themeDisplay.user.userId">My profile</a></li>
                        #else 
                                <li><a href="javascript:return false;" class="openreg">Register</a></li>
                                <li><a href="javascript:return false;" onclick="deferUntilLogin();">Sign In</a></li>
                        #end
                    </ul>
                </div>
                $processor.processColumn("column-9")
            </div>
</div> <!-- /#content -->