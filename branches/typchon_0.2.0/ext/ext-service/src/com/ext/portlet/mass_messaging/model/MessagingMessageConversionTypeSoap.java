/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="MessagingMessageConversionTypeSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.mass_messaging.service.http.MessagingMessageConversionTypeServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.mass_messaging.service.http.MessagingMessageConversionTypeServiceSoap
 *
 */
public class MessagingMessageConversionTypeSoap implements Serializable {
    private Long _typeId;
    private String _name;
    private String _description;

    public MessagingMessageConversionTypeSoap() {
    }

    public static MessagingMessageConversionTypeSoap toSoapModel(
        MessagingMessageConversionType model) {
        MessagingMessageConversionTypeSoap soapModel = new MessagingMessageConversionTypeSoap();

        soapModel.setTypeId(model.getTypeId());
        soapModel.setName(model.getName());
        soapModel.setDescription(model.getDescription());

        return soapModel;
    }

    public static MessagingMessageConversionTypeSoap[] toSoapModels(
        MessagingMessageConversionType[] models) {
        MessagingMessageConversionTypeSoap[] soapModels = new MessagingMessageConversionTypeSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static MessagingMessageConversionTypeSoap[][] toSoapModels(
        MessagingMessageConversionType[][] models) {
        MessagingMessageConversionTypeSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new MessagingMessageConversionTypeSoap[models.length][models[0].length];
        } else {
            soapModels = new MessagingMessageConversionTypeSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static MessagingMessageConversionTypeSoap[] toSoapModels(
        List<MessagingMessageConversionType> models) {
        List<MessagingMessageConversionTypeSoap> soapModels = new ArrayList<MessagingMessageConversionTypeSoap>(models.size());

        for (MessagingMessageConversionType model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new MessagingMessageConversionTypeSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _typeId;
    }

    public void setPrimaryKey(Long pk) {
        setTypeId(pk);
    }

    public Long getTypeId() {
        return _typeId;
    }

    public void setTypeId(Long typeId) {
        _typeId = typeId;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }
}
