<div class="lrContentPlaceholder lfr-column">
    &nbsp;
    $processor.processColumn("column-1")
</div>
<div id="message" class="lrContentPlaceholder lfr-column">
    &nbsp;
    $processor.processColumn("column-2")
</div>
    
    
    
<div id="content">
            <div  class="left lrContentPlaceholder lfr-column">&nbsp;
                $processor.processColumn("column-3")
            </div>
            <div  class="mid lrContentPlaceholder lfr-column">&nbsp;
                $processor.processColumn("column-4")
            </div>
            <div  class="right lrContentPlaceholder lfr-column">&nbsp;
                $processor.processColumn("column-5")
            </div>
 
           <div class="divider clear"></div>
        
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
        
            <div id="footer">
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
                $processor.processColumn("column-9")
            </div>
</div> <!-- /#content -->