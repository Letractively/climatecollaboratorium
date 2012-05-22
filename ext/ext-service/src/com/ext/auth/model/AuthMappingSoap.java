package com.ext.auth.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="AuthMappingSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.auth.service.http.AuthMappingServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.auth.service.http.AuthMappingServiceSoap
 *
 */
public class AuthMappingSoap implements Serializable {
    private String _identifier;
    private Long _userId;

    public AuthMappingSoap() {
    }

    public static AuthMappingSoap toSoapModel(AuthMapping model) {
        AuthMappingSoap soapModel = new AuthMappingSoap();

        soapModel.setIdentifier(model.getIdentifier());
        soapModel.setUserId(model.getUserId());

        return soapModel;
    }

    public static AuthMappingSoap[] toSoapModels(AuthMapping[] models) {
        AuthMappingSoap[] soapModels = new AuthMappingSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static AuthMappingSoap[][] toSoapModels(AuthMapping[][] models) {
        AuthMappingSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new AuthMappingSoap[models.length][models[0].length];
        } else {
            soapModels = new AuthMappingSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static AuthMappingSoap[] toSoapModels(List<AuthMapping> models) {
        List<AuthMappingSoap> soapModels = new ArrayList<AuthMappingSoap>(models.size());

        for (AuthMapping model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new AuthMappingSoap[soapModels.size()]);
    }

    public String getPrimaryKey() {
        return _identifier;
    }

    public void setPrimaryKey(String pk) {
        setIdentifier(pk);
    }

    public String getIdentifier() {
        return _identifier;
    }

    public void setIdentifier(String identifier) {
        _identifier = identifier;
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }
}
