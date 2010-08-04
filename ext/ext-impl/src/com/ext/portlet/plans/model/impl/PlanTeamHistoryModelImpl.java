package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanTeamHistory;
import com.ext.portlet.plans.model.PlanTeamHistorySoap;

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
 * <a href="PlanTeamHistoryModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>PlanTeamHistory</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanTeamHistory
 * @see com.ext.portlet.plans.model.PlanTeamHistoryModel
 * @see com.ext.portlet.plans.model.impl.PlanTeamHistoryImpl
 *
 */
public class PlanTeamHistoryModelImpl extends BaseModelImpl<PlanTeamHistory> {
    public static final String TABLE_NAME = "PlanTeamHistory";
    public static final Object[][] TABLE_COLUMNS = {
            { "id_", new Integer(Types.BIGINT) },
            

            { "planId", new Integer(Types.BIGINT) },
            

            { "userId", new Integer(Types.BIGINT) },
            

            { "action", new Integer(Types.VARCHAR) },
            

            { "payload", new Integer(Types.VARCHAR) },
            

            { "created", new Integer(Types.TIMESTAMP) },
            

            { "updateAuthorId", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table PlanTeamHistory (id_ LONG not null primary key,planId LONG,userId LONG,action VARCHAR(75) null,payload VARCHAR(75) null,created DATE null,updateAuthorId LONG)";
    public static final String TABLE_SQL_DROP = "drop table PlanTeamHistory";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.plans.model.PlanTeamHistory"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.plans.model.PlanTeamHistory"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.plans.model.PlanTeamHistory"));
    private Long _id;
    private Long _planId;
    private Long _originalPlanId;
    private Long _userId;
    private Long _originalUserId;
    private String _action;
    private String _payload;
    private Date _created;
    private Long _updateAuthorId;

    public PlanTeamHistoryModelImpl() {
    }

    public static PlanTeamHistory toModel(PlanTeamHistorySoap soapModel) {
        PlanTeamHistory model = new PlanTeamHistoryImpl();

        model.setId(soapModel.getId());
        model.setPlanId(soapModel.getPlanId());
        model.setUserId(soapModel.getUserId());
        model.setAction(soapModel.getAction());
        model.setPayload(soapModel.getPayload());
        model.setCreated(soapModel.getCreated());
        model.setUpdateAuthorId(soapModel.getUpdateAuthorId());

        return model;
    }

    public static List<PlanTeamHistory> toModels(
        PlanTeamHistorySoap[] soapModels) {
        List<PlanTeamHistory> models = new ArrayList<PlanTeamHistory>(soapModels.length);

        for (PlanTeamHistorySoap soapModel : soapModels) {
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

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;

        if (_originalUserId == null) {
            _originalUserId = userId;
        }
    }

    public Long getOriginalUserId() {
        return _originalUserId;
    }

    public String getAction() {
        return GetterUtil.getString(_action);
    }

    public void setAction(String action) {
        _action = action;
    }

    public String getPayload() {
        return GetterUtil.getString(_payload);
    }

    public void setPayload(String payload) {
        _payload = payload;
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

    public PlanTeamHistory toEscapedModel() {
        if (isEscapedModel()) {
            return (PlanTeamHistory) this;
        } else {
            PlanTeamHistory model = new PlanTeamHistoryImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setId(getId());
            model.setPlanId(getPlanId());
            model.setUserId(getUserId());
            model.setAction(HtmlUtil.escape(getAction()));
            model.setPayload(HtmlUtil.escape(getPayload()));
            model.setCreated(getCreated());
            model.setUpdateAuthorId(getUpdateAuthorId());

            model = (PlanTeamHistory) Proxy.newProxyInstance(PlanTeamHistory.class.getClassLoader(),
                    new Class[] { PlanTeamHistory.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        PlanTeamHistoryImpl clone = new PlanTeamHistoryImpl();

        clone.setId(getId());
        clone.setPlanId(getPlanId());
        clone.setUserId(getUserId());
        clone.setAction(getAction());
        clone.setPayload(getPayload());
        clone.setCreated(getCreated());
        clone.setUpdateAuthorId(getUpdateAuthorId());

        return clone;
    }

    public int compareTo(PlanTeamHistory planTeamHistory) {
        int value = 0;

        value = DateUtil.compareTo(getCreated(), planTeamHistory.getCreated());

        if (value != 0) {
            return value;
        }

        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlanTeamHistory planTeamHistory = null;

        try {
            planTeamHistory = (PlanTeamHistory) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = planTeamHistory.getPrimaryKey();

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
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", action=");
        sb.append(getAction());
        sb.append(", payload=");
        sb.append(getPayload());
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
        sb.append("com.ext.portlet.plans.model.PlanTeamHistory");
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
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>action</column-name><column-value><![CDATA[");
        sb.append(getAction());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>payload</column-name><column-value><![CDATA[");
        sb.append(getPayload());
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
