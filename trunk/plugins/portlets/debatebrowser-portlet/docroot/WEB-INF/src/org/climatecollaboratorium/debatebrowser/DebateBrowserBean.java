package org.climatecollaboratorium.debatebrowser;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.climatecollaboratorium.debatebrowser.utils.Helper;


import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.model.DebateCategory;
import com.ext.portlet.debaterevision.service.DebateCategoryLocalServiceUtil;
import com.ext.portlet.debaterevision.service.DebateLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

public class DebateBrowserBean {
    private List<DebateCategory> categories = new ArrayList<DebateCategory>();
    private DebateCategory category;
    private Debate debate;
    private DebateBrowserPageType pageType = DebateBrowserPageType.CATEGORIES;
    
    public DebateBrowserBean() throws SystemException, PortalException {
        for (DebateCategory cat : DebateCategoryLocalServiceUtil.getDebateCategories(0, Integer.MAX_VALUE)) {
            categories.add(cat);
        }
        Map<String, String> params = Helper.getUrlParametersMap();
        
        
        System.out.println(params);
        if (params.containsKey("categoryId")) {
            Long categoryId = Long.parseLong(params.get("categoryId"));
            category = DebateCategoryLocalServiceUtil.getDebateCategory(categoryId);
            pageType = DebateBrowserPageType.CATEGORY_DEBATES;
            
            if (params.containsKey("debateId")) {
                Long debateId = Long.parseLong(params.get("debateId"));
                debate = DebateLocalServiceUtil.getDebate(debateId);
                pageType = DebateBrowserPageType.DEBATE_DETAILS;
            }
        }
    }

    public List<DebateCategory> getCategories() {
        return categories;
    }

    
    public DebateCategory getCategory() throws SystemException {
        return category;
    }
    
    public Debate getDebate() throws SystemException {
        return debate;
    }
    
    public DebateBrowserPageType getPageType() {
        return pageType;
    }

}
