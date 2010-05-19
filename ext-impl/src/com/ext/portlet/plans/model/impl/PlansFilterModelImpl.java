package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlansFilter;
import com.ext.portlet.plans.model.PlansFilterSoap;
import com.ext.portlet.plans.service.persistence.PlansFilterPK;

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
 * <a href="PlansFilterModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>PlansFilter</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlansFilter
 * @see com.ext.portlet.plans.model.PlansFilterModel
 * @see com.ext.portlet.plans.model.impl.PlansFilterImpl
 *
 */
public class PlansFilterModelImpl extends BaseModelImpl<PlansFilter> {
    public static final String TABLE_NAME = "PlansFilter";
    public static final Object[][] TABLE_COLUMNS = {
            { "userId", new Integer(Types.BIGINT) },
            

            { "planTypeId", new Integer(Types.BIGINT) },
            

            { "name", new Integer(Types.VARCHAR) },
            

            { "creator", new Integer(Types.VARCHAR) },
            

            { "description", new Integer(Types.VARCHAR) },
            

            { "CO2From", new Integer(Types.DOUBLE) },
            

            { "CO2To", new Integer(Types.DOUBLE) },
            

            { "votesFrom", new Integer(Types.DOUBLE) },
            

            { "votesTo", new Integer(Types.DOUBLE) },
            

            { "damageFrom", new Integer(Types.DOUBLE) },
            

            { "damageTo", new Integer(Types.DOUBLE) },
            

            { "mitigationFrom", new Integer(Types.DOUBLE) },
            

            { "mitigationTo", new Integer(Types.DOUBLE) },
            

            { "dateFrom", new Integer(Types.TIMESTAMP) },
            

            { "dateTo", new Integer(Types.TIMESTAMP) },
            

            { "filterPositionsAll", new Integer(Types.BOOLEAN) },
            

            { "enabled", new Integer(Types.BOOLEAN) }
        };
    public static final String TABLE_SQL_CREATE = "create table PlansFilter (userId LONG not null,planTypeId LONG not null,name VARCHAR(75) null,creator VARCHAR(75) null,description VARCHAR(75) null,CO2From DOUBLE,CO2To DOUBLE,votesFrom DOUBLE,votesTo DOUBLE,damageFrom DOUBLE,damageTo DOUBLE,mitigationFrom DOUBLE,mitigationTo DOUBLE,dateFrom DATE null,dateTo DATE null,filterPositionsAll BOOLEAN,enabled BOOLEAN,primary key (userId, planTypeId))";
    public static final String TABLE_SQL_DROP = "drop table PlansFilter";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.plans.model.PlansFilter"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.plans.model.PlansFilter"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.plans.model.PlansFilter"));
    private Long _userId;
    private Long _planTypeId;
    private String _name;
    private String _creator;
    private String _description;
    private Double _CO2From;
    private Double _CO2To;
    private Double _votesFrom;
    private Double _votesTo;
    private Double _damageFrom;
    private Double _damageTo;
    private Double _mitigationFrom;
    private Double _mitigationTo;
    private Date _dateFrom;
    private Date _dateTo;
    private Boolean _filterPositionsAll;
    private Boolean _enabled;

    public PlansFilterModelImpl() {
    }

    public static PlansFilter toModel(PlansFilterSoap soapModel) {
        PlansFilter model = new PlansFilterImpl();

        model.setUserId(soapModel.getUserId());
        model.setPlanTypeId(soapModel.getPlanTypeId());
        model.setName(soapModel.getName());
        model.setCreator(soapModel.getCreator());
        model.setDescription(soapModel.getDescription());
        model.setCO2From(soapModel.getCO2From());
        model.setCO2To(soapModel.getCO2To());
        model.setVotesFrom(soapModel.getVotesFrom());
        model.setVotesTo(soapModel.getVotesTo());
        model.setDamageFrom(soapModel.getDamageFrom());
        model.setDamageTo(soapModel.getDamageTo());
        model.setMitigationFrom(soapModel.getMitigationFrom());
        model.setMitigationTo(soapModel.getMitigationTo());
        model.setDateFrom(soapModel.getDateFrom());
        model.setDateTo(soapModel.getDateTo());
        model.setFilterPositionsAll(soapModel.getFilterPositionsAll());
        model.setEnabled(soapModel.getEnabled());

        return model;
    }

    public static List<PlansFilter> toModels(PlansFilterSoap[] soapModels) {
        List<PlansFilter> models = new ArrayList<PlansFilter>(soapModels.length);

        for (PlansFilterSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public PlansFilterPK getPrimaryKey() {
        return new PlansFilterPK(_userId, _planTypeId);
    }

    public void setPrimaryKey(PlansFilterPK pk) {
        setUserId(pk.userId);
        setPlanTypeId(pk.planTypeId);
    }

    public Serializable getPrimaryKeyObj() {
        return new PlansFilterPK(_userId, _planTypeId);
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }

    public Long getPlanTypeId() {
        return _planTypeId;
    }

    public void setPlanTypeId(Long planTypeId) {
        _planTypeId = planTypeId;
    }

    public String getName() {
        return GetterUtil.getString(_name);
    }

    public void setName(String name) {
        _name = name;
    }

    public String getCreator() {
        return GetterUtil.getString(_creator);
    }

    public void setCreator(String creator) {
        _creator = creator;
    }

    public String getDescription() {
        return GetterUtil.getString(_description);
    }

    public void setDescription(String description) {
        _description = description;
    }

    public Double getCO2From() {
        return _CO2From;
    }

    public void setCO2From(Double CO2From) {
        _CO2From = CO2From;
    }

    public Double getCO2To() {
        return _CO2To;
    }

    public void setCO2To(Double CO2To) {
        _CO2To = CO2To;
    }

    public Double getVotesFrom() {
        return _votesFrom;
    }

    public void setVotesFrom(Double votesFrom) {
        _votesFrom = votesFrom;
    }

    public Double getVotesTo() {
        return _votesTo;
    }

    public void setVotesTo(Double votesTo) {
        _votesTo = votesTo;
    }

    public Double getDamageFrom() {
        return _damageFrom;
    }

    public void setDamageFrom(Double damageFrom) {
        _damageFrom = damageFrom;
    }

    public Double getDamageTo() {
        return _damageTo;
    }

    public void setDamageTo(Double damageTo) {
        _damageTo = damageTo;
    }

    public Double getMitigationFrom() {
        return _mitigationFrom;
    }

    public void setMitigationFrom(Double mitigationFrom) {
        _mitigationFrom = mitigationFrom;
    }

    public Double getMitigationTo() {
        return _mitigationTo;
    }

    public void setMitigationTo(Double mitigationTo) {
        _mitigationTo = mitigationTo;
    }

    public Date getDateFrom() {
        return _dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        _dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return _dateTo;
    }

    public void setDateTo(Date dateTo) {
        _dateTo = dateTo;
    }

    public Boolean getFilterPositionsAll() {
        return _filterPositionsAll;
    }

    public void setFilterPositionsAll(Boolean filterPositionsAll) {
        _filterPositionsAll = filterPositionsAll;
    }

    public Boolean getEnabled() {
        return _enabled;
    }

    public void setEnabled(Boolean enabled) {
        _enabled = enabled;
    }

    public PlansFilter toEscapedModel() {
        if (isEscapedModel()) {
            return (PlansFilter) this;
        } else {
            PlansFilter model = new PlansFilterImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setUserId(getUserId());
            model.setPlanTypeId(getPlanTypeId());
            model.setName(HtmlUtil.escape(getName()));
            model.setCreator(HtmlUtil.escape(getCreator()));
            model.setDescription(HtmlUtil.escape(getDescription()));
            model.setCO2From(getCO2From());
            model.setCO2To(getCO2To());
            model.setVotesFrom(getVotesFrom());
            model.setVotesTo(getVotesTo());
            model.setDamageFrom(getDamageFrom());
            model.setDamageTo(getDamageTo());
            model.setMitigationFrom(getMitigationFrom());
            model.setMitigationTo(getMitigationTo());
            model.setDateFrom(getDateFrom());
            model.setDateTo(getDateTo());
            model.setFilterPositionsAll(getFilterPositionsAll());
            model.setEnabled(getEnabled());

            model = (PlansFilter) Proxy.newProxyInstance(PlansFilter.class.getClassLoader(),
                    new Class[] { PlansFilter.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        PlansFilterImpl clone = new PlansFilterImpl();

        clone.setUserId(getUserId());
        clone.setPlanTypeId(getPlanTypeId());
        clone.setName(getName());
        clone.setCreator(getCreator());
        clone.setDescription(getDescription());
        clone.setCO2From(getCO2From());
        clone.setCO2To(getCO2To());
        clone.setVotesFrom(getVotesFrom());
        clone.setVotesTo(getVotesTo());
        clone.setDamageFrom(getDamageFrom());
        clone.setDamageTo(getDamageTo());
        clone.setMitigationFrom(getMitigationFrom());
        clone.setMitigationTo(getMitigationTo());
        clone.setDateFrom(getDateFrom());
        clone.setDateTo(getDateTo());
        clone.setFilterPositionsAll(getFilterPositionsAll());
        clone.setEnabled(getEnabled());

        return clone;
    }

    public int compareTo(PlansFilter plansFilter) {
        PlansFilterPK pk = plansFilter.getPrimaryKey();

        return getPrimaryKey().compareTo(pk);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlansFilter plansFilter = null;

        try {
            plansFilter = (PlansFilter) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        PlansFilterPK pk = plansFilter.getPrimaryKey();

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

        sb.append("{userId=");
        sb.append(getUserId());
        sb.append(", planTypeId=");
        sb.append(getPlanTypeId());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", creator=");
        sb.append(getCreator());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", CO2From=");
        sb.append(getCO2From());
        sb.append(", CO2To=");
        sb.append(getCO2To());
        sb.append(", votesFrom=");
        sb.append(getVotesFrom());
        sb.append(", votesTo=");
        sb.append(getVotesTo());
        sb.append(", damageFrom=");
        sb.append(getDamageFrom());
        sb.append(", damageTo=");
        sb.append(getDamageTo());
        sb.append(", mitigationFrom=");
        sb.append(getMitigationFrom());
        sb.append(", mitigationTo=");
        sb.append(getMitigationTo());
        sb.append(", dateFrom=");
        sb.append(getDateFrom());
        sb.append(", dateTo=");
        sb.append(getDateTo());
        sb.append(", filterPositionsAll=");
        sb.append(getFilterPositionsAll());
        sb.append(", enabled=");
        sb.append(getEnabled());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlansFilter");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planTypeId</column-name><column-value><![CDATA[");
        sb.append(getPlanTypeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>creator</column-name><column-value><![CDATA[");
        sb.append(getCreator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>CO2From</column-name><column-value><![CDATA[");
        sb.append(getCO2From());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>CO2To</column-name><column-value><![CDATA[");
        sb.append(getCO2To());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>votesFrom</column-name><column-value><![CDATA[");
        sb.append(getVotesFrom());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>votesTo</column-name><column-value><![CDATA[");
        sb.append(getVotesTo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>damageFrom</column-name><column-value><![CDATA[");
        sb.append(getDamageFrom());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>damageTo</column-name><column-value><![CDATA[");
        sb.append(getDamageTo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>mitigationFrom</column-name><column-value><![CDATA[");
        sb.append(getMitigationFrom());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>mitigationTo</column-name><column-value><![CDATA[");
        sb.append(getMitigationTo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>dateFrom</column-name><column-value><![CDATA[");
        sb.append(getDateFrom());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>dateTo</column-name><column-value><![CDATA[");
        sb.append(getDateTo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>filterPositionsAll</column-name><column-value><![CDATA[");
        sb.append(getFilterPositionsAll());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>enabled</column-name><column-value><![CDATA[");
        sb.append(getEnabled());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
