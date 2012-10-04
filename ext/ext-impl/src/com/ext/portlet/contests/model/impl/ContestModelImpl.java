package com.ext.portlet.contests.model.impl;

import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.model.ContestSoap;

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
 * <a href="ContestModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>Contest</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.model.Contest
 * @see com.ext.portlet.contests.model.ContestModel
 * @see com.ext.portlet.contests.model.impl.ContestImpl
 *
 */
public class ContestModelImpl extends BaseModelImpl<Contest> {
    public static final String TABLE_NAME = "Contest";
    public static final Object[][] TABLE_COLUMNS = {
            { "ContestPK", new Integer(Types.BIGINT) },
            

            { "ContestName", new Integer(Types.VARCHAR) },
            

            { "ContestShortName", new Integer(Types.VARCHAR) },
            

            { "ContestDescription", new Integer(Types.VARCHAR) },
            

            { "ContestModelDescription", new Integer(Types.VARCHAR) },
            

            { "ContestPositionsDescription", new Integer(Types.VARCHAR) },
            

            { "defaultPlanDescription", new Integer(Types.VARCHAR) },
            

            { "PlanTypeId", new Integer(Types.BIGINT) },
            

            { "created", new Integer(Types.TIMESTAMP) },
            

            { "updated", new Integer(Types.TIMESTAMP) },
            

            { "authorId", new Integer(Types.BIGINT) },
            

            { "contestActive", new Integer(Types.BOOLEAN) },
            

            { "planTemplateId", new Integer(Types.BIGINT) },
            

            { "focusAreaId", new Integer(Types.BIGINT) },
            

            { "contestLogoId", new Integer(Types.BIGINT) },
            

            { "featured_", new Integer(Types.BOOLEAN) },
            

            { "plansOpenByDefault", new Integer(Types.BOOLEAN) },
            

            { "flag", new Integer(Types.INTEGER) },
            

            { "flagText", new Integer(Types.VARCHAR) },
            

            { "groupId", new Integer(Types.BIGINT) },
            

            { "discussionGroupId", new Integer(Types.BIGINT) },
            

            { "weight", new Integer(Types.INTEGER) },
            

            { "resourcesUrl", new Integer(Types.VARCHAR) }
        };
    public static final String TABLE_SQL_CREATE = "create table Contest (ContestPK LONG not null primary key,ContestName VARCHAR(75) null,ContestShortName VARCHAR(75) null,ContestDescription VARCHAR(75) null,ContestModelDescription VARCHAR(75) null,ContestPositionsDescription VARCHAR(75) null,defaultPlanDescription VARCHAR(75) null,PlanTypeId LONG,created DATE null,updated DATE null,authorId LONG,contestActive BOOLEAN,planTemplateId LONG,focusAreaId LONG,contestLogoId LONG,featured_ BOOLEAN,plansOpenByDefault BOOLEAN,flag INTEGER,flagText VARCHAR(75) null,groupId LONG,discussionGroupId LONG,weight INTEGER,resourcesUrl VARCHAR(75) null)";
    public static final String TABLE_SQL_DROP = "drop table Contest";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.contests.model.Contest"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.contests.model.Contest"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.contests.model.Contest"));
    private Long _ContestPK;
    private String _ContestName;
    private String _ContestShortName;
    private String _ContestDescription;
    private String _ContestModelDescription;
    private String _ContestPositionsDescription;
    private String _defaultPlanDescription;
    private Long _PlanTypeId;
    private Date _created;
    private Date _updated;
    private Long _authorId;
    private Boolean _contestActive;
    private Boolean _originalContestActive;
    private Long _planTemplateId;
    private Long _focusAreaId;
    private Long _contestLogoId;
    private Boolean _featured;
    private Boolean _plansOpenByDefault;
    private Integer _flag;
    private String _flagText;
    private Long _groupId;
    private Long _discussionGroupId;
    private Integer _weight;
    private String _resourcesUrl;

    public ContestModelImpl() {
    }

    public static Contest toModel(ContestSoap soapModel) {
        Contest model = new ContestImpl();

        model.setContestPK(soapModel.getContestPK());
        model.setContestName(soapModel.getContestName());
        model.setContestShortName(soapModel.getContestShortName());
        model.setContestDescription(soapModel.getContestDescription());
        model.setContestModelDescription(soapModel.getContestModelDescription());
        model.setContestPositionsDescription(soapModel.getContestPositionsDescription());
        model.setDefaultPlanDescription(soapModel.getDefaultPlanDescription());
        model.setPlanTypeId(soapModel.getPlanTypeId());
        model.setCreated(soapModel.getCreated());
        model.setUpdated(soapModel.getUpdated());
        model.setAuthorId(soapModel.getAuthorId());
        model.setContestActive(soapModel.getContestActive());
        model.setPlanTemplateId(soapModel.getPlanTemplateId());
        model.setFocusAreaId(soapModel.getFocusAreaId());
        model.setContestLogoId(soapModel.getContestLogoId());
        model.setFeatured(soapModel.getFeatured());
        model.setPlansOpenByDefault(soapModel.getPlansOpenByDefault());
        model.setFlag(soapModel.getFlag());
        model.setFlagText(soapModel.getFlagText());
        model.setGroupId(soapModel.getGroupId());
        model.setDiscussionGroupId(soapModel.getDiscussionGroupId());
        model.setWeight(soapModel.getWeight());
        model.setResourcesUrl(soapModel.getResourcesUrl());

        return model;
    }

    public static List<Contest> toModels(ContestSoap[] soapModels) {
        List<Contest> models = new ArrayList<Contest>(soapModels.length);

        for (ContestSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _ContestPK;
    }

    public void setPrimaryKey(Long pk) {
        setContestPK(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _ContestPK;
    }

    public Long getContestPK() {
        return _ContestPK;
    }

    public void setContestPK(Long ContestPK) {
        _ContestPK = ContestPK;
    }

    public String getContestName() {
        return GetterUtil.getString(_ContestName);
    }

    public void setContestName(String ContestName) {
        _ContestName = ContestName;
    }

    public String getContestShortName() {
        return GetterUtil.getString(_ContestShortName);
    }

    public void setContestShortName(String ContestShortName) {
        _ContestShortName = ContestShortName;
    }

    public String getContestDescription() {
        return GetterUtil.getString(_ContestDescription);
    }

    public void setContestDescription(String ContestDescription) {
        _ContestDescription = ContestDescription;
    }

    public String getContestModelDescription() {
        return GetterUtil.getString(_ContestModelDescription);
    }

    public void setContestModelDescription(String ContestModelDescription) {
        _ContestModelDescription = ContestModelDescription;
    }

    public String getContestPositionsDescription() {
        return GetterUtil.getString(_ContestPositionsDescription);
    }

    public void setContestPositionsDescription(
        String ContestPositionsDescription) {
        _ContestPositionsDescription = ContestPositionsDescription;
    }

    public String getDefaultPlanDescription() {
        return GetterUtil.getString(_defaultPlanDescription);
    }

    public void setDefaultPlanDescription(String defaultPlanDescription) {
        _defaultPlanDescription = defaultPlanDescription;
    }

    public Long getPlanTypeId() {
        return _PlanTypeId;
    }

    public void setPlanTypeId(Long PlanTypeId) {
        _PlanTypeId = PlanTypeId;
    }

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        _created = created;
    }

    public Date getUpdated() {
        return _updated;
    }

    public void setUpdated(Date updated) {
        _updated = updated;
    }

    public Long getAuthorId() {
        return _authorId;
    }

    public void setAuthorId(Long authorId) {
        _authorId = authorId;
    }

    public Boolean getContestActive() {
        return _contestActive;
    }

    public void setContestActive(Boolean contestActive) {
        _contestActive = contestActive;

        if (_originalContestActive == null) {
            _originalContestActive = contestActive;
        }
    }

    public Boolean getOriginalContestActive() {
        return _originalContestActive;
    }

    public Long getPlanTemplateId() {
        return _planTemplateId;
    }

    public void setPlanTemplateId(Long planTemplateId) {
        _planTemplateId = planTemplateId;
    }

    public Long getFocusAreaId() {
        return _focusAreaId;
    }

    public void setFocusAreaId(Long focusAreaId) {
        _focusAreaId = focusAreaId;
    }

    public Long getContestLogoId() {
        return _contestLogoId;
    }

    public void setContestLogoId(Long contestLogoId) {
        _contestLogoId = contestLogoId;
    }

    public Boolean getFeatured() {
        return _featured;
    }

    public void setFeatured(Boolean featured) {
        _featured = featured;
    }

    public Boolean getPlansOpenByDefault() {
        return _plansOpenByDefault;
    }

    public void setPlansOpenByDefault(Boolean plansOpenByDefault) {
        _plansOpenByDefault = plansOpenByDefault;
    }

    public Integer getFlag() {
        return _flag;
    }

    public void setFlag(Integer flag) {
        _flag = flag;
    }

    public String getFlagText() {
        return GetterUtil.getString(_flagText);
    }

    public void setFlagText(String flagText) {
        _flagText = flagText;
    }

    public Long getGroupId() {
        return _groupId;
    }

    public void setGroupId(Long groupId) {
        _groupId = groupId;
    }

    public Long getDiscussionGroupId() {
        return _discussionGroupId;
    }

    public void setDiscussionGroupId(Long discussionGroupId) {
        _discussionGroupId = discussionGroupId;
    }

    public Integer getWeight() {
        return _weight;
    }

    public void setWeight(Integer weight) {
        _weight = weight;
    }

    public String getResourcesUrl() {
        return GetterUtil.getString(_resourcesUrl);
    }

    public void setResourcesUrl(String resourcesUrl) {
        _resourcesUrl = resourcesUrl;
    }

    public Contest toEscapedModel() {
        if (isEscapedModel()) {
            return (Contest) this;
        } else {
            Contest model = new ContestImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setContestPK(getContestPK());
            model.setContestName(HtmlUtil.escape(getContestName()));
            model.setContestShortName(HtmlUtil.escape(getContestShortName()));
            model.setContestDescription(HtmlUtil.escape(getContestDescription()));
            model.setContestModelDescription(HtmlUtil.escape(
                    getContestModelDescription()));
            model.setContestPositionsDescription(HtmlUtil.escape(
                    getContestPositionsDescription()));
            model.setDefaultPlanDescription(HtmlUtil.escape(
                    getDefaultPlanDescription()));
            model.setPlanTypeId(getPlanTypeId());
            model.setCreated(getCreated());
            model.setUpdated(getUpdated());
            model.setAuthorId(getAuthorId());
            model.setContestActive(getContestActive());
            model.setPlanTemplateId(getPlanTemplateId());
            model.setFocusAreaId(getFocusAreaId());
            model.setContestLogoId(getContestLogoId());
            model.setFeatured(getFeatured());
            model.setPlansOpenByDefault(getPlansOpenByDefault());
            model.setFlag(getFlag());
            model.setFlagText(HtmlUtil.escape(getFlagText()));
            model.setGroupId(getGroupId());
            model.setDiscussionGroupId(getDiscussionGroupId());
            model.setWeight(getWeight());
            model.setResourcesUrl(HtmlUtil.escape(getResourcesUrl()));

            model = (Contest) Proxy.newProxyInstance(Contest.class.getClassLoader(),
                    new Class[] { Contest.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        ContestImpl clone = new ContestImpl();

        clone.setContestPK(getContestPK());
        clone.setContestName(getContestName());
        clone.setContestShortName(getContestShortName());
        clone.setContestDescription(getContestDescription());
        clone.setContestModelDescription(getContestModelDescription());
        clone.setContestPositionsDescription(getContestPositionsDescription());
        clone.setDefaultPlanDescription(getDefaultPlanDescription());
        clone.setPlanTypeId(getPlanTypeId());
        clone.setCreated(getCreated());
        clone.setUpdated(getUpdated());
        clone.setAuthorId(getAuthorId());
        clone.setContestActive(getContestActive());
        clone.setPlanTemplateId(getPlanTemplateId());
        clone.setFocusAreaId(getFocusAreaId());
        clone.setContestLogoId(getContestLogoId());
        clone.setFeatured(getFeatured());
        clone.setPlansOpenByDefault(getPlansOpenByDefault());
        clone.setFlag(getFlag());
        clone.setFlagText(getFlagText());
        clone.setGroupId(getGroupId());
        clone.setDiscussionGroupId(getDiscussionGroupId());
        clone.setWeight(getWeight());
        clone.setResourcesUrl(getResourcesUrl());

        return clone;
    }

    public int compareTo(Contest contest) {
        int value = 0;

        value = getWeight().compareTo(contest.getWeight());

        if (value != 0) {
            return value;
        }

        value = DateUtil.compareTo(getCreated(), contest.getCreated());

        if (value != 0) {
            return value;
        }

        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        Contest contest = null;

        try {
            contest = (Contest) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = contest.getPrimaryKey();

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

        sb.append("{ContestPK=");
        sb.append(getContestPK());
        sb.append(", ContestName=");
        sb.append(getContestName());
        sb.append(", ContestShortName=");
        sb.append(getContestShortName());
        sb.append(", ContestDescription=");
        sb.append(getContestDescription());
        sb.append(", ContestModelDescription=");
        sb.append(getContestModelDescription());
        sb.append(", ContestPositionsDescription=");
        sb.append(getContestPositionsDescription());
        sb.append(", defaultPlanDescription=");
        sb.append(getDefaultPlanDescription());
        sb.append(", PlanTypeId=");
        sb.append(getPlanTypeId());
        sb.append(", created=");
        sb.append(getCreated());
        sb.append(", updated=");
        sb.append(getUpdated());
        sb.append(", authorId=");
        sb.append(getAuthorId());
        sb.append(", contestActive=");
        sb.append(getContestActive());
        sb.append(", planTemplateId=");
        sb.append(getPlanTemplateId());
        sb.append(", focusAreaId=");
        sb.append(getFocusAreaId());
        sb.append(", contestLogoId=");
        sb.append(getContestLogoId());
        sb.append(", featured=");
        sb.append(getFeatured());
        sb.append(", plansOpenByDefault=");
        sb.append(getPlansOpenByDefault());
        sb.append(", flag=");
        sb.append(getFlag());
        sb.append(", flagText=");
        sb.append(getFlagText());
        sb.append(", groupId=");
        sb.append(getGroupId());
        sb.append(", discussionGroupId=");
        sb.append(getDiscussionGroupId());
        sb.append(", weight=");
        sb.append(getWeight());
        sb.append(", resourcesUrl=");
        sb.append(getResourcesUrl());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.contests.model.Contest");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>ContestPK</column-name><column-value><![CDATA[");
        sb.append(getContestPK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ContestName</column-name><column-value><![CDATA[");
        sb.append(getContestName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ContestShortName</column-name><column-value><![CDATA[");
        sb.append(getContestShortName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ContestDescription</column-name><column-value><![CDATA[");
        sb.append(getContestDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ContestModelDescription</column-name><column-value><![CDATA[");
        sb.append(getContestModelDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ContestPositionsDescription</column-name><column-value><![CDATA[");
        sb.append(getContestPositionsDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>defaultPlanDescription</column-name><column-value><![CDATA[");
        sb.append(getDefaultPlanDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>PlanTypeId</column-name><column-value><![CDATA[");
        sb.append(getPlanTypeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>created</column-name><column-value><![CDATA[");
        sb.append(getCreated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updated</column-name><column-value><![CDATA[");
        sb.append(getUpdated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>authorId</column-name><column-value><![CDATA[");
        sb.append(getAuthorId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contestActive</column-name><column-value><![CDATA[");
        sb.append(getContestActive());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planTemplateId</column-name><column-value><![CDATA[");
        sb.append(getPlanTemplateId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>focusAreaId</column-name><column-value><![CDATA[");
        sb.append(getFocusAreaId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contestLogoId</column-name><column-value><![CDATA[");
        sb.append(getContestLogoId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>featured</column-name><column-value><![CDATA[");
        sb.append(getFeatured());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>plansOpenByDefault</column-name><column-value><![CDATA[");
        sb.append(getPlansOpenByDefault());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>flag</column-name><column-value><![CDATA[");
        sb.append(getFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>flagText</column-name><column-value><![CDATA[");
        sb.append(getFlagText());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>groupId</column-name><column-value><![CDATA[");
        sb.append(getGroupId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>discussionGroupId</column-name><column-value><![CDATA[");
        sb.append(getDiscussionGroupId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>weight</column-name><column-value><![CDATA[");
        sb.append(getWeight());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>resourcesUrl</column-name><column-value><![CDATA[");
        sb.append(getResourcesUrl());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
