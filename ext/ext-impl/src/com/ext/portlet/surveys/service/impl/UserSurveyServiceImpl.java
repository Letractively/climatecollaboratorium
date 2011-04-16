package com.ext.portlet.surveys.service.impl;

import java.util.Date;
import java.util.List;

import com.ext.portlet.surveys.model.UserSurvey;
import com.ext.portlet.surveys.service.UserSurveyLocalServiceUtil;
import com.ext.portlet.surveys.service.base.UserSurveyServiceBaseImpl;
import com.ext.portlet.surveys.service.persistence.UserSurveyPK;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;


public class UserSurveyServiceImpl extends UserSurveyServiceBaseImpl {    
    
    public boolean hasUserViewedSurveyForEvent(Long userId, String event) throws SystemException {
        List<UserSurvey> us = this.userSurveyPersistence.findByUserIdEventName(userId, event);
        return us.size() > 0;
    }
    
    public void setUserViewedSurvey(Long userId, String eventName) throws SystemException {
        UserSurveyPK userSurveyPk = new UserSurveyPK(userId, eventName);
        UserSurvey userSurvey = UserSurveyLocalServiceUtil.createUserSurvey(userSurveyPk);
        userSurvey.setCreateDate(new Date());
        this.userSurveyLocalService.addUserSurvey(userSurvey);
    }
    
    public void removeUserViewedSurvey(Long userId, String eventName) throws SystemException, PortalException {
        UserSurveyPK userSurveyPK = new UserSurveyPK(userId, eventName);
        
        this.userSurveyLocalService.deleteUserSurvey(userSurveyPK);
    }
    
}
