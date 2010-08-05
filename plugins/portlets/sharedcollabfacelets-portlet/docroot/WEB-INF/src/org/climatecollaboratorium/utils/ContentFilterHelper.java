package org.climatecollaboratorium.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ContentFilterHelper {
    public static String filterLineBreaks(String content) {
        return content.replaceAll("\n", "<br />\n");
    }
    
    public static String filterUrlEmbeddedLinks(String content) {
        Pattern pattern = Pattern.compile("\\[url=[^\\]]*\\][^\\[]*\\[/url\\]");
        Matcher matcher = pattern.matcher(content);
        StringBuilder strBuilder = new StringBuilder();
        int lastIndex = 0;
        while (matcher.find()) {
            strBuilder.append(content.substring(lastIndex, matcher.start()));
            String urlDef = matcher.group();
            int urlDefEnd = urlDef.indexOf("]");
            int urlDescEnd = urlDef.lastIndexOf("[");
            
            String url = urlDef.substring(5, urlDefEnd);
            String urlDesc = urlDef.substring(urlDefEnd + 1, urlDescEnd); 
            
            strBuilder.append(createLink(url, urlDesc));
            lastIndex = matcher.end();
        }
        strBuilder.append(content.substring(lastIndex));
        
        return strBuilder.toString();
    }
    
    public static String filterLinkifyUrls(String content) {
        Pattern existingLinksPattern = Pattern.compile("(<a[^>]*>[^<]*</a>)|(\\[url=[^\\[]*\\[/url\\])");
        Matcher existingLinksMatcher = existingLinksPattern.matcher(content);
        
        List<Integer[]> linksBeginEnd = new ArrayList<Integer[]>(); 
        while (existingLinksMatcher.find()) {
            linksBeginEnd.add(new Integer[] {existingLinksMatcher.start(), existingLinksMatcher.end()});
        }
        

        Pattern pattern = Pattern.compile("(http://|www\\.)([{\\w-]*\\.)+\\w{1,4}([^\\s]*)");
        Matcher matcher = pattern.matcher(content);
        StringBuilder strBuilder = new StringBuilder();
        
        int lastIndex = 0;
        while (matcher.find()) {
            // check if this link isn't already part of existing <a href=...
            boolean partOfAnchor = false;
            for (Integer[] linkStartEnd: linksBeginEnd) {
                if (matcher.start() > linkStartEnd[0] && matcher.start() < linkStartEnd[1]) {
                    partOfAnchor = true;
                    break;
                }
            }
            if (partOfAnchor) {
                continue;
            }
            
            strBuilder.append(content.substring(lastIndex, matcher.start()));
            String url = content.substring(matcher.start(), matcher.end());
            strBuilder.append(createLink(url, url));
            
            strBuilder.append(content.substring(matcher.end(), matcher.end()));
            lastIndex = matcher.end();
        }
        

        strBuilder.append(content.substring(lastIndex));
        return strBuilder.toString();
    }

    


    private static String createLink(String url, String desc) {
        return createLink(url, desc, "");
    }

    private static String createLink(String url, String desc, String title) {
        if (! url.contains("http://")) {
            url = "http://" + url;
        }
        return "<a href='" + url + "' title='" + title + "' class='" + title + "'>" + desc + "</a>";
    }
    
    public static String filterContent(String content) {
        String tmp = content;
        if (! content.contains("<br")) {
            tmp = filterLineBreaks(tmp);
        }
        tmp = filterUrlEmbeddedLinks(tmp);
        tmp = filterLinkifyUrls(tmp);
        
        return tmp;
    }
}
