package com.ext.portlet.facebook.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="UserFacebookMappingSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.facebook.service.http.UserFacebookMappingServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.facebook.service.http.UserFacebookMappingServiceSoap
 *
 */
public class UserFacebookMappingSoap implements Serializable {
    private Long _userId;
    private String _facebookId;

    public UserFacebookMappingSoap() {
    }

    public static UserFacebookMappingSoap toSoapModel(UserFacebookMapping model) {
        UserFacebookMappingSoap soapModel = new UserFacebookMappingSoap();

        soapModel.setUserId(model.getUserId());
        soapModel.setFacebookId(model.getFacebookId());

        return soapModel;
    }

    public static UserFacebookMappingSoap[] toSoapModels(
        UserFacebookMapping[] models) {
        UserFacebookMappingSoap[] soapModels = new UserFacebookMappingSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static UserFacebookMappingSoap[][] toSoapModels(
        UserFacebookMapping[][] models) {
        UserFacebookMappingSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new UserFacebookMappingSoap[models.length][models[0].length];
        } else {
            soapModels = new UserFacebookMappingSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static UserFacebookMappingSoap[] toSoapModels(
        List<UserFacebookMapping> models) {
        List<UserFacebookMappingSoap> soapModels = new ArrayList<UserFacebookMappingSoap>(models.size());

        for (UserFacebookMapping model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new UserFacebookMappingSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _userId;
    }

    public void setPrimaryKey(Long pk) {
        setUserId(pk);
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }

    public String getFacebookId() {
        return _facebookId;
    }

    public void setFacebookId(String facebookId) {
        _facebookId = facebookId;
    }
}
