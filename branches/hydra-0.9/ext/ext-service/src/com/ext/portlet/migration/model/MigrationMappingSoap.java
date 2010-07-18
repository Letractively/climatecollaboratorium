/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.migration.model;

import com.ext.portlet.migration.service.persistence.MigrationMappingPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <a href="MigrationMappingSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.migration.service.http.MigrationMappingServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.migration.service.http.MigrationMappingServiceSoap
 *
 */
public class MigrationMappingSoap implements Serializable {
    private String _entityName;
    private String _oldId;
    private Long _newId;
    private Long _userId;
    private Date _createDate;
    private Date _modifiedDate;

    public MigrationMappingSoap() {
    }

    public static MigrationMappingSoap toSoapModel(MigrationMapping model) {
        MigrationMappingSoap soapModel = new MigrationMappingSoap();

        soapModel.setEntityName(model.getEntityName());
        soapModel.setOldId(model.getOldId());
        soapModel.setNewId(model.getNewId());
        soapModel.setUserId(model.getUserId());
        soapModel.setCreateDate(model.getCreateDate());
        soapModel.setModifiedDate(model.getModifiedDate());

        return soapModel;
    }

    public static MigrationMappingSoap[] toSoapModels(MigrationMapping[] models) {
        MigrationMappingSoap[] soapModels = new MigrationMappingSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static MigrationMappingSoap[][] toSoapModels(
        MigrationMapping[][] models) {
        MigrationMappingSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new MigrationMappingSoap[models.length][models[0].length];
        } else {
            soapModels = new MigrationMappingSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static MigrationMappingSoap[] toSoapModels(
        List<MigrationMapping> models) {
        List<MigrationMappingSoap> soapModels = new ArrayList<MigrationMappingSoap>(models.size());

        for (MigrationMapping model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new MigrationMappingSoap[soapModels.size()]);
    }

    public MigrationMappingPK getPrimaryKey() {
        return new MigrationMappingPK(_entityName, _oldId);
    }

    public void setPrimaryKey(MigrationMappingPK pk) {
        setEntityName(pk.entityName);
        setOldId(pk.oldId);
    }

    public String getEntityName() {
        return _entityName;
    }

    public void setEntityName(String entityName) {
        _entityName = entityName;
    }

    public String getOldId() {
        return _oldId;
    }

    public void setOldId(String oldId) {
        _oldId = oldId;
    }

    public Long getNewId() {
        return _newId;
    }

    public void setNewId(Long newId) {
        _newId = newId;
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
}
