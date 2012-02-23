package com.ext.portlet.ontology.model.impl;

import com.ext.portlet.ontology.model.Category;
import com.ext.portlet.ontology.model.CategorySoap;

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
 * <a href="CategoryModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>Category</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.ontology.model.Category
 * @see com.ext.portlet.ontology.model.CategoryModel
 * @see com.ext.portlet.ontology.model.impl.CategoryImpl
 *
 */
public class CategoryModelImpl extends BaseModelImpl<Category> {
    public static final String TABLE_NAME = "Category";
    public static final Object[][] TABLE_COLUMNS = {
            { "id_", new Integer(Types.BIGINT) },
            

            { "name", new Integer(Types.VARCHAR) }
        };
    public static final String TABLE_SQL_CREATE = "create table Category (id_ LONG not null primary key,name VARCHAR(75) null)";
    public static final String TABLE_SQL_DROP = "drop table Category";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.ontology.model.Category"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.ontology.model.Category"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.ontology.model.Category"));
    private Long _id;
    private String _name;
    private String _originalName;

    public CategoryModelImpl() {
    }

    public static Category toModel(CategorySoap soapModel) {
        Category model = new CategoryImpl();

        model.setId(soapModel.getId());
        model.setName(soapModel.getName());

        return model;
    }

    public static List<Category> toModels(CategorySoap[] soapModels) {
        List<Category> models = new ArrayList<Category>(soapModels.length);

        for (CategorySoap soapModel : soapModels) {
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

        if (_originalName == null) {
            _originalName = name;
        }
    }

    public String getOriginalName() {
        return GetterUtil.getString(_originalName);
    }

    public Category toEscapedModel() {
        if (isEscapedModel()) {
            return (Category) this;
        } else {
            Category model = new CategoryImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setId(getId());
            model.setName(HtmlUtil.escape(getName()));

            model = (Category) Proxy.newProxyInstance(Category.class.getClassLoader(),
                    new Class[] { Category.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        CategoryImpl clone = new CategoryImpl();

        clone.setId(getId());
        clone.setName(getName());

        return clone;
    }

    public int compareTo(Category category) {
        Long pk = category.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        Category category = null;

        try {
            category = (Category) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = category.getPrimaryKey();

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
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.ontology.model.Category");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
