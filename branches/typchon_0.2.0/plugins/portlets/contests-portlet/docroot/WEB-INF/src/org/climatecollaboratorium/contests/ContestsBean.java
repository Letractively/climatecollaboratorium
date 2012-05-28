package org.climatecollaboratorium.contests;

import java.util.List;

import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.service.ContestLocalServiceUtil;
import com.liferay.portal.SystemException;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.blogs.service.BlogsEntryLocalServiceUtil;

public class ContestsBean {
    
    public List<Contest> getContests() throws SystemException {
        for (BlogsEntry entry: BlogsEntryLocalServiceUtil.getBlogsEntries(0, Integer.MAX_VALUE)) {
            
            String content = entry.getContent();
            /*content = content.replaceAll("style=\"[^\"]*\"", "");*/
            
            /* remove all xml tags */
            content = removeTagWithContents(content, "xml");
            content = removeTagWithContents(content, "style");
            content = removeAttribute(content, "style");
            content = removeAttribute(content, "border");
            content = removeTagLeaveContents(content, "font");
            
            entry.setContent(content);
            BlogsEntryLocalServiceUtil.updateBlogsEntry(entry);
        }
        return ContestLocalServiceUtil.findByActiveFeatured(true, true);
    }
    
    private String removeTagWithContents(String content, String tagName) {
        int startPos = content.indexOf("<" + tagName);
        while (startPos >= 0) {
            int endPos = content.indexOf("</" + tagName, startPos);
            endPos = content.indexOf(">", endPos) + 1;
            
            content = content.substring(0, startPos) + content.substring(endPos);
            startPos = content.indexOf("<" + tagName);
        }
            
        
        return content;
    }
    
    private String removeTagLeaveContents(String content, String tagName) {
        int startPos = content.indexOf("<" + tagName);
        while (startPos >= 0) {
            int endPos = content.indexOf(">", startPos) + 1;
            
            content = content.substring(0, startPos) + content.substring(endPos);
            startPos = content.indexOf("<" + tagName);
        }
        startPos = content.indexOf("</" + tagName);
        while (startPos >= 0) {
            int endPos = content.indexOf(">", startPos) + 1;
            
            content = content.substring(0, startPos) + content.substring(endPos);
            startPos = content.indexOf("</" + tagName);
        }
            
        
        return content;
    }
    
    private String removeAttribute(String content, String name) {
        return content.replaceAll(name + "=\"[^\"]*\"", "");
        
    }
    
    public ThemeDisplay getThemeDisplay() {
        return Helper.getThemeDisplay();
    }
    

}
