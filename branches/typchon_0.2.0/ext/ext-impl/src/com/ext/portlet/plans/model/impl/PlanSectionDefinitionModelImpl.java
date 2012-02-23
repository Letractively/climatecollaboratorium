package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanSectionDefinition;
import com.ext.portlet.plans.model.PlanSectionDefinitionSoap;

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
 * <a href="PlanSectionDefinitionModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>PlanSectionDefinition</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanSectionDefinition
 * @see com.ext.portlet.plans.model.PlanSectionDefinitionModel
 * @see com.ext.portlet.plans.model.impl.PlanSectionDefinitionImpl
 *
 */
public class PlanSectionDefinitionModelImpl extends BaseModelImpl<PlanSectionDefinition> {
    public static final String TABLE_NAME = "PlanSectionDefinition";
    public static final Object[][] TABLE_COLUMNS = {
            { "id_", new Integer(Types.BIGINT) },
            

            { "title", new Integer(Types.VARCHAR) },
            

            { "defaultText", new Integer(Types.VARCHAR) },
            

            { "categoryId", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table PlanSectionDefinition (id_ LONG not null primary key,title VARCHAR(75) null,defaultText VARCHAR(75) null,categoryId LONG)";
    public static final String TABLE_SQL_DROP = "drop table PlanSectionDefinition";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.plans.model.PlanSectionDefinition"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.plans.model.PlanSectionDefinition"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.plans.model.PlanSectionDefinition"));
    private Long _id;
    private String _title;
    private String _defaultText;
    private Long _categoryId;

    public PlanSectionDefinitionModelImpl() {
    }

    public static PlanSectionDefinition toModel(
        PlanSectionDefinitionSoap soapModel) {
        PlanSectionDefinition model = new PlanSectionDefinitionImpl();

        model.setId(soapModel.getId());
        model.setTitle(soapModel.getTitle());
        model.setDefaultText(soapModel.getDefaultText());
        model.setCategoryId(soapModel.getCategoryId());

        return model;
    }

    public static List<PlanSectionDefinition> toModels(
        PlanSectionDefinitionSoap[] soapModels) {
        List<PlanSectionDefinition> models = new ArrayList<PlanSectionDefinition>(soapModels.length);

        for (PlanSectionDefinitionSoap soapModel : soapModels) {
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

    public String getTitle() {
        return GetterUtil.getString(_title);
    }

    public void setTitle(String title) {
        _title = title;
    }

    public String getDefaultText() {
        return GetterUtil.getString(_defaultText);
    }

    public void setDefaultText(String defaultText) {
        _defaultText = defaultText;
    }

    public Long getCategoryId() {
        return _categoryId;
    }

    public void setCategoryId(Long categoryId) {
        _categoryId = categoryId;
    }

    public PlanSectionDefinition toEscapedModel() {
        if (isEscapedModel()) {
            return (PlanSectionDefinition) this;
        } else {
            PlanSectionDefinition model = new PlanSectionDefinitionImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setId(getId());
            model.setTitle(HtmlUtil.escape(getTitle()));
            model.setDefaultText(HtmlUtil.escape(getDefaultText()));
            model.setCategoryId(getCategoryId());

            model = (PlanSectionDefinition) Proxy.newProxyInstance(PlanSectionDefinition.class.getClassLoader(),
                    new Class[] { PlanSectionDefinition.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        PlanSectionDefinitionImpl clone = new PlanSectionDefinitionImpl();

        clone.setId(getId());
        clone.setTitle(getTitle());
        clone.setDefaultText(getDefaultText());
        clone.setCategoryId(getCategoryId());

        return clone;
    }

    public int compareTo(PlanSectionDefinition planSectionDefinition) {
        Long pk = planSectionDefinition.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlanSectionDefinition planSectionDefinition = null;

        try {
            planSectionDefinition = (PlanSectionDefinition) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = planSectionDefinition.getPrimaryKey();

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
        sb.append(", title=");
        sb.append(getTitle());
        sb.append(", defaultText=");
        sb.append(getDefaultText());
        sb.append(", categoryId=");
        sb.append(getCategoryId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlanSectionDefinition");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>title</column-name><column-value><![CDATA[");
        sb.append(getTitle());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>defaultText</column-name><column-value><![CDATA[");
        sb.append(getDefaultText());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>categoryId</column-name><column-value><![CDATA[");
        sb.append(getCategoryId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
