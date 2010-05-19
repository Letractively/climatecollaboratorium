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
 * <a href="MessagingRedirectLinkSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.mass_messaging.service.http.MessagingRedirectLinkServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.mass_messaging.service.http.MessagingRedirectLinkServiceSoap
 *
 */
public class MessagingRedirectLinkSoap implements Serializable {
    private Long _redirectId;
    private String _link;
    private Long _messageId;
    private Date _createDate;

    public MessagingRedirectLinkSoap() {
    }

    public static MessagingRedirectLinkSoap toSoapModel(
        MessagingRedirectLink model) {
        MessagingRedirectLinkSoap soapModel = new MessagingRedirectLinkSoap();

        soapModel.setRedirectId(model.getRedirectId());
        soapModel.setLink(model.getLink());
        soapModel.setMessageId(model.getMessageId());
        soapModel.setCreateDate(model.getCreateDate());

        return soapModel;
    }

    public static MessagingRedirectLinkSoap[] toSoapModels(
        MessagingRedirectLink[] models) {
        MessagingRedirectLinkSoap[] soapModels = new MessagingRedirectLinkSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static MessagingRedirectLinkSoap[][] toSoapModels(
        MessagingRedirectLink[][] models) {
        MessagingRedirectLinkSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new MessagingRedirectLinkSoap[models.length][models[0].length];
        } else {
            soapModels = new MessagingRedirectLinkSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static MessagingRedirectLinkSoap[] toSoapModels(
        List<MessagingRedirectLink> models) {
        List<MessagingRedirectLinkSoap> soapModels = new ArrayList<MessagingRedirectLinkSoap>(models.size());

        for (MessagingRedirectLink model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new MessagingRedirectLinkSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _redirectId;
    }

    public void setPrimaryKey(Long pk) {
        setRedirectId(pk);
    }

    public Long getRedirectId() {
        return _redirectId;
    }

    public void setRedirectId(Long redirectId) {
        _redirectId = redirectId;
    }

    public String getLink() {
        return _link;
    }

    public void setLink(String link) {
        _link = link;
    }

    public Long getMessageId() {
        return _messageId;
    }

    public void setMessageId(Long messageId) {
        _messageId = messageId;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }
}
