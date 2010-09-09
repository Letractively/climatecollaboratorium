package com.ext.portlet.contests.model.impl;

import com.ext.portlet.contests.model.ContestDebate;
import com.ext.portlet.contests.model.ContestDebateSoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="ContestDebateModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>ContestDebate</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.model.ContestDebate
 * @see com.ext.portlet.contests.model.ContestDebateModel
 * @see com.ext.portlet.contests.model.impl.ContestDebateImpl
 *
 */
public class ContestDebateModelImpl extends BaseModelImpl<ContestDebate> {
    public static final String TABLE_NAME = "ContestDebate";
    public static final Object[][] TABLE_COLUMNS = {
            { "id_", new Integer(Types.BIGINT) },
            

            { "debateId", new Integer(Types.BIGINT) },
            

            { "ContestPK", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table ContestDebate (id_ LONG not null primary key,debateId LONG,ContestPK LONG)";
    public static final String TABLE_SQL_DROP = "drop table ContestDebate";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.contests.model.ContestDebate"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.contests.model.ContestDebate"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.contests.model.ContestDebate"));
    private Long _id;
    private Long _debateId;
    private Long _ContestPK;

    public ContestDebateModelImpl() {
    }

    public static ContestDebate toModel(ContestDebateSoap soapModel) {
        ContestDebate model = new ContestDebateImpl();

        model.setId(soapModel.getId());
        model.setDebateId(soapModel.getDebateId());
        model.setContestPK(soapModel.getContestPK());

        return model;
    }

    public static List<ContestDebate> toModels(ContestDebateSoap[] soapModels) {
        List<ContestDebate> models = new ArrayList<ContestDebate>(soapModels.length);

        for (ContestDebateSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _id;
    }

    public void setPrimaryKey(Long pk) {
        setId(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _id;
    }

    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        _id = id;
    }

    public Long getDebateId() {
        return _debateId;
    }

    public void setDebateId(Long debateId) {
        _debateId = debateId;
    }

    public Long getContestPK() {
        return _ContestPK;
    }

    public void setContestPK(Long ContestPK) {
        _ContestPK = ContestPK;
    }

    public ContestDebate toEscapedModel() {
        if (isEscapedModel()) {
            return (ContestDebate) this;
        } else {
            ContestDebate model = new ContestDebateImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setId(getId());
            model.setDebateId(getDebateId());
            model.setContestPK(getContestPK());

            model = (ContestDebate) Proxy.newProxyInstance(ContestDebate.class.getClassLoader(),
                    new Class[] { ContestDebate.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        ContestDebateImpl clone = new ContestDebateImpl();

        clone.setId(getId());
        clone.setDebateId(getDebateId());
        clone.setContestPK(getContestPK());

        return clone;
    }

    public int compareTo(ContestDebate contestDebate) {
        Long pk = contestDebate.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        ContestDebate contestDebate = null;

        try {
            contestDebate = (ContestDebate) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = contestDebate.getPrimaryKey();

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

        sb.append("{id=");
        sb.append(getId());
        sb.append(", debateId=");
        sb.append(getDebateId());
        sb.append(", ContestPK=");
        sb.append(getContestPK());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.contests.model.ContestDebate");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>debateId</column-name><column-value><![CDATA[");
        sb.append(getDebateId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ContestPK</column-name><column-value><![CDATA[");
        sb.append(getContestPK());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
