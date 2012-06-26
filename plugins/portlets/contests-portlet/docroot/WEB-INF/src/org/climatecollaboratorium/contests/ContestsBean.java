package org.climatecollaboratorium.contests;

import java.util.List;

import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.service.ContestLocalServiceUtil;
import com.liferay.portal.SystemException;
import com.liferay.portal.theme.ThemeDisplay;

public class ContestsBean {
    
    public List<Contest> getContests() throws SystemException {
        return ContestLocalServiceUtil.findByActiveFeatured(true, true);
    }
    
    public ThemeDisplay getThemeDisplay() {
        return Helper.getThemeDisplay();
    }
    

}
