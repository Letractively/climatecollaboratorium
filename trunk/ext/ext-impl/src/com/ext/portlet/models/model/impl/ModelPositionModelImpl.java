package com.ext.portlet.models.model.impl;

import com.ext.portlet.models.model.ModelPosition;
import com.ext.portlet.models.model.ModelPositionSoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
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
            { "id_", new Integer(Types.BIGINT) },
            

            { "positionId", new Integer(Types.BIGINT) },
            

            { "modelId", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table ModelPosition (id_ LONG not null primary key,positionId LONG,modelId LONG)";
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
    private Long _id;
    private Long _positionId;
    private Long _modelId;

    public ModelPositionModelImpl() {
    }

    public static ModelPosition toModel(ModelPositionSoap soapModel) {
        ModelPosition model = new ModelPositionImpl();

        model.setId(soapModel.getId());
        model.setPositionId(soapModel.getPositionId());
        model.setModelId(soapModel.getModelId());

        return model;
    }

    public static List<ModelPosition> toModels(ModelPositionSoap[] soapModels) {
        List<ModelPosition> models = new ArrayList<ModelPosition>(soapModels.length);

        for (ModelPositionSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _id;
    }

    public void setPrimaryKey(Long pk) {
        setId(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _id;
    }

    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        _id = id;
    }

    public Long getPositionId() {
        return _positionId;
    }

    public void setPositionId(Long positionId) {
        _positionId = positionId;
    }

    public Long getModelId() {
        return _modelId;
    }

    public void setModelId(Long modelId) {
        _modelId = modelId;
    }

    public ModelPosition toEscapedModel() {
        if (isEscapedModel()) {
            return (ModelPosition) this;
        } else {
            ModelPosition model = new ModelPositionImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setId(getId());
            model.setPositionId(getPositionId());
            model.setModelId(getModelId());

            model = (ModelPosition) Proxy.newProxyInstance(ModelPosition.class.getClassLoader(),
                    new Class[] { ModelPosition.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        ModelPositionImpl clone = new ModelPositionImpl();

        clone.setId(getId());
        clone.setPositionId(getPositionId());
        clone.setModelId(getModelId());

        return clone;
    }

    public int compareTo(ModelPosition modelPosition) {
        Long pk = modelPosition.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
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

        Long pk = modelPosition.getPrimaryKey();

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

        sb.append("{id=");
        sb.append(getId());
        sb.append(", positionId=");
        sb.append(getPositionId());
        sb.append(", modelId=");
        sb.append(getModelId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.models.model.ModelPosition");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>positionId</column-name><column-value><![CDATA[");
        sb.append(getPositionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelId</column-name><column-value><![CDATA[");
        sb.append(getModelId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
