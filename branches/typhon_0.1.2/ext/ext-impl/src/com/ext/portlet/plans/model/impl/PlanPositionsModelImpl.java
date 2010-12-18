package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanPositions;
import com.ext.portlet.plans.model.PlanPositionsSoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <a href="PlanPositionsModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>PlanPositions</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanPositions
 * @see com.ext.portlet.plans.model.PlanPositionsModel
 * @see com.ext.portlet.plans.model.impl.PlanPositionsImpl
 *
 */
public class PlanPositionsModelImpl extends BaseModelImpl<PlanPositions> {
    public static final String TABLE_NAME = "PlanPositions";
    public static final Object[][] TABLE_COLUMNS = {
            { "id_", new Integer(Types.BIGINT) },
            

            { "planId", new Integer(Types.BIGINT) },
            

            { "planVersion", new Integer(Types.BIGINT) },
            

            { "version", new Integer(Types.BIGINT) },
            

            { "created", new Integer(Types.TIMESTAMP) },
            

            { "updateAuthorId", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table PlanPositions (id_ LONG not null primary key,planId LONG,planVersion LONG,version LONG,created DATE null,updateAuthorId LONG)";
    public static final String TABLE_SQL_DROP = "drop table PlanPositions";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.plans.model.PlanPositions"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.plans.model.PlanPositions"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.plans.model.PlanPositions"));
    private Long _id;
    private Long _planId;
    private Long _originalPlanId;
    private Long _planVersion;
    private Long _version;
    private Date _created;
    private Long _updateAuthorId;

    public PlanPositionsModelImpl() {
    }

    public static PlanPositions toModel(PlanPositionsSoap soapModel) {
        PlanPositions model = new PlanPositionsImpl();

        model.setId(soapModel.getId());
        model.setPlanId(soapModel.getPlanId());
        model.setPlanVersion(soapModel.getPlanVersion());
        model.setVersion(soapModel.getVersion());
        model.setCreated(soapModel.getCreated());
        model.setUpdateAuthorId(soapModel.getUpdateAuthorId());

        return model;
    }

    public static List<PlanPositions> toModels(PlanPositionsSoap[] soapModels) {
        List<PlanPositions> models = new ArrayList<PlanPositions>(soapModels.length);

        for (PlanPositionsSoap soapModel : soapModels) {
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

    public Long getPlanVersion() {
        return _planVersion;
    }

    public void setPlanVersion(Long planVersion) {
        _planVersion = planVersion;
    }

    public Long getVersion() {
        return _version;
    }

    public void setVersion(Long version) {
        _version = version;
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

    public PlanPositions toEscapedModel() {
        if (isEscapedModel()) {
            return (PlanPositions) this;
        } else {
            PlanPositions model = new PlanPositionsImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setId(getId());
            model.setPlanId(getPlanId());
            model.setPlanVersion(getPlanVersion());
            model.setVersion(getVersion());
            model.setCreated(getCreated());
            model.setUpdateAuthorId(getUpdateAuthorId());

            model = (PlanPositions) Proxy.newProxyInstance(PlanPositions.class.getClassLoader(),
                    new Class[] { PlanPositions.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        PlanPositionsImpl clone = new PlanPositionsImpl();

        clone.setId(getId());
        clone.setPlanId(getPlanId());
        clone.setPlanVersion(getPlanVersion());
        clone.setVersion(getVersion());
        clone.setCreated(getCreated());
        clone.setUpdateAuthorId(getUpdateAuthorId());

        return clone;
    }

    public int compareTo(PlanPositions planPositions) {
        int value = 0;

        value = getId().compareTo(planPositions.getId());

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

        PlanPositions planPositions = null;

        try {
            planPositions = (PlanPositions) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = planPositions.getPrimaryKey();

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
        sb.append(", planVersion=");
        sb.append(getPlanVersion());
        sb.append(", version=");
        sb.append(getVersion());
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
        sb.append("com.ext.portlet.plans.model.PlanPositions");
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
            "<column><column-name>planVersion</column-name><column-value><![CDATA[");
        sb.append(getPlanVersion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>version</column-name><column-value><![CDATA[");
        sb.append(getVersion());
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
