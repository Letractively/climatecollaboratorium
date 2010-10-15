package com.ext.portlet.models.model.impl;

import com.ext.portlet.models.model.ModelCategory;
import com.ext.portlet.models.model.ModelCategorySoap;

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
 * <a href="ModelCategoryModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>ModelCategory</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.model.ModelCategory
 * @see com.ext.portlet.models.model.ModelCategoryModel
 * @see com.ext.portlet.models.model.impl.ModelCategoryImpl
 *
 */
public class ModelCategoryModelImpl extends BaseModelImpl<ModelCategory> {
    public static final String TABLE_NAME = "ModelCategory";
    public static final Object[][] TABLE_COLUMNS = {
            { "modelCategoryPK", new Integer(Types.BIGINT) },
            

            { "modelCategoryName", new Integer(Types.VARCHAR) },
            

            { "modelCategoryDescription", new Integer(Types.VARCHAR) },
            

            { "modelCategoryDisplayWeight", new Integer(Types.INTEGER) }
        };
    public static final String TABLE_SQL_CREATE = "create table ModelCategory (modelCategoryPK LONG not null primary key,modelCategoryName VARCHAR(75) null,modelCategoryDescription VARCHAR(75) null,modelCategoryDisplayWeight INTEGER)";
    public static final String TABLE_SQL_DROP = "drop table ModelCategory";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.models.model.ModelCategory"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.models.model.ModelCategory"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.models.model.ModelCategory"));
    private Long _modelCategoryPK;
    private String _modelCategoryName;
    private String _modelCategoryDescription;
    private Integer _modelCategoryDisplayWeight;

    public ModelCategoryModelImpl() {
    }

    public static ModelCategory toModel(ModelCategorySoap soapModel) {
        ModelCategory model = new ModelCategoryImpl();

        model.setModelCategoryPK(soapModel.getModelCategoryPK());
        model.setModelCategoryName(soapModel.getModelCategoryName());
        model.setModelCategoryDescription(soapModel.getModelCategoryDescription());
        model.setModelCategoryDisplayWeight(soapModel.getModelCategoryDisplayWeight());

        return model;
    }

    public static List<ModelCategory> toModels(ModelCategorySoap[] soapModels) {
        List<ModelCategory> models = new ArrayList<ModelCategory>(soapModels.length);

        for (ModelCategorySoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _modelCategoryPK;
    }

    public void setPrimaryKey(Long pk) {
        setModelCategoryPK(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _modelCategoryPK;
    }

    public Long getModelCategoryPK() {
        return _modelCategoryPK;
    }

    public void setModelCategoryPK(Long modelCategoryPK) {
        _modelCategoryPK = modelCategoryPK;
    }

    public String getModelCategoryName() {
        return GetterUtil.getString(_modelCategoryName);
    }

    public void setModelCategoryName(String modelCategoryName) {
        _modelCategoryName = modelCategoryName;
    }

    public String getModelCategoryDescription() {
        return GetterUtil.getString(_modelCategoryDescription);
    }

    public void setModelCategoryDescription(String modelCategoryDescription) {
        _modelCategoryDescription = modelCategoryDescription;
    }

    public Integer getModelCategoryDisplayWeight() {
        return _modelCategoryDisplayWeight;
    }

    public void setModelCategoryDisplayWeight(
        Integer modelCategoryDisplayWeight) {
        _modelCategoryDisplayWeight = modelCategoryDisplayWeight;
    }

    public ModelCategory toEscapedModel() {
        if (isEscapedModel()) {
            return (ModelCategory) this;
        } else {
            ModelCategory model = new ModelCategoryImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setModelCategoryPK(getModelCategoryPK());
            model.setModelCategoryName(HtmlUtil.escape(getModelCategoryName()));
            model.setModelCategoryDescription(HtmlUtil.escape(
                    getModelCategoryDescription()));
            model.setModelCategoryDisplayWeight(getModelCategoryDisplayWeight());

            model = (ModelCategory) Proxy.newProxyInstance(ModelCategory.class.getClassLoader(),
                    new Class[] { ModelCategory.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        ModelCategoryImpl clone = new ModelCategoryImpl();

        clone.setModelCategoryPK(getModelCategoryPK());
        clone.setModelCategoryName(getModelCategoryName());
        clone.setModelCategoryDescription(getModelCategoryDescription());
        clone.setModelCategoryDisplayWeight(getModelCategoryDisplayWeight());

        return clone;
    }

    public int compareTo(ModelCategory modelCategory) {
        Long pk = modelCategory.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        ModelCategory modelCategory = null;

        try {
            modelCategory = (ModelCategory) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = modelCategory.getPrimaryKey();

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

        sb.append("{modelCategoryPK=");
        sb.append(getModelCategoryPK());
        sb.append(", modelCategoryName=");
        sb.append(getModelCategoryName());
        sb.append(", modelCategoryDescription=");
        sb.append(getModelCategoryDescription());
        sb.append(", modelCategoryDisplayWeight=");
        sb.append(getModelCategoryDisplayWeight());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.models.model.ModelCategory");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>modelCategoryPK</column-name><column-value><![CDATA[");
        sb.append(getModelCategoryPK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelCategoryName</column-name><column-value><![CDATA[");
        sb.append(getModelCategoryName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelCategoryDescription</column-name><column-value><![CDATA[");
        sb.append(getModelCategoryDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelCategoryDisplayWeight</column-name><column-value><![CDATA[");
        sb.append(getModelCategoryDisplayWeight());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
