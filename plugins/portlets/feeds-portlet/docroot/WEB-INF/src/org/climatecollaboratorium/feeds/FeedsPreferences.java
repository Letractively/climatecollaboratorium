package org.climatecollaboratorium.feeds;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

import com.liferay.portal.model.User;

public class FeedsPreferences {

    private int feedSize;
    private FeedType feedType;
    private String feedTitle;

    
    private final static String FEED_SIZE_PREF = "FEED_SIZE";
    private final static String FEED_TITLE_PREF = "FEED_TITLE";
    private final static String FEED_TYPE_PREF = "FEED_TYPE";
    
    
    private final static int defaultFeedSize = 20;
    private final static String defaultFeedTitle = null;
    private final static FeedType defaultFeedType = FeedType.ACTIVITIES;
        
    public FeedsPreferences() {
        PortletPreferences prefs = Helper.getPortletPrefs();
        
        feedSize = defaultFeedSize; 
        try {
            feedSize = Integer.parseInt(prefs.getValue(FEED_SIZE_PREF, String.valueOf(defaultFeedSize)));
        }
        catch (NumberFormatException e) {
            // ignore
        }
        
        feedType = defaultFeedType;
        try {
            feedType = FeedType.valueOf(prefs.getValue(FEED_TYPE_PREF, defaultFeedType.name()));
        }
        catch (Exception e) {
            // ignore
        }
        
        feedTitle = prefs.getValue(FEED_TITLE_PREF, defaultFeedTitle);
        if (feedTitle == null) {
            feedTitle = feedType.getDescription();
        }        
    }

    
    public String submit() throws ReadOnlyException, ValidatorException, IOException {
        
        PortletPreferences prefs = Helper.getPortletPrefs();
        prefs.setValue(FEED_SIZE_PREF, String.valueOf(feedSize));
        prefs.setValue(FEED_TITLE_PREF, feedTitle);
        prefs.setValue(FEED_TYPE_PREF, feedType.name());

        prefs.store();
            
        
        FacesMessage fm = new FacesMessage();
        fm.setSummary("Settings saved successfully");
        fm.setSeverity(FacesMessage.SEVERITY_INFO);

        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, fm);
        
        return null;
    }


    public int getFeedSize() {
        return feedSize;
    }


    public FeedType getFeedType() {
        return feedType;
    }


    public String getFeedTitle() {
        return feedTitle;
    }


    public void setFeedSize(int feedSize) {
        this.feedSize = feedSize;
    }


    public void setFeedType(FeedType feedType) {
        this.feedType = feedType;
    }


    public void setFeedTitle(String feedTitle) {
        this.feedTitle = feedTitle;
    }
    
    
    public List<SelectItem> getFeedTypes() {
        List<SelectItem> ret = new ArrayList<SelectItem>();
        
        for (FeedType type: FeedType.values()) {
            ret.add(new SelectItem(type, type.name()));
        }
        return ret;
    }
}
