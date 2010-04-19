package org.climatecollaboratorium.debates.bean.backing;

import com.ext.portlet.debaterevision.model.DebateItem;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.ratings.model.RatingsStats;
import com.liferay.portlet.ratings.service.RatingsStatsLocalServiceUtil;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {
    public static int getDebateItemVotes(DebateItem debateItem) throws SystemException {
        RatingsStats ratingsStats =
            RatingsStatsLocalServiceUtil.getStats(DebateItem.class.getName(), debateItem.getDebateItemId());
        return ratingsStats.getTotalEntries();
    }

    //The user info is accessible using standard Portlet and JSF mechanisms
    public static Map getUserInfoMap(){
        Map requestMap = getRequestMap();
        Object obj = requestMap.get(PortletRequest.USER_INFO);
        


        if ( obj != null && obj instanceof Map){
            return (Map)obj;
        }
        return null;
        
    }
    

    
    public static boolean isUserLoggedIn() {
        
        return getUserInfoMap() != null;
    }

    public static Object getLiferayUserInfo(String key) {
        return getUserInfoMap().get(key);
    }

    //The keys in the user info map are specific to Liferay
    public static String getLiferayUserId(){
        return (String)getLiferayUserInfo("liferay.user.id");
    }

    public static String getLiferayCompanyId(){
        return (String)getLiferayUserInfo("liferay.company.id");
    }

    public static String getLiferayUserEmail(){
        return getLiferayUser().getEmailAddress();
    }

    //Since the email is not in the user info map, we need to use
    //a Liferay API to get that information.
    public static User getLiferayUser(){
        try {
            return UserLocalServiceUtil.getUserById(Long.parseLong(getLiferayUserId()));
        } catch (com.liferay.portal.PortalException e) {
            e.printStackTrace();
        } catch (com.liferay.portal.SystemException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static ThemeDisplay getThemeDisplay() {
        Map map = getRequestMap();
        return (ThemeDisplay) map.get(WebKeys.THEME_DISPLAY);
    }
    
    public static PermissionChecker getPermissionChecker() {
        return getThemeDisplay().getPermissionChecker();
    }
    
    public static String getPortletID() {
        Map map = getRequestMap();
        return (String) map.get(WebKeys.PORTLET_ID);
    }
    
    private static Map getRequestMap() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        Map map = ec.getRequestMap();
        
        /*
        System.out.println("\n\n*********************\n\n");
        for (Object key: map.keySet()) {
            System.out.println(key);
        }
        */
        return map;
    }
    
    public static PortletPreferences getPortletPrefs(){
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        PortletRequest pReq = (PortletRequest)ec.getRequest();
        PortletPreferences prefs = pReq.getPreferences();
        return prefs;
    }
    
    
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
        Pattern pattern = Pattern.compile("[^\\'\\\"]http://[^\\s]*[^\\'\\\"]");
        Matcher matcher = pattern.matcher(content);
        StringBuilder strBuilder = new StringBuilder();
        
        int lastIndex = 0;
        while (matcher.find()) {
            strBuilder.append(content.substring(lastIndex, matcher.start()));
            String url = content.substring(matcher.start(), matcher.end() - 1);
            strBuilder.append(createLink(url, url));
            
            strBuilder.append(content.substring(matcher.end(), matcher.end()));
            lastIndex = matcher.end();
        }
        

        strBuilder.append(content.substring(lastIndex));
        return strBuilder.toString();
    }
    
    private static String createLink(String url, String desc) {
        return "<a href='" + url + "'>" + desc + "</a>";
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