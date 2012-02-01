package com.ext.portlet.ontology.model.impl;

import com.ext.portlet.ontology.model.OntologyTermEntity;
import com.ext.portlet.ontology.model.OntologyTermEntitySoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="OntologyTermEntityModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>OntologyTermEntity</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.ontology.model.OntologyTermEntity
 * @see com.ext.portlet.ontology.model.OntologyTermEntityModel
 * @see com.ext.portlet.ontology.model.impl.OntologyTermEntityImpl
 *
 */
public class OntologyTermEntityModelImpl extends BaseModelImpl<OntologyTermEntity> {
    public static final String TABLE_NAME = "OntologyTermEntity";
    public static final Object[][] TABLE_COLUMNS = {
            { "id_", new Integer(Types.BIGINT) },
            

            { "termId", new Integer(Types.BIGINT) },
            

            { "classNameId", new Integer(Types.BIGINT) },
            

            { "classPK", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table OntologyTermEntity (id_ LONG not null primary key,termId LONG,classNameId LONG,classPK LONG)";
    public static final String TABLE_SQL_DROP = "drop table OntologyTermEntity";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.ontology.model.OntologyTermEntity"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.ontology.model.OntologyTermEntity"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.ontology.model.OntologyTermEntity"));
    private Long _id;
    private Long _termId;
    private Long _classNameId;
    private Long _classPK;

    public OntologyTermEntityModelImpl() {
    }

    public static OntologyTermEntity toModel(OntologyTermEntitySoap soapModel) {
        OntologyTermEntity model = new OntologyTermEntityImpl();

        model.setId(soapModel.getId());
        model.setTermId(soapModel.getTermId());
        model.setClassNameId(soapModel.getClassNameId());
        model.setClassPK(soapModel.getClassPK());

        return model;
    }

    public static List<OntologyTermEntity> toModels(
        OntologyTermEntitySoap[] soapModels) {
        List<OntologyTermEntity> models = new ArrayList<OntologyTermEntity>(soapModels.length);

        for (OntologyTermEntitySoap soapModel : soapModels) {
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

    public Long getTermId() {
        return _termId;
    }

    public void setTermId(Long termId) {
        _termId = termId;
    }

    public String getClassName() {
        if (getClassNameId() <= 0) {
            return StringPool.BLANK;
        }

        return PortalUtil.getClassName(getClassNameId());
    }

    public Long getClassNameId() {
        return _classNameId;
    }

    public void setClassNameId(Long classNameId) {
        _classNameId = classNameId;
    }

    public Long getClassPK() {
        return _classPK;
    }

    public void setClassPK(Long classPK) {
        _classPK = classPK;
    }

    public OntologyTermEntity toEscapedModel() {
        if (isEscapedModel()) {
            return (OntologyTermEntity) this;
        } else {
            OntologyTermEntity model = new OntologyTermEntityImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setId(getId());
            model.setTermId(getTermId());
            model.setClassNameId(getClassNameId());
            model.setClassPK(getClassPK());

            model = (OntologyTermEntity) Proxy.newProxyInstance(OntologyTermEntity.class.getClassLoader(),
                    new Class[] { OntologyTermEntity.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        OntologyTermEntityImpl clone = new OntologyTermEntityImpl();

        clone.setId(getId());
        clone.setTermId(getTermId());
        clone.setClassNameId(getClassNameId());
        clone.setClassPK(getClassPK());

        return clone;
    }

    public int compareTo(OntologyTermEntity ontologyTermEntity) {
        Long pk = ontologyTermEntity.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        OntologyTermEntity ontologyTermEntity = null;

        try {
            ontologyTermEntity = (OntologyTermEntity) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = ontologyTermEntity.getPrimaryKey();

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
        sb.append(", termId=");
        sb.append(getTermId());
        sb.append(", classNameId=");
        sb.append(getClassNameId());
        sb.append(", classPK=");
        sb.append(getClassPK());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.ontology.model.OntologyTermEntity");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>termId</column-name><column-value><![CDATA[");
        sb.append(getTermId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>classNameId</column-name><column-value><![CDATA[");
        sb.append(getClassNameId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>classPK</column-name><column-value><![CDATA[");
        sb.append(getClassPK());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
