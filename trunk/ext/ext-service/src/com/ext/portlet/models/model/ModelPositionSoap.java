/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.model;

import com.ext.portlet.models.service.persistence.ModelPositionPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <a href="ModelPositionSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.models.service.http.ModelPositionServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.service.http.ModelPositionServiceSoap
 *
 */
public class ModelPositionSoap implements Serializable {
    private Long _modelId;
    private Long _positionId;
    private Long _userId;
    private String _userName;
    private Date _createDate;
    private Date _modifiedDate;

    public ModelPositionSoap() {
    }

    public static ModelPositionSoap toSoapModel(ModelPosition model) {
        ModelPositionSoap soapModel = new ModelPositionSoap();

        soapModel.setModelId(model.getModelId());
        soapModel.setPositionId(model.getPositionId());
        soapModel.setUserId(model.getUserId());
        soapModel.setUserName(model.getUserName());
        soapModel.setCreateDate(model.getCreateDate());
        soapModel.setModifiedDate(model.getModifiedDate());

        return soapModel;
    }

    public static ModelPositionSoap[] toSoapModels(ModelPosition[] models) {
        ModelPositionSoap[] soapModels = new ModelPositionSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ModelPositionSoap[][] toSoapModels(ModelPosition[][] models) {
        ModelPositionSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ModelPositionSoap[models.length][models[0].length];
        } else {
            soapModels = new ModelPositionSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ModelPositionSoap[] toSoapModels(List<ModelPosition> models) {
        List<ModelPositionSoap> soapModels = new ArrayList<ModelPositionSoap>(models.size());

        for (ModelPosition model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ModelPositionSoap[soapModels.size()]);
    }

    public ModelPositionPK getPrimaryKey() {
        return new ModelPositionPK(_modelId, _positionId);
    }

    public void setPrimaryKey(ModelPositionPK pk) {
        setModelId(pk.modelId);
        setPositionId(pk.positionId);
    }

    public Long getModelId() {
        return _modelId;
    }

    public void setModelId(Long modelId) {
        _modelId = modelId;
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
        return _userName;
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
}
