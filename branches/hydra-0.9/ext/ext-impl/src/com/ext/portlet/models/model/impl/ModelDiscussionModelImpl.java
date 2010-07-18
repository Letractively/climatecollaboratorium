package com.ext.portlet.models.model.impl;

import com.ext.portlet.models.model.ModelDiscussion;
import com.ext.portlet.models.model.ModelDiscussionSoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="ModelDiscussionModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>ModelDiscussion</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.model.ModelDiscussion
 * @see com.ext.portlet.models.model.ModelDiscussionModel
 * @see com.ext.portlet.models.model.impl.ModelDiscussionImpl
 *
 */
public class ModelDiscussionModelImpl extends BaseModelImpl<ModelDiscussion> {
    public static final String TABLE_NAME = "ModelDiscussion";
    public static final Object[][] TABLE_COLUMNS = {
            { "modelDiscussionId", new Integer(Types.BIGINT) },
            

            { "modelId", new Integer(Types.BIGINT) },
            

            { "categoryId", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table ModelDiscussion (modelDiscussionId LONG not null primary key,modelId LONG,categoryId LONG)";
    public static final String TABLE_SQL_DROP = "drop table ModelDiscussion";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.models.model.ModelDiscussion"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.models.model.ModelDiscussion"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.models.model.ModelDiscussion"));
    private Long _modelDiscussionId;
    private Long _modelId;
    private Long _categoryId;

    public ModelDiscussionModelImpl() {
    }

    public static ModelDiscussion toModel(ModelDiscussionSoap soapModel) {
        ModelDiscussion model = new ModelDiscussionImpl();

        model.setModelDiscussionId(soapModel.getModelDiscussionId());
        model.setModelId(soapModel.getModelId());
        model.setCategoryId(soapModel.getCategoryId());

        return model;
    }

    public static List<ModelDiscussion> toModels(
        ModelDiscussionSoap[] soapModels) {
        List<ModelDiscussion> models = new ArrayList<ModelDiscussion>(soapModels.length);

        for (ModelDiscussionSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _modelDiscussionId;
    }

    public void setPrimaryKey(Long pk) {
        setModelDiscussionId(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _modelDiscussionId;
    }

    public Long getModelDiscussionId() {
        return _modelDiscussionId;
    }

    public void setModelDiscussionId(Long modelDiscussionId) {
        _modelDiscussionId = modelDiscussionId;
    }

    public Long getModelId() {
        return _modelId;
    }

    public void setModelId(Long modelId) {
        _modelId = modelId;
    }

    public Long getCategoryId() {
        return _categoryId;
    }

    public void setCategoryId(Long categoryId) {
        _categoryId = categoryId;
    }

    public ModelDiscussion toEscapedModel() {
        if (isEscapedModel()) {
            return (ModelDiscussion) this;
        } else {
            ModelDiscussion model = new ModelDiscussionImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setModelDiscussionId(getModelDiscussionId());
            model.setModelId(getModelId());
            model.setCategoryId(getCategoryId());

            model = (ModelDiscussion) Proxy.newProxyInstance(ModelDiscussion.class.getClassLoader(),
                    new Class[] { ModelDiscussion.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        ModelDiscussionImpl clone = new ModelDiscussionImpl();

        clone.setModelDiscussionId(getModelDiscussionId());
        clone.setModelId(getModelId());
        clone.setCategoryId(getCategoryId());

        return clone;
    }

    public int compareTo(ModelDiscussion modelDiscussion) {
        Long pk = modelDiscussion.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        ModelDiscussion modelDiscussion = null;

        try {
            modelDiscussion = (ModelDiscussion) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = modelDiscussion.getPrimaryKey();

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

        sb.append("{modelDiscussionId=");
        sb.append(getModelDiscussionId());
        sb.append(", modelId=");
        sb.append(getModelId());
        sb.append(", categoryId=");
        sb.append(getCategoryId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.models.model.ModelDiscussion");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>modelDiscussionId</column-name><column-value><![CDATA[");
        sb.append(getModelDiscussionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelId</column-name><column-value><![CDATA[");
        sb.append(getModelId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>categoryId</column-name><column-value><![CDATA[");
        sb.append(getCategoryId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
