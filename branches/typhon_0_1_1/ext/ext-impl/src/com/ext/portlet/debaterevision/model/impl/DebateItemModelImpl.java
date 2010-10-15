package com.ext.portlet.debaterevision.model.impl;

import com.ext.portlet.debaterevision.model.DebateItem;
import com.ext.portlet.debaterevision.model.DebateItemSoap;

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
 * <a href="DebateItemModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>DebateItem</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.model.DebateItem
 * @see com.ext.portlet.debaterevision.model.DebateItemModel
 * @see com.ext.portlet.debaterevision.model.impl.DebateItemImpl
 *
 */
public class DebateItemModelImpl extends BaseModelImpl<DebateItem> {
    public static final String TABLE_NAME = "DebateItem";
    public static final Object[][] TABLE_COLUMNS = {
            { "debateItemPK", new Integer(Types.BIGINT) },
            

            { "debateItemId", new Integer(Types.BIGINT) },
            

            { "debateItemParentId", new Integer(Types.BIGINT) },
            

            { "debateId", new Integer(Types.BIGINT) },
            

            { "debateSummary", new Integer(Types.VARCHAR) },
            

            { "debateDetail", new Integer(Types.VARCHAR) },
            

            { "debatePostType", new Integer(Types.VARCHAR) },
            

            { "authorId", new Integer(Types.BIGINT) },
            

            { "weight", new Integer(Types.BIGINT) },
            

            { "itemVersion", new Integer(Types.BIGINT) },
            

            { "treeVersion", new Integer(Types.BIGINT) },
            

            { "updated", new Integer(Types.TIMESTAMP) },
            

            { "status", new Integer(Types.VARCHAR) }
        };
    public static final String TABLE_SQL_CREATE = "create table DebateItem (debateItemPK LONG not null primary key,debateItemId LONG,debateItemParentId LONG,debateId LONG,debateSummary VARCHAR(75) null,debateDetail VARCHAR(75) null,debatePostType VARCHAR(75) null,authorId LONG,weight LONG,itemVersion LONG,treeVersion LONG,updated DATE null,status VARCHAR(75) null)";
    public static final String TABLE_SQL_DROP = "drop table DebateItem";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.debaterevision.model.DebateItem"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.debaterevision.model.DebateItem"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.debaterevision.model.DebateItem"));
    private Long _debateItemPK;
    private Long _debateItemId;
    private Long _debateItemParentId;
    private Long _debateId;
    private String _debateSummary;
    private String _debateDetail;
    private String _debatePostType;
    private Long _authorId;
    private Long _weight;
    private Long _itemVersion;
    private Long _treeVersion;
    private Date _updated;
    private String _status;

    public DebateItemModelImpl() {
    }

    public static DebateItem toModel(DebateItemSoap soapModel) {
        DebateItem model = new DebateItemImpl();

        model.setDebateItemPK(soapModel.getDebateItemPK());
        model.setDebateItemId(soapModel.getDebateItemId());
        model.setDebateItemParentId(soapModel.getDebateItemParentId());
        model.setDebateId(soapModel.getDebateId());
        model.setDebateSummary(soapModel.getDebateSummary());
        model.setDebateDetail(soapModel.getDebateDetail());
        model.setDebatePostType(soapModel.getDebatePostType());
        model.setAuthorId(soapModel.getAuthorId());
        model.setWeight(soapModel.getWeight());
        model.setItemVersion(soapModel.getItemVersion());
        model.setTreeVersion(soapModel.getTreeVersion());
        model.setUpdated(soapModel.getUpdated());
        model.setStatus(soapModel.getStatus());

        return model;
    }

    public static List<DebateItem> toModels(DebateItemSoap[] soapModels) {
        List<DebateItem> models = new ArrayList<DebateItem>(soapModels.length);

        for (DebateItemSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _debateItemPK;
    }

    public void setPrimaryKey(Long pk) {
        setDebateItemPK(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _debateItemPK;
    }

    public Long getDebateItemPK() {
        return _debateItemPK;
    }

    public void setDebateItemPK(Long debateItemPK) {
        _debateItemPK = debateItemPK;
    }

    public Long getDebateItemId() {
        return _debateItemId;
    }

    public void setDebateItemId(Long debateItemId) {
        _debateItemId = debateItemId;
    }

    public Long getDebateItemParentId() {
        return _debateItemParentId;
    }

    public void setDebateItemParentId(Long debateItemParentId) {
        _debateItemParentId = debateItemParentId;
    }

    public Long getDebateId() {
        return _debateId;
    }

    public void setDebateId(Long debateId) {
        _debateId = debateId;
    }

    public String getDebateSummary() {
        return GetterUtil.getString(_debateSummary);
    }

    public void setDebateSummary(String debateSummary) {
        _debateSummary = debateSummary;
    }

    public String getDebateDetail() {
        return GetterUtil.getString(_debateDetail);
    }

    public void setDebateDetail(String debateDetail) {
        _debateDetail = debateDetail;
    }

    public String getDebatePostType() {
        return GetterUtil.getString(_debatePostType);
    }

    public void setDebatePostType(String debatePostType) {
        _debatePostType = debatePostType;
    }

    public Long getAuthorId() {
        return _authorId;
    }

    public void setAuthorId(Long authorId) {
        _authorId = authorId;
    }

    public Long getWeight() {
        return _weight;
    }

    public void setWeight(Long weight) {
        _weight = weight;
    }

    public Long getItemVersion() {
        return _itemVersion;
    }

    public void setItemVersion(Long itemVersion) {
        _itemVersion = itemVersion;
    }

    public Long getTreeVersion() {
        return _treeVersion;
    }

    public void setTreeVersion(Long treeVersion) {
        _treeVersion = treeVersion;
    }

    public Date getUpdated() {
        return _updated;
    }

    public void setUpdated(Date updated) {
        _updated = updated;
    }

    public String getStatus() {
        return GetterUtil.getString(_status);
    }

    public void setStatus(String status) {
        _status = status;
    }

    public DebateItem toEscapedModel() {
        if (isEscapedModel()) {
            return (DebateItem) this;
        } else {
            DebateItem model = new DebateItemImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setDebateItemPK(getDebateItemPK());
            model.setDebateItemId(getDebateItemId());
            model.setDebateItemParentId(getDebateItemParentId());
            model.setDebateId(getDebateId());
            model.setDebateSummary(HtmlUtil.escape(getDebateSummary()));
            model.setDebateDetail(HtmlUtil.escape(getDebateDetail()));
            model.setDebatePostType(HtmlUtil.escape(getDebatePostType()));
            model.setAuthorId(getAuthorId());
            model.setWeight(getWeight());
            model.setItemVersion(getItemVersion());
            model.setTreeVersion(getTreeVersion());
            model.setUpdated(getUpdated());
            model.setStatus(HtmlUtil.escape(getStatus()));

            model = (DebateItem) Proxy.newProxyInstance(DebateItem.class.getClassLoader(),
                    new Class[] { DebateItem.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        DebateItemImpl clone = new DebateItemImpl();

        clone.setDebateItemPK(getDebateItemPK());
        clone.setDebateItemId(getDebateItemId());
        clone.setDebateItemParentId(getDebateItemParentId());
        clone.setDebateId(getDebateId());
        clone.setDebateSummary(getDebateSummary());
        clone.setDebateDetail(getDebateDetail());
        clone.setDebatePostType(getDebatePostType());
        clone.setAuthorId(getAuthorId());
        clone.setWeight(getWeight());
        clone.setItemVersion(getItemVersion());
        clone.setTreeVersion(getTreeVersion());
        clone.setUpdated(getUpdated());
        clone.setStatus(getStatus());

        return clone;
    }

    public int compareTo(DebateItem debateItem) {
        int value = 0;

        value = getItemVersion().compareTo(debateItem.getItemVersion());

        if (value != 0) {
            return value;
        }

        value = getWeight().compareTo(debateItem.getWeight());

        if (value != 0) {
            return value;
        }

        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        DebateItem debateItem = null;

        try {
            debateItem = (DebateItem) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = debateItem.getPrimaryKey();

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

        sb.append("{debateItemPK=");
        sb.append(getDebateItemPK());
        sb.append(", debateItemId=");
        sb.append(getDebateItemId());
        sb.append(", debateItemParentId=");
        sb.append(getDebateItemParentId());
        sb.append(", debateId=");
        sb.append(getDebateId());
        sb.append(", debateSummary=");
        sb.append(getDebateSummary());
        sb.append(", debateDetail=");
        sb.append(getDebateDetail());
        sb.append(", debatePostType=");
        sb.append(getDebatePostType());
        sb.append(", authorId=");
        sb.append(getAuthorId());
        sb.append(", weight=");
        sb.append(getWeight());
        sb.append(", itemVersion=");
        sb.append(getItemVersion());
        sb.append(", treeVersion=");
        sb.append(getTreeVersion());
        sb.append(", updated=");
        sb.append(getUpdated());
        sb.append(", status=");
        sb.append(getStatus());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.debaterevision.model.DebateItem");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>debateItemPK</column-name><column-value><![CDATA[");
        sb.append(getDebateItemPK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>debateItemId</column-name><column-value><![CDATA[");
        sb.append(getDebateItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>debateItemParentId</column-name><column-value><![CDATA[");
        sb.append(getDebateItemParentId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>debateId</column-name><column-value><![CDATA[");
        sb.append(getDebateId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>debateSummary</column-name><column-value><![CDATA[");
        sb.append(getDebateSummary());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>debateDetail</column-name><column-value><![CDATA[");
        sb.append(getDebateDetail());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>debatePostType</column-name><column-value><![CDATA[");
        sb.append(getDebatePostType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>authorId</column-name><column-value><![CDATA[");
        sb.append(getAuthorId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>weight</column-name><column-value><![CDATA[");
        sb.append(getWeight());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemVersion</column-name><column-value><![CDATA[");
        sb.append(getItemVersion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>treeVersion</column-name><column-value><![CDATA[");
        sb.append(getTreeVersion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updated</column-name><column-value><![CDATA[");
        sb.append(getUpdated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>status</column-name><column-value><![CDATA[");
        sb.append(getStatus());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
