package com.ext.portlet.models.model.impl;

import com.ext.portlet.models.model.ModelGlobalPreference;
import com.ext.portlet.models.model.ModelGlobalPreferenceSoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="ModelGlobalPreferenceModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>ModelGlobalPreference</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.model.ModelGlobalPreference
 * @see com.ext.portlet.models.model.ModelGlobalPreferenceModel
 * @see com.ext.portlet.models.model.impl.ModelGlobalPreferenceImpl
 *
 */
public class ModelGlobalPreferenceModelImpl extends BaseModelImpl<ModelGlobalPreference> {
    public static final String TABLE_NAME = "ModelGlobalPreference";
    public static final Object[][] TABLE_COLUMNS = {
            { "modelGlobalPreferencePK", new Integer(Types.BIGINT) },
            

            { "modelId", new Integer(Types.BIGINT) },
            

            { "visible", new Integer(Types.BOOLEAN) },
            

            { "weight", new Integer(Types.INTEGER) },
            

            { "expertEvaluationPageId", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table ModelGlobalPreference (modelGlobalPreferencePK LONG not null primary key,modelId LONG,visible BOOLEAN,weight INTEGER,expertEvaluationPageId LONG)";
    public static final String TABLE_SQL_DROP = "drop table ModelGlobalPreference";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.models.model.ModelGlobalPreference"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.models.model.ModelGlobalPreference"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.models.model.ModelGlobalPreference"));
    private Long _modelGlobalPreferencePK;
    private Long _modelId;
    private Long _originalModelId;
    private Boolean _visible;
    private Integer _weight;
    private Long _expertEvaluationPageId;

    public ModelGlobalPreferenceModelImpl() {
    }

    public static ModelGlobalPreference toModel(
        ModelGlobalPreferenceSoap soapModel) {
        ModelGlobalPreference model = new ModelGlobalPreferenceImpl();

        model.setModelGlobalPreferencePK(soapModel.getModelGlobalPreferencePK());
        model.setModelId(soapModel.getModelId());
        model.setVisible(soapModel.getVisible());
        model.setWeight(soapModel.getWeight());
        model.setExpertEvaluationPageId(soapModel.getExpertEvaluationPageId());

        return model;
    }

    public static List<ModelGlobalPreference> toModels(
        ModelGlobalPreferenceSoap[] soapModels) {
        List<ModelGlobalPreference> models = new ArrayList<ModelGlobalPreference>(soapModels.length);

        for (ModelGlobalPreferenceSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _modelGlobalPreferencePK;
    }

    public void setPrimaryKey(Long pk) {
        setModelGlobalPreferencePK(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _modelGlobalPreferencePK;
    }

    public Long getModelGlobalPreferencePK() {
        return _modelGlobalPreferencePK;
    }

    public void setModelGlobalPreferencePK(Long modelGlobalPreferencePK) {
        _modelGlobalPreferencePK = modelGlobalPreferencePK;
    }

    public Long getModelId() {
        return _modelId;
    }

    public void setModelId(Long modelId) {
        _modelId = modelId;

        if (_originalModelId == null) {
            _originalModelId = modelId;
        }
    }

    public Long getOriginalModelId() {
        return _originalModelId;
    }

    public Boolean getVisible() {
        return _visible;
    }

    public void setVisible(Boolean visible) {
        _visible = visible;
    }

    public Integer getWeight() {
        return _weight;
    }

    public void setWeight(Integer weight) {
        _weight = weight;
    }

    public Long getExpertEvaluationPageId() {
        return _expertEvaluationPageId;
    }

    public void setExpertEvaluationPageId(Long expertEvaluationPageId) {
        _expertEvaluationPageId = expertEvaluationPageId;
    }

    public ModelGlobalPreference toEscapedModel() {
        if (isEscapedModel()) {
            return (ModelGlobalPreference) this;
        } else {
            ModelGlobalPreference model = new ModelGlobalPreferenceImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setModelGlobalPreferencePK(getModelGlobalPreferencePK());
            model.setModelId(getModelId());
            model.setVisible(getVisible());
            model.setWeight(getWeight());
            model.setExpertEvaluationPageId(getExpertEvaluationPageId());

            model = (ModelGlobalPreference) Proxy.newProxyInstance(ModelGlobalPreference.class.getClassLoader(),
                    new Class[] { ModelGlobalPreference.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        ModelGlobalPreferenceImpl clone = new ModelGlobalPreferenceImpl();

        clone.setModelGlobalPreferencePK(getModelGlobalPreferencePK());
        clone.setModelId(getModelId());
        clone.setVisible(getVisible());
        clone.setWeight(getWeight());
        clone.setExpertEvaluationPageId(getExpertEvaluationPageId());

        return clone;
    }

    public int compareTo(ModelGlobalPreference modelGlobalPreference) {
        Long pk = modelGlobalPreference.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        ModelGlobalPreference modelGlobalPreference = null;

        try {
            modelGlobalPreference = (ModelGlobalPreference) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = modelGlobalPreference.getPrimaryKey();

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

        sb.append("{modelGlobalPreferencePK=");
        sb.append(getModelGlobalPreferencePK());
        sb.append(", modelId=");
        sb.append(getModelId());
        sb.append(", visible=");
        sb.append(getVisible());
        sb.append(", weight=");
        sb.append(getWeight());
        sb.append(", expertEvaluationPageId=");
        sb.append(getExpertEvaluationPageId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.models.model.ModelGlobalPreference");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>modelGlobalPreferencePK</column-name><column-value><![CDATA[");
        sb.append(getModelGlobalPreferencePK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelId</column-name><column-value><![CDATA[");
        sb.append(getModelId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>visible</column-name><column-value><![CDATA[");
        sb.append(getVisible());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>weight</column-name><column-value><![CDATA[");
        sb.append(getWeight());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>expertEvaluationPageId</column-name><column-value><![CDATA[");
        sb.append(getExpertEvaluationPageId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
