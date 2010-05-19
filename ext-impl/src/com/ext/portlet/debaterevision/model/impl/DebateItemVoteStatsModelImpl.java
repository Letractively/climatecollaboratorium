package com.ext.portlet.debaterevision.model.impl;

import com.ext.portlet.debaterevision.model.DebateItemVoteStats;
import com.ext.portlet.debaterevision.model.DebateItemVoteStatsSoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="DebateItemVoteStatsModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>DebateItemVoteStats</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.model.DebateItemVoteStats
 * @see com.ext.portlet.debaterevision.model.DebateItemVoteStatsModel
 * @see com.ext.portlet.debaterevision.model.impl.DebateItemVoteStatsImpl
 *
 */
public class DebateItemVoteStatsModelImpl extends BaseModelImpl<DebateItemVoteStats> {
    public static final String TABLE_NAME = "DebateItemVoteStats";
    public static final Object[][] TABLE_COLUMNS = {
            { "debateItemVotesStats", new Integer(Types.BIGINT) },
            

            { "debateItemId", new Integer(Types.BIGINT) },
            

            { "votesCount", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table DebateItemVoteStats (debateItemVotesStats LONG not null primary key,debateItemId LONG,votesCount LONG)";
    public static final String TABLE_SQL_DROP = "drop table DebateItemVoteStats";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.debaterevision.model.DebateItemVoteStats"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.debaterevision.model.DebateItemVoteStats"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.debaterevision.model.DebateItemVoteStats"));
    private Long _debateItemVotesStats;
    private Long _debateItemId;
    private Long _originalDebateItemId;
    private Long _votesCount;

    public DebateItemVoteStatsModelImpl() {
    }

    public static DebateItemVoteStats toModel(DebateItemVoteStatsSoap soapModel) {
        DebateItemVoteStats model = new DebateItemVoteStatsImpl();

        model.setDebateItemVotesStats(soapModel.getDebateItemVotesStats());
        model.setDebateItemId(soapModel.getDebateItemId());
        model.setVotesCount(soapModel.getVotesCount());

        return model;
    }

    public static List<DebateItemVoteStats> toModels(
        DebateItemVoteStatsSoap[] soapModels) {
        List<DebateItemVoteStats> models = new ArrayList<DebateItemVoteStats>(soapModels.length);

        for (DebateItemVoteStatsSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _debateItemVotesStats;
    }

    public void setPrimaryKey(Long pk) {
        setDebateItemVotesStats(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _debateItemVotesStats;
    }

    public Long getDebateItemVotesStats() {
        return _debateItemVotesStats;
    }

    public void setDebateItemVotesStats(Long debateItemVotesStats) {
        _debateItemVotesStats = debateItemVotesStats;
    }

    public Long getDebateItemId() {
        return _debateItemId;
    }

    public void setDebateItemId(Long debateItemId) {
        _debateItemId = debateItemId;

        if (_originalDebateItemId == null) {
            _originalDebateItemId = debateItemId;
        }
    }

    public Long getOriginalDebateItemId() {
        return _originalDebateItemId;
    }

    public Long getVotesCount() {
        return _votesCount;
    }

    public void setVotesCount(Long votesCount) {
        _votesCount = votesCount;
    }

    public DebateItemVoteStats toEscapedModel() {
        if (isEscapedModel()) {
            return (DebateItemVoteStats) this;
        } else {
            DebateItemVoteStats model = new DebateItemVoteStatsImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setDebateItemVotesStats(getDebateItemVotesStats());
            model.setDebateItemId(getDebateItemId());
            model.setVotesCount(getVotesCount());

            model = (DebateItemVoteStats) Proxy.newProxyInstance(DebateItemVoteStats.class.getClassLoader(),
                    new Class[] { DebateItemVoteStats.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        DebateItemVoteStatsImpl clone = new DebateItemVoteStatsImpl();

        clone.setDebateItemVotesStats(getDebateItemVotesStats());
        clone.setDebateItemId(getDebateItemId());
        clone.setVotesCount(getVotesCount());

        return clone;
    }

    public int compareTo(DebateItemVoteStats debateItemVoteStats) {
        Long pk = debateItemVoteStats.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        DebateItemVoteStats debateItemVoteStats = null;

        try {
            debateItemVoteStats = (DebateItemVoteStats) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = debateItemVoteStats.getPrimaryKey();

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

        sb.append("{debateItemVotesStats=");
        sb.append(getDebateItemVotesStats());
        sb.append(", debateItemId=");
        sb.append(getDebateItemId());
        sb.append(", votesCount=");
        sb.append(getVotesCount());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.debaterevision.model.DebateItemVoteStats");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>debateItemVotesStats</column-name><column-value><![CDATA[");
        sb.append(getDebateItemVotesStats());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>debateItemId</column-name><column-value><![CDATA[");
        sb.append(getDebateItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>votesCount</column-name><column-value><![CDATA[");
        sb.append(getVotesCount());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
