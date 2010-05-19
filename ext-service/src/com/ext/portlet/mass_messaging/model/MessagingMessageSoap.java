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
 * <a href="MessagingMessageSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.mass_messaging.service.http.MessagingMessageServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.mass_messaging.service.http.MessagingMessageServiceSoap
 *
 */
public class MessagingMessageSoap implements Serializable {
    private Long _messageId;
    private String _name;
    private String _description;
    private String _subject;
    private String _body;
    private String _replyTo;
    private Boolean _sendToAll;
    private Long _conversionCount;
    private String _redirectURL;
    private Long _creatorId;
    private Date _createDate;
    private Date _modifiedDate;

    public MessagingMessageSoap() {
    }

    public static MessagingMessageSoap toSoapModel(MessagingMessage model) {
        MessagingMessageSoap soapModel = new MessagingMessageSoap();

        soapModel.setMessageId(model.getMessageId());
        soapModel.setName(model.getName());
        soapModel.setDescription(model.getDescription());
        soapModel.setSubject(model.getSubject());
        soapModel.setBody(model.getBody());
        soapModel.setReplyTo(model.getReplyTo());
        soapModel.setSendToAll(model.getSendToAll());
        soapModel.setConversionCount(model.getConversionCount());
        soapModel.setRedirectURL(model.getRedirectURL());
        soapModel.setCreatorId(model.getCreatorId());
        soapModel.setCreateDate(model.getCreateDate());
        soapModel.setModifiedDate(model.getModifiedDate());

        return soapModel;
    }

    public static MessagingMessageSoap[] toSoapModels(MessagingMessage[] models) {
        MessagingMessageSoap[] soapModels = new MessagingMessageSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static MessagingMessageSoap[][] toSoapModels(
        MessagingMessage[][] models) {
        MessagingMessageSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new MessagingMessageSoap[models.length][models[0].length];
        } else {
            soapModels = new MessagingMessageSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static MessagingMessageSoap[] toSoapModels(
        List<MessagingMessage> models) {
        List<MessagingMessageSoap> soapModels = new ArrayList<MessagingMessageSoap>(models.size());

        for (MessagingMessage model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new MessagingMessageSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _messageId;
    }

    public void setPrimaryKey(Long pk) {
        setMessageId(pk);
    }

    public Long getMessageId() {
        return _messageId;
    }

    public void setMessageId(Long messageId) {
        _messageId = messageId;
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

    public String getSubject() {
        return _subject;
    }

    public void setSubject(String subject) {
        _subject = subject;
    }

    public String getBody() {
        return _body;
    }

    public void setBody(String body) {
        _body = body;
    }

    public String getReplyTo() {
        return _replyTo;
    }

    public void setReplyTo(String replyTo) {
        _replyTo = replyTo;
    }

    public Boolean getSendToAll() {
        return _sendToAll;
    }

    public void setSendToAll(Boolean sendToAll) {
        _sendToAll = sendToAll;
    }

    public Long getConversionCount() {
        return _conversionCount;
    }

    public void setConversionCount(Long conversionCount) {
        _conversionCount = conversionCount;
    }

    public String getRedirectURL() {
        return _redirectURL;
    }

    public void setRedirectURL(String redirectURL) {
        _redirectURL = redirectURL;
    }

    public Long getCreatorId() {
        return _creatorId;
    }

    public void setCreatorId(Long creatorId) {
        _creatorId = creatorId;
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
