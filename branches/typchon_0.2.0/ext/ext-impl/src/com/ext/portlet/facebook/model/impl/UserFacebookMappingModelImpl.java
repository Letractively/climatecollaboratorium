package com.ext.portlet.facebook.model.impl;

import com.ext.portlet.facebook.model.UserFacebookMapping;
import com.ext.portlet.facebook.model.UserFacebookMappingSoap;

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
 * <a href="UserFacebookMappingModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>UserFacebookMapping</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.facebook.model.UserFacebookMapping
 * @see com.ext.portlet.facebook.model.UserFacebookMappingModel
 * @see com.ext.portlet.facebook.model.impl.UserFacebookMappingImpl
 *
 */
public class UserFacebookMappingModelImpl extends BaseModelImpl<UserFacebookMapping> {
    public static final String TABLE_NAME = "UserFacebookMapping";
    public static final Object[][] TABLE_COLUMNS = {
            { "userId", new Integer(Types.BIGINT) },
            

            { "facebookId", new Integer(Types.VARCHAR) }
        };
    public static final String TABLE_SQL_CREATE = "create table UserFacebookMapping (userId LONG not null primary key,facebookId VARCHAR(75) null)";
    public static final String TABLE_SQL_DROP = "drop table UserFacebookMapping";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.facebook.model.UserFacebookMapping"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.facebook.model.UserFacebookMapping"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.facebook.model.UserFacebookMapping"));
    private Long _userId;
    private String _facebookId;
    private String _originalFacebookId;

    public UserFacebookMappingModelImpl() {
    }

    public static UserFacebookMapping toModel(UserFacebookMappingSoap soapModel) {
        UserFacebookMapping model = new UserFacebookMappingImpl();

        model.setUserId(soapModel.getUserId());
        model.setFacebookId(soapModel.getFacebookId());

        return model;
    }

    public static List<UserFacebookMapping> toModels(
        UserFacebookMappingSoap[] soapModels) {
        List<UserFacebookMapping> models = new ArrayList<UserFacebookMapping>(soapModels.length);

        for (UserFacebookMappingSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _userId;
    }

    public void setPrimaryKey(Long pk) {
        setUserId(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _userId;
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }

    public String getFacebookId() {
        return GetterUtil.getString(_facebookId);
    }

    public void setFacebookId(String facebookId) {
        _facebookId = facebookId;

        if (_originalFacebookId == null) {
            _originalFacebookId = facebookId;
        }
    }

    public String getOriginalFacebookId() {
        return GetterUtil.getString(_originalFacebookId);
    }

    public UserFacebookMapping toEscapedModel() {
        if (isEscapedModel()) {
            return (UserFacebookMapping) this;
        } else {
            UserFacebookMapping model = new UserFacebookMappingImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setUserId(getUserId());
            model.setFacebookId(HtmlUtil.escape(getFacebookId()));

            model = (UserFacebookMapping) Proxy.newProxyInstance(UserFacebookMapping.class.getClassLoader(),
                    new Class[] { UserFacebookMapping.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        UserFacebookMappingImpl clone = new UserFacebookMappingImpl();

        clone.setUserId(getUserId());
        clone.setFacebookId(getFacebookId());

        return clone;
    }

    public int compareTo(UserFacebookMapping userFacebookMapping) {
        Long pk = userFacebookMapping.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        UserFacebookMapping userFacebookMapping = null;

        try {
            userFacebookMapping = (UserFacebookMapping) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = userFacebookMapping.getPrimaryKey();

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
        sb.append(", facebookId=");
        sb.append(getFacebookId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.facebook.model.UserFacebookMapping");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>facebookId</column-name><column-value><![CDATA[");
        sb.append(getFacebookId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
