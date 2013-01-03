package com.ext.portlet.contests.model.impl;

import com.ext.portlet.contests.model.ContestPhaseStatus;
import com.ext.portlet.contests.model.ContestPhaseStatusSoap;

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
 * <a href="ContestPhaseStatusModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>ContestPhaseStatus</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.model.ContestPhaseStatus
 * @see com.ext.portlet.contests.model.ContestPhaseStatusModel
 * @see com.ext.portlet.contests.model.impl.ContestPhaseStatusImpl
 *
 */
public class ContestPhaseStatusModelImpl extends BaseModelImpl<ContestPhaseStatus> {
    public static final String TABLE_NAME = "ContestPhaseStatus";
    public static final Object[][] TABLE_COLUMNS = {
            { "name", new Integer(Types.VARCHAR) },
            

            { "description", new Integer(Types.VARCHAR) }
        };
    public static final String TABLE_SQL_CREATE = "create table ContestPhaseStatus (name VARCHAR(75) not null primary key,description VARCHAR(75) null)";
    public static final String TABLE_SQL_DROP = "drop table ContestPhaseStatus";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.contests.model.ContestPhaseStatus"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.contests.model.ContestPhaseStatus"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.contests.model.ContestPhaseStatus"));
    private String _name;
    private String _description;

    public ContestPhaseStatusModelImpl() {
    }

    public static ContestPhaseStatus toModel(ContestPhaseStatusSoap soapModel) {
        ContestPhaseStatus model = new ContestPhaseStatusImpl();

        model.setName(soapModel.getName());
        model.setDescription(soapModel.getDescription());

        return model;
    }

    public static List<ContestPhaseStatus> toModels(
        ContestPhaseStatusSoap[] soapModels) {
        List<ContestPhaseStatus> models = new ArrayList<ContestPhaseStatus>(soapModels.length);

        for (ContestPhaseStatusSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public String getPrimaryKey() {
        return _name;
    }

    public void setPrimaryKey(String pk) {
        setName(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _name;
    }

    public String getName() {
        return GetterUtil.getString(_name);
    }

    public void setName(String name) {
        _name = name;
    }

    public String getDescription() {
        return GetterUtil.getString(_description);
    }

    public void setDescription(String description) {
        _description = description;
    }

    public ContestPhaseStatus toEscapedModel() {
        if (isEscapedModel()) {
            return (ContestPhaseStatus) this;
        } else {
            ContestPhaseStatus model = new ContestPhaseStatusImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setName(HtmlUtil.escape(getName()));
            model.setDescription(HtmlUtil.escape(getDescription()));

            model = (ContestPhaseStatus) Proxy.newProxyInstance(ContestPhaseStatus.class.getClassLoader(),
                    new Class[] { ContestPhaseStatus.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        ContestPhaseStatusImpl clone = new ContestPhaseStatusImpl();

        clone.setName(getName());
        clone.setDescription(getDescription());

        return clone;
    }

    public int compareTo(ContestPhaseStatus contestPhaseStatus) {
        String pk = contestPhaseStatus.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        ContestPhaseStatus contestPhaseStatus = null;

        try {
            contestPhaseStatus = (ContestPhaseStatus) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        String pk = contestPhaseStatus.getPrimaryKey();

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

        sb.append("{name=");
        sb.append(getName());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.contests.model.ContestPhaseStatus");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
