package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanPositionItem;
import com.ext.portlet.plans.model.PlanPositionItemSoap;
import com.ext.portlet.plans.service.persistence.PlanPositionItemPK;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="PlanPositionItemModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>PlanPositionItem</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanPositionItem
 * @see com.ext.portlet.plans.model.PlanPositionItemModel
 * @see com.ext.portlet.plans.model.impl.PlanPositionItemImpl
 *
 */
public class PlanPositionItemModelImpl extends BaseModelImpl<PlanPositionItem> {
    public static final String TABLE_NAME = "PlanPositionItem";
    public static final Object[][] TABLE_COLUMNS = {
            { "planPositionsId", new Integer(Types.BIGINT) },
            

            { "positionId", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table PlanPositionItem (planPositionsId LONG not null,positionId LONG not null,primary key (planPositionsId, positionId))";
    public static final String TABLE_SQL_DROP = "drop table PlanPositionItem";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.plans.model.PlanPositionItem"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.plans.model.PlanPositionItem"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.plans.model.PlanPositionItem"));
    private Long _planPositionsId;
    private Long _positionId;

    public PlanPositionItemModelImpl() {
    }

    public static PlanPositionItem toModel(PlanPositionItemSoap soapModel) {
        PlanPositionItem model = new PlanPositionItemImpl();

        model.setPlanPositionsId(soapModel.getPlanPositionsId());
        model.setPositionId(soapModel.getPositionId());

        return model;
    }

    public static List<PlanPositionItem> toModels(
        PlanPositionItemSoap[] soapModels) {
        List<PlanPositionItem> models = new ArrayList<PlanPositionItem>(soapModels.length);

        for (PlanPositionItemSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public PlanPositionItemPK getPrimaryKey() {
        return new PlanPositionItemPK(_planPositionsId, _positionId);
    }

    public void setPrimaryKey(PlanPositionItemPK pk) {
        setPlanPositionsId(pk.planPositionsId);
        setPositionId(pk.positionId);
    }

    public Serializable getPrimaryKeyObj() {
        return new PlanPositionItemPK(_planPositionsId, _positionId);
    }

    public Long getPlanPositionsId() {
        return _planPositionsId;
    }

    public void setPlanPositionsId(Long planPositionsId) {
        _planPositionsId = planPositionsId;
    }

    public Long getPositionId() {
        return _positionId;
    }

    public void setPositionId(Long positionId) {
        _positionId = positionId;
    }

    public PlanPositionItem toEscapedModel() {
        if (isEscapedModel()) {
            return (PlanPositionItem) this;
        } else {
            PlanPositionItem model = new PlanPositionItemImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setPlanPositionsId(getPlanPositionsId());
            model.setPositionId(getPositionId());

            model = (PlanPositionItem) Proxy.newProxyInstance(PlanPositionItem.class.getClassLoader(),
                    new Class[] { PlanPositionItem.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        PlanPositionItemImpl clone = new PlanPositionItemImpl();

        clone.setPlanPositionsId(getPlanPositionsId());
        clone.setPositionId(getPositionId());

        return clone;
    }

    public int compareTo(PlanPositionItem planPositionItem) {
        PlanPositionItemPK pk = planPositionItem.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlanPositionItem planPositionItem = null;

        try {
            planPositionItem = (PlanPositionItem) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        PlanPositionItemPK pk = planPositionItem.getPrimaryKey();

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

        sb.append("{planPositionsId=");
        sb.append(getPlanPositionsId());
        sb.append(", positionId=");
        sb.append(getPositionId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlanPositionItem");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>planPositionsId</column-name><column-value><![CDATA[");
        sb.append(getPlanPositionsId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>positionId</column-name><column-value><![CDATA[");
        sb.append(getPositionId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
