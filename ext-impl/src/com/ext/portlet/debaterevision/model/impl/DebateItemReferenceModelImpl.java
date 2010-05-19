package com.ext.portlet.debaterevision.model.impl;

import com.ext.portlet.debaterevision.model.DebateItemReference;
import com.ext.portlet.debaterevision.model.DebateItemReferenceSoap;

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
 * <a href="DebateItemReferenceModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>DebateItemReference</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.model.DebateItemReference
 * @see com.ext.portlet.debaterevision.model.DebateItemReferenceModel
 * @see com.ext.portlet.debaterevision.model.impl.DebateItemReferenceImpl
 *
 */
public class DebateItemReferenceModelImpl extends BaseModelImpl<DebateItemReference> {
    public static final String TABLE_NAME = "DebateItemReference";
    public static final Object[][] TABLE_COLUMNS = {
            { "debateItemReferencePK", new Integer(Types.BIGINT) },
            

            { "debateItemId", new Integer(Types.BIGINT) },
            

            { "debateId", new Integer(Types.BIGINT) },
            

            { "itemVersion", new Integer(Types.BIGINT) },
            

            { "status", new Integer(Types.VARCHAR) },
            

            { "text_", new Integer(Types.VARCHAR) },
            

            { "url", new Integer(Types.VARCHAR) }
        };
    public static final String TABLE_SQL_CREATE = "create table DebateItemReference (debateItemReferencePK LONG not null primary key,debateItemId LONG,debateId LONG,itemVersion LONG,status VARCHAR(75) null,text_ VARCHAR(75) null,url VARCHAR(75) null)";
    public static final String TABLE_SQL_DROP = "drop table DebateItemReference";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.debaterevision.model.DebateItemReference"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.debaterevision.model.DebateItemReference"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.debaterevision.model.DebateItemReference"));
    private Long _debateItemReferencePK;
    private Long _debateItemId;
    private Long _debateId;
    private Long _itemVersion;
    private String _status;
    private String _text;
    private String _url;

    public DebateItemReferenceModelImpl() {
    }

    public static DebateItemReference toModel(DebateItemReferenceSoap soapModel) {
        DebateItemReference model = new DebateItemReferenceImpl();

        model.setDebateItemReferencePK(soapModel.getDebateItemReferencePK());
        model.setDebateItemId(soapModel.getDebateItemId());
        model.setDebateId(soapModel.getDebateId());
        model.setItemVersion(soapModel.getItemVersion());
        model.setStatus(soapModel.getStatus());
        model.setText(soapModel.getText());
        model.setUrl(soapModel.getUrl());

        return model;
    }

    public static List<DebateItemReference> toModels(
        DebateItemReferenceSoap[] soapModels) {
        List<DebateItemReference> models = new ArrayList<DebateItemReference>(soapModels.length);

        for (DebateItemReferenceSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _debateItemReferencePK;
    }

    public void setPrimaryKey(Long pk) {
        setDebateItemReferencePK(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _debateItemReferencePK;
    }

    public Long getDebateItemReferencePK() {
        return _debateItemReferencePK;
    }

    public void setDebateItemReferencePK(Long debateItemReferencePK) {
        _debateItemReferencePK = debateItemReferencePK;
    }

    public Long getDebateItemId() {
        return _debateItemId;
    }

    public void setDebateItemId(Long debateItemId) {
        _debateItemId = debateItemId;
    }

    public Long getDebateId() {
        return _debateId;
    }

    public void setDebateId(Long debateId) {
        _debateId = debateId;
    }

    public Long getItemVersion() {
        return _itemVersion;
    }

    public void setItemVersion(Long itemVersion) {
        _itemVersion = itemVersion;
    }

    public String getStatus() {
        return GetterUtil.getString(_status);
    }

    public void setStatus(String status) {
        _status = status;
    }

    public String getText() {
        return GetterUtil.getString(_text);
    }

    public void setText(String text) {
        _text = text;
    }

    public String getUrl() {
        return GetterUtil.getString(_url);
    }

    public void setUrl(String url) {
        _url = url;
    }

    public DebateItemReference toEscapedModel() {
        if (isEscapedModel()) {
            return (DebateItemReference) this;
        } else {
            DebateItemReference model = new DebateItemReferenceImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setDebateItemReferencePK(getDebateItemReferencePK());
            model.setDebateItemId(getDebateItemId());
            model.setDebateId(getDebateId());
            model.setItemVersion(getItemVersion());
            model.setStatus(HtmlUtil.escape(getStatus()));
            model.setText(HtmlUtil.escape(getText()));
            model.setUrl(HtmlUtil.escape(getUrl()));

            model = (DebateItemReference) Proxy.newProxyInstance(DebateItemReference.class.getClassLoader(),
                    new Class[] { DebateItemReference.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        DebateItemReferenceImpl clone = new DebateItemReferenceImpl();

        clone.setDebateItemReferencePK(getDebateItemReferencePK());
        clone.setDebateItemId(getDebateItemId());
        clone.setDebateId(getDebateId());
        clone.setItemVersion(getItemVersion());
        clone.setStatus(getStatus());
        clone.setText(getText());
        clone.setUrl(getUrl());

        return clone;
    }

    public int compareTo(DebateItemReference debateItemReference) {
        Long pk = debateItemReference.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        DebateItemReference debateItemReference = null;

        try {
            debateItemReference = (DebateItemReference) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = debateItemReference.getPrimaryKey();

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

        sb.append("{debateItemReferencePK=");
        sb.append(getDebateItemReferencePK());
        sb.append(", debateItemId=");
        sb.append(getDebateItemId());
        sb.append(", debateId=");
        sb.append(getDebateId());
        sb.append(", itemVersion=");
        sb.append(getItemVersion());
        sb.append(", status=");
        sb.append(getStatus());
        sb.append(", text=");
        sb.append(getText());
        sb.append(", url=");
        sb.append(getUrl());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.debaterevision.model.DebateItemReference");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>debateItemReferencePK</column-name><column-value><![CDATA[");
        sb.append(getDebateItemReferencePK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>debateItemId</column-name><column-value><![CDATA[");
        sb.append(getDebateItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>debateId</column-name><column-value><![CDATA[");
        sb.append(getDebateId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemVersion</column-name><column-value><![CDATA[");
        sb.append(getItemVersion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>status</column-name><column-value><![CDATA[");
        sb.append(getStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>text</column-name><column-value><![CDATA[");
        sb.append(getText());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>url</column-name><column-value><![CDATA[");
        sb.append(getUrl());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
