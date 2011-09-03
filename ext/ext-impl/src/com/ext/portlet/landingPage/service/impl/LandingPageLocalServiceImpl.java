package com.ext.portlet.landingPage.service.impl;

import java.util.Date;

import com.ext.portlet.landingPage.model.LandingPage;
import com.ext.portlet.landingPage.service.base.LandingPageLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.SystemException;


public class LandingPageLocalServiceImpl extends LandingPageLocalServiceBaseImpl {
    public LandingPage createNewLandingPage(String baseUrl, String targetUrl) throws SystemException {
        Long id = CounterLocalServiceUtil.increment(LandingPage.class.getName());
        
        LandingPage lp = createLandingPage(id);
        lp.setUpdated(new Date());
        lp.setBaseUrl(baseUrl);
        lp.setTargetUrl(targetUrl);
        
        lp.store();
        
        return lp;
        
    }
}
