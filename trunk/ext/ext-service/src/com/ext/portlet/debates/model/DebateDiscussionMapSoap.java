/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.debates.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="DebateDiscussionMapSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.debates.service.http.DebateDiscussionMapServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debates.service.http.DebateDiscussionMapServiceSoap
 *
 */
public class DebateDiscussionMapSoap implements Serializable {
    private Long _debateMessageId;
    private Long _discussionThreadId;

    public DebateDiscussionMapSoap() {
    }

    public static DebateDiscussionMapSoap toSoapModel(DebateDiscussionMap model) {
        DebateDiscussionMapSoap soapModel = new DebateDiscussionMapSoap();

        soapModel.setDebateMessageId(model.getDebateMessageId());
        soapModel.setDiscussionThreadId(model.getDiscussionThreadId());

        return soapModel;
    }

    public static DebateDiscussionMapSoap[] toSoapModels(
        DebateDiscussionMap[] models) {
        DebateDiscussionMapSoap[] soapModels = new DebateDiscussionMapSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static DebateDiscussionMapSoap[][] toSoapModels(
        DebateDiscussionMap[][] models) {
        DebateDiscussionMapSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new DebateDiscussionMapSoap[models.length][models[0].length];
        } else {
            soapModels = new DebateDiscussionMapSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static DebateDiscussionMapSoap[] toSoapModels(
        List<DebateDiscussionMap> models) {
        List<DebateDiscussionMapSoap> soapModels = new ArrayList<DebateDiscussionMapSoap>(models.size());

        for (DebateDiscussionMap model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new DebateDiscussionMapSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _debateMessageId;
    }

    public void setPrimaryKey(Long pk) {
        setDebateMessageId(pk);
    }

    public Long getDebateMessageId() {
        return _debateMessageId;
    }

    public void setDebateMessageId(Long debateMessageId) {
        _debateMessageId = debateMessageId;
    }

    public Long getDiscussionThreadId() {
        return _discussionThreadId;
    }

    public void setDiscussionThreadId(Long discussionThreadId) {
        _discussionThreadId = discussionThreadId;
    }
}
