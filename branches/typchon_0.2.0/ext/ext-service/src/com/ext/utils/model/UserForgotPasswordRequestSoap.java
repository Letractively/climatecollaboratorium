package com.ext.utils.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <a href="UserForgotPasswordRequestSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.utils.service.http.UserForgotPasswordRequestServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.utils.service.http.UserForgotPasswordRequestServiceSoap
 *
 */
public class UserForgotPasswordRequestSoap implements Serializable {
    private String _token;
    private Long _userId;
    private Date _created;

    public UserForgotPasswordRequestSoap() {
    }

    public static UserForgotPasswordRequestSoap toSoapModel(
        UserForgotPasswordRequest model) {
        UserForgotPasswordRequestSoap soapModel = new UserForgotPasswordRequestSoap();

        soapModel.setToken(model.getToken());
        soapModel.setUserId(model.getUserId());
        soapModel.setCreated(model.getCreated());

        return soapModel;
    }

    public static UserForgotPasswordRequestSoap[] toSoapModels(
        UserForgotPasswordRequest[] models) {
        UserForgotPasswordRequestSoap[] soapModels = new UserForgotPasswordRequestSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static UserForgotPasswordRequestSoap[][] toSoapModels(
        UserForgotPasswordRequest[][] models) {
        UserForgotPasswordRequestSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new UserForgotPasswordRequestSoap[models.length][models[0].length];
        } else {
            soapModels = new UserForgotPasswordRequestSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static UserForgotPasswordRequestSoap[] toSoapModels(
        List<UserForgotPasswordRequest> models) {
        List<UserForgotPasswordRequestSoap> soapModels = new ArrayList<UserForgotPasswordRequestSoap>(models.size());

        for (UserForgotPasswordRequest model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new UserForgotPasswordRequestSoap[soapModels.size()]);
    }

    public String getPrimaryKey() {
        return _token;
    }

    public void setPrimaryKey(String pk) {
        setToken(pk);
    }

    public String getToken() {
        return _token;
    }

    public void setToken(String token) {
        _token = token;
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        _created = created;
    }
}
