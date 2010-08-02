package com.ext.portlet.discussions.model.impl;

import com.ext.portlet.discussions.model.DiscussionCategoryGroup;
import com.ext.portlet.discussions.model.DiscussionCategoryGroupSoap;

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
 * <a href="DiscussionCategoryGroupModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>DiscussionCategoryGroup</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.discussions.model.DiscussionCategoryGroup
 * @see com.ext.portlet.discussions.model.DiscussionCategoryGroupModel
 * @see com.ext.portlet.discussions.model.impl.DiscussionCategoryGroupImpl
 *
 */
public class DiscussionCategoryGroupModelImpl extends BaseModelImpl<DiscussionCategoryGroup> {
    public static final String TABLE_NAME = "DiscussionCategoryGroup";
    public static final Object[][] TABLE_COLUMNS = {
            { "id_", new Integer(Types.BIGINT) },
            

            { "description", new Integer(Types.VARCHAR) },
            

            { "url", new Integer(Types.VARCHAR) }
        };
    public static final String TABLE_SQL_CREATE = "create table DiscussionCategoryGroup (id_ LONG not null primary key,description VARCHAR(75) null,url VARCHAR(75) null)";
    public static final String TABLE_SQL_DROP = "drop table DiscussionCategoryGroup";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.discussions.model.DiscussionCategoryGroup"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.discussions.model.DiscussionCategoryGroup"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.discussions.model.DiscussionCategoryGroup"));
    private Long _id;
    private String _description;
    private String _url;

    public DiscussionCategoryGroupModelImpl() {
    }

    public static DiscussionCategoryGroup toModel(
        DiscussionCategoryGroupSoap soapModel) {
        DiscussionCategoryGroup model = new DiscussionCategoryGroupImpl();

        model.setId(soapModel.getId());
        model.setDescription(soapModel.getDescription());
        model.setUrl(soapModel.getUrl());

        return model;
    }

    public static List<DiscussionCategoryGroup> toModels(
        DiscussionCategoryGroupSoap[] soapModels) {
        List<DiscussionCategoryGroup> models = new ArrayList<DiscussionCategoryGroup>(soapModels.length);

        for (DiscussionCategoryGroupSoap soapModel : soapModels) {
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

    public String getDescription() {
        return GetterUtil.getString(_description);
    }

    public void setDescription(String description) {
        _description = description;
    }

    public String getUrl() {
        return GetterUtil.getString(_url);
    }

    public void setUrl(String url) {
        _url = url;
    }

    public DiscussionCategoryGroup toEscapedModel() {
        if (isEscapedModel()) {
            return (DiscussionCategoryGroup) this;
        } else {
            DiscussionCategoryGroup model = new DiscussionCategoryGroupImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setId(getId());
            model.setDescription(HtmlUtil.escape(getDescription()));
            model.setUrl(HtmlUtil.escape(getUrl()));

            model = (DiscussionCategoryGroup) Proxy.newProxyInstance(DiscussionCategoryGroup.class.getClassLoader(),
                    new Class[] { DiscussionCategoryGroup.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        DiscussionCategoryGroupImpl clone = new DiscussionCategoryGroupImpl();

        clone.setId(getId());
        clone.setDescription(getDescription());
        clone.setUrl(getUrl());

        return clone;
    }

    public int compareTo(DiscussionCategoryGroup discussionCategoryGroup) {
        Long pk = discussionCategoryGroup.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        DiscussionCategoryGroup discussionCategoryGroup = null;

        try {
            discussionCategoryGroup = (DiscussionCategoryGroup) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = discussionCategoryGroup.getPrimaryKey();

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
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", url=");
        sb.append(getUrl());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.discussions.model.DiscussionCategoryGroup");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>url</column-name><column-value><![CDATA[");
        sb.append(getUrl());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
