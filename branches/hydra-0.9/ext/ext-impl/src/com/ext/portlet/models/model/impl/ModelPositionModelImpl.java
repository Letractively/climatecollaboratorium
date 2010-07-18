package com.ext.portlet.models.model.impl;

import com.ext.portlet.models.model.ModelPosition;
import com.ext.portlet.models.model.ModelPositionSoap;
import com.ext.portlet.models.service.persistence.ModelPositionPK;

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
 * <a href="ModelPositionModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>ModelPosition</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.model.ModelPosition
 * @see com.ext.portlet.models.model.ModelPositionModel
 * @see com.ext.portlet.models.model.impl.ModelPositionImpl
 *
 */
public class ModelPositionModelImpl extends BaseModelImpl<ModelPosition> {
    public static final String TABLE_NAME = "ModelPosition";
    public static final Object[][] TABLE_COLUMNS = {
            { "modelId", new Integer(Types.BIGINT) },
            

            { "positionId", new Integer(Types.BIGINT) },
            

            { "userId", new Integer(Types.BIGINT) },
            

            { "userName", new Integer(Types.VARCHAR) },
            

            { "createDate", new Integer(Types.TIMESTAMP) },
            

            { "modifiedDate", new Integer(Types.TIMESTAMP) }
        };
    public static final String TABLE_SQL_CREATE = "create table ModelPosition (modelId LONG not null,positionId LONG not null,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,primary key (modelId, positionId))";
    public static final String TABLE_SQL_DROP = "drop table ModelPosition";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.models.model.ModelPosition"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.models.model.ModelPosition"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.models.model.ModelPosition"));
    private Long _modelId;
    private Long _positionId;
    private Long _userId;
    private String _userName;
    private Date _createDate;
    private Date _modifiedDate;

    public ModelPositionModelImpl() {
    }

    public static ModelPosition toModel(ModelPositionSoap soapModel) {
        ModelPosition model = new ModelPositionImpl();

        model.setModelId(soapModel.getModelId());
        model.setPositionId(soapModel.getPositionId());
        model.setUserId(soapModel.getUserId());
        model.setUserName(soapModel.getUserName());
        model.setCreateDate(soapModel.getCreateDate());
        model.setModifiedDate(soapModel.getModifiedDate());

        return model;
    }

    public static List<ModelPosition> toModels(ModelPositionSoap[] soapModels) {
        List<ModelPosition> models = new ArrayList<ModelPosition>(soapModels.length);

        for (ModelPositionSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public ModelPositionPK getPrimaryKey() {
        return new ModelPositionPK(_modelId, _positionId);
    }

    public void setPrimaryKey(ModelPositionPK pk) {
        setModelId(pk.modelId);
        setPositionId(pk.positionId);
    }

    public Serializable getPrimaryKeyObj() {
        return new ModelPositionPK(_modelId, _positionId);
    }

    public Long getModelId() {
        return _modelId;
    }

    public void setModelId(Long modelId) {
        _modelId = modelId;
    }

    public Long getPositionId() {
        return _positionId;
    }

    public void setPositionId(Long positionId) {
        _positionId = positionId;
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }

    public String getUserName() {
        return GetterUtil.getString(_userName);
    }

    public void setUserName(String userName) {
        _userName = userName;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public ModelPosition toEscapedModel() {
        if (isEscapedModel()) {
            return (ModelPosition) this;
        } else {
            ModelPosition model = new ModelPositionImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setModelId(getModelId());
            model.setPositionId(getPositionId());
            model.setUserId(getUserId());
            model.setUserName(HtmlUtil.escape(getUserName()));
            model.setCreateDate(getCreateDate());
            model.setModifiedDate(getModifiedDate());

            model = (ModelPosition) Proxy.newProxyInstance(ModelPosition.class.getClassLoader(),
                    new Class[] { ModelPosition.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        ModelPositionImpl clone = new ModelPositionImpl();

        clone.setModelId(getModelId());
        clone.setPositionId(getPositionId());
        clone.setUserId(getUserId());
        clone.setUserName(getUserName());
        clone.setCreateDate(getCreateDate());
        clone.setModifiedDate(getModifiedDate());

        return clone;
    }

    public int compareTo(ModelPosition modelPosition) {
        int value = 0;

        value = getModelId().compareTo(modelPosition.getModelId());

        if (value != 0) {
            return value;
        }

        value = getPositionId().compareTo(modelPosition.getPositionId());

        if (value != 0) {
            return value;
        }

        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        ModelPosition modelPosition = null;

        try {
            modelPosition = (ModelPosition) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        ModelPositionPK pk = modelPosition.getPrimaryKey();

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

        sb.append("{modelId=");
        sb.append(getModelId());
        sb.append(", positionId=");
        sb.append(getPositionId());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", userName=");
        sb.append(getUserName());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.models.model.ModelPosition");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>modelId</column-name><column-value><![CDATA[");
        sb.append(getModelId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>positionId</column-name><column-value><![CDATA[");
        sb.append(getPositionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userName</column-name><column-value><![CDATA[");
        sb.append(getUserName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
