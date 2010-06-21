/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanMeta;
import com.ext.portlet.plans.model.PlanMetaSoap;

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
 * <a href="PlanMetaModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>PlanMeta</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanMeta
 * @see com.ext.portlet.plans.model.PlanMetaModel
 * @see com.ext.portlet.plans.model.impl.PlanMetaImpl
 *
 */
public class PlanMetaModelImpl extends BaseModelImpl<PlanMeta> {
    public static final String TABLE_NAME = "PlanMeta";
    public static final Object[][] TABLE_COLUMNS = {
            { "id_", new Integer(Types.BIGINT) },
            

            { "planId", new Integer(Types.BIGINT) },
            

            { "planTypeId", new Integer(Types.BIGINT) },
            

            { "planCreated", new Integer(Types.BIGINT) },
            

            { "authorId", new Integer(Types.BIGINT) },
            

            { "planGroupId", new Integer(Types.BIGINT) },
            

            { "mbCategoryId", new Integer(Types.BIGINT) },
            

            { "version", new Integer(Types.BIGINT) },
            

            { "planVersion", new Integer(Types.BIGINT) },
            

            { "created", new Integer(Types.TIMESTAMP) },
            

            { "updateAuthorId", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table PlanMeta (id_ LONG not null primary key,planId LONG,planTypeId LONG,planCreated LONG,authorId LONG,planGroupId LONG,mbCategoryId LONG,version LONG,planVersion LONG,created DATE null,updateAuthorId LONG)";
    public static final String TABLE_SQL_DROP = "drop table PlanMeta";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.plans.model.PlanMeta"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.plans.model.PlanMeta"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.plans.model.PlanMeta"));
    private Long _id;
    private Long _planId;
    private Long _originalPlanId;
    private Long _planTypeId;
    private Long _planCreated;
    private Long _authorId;
    private Long _planGroupId;
    private Long _mbCategoryId;
    private Long _version;
    private Long _planVersion;
    private Date _created;
    private Long _updateAuthorId;

    public PlanMetaModelImpl() {
    }

    public static PlanMeta toModel(PlanMetaSoap soapModel) {
        PlanMeta model = new PlanMetaImpl();

        model.setId(soapModel.getId());
        model.setPlanId(soapModel.getPlanId());
        model.setPlanTypeId(soapModel.getPlanTypeId());
        model.setPlanCreated(soapModel.getPlanCreated());
        model.setAuthorId(soapModel.getAuthorId());
        model.setPlanGroupId(soapModel.getPlanGroupId());
        model.setMbCategoryId(soapModel.getMbCategoryId());
        model.setVersion(soapModel.getVersion());
        model.setPlanVersion(soapModel.getPlanVersion());
        model.setCreated(soapModel.getCreated());
        model.setUpdateAuthorId(soapModel.getUpdateAuthorId());

        return model;
    }

    public static List<PlanMeta> toModels(PlanMetaSoap[] soapModels) {
        List<PlanMeta> models = new ArrayList<PlanMeta>(soapModels.length);

        for (PlanMetaSoap soapModel : soapModels) {
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

    public Long getPlanTypeId() {
        return _planTypeId;
    }

    public void setPlanTypeId(Long planTypeId) {
        _planTypeId = planTypeId;
    }

    public Long getPlanCreated() {
        return _planCreated;
    }

    public void setPlanCreated(Long planCreated) {
        _planCreated = planCreated;
    }

    public Long getAuthorId() {
        return _authorId;
    }

    public void setAuthorId(Long authorId) {
        _authorId = authorId;
    }

    public Long getPlanGroupId() {
        return _planGroupId;
    }

    public void setPlanGroupId(Long planGroupId) {
        _planGroupId = planGroupId;
    }

    public Long getMbCategoryId() {
        return _mbCategoryId;
    }

    public void setMbCategoryId(Long mbCategoryId) {
        _mbCategoryId = mbCategoryId;
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

    public PlanMeta toEscapedModel() {
        if (isEscapedModel()) {
            return (PlanMeta) this;
        } else {
            PlanMeta model = new PlanMetaImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setId(getId());
            model.setPlanId(getPlanId());
            model.setPlanTypeId(getPlanTypeId());
            model.setPlanCreated(getPlanCreated());
            model.setAuthorId(getAuthorId());
            model.setPlanGroupId(getPlanGroupId());
            model.setMbCategoryId(getMbCategoryId());
            model.setVersion(getVersion());
            model.setPlanVersion(getPlanVersion());
            model.setCreated(getCreated());
            model.setUpdateAuthorId(getUpdateAuthorId());

            model = (PlanMeta) Proxy.newProxyInstance(PlanMeta.class.getClassLoader(),
                    new Class[] { PlanMeta.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        PlanMetaImpl clone = new PlanMetaImpl();

        clone.setId(getId());
        clone.setPlanId(getPlanId());
        clone.setPlanTypeId(getPlanTypeId());
        clone.setPlanCreated(getPlanCreated());
        clone.setAuthorId(getAuthorId());
        clone.setPlanGroupId(getPlanGroupId());
        clone.setMbCategoryId(getMbCategoryId());
        clone.setVersion(getVersion());
        clone.setPlanVersion(getPlanVersion());
        clone.setCreated(getCreated());
        clone.setUpdateAuthorId(getUpdateAuthorId());

        return clone;
    }

    public int compareTo(PlanMeta planMeta) {
        int value = 0;

        value = getVersion().compareTo(planMeta.getVersion());

        value = value * -1;

        if (value != 0) {
            return value;
        }

        value = DateUtil.compareTo(getCreated(), planMeta.getCreated());

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

        PlanMeta planMeta = null;

        try {
            planMeta = (PlanMeta) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = planMeta.getPrimaryKey();

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
        sb.append(", planTypeId=");
        sb.append(getPlanTypeId());
        sb.append(", planCreated=");
        sb.append(getPlanCreated());
        sb.append(", authorId=");
        sb.append(getAuthorId());
        sb.append(", planGroupId=");
        sb.append(getPlanGroupId());
        sb.append(", mbCategoryId=");
        sb.append(getMbCategoryId());
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
        sb.append("com.ext.portlet.plans.model.PlanMeta");
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
            "<column><column-name>planTypeId</column-name><column-value><![CDATA[");
        sb.append(getPlanTypeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planCreated</column-name><column-value><![CDATA[");
        sb.append(getPlanCreated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>authorId</column-name><column-value><![CDATA[");
        sb.append(getAuthorId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planGroupId</column-name><column-value><![CDATA[");
        sb.append(getPlanGroupId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>mbCategoryId</column-name><column-value><![CDATA[");
        sb.append(getMbCategoryId());
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
