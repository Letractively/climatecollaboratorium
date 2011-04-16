package com.ext.portlet.surveys.model;

import com.ext.portlet.surveys.service.persistence.UserSurveyPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <a href="UserSurveySoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.surveys.service.http.UserSurveyServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.surveys.service.http.UserSurveyServiceSoap
 *
 */
public class UserSurveySoap implements Serializable {
    private Long _userId;
    private String _eventName;
    private Date _createDate;

    public UserSurveySoap() {
    }

    public static UserSurveySoap toSoapModel(UserSurvey model) {
        UserSurveySoap soapModel = new UserSurveySoap();

        soapModel.setUserId(model.getUserId());
        soapModel.setEventName(model.getEventName());
        soapModel.setCreateDate(model.getCreateDate());

        return soapModel;
    }

    public static UserSurveySoap[] toSoapModels(UserSurvey[] models) {
        UserSurveySoap[] soapModels = new UserSurveySoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static UserSurveySoap[][] toSoapModels(UserSurvey[][] models) {
        UserSurveySoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new UserSurveySoap[models.length][models[0].length];
        } else {
            soapModels = new UserSurveySoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static UserSurveySoap[] toSoapModels(List<UserSurvey> models) {
        List<UserSurveySoap> soapModels = new ArrayList<UserSurveySoap>(models.size());

        for (UserSurvey model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new UserSurveySoap[soapModels.size()]);
    }

    public UserSurveyPK getPrimaryKey() {
        return new UserSurveyPK(_userId, _eventName);
    }

    public void setPrimaryKey(UserSurveyPK pk) {
        setUserId(pk.userId);
        setEventName(pk.eventName);
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }

    public String getEventName() {
        return _eventName;
    }

    public void setEventName(String eventName) {
        _eventName = eventName;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }
}
