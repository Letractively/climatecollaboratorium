package com.ext.inlinehelp.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="HelpUserSettingSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.inlinehelp.service.http.HelpUserSettingServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.inlinehelp.service.http.HelpUserSettingServiceSoap
 *
 */
public class HelpUserSettingSoap implements Serializable {
    private Long _HelpUserSettingId;
    private Long _userId;
    private String _messageId;
    private Boolean _visible;

    public HelpUserSettingSoap() {
    }

    public static HelpUserSettingSoap toSoapModel(HelpUserSetting model) {
        HelpUserSettingSoap soapModel = new HelpUserSettingSoap();

        soapModel.setHelpUserSettingId(model.getHelpUserSettingId());
        soapModel.setUserId(model.getUserId());
        soapModel.setMessageId(model.getMessageId());
        soapModel.setVisible(model.getVisible());

        return soapModel;
    }

    public static HelpUserSettingSoap[] toSoapModels(HelpUserSetting[] models) {
        HelpUserSettingSoap[] soapModels = new HelpUserSettingSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static HelpUserSettingSoap[][] toSoapModels(
        HelpUserSetting[][] models) {
        HelpUserSettingSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new HelpUserSettingSoap[models.length][models[0].length];
        } else {
            soapModels = new HelpUserSettingSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static HelpUserSettingSoap[] toSoapModels(
        List<HelpUserSetting> models) {
        List<HelpUserSettingSoap> soapModels = new ArrayList<HelpUserSettingSoap>(models.size());

        for (HelpUserSetting model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new HelpUserSettingSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _HelpUserSettingId;
    }

    public void setPrimaryKey(Long pk) {
        setHelpUserSettingId(pk);
    }

    public Long getHelpUserSettingId() {
        return _HelpUserSettingId;
    }

    public void setHelpUserSettingId(Long HelpUserSettingId) {
        _HelpUserSettingId = HelpUserSettingId;
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }

    public String getMessageId() {
        return _messageId;
    }

    public void setMessageId(String messageId) {
        _messageId = messageId;
    }

    public Boolean getVisible() {
        return _visible;
    }

    public void setVisible(Boolean visible) {
        _visible = visible;
    }
}
