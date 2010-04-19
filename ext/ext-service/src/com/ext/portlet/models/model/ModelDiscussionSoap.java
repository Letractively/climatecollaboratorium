/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="ModelDiscussionSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.models.service.http.ModelDiscussionServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.service.http.ModelDiscussionServiceSoap
 *
 */
public class ModelDiscussionSoap implements Serializable {
    private Long _modelDiscussionId;
    private Long _modelId;
    private Long _categoryId;

    public ModelDiscussionSoap() {
    }

    public static ModelDiscussionSoap toSoapModel(ModelDiscussion model) {
        ModelDiscussionSoap soapModel = new ModelDiscussionSoap();

        soapModel.setModelDiscussionId(model.getModelDiscussionId());
        soapModel.setModelId(model.getModelId());
        soapModel.setCategoryId(model.getCategoryId());

        return soapModel;
    }

    public static ModelDiscussionSoap[] toSoapModels(ModelDiscussion[] models) {
        ModelDiscussionSoap[] soapModels = new ModelDiscussionSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ModelDiscussionSoap[][] toSoapModels(
        ModelDiscussion[][] models) {
        ModelDiscussionSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ModelDiscussionSoap[models.length][models[0].length];
        } else {
            soapModels = new ModelDiscussionSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ModelDiscussionSoap[] toSoapModels(
        List<ModelDiscussion> models) {
        List<ModelDiscussionSoap> soapModels = new ArrayList<ModelDiscussionSoap>(models.size());

        for (ModelDiscussion model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ModelDiscussionSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _modelDiscussionId;
    }

    public void setPrimaryKey(Long pk) {
        setModelDiscussionId(pk);
    }

    public Long getModelDiscussionId() {
        return _modelDiscussionId;
    }

    public void setModelDiscussionId(Long modelDiscussionId) {
        _modelDiscussionId = modelDiscussionId;
    }

    public Long getModelId() {
        return _modelId;
    }

    public void setModelId(Long modelId) {
        _modelId = modelId;
    }

    public Long getCategoryId() {
        return _categoryId;
    }

    public void setCategoryId(Long categoryId) {
        _categoryId = categoryId;
    }
}
