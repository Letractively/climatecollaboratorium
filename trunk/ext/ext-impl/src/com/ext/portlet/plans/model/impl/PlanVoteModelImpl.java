package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanVote;
import com.ext.portlet.plans.model.PlanVoteSoap;
import com.ext.portlet.plans.service.persistence.PlanVotePK;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <a href="PlanVoteModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>PlanVote</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanVote
 * @see com.ext.portlet.plans.model.PlanVoteModel
 * @see com.ext.portlet.plans.model.impl.PlanVoteImpl
 *
 */
public class PlanVoteModelImpl extends BaseModelImpl<PlanVote> {
    public static final String TABLE_NAME = "PlanVote";
    public static final Object[][] TABLE_COLUMNS = {
            { "userId", new Integer(Types.BIGINT) },
            

            { "contestId", new Integer(Types.BIGINT) },
            

            { "planId", new Integer(Types.BIGINT) },
            

            { "createDate", new Integer(Types.TIMESTAMP) }
        };
    public static final String TABLE_SQL_CREATE = "create table PlanVote (userId LONG not null,contestId LONG not null,planId LONG,createDate DATE null,primary key (userId, contestId))";
    public static final String TABLE_SQL_DROP = "drop table PlanVote";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.plans.model.PlanVote"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.plans.model.PlanVote"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.plans.model.PlanVote"));
    private Long _userId;
    private Long _originalUserId;
    private Long _contestId;
    private Long _originalContestId;
    private Long _planId;
    private Date _createDate;

    public PlanVoteModelImpl() {
    }

    public static PlanVote toModel(PlanVoteSoap soapModel) {
        PlanVote model = new PlanVoteImpl();

        model.setUserId(soapModel.getUserId());
        model.setContestId(soapModel.getContestId());
        model.setPlanId(soapModel.getPlanId());
        model.setCreateDate(soapModel.getCreateDate());

        return model;
    }

    public static List<PlanVote> toModels(PlanVoteSoap[] soapModels) {
        List<PlanVote> models = new ArrayList<PlanVote>(soapModels.length);

        for (PlanVoteSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public PlanVotePK getPrimaryKey() {
        return new PlanVotePK(_userId, _contestId);
    }

    public void setPrimaryKey(PlanVotePK pk) {
        setUserId(pk.userId);
        setContestId(pk.contestId);
    }

    public Serializable getPrimaryKeyObj() {
        return new PlanVotePK(_userId, _contestId);
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;

        if (_originalUserId == null) {
            _originalUserId = userId;
        }
    }

    public Long getOriginalUserId() {
        return _originalUserId;
    }

    public Long getContestId() {
        return _contestId;
    }

    public void setContestId(Long contestId) {
        _contestId = contestId;

        if (_originalContestId == null) {
            _originalContestId = contestId;
        }
    }

    public Long getOriginalContestId() {
        return _originalContestId;
    }

    public Long getPlanId() {
        return _planId;
    }

    public void setPlanId(Long planId) {
        _planId = planId;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public PlanVote toEscapedModel() {
        if (isEscapedModel()) {
            return (PlanVote) this;
        } else {
            PlanVote model = new PlanVoteImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setUserId(getUserId());
            model.setContestId(getContestId());
            model.setPlanId(getPlanId());
            model.setCreateDate(getCreateDate());

            model = (PlanVote) Proxy.newProxyInstance(PlanVote.class.getClassLoader(),
                    new Class[] { PlanVote.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        PlanVoteImpl clone = new PlanVoteImpl();

        clone.setUserId(getUserId());
        clone.setContestId(getContestId());
        clone.setPlanId(getPlanId());
        clone.setCreateDate(getCreateDate());

        return clone;
    }

    public int compareTo(PlanVote planVote) {
        PlanVotePK pk = planVote.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlanVote planVote = null;

        try {
            planVote = (PlanVote) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        PlanVotePK pk = planVote.getPrimaryKey();

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

        sb.append("{userId=");
        sb.append(getUserId());
        sb.append(", contestId=");
        sb.append(getContestId());
        sb.append(", planId=");
        sb.append(getPlanId());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlanVote");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contestId</column-name><column-value><![CDATA[");
        sb.append(getContestId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planId</column-name><column-value><![CDATA[");
        sb.append(getPlanId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
