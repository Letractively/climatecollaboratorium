package com.ext.portlet.messaging.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="MessagingUserPreferencesSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.messaging.service.http.MessagingUserPreferencesServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.messaging.service.http.MessagingUserPreferencesServiceSoap
 *
 */
public class MessagingUserPreferencesSoap implements Serializable {
    private Long _messagingPreferencesId;
    private Long _userId;
    private Boolean _emailOnSend;
    private Boolean _emailOnReceipt;

    public MessagingUserPreferencesSoap() {
    }

    public static MessagingUserPreferencesSoap toSoapModel(
        MessagingUserPreferences model) {
        MessagingUserPreferencesSoap soapModel = new MessagingUserPreferencesSoap();

        soapModel.setMessagingPreferencesId(model.getMessagingPreferencesId());
        soapModel.setUserId(model.getUserId());
        soapModel.setEmailOnSend(model.getEmailOnSend());
        soapModel.setEmailOnReceipt(model.getEmailOnReceipt());

        return soapModel;
    }

    public static MessagingUserPreferencesSoap[] toSoapModels(
        MessagingUserPreferences[] models) {
        MessagingUserPreferencesSoap[] soapModels = new MessagingUserPreferencesSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static MessagingUserPreferencesSoap[][] toSoapModels(
        MessagingUserPreferences[][] models) {
        MessagingUserPreferencesSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new MessagingUserPreferencesSoap[models.length][models[0].length];
        } else {
            soapModels = new MessagingUserPreferencesSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static MessagingUserPreferencesSoap[] toSoapModels(
        List<MessagingUserPreferences> models) {
        List<MessagingUserPreferencesSoap> soapModels = new ArrayList<MessagingUserPreferencesSoap>(models.size());

        for (MessagingUserPreferences model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new MessagingUserPreferencesSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _messagingPreferencesId;
    }

    public void setPrimaryKey(Long pk) {
        setMessagingPreferencesId(pk);
    }

    public Long getMessagingPreferencesId() {
        return _messagingPreferencesId;
    }

    public void setMessagingPreferencesId(Long messagingPreferencesId) {
        _messagingPreferencesId = messagingPreferencesId;
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }

    public Boolean getEmailOnSend() {
        return _emailOnSend;
    }

    public void setEmailOnSend(Boolean emailOnSend) {
        _emailOnSend = emailOnSend;
    }

    public Boolean getEmailOnReceipt() {
        return _emailOnReceipt;
    }

    public void setEmailOnReceipt(Boolean emailOnReceipt) {
        _emailOnReceipt = emailOnReceipt;
    }
}
