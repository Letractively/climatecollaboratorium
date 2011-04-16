package com.ext.portlet.surveys.model.impl;

import com.ext.portlet.surveys.model.UserSurvey;
import com.ext.portlet.surveys.model.UserSurveySoap;
import com.ext.portlet.surveys.service.persistence.UserSurveyPK;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <a href="UserSurveyModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>UserSurvey</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.surveys.model.UserSurvey
 * @see com.ext.portlet.surveys.model.UserSurveyModel
 * @see com.ext.portlet.surveys.model.impl.UserSurveyImpl
 *
 */
public class UserSurveyModelImpl extends BaseModelImpl<UserSurvey> {
    public static final String TABLE_NAME = "UserSurvey";
    public static final Object[][] TABLE_COLUMNS = {
            { "userId", new Integer(Types.BIGINT) },
            

            { "eventName", new Integer(Types.VARCHAR) },
            

            { "createDate", new Integer(Types.TIMESTAMP) }
        };
    public static final String TABLE_SQL_CREATE = "create table UserSurvey (userId LONG not null,eventName VARCHAR(75) not null,createDate DATE null,primary key (userId, eventName))";
    public static final String TABLE_SQL_DROP = "drop table UserSurvey";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.surveys.model.UserSurvey"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.surveys.model.UserSurvey"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.surveys.model.UserSurvey"));
    private Long _userId;
    private String _eventName;
    private Date _createDate;

    public UserSurveyModelImpl() {
    }

    public static UserSurvey toModel(UserSurveySoap soapModel) {
        UserSurvey model = new UserSurveyImpl();

        model.setUserId(soapModel.getUserId());
        model.setEventName(soapModel.getEventName());
        model.setCreateDate(soapModel.getCreateDate());

        return model;
    }

    public static List<UserSurvey> toModels(UserSurveySoap[] soapModels) {
        List<UserSurvey> models = new ArrayList<UserSurvey>(soapModels.length);

        for (UserSurveySoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public UserSurveyPK getPrimaryKey() {
        return new UserSurveyPK(_userId, _eventName);
    }

    public void setPrimaryKey(UserSurveyPK pk) {
        setUserId(pk.userId);
        setEventName(pk.eventName);
    }

    public Serializable getPrimaryKeyObj() {
        return new UserSurveyPK(_userId, _eventName);
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }

    public String getEventName() {
        return GetterUtil.getString(_eventName);
    }

    public void setEventName(String eventName) {
        _eventName = eventName;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public UserSurvey toEscapedModel() {
        if (isEscapedModel()) {
            return (UserSurvey) this;
        } else {
            UserSurvey model = new UserSurveyImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setUserId(getUserId());
            model.setEventName(HtmlUtil.escape(getEventName()));
            model.setCreateDate(getCreateDate());

            model = (UserSurvey) Proxy.newProxyInstance(UserSurvey.class.getClassLoader(),
                    new Class[] { UserSurvey.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        UserSurveyImpl clone = new UserSurveyImpl();

        clone.setUserId(getUserId());
        clone.setEventName(getEventName());
        clone.setCreateDate(getCreateDate());

        return clone;
    }

    public int compareTo(UserSurvey userSurvey) {
        UserSurveyPK pk = userSurvey.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        UserSurvey userSurvey = null;

        try {
            userSurvey = (UserSurvey) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        UserSurveyPK pk = userSurvey.getPrimaryKey();

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

        sb.append("{userId=");
        sb.append(getUserId());
        sb.append(", eventName=");
        sb.append(getEventName());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.surveys.model.UserSurvey");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>eventName</column-name><column-value><![CDATA[");
        sb.append(getEventName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
