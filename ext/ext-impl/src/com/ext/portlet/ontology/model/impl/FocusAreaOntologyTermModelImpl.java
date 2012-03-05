package com.ext.portlet.ontology.model.impl;

import com.ext.portlet.ontology.model.FocusAreaOntologyTerm;
import com.ext.portlet.ontology.model.FocusAreaOntologyTermSoap;
import com.ext.portlet.ontology.service.persistence.FocusAreaOntologyTermPK;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="FocusAreaOntologyTermModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>FocusAreaOntologyTerm</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.ontology.model.FocusAreaOntologyTerm
 * @see com.ext.portlet.ontology.model.FocusAreaOntologyTermModel
 * @see com.ext.portlet.ontology.model.impl.FocusAreaOntologyTermImpl
 *
 */
public class FocusAreaOntologyTermModelImpl extends BaseModelImpl<FocusAreaOntologyTerm> {
    public static final String TABLE_NAME = "FocusAreaOntologyTerm";
    public static final Object[][] TABLE_COLUMNS = {
            { "focusAreaId", new Integer(Types.BIGINT) },
            

            { "ontologyTermId", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table FocusAreaOntologyTerm (focusAreaId LONG not null,ontologyTermId LONG not null,primary key (focusAreaId, ontologyTermId))";
    public static final String TABLE_SQL_DROP = "drop table FocusAreaOntologyTerm";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.ontology.model.FocusAreaOntologyTerm"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.ontology.model.FocusAreaOntologyTerm"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.ontology.model.FocusAreaOntologyTerm"));
    private Long _focusAreaId;
    private Long _ontologyTermId;

    public FocusAreaOntologyTermModelImpl() {
    }

    public static FocusAreaOntologyTerm toModel(
        FocusAreaOntologyTermSoap soapModel) {
        FocusAreaOntologyTerm model = new FocusAreaOntologyTermImpl();

        model.setFocusAreaId(soapModel.getFocusAreaId());
        model.setOntologyTermId(soapModel.getOntologyTermId());

        return model;
    }

    public static List<FocusAreaOntologyTerm> toModels(
        FocusAreaOntologyTermSoap[] soapModels) {
        List<FocusAreaOntologyTerm> models = new ArrayList<FocusAreaOntologyTerm>(soapModels.length);

        for (FocusAreaOntologyTermSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public FocusAreaOntologyTermPK getPrimaryKey() {
        return new FocusAreaOntologyTermPK(_focusAreaId, _ontologyTermId);
    }

    public void setPrimaryKey(FocusAreaOntologyTermPK pk) {
        setFocusAreaId(pk.focusAreaId);
        setOntologyTermId(pk.ontologyTermId);
    }

    public Serializable getPrimaryKeyObj() {
        return new FocusAreaOntologyTermPK(_focusAreaId, _ontologyTermId);
    }

    public Long getFocusAreaId() {
        return _focusAreaId;
    }

    public void setFocusAreaId(Long focusAreaId) {
        _focusAreaId = focusAreaId;
    }

    public Long getOntologyTermId() {
        return _ontologyTermId;
    }

    public void setOntologyTermId(Long ontologyTermId) {
        _ontologyTermId = ontologyTermId;
    }

    public FocusAreaOntologyTerm toEscapedModel() {
        if (isEscapedModel()) {
            return (FocusAreaOntologyTerm) this;
        } else {
            FocusAreaOntologyTerm model = new FocusAreaOntologyTermImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setFocusAreaId(getFocusAreaId());
            model.setOntologyTermId(getOntologyTermId());

            model = (FocusAreaOntologyTerm) Proxy.newProxyInstance(FocusAreaOntologyTerm.class.getClassLoader(),
                    new Class[] { FocusAreaOntologyTerm.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        FocusAreaOntologyTermImpl clone = new FocusAreaOntologyTermImpl();

        clone.setFocusAreaId(getFocusAreaId());
        clone.setOntologyTermId(getOntologyTermId());

        return clone;
    }

    public int compareTo(FocusAreaOntologyTerm focusAreaOntologyTerm) {
        FocusAreaOntologyTermPK pk = focusAreaOntologyTerm.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        FocusAreaOntologyTerm focusAreaOntologyTerm = null;

        try {
            focusAreaOntologyTerm = (FocusAreaOntologyTerm) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        FocusAreaOntologyTermPK pk = focusAreaOntologyTerm.getPrimaryKey();

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

        sb.append("{focusAreaId=");
        sb.append(getFocusAreaId());
        sb.append(", ontologyTermId=");
        sb.append(getOntologyTermId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.ontology.model.FocusAreaOntologyTerm");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>focusAreaId</column-name><column-value><![CDATA[");
        sb.append(getFocusAreaId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ontologyTermId</column-name><column-value><![CDATA[");
        sb.append(getOntologyTermId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
