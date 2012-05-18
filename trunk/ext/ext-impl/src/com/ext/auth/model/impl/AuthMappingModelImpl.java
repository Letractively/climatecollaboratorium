package com.ext.auth.model.impl;

import com.ext.auth.model.AuthMapping;
import com.ext.auth.model.AuthMappingSoap;

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
 * <a href="AuthMappingModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>AuthMapping</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.auth.model.AuthMapping
 * @see com.ext.auth.model.AuthMappingModel
 * @see com.ext.auth.model.impl.AuthMappingImpl
 *
 */
public class AuthMappingModelImpl extends BaseModelImpl<AuthMapping> {
    public static final String TABLE_NAME = "AuthMapping";
    public static final Object[][] TABLE_COLUMNS = {
            { "identifier", new Integer(Types.VARCHAR) },
            

            { "userId", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table AuthMapping (identifier VARCHAR(75) not null primary key,userId LONG)";
    public static final String TABLE_SQL_DROP = "drop table AuthMapping";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.auth.model.AuthMapping"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.auth.model.AuthMapping"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.auth.model.AuthMapping"));
    private String _identifier;
    private Long _userId;

    public AuthMappingModelImpl() {
    }

    public static AuthMapping toModel(AuthMappingSoap soapModel) {
        AuthMapping model = new AuthMappingImpl();

        model.setIdentifier(soapModel.getIdentifier());
        model.setUserId(soapModel.getUserId());

        return model;
    }

    public static List<AuthMapping> toModels(AuthMappingSoap[] soapModels) {
        List<AuthMapping> models = new ArrayList<AuthMapping>(soapModels.length);

        for (AuthMappingSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public String getPrimaryKey() {
        return _identifier;
    }

    public void setPrimaryKey(String pk) {
        setIdentifier(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _identifier;
    }

    public String getIdentifier() {
        return GetterUtil.getString(_identifier);
    }

    public void setIdentifier(String identifier) {
        _identifier = identifier;
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }

    public AuthMapping toEscapedModel() {
        if (isEscapedModel()) {
            return (AuthMapping) this;
        } else {
            AuthMapping model = new AuthMappingImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setIdentifier(HtmlUtil.escape(getIdentifier()));
            model.setUserId(getUserId());

            model = (AuthMapping) Proxy.newProxyInstance(AuthMapping.class.getClassLoader(),
                    new Class[] { AuthMapping.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        AuthMappingImpl clone = new AuthMappingImpl();

        clone.setIdentifier(getIdentifier());
        clone.setUserId(getUserId());

        return clone;
    }

    public int compareTo(AuthMapping authMapping) {
        String pk = authMapping.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        AuthMapping authMapping = null;

        try {
            authMapping = (AuthMapping) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        String pk = authMapping.getPrimaryKey();

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

        sb.append("{identifier=");
        sb.append(getIdentifier());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.auth.model.AuthMapping");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>identifier</column-name><column-value><![CDATA[");
        sb.append(getIdentifier());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
