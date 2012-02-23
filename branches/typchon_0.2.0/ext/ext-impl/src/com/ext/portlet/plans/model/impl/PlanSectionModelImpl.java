package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanSection;
import com.ext.portlet.plans.model.PlanSectionSoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
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
            { "id_", new Integer(Types.BIGINT) },
            

            { "planSectionDefinitionId", new Integer(Types.BIGINT) },
            

            { "planId", new Integer(Types.BIGINT) },
            

            { "content", new Integer(Types.VARCHAR) },
            

            { "created", new Integer(Types.TIMESTAMP) },
            

            { "version", new Integer(Types.BIGINT) },
            

            { "planVersion", new Integer(Types.BIGINT) },
            

            { "updateAuthorId", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table PlanSection (id_ LONG not null primary key,planSectionDefinitionId LONG,planId LONG,content VARCHAR(75) null,created DATE null,version LONG,planVersion LONG,updateAuthorId LONG)";
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
    private Long _id;
    private Long _planSectionDefinitionId;
    private Long _originalPlanSectionDefinitionId;
    private Long _planId;
    private Long _originalPlanId;
    private String _content;
    private Date _created;
    private Long _version;
    private Long _planVersion;
    private Long _updateAuthorId;

    public PlanSectionModelImpl() {
    }

    public static PlanSection toModel(PlanSectionSoap soapModel) {
        PlanSection model = new PlanSectionImpl();

        model.setId(soapModel.getId());
        model.setPlanSectionDefinitionId(soapModel.getPlanSectionDefinitionId());
        model.setPlanId(soapModel.getPlanId());
        model.setContent(soapModel.getContent());
        model.setCreated(soapModel.getCreated());
        model.setVersion(soapModel.getVersion());
        model.setPlanVersion(soapModel.getPlanVersion());
        model.setUpdateAuthorId(soapModel.getUpdateAuthorId());

        return model;
    }

    public static List<PlanSection> toModels(PlanSectionSoap[] soapModels) {
        List<PlanSection> models = new ArrayList<PlanSection>(soapModels.length);

        for (PlanSectionSoap soapModel : soapModels) {
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

    public Long getPlanSectionDefinitionId() {
        return _planSectionDefinitionId;
    }

    public void setPlanSectionDefinitionId(Long planSectionDefinitionId) {
        _planSectionDefinitionId = planSectionDefinitionId;

        if (_originalPlanSectionDefinitionId == null) {
            _originalPlanSectionDefinitionId = planSectionDefinitionId;
        }
    }

    public Long getOriginalPlanSectionDefinitionId() {
        return _originalPlanSectionDefinitionId;
    }

    public Long getPlanId() {
        return _planId;
    }

    public void setPlanId(Long planId) {
        _planId = planId;

        if (_originalPlanId == null) {
            _originalPlanId = planId;
        }
    }

    public Long getOriginalPlanId() {
        return _originalPlanId;
    }

    public String getContent() {
        return GetterUtil.getString(_content);
    }

    public void setContent(String content) {
        _content = content;
    }

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        _created = created;
    }

    public Long getVersion() {
        return _version;
    }

    public void setVersion(Long version) {
        _version = version;
    }

    public Long getPlanVersion() {
        return _planVersion;
    }

    public void setPlanVersion(Long planVersion) {
        _planVersion = planVersion;
    }

    public Long getUpdateAuthorId() {
        return _updateAuthorId;
    }

    public void setUpdateAuthorId(Long updateAuthorId) {
        _updateAuthorId = updateAuthorId;
    }

    public PlanSection toEscapedModel() {
        if (isEscapedModel()) {
            return (PlanSection) this;
        } else {
            PlanSection model = new PlanSectionImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setId(getId());
            model.setPlanSectionDefinitionId(getPlanSectionDefinitionId());
            model.setPlanId(getPlanId());
            model.setContent(HtmlUtil.escape(getContent()));
            model.setCreated(getCreated());
            model.setVersion(getVersion());
            model.setPlanVersion(getPlanVersion());
            model.setUpdateAuthorId(getUpdateAuthorId());

            model = (PlanSection) Proxy.newProxyInstance(PlanSection.class.getClassLoader(),
                    new Class[] { PlanSection.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        PlanSectionImpl clone = new PlanSectionImpl();

        clone.setId(getId());
        clone.setPlanSectionDefinitionId(getPlanSectionDefinitionId());
        clone.setPlanId(getPlanId());
        clone.setContent(getContent());
        clone.setCreated(getCreated());
        clone.setVersion(getVersion());
        clone.setPlanVersion(getPlanVersion());
        clone.setUpdateAuthorId(getUpdateAuthorId());

        return clone;
    }

    public int compareTo(PlanSection planSection) {
        int value = 0;

        value = getId().compareTo(planSection.getId());

        value = value * -1;

        if (value != 0) {
            return value;
        }

        return 0;
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

        Long pk = planSection.getPrimaryKey();

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
        sb.append(", planSectionDefinitionId=");
        sb.append(getPlanSectionDefinitionId());
        sb.append(", planId=");
        sb.append(getPlanId());
        sb.append(", content=");
        sb.append(getContent());
        sb.append(", created=");
        sb.append(getCreated());
        sb.append(", version=");
        sb.append(getVersion());
        sb.append(", planVersion=");
        sb.append(getPlanVersion());
        sb.append(", updateAuthorId=");
        sb.append(getUpdateAuthorId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlanSection");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
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
        sb.append(
            "<column><column-name>created</column-name><column-value><![CDATA[");
        sb.append(getCreated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>version</column-name><column-value><![CDATA[");
        sb.append(getVersion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planVersion</column-name><column-value><![CDATA[");
        sb.append(getPlanVersion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updateAuthorId</column-name><column-value><![CDATA[");
        sb.append(getUpdateAuthorId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
