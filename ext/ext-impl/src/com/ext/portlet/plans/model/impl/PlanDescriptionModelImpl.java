package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanDescription;
import com.ext.portlet.plans.model.PlanDescriptionSoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.DateUtil;
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
 * <a href="PlanDescriptionModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>PlanDescription</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanDescription
 * @see com.ext.portlet.plans.model.PlanDescriptionModel
 * @see com.ext.portlet.plans.model.impl.PlanDescriptionImpl
 *
 */
public class PlanDescriptionModelImpl extends BaseModelImpl<PlanDescription> {
    public static final String TABLE_NAME = "PlanDescription";
    public static final Object[][] TABLE_COLUMNS = {
            { "id_", new Integer(Types.BIGINT) },
            

            { "planId", new Integer(Types.BIGINT) },
            

            { "name", new Integer(Types.VARCHAR) },
            

            { "description", new Integer(Types.VARCHAR) },
            

            { "version", new Integer(Types.BIGINT) },
            

            { "planVersion", new Integer(Types.BIGINT) },
            

            { "created", new Integer(Types.TIMESTAMP) },
            

            { "updateAuthorId", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table PlanDescription (id_ LONG not null primary key,planId LONG,name VARCHAR(75) null,description VARCHAR(75) null,version LONG,planVersion LONG,created DATE null,updateAuthorId LONG)";
    public static final String TABLE_SQL_DROP = "drop table PlanDescription";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.plans.model.PlanDescription"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.plans.model.PlanDescription"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.plans.model.PlanDescription"));
    private Long _id;
    private Long _planId;
    private Long _originalPlanId;
    private String _name;
    private String _description;
    private Long _version;
    private Long _planVersion;
    private Date _created;
    private Long _updateAuthorId;

    public PlanDescriptionModelImpl() {
    }

    public static PlanDescription toModel(PlanDescriptionSoap soapModel) {
        PlanDescription model = new PlanDescriptionImpl();

        model.setId(soapModel.getId());
        model.setPlanId(soapModel.getPlanId());
        model.setName(soapModel.getName());
        model.setDescription(soapModel.getDescription());
        model.setVersion(soapModel.getVersion());
        model.setPlanVersion(soapModel.getPlanVersion());
        model.setCreated(soapModel.getCreated());
        model.setUpdateAuthorId(soapModel.getUpdateAuthorId());

        return model;
    }

    public static List<PlanDescription> toModels(
        PlanDescriptionSoap[] soapModels) {
        List<PlanDescription> models = new ArrayList<PlanDescription>(soapModels.length);

        for (PlanDescriptionSoap soapModel : soapModels) {
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

    public String getName() {
        return GetterUtil.getString(_name);
    }

    public void setName(String name) {
        _name = name;
    }

    public String getDescription() {
        return GetterUtil.getString(_description);
    }

    public void setDescription(String description) {
        _description = description;
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

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        _created = created;
    }

    public Long getUpdateAuthorId() {
        return _updateAuthorId;
    }

    public void setUpdateAuthorId(Long updateAuthorId) {
        _updateAuthorId = updateAuthorId;
    }

    public PlanDescription toEscapedModel() {
        if (isEscapedModel()) {
            return (PlanDescription) this;
        } else {
            PlanDescription model = new PlanDescriptionImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setId(getId());
            model.setPlanId(getPlanId());
            model.setName(HtmlUtil.escape(getName()));
            model.setDescription(HtmlUtil.escape(getDescription()));
            model.setVersion(getVersion());
            model.setPlanVersion(getPlanVersion());
            model.setCreated(getCreated());
            model.setUpdateAuthorId(getUpdateAuthorId());

            model = (PlanDescription) Proxy.newProxyInstance(PlanDescription.class.getClassLoader(),
                    new Class[] { PlanDescription.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        PlanDescriptionImpl clone = new PlanDescriptionImpl();

        clone.setId(getId());
        clone.setPlanId(getPlanId());
        clone.setName(getName());
        clone.setDescription(getDescription());
        clone.setVersion(getVersion());
        clone.setPlanVersion(getPlanVersion());
        clone.setCreated(getCreated());
        clone.setUpdateAuthorId(getUpdateAuthorId());

        return clone;
    }

    public int compareTo(PlanDescription planDescription) {
        int value = 0;

        value = getVersion().compareTo(planDescription.getVersion());

        value = value * -1;

        if (value != 0) {
            return value;
        }

        value = DateUtil.compareTo(getCreated(), planDescription.getCreated());

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

        PlanDescription planDescription = null;

        try {
            planDescription = (PlanDescription) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = planDescription.getPrimaryKey();

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
        sb.append(", planId=");
        sb.append(getPlanId());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", version=");
        sb.append(getVersion());
        sb.append(", planVersion=");
        sb.append(getPlanVersion());
        sb.append(", created=");
        sb.append(getCreated());
        sb.append(", updateAuthorId=");
        sb.append(getUpdateAuthorId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlanDescription");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planId</column-name><column-value><![CDATA[");
        sb.append(getPlanId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
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
            "<column><column-name>created</column-name><column-value><![CDATA[");
        sb.append(getCreated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updateAuthorId</column-name><column-value><![CDATA[");
        sb.append(getUpdateAuthorId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
