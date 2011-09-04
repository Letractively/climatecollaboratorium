package com.ext.portlet.twitter.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="UserTwitterMappingSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.twitter.service.http.UserTwitterMappingServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.twitter.service.http.UserTwitterMappingServiceSoap
 *
 */
public class UserTwitterMappingSoap implements Serializable {
    private Long _twitterId;
    private Long _userId;

    public UserTwitterMappingSoap() {
    }

    public static UserTwitterMappingSoap toSoapModel(UserTwitterMapping model) {
        UserTwitterMappingSoap soapModel = new UserTwitterMappingSoap();

        soapModel.setTwitterId(model.getTwitterId());
        soapModel.setUserId(model.getUserId());

        return soapModel;
    }

    public static UserTwitterMappingSoap[] toSoapModels(
        UserTwitterMapping[] models) {
        UserTwitterMappingSoap[] soapModels = new UserTwitterMappingSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static UserTwitterMappingSoap[][] toSoapModels(
        UserTwitterMapping[][] models) {
        UserTwitterMappingSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new UserTwitterMappingSoap[models.length][models[0].length];
        } else {
            soapModels = new UserTwitterMappingSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static UserTwitterMappingSoap[] toSoapModels(
        List<UserTwitterMapping> models) {
        List<UserTwitterMappingSoap> soapModels = new ArrayList<UserTwitterMappingSoap>(models.size());

        for (UserTwitterMapping model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new UserTwitterMappingSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _twitterId;
    }

    public void setPrimaryKey(Long pk) {
        setTwitterId(pk);
    }

    public Long getTwitterId() {
        return _twitterId;
    }

    public void setTwitterId(Long twitterId) {
        _twitterId = twitterId;
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }
}
