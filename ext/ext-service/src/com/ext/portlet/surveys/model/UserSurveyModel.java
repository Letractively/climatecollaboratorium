package com.ext.portlet.surveys.model;

import com.ext.portlet.surveys.service.persistence.UserSurveyPK;

import com.liferay.portal.model.BaseModel;

import java.util.Date;


/**
 * <a href="UserSurveyModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>UserSurvey</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.surveys.model.UserSurvey
 * @see com.ext.portlet.surveys.model.impl.UserSurveyImpl
 * @see com.ext.portlet.surveys.model.impl.UserSurveyModelImpl
 *
 */
public interface UserSurveyModel extends BaseModel<UserSurvey> {
    public UserSurveyPK getPrimaryKey();

    public void setPrimaryKey(UserSurveyPK pk);

    public Long getUserId();

    public void setUserId(Long userId);

    public String getEventName();

    public void setEventName(String eventName);

    public Date getCreateDate();

    public void setCreateDate(Date createDate);

    public UserSurvey toEscapedModel();
}
