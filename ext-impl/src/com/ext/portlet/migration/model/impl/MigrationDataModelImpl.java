/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.migration.model.impl;

import com.ext.portlet.migration.model.MigrationData;
import com.ext.portlet.migration.model.MigrationDataSoap;

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
 * <a href="MigrationDataModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>MigrationData</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.migration.model.MigrationData
 * @see com.ext.portlet.migration.model.MigrationDataModel
 * @see com.ext.portlet.migration.model.impl.MigrationDataImpl
 *
 */
public class MigrationDataModelImpl extends BaseModelImpl<MigrationData> {
    public static final String TABLE_NAME = "MigrationData";
    public static final Object[][] TABLE_COLUMNS = {
            { "migrationId", new Integer(Types.BIGINT) },
            

            { "name", new Integer(Types.VARCHAR) },
            

            { "xml", new Integer(Types.VARCHAR) },
            

            { "description", new Integer(Types.VARCHAR) },
            

            { "users", new Integer(Types.INTEGER) },
            

            { "plans", new Integer(Types.INTEGER) },
            

            { "questions", new Integer(Types.INTEGER) },
            

            { "alternatives", new Integer(Types.INTEGER) },
            

            { "arguments", new Integer(Types.INTEGER) },
            

            { "votes", new Integer(Types.INTEGER) },
            

            { "userId", new Integer(Types.BIGINT) },
            

            { "modifiedDate", new Integer(Types.TIMESTAMP) },
            

            { "createdDate", new Integer(Types.TIMESTAMP) }
        };
    public static final String TABLE_SQL_CREATE = "create table MigrationData (migrationId LONG not null primary key,name VARCHAR(75) null,xml VARCHAR(75) null,description VARCHAR(75) null,users INTEGER,plans INTEGER,questions INTEGER,alternatives INTEGER,arguments INTEGER,votes INTEGER,userId LONG,modifiedDate DATE null,createdDate DATE null)";
    public static final String TABLE_SQL_DROP = "drop table MigrationData";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.migration.model.MigrationData"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.migration.model.MigrationData"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.migration.model.MigrationData"));
    private Long _migrationId;
    private String _name;
    private String _xml;
    private String _description;
    private Integer _users;
    private Integer _plans;
    private Integer _questions;
    private Integer _alternatives;
    private Integer _arguments;
    private Integer _votes;
    private Long _userId;
    private Date _modifiedDate;
    private Date _createdDate;

    public MigrationDataModelImpl() {
    }

    public static MigrationData toModel(MigrationDataSoap soapModel) {
        MigrationData model = new MigrationDataImpl();

        model.setMigrationId(soapModel.getMigrationId());
        model.setName(soapModel.getName());
        model.setXml(soapModel.getXml());
        model.setDescription(soapModel.getDescription());
        model.setUsers(soapModel.getUsers());
        model.setPlans(soapModel.getPlans());
        model.setQuestions(soapModel.getQuestions());
        model.setAlternatives(soapModel.getAlternatives());
        model.setArguments(soapModel.getArguments());
        model.setVotes(soapModel.getVotes());
        model.setUserId(soapModel.getUserId());
        model.setModifiedDate(soapModel.getModifiedDate());
        model.setCreatedDate(soapModel.getCreatedDate());

        return model;
    }

    public static List<MigrationData> toModels(MigrationDataSoap[] soapModels) {
        List<MigrationData> models = new ArrayList<MigrationData>(soapModels.length);

        for (MigrationDataSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _migrationId;
    }

    public void setPrimaryKey(Long pk) {
        setMigrationId(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _migrationId;
    }

    public Long getMigrationId() {
        return _migrationId;
    }

    public void setMigrationId(Long migrationId) {
        _migrationId = migrationId;
    }

    public String getName() {
        return GetterUtil.getString(_name);
    }

    public void setName(String name) {
        _name = name;
    }

    public String getXml() {
        return GetterUtil.getString(_xml);
    }

    public void setXml(String xml) {
        _xml = xml;
    }

    public String getDescription() {
        return GetterUtil.getString(_description);
    }

    public void setDescription(String description) {
        _description = description;
    }

    public Integer getUsers() {
        return _users;
    }

    public void setUsers(Integer users) {
        _users = users;
    }

    public Integer getPlans() {
        return _plans;
    }

    public void setPlans(Integer plans) {
        _plans = plans;
    }

    public Integer getQuestions() {
        return _questions;
    }

    public void setQuestions(Integer questions) {
        _questions = questions;
    }

    public Integer getAlternatives() {
        return _alternatives;
    }

    public void setAlternatives(Integer alternatives) {
        _alternatives = alternatives;
    }

    public Integer getArguments() {
        return _arguments;
    }

    public void setArguments(Integer arguments) {
        _arguments = arguments;
    }

    public Integer getVotes() {
        return _votes;
    }

    public void setVotes(Integer votes) {
        _votes = votes;
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public MigrationData toEscapedModel() {
        if (isEscapedModel()) {
            return (MigrationData) this;
        } else {
            MigrationData model = new MigrationDataImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setMigrationId(getMigrationId());
            model.setName(HtmlUtil.escape(getName()));
            model.setXml(HtmlUtil.escape(getXml()));
            model.setDescription(HtmlUtil.escape(getDescription()));
            model.setUsers(getUsers());
            model.setPlans(getPlans());
            model.setQuestions(getQuestions());
            model.setAlternatives(getAlternatives());
            model.setArguments(getArguments());
            model.setVotes(getVotes());
            model.setUserId(getUserId());
            model.setModifiedDate(getModifiedDate());
            model.setCreatedDate(getCreatedDate());

            model = (MigrationData) Proxy.newProxyInstance(MigrationData.class.getClassLoader(),
                    new Class[] { MigrationData.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        MigrationDataImpl clone = new MigrationDataImpl();

        clone.setMigrationId(getMigrationId());
        clone.setName(getName());
        clone.setXml(getXml());
        clone.setDescription(getDescription());
        clone.setUsers(getUsers());
        clone.setPlans(getPlans());
        clone.setQuestions(getQuestions());
        clone.setAlternatives(getAlternatives());
        clone.setArguments(getArguments());
        clone.setVotes(getVotes());
        clone.setUserId(getUserId());
        clone.setModifiedDate(getModifiedDate());
        clone.setCreatedDate(getCreatedDate());

        return clone;
    }

    public int compareTo(MigrationData migrationData) {
        int value = 0;

        value = DateUtil.compareTo(getCreatedDate(),
                migrationData.getCreatedDate());

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

        MigrationData migrationData = null;

        try {
            migrationData = (MigrationData) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = migrationData.getPrimaryKey();

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

        sb.append("{migrationId=");
        sb.append(getMigrationId());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", xml=");
        sb.append(getXml());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", users=");
        sb.append(getUsers());
        sb.append(", plans=");
        sb.append(getPlans());
        sb.append(", questions=");
        sb.append(getQuestions());
        sb.append(", alternatives=");
        sb.append(getAlternatives());
        sb.append(", arguments=");
        sb.append(getArguments());
        sb.append(", votes=");
        sb.append(getVotes());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.migration.model.MigrationData");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>migrationId</column-name><column-value><![CDATA[");
        sb.append(getMigrationId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>xml</column-name><column-value><![CDATA[");
        sb.append(getXml());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>users</column-name><column-value><![CDATA[");
        sb.append(getUsers());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>plans</column-name><column-value><![CDATA[");
        sb.append(getPlans());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>questions</column-name><column-value><![CDATA[");
        sb.append(getQuestions());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>alternatives</column-name><column-value><![CDATA[");
        sb.append(getAlternatives());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>arguments</column-name><column-value><![CDATA[");
        sb.append(getArguments());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>votes</column-name><column-value><![CDATA[");
        sb.append(getVotes());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
