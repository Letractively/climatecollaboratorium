package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanPosition;
import com.ext.portlet.plans.model.PlanPositionSoap;
import com.ext.portlet.plans.service.persistence.PlanPositionPK;

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
 * <a href="PlanPositionModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>PlanPosition</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanPosition
 * @see com.ext.portlet.plans.model.PlanPositionModel
 * @see com.ext.portlet.plans.model.impl.PlanPositionImpl
 *
 */
public class PlanPositionModelImpl extends BaseModelImpl<PlanPosition> {
    public static final String TABLE_NAME = "PlanPosition";
    public static final Object[][] TABLE_COLUMNS = {
            { "planId", new Integer(Types.BIGINT) },
            

            { "positionId", new Integer(Types.BIGINT) },
            

            { "userId", new Integer(Types.BIGINT) },
            

            { "userName", new Integer(Types.VARCHAR) },
            

            { "createDate", new Integer(Types.TIMESTAMP) },
            

            { "modifiedDate", new Integer(Types.TIMESTAMP) }
        };
    public static final String TABLE_SQL_CREATE = "create table PlanPosition (planId LONG not null,positionId LONG not null,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,primary key (planId, positionId))";
    public static final String TABLE_SQL_DROP = "drop table PlanPosition";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.plans.model.PlanPosition"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.plans.model.PlanPosition"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.plans.model.PlanPosition"));
    private Long _planId;
    private Long _positionId;
    private Long _userId;
    private String _userName;
    private Date _createDate;
    private Date _modifiedDate;

    public PlanPositionModelImpl() {
    }

    public static PlanPosition toModel(PlanPositionSoap soapModel) {
        PlanPosition model = new PlanPositionImpl();

        model.setPlanId(soapModel.getPlanId());
        model.setPositionId(soapModel.getPositionId());
        model.setUserId(soapModel.getUserId());
        model.setUserName(soapModel.getUserName());
        model.setCreateDate(soapModel.getCreateDate());
        model.setModifiedDate(soapModel.getModifiedDate());

        return model;
    }

    public static List<PlanPosition> toModels(PlanPositionSoap[] soapModels) {
        List<PlanPosition> models = new ArrayList<PlanPosition>(soapModels.length);

        for (PlanPositionSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public PlanPositionPK getPrimaryKey() {
        return new PlanPositionPK(_planId, _positionId);
    }

    public void setPrimaryKey(PlanPositionPK pk) {
        setPlanId(pk.planId);
        setPositionId(pk.positionId);
    }

    public Serializable getPrimaryKeyObj() {
        return new PlanPositionPK(_planId, _positionId);
    }

    public Long getPlanId() {
        return _planId;
    }

    public void setPlanId(Long planId) {
        _planId = planId;
    }

    public Long getPositionId() {
        return _positionId;
    }

    public void setPositionId(Long positionId) {
        _positionId = positionId;
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }

    public String getUserName() {
        return GetterUtil.getString(_userName);
    }

    public void setUserName(String userName) {
        _userName = userName;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public PlanPosition toEscapedModel() {
        if (isEscapedModel()) {
            return (PlanPosition) this;
        } else {
            PlanPosition model = new PlanPositionImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setPlanId(getPlanId());
            model.setPositionId(getPositionId());
            model.setUserId(getUserId());
            model.setUserName(HtmlUtil.escape(getUserName()));
            model.setCreateDate(getCreateDate());
            model.setModifiedDate(getModifiedDate());

            model = (PlanPosition) Proxy.newProxyInstance(PlanPosition.class.getClassLoader(),
                    new Class[] { PlanPosition.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        PlanPositionImpl clone = new PlanPositionImpl();

        clone.setPlanId(getPlanId());
        clone.setPositionId(getPositionId());
        clone.setUserId(getUserId());
        clone.setUserName(getUserName());
        clone.setCreateDate(getCreateDate());
        clone.setModifiedDate(getModifiedDate());

        return clone;
    }

    public int compareTo(PlanPosition planPosition) {
        int value = 0;

        value = getPlanId().compareTo(planPosition.getPlanId());

        if (value != 0) {
            return value;
        }

        value = getPositionId().compareTo(planPosition.getPositionId());

        if (value != 0) {
            return value;
        }

        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlanPosition planPosition = null;

        try {
            planPosition = (PlanPosition) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        PlanPositionPK pk = planPosition.getPrimaryKey();

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

        sb.append("{planId=");
        sb.append(getPlanId());
        sb.append(", positionId=");
        sb.append(getPositionId());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", userName=");
        sb.append(getUserName());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlanPosition");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>planId</column-name><column-value><![CDATA[");
        sb.append(getPlanId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>positionId</column-name><column-value><![CDATA[");
        sb.append(getPositionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userName</column-name><column-value><![CDATA[");
        sb.append(getUserName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
