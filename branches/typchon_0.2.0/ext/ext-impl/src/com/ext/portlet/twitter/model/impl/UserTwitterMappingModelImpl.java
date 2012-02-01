package com.ext.portlet.twitter.model.impl;

import com.ext.portlet.twitter.model.UserTwitterMapping;
import com.ext.portlet.twitter.model.UserTwitterMappingSoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="UserTwitterMappingModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>UserTwitterMapping</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.twitter.model.UserTwitterMapping
 * @see com.ext.portlet.twitter.model.UserTwitterMappingModel
 * @see com.ext.portlet.twitter.model.impl.UserTwitterMappingImpl
 *
 */
public class UserTwitterMappingModelImpl extends BaseModelImpl<UserTwitterMapping> {
    public static final String TABLE_NAME = "UserTwitterMapping";
    public static final Object[][] TABLE_COLUMNS = {
            { "twitterId", new Integer(Types.BIGINT) },
            

            { "userId", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table UserTwitterMapping (twitterId LONG not null primary key,userId LONG)";
    public static final String TABLE_SQL_DROP = "drop table UserTwitterMapping";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.twitter.model.UserTwitterMapping"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.twitter.model.UserTwitterMapping"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.twitter.model.UserTwitterMapping"));
    private Long _twitterId;
    private Long _userId;

    public UserTwitterMappingModelImpl() {
    }

    public static UserTwitterMapping toModel(UserTwitterMappingSoap soapModel) {
        UserTwitterMapping model = new UserTwitterMappingImpl();

        model.setTwitterId(soapModel.getTwitterId());
        model.setUserId(soapModel.getUserId());

        return model;
    }

    public static List<UserTwitterMapping> toModels(
        UserTwitterMappingSoap[] soapModels) {
        List<UserTwitterMapping> models = new ArrayList<UserTwitterMapping>(soapModels.length);

        for (UserTwitterMappingSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _twitterId;
    }

    public void setPrimaryKey(Long pk) {
        setTwitterId(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _twitterId;
    }

    public Long getTwitterId() {
        return _twitterId;
    }

    public void setTwitterId(Long twitterId) {
        _twitterId = twitterId;
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }

    public UserTwitterMapping toEscapedModel() {
        if (isEscapedModel()) {
            return (UserTwitterMapping) this;
        } else {
            UserTwitterMapping model = new UserTwitterMappingImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setTwitterId(getTwitterId());
            model.setUserId(getUserId());

            model = (UserTwitterMapping) Proxy.newProxyInstance(UserTwitterMapping.class.getClassLoader(),
                    new Class[] { UserTwitterMapping.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        UserTwitterMappingImpl clone = new UserTwitterMappingImpl();

        clone.setTwitterId(getTwitterId());
        clone.setUserId(getUserId());

        return clone;
    }

    public int compareTo(UserTwitterMapping userTwitterMapping) {
        Long pk = userTwitterMapping.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        UserTwitterMapping userTwitterMapping = null;

        try {
            userTwitterMapping = (UserTwitterMapping) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = userTwitterMapping.getPrimaryKey();

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

        sb.append("{twitterId=");
        sb.append(getTwitterId());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.twitter.model.UserTwitterMapping");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>twitterId</column-name><column-value><![CDATA[");
        sb.append(getTwitterId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
