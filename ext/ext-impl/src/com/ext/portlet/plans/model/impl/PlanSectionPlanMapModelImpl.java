package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanSectionPlanMap;
import com.ext.portlet.plans.model.PlanSectionPlanMapSoap;
import com.ext.portlet.plans.service.persistence.PlanSectionPlanMapPK;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="PlanSectionPlanMapModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>PlanSectionPlanMap</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanSectionPlanMap
 * @see com.ext.portlet.plans.model.PlanSectionPlanMapModel
 * @see com.ext.portlet.plans.model.impl.PlanSectionPlanMapImpl
 *
 */
public class PlanSectionPlanMapModelImpl extends BaseModelImpl<PlanSectionPlanMap> {
    public static final String TABLE_NAME = "PlanSectionPlanMap";
    public static final Object[][] TABLE_COLUMNS = {
            { "sectionId", new Integer(Types.BIGINT) },
            

            { "relatedPlanId", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table PlanSectionPlanMap (sectionId LONG not null,relatedPlanId LONG not null,primary key (sectionId, relatedPlanId))";
    public static final String TABLE_SQL_DROP = "drop table PlanSectionPlanMap";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.plans.model.PlanSectionPlanMap"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.plans.model.PlanSectionPlanMap"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.plans.model.PlanSectionPlanMap"));
    private Long _sectionId;
    private Long _relatedPlanId;

    public PlanSectionPlanMapModelImpl() {
    }

    public static PlanSectionPlanMap toModel(PlanSectionPlanMapSoap soapModel) {
        PlanSectionPlanMap model = new PlanSectionPlanMapImpl();

        model.setSectionId(soapModel.getSectionId());
        model.setRelatedPlanId(soapModel.getRelatedPlanId());

        return model;
    }

    public static List<PlanSectionPlanMap> toModels(
        PlanSectionPlanMapSoap[] soapModels) {
        List<PlanSectionPlanMap> models = new ArrayList<PlanSectionPlanMap>(soapModels.length);

        for (PlanSectionPlanMapSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public PlanSectionPlanMapPK getPrimaryKey() {
        return new PlanSectionPlanMapPK(_sectionId, _relatedPlanId);
    }

    public void setPrimaryKey(PlanSectionPlanMapPK pk) {
        setSectionId(pk.sectionId);
        setRelatedPlanId(pk.relatedPlanId);
    }

    public Serializable getPrimaryKeyObj() {
        return new PlanSectionPlanMapPK(_sectionId, _relatedPlanId);
    }

    public Long getSectionId() {
        return _sectionId;
    }

    public void setSectionId(Long sectionId) {
        _sectionId = sectionId;
    }

    public Long getRelatedPlanId() {
        return _relatedPlanId;
    }

    public void setRelatedPlanId(Long relatedPlanId) {
        _relatedPlanId = relatedPlanId;
    }

    public PlanSectionPlanMap toEscapedModel() {
        if (isEscapedModel()) {
            return (PlanSectionPlanMap) this;
        } else {
            PlanSectionPlanMap model = new PlanSectionPlanMapImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setSectionId(getSectionId());
            model.setRelatedPlanId(getRelatedPlanId());

            model = (PlanSectionPlanMap) Proxy.newProxyInstance(PlanSectionPlanMap.class.getClassLoader(),
                    new Class[] { PlanSectionPlanMap.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        PlanSectionPlanMapImpl clone = new PlanSectionPlanMapImpl();

        clone.setSectionId(getSectionId());
        clone.setRelatedPlanId(getRelatedPlanId());

        return clone;
    }

    public int compareTo(PlanSectionPlanMap planSectionPlanMap) {
        PlanSectionPlanMapPK pk = planSectionPlanMap.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlanSectionPlanMap planSectionPlanMap = null;

        try {
            planSectionPlanMap = (PlanSectionPlanMap) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        PlanSectionPlanMapPK pk = planSectionPlanMap.getPrimaryKey();

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

        sb.append("{sectionId=");
        sb.append(getSectionId());
        sb.append(", relatedPlanId=");
        sb.append(getRelatedPlanId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlanSectionPlanMap");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>sectionId</column-name><column-value><![CDATA[");
        sb.append(getSectionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>relatedPlanId</column-name><column-value><![CDATA[");
        sb.append(getRelatedPlanId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
