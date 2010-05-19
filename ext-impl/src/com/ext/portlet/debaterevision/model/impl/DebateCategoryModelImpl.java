package com.ext.portlet.debaterevision.model.impl;

import com.ext.portlet.debaterevision.model.DebateCategory;
import com.ext.portlet.debaterevision.model.DebateCategorySoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
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
 * <a href="DebateCategoryModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>DebateCategory</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.model.DebateCategory
 * @see com.ext.portlet.debaterevision.model.DebateCategoryModel
 * @see com.ext.portlet.debaterevision.model.impl.DebateCategoryImpl
 *
 */
public class DebateCategoryModelImpl extends BaseModelImpl<DebateCategory> {
    public static final String TABLE_NAME = "DebateCategory";
    public static final Object[][] TABLE_COLUMNS = {
            { "debateCategoryPK", new Integer(Types.BIGINT) },
            

            { "authorId", new Integer(Types.BIGINT) },
            

            { "title", new Integer(Types.VARCHAR) },
            

            { "brandingContentId", new Integer(Types.BIGINT) },
            

            { "description", new Integer(Types.VARCHAR) },
            

            { "parentCategory", new Integer(Types.BIGINT) },
            

            { "updated", new Integer(Types.TIMESTAMP) }
        };
    public static final String TABLE_SQL_CREATE = "create table DebateCategory (debateCategoryPK LONG not null primary key,authorId LONG,title VARCHAR(75) null,brandingContentId LONG,description VARCHAR(75) null,parentCategory LONG,updated DATE null)";
    public static final String TABLE_SQL_DROP = "drop table DebateCategory";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.debaterevision.model.DebateCategory"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.debaterevision.model.DebateCategory"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.debaterevision.model.DebateCategory"));
    private Long _debateCategoryPK;
    private Long _authorId;
    private String _title;
    private Long _brandingContentId;
    private String _description;
    private Long _parentCategory;
    private Date _updated;

    public DebateCategoryModelImpl() {
    }

    public static DebateCategory toModel(DebateCategorySoap soapModel) {
        DebateCategory model = new DebateCategoryImpl();

        model.setDebateCategoryPK(soapModel.getDebateCategoryPK());
        model.setAuthorId(soapModel.getAuthorId());
        model.setTitle(soapModel.getTitle());
        model.setBrandingContentId(soapModel.getBrandingContentId());
        model.setDescription(soapModel.getDescription());
        model.setParentCategory(soapModel.getParentCategory());
        model.setUpdated(soapModel.getUpdated());

        return model;
    }

    public static List<DebateCategory> toModels(DebateCategorySoap[] soapModels) {
        List<DebateCategory> models = new ArrayList<DebateCategory>(soapModels.length);

        for (DebateCategorySoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _debateCategoryPK;
    }

    public void setPrimaryKey(Long pk) {
        setDebateCategoryPK(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _debateCategoryPK;
    }

    public Long getDebateCategoryPK() {
        return _debateCategoryPK;
    }

    public void setDebateCategoryPK(Long debateCategoryPK) {
        _debateCategoryPK = debateCategoryPK;
    }

    public Long getAuthorId() {
        return _authorId;
    }

    public void setAuthorId(Long authorId) {
        _authorId = authorId;
    }

    public String getTitle() {
        return GetterUtil.getString(_title);
    }

    public void setTitle(String title) {
        _title = title;
    }

    public Long getBrandingContentId() {
        return _brandingContentId;
    }

    public void setBrandingContentId(Long brandingContentId) {
        _brandingContentId = brandingContentId;
    }

    public String getDescription() {
        return GetterUtil.getString(_description);
    }

    public void setDescription(String description) {
        _description = description;
    }

    public Long getParentCategory() {
        return _parentCategory;
    }

    public void setParentCategory(Long parentCategory) {
        _parentCategory = parentCategory;
    }

    public Date getUpdated() {
        return _updated;
    }

    public void setUpdated(Date updated) {
        _updated = updated;
    }

    public DebateCategory toEscapedModel() {
        if (isEscapedModel()) {
            return (DebateCategory) this;
        } else {
            DebateCategory model = new DebateCategoryImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setDebateCategoryPK(getDebateCategoryPK());
            model.setAuthorId(getAuthorId());
            model.setTitle(HtmlUtil.escape(getTitle()));
            model.setBrandingContentId(getBrandingContentId());
            model.setDescription(HtmlUtil.escape(getDescription()));
            model.setParentCategory(getParentCategory());
            model.setUpdated(getUpdated());

            model = (DebateCategory) Proxy.newProxyInstance(DebateCategory.class.getClassLoader(),
                    new Class[] { DebateCategory.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        DebateCategoryImpl clone = new DebateCategoryImpl();

        clone.setDebateCategoryPK(getDebateCategoryPK());
        clone.setAuthorId(getAuthorId());
        clone.setTitle(getTitle());
        clone.setBrandingContentId(getBrandingContentId());
        clone.setDescription(getDescription());
        clone.setParentCategory(getParentCategory());
        clone.setUpdated(getUpdated());

        return clone;
    }

    public int compareTo(DebateCategory debateCategory) {
        Long pk = debateCategory.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        DebateCategory debateCategory = null;

        try {
            debateCategory = (DebateCategory) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = debateCategory.getPrimaryKey();

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

        sb.append("{debateCategoryPK=");
        sb.append(getDebateCategoryPK());
        sb.append(", authorId=");
        sb.append(getAuthorId());
        sb.append(", title=");
        sb.append(getTitle());
        sb.append(", brandingContentId=");
        sb.append(getBrandingContentId());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", parentCategory=");
        sb.append(getParentCategory());
        sb.append(", updated=");
        sb.append(getUpdated());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.debaterevision.model.DebateCategory");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>debateCategoryPK</column-name><column-value><![CDATA[");
        sb.append(getDebateCategoryPK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>authorId</column-name><column-value><![CDATA[");
        sb.append(getAuthorId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>title</column-name><column-value><![CDATA[");
        sb.append(getTitle());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brandingContentId</column-name><column-value><![CDATA[");
        sb.append(getBrandingContentId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parentCategory</column-name><column-value><![CDATA[");
        sb.append(getParentCategory());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updated</column-name><column-value><![CDATA[");
        sb.append(getUpdated());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
