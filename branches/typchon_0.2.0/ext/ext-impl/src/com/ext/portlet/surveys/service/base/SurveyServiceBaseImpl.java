package com.ext.portlet.surveys.service.base;

import com.ext.portlet.surveys.service.SurveyLocalService;
import com.ext.portlet.surveys.service.SurveyService;
import com.ext.portlet.surveys.service.UserSurveyLocalService;
import com.ext.portlet.surveys.service.UserSurveyService;
import com.ext.portlet.surveys.service.persistence.SurveyPersistence;
import com.ext.portlet.surveys.service.persistence.UserSurveyPersistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.service.base.PrincipalBean;
import com.liferay.portal.util.PortalUtil;


public abstract class SurveyServiceBaseImpl extends PrincipalBean
    implements SurveyService {
    @BeanReference(name = "com.ext.portlet.surveys.service.SurveyLocalService.impl")
    protected SurveyLocalService surveyLocalService;
    @BeanReference(name = "com.ext.portlet.surveys.service.SurveyService.impl")
    protected SurveyService surveyService;
    @BeanReference(name = "com.ext.portlet.surveys.service.persistence.SurveyPersistence.impl")
    protected SurveyPersistence surveyPersistence;
    @BeanReference(name = "com.ext.portlet.surveys.service.UserSurveyLocalService.impl")
    protected UserSurveyLocalService userSurveyLocalService;
    @BeanReference(name = "com.ext.portlet.surveys.service.UserSurveyService.impl")
    protected UserSurveyService userSurveyService;
    @BeanReference(name = "com.ext.portlet.surveys.service.persistence.UserSurveyPersistence.impl")
    protected UserSurveyPersistence userSurveyPersistence;

    public SurveyLocalService getSurveyLocalService() {
        return surveyLocalService;
    }

    public void setSurveyLocalService(SurveyLocalService surveyLocalService) {
        this.surveyLocalService = surveyLocalService;
    }

    public SurveyService getSurveyService() {
        return surveyService;
    }

    public void setSurveyService(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    public SurveyPersistence getSurveyPersistence() {
        return surveyPersistence;
    }

    public void setSurveyPersistence(SurveyPersistence surveyPersistence) {
        this.surveyPersistence = surveyPersistence;
    }

    public UserSurveyLocalService getUserSurveyLocalService() {
        return userSurveyLocalService;
    }

    public void setUserSurveyLocalService(
        UserSurveyLocalService userSurveyLocalService) {
        this.userSurveyLocalService = userSurveyLocalService;
    }

    public UserSurveyService getUserSurveyService() {
        return userSurveyService;
    }

    public void setUserSurveyService(UserSurveyService userSurveyService) {
        this.userSurveyService = userSurveyService;
    }

    public UserSurveyPersistence getUserSurveyPersistence() {
        return userSurveyPersistence;
    }

    public void setUserSurveyPersistence(
        UserSurveyPersistence userSurveyPersistence) {
        this.userSurveyPersistence = userSurveyPersistence;
    }

    protected void runSQL(String sql) throws SystemException {
        try {
            PortalUtil.runSQL(sql);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
