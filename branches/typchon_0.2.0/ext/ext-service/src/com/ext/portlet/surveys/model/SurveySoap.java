package com.ext.portlet.surveys.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="SurveySoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.surveys.service.http.SurveyServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.surveys.service.http.SurveyServiceSoap
 *
 */
public class SurveySoap implements Serializable {
    private String _eventName;
    private String _description;
    private String _url;
    private String _type;

    public SurveySoap() {
    }

    public static SurveySoap toSoapModel(Survey model) {
        SurveySoap soapModel = new SurveySoap();

        soapModel.setEventName(model.getEventName());
        soapModel.setDescription(model.getDescription());
        soapModel.setUrl(model.getUrl());
        soapModel.setType(model.getType());

        return soapModel;
    }

    public static SurveySoap[] toSoapModels(Survey[] models) {
        SurveySoap[] soapModels = new SurveySoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static SurveySoap[][] toSoapModels(Survey[][] models) {
        SurveySoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new SurveySoap[models.length][models[0].length];
        } else {
            soapModels = new SurveySoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static SurveySoap[] toSoapModels(List<Survey> models) {
        List<SurveySoap> soapModels = new ArrayList<SurveySoap>(models.size());

        for (Survey model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new SurveySoap[soapModels.size()]);
    }

    public String getPrimaryKey() {
        return _eventName;
    }

    public void setPrimaryKey(String pk) {
        setEventName(pk);
    }

    public String getEventName() {
        return _eventName;
    }

    public void setEventName(String eventName) {
        _eventName = eventName;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public String getUrl() {
        return _url;
    }

    public void setUrl(String url) {
        _url = url;
    }

    public String getType() {
        return _type;
    }

    public void setType(String type) {
        _type = type;
    }
}
