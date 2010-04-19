/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <a href="MessagingMessageConversionSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.mass_messaging.service.http.MessagingMessageConversionServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.mass_messaging.service.http.MessagingMessageConversionServiceSoap
 *
 */
public class MessagingMessageConversionSoap implements Serializable {
    private Long _conversionId;
    private Long _conversionTypeId;
    private Long _messageId;
    private String _ipAddress;
    private String _extraData;
    private String _extraData2;
    private Date _createDate;

    public MessagingMessageConversionSoap() {
    }

    public static MessagingMessageConversionSoap toSoapModel(
        MessagingMessageConversion model) {
        MessagingMessageConversionSoap soapModel = new MessagingMessageConversionSoap();

        soapModel.setConversionId(model.getConversionId());
        soapModel.setConversionTypeId(model.getConversionTypeId());
        soapModel.setMessageId(model.getMessageId());
        soapModel.setIpAddress(model.getIpAddress());
        soapModel.setExtraData(model.getExtraData());
        soapModel.setExtraData2(model.getExtraData2());
        soapModel.setCreateDate(model.getCreateDate());

        return soapModel;
    }

    public static MessagingMessageConversionSoap[] toSoapModels(
        MessagingMessageConversion[] models) {
        MessagingMessageConversionSoap[] soapModels = new MessagingMessageConversionSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static MessagingMessageConversionSoap[][] toSoapModels(
        MessagingMessageConversion[][] models) {
        MessagingMessageConversionSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new MessagingMessageConversionSoap[models.length][models[0].length];
        } else {
            soapModels = new MessagingMessageConversionSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static MessagingMessageConversionSoap[] toSoapModels(
        List<MessagingMessageConversion> models) {
        List<MessagingMessageConversionSoap> soapModels = new ArrayList<MessagingMessageConversionSoap>(models.size());

        for (MessagingMessageConversion model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new MessagingMessageConversionSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _conversionId;
    }

    public void setPrimaryKey(Long pk) {
        setConversionId(pk);
    }

    public Long getConversionId() {
        return _conversionId;
    }

    public void setConversionId(Long conversionId) {
        _conversionId = conversionId;
    }

    public Long getConversionTypeId() {
        return _conversionTypeId;
    }

    public void setConversionTypeId(Long conversionTypeId) {
        _conversionTypeId = conversionTypeId;
    }

    public Long getMessageId() {
        return _messageId;
    }

    public void setMessageId(Long messageId) {
        _messageId = messageId;
    }

    public String getIpAddress() {
        return _ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        _ipAddress = ipAddress;
    }

    public String getExtraData() {
        return _extraData;
    }

    public void setExtraData(String extraData) {
        _extraData = extraData;
    }

    public String getExtraData2() {
        return _extraData2;
    }

    public void setExtraData2(String extraData2) {
        _extraData2 = extraData2;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }
}
