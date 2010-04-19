package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlansFilterPosition;
import com.ext.portlet.plans.model.PlansFilterPositionSoap;
import com.ext.portlet.plans.service.persistence.PlansFilterPositionPK;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="PlansFilterPositionModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>PlansFilterPosition</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlansFilterPosition
 * @see com.ext.portlet.plans.model.PlansFilterPositionModel
 * @see com.ext.portlet.plans.model.impl.PlansFilterPositionImpl
 *
 */
public class PlansFilterPositionModelImpl extends BaseModelImpl<PlansFilterPosition> {
    public static final String TABLE_NAME = "PlansFilterPosition";
    public static final Object[][] TABLE_COLUMNS = {
            { "userId", new Integer(Types.BIGINT) },
            

            { "planTypeId", new Integer(Types.BIGINT) },
            

            { "positionId", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table PlansFilterPosition (userId LONG not null,planTypeId LONG not null,positionId LONG not null,primary key (userId, planTypeId, positionId))";
    public static final String TABLE_SQL_DROP = "drop table PlansFilterPosition";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.plans.model.PlansFilterPosition"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.plans.model.PlansFilterPosition"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.plans.model.PlansFilterPosition"));
    private Long _userId;
    private Long _planTypeId;
    private Long _positionId;

    public PlansFilterPositionModelImpl() {
    }

    public static PlansFilterPosition toModel(PlansFilterPositionSoap soapModel) {
        PlansFilterPosition model = new PlansFilterPositionImpl();

        model.setUserId(soapModel.getUserId());
        model.setPlanTypeId(soapModel.getPlanTypeId());
        model.setPositionId(soapModel.getPositionId());

        return model;
    }

    public static List<PlansFilterPosition> toModels(
        PlansFilterPositionSoap[] soapModels) {
        List<PlansFilterPosition> models = new ArrayList<PlansFilterPosition>(soapModels.length);

        for (PlansFilterPositionSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public PlansFilterPositionPK getPrimaryKey() {
        return new PlansFilterPositionPK(_userId, _planTypeId, _positionId);
    }

    public void setPrimaryKey(PlansFilterPositionPK pk) {
        setUserId(pk.userId);
        setPlanTypeId(pk.planTypeId);
        setPositionId(pk.positionId);
    }

    public Serializable getPrimaryKeyObj() {
        return new PlansFilterPositionPK(_userId, _planTypeId, _positionId);
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }

    public Long getPlanTypeId() {
        return _planTypeId;
    }

    public void setPlanTypeId(Long planTypeId) {
        _planTypeId = planTypeId;
    }

    public Long getPositionId() {
        return _positionId;
    }

    public void setPositionId(Long positionId) {
        _positionId = positionId;
    }

    public PlansFilterPosition toEscapedModel() {
        if (isEscapedModel()) {
            return (PlansFilterPosition) this;
        } else {
            PlansFilterPosition model = new PlansFilterPositionImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setUserId(getUserId());
            model.setPlanTypeId(getPlanTypeId());
            model.setPositionId(getPositionId());

            model = (PlansFilterPosition) Proxy.newProxyInstance(PlansFilterPosition.class.getClassLoader(),
                    new Class[] { PlansFilterPosition.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        PlansFilterPositionImpl clone = new PlansFilterPositionImpl();

        clone.setUserId(getUserId());
        clone.setPlanTypeId(getPlanTypeId());
        clone.setPositionId(getPositionId());

        return clone;
    }

    public int compareTo(PlansFilterPosition plansFilterPosition) {
        PlansFilterPositionPK pk = plansFilterPosition.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlansFilterPosition plansFilterPosition = null;

        try {
            plansFilterPosition = (PlansFilterPosition) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        PlansFilterPositionPK pk = plansFilterPosition.getPrimaryKey();

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

        sb.append("{userId=");
        sb.append(getUserId());
        sb.append(", planTypeId=");
        sb.append(getPlanTypeId());
        sb.append(", positionId=");
        sb.append(getPositionId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlansFilterPosition");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planTypeId</column-name><column-value><![CDATA[");
        sb.append(getPlanTypeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>positionId</column-name><column-value><![CDATA[");
        sb.append(getPositionId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
