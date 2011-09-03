package com.ext.portlet.landingPage.service.base;

import com.ext.portlet.landingPage.service.LandingPageLocalService;
import com.ext.portlet.landingPage.service.LandingPageService;
import com.ext.portlet.landingPage.service.persistence.LandingPagePersistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.service.base.PrincipalBean;
import com.liferay.portal.util.PortalUtil;


public abstract class LandingPageServiceBaseImpl extends PrincipalBean
    implements LandingPageService {
    @BeanReference(name = "com.ext.portlet.landingPage.service.LandingPageLocalService.impl")
    protected LandingPageLocalService landingPageLocalService;
    @BeanReference(name = "com.ext.portlet.landingPage.service.LandingPageService.impl")
    protected LandingPageService landingPageService;
    @BeanReference(name = "com.ext.portlet.landingPage.service.persistence.LandingPagePersistence.impl")
    protected LandingPagePersistence landingPagePersistence;

    public LandingPageLocalService getLandingPageLocalService() {
        return landingPageLocalService;
    }

    public void setLandingPageLocalService(
        LandingPageLocalService landingPageLocalService) {
        this.landingPageLocalService = landingPageLocalService;
    }

    public LandingPageService getLandingPageService() {
        return landingPageService;
    }

    public void setLandingPageService(LandingPageService landingPageService) {
        this.landingPageService = landingPageService;
    }

    public LandingPagePersistence getLandingPagePersistence() {
        return landingPagePersistence;
    }

    public void setLandingPagePersistence(
        LandingPagePersistence landingPagePersistence) {
        this.landingPagePersistence = landingPagePersistence;
    }

    protected void runSQL(String sql) throws SystemException {
        try {
            PortalUtil.runSQL(sql);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
