package com.climatecollaboratorium.navigation;

import java.util.Map;

import com.liferay.portal.kernel.portlet.BaseFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;

public class SharedFriendlyURLMapper extends BaseFriendlyURLMapper{
    private static final String _MAPPING = "shared";

    private static final String _PORTLET_ID = "sharedcollabfacelets_WAR_sharedcollabfaceletsportlet";

    
    @Override
    public String getPortletId() {
        return _PORTLET_ID;
    }

    @Override
    public String buildPath(LiferayPortletURL portletURL) {
        // TODO Auto-generated method stub
        ///System.out.println("Liferay portlet url: " + portletURL);
        return "/shared/";
    }

    @Override
    public String getMapping() {
        return _MAPPING;
    }

    @Override
    public void populateParams(String friendlyURLPath, Map<String, String[]> params) {
        System.out.println("friendlyURLPath: " + friendlyURLPath);
        System.out.println("params: " + params);
        
        addParam(params, "p_p_id", _PORTLET_ID);
        //addParam(params, "p_p_lifecycle", "2");
        addParam(params, "planId", "3333");

        params.put("planId", new String[] {"3306"});
        
        System.out.println(params);
        
        
    }
    
    

}
