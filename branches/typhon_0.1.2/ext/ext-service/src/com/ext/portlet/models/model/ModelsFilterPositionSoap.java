/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.model;

import com.ext.portlet.models.service.persistence.ModelsFilterPositionPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="ModelsFilterPositionSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.models.service.http.ModelsFilterPositionServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.service.http.ModelsFilterPositionServiceSoap
 *
 */
public class ModelsFilterPositionSoap implements Serializable {
    private Long _userId;
    private Boolean _published;
    private Long _positionId;

    public ModelsFilterPositionSoap() {
    }

    public static ModelsFilterPositionSoap toSoapModel(
        ModelsFilterPosition model) {
        ModelsFilterPositionSoap soapModel = new ModelsFilterPositionSoap();

        soapModel.setUserId(model.getUserId());
        soapModel.setPublished(model.getPublished());
        soapModel.setPositionId(model.getPositionId());

        return soapModel;
    }

    public static ModelsFilterPositionSoap[] toSoapModels(
        ModelsFilterPosition[] models) {
        ModelsFilterPositionSoap[] soapModels = new ModelsFilterPositionSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ModelsFilterPositionSoap[][] toSoapModels(
        ModelsFilterPosition[][] models) {
        ModelsFilterPositionSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ModelsFilterPositionSoap[models.length][models[0].length];
        } else {
            soapModels = new ModelsFilterPositionSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ModelsFilterPositionSoap[] toSoapModels(
        List<ModelsFilterPosition> models) {
        List<ModelsFilterPositionSoap> soapModels = new ArrayList<ModelsFilterPositionSoap>(models.size());

        for (ModelsFilterPosition model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ModelsFilterPositionSoap[soapModels.size()]);
    }

    public ModelsFilterPositionPK getPrimaryKey() {
        return new ModelsFilterPositionPK(_userId, _published, _positionId);
    }

    public void setPrimaryKey(ModelsFilterPositionPK pk) {
        setUserId(pk.userId);
        setPublished(pk.published);
        setPositionId(pk.positionId);
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }

    public Boolean getPublished() {
        return _published;
    }

    public void setPublished(Boolean published) {
        _published = published;
    }

    public Long getPositionId() {
        return _positionId;
    }

    public void setPositionId(Long positionId) {
        _positionId = positionId;
    }
}
