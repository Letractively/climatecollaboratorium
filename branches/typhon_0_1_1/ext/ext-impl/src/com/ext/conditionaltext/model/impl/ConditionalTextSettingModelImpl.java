package com.ext.conditionaltext.model.impl;

import com.ext.conditionaltext.model.ConditionalTextSetting;
import com.ext.conditionaltext.model.ConditionalTextSettingSoap;

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
 * <a href="ConditionalTextSettingModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>ConditionalTextSetting</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.conditionaltext.model.ConditionalTextSetting
 * @see com.ext.conditionaltext.model.ConditionalTextSettingModel
 * @see com.ext.conditionaltext.model.impl.ConditionalTextSettingImpl
 *
 */
public class ConditionalTextSettingModelImpl extends BaseModelImpl<ConditionalTextSetting> {
    public static final String TABLE_NAME = "ConditionalTextSetting";
    public static final Object[][] TABLE_COLUMNS = {
            { "ConditionalTextSettingId", new Integer(Types.BIGINT) },
            

            { "styleClass", new Integer(Types.VARCHAR) },
            

            { "paramKey", new Integer(Types.VARCHAR) },
            

            { "paramValue", new Integer(Types.VARCHAR) },
            

            { "html", new Integer(Types.VARCHAR) }
        };
    public static final String TABLE_SQL_CREATE = "create table ConditionalTextSetting (ConditionalTextSettingId LONG not null primary key,styleClass VARCHAR(75) null,paramKey VARCHAR(75) null,paramValue VARCHAR(75) null,html VARCHAR(75) null)";
    public static final String TABLE_SQL_DROP = "drop table ConditionalTextSetting";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.conditionaltext.model.ConditionalTextSetting"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.conditionaltext.model.ConditionalTextSetting"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.conditionaltext.model.ConditionalTextSetting"));
    private Long _ConditionalTextSettingId;
    private String _styleClass;
    private String _paramKey;
    private String _originalParamKey;
    private String _paramValue;
    private String _originalParamValue;
    private String _html;

    public ConditionalTextSettingModelImpl() {
    }

    public static ConditionalTextSetting toModel(
        ConditionalTextSettingSoap soapModel) {
        ConditionalTextSetting model = new ConditionalTextSettingImpl();

        model.setConditionalTextSettingId(soapModel.getConditionalTextSettingId());
        model.setStyleClass(soapModel.getStyleClass());
        model.setParamKey(soapModel.getParamKey());
        model.setParamValue(soapModel.getParamValue());
        model.setHtml(soapModel.getHtml());

        return model;
    }

    public static List<ConditionalTextSetting> toModels(
        ConditionalTextSettingSoap[] soapModels) {
        List<ConditionalTextSetting> models = new ArrayList<ConditionalTextSetting>(soapModels.length);

        for (ConditionalTextSettingSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _ConditionalTextSettingId;
    }

    public void setPrimaryKey(Long pk) {
        setConditionalTextSettingId(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _ConditionalTextSettingId;
    }

    public Long getConditionalTextSettingId() {
        return _ConditionalTextSettingId;
    }

    public void setConditionalTextSettingId(Long ConditionalTextSettingId) {
        _ConditionalTextSettingId = ConditionalTextSettingId;
    }

    public String getStyleClass() {
        return GetterUtil.getString(_styleClass);
    }

    public void setStyleClass(String styleClass) {
        _styleClass = styleClass;
    }

    public String getParamKey() {
        return GetterUtil.getString(_paramKey);
    }

    public void setParamKey(String paramKey) {
        _paramKey = paramKey;

        if (_originalParamKey == null) {
            _originalParamKey = paramKey;
        }
    }

    public String getOriginalParamKey() {
        return GetterUtil.getString(_originalParamKey);
    }

    public String getParamValue() {
        return GetterUtil.getString(_paramValue);
    }

    public void setParamValue(String paramValue) {
        _paramValue = paramValue;

        if (_originalParamValue == null) {
            _originalParamValue = paramValue;
        }
    }

    public String getOriginalParamValue() {
        return GetterUtil.getString(_originalParamValue);
    }

    public String getHtml() {
        return GetterUtil.getString(_html);
    }

    public void setHtml(String html) {
        _html = html;
    }

    public ConditionalTextSetting toEscapedModel() {
        if (isEscapedModel()) {
            return (ConditionalTextSetting) this;
        } else {
            ConditionalTextSetting model = new ConditionalTextSettingImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setConditionalTextSettingId(getConditionalTextSettingId());
            model.setStyleClass(HtmlUtil.escape(getStyleClass()));
            model.setParamKey(HtmlUtil.escape(getParamKey()));
            model.setParamValue(HtmlUtil.escape(getParamValue()));
            model.setHtml(HtmlUtil.escape(getHtml()));

            model = (ConditionalTextSetting) Proxy.newProxyInstance(ConditionalTextSetting.class.getClassLoader(),
                    new Class[] { ConditionalTextSetting.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        ConditionalTextSettingImpl clone = new ConditionalTextSettingImpl();

        clone.setConditionalTextSettingId(getConditionalTextSettingId());
        clone.setStyleClass(getStyleClass());
        clone.setParamKey(getParamKey());
        clone.setParamValue(getParamValue());
        clone.setHtml(getHtml());

        return clone;
    }

    public int compareTo(ConditionalTextSetting conditionalTextSetting) {
        Long pk = conditionalTextSetting.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        ConditionalTextSetting conditionalTextSetting = null;

        try {
            conditionalTextSetting = (ConditionalTextSetting) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = conditionalTextSetting.getPrimaryKey();

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

        sb.append("{ConditionalTextSettingId=");
        sb.append(getConditionalTextSettingId());
        sb.append(", styleClass=");
        sb.append(getStyleClass());
        sb.append(", paramKey=");
        sb.append(getParamKey());
        sb.append(", paramValue=");
        sb.append(getParamValue());
        sb.append(", html=");
        sb.append(getHtml());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.conditionaltext.model.ConditionalTextSetting");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>ConditionalTextSettingId</column-name><column-value><![CDATA[");
        sb.append(getConditionalTextSettingId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>styleClass</column-name><column-value><![CDATA[");
        sb.append(getStyleClass());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>paramKey</column-name><column-value><![CDATA[");
        sb.append(getParamKey());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>paramValue</column-name><column-value><![CDATA[");
        sb.append(getParamValue());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>html</column-name><column-value><![CDATA[");
        sb.append(getHtml());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
