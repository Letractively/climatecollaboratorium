package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanSection;
import com.ext.portlet.plans.model.PlanSectionSoap;
import com.ext.portlet.plans.service.persistence.PlanSectionPK;

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
 * <a href="PlanSectionModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>PlanSection</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanSection
 * @see com.ext.portlet.plans.model.PlanSectionModel
 * @see com.ext.portlet.plans.model.impl.PlanSectionImpl
 *
 */
public class PlanSectionModelImpl extends BaseModelImpl<PlanSection> {
    public static final String TABLE_NAME = "PlanSection";
    public static final Object[][] TABLE_COLUMNS = {
            { "planSectionDefinitionId", new Integer(Types.BIGINT) },
            

            { "planId", new Integer(Types.BIGINT) },
            

            { "content", new Integer(Types.VARCHAR) }
        };
    public static final String TABLE_SQL_CREATE = "create table PlanSection (planSectionDefinitionId LONG not null,planId LONG not null,content VARCHAR(75) null,primary key (planSectionDefinitionId, planId))";
    public static final String TABLE_SQL_DROP = "drop table PlanSection";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.plans.model.PlanSection"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.plans.model.PlanSection"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.plans.model.PlanSection"));
    private Long _planSectionDefinitionId;
    private Long _planId;
    private String _content;

    public PlanSectionModelImpl() {
    }

    public static PlanSection toModel(PlanSectionSoap soapModel) {
        PlanSection model = new PlanSectionImpl();

        model.setPlanSectionDefinitionId(soapModel.getPlanSectionDefinitionId());
        model.setPlanId(soapModel.getPlanId());
        model.setContent(soapModel.getContent());

        return model;
    }

    public static List<PlanSection> toModels(PlanSectionSoap[] soapModels) {
        List<PlanSection> models = new ArrayList<PlanSection>(soapModels.length);

        for (PlanSectionSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public PlanSectionPK getPrimaryKey() {
        return new PlanSectionPK(_planSectionDefinitionId, _planId);
    }

    public void setPrimaryKey(PlanSectionPK pk) {
        setPlanSectionDefinitionId(pk.planSectionDefinitionId);
        setPlanId(pk.planId);
    }

    public Serializable getPrimaryKeyObj() {
        return new PlanSectionPK(_planSectionDefinitionId, _planId);
    }

    public Long getPlanSectionDefinitionId() {
        return _planSectionDefinitionId;
    }

    public void setPlanSectionDefinitionId(Long planSectionDefinitionId) {
        _planSectionDefinitionId = planSectionDefinitionId;
    }

    public Long getPlanId() {
        return _planId;
    }

    public void setPlanId(Long planId) {
        _planId = planId;
    }

    public String getContent() {
        return GetterUtil.getString(_content);
    }

    public void setContent(String content) {
        _content = content;
    }

    public PlanSection toEscapedModel() {
        if (isEscapedModel()) {
            return (PlanSection) this;
        } else {
            PlanSection model = new PlanSectionImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setPlanSectionDefinitionId(getPlanSectionDefinitionId());
            model.setPlanId(getPlanId());
            model.setContent(HtmlUtil.escape(getContent()));

            model = (PlanSection) Proxy.newProxyInstance(PlanSection.class.getClassLoader(),
                    new Class[] { PlanSection.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        PlanSectionImpl clone = new PlanSectionImpl();

        clone.setPlanSectionDefinitionId(getPlanSectionDefinitionId());
        clone.setPlanId(getPlanId());
        clone.setContent(getContent());

        return clone;
    }

    public int compareTo(PlanSection planSection) {
        PlanSectionPK pk = planSection.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlanSection planSection = null;

        try {
            planSection = (PlanSection) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        PlanSectionPK pk = planSection.getPrimaryKey();

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

        sb.append("{planSectionDefinitionId=");
        sb.append(getPlanSectionDefinitionId());
        sb.append(", planId=");
        sb.append(getPlanId());
        sb.append(", content=");
        sb.append(getContent());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlanSection");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>planSectionDefinitionId</column-name><column-value><![CDATA[");
        sb.append(getPlanSectionDefinitionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planId</column-name><column-value><![CDATA[");
        sb.append(getPlanId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>content</column-name><column-value><![CDATA[");
        sb.append(getContent());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
