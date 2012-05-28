package com.ext.portlet.contests.model.impl;

import com.ext.portlet.contests.model.ContestTeamMember;
import com.ext.portlet.contests.model.ContestTeamMemberSoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="ContestTeamMemberModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>ContestTeamMember</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.model.ContestTeamMember
 * @see com.ext.portlet.contests.model.ContestTeamMemberModel
 * @see com.ext.portlet.contests.model.impl.ContestTeamMemberImpl
 *
 */
public class ContestTeamMemberModelImpl extends BaseModelImpl<ContestTeamMember> {
    public static final String TABLE_NAME = "ContestTeamMember";
    public static final Object[][] TABLE_COLUMNS = {
            { "id_", new Integer(Types.BIGINT) },
            

            { "contestId", new Integer(Types.BIGINT) },
            

            { "userId", new Integer(Types.BIGINT) },
            

            { "role", new Integer(Types.VARCHAR) }
        };
    public static final String TABLE_SQL_CREATE = "create table ContestTeamMember (id_ LONG not null primary key,contestId LONG,userId LONG,role VARCHAR(75) null)";
    public static final String TABLE_SQL_DROP = "drop table ContestTeamMember";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.contests.model.ContestTeamMember"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.contests.model.ContestTeamMember"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.contests.model.ContestTeamMember"));
    private Long _id;
    private Long _contestId;
    private Long _userId;
    private String _role;

    public ContestTeamMemberModelImpl() {
    }

    public static ContestTeamMember toModel(ContestTeamMemberSoap soapModel) {
        ContestTeamMember model = new ContestTeamMemberImpl();

        model.setId(soapModel.getId());
        model.setContestId(soapModel.getContestId());
        model.setUserId(soapModel.getUserId());
        model.setRole(soapModel.getRole());

        return model;
    }

    public static List<ContestTeamMember> toModels(
        ContestTeamMemberSoap[] soapModels) {
        List<ContestTeamMember> models = new ArrayList<ContestTeamMember>(soapModels.length);

        for (ContestTeamMemberSoap soapModel : soapModels) {
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

    public Long getContestId() {
        return _contestId;
    }

    public void setContestId(Long contestId) {
        _contestId = contestId;
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }

    public String getRole() {
        return GetterUtil.getString(_role);
    }

    public void setRole(String role) {
        _role = role;
    }

    public ContestTeamMember toEscapedModel() {
        if (isEscapedModel()) {
            return (ContestTeamMember) this;
        } else {
            ContestTeamMember model = new ContestTeamMemberImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setId(getId());
            model.setContestId(getContestId());
            model.setUserId(getUserId());
            model.setRole(HtmlUtil.escape(getRole()));

            model = (ContestTeamMember) Proxy.newProxyInstance(ContestTeamMember.class.getClassLoader(),
                    new Class[] { ContestTeamMember.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        ContestTeamMemberImpl clone = new ContestTeamMemberImpl();

        clone.setId(getId());
        clone.setContestId(getContestId());
        clone.setUserId(getUserId());
        clone.setRole(getRole());

        return clone;
    }

    public int compareTo(ContestTeamMember contestTeamMember) {
        int value = 0;

        value = getId().compareTo(contestTeamMember.getId());

        if (value != 0) {
            return value;
        }

        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        ContestTeamMember contestTeamMember = null;

        try {
            contestTeamMember = (ContestTeamMember) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = contestTeamMember.getPrimaryKey();

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
        sb.append(", contestId=");
        sb.append(getContestId());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", role=");
        sb.append(getRole());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.contests.model.ContestTeamMember");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contestId</column-name><column-value><![CDATA[");
        sb.append(getContestId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>role</column-name><column-value><![CDATA[");
        sb.append(getRole());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
