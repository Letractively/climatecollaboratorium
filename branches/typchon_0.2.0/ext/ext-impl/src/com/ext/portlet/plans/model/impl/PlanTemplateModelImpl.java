package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanTemplate;
import com.ext.portlet.plans.model.PlanTemplateSoap;

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
 * <a href="PlanTemplateModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>PlanTemplate</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanTemplate
 * @see com.ext.portlet.plans.model.PlanTemplateModel
 * @see com.ext.portlet.plans.model.impl.PlanTemplateImpl
 *
 */
public class PlanTemplateModelImpl extends BaseModelImpl<PlanTemplate> {
    public static final String TABLE_NAME = "PlanTemplate";
    public static final Object[][] TABLE_COLUMNS = {
            { "id_", new Integer(Types.BIGINT) },
            

            { "name", new Integer(Types.VARCHAR) }
        };
    public static final String TABLE_SQL_CREATE = "create table PlanTemplate (id_ LONG not null primary key,name VARCHAR(75) null)";
    public static final String TABLE_SQL_DROP = "drop table PlanTemplate";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.plans.model.PlanTemplate"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.plans.model.PlanTemplate"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.plans.model.PlanTemplate"));
    private Long _id;
    private String _name;

    public PlanTemplateModelImpl() {
    }

    public static PlanTemplate toModel(PlanTemplateSoap soapModel) {
        PlanTemplate model = new PlanTemplateImpl();

        model.setId(soapModel.getId());
        model.setName(soapModel.getName());

        return model;
    }

    public static List<PlanTemplate> toModels(PlanTemplateSoap[] soapModels) {
        List<PlanTemplate> models = new ArrayList<PlanTemplate>(soapModels.length);

        for (PlanTemplateSoap soapModel : soapModels) {
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

    public String getName() {
        return GetterUtil.getString(_name);
    }

    public void setName(String name) {
        _name = name;
    }

    public PlanTemplate toEscapedModel() {
        if (isEscapedModel()) {
            return (PlanTemplate) this;
        } else {
            PlanTemplate model = new PlanTemplateImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setId(getId());
            model.setName(HtmlUtil.escape(getName()));

            model = (PlanTemplate) Proxy.newProxyInstance(PlanTemplate.class.getClassLoader(),
                    new Class[] { PlanTemplate.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        PlanTemplateImpl clone = new PlanTemplateImpl();

        clone.setId(getId());
        clone.setName(getName());

        return clone;
    }

    public int compareTo(PlanTemplate planTemplate) {
        Long pk = planTemplate.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlanTemplate planTemplate = null;

        try {
            planTemplate = (PlanTemplate) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = planTemplate.getPrimaryKey();

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
        sb.append(", name=");
        sb.append(getName());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlanTemplate");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
