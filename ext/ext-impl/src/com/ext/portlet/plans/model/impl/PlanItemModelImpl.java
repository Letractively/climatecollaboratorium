package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanItemSoap;

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
 * <a href="PlanItemModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>PlanItem</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanItem
 * @see com.ext.portlet.plans.model.PlanItemModel
 * @see com.ext.portlet.plans.model.impl.PlanItemImpl
 *
 */
public class PlanItemModelImpl extends BaseModelImpl<PlanItem> {
    public static final String TABLE_NAME = "PlanItem";
    public static final Object[][] TABLE_COLUMNS = {
            { "id_", new Integer(Types.BIGINT) },
            

            { "planId", new Integer(Types.BIGINT) },
            

            { "state", new Integer(Types.VARCHAR) },
            

            { "updated", new Integer(Types.TIMESTAMP) },
            

            { "updateAuthorId", new Integer(Types.BIGINT) },
            

            { "updateType", new Integer(Types.VARCHAR) },
            

            { "version", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table PlanItem (id_ LONG not null primary key,planId LONG,state VARCHAR(75) null,updated DATE null,updateAuthorId LONG,updateType VARCHAR(75) null,version LONG)";
    public static final String TABLE_SQL_DROP = "drop table PlanItem";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.plans.model.PlanItem"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.plans.model.PlanItem"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.plans.model.PlanItem"));
    private Long _id;
    private Long _planId;
    private Long _originalPlanId;
    private String _state;
    private Date _updated;
    private Long _updateAuthorId;
    private String _updateType;
    private Long _version;

    public PlanItemModelImpl() {
    }

    public static PlanItem toModel(PlanItemSoap soapModel) {
        PlanItem model = new PlanItemImpl();

        model.setId(soapModel.getId());
        model.setPlanId(soapModel.getPlanId());
        model.setState(soapModel.getState());
        model.setUpdated(soapModel.getUpdated());
        model.setUpdateAuthorId(soapModel.getUpdateAuthorId());
        model.setUpdateType(soapModel.getUpdateType());
        model.setVersion(soapModel.getVersion());

        return model;
    }

    public static List<PlanItem> toModels(PlanItemSoap[] soapModels) {
        List<PlanItem> models = new ArrayList<PlanItem>(soapModels.length);

        for (PlanItemSoap soapModel : soapModels) {
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

    public String getState() {
        return GetterUtil.getString(_state);
    }

    public void setState(String state) {
        _state = state;
    }

    public Date getUpdated() {
        return _updated;
    }

    public void setUpdated(Date updated) {
        _updated = updated;
    }

    public Long getUpdateAuthorId() {
        return _updateAuthorId;
    }

    public void setUpdateAuthorId(Long updateAuthorId) {
        _updateAuthorId = updateAuthorId;
    }

    public String getUpdateType() {
        return GetterUtil.getString(_updateType);
    }

    public void setUpdateType(String updateType) {
        _updateType = updateType;
    }

    public Long getVersion() {
        return _version;
    }

    public void setVersion(Long version) {
        _version = version;
    }

    public PlanItem toEscapedModel() {
        if (isEscapedModel()) {
            return (PlanItem) this;
        } else {
            PlanItem model = new PlanItemImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setId(getId());
            model.setPlanId(getPlanId());
            model.setState(HtmlUtil.escape(getState()));
            model.setUpdated(getUpdated());
            model.setUpdateAuthorId(getUpdateAuthorId());
            model.setUpdateType(HtmlUtil.escape(getUpdateType()));
            model.setVersion(getVersion());

            model = (PlanItem) Proxy.newProxyInstance(PlanItem.class.getClassLoader(),
                    new Class[] { PlanItem.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        PlanItemImpl clone = new PlanItemImpl();

        clone.setId(getId());
        clone.setPlanId(getPlanId());
        clone.setState(getState());
        clone.setUpdated(getUpdated());
        clone.setUpdateAuthorId(getUpdateAuthorId());
        clone.setUpdateType(getUpdateType());
        clone.setVersion(getVersion());

        return clone;
    }

    public int compareTo(PlanItem planItem) {
        int value = 0;

        value = getId().compareTo(planItem.getId());

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

        PlanItem planItem = null;

        try {
            planItem = (PlanItem) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = planItem.getPrimaryKey();

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
        sb.append(", state=");
        sb.append(getState());
        sb.append(", updated=");
        sb.append(getUpdated());
        sb.append(", updateAuthorId=");
        sb.append(getUpdateAuthorId());
        sb.append(", updateType=");
        sb.append(getUpdateType());
        sb.append(", version=");
        sb.append(getVersion());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlanItem");
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
            "<column><column-name>state</column-name><column-value><![CDATA[");
        sb.append(getState());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updated</column-name><column-value><![CDATA[");
        sb.append(getUpdated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updateAuthorId</column-name><column-value><![CDATA[");
        sb.append(getUpdateAuthorId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updateType</column-name><column-value><![CDATA[");
        sb.append(getUpdateType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>version</column-name><column-value><![CDATA[");
        sb.append(getVersion());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
