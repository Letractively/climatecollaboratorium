package com.ext.portlet.contests.model.impl;

import com.ext.portlet.contests.model.ContestPhase;
import com.ext.portlet.contests.model.ContestPhaseSoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <a href="ContestPhaseModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>ContestPhase</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.model.ContestPhase
 * @see com.ext.portlet.contests.model.ContestPhaseModel
 * @see com.ext.portlet.contests.model.impl.ContestPhaseImpl
 *
 */
public class ContestPhaseModelImpl extends BaseModelImpl<ContestPhase> {
    public static final String TABLE_NAME = "ContestPhase";
    public static final Object[][] TABLE_COLUMNS = {
            { "ContestPhasePK", new Integer(Types.BIGINT) },
            

            { "ContestPK", new Integer(Types.BIGINT) },
            

            { "ContestPhaseType", new Integer(Types.BIGINT) },
            

            { "ContestPhaseDescriptionOverride", new Integer(Types.VARCHAR) },
            

            { "phaseActiveOverride", new Integer(Types.BOOLEAN) },
            

            { "PhaseStartDate", new Integer(Types.TIMESTAMP) },
            

            { "PhaseEndDate", new Integer(Types.TIMESTAMP) },
            

            { "nextStatus", new Integer(Types.VARCHAR) },
            

            { "created", new Integer(Types.TIMESTAMP) },
            

            { "updated", new Integer(Types.TIMESTAMP) },
            

            { "authorId", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table ContestPhase (ContestPhasePK LONG not null primary key,ContestPK LONG,ContestPhaseType LONG,ContestPhaseDescriptionOverride VARCHAR(75) null,phaseActiveOverride BOOLEAN,PhaseStartDate DATE null,PhaseEndDate DATE null,nextStatus VARCHAR(75) null,created DATE null,updated DATE null,authorId LONG)";
    public static final String TABLE_SQL_DROP = "drop table ContestPhase";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.contests.model.ContestPhase"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.contests.model.ContestPhase"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.contests.model.ContestPhase"));
    private Long _ContestPhasePK;
    private Long _ContestPK;
    private Long _originalContestPK;
    private Long _ContestPhaseType;
    private String _ContestPhaseDescriptionOverride;
    private Boolean _phaseActiveOverride;
    private Date _PhaseStartDate;
    private Date _originalPhaseStartDate;
    private Date _PhaseEndDate;
    private Date _originalPhaseEndDate;
    private String _nextStatus;
    private Date _created;
    private Date _updated;
    private Long _authorId;

    public ContestPhaseModelImpl() {
    }

    public static ContestPhase toModel(ContestPhaseSoap soapModel) {
        ContestPhase model = new ContestPhaseImpl();

        model.setContestPhasePK(soapModel.getContestPhasePK());
        model.setContestPK(soapModel.getContestPK());
        model.setContestPhaseType(soapModel.getContestPhaseType());
        model.setContestPhaseDescriptionOverride(soapModel.getContestPhaseDescriptionOverride());
        model.setPhaseActiveOverride(soapModel.getPhaseActiveOverride());
        model.setPhaseStartDate(soapModel.getPhaseStartDate());
        model.setPhaseEndDate(soapModel.getPhaseEndDate());
        model.setNextStatus(soapModel.getNextStatus());
        model.setCreated(soapModel.getCreated());
        model.setUpdated(soapModel.getUpdated());
        model.setAuthorId(soapModel.getAuthorId());

        return model;
    }

    public static List<ContestPhase> toModels(ContestPhaseSoap[] soapModels) {
        List<ContestPhase> models = new ArrayList<ContestPhase>(soapModels.length);

        for (ContestPhaseSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _ContestPhasePK;
    }

    public void setPrimaryKey(Long pk) {
        setContestPhasePK(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _ContestPhasePK;
    }

    public Long getContestPhasePK() {
        return _ContestPhasePK;
    }

    public void setContestPhasePK(Long ContestPhasePK) {
        _ContestPhasePK = ContestPhasePK;
    }

    public Long getContestPK() {
        return _ContestPK;
    }

    public void setContestPK(Long ContestPK) {
        _ContestPK = ContestPK;

        if (_originalContestPK == null) {
            _originalContestPK = ContestPK;
        }
    }

    public Long getOriginalContestPK() {
        return _originalContestPK;
    }

    public Long getContestPhaseType() {
        return _ContestPhaseType;
    }

    public void setContestPhaseType(Long ContestPhaseType) {
        _ContestPhaseType = ContestPhaseType;
    }

    public String getContestPhaseDescriptionOverride() {
        return GetterUtil.getString(_ContestPhaseDescriptionOverride);
    }

    public void setContestPhaseDescriptionOverride(
        String ContestPhaseDescriptionOverride) {
        _ContestPhaseDescriptionOverride = ContestPhaseDescriptionOverride;
    }

    public Boolean getPhaseActiveOverride() {
        return _phaseActiveOverride;
    }

    public void setPhaseActiveOverride(Boolean phaseActiveOverride) {
        _phaseActiveOverride = phaseActiveOverride;
    }

    public Date getPhaseStartDate() {
        return _PhaseStartDate;
    }

    public void setPhaseStartDate(Date PhaseStartDate) {
        _PhaseStartDate = PhaseStartDate;

        if (_originalPhaseStartDate == null) {
            _originalPhaseStartDate = PhaseStartDate;
        }
    }

    public Date getOriginalPhaseStartDate() {
        return _originalPhaseStartDate;
    }

    public Date getPhaseEndDate() {
        return _PhaseEndDate;
    }

    public void setPhaseEndDate(Date PhaseEndDate) {
        _PhaseEndDate = PhaseEndDate;

        if (_originalPhaseEndDate == null) {
            _originalPhaseEndDate = PhaseEndDate;
        }
    }

    public Date getOriginalPhaseEndDate() {
        return _originalPhaseEndDate;
    }

    public String getNextStatus() {
        return GetterUtil.getString(_nextStatus);
    }

    public void setNextStatus(String nextStatus) {
        _nextStatus = nextStatus;
    }

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        _created = created;
    }

    public Date getUpdated() {
        return _updated;
    }

    public void setUpdated(Date updated) {
        _updated = updated;
    }

    public Long getAuthorId() {
        return _authorId;
    }

    public void setAuthorId(Long authorId) {
        _authorId = authorId;
    }

    public ContestPhase toEscapedModel() {
        if (isEscapedModel()) {
            return (ContestPhase) this;
        } else {
            ContestPhase model = new ContestPhaseImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setContestPhasePK(getContestPhasePK());
            model.setContestPK(getContestPK());
            model.setContestPhaseType(getContestPhaseType());
            model.setContestPhaseDescriptionOverride(HtmlUtil.escape(
                    getContestPhaseDescriptionOverride()));
            model.setPhaseActiveOverride(getPhaseActiveOverride());
            model.setPhaseStartDate(getPhaseStartDate());
            model.setPhaseEndDate(getPhaseEndDate());
            model.setNextStatus(HtmlUtil.escape(getNextStatus()));
            model.setCreated(getCreated());
            model.setUpdated(getUpdated());
            model.setAuthorId(getAuthorId());

            model = (ContestPhase) Proxy.newProxyInstance(ContestPhase.class.getClassLoader(),
                    new Class[] { ContestPhase.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        ContestPhaseImpl clone = new ContestPhaseImpl();

        clone.setContestPhasePK(getContestPhasePK());
        clone.setContestPK(getContestPK());
        clone.setContestPhaseType(getContestPhaseType());
        clone.setContestPhaseDescriptionOverride(getContestPhaseDescriptionOverride());
        clone.setPhaseActiveOverride(getPhaseActiveOverride());
        clone.setPhaseStartDate(getPhaseStartDate());
        clone.setPhaseEndDate(getPhaseEndDate());
        clone.setNextStatus(getNextStatus());
        clone.setCreated(getCreated());
        clone.setUpdated(getUpdated());
        clone.setAuthorId(getAuthorId());

        return clone;
    }

    public int compareTo(ContestPhase contestPhase) {
        int value = 0;

        value = DateUtil.compareTo(getPhaseStartDate(),
                contestPhase.getPhaseStartDate());

        if (value != 0) {
            return value;
        }

        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        ContestPhase contestPhase = null;

        try {
            contestPhase = (ContestPhase) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = contestPhase.getPrimaryKey();

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

        sb.append("{ContestPhasePK=");
        sb.append(getContestPhasePK());
        sb.append(", ContestPK=");
        sb.append(getContestPK());
        sb.append(", ContestPhaseType=");
        sb.append(getContestPhaseType());
        sb.append(", ContestPhaseDescriptionOverride=");
        sb.append(getContestPhaseDescriptionOverride());
        sb.append(", phaseActiveOverride=");
        sb.append(getPhaseActiveOverride());
        sb.append(", PhaseStartDate=");
        sb.append(getPhaseStartDate());
        sb.append(", PhaseEndDate=");
        sb.append(getPhaseEndDate());
        sb.append(", nextStatus=");
        sb.append(getNextStatus());
        sb.append(", created=");
        sb.append(getCreated());
        sb.append(", updated=");
        sb.append(getUpdated());
        sb.append(", authorId=");
        sb.append(getAuthorId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.contests.model.ContestPhase");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>ContestPhasePK</column-name><column-value><![CDATA[");
        sb.append(getContestPhasePK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ContestPK</column-name><column-value><![CDATA[");
        sb.append(getContestPK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ContestPhaseType</column-name><column-value><![CDATA[");
        sb.append(getContestPhaseType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ContestPhaseDescriptionOverride</column-name><column-value><![CDATA[");
        sb.append(getContestPhaseDescriptionOverride());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>phaseActiveOverride</column-name><column-value><![CDATA[");
        sb.append(getPhaseActiveOverride());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>PhaseStartDate</column-name><column-value><![CDATA[");
        sb.append(getPhaseStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>PhaseEndDate</column-name><column-value><![CDATA[");
        sb.append(getPhaseEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>nextStatus</column-name><column-value><![CDATA[");
        sb.append(getNextStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>created</column-name><column-value><![CDATA[");
        sb.append(getCreated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updated</column-name><column-value><![CDATA[");
        sb.append(getUpdated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>authorId</column-name><column-value><![CDATA[");
        sb.append(getAuthorId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
