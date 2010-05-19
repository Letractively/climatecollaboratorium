package com.ext.inlinehelp.model.impl;

import com.ext.inlinehelp.model.HelpUserSetting;
import com.ext.inlinehelp.model.HelpUserSettingSoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="HelpUserSettingModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>HelpUserSetting</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.inlinehelp.model.HelpUserSetting
 * @see com.ext.inlinehelp.model.HelpUserSettingModel
 * @see com.ext.inlinehelp.model.impl.HelpUserSettingImpl
 *
 */
public class HelpUserSettingModelImpl extends BaseModelImpl<HelpUserSetting> {
    public static final String TABLE_NAME = "HelpUserSetting";
    public static final Object[][] TABLE_COLUMNS = {
            { "HelpUserSettingId", new Integer(Types.BIGINT) },
            

            { "userId", new Integer(Types.BIGINT) },
            

            { "messageId", new Integer(Types.VARCHAR) },
            

            { "visible", new Integer(Types.BOOLEAN) }
        };
    public static final String TABLE_SQL_CREATE = "create table HelpUserSetting (HelpUserSettingId LONG not null primary key,userId LONG,messageId VARCHAR(75) null,visible BOOLEAN)";
    public static final String TABLE_SQL_DROP = "drop table HelpUserSetting";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.inlinehelp.model.HelpUserSetting"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.inlinehelp.model.HelpUserSetting"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.inlinehelp.model.HelpUserSetting"));
    private Long _HelpUserSettingId;
    private Long _userId;
    private String _messageId;
    private Boolean _visible;

    public HelpUserSettingModelImpl() {
    }

    public static HelpUserSetting toModel(HelpUserSettingSoap soapModel) {
        HelpUserSetting model = new HelpUserSettingImpl();

        model.setHelpUserSettingId(soapModel.getHelpUserSettingId());
        model.setUserId(soapModel.getUserId());
        model.setMessageId(soapModel.getMessageId());
        model.setVisible(soapModel.getVisible());

        return model;
    }

    public static List<HelpUserSetting> toModels(
        HelpUserSettingSoap[] soapModels) {
        List<HelpUserSetting> models = new ArrayList<HelpUserSetting>(soapModels.length);

        for (HelpUserSettingSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _HelpUserSettingId;
    }

    public void setPrimaryKey(Long pk) {
        setHelpUserSettingId(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _HelpUserSettingId;
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
        return GetterUtil.getString(_messageId);
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

    public HelpUserSetting toEscapedModel() {
        if (isEscapedModel()) {
            return (HelpUserSetting) this;
        } else {
            HelpUserSetting model = new HelpUserSettingImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setHelpUserSettingId(getHelpUserSettingId());
            model.setUserId(getUserId());
            model.setMessageId(HtmlUtil.escape(getMessageId()));
            model.setVisible(getVisible());

            model = (HelpUserSetting) Proxy.newProxyInstance(HelpUserSetting.class.getClassLoader(),
                    new Class[] { HelpUserSetting.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        HelpUserSettingImpl clone = new HelpUserSettingImpl();

        clone.setHelpUserSettingId(getHelpUserSettingId());
        clone.setUserId(getUserId());
        clone.setMessageId(getMessageId());
        clone.setVisible(getVisible());

        return clone;
    }

    public int compareTo(HelpUserSetting helpUserSetting) {
        Long pk = helpUserSetting.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        HelpUserSetting helpUserSetting = null;

        try {
            helpUserSetting = (HelpUserSetting) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = helpUserSetting.getPrimaryKey();

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

        sb.append("{HelpUserSettingId=");
        sb.append(getHelpUserSettingId());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", messageId=");
        sb.append(getMessageId());
        sb.append(", visible=");
        sb.append(getVisible());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.inlinehelp.model.HelpUserSetting");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>HelpUserSettingId</column-name><column-value><![CDATA[");
        sb.append(getHelpUserSettingId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>messageId</column-name><column-value><![CDATA[");
        sb.append(getMessageId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>visible</column-name><column-value><![CDATA[");
        sb.append(getVisible());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
