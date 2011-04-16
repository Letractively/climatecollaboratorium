package com.ext.portlet.surveys.service.impl;

import java.util.List;

import com.ext.portlet.surveys.NoSuchSurveyException;
import com.ext.portlet.surveys.model.Survey;
import com.ext.portlet.surveys.service.base.SurveyServiceBaseImpl;
import com.liferay.portal.SystemException;


public class SurveyServiceImpl extends SurveyServiceBaseImpl {
    
    public List<Survey> getSurveys() throws SystemException {
        return this.surveyLocalService.getSurveies(0, Integer.MAX_VALUE);
    }
    
    public Survey getSurvey(String eventName) throws NoSuchSurveyException, SystemException {
        return this.surveyPersistence.findByPrimaryKey(eventName);
    }
}
