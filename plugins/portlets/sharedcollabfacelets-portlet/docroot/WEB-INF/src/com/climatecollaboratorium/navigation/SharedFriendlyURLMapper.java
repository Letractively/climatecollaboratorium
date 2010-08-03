package com.climatecollaboratorium.navigation;

import java.util.Map;

import com.liferay.portal.kernel.portlet.BaseFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;

public class SharedFriendlyURLMapper extends BaseFriendlyURLMapper{
    private static final String _MAPPING = "shared";

    private static final String _PORTLET_ID = "sharedcollabfacelets";

    
    @Override
    public String getPortletId() {
        return _PORTLET_ID;
    }

    @Override
    public String buildPath(LiferayPortletURL portletURL) {
        // TODO Auto-generated method stub
        return "/shared/";
    }

    @Override
    public String getMapping() {
        return _MAPPING;
    }

    @Override
    public void populateParams(String friendlyURLPath, Map<String, String[]> params) {
        System.out.println("friendlyURLPath: " + friendlyURLPath);
    }
    
    

}
