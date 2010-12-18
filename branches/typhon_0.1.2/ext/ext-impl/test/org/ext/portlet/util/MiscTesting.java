/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.ext.portlet.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Oct 1, 2010
 * Time: 2:22:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class MiscTesting {

     public static int MAX_SHORTENED_LENGTH=80;

    private static String getShortString(String content) {

        //strip leading whitespace, breaks
        content = content.trim();
        content = content.replaceAll("\n+"," ");
        content = content.replaceAll("(?:<br\\s*/\\s*>)+"," ");


        Pattern p = Pattern.compile("((?:\\s*<br\\s*/\\s*>)*).*");
        Matcher m = p.matcher(content);
        m.find();
        if (m.group(1).length()> 0) {
            content = content.substring(m.group(1).length(),content.length());
        }

        //replace remaining breaks

        if (content.length() <= MAX_SHORTENED_LENGTH) return content;
        String remainder = content.substring(MAX_SHORTENED_LENGTH,content.length());
        content = content.substring(0,MAX_SHORTENED_LENGTH);

        System.err.println("Remainder = "+remainder);
        Pattern pattern = Pattern.compile("\\[url=[^\\]]*\\][^\\[]*");
        Matcher matcher = pattern.matcher(content);


        if (matcher.find()) {
              int idx = remainder.indexOf("[/url]");
                System.err.println("Index of url closing tag is "+idx);
              content+=remainder.substring(idx,idx+6);
        }

        return content;
    }

     public static void main(String[] args) {
        String test = "\n\n\n\n<br/><br/><br/><br/>\n\nThis is a \ntest<br/><br  />[url=\";lakdsjf;lkjasdf\"]fooey[/url]\n\n\n\n<br/><br/><br/><br/>\n\nThis is a \ntest<br/><br  />[url=\";lakdsjf;lkjasdf\"]fooey[/url]\n\n\n\n<br/><br/><br/><br/>\n\nThis is a \ntest<br/><br  />[url=\";lakdsjf;lkjasdf\"]fooey[/url]";
        System.err.println(getShortString(test));
    }
}
