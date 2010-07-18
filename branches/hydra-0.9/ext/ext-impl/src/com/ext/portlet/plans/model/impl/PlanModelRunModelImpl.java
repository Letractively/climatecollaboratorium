package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanModelRun;
import com.ext.portlet.plans.model.PlanModelRunSoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <a href="PlanModelRunModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>PlanModelRun</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanModelRun
 * @see com.ext.portlet.plans.model.PlanModelRunModel
 * @see com.ext.portlet.plans.model.impl.PlanModelRunImpl
 *
 */
public class PlanModelRunModelImpl extends BaseModelImpl<PlanModelRun> {
    public static final String TABLE_NAME = "PlanModelRun";
    public static final Object[][] TABLE_COLUMNS = {
            { "id_", new Integer(Types.BIGINT) },
            

            { "planId", new Integer(Types.BIGINT) },
            

            { "scenarioId", new Integer(Types.BIGINT) },
            

            { "planVersion", new Integer(Types.BIGINT) },
            

            { "version", new Integer(Types.BIGINT) },
            

            { "created", new Integer(Types.TIMESTAMP) },
            

            { "updateAuthorId", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table PlanModelRun (id_ LONG not null primary key,planId LONG,scenarioId LONG,planVersion LONG,version LONG,created DATE null,updateAuthorId LONG)";
    public static final String TABLE_SQL_DROP = "drop table PlanModelRun";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.plans.model.PlanModelRun"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.plans.model.PlanModelRun"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.plans.model.PlanModelRun"));
    private Long _id;
    private Long _planId;
    private Long _originalPlanId;
    private Long _scenarioId;
    private Long _planVersion;
    private Long _version;
    private Date _created;
    private Long _updateAuthorId;

    public PlanModelRunModelImpl() {
    }

    public static PlanModelRun toModel(PlanModelRunSoap soapModel) {
        PlanModelRun model = new PlanModelRunImpl();

        model.setId(soapModel.getId());
        model.setPlanId(soapModel.getPlanId());
        model.setScenarioId(soapModel.getScenarioId());
        model.setPlanVersion(soapModel.getPlanVersion());
        model.setVersion(soapModel.getVersion());
        model.setCreated(soapModel.getCreated());
        model.setUpdateAuthorId(soapModel.getUpdateAuthorId());

        return model;
    }

    public static List<PlanModelRun> toModels(PlanModelRunSoap[] soapModels) {
        List<PlanModelRun> models = new ArrayList<PlanModelRun>(soapModels.length);

        for (PlanModelRunSoap soapModel : soapModels) {
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

    public Long getScenarioId() {
        return _scenarioId;
    }

    public void setScenarioId(Long scenarioId) {
        _scenarioId = scenarioId;
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

    public PlanModelRun toEscapedModel() {
        if (isEscapedModel()) {
            return (PlanModelRun) this;
        } else {
            PlanModelRun model = new PlanModelRunImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setId(getId());
            model.setPlanId(getPlanId());
            model.setScenarioId(getScenarioId());
            model.setPlanVersion(getPlanVersion());
            model.setVersion(getVersion());
            model.setCreated(getCreated());
            model.setUpdateAuthorId(getUpdateAuthorId());

            model = (PlanModelRun) Proxy.newProxyInstance(PlanModelRun.class.getClassLoader(),
                    new Class[] { PlanModelRun.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        PlanModelRunImpl clone = new PlanModelRunImpl();

        clone.setId(getId());
        clone.setPlanId(getPlanId());
        clone.setScenarioId(getScenarioId());
        clone.setPlanVersion(getPlanVersion());
        clone.setVersion(getVersion());
        clone.setCreated(getCreated());
        clone.setUpdateAuthorId(getUpdateAuthorId());

        return clone;
    }

    public int compareTo(PlanModelRun planModelRun) {
        int value = 0;

        value = getVersion().compareTo(planModelRun.getVersion());

        value = value * -1;

        if (value != 0) {
            return value;
        }

        value = DateUtil.compareTo(getCreated(), planModelRun.getCreated());

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

        PlanModelRun planModelRun = null;

        try {
            planModelRun = (PlanModelRun) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = planModelRun.getPrimaryKey();

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
        sb.append(", scenarioId=");
        sb.append(getScenarioId());
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
        sb.append("com.ext.portlet.plans.model.PlanModelRun");
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
            "<column><column-name>scenarioId</column-name><column-value><![CDATA[");
        sb.append(getScenarioId());
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
