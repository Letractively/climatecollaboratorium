/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.migration.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <a href="MigrationDataSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.migration.service.http.MigrationDataServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.migration.service.http.MigrationDataServiceSoap
 *
 */
public class MigrationDataSoap implements Serializable {
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

    public MigrationDataSoap() {
    }

    public static MigrationDataSoap toSoapModel(MigrationData model) {
        MigrationDataSoap soapModel = new MigrationDataSoap();

        soapModel.setMigrationId(model.getMigrationId());
        soapModel.setName(model.getName());
        soapModel.setXml(model.getXml());
        soapModel.setDescription(model.getDescription());
        soapModel.setUsers(model.getUsers());
        soapModel.setPlans(model.getPlans());
        soapModel.setQuestions(model.getQuestions());
        soapModel.setAlternatives(model.getAlternatives());
        soapModel.setArguments(model.getArguments());
        soapModel.setVotes(model.getVotes());
        soapModel.setUserId(model.getUserId());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setCreatedDate(model.getCreatedDate());

        return soapModel;
    }

    public static MigrationDataSoap[] toSoapModels(MigrationData[] models) {
        MigrationDataSoap[] soapModels = new MigrationDataSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static MigrationDataSoap[][] toSoapModels(MigrationData[][] models) {
        MigrationDataSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new MigrationDataSoap[models.length][models[0].length];
        } else {
            soapModels = new MigrationDataSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static MigrationDataSoap[] toSoapModels(List<MigrationData> models) {
        List<MigrationDataSoap> soapModels = new ArrayList<MigrationDataSoap>(models.size());

        for (MigrationData model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new MigrationDataSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _migrationId;
    }

    public void setPrimaryKey(Long pk) {
        setMigrationId(pk);
    }

    public Long getMigrationId() {
        return _migrationId;
    }

    public void setMigrationId(Long migrationId) {
        _migrationId = migrationId;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getXml() {
        return _xml;
    }

    public void setXml(String xml) {
        _xml = xml;
    }

    public String getDescription() {
        return _description;
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
}
