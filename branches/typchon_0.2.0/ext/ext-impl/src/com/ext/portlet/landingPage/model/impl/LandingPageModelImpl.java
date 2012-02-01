package com.ext.portlet.landingPage.model.impl;

import com.ext.portlet.landingPage.model.LandingPage;
import com.ext.portlet.landingPage.model.LandingPageSoap;

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
 * <a href="LandingPageModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>LandingPage</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.landingPage.model.LandingPage
 * @see com.ext.portlet.landingPage.model.LandingPageModel
 * @see com.ext.portlet.landingPage.model.impl.LandingPageImpl
 *
 */
public class LandingPageModelImpl extends BaseModelImpl<LandingPage> {
    public static final String TABLE_NAME = "LandingPage";
    public static final Object[][] TABLE_COLUMNS = {
            { "id_", new Integer(Types.BIGINT) },
            

            { "baseUrl", new Integer(Types.VARCHAR) },
            

            { "targetUrl", new Integer(Types.VARCHAR) },
            

            { "updated", new Integer(Types.TIMESTAMP) }
        };
    public static final String TABLE_SQL_CREATE = "create table LandingPage (id_ LONG not null primary key,baseUrl VARCHAR(75) null,targetUrl VARCHAR(75) null,updated DATE null)";
    public static final String TABLE_SQL_DROP = "drop table LandingPage";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.landingPage.model.LandingPage"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.landingPage.model.LandingPage"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.landingPage.model.LandingPage"));
    private Long _id;
    private String _baseUrl;
    private String _targetUrl;
    private Date _updated;

    public LandingPageModelImpl() {
    }

    public static LandingPage toModel(LandingPageSoap soapModel) {
        LandingPage model = new LandingPageImpl();

        model.setId(soapModel.getId());
        model.setBaseUrl(soapModel.getBaseUrl());
        model.setTargetUrl(soapModel.getTargetUrl());
        model.setUpdated(soapModel.getUpdated());

        return model;
    }

    public static List<LandingPage> toModels(LandingPageSoap[] soapModels) {
        List<LandingPage> models = new ArrayList<LandingPage>(soapModels.length);

        for (LandingPageSoap soapModel : soapModels) {
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

    public String getBaseUrl() {
        return GetterUtil.getString(_baseUrl);
    }

    public void setBaseUrl(String baseUrl) {
        _baseUrl = baseUrl;
    }

    public String getTargetUrl() {
        return GetterUtil.getString(_targetUrl);
    }

    public void setTargetUrl(String targetUrl) {
        _targetUrl = targetUrl;
    }

    public Date getUpdated() {
        return _updated;
    }

    public void setUpdated(Date updated) {
        _updated = updated;
    }

    public LandingPage toEscapedModel() {
        if (isEscapedModel()) {
            return (LandingPage) this;
        } else {
            LandingPage model = new LandingPageImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setId(getId());
            model.setBaseUrl(HtmlUtil.escape(getBaseUrl()));
            model.setTargetUrl(HtmlUtil.escape(getTargetUrl()));
            model.setUpdated(getUpdated());

            model = (LandingPage) Proxy.newProxyInstance(LandingPage.class.getClassLoader(),
                    new Class[] { LandingPage.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        LandingPageImpl clone = new LandingPageImpl();

        clone.setId(getId());
        clone.setBaseUrl(getBaseUrl());
        clone.setTargetUrl(getTargetUrl());
        clone.setUpdated(getUpdated());

        return clone;
    }

    public int compareTo(LandingPage landingPage) {
        Long pk = landingPage.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        LandingPage landingPage = null;

        try {
            landingPage = (LandingPage) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = landingPage.getPrimaryKey();

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
        sb.append(", baseUrl=");
        sb.append(getBaseUrl());
        sb.append(", targetUrl=");
        sb.append(getTargetUrl());
        sb.append(", updated=");
        sb.append(getUpdated());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.landingPage.model.LandingPage");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>baseUrl</column-name><column-value><![CDATA[");
        sb.append(getBaseUrl());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>targetUrl</column-name><column-value><![CDATA[");
        sb.append(getTargetUrl());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updated</column-name><column-value><![CDATA[");
        sb.append(getUpdated());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
