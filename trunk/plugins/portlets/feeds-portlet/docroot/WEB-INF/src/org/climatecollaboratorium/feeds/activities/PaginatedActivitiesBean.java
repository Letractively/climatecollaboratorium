package org.climatecollaboratorium.feeds.activities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.faces.model.DataModel;

import org.climatecollaboratorium.feeds.FeedsPreferences;
import org.climatecollaboratorium.feeds.Helper;
import org.climatecollaboratorium.feeds.activities.utils.DataPage;
import org.climatecollaboratorium.feeds.activities.utils.DataSource;
import org.climatecollaboratorium.feeds.activities.utils.PagedListDataModel;

import com.ext.portlet.Activity.ActivityUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portlet.social.model.SocialActivity;

public class PaginatedActivitiesBean extends DataSource {

    /**
     * Max query to handle the bug with no activity body - sufficiently high number that we're very likely to get enough
     * non-null entres to meet the feedSize param
     */
    private boolean showAdmin = true;
    private String feedStyle = "FULL";
    private List<SocialActivityWrapper> activities;

    public PaginatedActivitiesBean(FeedsPreferences preferences) {
        super("");
        
        showAdmin = !preferences.getRemoveAdmin();
        feedStyle = preferences.getFeedStyle();

    }

    public void setFeedStyle(String feedStyle) {
        this.feedStyle = feedStyle;
    }

    public String getFeedStyle() {
        return feedStyle;
    }

    @Override
    protected boolean isDefaultAscending(String sortColumn) {
        // TODO Auto-generated method stub
        return false;
    }
    
    /**
     * Bound to DataTable value in the ui.
     */
    public DataModel getData() {
        if(onePageDataModel == null){
            onePageDataModel = new LocalDataModel(pageSize);
        }
        return onePageDataModel;
    }
    
    /**
     * This is where the Customer data is retrieved from the database and
     * returned as a list of CustomerBean objects for display in the UI.
     * @throws IOException 
     * @throws PortalException 
     * @throws SystemException 
     */
    private DataPage getDataPage(int startRow, int pageSize) throws IOException, SystemException, PortalException {
        int totalActivitiesCount = ActivityUtil.getAllActivitiesCount();

        // Calculate indices to be displayed in the ui.
        int endIndex = startRow + pageSize;
        if (endIndex > totalActivitiesCount) {
            endIndex = totalActivitiesCount;
        }

        List<SocialActivityWrapper> items = new ArrayList<SocialActivityWrapper>();
        
        int lastDaysBetween = -1;
        Date now = new Date();
        
        for (SocialActivity activity: ActivityUtil.retrieveAllActivities(startRow, endIndex)) {
            if (SocialActivityWrapper.isEmpty(activity) || (!showAdmin && Helper.isUserAdmin(activity.getUserId()))) {
                continue;
            }
            int curDaysBetween = DateUtil.getDaysBetween(activity.getCreateDate(), now, TimeZone.getDefault());
            lastDaysBetween = curDaysBetween;
            items.add(new SocialActivityWrapper(activity, curDaysBetween, lastDaysBetween < curDaysBetween));
        }

        return new DataPage(totalActivitiesCount, startRow, items);
    }

    private class LocalDataModel extends PagedListDataModel {
        public LocalDataModel(int pageSize) {
            super(pageSize);
        }

        public DataPage fetchPage(int startRow, int pageSize) {
            // call enclosing managed bean method to fetch the data
            try {
                return getDataPage(startRow, pageSize);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return new DataPage(0, 0, new ArrayList<SocialActivityWrapper>());
        }
    }
}