/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.debates.model.impl;

import com.ext.portlet.debates.model.DebateDiscussionMap;
import com.ext.portlet.debates.model.DebateDiscussionMapSoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="DebateDiscussionMapModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>DebateDiscussionMap</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debates.model.DebateDiscussionMap
 * @see com.ext.portlet.debates.model.DebateDiscussionMapModel
 * @see com.ext.portlet.debates.model.impl.DebateDiscussionMapImpl
 *
 */
public class DebateDiscussionMapModelImpl extends BaseModelImpl<DebateDiscussionMap> {
    public static final String TABLE_NAME = "DebateDiscussionMap";
    public static final Object[][] TABLE_COLUMNS = {
            { "debateMessageId", new Integer(Types.BIGINT) },
            

            { "discussionThreadId", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table DebateDiscussionMap (debateMessageId LONG not null primary key,discussionThreadId LONG)";
    public static final String TABLE_SQL_DROP = "drop table DebateDiscussionMap";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.debates.model.DebateDiscussionMap"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.debates.model.DebateDiscussionMap"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.debates.model.DebateDiscussionMap"));
    private Long _debateMessageId;
    private Long _originalDebateMessageId;
    private Long _discussionThreadId;

    public DebateDiscussionMapModelImpl() {
    }

    public static DebateDiscussionMap toModel(DebateDiscussionMapSoap soapModel) {
        DebateDiscussionMap model = new DebateDiscussionMapImpl();

        model.setDebateMessageId(soapModel.getDebateMessageId());
        model.setDiscussionThreadId(soapModel.getDiscussionThreadId());

        return model;
    }

    public static List<DebateDiscussionMap> toModels(
        DebateDiscussionMapSoap[] soapModels) {
        List<DebateDiscussionMap> models = new ArrayList<DebateDiscussionMap>(soapModels.length);

        for (DebateDiscussionMapSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _debateMessageId;
    }

    public void setPrimaryKey(Long pk) {
        setDebateMessageId(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _debateMessageId;
    }

    public Long getDebateMessageId() {
        return _debateMessageId;
    }

    public void setDebateMessageId(Long debateMessageId) {
        _debateMessageId = debateMessageId;

        if (_originalDebateMessageId == null) {
            _originalDebateMessageId = debateMessageId;
        }
    }

    public Long getOriginalDebateMessageId() {
        return _originalDebateMessageId;
    }

    public Long getDiscussionThreadId() {
        return _discussionThreadId;
    }

    public void setDiscussionThreadId(Long discussionThreadId) {
        _discussionThreadId = discussionThreadId;
    }

    public DebateDiscussionMap toEscapedModel() {
        if (isEscapedModel()) {
            return (DebateDiscussionMap) this;
        } else {
            DebateDiscussionMap model = new DebateDiscussionMapImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setDebateMessageId(getDebateMessageId());
            model.setDiscussionThreadId(getDiscussionThreadId());

            model = (DebateDiscussionMap) Proxy.newProxyInstance(DebateDiscussionMap.class.getClassLoader(),
                    new Class[] { DebateDiscussionMap.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        DebateDiscussionMapImpl clone = new DebateDiscussionMapImpl();

        clone.setDebateMessageId(getDebateMessageId());
        clone.setDiscussionThreadId(getDiscussionThreadId());

        return clone;
    }

    public int compareTo(DebateDiscussionMap debateDiscussionMap) {
        Long pk = debateDiscussionMap.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        DebateDiscussionMap debateDiscussionMap = null;

        try {
            debateDiscussionMap = (DebateDiscussionMap) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = debateDiscussionMap.getPrimaryKey();

        if (getPrimaryKey().equals(pk)) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return getPrimaryKey().hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{debateMessageId=");
        sb.append(getDebateMessageId());
        sb.append(", discussionThreadId=");
        sb.append(getDiscussionThreadId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.debates.model.DebateDiscussionMap");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>debateMessageId</column-name><column-value><![CDATA[");
        sb.append(getDebateMessageId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>discussionThreadId</column-name><column-value><![CDATA[");
        sb.append(getDiscussionThreadId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
