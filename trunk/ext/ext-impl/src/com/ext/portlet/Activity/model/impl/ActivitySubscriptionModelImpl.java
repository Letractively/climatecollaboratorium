package com.ext.portlet.Activity.model.impl;

import com.ext.portlet.Activity.model.ActivitySubscription;
import com.ext.portlet.Activity.model.ActivitySubscriptionSoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <a href="ActivitySubscriptionModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>ActivitySubscription</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.Activity.model.ActivitySubscription
 * @see com.ext.portlet.Activity.model.ActivitySubscriptionModel
 * @see com.ext.portlet.Activity.model.impl.ActivitySubscriptionImpl
 *
 */
public class ActivitySubscriptionModelImpl extends BaseModelImpl<ActivitySubscription> {
    public static final String TABLE_NAME = "ActivitySubscription";
    public static final Object[][] TABLE_COLUMNS = {
            { "pk", new Integer(Types.BIGINT) },
            

            { "classNameId", new Integer(Types.BIGINT) },
            

            { "classPK", new Integer(Types.BIGINT) },
            

            { "type_", new Integer(Types.INTEGER) },
            

            { "extraData", new Integer(Types.VARCHAR) },
            

            { "receiverId", new Integer(Types.BIGINT) },
            

            { "createDate", new Integer(Types.TIMESTAMP) },
            

            { "modifiedDate", new Integer(Types.TIMESTAMP) }
        };
    public static final String TABLE_SQL_CREATE = "create table ActivitySubscription (pk LONG not null primary key,classNameId LONG,classPK LONG,type_ INTEGER,extraData VARCHAR(75) null,receiverId LONG,createDate DATE null,modifiedDate DATE null)";
    public static final String TABLE_SQL_DROP = "drop table ActivitySubscription";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.Activity.model.ActivitySubscription"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.Activity.model.ActivitySubscription"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.Activity.model.ActivitySubscription"));
    private Long _pk;
    private Long _classNameId;
    private Long _classPK;
    private Integer _type;
    private String _extraData;
    private Long _receiverId;
    private Date _createDate;
    private Date _modifiedDate;

    public ActivitySubscriptionModelImpl() {
    }

    public static ActivitySubscription toModel(
        ActivitySubscriptionSoap soapModel) {
        ActivitySubscription model = new ActivitySubscriptionImpl();

        model.setPk(soapModel.getPk());
        model.setClassNameId(soapModel.getClassNameId());
        model.setClassPK(soapModel.getClassPK());
        model.setType(soapModel.getType());
        model.setExtraData(soapModel.getExtraData());
        model.setReceiverId(soapModel.getReceiverId());
        model.setCreateDate(soapModel.getCreateDate());
        model.setModifiedDate(soapModel.getModifiedDate());

        return model;
    }

    public static List<ActivitySubscription> toModels(
        ActivitySubscriptionSoap[] soapModels) {
        List<ActivitySubscription> models = new ArrayList<ActivitySubscription>(soapModels.length);

        for (ActivitySubscriptionSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _pk;
    }

    public void setPrimaryKey(Long pk) {
        setPk(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _pk;
    }

    public Long getPk() {
        return _pk;
    }

    public void setPk(Long pk) {
        _pk = pk;
    }

    public String getClassName() {
        if (getClassNameId() <= 0) {
            return StringPool.BLANK;
        }

        return PortalUtil.getClassName(getClassNameId());
    }

    public Long getClassNameId() {
        return _classNameId;
    }

    public void setClassNameId(Long classNameId) {
        _classNameId = classNameId;
    }

    public Long getClassPK() {
        return _classPK;
    }

    public void setClassPK(Long classPK) {
        _classPK = classPK;
    }

    public Integer getType() {
        return _type;
    }

    public void setType(Integer type) {
        _type = type;
    }

    public String getExtraData() {
        return GetterUtil.getString(_extraData);
    }

    public void setExtraData(String extraData) {
        _extraData = extraData;
    }

    public Long getReceiverId() {
        return _receiverId;
    }

    public void setReceiverId(Long receiverId) {
        _receiverId = receiverId;
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

    public ActivitySubscription toEscapedModel() {
        if (isEscapedModel()) {
            return (ActivitySubscription) this;
        } else {
            ActivitySubscription model = new ActivitySubscriptionImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setPk(getPk());
            model.setClassNameId(getClassNameId());
            model.setClassPK(getClassPK());
            model.setType(getType());
            model.setExtraData(HtmlUtil.escape(getExtraData()));
            model.setReceiverId(getReceiverId());
            model.setCreateDate(getCreateDate());
            model.setModifiedDate(getModifiedDate());

            model = (ActivitySubscription) Proxy.newProxyInstance(ActivitySubscription.class.getClassLoader(),
                    new Class[] { ActivitySubscription.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        ActivitySubscriptionImpl clone = new ActivitySubscriptionImpl();

        clone.setPk(getPk());
        clone.setClassNameId(getClassNameId());
        clone.setClassPK(getClassPK());
        clone.setType(getType());
        clone.setExtraData(getExtraData());
        clone.setReceiverId(getReceiverId());
        clone.setCreateDate(getCreateDate());
        clone.setModifiedDate(getModifiedDate());

        return clone;
    }

    public int compareTo(ActivitySubscription activitySubscription) {
        Long pk = activitySubscription.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        ActivitySubscription activitySubscription = null;

        try {
            activitySubscription = (ActivitySubscription) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = activitySubscription.getPrimaryKey();

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

        sb.append("{pk=");
        sb.append(getPk());
        sb.append(", classNameId=");
        sb.append(getClassNameId());
        sb.append(", classPK=");
        sb.append(getClassPK());
        sb.append(", type=");
        sb.append(getType());
        sb.append(", extraData=");
        sb.append(getExtraData());
        sb.append(", receiverId=");
        sb.append(getReceiverId());
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
        sb.append("com.ext.portlet.Activity.model.ActivitySubscription");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>pk</column-name><column-value><![CDATA[");
        sb.append(getPk());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>classNameId</column-name><column-value><![CDATA[");
        sb.append(getClassNameId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>classPK</column-name><column-value><![CDATA[");
        sb.append(getClassPK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>type</column-name><column-value><![CDATA[");
        sb.append(getType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>extraData</column-name><column-value><![CDATA[");
        sb.append(getExtraData());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>receiverId</column-name><column-value><![CDATA[");
        sb.append(getReceiverId());
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
