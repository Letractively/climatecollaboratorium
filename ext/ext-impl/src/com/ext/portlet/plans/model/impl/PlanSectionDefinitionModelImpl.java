package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanSectionDefinition;
import com.ext.portlet.plans.model.PlanSectionDefinitionSoap;

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
 * <a href="PlanSectionDefinitionModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>PlanSectionDefinition</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanSectionDefinition
 * @see com.ext.portlet.plans.model.PlanSectionDefinitionModel
 * @see com.ext.portlet.plans.model.impl.PlanSectionDefinitionImpl
 *
 */
public class PlanSectionDefinitionModelImpl extends BaseModelImpl<PlanSectionDefinition> {
    public static final String TABLE_NAME = "PlanSectionDefinition";
    public static final Object[][] TABLE_COLUMNS = {
            { "id_", new Integer(Types.BIGINT) },
            

            { "adminTitle", new Integer(Types.VARCHAR) },
            

            { "title", new Integer(Types.VARCHAR) },
            

            { "defaultText", new Integer(Types.VARCHAR) },
            

            { "helpText", new Integer(Types.VARCHAR) },
            

            { "characterLimit", new Integer(Types.INTEGER) },
            

            { "focusAreaId", new Integer(Types.BIGINT) }
        };
    public static final String TABLE_SQL_CREATE = "create table PlanSectionDefinition (id_ LONG not null primary key,adminTitle VARCHAR(75) null,title VARCHAR(75) null,defaultText VARCHAR(75) null,helpText VARCHAR(75) null,characterLimit INTEGER,focusAreaId LONG)";
    public static final String TABLE_SQL_DROP = "drop table PlanSectionDefinition";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.plans.model.PlanSectionDefinition"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.plans.model.PlanSectionDefinition"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.plans.model.PlanSectionDefinition"));
    private Long _id;
    private String _adminTitle;
    private String _title;
    private String _defaultText;
    private String _helpText;
    private Integer _characterLimit;
    private Long _focusAreaId;

    public PlanSectionDefinitionModelImpl() {
    }

    public static PlanSectionDefinition toModel(
        PlanSectionDefinitionSoap soapModel) {
        PlanSectionDefinition model = new PlanSectionDefinitionImpl();

        model.setId(soapModel.getId());
        model.setAdminTitle(soapModel.getAdminTitle());
        model.setTitle(soapModel.getTitle());
        model.setDefaultText(soapModel.getDefaultText());
        model.setHelpText(soapModel.getHelpText());
        model.setCharacterLimit(soapModel.getCharacterLimit());
        model.setFocusAreaId(soapModel.getFocusAreaId());

        return model;
    }

    public static List<PlanSectionDefinition> toModels(
        PlanSectionDefinitionSoap[] soapModels) {
        List<PlanSectionDefinition> models = new ArrayList<PlanSectionDefinition>(soapModels.length);

        for (PlanSectionDefinitionSoap soapModel : soapModels) {
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

    public String getAdminTitle() {
        return GetterUtil.getString(_adminTitle);
    }

    public void setAdminTitle(String adminTitle) {
        _adminTitle = adminTitle;
    }

    public String getTitle() {
        return GetterUtil.getString(_title);
    }

    public void setTitle(String title) {
        _title = title;
    }

    public String getDefaultText() {
        return GetterUtil.getString(_defaultText);
    }

    public void setDefaultText(String defaultText) {
        _defaultText = defaultText;
    }

    public String getHelpText() {
        return GetterUtil.getString(_helpText);
    }

    public void setHelpText(String helpText) {
        _helpText = helpText;
    }

    public Integer getCharacterLimit() {
        return _characterLimit;
    }

    public void setCharacterLimit(Integer characterLimit) {
        _characterLimit = characterLimit;
    }

    public Long getFocusAreaId() {
        return _focusAreaId;
    }

    public void setFocusAreaId(Long focusAreaId) {
        _focusAreaId = focusAreaId;
    }

    public PlanSectionDefinition toEscapedModel() {
        if (isEscapedModel()) {
            return (PlanSectionDefinition) this;
        } else {
            PlanSectionDefinition model = new PlanSectionDefinitionImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setId(getId());
            model.setAdminTitle(HtmlUtil.escape(getAdminTitle()));
            model.setTitle(HtmlUtil.escape(getTitle()));
            model.setDefaultText(HtmlUtil.escape(getDefaultText()));
            model.setHelpText(HtmlUtil.escape(getHelpText()));
            model.setCharacterLimit(getCharacterLimit());
            model.setFocusAreaId(getFocusAreaId());

            model = (PlanSectionDefinition) Proxy.newProxyInstance(PlanSectionDefinition.class.getClassLoader(),
                    new Class[] { PlanSectionDefinition.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        PlanSectionDefinitionImpl clone = new PlanSectionDefinitionImpl();

        clone.setId(getId());
        clone.setAdminTitle(getAdminTitle());
        clone.setTitle(getTitle());
        clone.setDefaultText(getDefaultText());
        clone.setHelpText(getHelpText());
        clone.setCharacterLimit(getCharacterLimit());
        clone.setFocusAreaId(getFocusAreaId());

        return clone;
    }

    public int compareTo(PlanSectionDefinition planSectionDefinition) {
        Long pk = planSectionDefinition.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlanSectionDefinition planSectionDefinition = null;

        try {
            planSectionDefinition = (PlanSectionDefinition) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = planSectionDefinition.getPrimaryKey();

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
        sb.append(", adminTitle=");
        sb.append(getAdminTitle());
        sb.append(", title=");
        sb.append(getTitle());
        sb.append(", defaultText=");
        sb.append(getDefaultText());
        sb.append(", helpText=");
        sb.append(getHelpText());
        sb.append(", characterLimit=");
        sb.append(getCharacterLimit());
        sb.append(", focusAreaId=");
        sb.append(getFocusAreaId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlanSectionDefinition");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>adminTitle</column-name><column-value><![CDATA[");
        sb.append(getAdminTitle());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>title</column-name><column-value><![CDATA[");
        sb.append(getTitle());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>defaultText</column-name><column-value><![CDATA[");
        sb.append(getDefaultText());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>helpText</column-name><column-value><![CDATA[");
        sb.append(getHelpText());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>characterLimit</column-name><column-value><![CDATA[");
        sb.append(getCharacterLimit());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>focusAreaId</column-name><column-value><![CDATA[");
        sb.append(getFocusAreaId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}