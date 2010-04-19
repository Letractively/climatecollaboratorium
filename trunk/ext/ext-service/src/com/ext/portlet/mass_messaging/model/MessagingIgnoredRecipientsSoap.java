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
 * <a href="MessagingIgnoredRecipientsSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.mass_messaging.service.http.MessagingIgnoredRecipientsServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.mass_messaging.service.http.MessagingIgnoredRecipientsServiceSoap
 *
 */
public class MessagingIgnoredRecipientsSoap implements Serializable {
    private Long _ignoredRecipientId;
    private String _email;
    private String _name;
    private Long _userId;
    private Date _createDate;

    public MessagingIgnoredRecipientsSoap() {
    }

    public static MessagingIgnoredRecipientsSoap toSoapModel(
        MessagingIgnoredRecipients model) {
        MessagingIgnoredRecipientsSoap soapModel = new MessagingIgnoredRecipientsSoap();

        soapModel.setIgnoredRecipientId(model.getIgnoredRecipientId());
        soapModel.setEmail(model.getEmail());
        soapModel.setName(model.getName());
        soapModel.setUserId(model.getUserId());
        soapModel.setCreateDate(model.getCreateDate());

        return soapModel;
    }

    public static MessagingIgnoredRecipientsSoap[] toSoapModels(
        MessagingIgnoredRecipients[] models) {
        MessagingIgnoredRecipientsSoap[] soapModels = new MessagingIgnoredRecipientsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static MessagingIgnoredRecipientsSoap[][] toSoapModels(
        MessagingIgnoredRecipients[][] models) {
        MessagingIgnoredRecipientsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new MessagingIgnoredRecipientsSoap[models.length][models[0].length];
        } else {
            soapModels = new MessagingIgnoredRecipientsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static MessagingIgnoredRecipientsSoap[] toSoapModels(
        List<MessagingIgnoredRecipients> models) {
        List<MessagingIgnoredRecipientsSoap> soapModels = new ArrayList<MessagingIgnoredRecipientsSoap>(models.size());

        for (MessagingIgnoredRecipients model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new MessagingIgnoredRecipientsSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _ignoredRecipientId;
    }

    public void setPrimaryKey(Long pk) {
        setIgnoredRecipientId(pk);
    }

    public Long getIgnoredRecipientId() {
        return _ignoredRecipientId;
    }

    public void setIgnoredRecipientId(Long ignoredRecipientId) {
        _ignoredRecipientId = ignoredRecipientId;
    }

    public String getEmail() {
        return _email;
    }

    public void setEmail(String email) {
        _email = email;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
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
}
