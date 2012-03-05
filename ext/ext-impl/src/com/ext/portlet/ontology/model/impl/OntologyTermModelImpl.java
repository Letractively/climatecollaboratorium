package com.ext.portlet.ontology.model.impl;

import com.ext.portlet.ontology.model.OntologyTerm;
import com.ext.portlet.ontology.model.OntologyTermSoap;

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
 * <a href="OntologyTermModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>OntologyTerm</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.ontology.model.OntologyTerm
 * @see com.ext.portlet.ontology.model.OntologyTermModel
 * @see com.ext.portlet.ontology.model.impl.OntologyTermImpl
 *
 */
public class OntologyTermModelImpl extends BaseModelImpl<OntologyTerm> {
    public static final String TABLE_NAME = "OntologyTerm";
    public static final Object[][] TABLE_COLUMNS = {
            { "id_", new Integer(Types.BIGINT) },
            

            { "parentId", new Integer(Types.BIGINT) },
            

            { "ontologySpaceId", new Integer(Types.BIGINT) },
            

            { "name", new Integer(Types.VARCHAR) }
        };
    public static final String TABLE_SQL_CREATE = "create table OntologyTerm (id_ LONG not null primary key,parentId LONG,ontologySpaceId LONG,name VARCHAR(75) null)";
    public static final String TABLE_SQL_DROP = "drop table OntologyTerm";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.ontology.model.OntologyTerm"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.ontology.model.OntologyTerm"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.ontology.model.OntologyTerm"));
    private Long _id;
    private Long _parentId;
    private Long _ontologySpaceId;
    private String _name;

    public OntologyTermModelImpl() {
    }

    public static OntologyTerm toModel(OntologyTermSoap soapModel) {
        OntologyTerm model = new OntologyTermImpl();

        model.setId(soapModel.getId());
        model.setParentId(soapModel.getParentId());
        model.setOntologySpaceId(soapModel.getOntologySpaceId());
        model.setName(soapModel.getName());

        return model;
    }

    public static List<OntologyTerm> toModels(OntologyTermSoap[] soapModels) {
        List<OntologyTerm> models = new ArrayList<OntologyTerm>(soapModels.length);

        for (OntologyTermSoap soapModel : soapModels) {
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

    public Long getParentId() {
        return _parentId;
    }

    public void setParentId(Long parentId) {
        _parentId = parentId;
    }

    public Long getOntologySpaceId() {
        return _ontologySpaceId;
    }

    public void setOntologySpaceId(Long ontologySpaceId) {
        _ontologySpaceId = ontologySpaceId;
    }

    public String getName() {
        return GetterUtil.getString(_name);
    }

    public void setName(String name) {
        _name = name;
    }

    public OntologyTerm toEscapedModel() {
        if (isEscapedModel()) {
            return (OntologyTerm) this;
        } else {
            OntologyTerm model = new OntologyTermImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setId(getId());
            model.setParentId(getParentId());
            model.setOntologySpaceId(getOntologySpaceId());
            model.setName(HtmlUtil.escape(getName()));

            model = (OntologyTerm) Proxy.newProxyInstance(OntologyTerm.class.getClassLoader(),
                    new Class[] { OntologyTerm.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        OntologyTermImpl clone = new OntologyTermImpl();

        clone.setId(getId());
        clone.setParentId(getParentId());
        clone.setOntologySpaceId(getOntologySpaceId());
        clone.setName(getName());

        return clone;
    }

    public int compareTo(OntologyTerm ontologyTerm) {
        Long pk = ontologyTerm.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        OntologyTerm ontologyTerm = null;

        try {
            ontologyTerm = (OntologyTerm) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = ontologyTerm.getPrimaryKey();

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
        sb.append(", parentId=");
        sb.append(getParentId());
        sb.append(", ontologySpaceId=");
        sb.append(getOntologySpaceId());
        sb.append(", name=");
        sb.append(getName());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.ontology.model.OntologyTerm");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parentId</column-name><column-value><![CDATA[");
        sb.append(getParentId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ontologySpaceId</column-name><column-value><![CDATA[");
        sb.append(getOntologySpaceId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
