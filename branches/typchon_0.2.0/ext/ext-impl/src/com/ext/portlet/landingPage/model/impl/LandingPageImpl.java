package com.ext.portlet.landingPage.model.impl;

import com.ext.portlet.landingPage.model.LandingPage;
import com.ext.portlet.landingPage.service.LandingPageLocalServiceUtil;
import com.liferay.portal.SystemException;


public class LandingPageImpl extends LandingPageModelImpl implements LandingPage {
    public LandingPageImpl() {
    }
    
    public void store() throws SystemException {
        if (isNew()) {
            LandingPageLocalServiceUtil.addLandingPage(this);
        }
        else {
            LandingPageLocalServiceUtil.updateLandingPage(this);
        }
    }
}
