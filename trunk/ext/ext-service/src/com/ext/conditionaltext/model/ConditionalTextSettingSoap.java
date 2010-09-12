package com.ext.conditionaltext.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="ConditionalTextSettingSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.conditionaltext.service.http.ConditionalTextSettingServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.conditionaltext.service.http.ConditionalTextSettingServiceSoap
 *
 */
public class ConditionalTextSettingSoap implements Serializable {
    private Long _ConditionalTextSettingId;
    private String _styleClass;
    private String _paramKey;
    private String _paramValue;
    private String _html;

    public ConditionalTextSettingSoap() {
    }

    public static ConditionalTextSettingSoap toSoapModel(
        ConditionalTextSetting model) {
        ConditionalTextSettingSoap soapModel = new ConditionalTextSettingSoap();

        soapModel.setConditionalTextSettingId(model.getConditionalTextSettingId());
        soapModel.setStyleClass(model.getStyleClass());
        soapModel.setParamKey(model.getParamKey());
        soapModel.setParamValue(model.getParamValue());
        soapModel.setHtml(model.getHtml());

        return soapModel;
    }

    public static ConditionalTextSettingSoap[] toSoapModels(
        ConditionalTextSetting[] models) {
        ConditionalTextSettingSoap[] soapModels = new ConditionalTextSettingSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ConditionalTextSettingSoap[][] toSoapModels(
        ConditionalTextSetting[][] models) {
        ConditionalTextSettingSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ConditionalTextSettingSoap[models.length][models[0].length];
        } else {
            soapModels = new ConditionalTextSettingSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ConditionalTextSettingSoap[] toSoapModels(
        List<ConditionalTextSetting> models) {
        List<ConditionalTextSettingSoap> soapModels = new ArrayList<ConditionalTextSettingSoap>(models.size());

        for (ConditionalTextSetting model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ConditionalTextSettingSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _ConditionalTextSettingId;
    }

    public void setPrimaryKey(Long pk) {
        setConditionalTextSettingId(pk);
    }

    public Long getConditionalTextSettingId() {
        return _ConditionalTextSettingId;
    }

    public void setConditionalTextSettingId(Long ConditionalTextSettingId) {
        _ConditionalTextSettingId = ConditionalTextSettingId;
    }

    public String getStyleClass() {
        return _styleClass;
    }

    public void setStyleClass(String styleClass) {
        _styleClass = styleClass;
    }

    public String getParamKey() {
        return _paramKey;
    }

    public void setParamKey(String paramKey) {
        _paramKey = paramKey;
    }

    public String getParamValue() {
        return _paramValue;
    }

    public void setParamValue(String paramValue) {
        _paramValue = paramValue;
    }

    public String getHtml() {
        return _html;
    }

    public void setHtml(String html) {
        _html = html;
    }
}
