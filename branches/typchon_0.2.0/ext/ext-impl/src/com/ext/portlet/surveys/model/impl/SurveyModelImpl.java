package com.ext.portlet.surveys.model.impl;

import com.ext.portlet.surveys.model.Survey;
import com.ext.portlet.surveys.model.SurveySoap;

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
 * <a href="SurveyModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>Survey</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.surveys.model.Survey
 * @see com.ext.portlet.surveys.model.SurveyModel
 * @see com.ext.portlet.surveys.model.impl.SurveyImpl
 *
 */
public class SurveyModelImpl extends BaseModelImpl<Survey> {
    public static final String TABLE_NAME = "Survey";
    public static final Object[][] TABLE_COLUMNS = {
            { "eventName", new Integer(Types.VARCHAR) },
            

            { "description", new Integer(Types.VARCHAR) },
            

            { "url", new Integer(Types.VARCHAR) },
            

            { "type_", new Integer(Types.VARCHAR) }
        };
    public static final String TABLE_SQL_CREATE = "create table Survey (eventName VARCHAR(75) not null primary key,description VARCHAR(75) null,url VARCHAR(75) null,type_ VARCHAR(75) null)";
    public static final String TABLE_SQL_DROP = "drop table Survey";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.surveys.model.Survey"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.surveys.model.Survey"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.surveys.model.Survey"));
    private String _eventName;
    private String _description;
    private String _url;
    private String _type;

    public SurveyModelImpl() {
    }

    public static Survey toModel(SurveySoap soapModel) {
        Survey model = new SurveyImpl();

        model.setEventName(soapModel.getEventName());
        model.setDescription(soapModel.getDescription());
        model.setUrl(soapModel.getUrl());
        model.setType(soapModel.getType());

        return model;
    }

    public static List<Survey> toModels(SurveySoap[] soapModels) {
        List<Survey> models = new ArrayList<Survey>(soapModels.length);

        for (SurveySoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public String getPrimaryKey() {
        return _eventName;
    }

    public void setPrimaryKey(String pk) {
        setEventName(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _eventName;
    }

    public String getEventName() {
        return GetterUtil.getString(_eventName);
    }

    public void setEventName(String eventName) {
        _eventName = eventName;
    }

    public String getDescription() {
        return GetterUtil.getString(_description);
    }

    public void setDescription(String description) {
        _description = description;
    }

    public String getUrl() {
        return GetterUtil.getString(_url);
    }

    public void setUrl(String url) {
        _url = url;
    }

    public String getType() {
        return GetterUtil.getString(_type);
    }

    public void setType(String type) {
        _type = type;
    }

    public Survey toEscapedModel() {
        if (isEscapedModel()) {
            return (Survey) this;
        } else {
            Survey model = new SurveyImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setEventName(HtmlUtil.escape(getEventName()));
            model.setDescription(HtmlUtil.escape(getDescription()));
            model.setUrl(HtmlUtil.escape(getUrl()));
            model.setType(HtmlUtil.escape(getType()));

            model = (Survey) Proxy.newProxyInstance(Survey.class.getClassLoader(),
                    new Class[] { Survey.class }, new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        SurveyImpl clone = new SurveyImpl();

        clone.setEventName(getEventName());
        clone.setDescription(getDescription());
        clone.setUrl(getUrl());
        clone.setType(getType());

        return clone;
    }

    public int compareTo(Survey survey) {
        String pk = survey.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        Survey survey = null;

        try {
            survey = (Survey) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        String pk = survey.getPrimaryKey();

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

        sb.append("{eventName=");
        sb.append(getEventName());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", url=");
        sb.append(getUrl());
        sb.append(", type=");
        sb.append(getType());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.surveys.model.Survey");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>eventName</column-name><column-value><![CDATA[");
        sb.append(getEventName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>url</column-name><column-value><![CDATA[");
        sb.append(getUrl());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>type</column-name><column-value><![CDATA[");
        sb.append(getType());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
