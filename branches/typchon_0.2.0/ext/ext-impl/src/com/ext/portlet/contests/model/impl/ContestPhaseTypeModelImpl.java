package com.ext.portlet.contests.model.impl;

import com.ext.portlet.contests.model.ContestPhaseType;
import com.ext.portlet.contests.model.ContestPhaseTypeSoap;

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
 * <a href="ContestPhaseTypeModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>ContestPhaseType</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.model.ContestPhaseType
 * @see com.ext.portlet.contests.model.ContestPhaseTypeModel
 * @see com.ext.portlet.contests.model.impl.ContestPhaseTypeImpl
 *
 */
public class ContestPhaseTypeModelImpl extends BaseModelImpl<ContestPhaseType> {
    public static final String TABLE_NAME = "ContestPhaseType";
    public static final Object[][] TABLE_COLUMNS = {
            { "id_", new Integer(Types.BIGINT) },
            

            { "name", new Integer(Types.VARCHAR) },
            

            { "description", new Integer(Types.VARCHAR) },
            

            { "status", new Integer(Types.VARCHAR) }
        };
    public static final String TABLE_SQL_CREATE = "create table ContestPhaseType (id_ LONG not null primary key,name VARCHAR(75) null,description VARCHAR(75) null,status VARCHAR(75) null)";
    public static final String TABLE_SQL_DROP = "drop table ContestPhaseType";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.contests.model.ContestPhaseType"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.contests.model.ContestPhaseType"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.contests.model.ContestPhaseType"));
    private Long _id;
    private String _name;
    private String _description;
    private String _status;

    public ContestPhaseTypeModelImpl() {
    }

    public static ContestPhaseType toModel(ContestPhaseTypeSoap soapModel) {
        ContestPhaseType model = new ContestPhaseTypeImpl();

        model.setId(soapModel.getId());
        model.setName(soapModel.getName());
        model.setDescription(soapModel.getDescription());
        model.setStatus(soapModel.getStatus());

        return model;
    }

    public static List<ContestPhaseType> toModels(
        ContestPhaseTypeSoap[] soapModels) {
        List<ContestPhaseType> models = new ArrayList<ContestPhaseType>(soapModels.length);

        for (ContestPhaseTypeSoap soapModel : soapModels) {
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

    public String getStatus() {
        return GetterUtil.getString(_status);
    }

    public void setStatus(String status) {
        _status = status;
    }

    public ContestPhaseType toEscapedModel() {
        if (isEscapedModel()) {
            return (ContestPhaseType) this;
        } else {
            ContestPhaseType model = new ContestPhaseTypeImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setId(getId());
            model.setName(HtmlUtil.escape(getName()));
            model.setDescription(HtmlUtil.escape(getDescription()));
            model.setStatus(HtmlUtil.escape(getStatus()));

            model = (ContestPhaseType) Proxy.newProxyInstance(ContestPhaseType.class.getClassLoader(),
                    new Class[] { ContestPhaseType.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        ContestPhaseTypeImpl clone = new ContestPhaseTypeImpl();

        clone.setId(getId());
        clone.setName(getName());
        clone.setDescription(getDescription());
        clone.setStatus(getStatus());

        return clone;
    }

    public int compareTo(ContestPhaseType contestPhaseType) {
        Long pk = contestPhaseType.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        ContestPhaseType contestPhaseType = null;

        try {
            contestPhaseType = (ContestPhaseType) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = contestPhaseType.getPrimaryKey();

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
        sb.append(", name=");
        sb.append(getName());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", status=");
        sb.append(getStatus());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.contests.model.ContestPhaseType");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>status</column-name><column-value><![CDATA[");
        sb.append(getStatus());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
