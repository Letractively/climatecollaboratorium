package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanTemplateSection;
import com.ext.portlet.plans.model.PlanTemplateSectionSoap;
import com.ext.portlet.plans.service.persistence.PlanTemplateSectionPK;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="PlanTemplateSectionModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>PlanTemplateSection</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanTemplateSection
 * @see com.ext.portlet.plans.model.PlanTemplateSectionModel
 * @see com.ext.portlet.plans.model.impl.PlanTemplateSectionImpl
 *
 */
public class PlanTemplateSectionModelImpl extends BaseModelImpl<PlanTemplateSection> {
    public static final String TABLE_NAME = "PlanTemplateSection";
    public static final Object[][] TABLE_COLUMNS = {
            { "planTemplateId", new Integer(Types.BIGINT) },
            

            { "planSectionId", new Integer(Types.BIGINT) },
            

            { "weight", new Integer(Types.INTEGER) }
        };
    public static final String TABLE_SQL_CREATE = "create table PlanTemplateSection (planTemplateId LONG not null,planSectionId LONG not null,weight INTEGER,primary key (planTemplateId, planSectionId))";
    public static final String TABLE_SQL_DROP = "drop table PlanTemplateSection";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.plans.model.PlanTemplateSection"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.plans.model.PlanTemplateSection"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.plans.model.PlanTemplateSection"));
    private Long _planTemplateId;
    private Long _planSectionId;
    private Integer _weight;

    public PlanTemplateSectionModelImpl() {
    }

    public static PlanTemplateSection toModel(PlanTemplateSectionSoap soapModel) {
        PlanTemplateSection model = new PlanTemplateSectionImpl();

        model.setPlanTemplateId(soapModel.getPlanTemplateId());
        model.setPlanSectionId(soapModel.getPlanSectionId());
        model.setWeight(soapModel.getWeight());

        return model;
    }

    public static List<PlanTemplateSection> toModels(
        PlanTemplateSectionSoap[] soapModels) {
        List<PlanTemplateSection> models = new ArrayList<PlanTemplateSection>(soapModels.length);

        for (PlanTemplateSectionSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public PlanTemplateSectionPK getPrimaryKey() {
        return new PlanTemplateSectionPK(_planTemplateId, _planSectionId);
    }

    public void setPrimaryKey(PlanTemplateSectionPK pk) {
        setPlanTemplateId(pk.planTemplateId);
        setPlanSectionId(pk.planSectionId);
    }

    public Serializable getPrimaryKeyObj() {
        return new PlanTemplateSectionPK(_planTemplateId, _planSectionId);
    }

    public Long getPlanTemplateId() {
        return _planTemplateId;
    }

    public void setPlanTemplateId(Long planTemplateId) {
        _planTemplateId = planTemplateId;
    }

    public Long getPlanSectionId() {
        return _planSectionId;
    }

    public void setPlanSectionId(Long planSectionId) {
        _planSectionId = planSectionId;
    }

    public Integer getWeight() {
        return _weight;
    }

    public void setWeight(Integer weight) {
        _weight = weight;
    }

    public PlanTemplateSection toEscapedModel() {
        if (isEscapedModel()) {
            return (PlanTemplateSection) this;
        } else {
            PlanTemplateSection model = new PlanTemplateSectionImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setPlanTemplateId(getPlanTemplateId());
            model.setPlanSectionId(getPlanSectionId());
            model.setWeight(getWeight());

            model = (PlanTemplateSection) Proxy.newProxyInstance(PlanTemplateSection.class.getClassLoader(),
                    new Class[] { PlanTemplateSection.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        PlanTemplateSectionImpl clone = new PlanTemplateSectionImpl();

        clone.setPlanTemplateId(getPlanTemplateId());
        clone.setPlanSectionId(getPlanSectionId());
        clone.setWeight(getWeight());

        return clone;
    }

    public int compareTo(PlanTemplateSection planTemplateSection) {
        int value = 0;

        value = getWeight().compareTo(planTemplateSection.getWeight());

        if (value != 0) {
            return value;
        }

        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlanTemplateSection planTemplateSection = null;

        try {
            planTemplateSection = (PlanTemplateSection) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        PlanTemplateSectionPK pk = planTemplateSection.getPrimaryKey();

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

        sb.append("{planTemplateId=");
        sb.append(getPlanTemplateId());
        sb.append(", planSectionId=");
        sb.append(getPlanSectionId());
        sb.append(", weight=");
        sb.append(getWeight());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlanTemplateSection");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>planTemplateId</column-name><column-value><![CDATA[");
        sb.append(getPlanTemplateId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planSectionId</column-name><column-value><![CDATA[");
        sb.append(getPlanSectionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>weight</column-name><column-value><![CDATA[");
        sb.append(getWeight());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
