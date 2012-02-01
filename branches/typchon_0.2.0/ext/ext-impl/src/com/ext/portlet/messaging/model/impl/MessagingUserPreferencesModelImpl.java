package com.ext.portlet.messaging.model.impl;

import com.ext.portlet.messaging.model.MessagingUserPreferences;
import com.ext.portlet.messaging.model.MessagingUserPreferencesSoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="MessagingUserPreferencesModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>MessagingUserPreferences</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.messaging.model.MessagingUserPreferences
 * @see com.ext.portlet.messaging.model.MessagingUserPreferencesModel
 * @see com.ext.portlet.messaging.model.impl.MessagingUserPreferencesImpl
 *
 */
public class MessagingUserPreferencesModelImpl extends BaseModelImpl<MessagingUserPreferences> {
    public static final String TABLE_NAME = "MessagingUserPreferences";
    public static final Object[][] TABLE_COLUMNS = {
            { "messagingPreferencesId", new Integer(Types.BIGINT) },
            

            { "userId", new Integer(Types.BIGINT) },
            

            { "emailOnSend", new Integer(Types.BOOLEAN) },
            

            { "emailOnReceipt", new Integer(Types.BOOLEAN) },
            

            { "emailOnActivity", new Integer(Types.BOOLEAN) }
        };
    public static final String TABLE_SQL_CREATE = "create table MessagingUserPreferences (messagingPreferencesId LONG not null primary key,userId LONG,emailOnSend BOOLEAN,emailOnReceipt BOOLEAN,emailOnActivity BOOLEAN)";
    public static final String TABLE_SQL_DROP = "drop table MessagingUserPreferences";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.messaging.model.MessagingUserPreferences"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.messaging.model.MessagingUserPreferences"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.messaging.model.MessagingUserPreferences"));
    private Long _messagingPreferencesId;
    private Long _userId;
    private Long _originalUserId;
    private Boolean _emailOnSend;
    private Boolean _emailOnReceipt;
    private Boolean _emailOnActivity;

    public MessagingUserPreferencesModelImpl() {
    }

    public static MessagingUserPreferences toModel(
        MessagingUserPreferencesSoap soapModel) {
        MessagingUserPreferences model = new MessagingUserPreferencesImpl();

        model.setMessagingPreferencesId(soapModel.getMessagingPreferencesId());
        model.setUserId(soapModel.getUserId());
        model.setEmailOnSend(soapModel.getEmailOnSend());
        model.setEmailOnReceipt(soapModel.getEmailOnReceipt());
        model.setEmailOnActivity(soapModel.getEmailOnActivity());

        return model;
    }

    public static List<MessagingUserPreferences> toModels(
        MessagingUserPreferencesSoap[] soapModels) {
        List<MessagingUserPreferences> models = new ArrayList<MessagingUserPreferences>(soapModels.length);

        for (MessagingUserPreferencesSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _messagingPreferencesId;
    }

    public void setPrimaryKey(Long pk) {
        setMessagingPreferencesId(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _messagingPreferencesId;
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

        if (_originalUserId == null) {
            _originalUserId = userId;
        }
    }

    public Long getOriginalUserId() {
        return _originalUserId;
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

    public Boolean getEmailOnActivity() {
        return _emailOnActivity;
    }

    public void setEmailOnActivity(Boolean emailOnActivity) {
        _emailOnActivity = emailOnActivity;
    }

    public MessagingUserPreferences toEscapedModel() {
        if (isEscapedModel()) {
            return (MessagingUserPreferences) this;
        } else {
            MessagingUserPreferences model = new MessagingUserPreferencesImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setMessagingPreferencesId(getMessagingPreferencesId());
            model.setUserId(getUserId());
            model.setEmailOnSend(getEmailOnSend());
            model.setEmailOnReceipt(getEmailOnReceipt());
            model.setEmailOnActivity(getEmailOnActivity());

            model = (MessagingUserPreferences) Proxy.newProxyInstance(MessagingUserPreferences.class.getClassLoader(),
                    new Class[] { MessagingUserPreferences.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        MessagingUserPreferencesImpl clone = new MessagingUserPreferencesImpl();

        clone.setMessagingPreferencesId(getMessagingPreferencesId());
        clone.setUserId(getUserId());
        clone.setEmailOnSend(getEmailOnSend());
        clone.setEmailOnReceipt(getEmailOnReceipt());
        clone.setEmailOnActivity(getEmailOnActivity());

        return clone;
    }

    public int compareTo(MessagingUserPreferences messagingUserPreferences) {
        Long pk = messagingUserPreferences.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        MessagingUserPreferences messagingUserPreferences = null;

        try {
            messagingUserPreferences = (MessagingUserPreferences) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = messagingUserPreferences.getPrimaryKey();

        if (getPrimaryKey().equals(pk)) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return getPrimaryKey().hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{messagingPreferencesId=");
        sb.append(getMessagingPreferencesId());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", emailOnSend=");
        sb.append(getEmailOnSend());
        sb.append(", emailOnReceipt=");
        sb.append(getEmailOnReceipt());
        sb.append(", emailOnActivity=");
        sb.append(getEmailOnActivity());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.messaging.model.MessagingUserPreferences");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>messagingPreferencesId</column-name><column-value><![CDATA[");
        sb.append(getMessagingPreferencesId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>emailOnSend</column-name><column-value><![CDATA[");
        sb.append(getEmailOnSend());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>emailOnReceipt</column-name><column-value><![CDATA[");
        sb.append(getEmailOnReceipt());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>emailOnActivity</column-name><column-value><![CDATA[");
        sb.append(getEmailOnActivity());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
