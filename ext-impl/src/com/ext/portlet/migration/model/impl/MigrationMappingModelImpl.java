/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.migration.model.impl;

import com.ext.portlet.migration.model.MigrationMapping;
import com.ext.portlet.migration.model.MigrationMappingSoap;
import com.ext.portlet.migration.service.persistence.MigrationMappingPK;

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
 * <a href="MigrationMappingModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>MigrationMapping</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.migration.model.MigrationMapping
 * @see com.ext.portlet.migration.model.MigrationMappingModel
 * @see com.ext.portlet.migration.model.impl.MigrationMappingImpl
 *
 */
public class MigrationMappingModelImpl extends BaseModelImpl<MigrationMapping> {
    public static final String TABLE_NAME = "MigrationMapping";
    public static final Object[][] TABLE_COLUMNS = {
            { "entityName", new Integer(Types.VARCHAR) },
            

            { "oldId", new Integer(Types.VARCHAR) },
            

            { "newId", new Integer(Types.BIGINT) },
            

            { "userId", new Integer(Types.BIGINT) },
            

            { "createDate", new Integer(Types.TIMESTAMP) },
            

            { "modifiedDate", new Integer(Types.TIMESTAMP) }
        };
    public static final String TABLE_SQL_CREATE = "create table MigrationMapping (entityName VARCHAR(75) not null,oldId VARCHAR(75) not null,newId LONG,userId LONG,createDate DATE null,modifiedDate DATE null,primary key (entityName, oldId))";
    public static final String TABLE_SQL_DROP = "drop table MigrationMapping";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.migration.model.MigrationMapping"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.migration.model.MigrationMapping"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.migration.model.MigrationMapping"));
    private String _entityName;
    private String _oldId;
    private String _originalOldId;
    private Long _newId;
    private Long _originalNewId;
    private Long _userId;
    private Date _createDate;
    private Date _modifiedDate;

    public MigrationMappingModelImpl() {
    }

    public static MigrationMapping toModel(MigrationMappingSoap soapModel) {
        MigrationMapping model = new MigrationMappingImpl();

        model.setEntityName(soapModel.getEntityName());
        model.setOldId(soapModel.getOldId());
        model.setNewId(soapModel.getNewId());
        model.setUserId(soapModel.getUserId());
        model.setCreateDate(soapModel.getCreateDate());
        model.setModifiedDate(soapModel.getModifiedDate());

        return model;
    }

    public static List<MigrationMapping> toModels(
        MigrationMappingSoap[] soapModels) {
        List<MigrationMapping> models = new ArrayList<MigrationMapping>(soapModels.length);

        for (MigrationMappingSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public MigrationMappingPK getPrimaryKey() {
        return new MigrationMappingPK(_entityName, _oldId);
    }

    public void setPrimaryKey(MigrationMappingPK pk) {
        setEntityName(pk.entityName);
        setOldId(pk.oldId);
    }

    public Serializable getPrimaryKeyObj() {
        return new MigrationMappingPK(_entityName, _oldId);
    }

    public String getEntityName() {
        return GetterUtil.getString(_entityName);
    }

    public void setEntityName(String entityName) {
        _entityName = entityName;
    }

    public String getOldId() {
        return GetterUtil.getString(_oldId);
    }

    public void setOldId(String oldId) {
        _oldId = oldId;

        if (_originalOldId == null) {
            _originalOldId = oldId;
        }
    }

    public String getOriginalOldId() {
        return GetterUtil.getString(_originalOldId);
    }

    public Long getNewId() {
        return _newId;
    }

    public void setNewId(Long newId) {
        _newId = newId;

        if (_originalNewId == null) {
            _originalNewId = newId;
        }
    }

    public Long getOriginalNewId() {
        return _originalNewId;
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
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

    public MigrationMapping toEscapedModel() {
        if (isEscapedModel()) {
            return (MigrationMapping) this;
        } else {
            MigrationMapping model = new MigrationMappingImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setEntityName(HtmlUtil.escape(getEntityName()));
            model.setOldId(HtmlUtil.escape(getOldId()));
            model.setNewId(getNewId());
            model.setUserId(getUserId());
            model.setCreateDate(getCreateDate());
            model.setModifiedDate(getModifiedDate());

            model = (MigrationMapping) Proxy.newProxyInstance(MigrationMapping.class.getClassLoader(),
                    new Class[] { MigrationMapping.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        MigrationMappingImpl clone = new MigrationMappingImpl();

        clone.setEntityName(getEntityName());
        clone.setOldId(getOldId());
        clone.setNewId(getNewId());
        clone.setUserId(getUserId());
        clone.setCreateDate(getCreateDate());
        clone.setModifiedDate(getModifiedDate());

        return clone;
    }

    public int compareTo(MigrationMapping migrationMapping) {
        MigrationMappingPK pk = migrationMapping.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        MigrationMapping migrationMapping = null;

        try {
            migrationMapping = (MigrationMapping) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        MigrationMappingPK pk = migrationMapping.getPrimaryKey();

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

        sb.append("{entityName=");
        sb.append(getEntityName());
        sb.append(", oldId=");
        sb.append(getOldId());
        sb.append(", newId=");
        sb.append(getNewId());
        sb.append(", userId=");
        sb.append(getUserId());
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
        sb.append("com.ext.portlet.migration.model.MigrationMapping");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>entityName</column-name><column-value><![CDATA[");
        sb.append(getEntityName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>oldId</column-name><column-value><![CDATA[");
        sb.append(getOldId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>newId</column-name><column-value><![CDATA[");
        sb.append(getNewId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
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
