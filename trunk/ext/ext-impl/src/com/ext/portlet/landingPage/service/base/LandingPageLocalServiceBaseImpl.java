package com.ext.portlet.landingPage.service.base;

import com.ext.portlet.landingPage.model.LandingPage;
import com.ext.portlet.landingPage.service.LandingPageLocalService;
import com.ext.portlet.landingPage.service.LandingPageService;
import com.ext.portlet.landingPage.service.persistence.LandingPagePersistence;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.util.PortalUtil;

import java.util.List;


public abstract class LandingPageLocalServiceBaseImpl
    implements LandingPageLocalService {
    @BeanReference(name = "com.ext.portlet.landingPage.service.LandingPageLocalService.impl")
    protected LandingPageLocalService landingPageLocalService;
    @BeanReference(name = "com.ext.portlet.landingPage.service.LandingPageService.impl")
    protected LandingPageService landingPageService;
    @BeanReference(name = "com.ext.portlet.landingPage.service.persistence.LandingPagePersistence.impl")
    protected LandingPagePersistence landingPagePersistence;

    public LandingPage addLandingPage(LandingPage landingPage)
        throws SystemException {
        landingPage.setNew(true);

        return landingPagePersistence.update(landingPage, false);
    }

    public LandingPage createLandingPage(Long id) {
        return landingPagePersistence.create(id);
    }

    public void deleteLandingPage(Long id)
        throws PortalException, SystemException {
        landingPagePersistence.remove(id);
    }

    public void deleteLandingPage(LandingPage landingPage)
        throws SystemException {
        landingPagePersistence.remove(landingPage);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return landingPagePersistence.findWithDynamicQuery(dynamicQuery);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
        int end) throws SystemException {
        return landingPagePersistence.findWithDynamicQuery(dynamicQuery, start,
            end);
    }

    public LandingPage getLandingPage(Long id)
        throws PortalException, SystemException {
        return landingPagePersistence.findByPrimaryKey(id);
    }

    public List<LandingPage> getLandingPages(int start, int end)
        throws SystemException {
        return landingPagePersistence.findAll(start, end);
    }

    public int getLandingPagesCount() throws SystemException {
        return landingPagePersistence.countAll();
    }

    public LandingPage updateLandingPage(LandingPage landingPage)
        throws SystemException {
        landingPage.setNew(false);

        return landingPagePersistence.update(landingPage, true);
    }

    public LandingPage updateLandingPage(LandingPage landingPage, boolean merge)
        throws SystemException {
        landingPage.setNew(false);

        return landingPagePersistence.update(landingPage, merge);
    }

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
