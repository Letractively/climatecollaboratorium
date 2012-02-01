package com.ext.utils.model.impl;

import com.ext.utils.model.UserForgotPasswordRequest;
import com.ext.utils.model.UserForgotPasswordRequestSoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.DateUtil;
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
 * <a href="UserForgotPasswordRequestModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>UserForgotPasswordRequest</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.utils.model.UserForgotPasswordRequest
 * @see com.ext.utils.model.UserForgotPasswordRequestModel
 * @see com.ext.utils.model.impl.UserForgotPasswordRequestImpl
 *
 */
public class UserForgotPasswordRequestModelImpl extends BaseModelImpl<UserForgotPasswordRequest> {
    public static final String TABLE_NAME = "UserForgotPasswordRequest";
    public static final Object[][] TABLE_COLUMNS = {
            { "token", new Integer(Types.VARCHAR) },
            

            { "userId", new Integer(Types.BIGINT) },
            

            { "created", new Integer(Types.TIMESTAMP) }
        };
    public static final String TABLE_SQL_CREATE = "create table UserForgotPasswordRequest (token VARCHAR(75) not null primary key,userId LONG,created DATE null)";
    public static final String TABLE_SQL_DROP = "drop table UserForgotPasswordRequest";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.utils.model.UserForgotPasswordRequest"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.utils.model.UserForgotPasswordRequest"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.utils.model.UserForgotPasswordRequest"));
    private String _token;
    private Long _userId;
    private Date _created;

    public UserForgotPasswordRequestModelImpl() {
    }

    public static UserForgotPasswordRequest toModel(
        UserForgotPasswordRequestSoap soapModel) {
        UserForgotPasswordRequest model = new UserForgotPasswordRequestImpl();

        model.setToken(soapModel.getToken());
        model.setUserId(soapModel.getUserId());
        model.setCreated(soapModel.getCreated());

        return model;
    }

    public static List<UserForgotPasswordRequest> toModels(
        UserForgotPasswordRequestSoap[] soapModels) {
        List<UserForgotPasswordRequest> models = new ArrayList<UserForgotPasswordRequest>(soapModels.length);

        for (UserForgotPasswordRequestSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public String getPrimaryKey() {
        return _token;
    }

    public void setPrimaryKey(String pk) {
        setToken(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _token;
    }

    public String getToken() {
        return GetterUtil.getString(_token);
    }

    public void setToken(String token) {
        _token = token;
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        _created = created;
    }

    public UserForgotPasswordRequest toEscapedModel() {
        if (isEscapedModel()) {
            return (UserForgotPasswordRequest) this;
        } else {
            UserForgotPasswordRequest model = new UserForgotPasswordRequestImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setToken(HtmlUtil.escape(getToken()));
            model.setUserId(getUserId());
            model.setCreated(getCreated());

            model = (UserForgotPasswordRequest) Proxy.newProxyInstance(UserForgotPasswordRequest.class.getClassLoader(),
                    new Class[] { UserForgotPasswordRequest.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        UserForgotPasswordRequestImpl clone = new UserForgotPasswordRequestImpl();

        clone.setToken(getToken());
        clone.setUserId(getUserId());
        clone.setCreated(getCreated());

        return clone;
    }

    public int compareTo(UserForgotPasswordRequest userForgotPasswordRequest) {
        int value = 0;

        value = DateUtil.compareTo(getCreated(),
                userForgotPasswordRequest.getCreated());

        value = value * -1;

        if (value != 0) {
            return value;
        }

        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        UserForgotPasswordRequest userForgotPasswordRequest = null;

        try {
            userForgotPasswordRequest = (UserForgotPasswordRequest) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        String pk = userForgotPasswordRequest.getPrimaryKey();

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

        sb.append("{token=");
        sb.append(getToken());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", created=");
        sb.append(getCreated());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.utils.model.UserForgotPasswordRequest");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>token</column-name><column-value><![CDATA[");
        sb.append(getToken());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>created</column-name><column-value><![CDATA[");
        sb.append(getCreated());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
