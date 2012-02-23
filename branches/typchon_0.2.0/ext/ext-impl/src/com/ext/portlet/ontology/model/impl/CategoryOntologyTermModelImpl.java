package com.ext.portlet.ontology.model.impl;

import com.ext.portlet.ontology.model.CategoryOntologyTerm;
import com.ext.portlet.ontology.model.CategoryOntologyTermSoap;
import com.ext.portlet.ontology.service.persistence.CategoryOntologyTermPK;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="CategoryOntologyTermModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>CategoryOntologyTerm</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.ontology.model.CategoryOntologyTerm
 * @see com.ext.portlet.ontology.model.CategoryOntologyTermModel
 * @see com.ext.portlet.ontology.model.impl.CategoryOntologyTermImpl
 *
 */
public class CategoryOntologyTermModelImpl extends BaseModelImpl<CategoryOntologyTerm> {
    public static final String TABLE_NAME = "CategoryOntologyTerm";
    public static final Object[][] TABLE_COLUMNS = {
            { "categoryId", new Integer(Types.BIGINT) },
            

            { "ontologyTerm", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table CategoryOntologyTerm (categoryId LONG not null,ontologyTerm LONG not null,primary key (categoryId, ontologyTerm))";
    public static final String TABLE_SQL_DROP = "drop table CategoryOntologyTerm";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.ontology.model.CategoryOntologyTerm"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.ontology.model.CategoryOntologyTerm"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.ontology.model.CategoryOntologyTerm"));
    private Long _categoryId;
    private Long _ontologyTerm;

    public CategoryOntologyTermModelImpl() {
    }

    public static CategoryOntologyTerm toModel(
        CategoryOntologyTermSoap soapModel) {
        CategoryOntologyTerm model = new CategoryOntologyTermImpl();

        model.setCategoryId(soapModel.getCategoryId());
        model.setOntologyTerm(soapModel.getOntologyTerm());

        return model;
    }

    public static List<CategoryOntologyTerm> toModels(
        CategoryOntologyTermSoap[] soapModels) {
        List<CategoryOntologyTerm> models = new ArrayList<CategoryOntologyTerm>(soapModels.length);

        for (CategoryOntologyTermSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public CategoryOntologyTermPK getPrimaryKey() {
        return new CategoryOntologyTermPK(_categoryId, _ontologyTerm);
    }

    public void setPrimaryKey(CategoryOntologyTermPK pk) {
        setCategoryId(pk.categoryId);
        setOntologyTerm(pk.ontologyTerm);
    }

    public Serializable getPrimaryKeyObj() {
        return new CategoryOntologyTermPK(_categoryId, _ontologyTerm);
    }

    public Long getCategoryId() {
        return _categoryId;
    }

    public void setCategoryId(Long categoryId) {
        _categoryId = categoryId;
    }

    public Long getOntologyTerm() {
        return _ontologyTerm;
    }

    public void setOntologyTerm(Long ontologyTerm) {
        _ontologyTerm = ontologyTerm;
    }

    public CategoryOntologyTerm toEscapedModel() {
        if (isEscapedModel()) {
            return (CategoryOntologyTerm) this;
        } else {
            CategoryOntologyTerm model = new CategoryOntologyTermImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setCategoryId(getCategoryId());
            model.setOntologyTerm(getOntologyTerm());

            model = (CategoryOntologyTerm) Proxy.newProxyInstance(CategoryOntologyTerm.class.getClassLoader(),
                    new Class[] { CategoryOntologyTerm.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        CategoryOntologyTermImpl clone = new CategoryOntologyTermImpl();

        clone.setCategoryId(getCategoryId());
        clone.setOntologyTerm(getOntologyTerm());

        return clone;
    }

    public int compareTo(CategoryOntologyTerm categoryOntologyTerm) {
        CategoryOntologyTermPK pk = categoryOntologyTerm.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        CategoryOntologyTerm categoryOntologyTerm = null;

        try {
            categoryOntologyTerm = (CategoryOntologyTerm) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        CategoryOntologyTermPK pk = categoryOntologyTerm.getPrimaryKey();

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

        sb.append("{categoryId=");
        sb.append(getCategoryId());
        sb.append(", ontologyTerm=");
        sb.append(getOntologyTerm());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.ontology.model.CategoryOntologyTerm");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>categoryId</column-name><column-value><![CDATA[");
        sb.append(getCategoryId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ontologyTerm</column-name><column-value><![CDATA[");
        sb.append(getOntologyTerm());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
