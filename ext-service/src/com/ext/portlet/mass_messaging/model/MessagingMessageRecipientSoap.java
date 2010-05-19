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
 * <a href="MessagingMessageRecipientSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.mass_messaging.service.http.MessagingMessageRecipientServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.mass_messaging.service.http.MessagingMessageRecipientServiceSoap
 *
 */
public class MessagingMessageRecipientSoap implements Serializable {
    private Long _recipientId;
    private Long _messageId;
    private Long _userId;
    private String _emailAddress;

    public MessagingMessageRecipientSoap() {
    }

    public static MessagingMessageRecipientSoap toSoapModel(
        MessagingMessageRecipient model) {
        MessagingMessageRecipientSoap soapModel = new MessagingMessageRecipientSoap();

        soapModel.setRecipientId(model.getRecipientId());
        soapModel.setMessageId(model.getMessageId());
        soapModel.setUserId(model.getUserId());
        soapModel.setEmailAddress(model.getEmailAddress());

        return soapModel;
    }

    public static MessagingMessageRecipientSoap[] toSoapModels(
        MessagingMessageRecipient[] models) {
        MessagingMessageRecipientSoap[] soapModels = new MessagingMessageRecipientSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static MessagingMessageRecipientSoap[][] toSoapModels(
        MessagingMessageRecipient[][] models) {
        MessagingMessageRecipientSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new MessagingMessageRecipientSoap[models.length][models[0].length];
        } else {
            soapModels = new MessagingMessageRecipientSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static MessagingMessageRecipientSoap[] toSoapModels(
        List<MessagingMessageRecipient> models) {
        List<MessagingMessageRecipientSoap> soapModels = new ArrayList<MessagingMessageRecipientSoap>(models.size());

        for (MessagingMessageRecipient model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new MessagingMessageRecipientSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _recipientId;
    }

    public void setPrimaryKey(Long pk) {
        setRecipientId(pk);
    }

    public Long getRecipientId() {
        return _recipientId;
    }

    public void setRecipientId(Long recipientId) {
        _recipientId = recipientId;
    }

    public Long getMessageId() {
        return _messageId;
    }

    public void setMessageId(Long messageId) {
        _messageId = messageId;
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }

    public String getEmailAddress() {
        return _emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        _emailAddress = emailAddress;
    }
}
