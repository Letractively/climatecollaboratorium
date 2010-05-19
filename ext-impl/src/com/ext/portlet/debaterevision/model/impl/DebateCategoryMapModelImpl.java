package com.ext.portlet.debaterevision.model.impl;

import com.ext.portlet.debaterevision.model.DebateCategoryMap;
import com.ext.portlet.debaterevision.model.DebateCategoryMapSoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="DebateCategoryMapModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>DebateCategoryMap</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.model.DebateCategoryMap
 * @see com.ext.portlet.debaterevision.model.DebateCategoryMapModel
 * @see com.ext.portlet.debaterevision.model.impl.DebateCategoryMapImpl
 *
 */
public class DebateCategoryMapModelImpl extends BaseModelImpl<DebateCategoryMap> {
    public static final String TABLE_NAME = "DebateCategoryMap";
    public static final Object[][] TABLE_COLUMNS = {
            { "debateCategoryMapPK", new Integer(Types.BIGINT) },
            

            { "debateCategoryPK", new Integer(Types.BIGINT) },
            

            { "debateId", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table DebateCategoryMap (debateCategoryMapPK LONG not null primary key,debateCategoryPK LONG,debateId LONG)";
    public static final String TABLE_SQL_DROP = "drop table DebateCategoryMap";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.debaterevision.model.DebateCategoryMap"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.debaterevision.model.DebateCategoryMap"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.debaterevision.model.DebateCategoryMap"));
    private Long _debateCategoryMapPK;
    private Long _debateCategoryPK;
    private Long _originalDebateCategoryPK;
    private Long _debateId;
    private Long _originalDebateId;

    public DebateCategoryMapModelImpl() {
    }

    public static DebateCategoryMap toModel(DebateCategoryMapSoap soapModel) {
        DebateCategoryMap model = new DebateCategoryMapImpl();

        model.setDebateCategoryMapPK(soapModel.getDebateCategoryMapPK());
        model.setDebateCategoryPK(soapModel.getDebateCategoryPK());
        model.setDebateId(soapModel.getDebateId());

        return model;
    }

    public static List<DebateCategoryMap> toModels(
        DebateCategoryMapSoap[] soapModels) {
        List<DebateCategoryMap> models = new ArrayList<DebateCategoryMap>(soapModels.length);

        for (DebateCategoryMapSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _debateCategoryMapPK;
    }

    public void setPrimaryKey(Long pk) {
        setDebateCategoryMapPK(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _debateCategoryMapPK;
    }

    public Long getDebateCategoryMapPK() {
        return _debateCategoryMapPK;
    }

    public void setDebateCategoryMapPK(Long debateCategoryMapPK) {
        _debateCategoryMapPK = debateCategoryMapPK;
    }

    public Long getDebateCategoryPK() {
        return _debateCategoryPK;
    }

    public void setDebateCategoryPK(Long debateCategoryPK) {
        _debateCategoryPK = debateCategoryPK;

        if (_originalDebateCategoryPK == null) {
            _originalDebateCategoryPK = debateCategoryPK;
        }
    }

    public Long getOriginalDebateCategoryPK() {
        return _originalDebateCategoryPK;
    }

    public Long getDebateId() {
        return _debateId;
    }

    public void setDebateId(Long debateId) {
        _debateId = debateId;

        if (_originalDebateId == null) {
            _originalDebateId = debateId;
        }
    }

    public Long getOriginalDebateId() {
        return _originalDebateId;
    }

    public DebateCategoryMap toEscapedModel() {
        if (isEscapedModel()) {
            return (DebateCategoryMap) this;
        } else {
            DebateCategoryMap model = new DebateCategoryMapImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setDebateCategoryMapPK(getDebateCategoryMapPK());
            model.setDebateCategoryPK(getDebateCategoryPK());
            model.setDebateId(getDebateId());

            model = (DebateCategoryMap) Proxy.newProxyInstance(DebateCategoryMap.class.getClassLoader(),
                    new Class[] { DebateCategoryMap.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        DebateCategoryMapImpl clone = new DebateCategoryMapImpl();

        clone.setDebateCategoryMapPK(getDebateCategoryMapPK());
        clone.setDebateCategoryPK(getDebateCategoryPK());
        clone.setDebateId(getDebateId());

        return clone;
    }

    public int compareTo(DebateCategoryMap debateCategoryMap) {
        Long pk = debateCategoryMap.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        DebateCategoryMap debateCategoryMap = null;

        try {
            debateCategoryMap = (DebateCategoryMap) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = debateCategoryMap.getPrimaryKey();

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

        sb.append("{debateCategoryMapPK=");
        sb.append(getDebateCategoryMapPK());
        sb.append(", debateCategoryPK=");
        sb.append(getDebateCategoryPK());
        sb.append(", debateId=");
        sb.append(getDebateId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.debaterevision.model.DebateCategoryMap");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>debateCategoryMapPK</column-name><column-value><![CDATA[");
        sb.append(getDebateCategoryMapPK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>debateCategoryPK</column-name><column-value><![CDATA[");
        sb.append(getDebateCategoryPK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>debateId</column-name><column-value><![CDATA[");
        sb.append(getDebateId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
