package com.ext.portlet.debaterevision.model.impl;

import com.ext.portlet.debaterevision.model.DebateItemVote;
import com.ext.portlet.debaterevision.model.DebateItemVoteSoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="DebateItemVoteModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>DebateItemVote</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.model.DebateItemVote
 * @see com.ext.portlet.debaterevision.model.DebateItemVoteModel
 * @see com.ext.portlet.debaterevision.model.impl.DebateItemVoteImpl
 *
 */
public class DebateItemVoteModelImpl extends BaseModelImpl<DebateItemVote> {
    public static final String TABLE_NAME = "DebateItemVote";
    public static final Object[][] TABLE_COLUMNS = {
            { "debateItemVoteId", new Integer(Types.BIGINT) },
            

            { "debateItemId", new Integer(Types.BIGINT) },
            

            { "userId", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table DebateItemVote (debateItemVoteId LONG not null primary key,debateItemId LONG,userId LONG)";
    public static final String TABLE_SQL_DROP = "drop table DebateItemVote";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.debaterevision.model.DebateItemVote"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.debaterevision.model.DebateItemVote"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.debaterevision.model.DebateItemVote"));
    private Long _debateItemVoteId;
    private Long _debateItemId;
    private Long _userId;

    public DebateItemVoteModelImpl() {
    }

    public static DebateItemVote toModel(DebateItemVoteSoap soapModel) {
        DebateItemVote model = new DebateItemVoteImpl();

        model.setDebateItemVoteId(soapModel.getDebateItemVoteId());
        model.setDebateItemId(soapModel.getDebateItemId());
        model.setUserId(soapModel.getUserId());

        return model;
    }

    public static List<DebateItemVote> toModels(DebateItemVoteSoap[] soapModels) {
        List<DebateItemVote> models = new ArrayList<DebateItemVote>(soapModels.length);

        for (DebateItemVoteSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _debateItemVoteId;
    }

    public void setPrimaryKey(Long pk) {
        setDebateItemVoteId(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _debateItemVoteId;
    }

    public Long getDebateItemVoteId() {
        return _debateItemVoteId;
    }

    public void setDebateItemVoteId(Long debateItemVoteId) {
        _debateItemVoteId = debateItemVoteId;
    }

    public Long getDebateItemId() {
        return _debateItemId;
    }

    public void setDebateItemId(Long debateItemId) {
        _debateItemId = debateItemId;
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }

    public DebateItemVote toEscapedModel() {
        if (isEscapedModel()) {
            return (DebateItemVote) this;
        } else {
            DebateItemVote model = new DebateItemVoteImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setDebateItemVoteId(getDebateItemVoteId());
            model.setDebateItemId(getDebateItemId());
            model.setUserId(getUserId());

            model = (DebateItemVote) Proxy.newProxyInstance(DebateItemVote.class.getClassLoader(),
                    new Class[] { DebateItemVote.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        DebateItemVoteImpl clone = new DebateItemVoteImpl();

        clone.setDebateItemVoteId(getDebateItemVoteId());
        clone.setDebateItemId(getDebateItemId());
        clone.setUserId(getUserId());

        return clone;
    }

    public int compareTo(DebateItemVote debateItemVote) {
        Long pk = debateItemVote.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        DebateItemVote debateItemVote = null;

        try {
            debateItemVote = (DebateItemVote) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = debateItemVote.getPrimaryKey();

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

        sb.append("{debateItemVoteId=");
        sb.append(getDebateItemVoteId());
        sb.append(", debateItemId=");
        sb.append(getDebateItemId());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.debaterevision.model.DebateItemVote");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>debateItemVoteId</column-name><column-value><![CDATA[");
        sb.append(getDebateItemVoteId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>debateItemId</column-name><column-value><![CDATA[");
        sb.append(getDebateItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
