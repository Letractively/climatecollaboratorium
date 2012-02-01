package com.ext.portlet.surveys.service.base;

import com.ext.portlet.surveys.model.Survey;
import com.ext.portlet.surveys.service.SurveyLocalService;
import com.ext.portlet.surveys.service.SurveyService;
import com.ext.portlet.surveys.service.UserSurveyLocalService;
import com.ext.portlet.surveys.service.UserSurveyService;
import com.ext.portlet.surveys.service.persistence.SurveyPersistence;
import com.ext.portlet.surveys.service.persistence.UserSurveyPersistence;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.util.PortalUtil;

import java.util.List;


public abstract class SurveyLocalServiceBaseImpl implements SurveyLocalService {
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

    public Survey addSurvey(Survey survey) throws SystemException {
        survey.setNew(true);

        return surveyPersistence.update(survey, false);
    }

    public Survey createSurvey(String eventName) {
        return surveyPersistence.create(eventName);
    }

    public void deleteSurvey(String eventName)
        throws PortalException, SystemException {
        surveyPersistence.remove(eventName);
    }

    public void deleteSurvey(Survey survey) throws SystemException {
        surveyPersistence.remove(survey);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return surveyPersistence.findWithDynamicQuery(dynamicQuery);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
        int end) throws SystemException {
        return surveyPersistence.findWithDynamicQuery(dynamicQuery, start, end);
    }

    public Survey getSurvey(String eventName)
        throws PortalException, SystemException {
        return surveyPersistence.findByPrimaryKey(eventName);
    }

    public List<Survey> getSurveies(int start, int end)
        throws SystemException {
        return surveyPersistence.findAll(start, end);
    }

    public int getSurveiesCount() throws SystemException {
        return surveyPersistence.countAll();
    }

    public Survey updateSurvey(Survey survey) throws SystemException {
        survey.setNew(false);

        return surveyPersistence.update(survey, true);
    }

    public Survey updateSurvey(Survey survey, boolean merge)
        throws SystemException {
        survey.setNew(false);

        return surveyPersistence.update(survey, merge);
    }

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
