package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanRelated;
import com.ext.portlet.plans.model.PlanRelatedSoap;
import com.ext.portlet.plans.service.persistence.PlanRelatedPK;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="PlanRelatedModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>PlanRelated</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanRelated
 * @see com.ext.portlet.plans.model.PlanRelatedModel
 * @see com.ext.portlet.plans.model.impl.PlanRelatedImpl
 *
 */
public class PlanRelatedModelImpl extends BaseModelImpl<PlanRelated> {
    public static final String TABLE_NAME = "PlanRelated";
    public static final Object[][] TABLE_COLUMNS = {
            { "sectionId", new Integer(Types.BIGINT) },
            

            { "relatedPlanId", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table PlanRelated (sectionId LONG not null,relatedPlanId LONG not null,primary key (sectionId, relatedPlanId))";
    public static final String TABLE_SQL_DROP = "drop table PlanRelated";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.plans.model.PlanRelated"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.plans.model.PlanRelated"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.plans.model.PlanRelated"));
    private Long _sectionId;
    private Long _relatedPlanId;

    public PlanRelatedModelImpl() {
    }

    public static PlanRelated toModel(PlanRelatedSoap soapModel) {
        PlanRelated model = new PlanRelatedImpl();

        model.setSectionId(soapModel.getSectionId());
        model.setRelatedPlanId(soapModel.getRelatedPlanId());

        return model;
    }

    public static List<PlanRelated> toModels(PlanRelatedSoap[] soapModels) {
        List<PlanRelated> models = new ArrayList<PlanRelated>(soapModels.length);

        for (PlanRelatedSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public PlanRelatedPK getPrimaryKey() {
        return new PlanRelatedPK(_sectionId, _relatedPlanId);
    }

    public void setPrimaryKey(PlanRelatedPK pk) {
        setSectionId(pk.sectionId);
        setRelatedPlanId(pk.relatedPlanId);
    }

    public Serializable getPrimaryKeyObj() {
        return new PlanRelatedPK(_sectionId, _relatedPlanId);
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

    public PlanRelated toEscapedModel() {
        if (isEscapedModel()) {
            return (PlanRelated) this;
        } else {
            PlanRelated model = new PlanRelatedImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setSectionId(getSectionId());
            model.setRelatedPlanId(getRelatedPlanId());

            model = (PlanRelated) Proxy.newProxyInstance(PlanRelated.class.getClassLoader(),
                    new Class[] { PlanRelated.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        PlanRelatedImpl clone = new PlanRelatedImpl();

        clone.setSectionId(getSectionId());
        clone.setRelatedPlanId(getRelatedPlanId());

        return clone;
    }

    public int compareTo(PlanRelated planRelated) {
        PlanRelatedPK pk = planRelated.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlanRelated planRelated = null;

        try {
            planRelated = (PlanRelated) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        PlanRelatedPK pk = planRelated.getPrimaryKey();

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
        sb.append("com.ext.portlet.plans.model.PlanRelated");
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
