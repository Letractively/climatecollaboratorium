package com.ext.portlet.discussions.model.impl;

import com.ext.portlet.discussions.model.DiscussionCategory;
import com.ext.portlet.discussions.model.DiscussionCategorySoap;

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
 * <a href="DiscussionCategoryModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>DiscussionCategory</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.discussions.model.DiscussionCategory
 * @see com.ext.portlet.discussions.model.DiscussionCategoryModel
 * @see com.ext.portlet.discussions.model.impl.DiscussionCategoryImpl
 *
 */
public class DiscussionCategoryModelImpl extends BaseModelImpl<DiscussionCategory> {
    public static final String TABLE_NAME = "DiscussionCategory";
    public static final Object[][] TABLE_COLUMNS = {
            { "pk", new Integer(Types.BIGINT) },
            

            { "categoryId", new Integer(Types.BIGINT) },
            

            { "categoryGroupId", new Integer(Types.BIGINT) },
            

            { "authorId", new Integer(Types.BIGINT) },
            

            { "name", new Integer(Types.VARCHAR) },
            

            { "description", new Integer(Types.VARCHAR) },
            

            { "createDate", new Integer(Types.TIMESTAMP) },
            

            { "deleted", new Integer(Types.TIMESTAMP) },
            

            { "threadsCount", new Integer(Types.INTEGER) },
            

            { "lastActivityDate", new Integer(Types.TIMESTAMP) },
            

            { "lastActivityAuthorId", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table DiscussionCategory (pk LONG not null primary key,categoryId LONG,categoryGroupId LONG,authorId LONG,name VARCHAR(75) null,description VARCHAR(75) null,createDate DATE null,deleted DATE null,threadsCount INTEGER,lastActivityDate DATE null,lastActivityAuthorId LONG)";
    public static final String TABLE_SQL_DROP = "drop table DiscussionCategory";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.discussions.model.DiscussionCategory"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.discussions.model.DiscussionCategory"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.discussions.model.DiscussionCategory"));
    private Long _pk;
    private Long _categoryId;
    private Long _originalCategoryId;
    private Long _categoryGroupId;
    private Long _authorId;
    private String _name;
    private String _description;
    private Date _createDate;
    private Date _deleted;
    private Integer _threadsCount;
    private Date _lastActivityDate;
    private Long _lastActivityAuthorId;

    public DiscussionCategoryModelImpl() {
    }

    public static DiscussionCategory toModel(DiscussionCategorySoap soapModel) {
        DiscussionCategory model = new DiscussionCategoryImpl();

        model.setPk(soapModel.getPk());
        model.setCategoryId(soapModel.getCategoryId());
        model.setCategoryGroupId(soapModel.getCategoryGroupId());
        model.setAuthorId(soapModel.getAuthorId());
        model.setName(soapModel.getName());
        model.setDescription(soapModel.getDescription());
        model.setCreateDate(soapModel.getCreateDate());
        model.setDeleted(soapModel.getDeleted());
        model.setThreadsCount(soapModel.getThreadsCount());
        model.setLastActivityDate(soapModel.getLastActivityDate());
        model.setLastActivityAuthorId(soapModel.getLastActivityAuthorId());

        return model;
    }

    public static List<DiscussionCategory> toModels(
        DiscussionCategorySoap[] soapModels) {
        List<DiscussionCategory> models = new ArrayList<DiscussionCategory>(soapModels.length);

        for (DiscussionCategorySoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _pk;
    }

    public void setPrimaryKey(Long pk) {
        setPk(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _pk;
    }

    public Long getPk() {
        return _pk;
    }

    public void setPk(Long pk) {
        _pk = pk;
    }

    public Long getCategoryId() {
        return _categoryId;
    }

    public void setCategoryId(Long categoryId) {
        _categoryId = categoryId;

        if (_originalCategoryId == null) {
            _originalCategoryId = categoryId;
        }
    }

    public Long getOriginalCategoryId() {
        return _originalCategoryId;
    }

    public Long getCategoryGroupId() {
        return _categoryGroupId;
    }

    public void setCategoryGroupId(Long categoryGroupId) {
        _categoryGroupId = categoryGroupId;
    }

    public Long getAuthorId() {
        return _authorId;
    }

    public void setAuthorId(Long authorId) {
        _authorId = authorId;
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

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public Date getDeleted() {
        return _deleted;
    }

    public void setDeleted(Date deleted) {
        _deleted = deleted;
    }

    public Integer getThreadsCount() {
        return _threadsCount;
    }

    public void setThreadsCount(Integer threadsCount) {
        _threadsCount = threadsCount;
    }

    public Date getLastActivityDate() {
        return _lastActivityDate;
    }

    public void setLastActivityDate(Date lastActivityDate) {
        _lastActivityDate = lastActivityDate;
    }

    public Long getLastActivityAuthorId() {
        return _lastActivityAuthorId;
    }

    public void setLastActivityAuthorId(Long lastActivityAuthorId) {
        _lastActivityAuthorId = lastActivityAuthorId;
    }

    public DiscussionCategory toEscapedModel() {
        if (isEscapedModel()) {
            return (DiscussionCategory) this;
        } else {
            DiscussionCategory model = new DiscussionCategoryImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setPk(getPk());
            model.setCategoryId(getCategoryId());
            model.setCategoryGroupId(getCategoryGroupId());
            model.setAuthorId(getAuthorId());
            model.setName(HtmlUtil.escape(getName()));
            model.setDescription(HtmlUtil.escape(getDescription()));
            model.setCreateDate(getCreateDate());
            model.setDeleted(getDeleted());
            model.setThreadsCount(getThreadsCount());
            model.setLastActivityDate(getLastActivityDate());
            model.setLastActivityAuthorId(getLastActivityAuthorId());

            model = (DiscussionCategory) Proxy.newProxyInstance(DiscussionCategory.class.getClassLoader(),
                    new Class[] { DiscussionCategory.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        DiscussionCategoryImpl clone = new DiscussionCategoryImpl();

        clone.setPk(getPk());
        clone.setCategoryId(getCategoryId());
        clone.setCategoryGroupId(getCategoryGroupId());
        clone.setAuthorId(getAuthorId());
        clone.setName(getName());
        clone.setDescription(getDescription());
        clone.setCreateDate(getCreateDate());
        clone.setDeleted(getDeleted());
        clone.setThreadsCount(getThreadsCount());
        clone.setLastActivityDate(getLastActivityDate());
        clone.setLastActivityAuthorId(getLastActivityAuthorId());

        return clone;
    }

    public int compareTo(DiscussionCategory discussionCategory) {
        int value = 0;

        value = DateUtil.compareTo(getCreateDate(),
                discussionCategory.getCreateDate());

        value = value * -1;

        if (value != 0) {
            return value;
        }

        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        DiscussionCategory discussionCategory = null;

        try {
            discussionCategory = (DiscussionCategory) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = discussionCategory.getPrimaryKey();

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

        sb.append("{pk=");
        sb.append(getPk());
        sb.append(", categoryId=");
        sb.append(getCategoryId());
        sb.append(", categoryGroupId=");
        sb.append(getCategoryGroupId());
        sb.append(", authorId=");
        sb.append(getAuthorId());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append(", deleted=");
        sb.append(getDeleted());
        sb.append(", threadsCount=");
        sb.append(getThreadsCount());
        sb.append(", lastActivityDate=");
        sb.append(getLastActivityDate());
        sb.append(", lastActivityAuthorId=");
        sb.append(getLastActivityAuthorId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.discussions.model.DiscussionCategory");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>pk</column-name><column-value><![CDATA[");
        sb.append(getPk());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>categoryId</column-name><column-value><![CDATA[");
        sb.append(getCategoryId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>categoryGroupId</column-name><column-value><![CDATA[");
        sb.append(getCategoryGroupId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>authorId</column-name><column-value><![CDATA[");
        sb.append(getAuthorId());
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
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deleted</column-name><column-value><![CDATA[");
        sb.append(getDeleted());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>threadsCount</column-name><column-value><![CDATA[");
        sb.append(getThreadsCount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lastActivityDate</column-name><column-value><![CDATA[");
        sb.append(getLastActivityDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lastActivityAuthorId</column-name><column-value><![CDATA[");
        sb.append(getLastActivityAuthorId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
