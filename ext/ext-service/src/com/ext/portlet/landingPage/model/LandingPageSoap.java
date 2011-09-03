package com.ext.portlet.landingPage.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <a href="LandingPageSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.landingPage.service.http.LandingPageServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.landingPage.service.http.LandingPageServiceSoap
 *
 */
public class LandingPageSoap implements Serializable {
    private Long _id;
    private String _baseUrl;
    private String _targetUrl;
    private Date _updated;

    public LandingPageSoap() {
    }

    public static LandingPageSoap toSoapModel(LandingPage model) {
        LandingPageSoap soapModel = new LandingPageSoap();

        soapModel.setId(model.getId());
        soapModel.setBaseUrl(model.getBaseUrl());
        soapModel.setTargetUrl(model.getTargetUrl());
        soapModel.setUpdated(model.getUpdated());

        return soapModel;
    }

    public static LandingPageSoap[] toSoapModels(LandingPage[] models) {
        LandingPageSoap[] soapModels = new LandingPageSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static LandingPageSoap[][] toSoapModels(LandingPage[][] models) {
        LandingPageSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new LandingPageSoap[models.length][models[0].length];
        } else {
            soapModels = new LandingPageSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static LandingPageSoap[] toSoapModels(List<LandingPage> models) {
        List<LandingPageSoap> soapModels = new ArrayList<LandingPageSoap>(models.size());

        for (LandingPage model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new LandingPageSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _id;
    }

    public void setPrimaryKey(Long pk) {
        setId(pk);
    }

    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        _id = id;
    }

    public String getBaseUrl() {
        return _baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        _baseUrl = baseUrl;
    }

    public String getTargetUrl() {
        return _targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        _targetUrl = targetUrl;
    }

    public Date getUpdated() {
        return _updated;
    }

    public void setUpdated(Date updated) {
        _updated = updated;
    }
}
