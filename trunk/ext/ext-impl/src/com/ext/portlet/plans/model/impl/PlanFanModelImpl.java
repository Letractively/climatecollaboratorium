package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanFan;
import com.ext.portlet.plans.model.PlanFanSoap;

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
 * <a href="PlanFanModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>PlanFan</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanFan
 * @see com.ext.portlet.plans.model.PlanFanModel
 * @see com.ext.portlet.plans.model.impl.PlanFanImpl
 *
 */
public class PlanFanModelImpl extends BaseModelImpl<PlanFan> {
    public static final String TABLE_NAME = "PlanFan";
    public static final Object[][] TABLE_COLUMNS = {
            { "id_", new Integer(Types.BIGINT) },
            

            { "userId", new Integer(Types.BIGINT) },
            

            { "planId", new Integer(Types.BIGINT) },
            

            { "created", new Integer(Types.TIMESTAMP) },
            

            { "deleted", new Integer(Types.TIMESTAMP) }
        };
    public static final String TABLE_SQL_CREATE = "create table PlanFan (id_ LONG not null primary key,userId LONG,planId LONG,created DATE null,deleted DATE null)";
    public static final String TABLE_SQL_DROP = "drop table PlanFan";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.plans.model.PlanFan"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.plans.model.PlanFan"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.plans.model.PlanFan"));
    private Long _id;
    private Long _userId;
    private Long _planId;
    private Date _created;
    private Date _deleted;

    public PlanFanModelImpl() {
    }

    public static PlanFan toModel(PlanFanSoap soapModel) {
        PlanFan model = new PlanFanImpl();

        model.setId(soapModel.getId());
        model.setUserId(soapModel.getUserId());
        model.setPlanId(soapModel.getPlanId());
        model.setCreated(soapModel.getCreated());
        model.setDeleted(soapModel.getDeleted());

        return model;
    }

    public static List<PlanFan> toModels(PlanFanSoap[] soapModels) {
        List<PlanFan> models = new ArrayList<PlanFan>(soapModels.length);

        for (PlanFanSoap soapModel : soapModels) {
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

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }

    public Long getPlanId() {
        return _planId;
    }

    public void setPlanId(Long planId) {
        _planId = planId;
    }

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        _created = created;
    }

    public Date getDeleted() {
        return _deleted;
    }

    public void setDeleted(Date deleted) {
        _deleted = deleted;
    }

    public PlanFan toEscapedModel() {
        if (isEscapedModel()) {
            return (PlanFan) this;
        } else {
            PlanFan model = new PlanFanImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setId(getId());
            model.setUserId(getUserId());
            model.setPlanId(getPlanId());
            model.setCreated(getCreated());
            model.setDeleted(getDeleted());

            model = (PlanFan) Proxy.newProxyInstance(PlanFan.class.getClassLoader(),
                    new Class[] { PlanFan.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        PlanFanImpl clone = new PlanFanImpl();

        clone.setId(getId());
        clone.setUserId(getUserId());
        clone.setPlanId(getPlanId());
        clone.setCreated(getCreated());
        clone.setDeleted(getDeleted());

        return clone;
    }

    public int compareTo(PlanFan planFan) {
        Long pk = planFan.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlanFan planFan = null;

        try {
            planFan = (PlanFan) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = planFan.getPrimaryKey();

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
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", planId=");
        sb.append(getPlanId());
        sb.append(", created=");
        sb.append(getCreated());
        sb.append(", deleted=");
        sb.append(getDeleted());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlanFan");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planId</column-name><column-value><![CDATA[");
        sb.append(getPlanId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>created</column-name><column-value><![CDATA[");
        sb.append(getCreated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deleted</column-name><column-value><![CDATA[");
        sb.append(getDeleted());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
