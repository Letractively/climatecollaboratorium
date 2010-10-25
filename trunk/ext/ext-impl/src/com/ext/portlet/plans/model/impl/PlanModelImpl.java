package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.Plan;
import com.ext.portlet.plans.model.PlanSoap;

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
 * <a href="PlanModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>Plan</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.Plan
 * @see com.ext.portlet.plans.model.PlanModel
 * @see com.ext.portlet.plans.model.impl.PlanImpl
 *
 */
public class PlanModelImpl extends BaseModelImpl<Plan> {
    public static final String TABLE_NAME = "Plan";
    public static final Object[][] TABLE_COLUMNS = {
            { "planId", new Integer(Types.BIGINT) },
            

            { "name", new Integer(Types.VARCHAR) },
            

            { "content", new Integer(Types.VARCHAR) },
            

            { "shortcontent", new Integer(Types.VARCHAR) },
            

            { "planTypeId", new Integer(Types.BIGINT) },
            

            { "companyId", new Integer(Types.BIGINT) },
            

            { "groupId", new Integer(Types.BIGINT) },
            

            { "childGroupId", new Integer(Types.BIGINT) },
            

            { "MBCategoryId", new Integer(Types.BIGINT) },
            

            { "scenarioId", new Integer(Types.VARCHAR) },
            

            { "topicId", new Integer(Types.VARCHAR) },
            

            { "votes", new Integer(Types.INTEGER) },
            

            { "createDate", new Integer(Types.TIMESTAMP) },
            

            { "publishDate", new Integer(Types.TIMESTAMP) },
            

            { "userId", new Integer(Types.BIGINT) },
            

            { "userName", new Integer(Types.VARCHAR) },
            

            { "userScreenName", new Integer(Types.VARCHAR) },
            

            { "modifiedDate", new Integer(Types.TIMESTAMP) }
        };
    public static final String TABLE_SQL_CREATE = "create table Plan (planId LONG not null primary key,name VARCHAR(75) null,content VARCHAR(75) null,shortcontent VARCHAR(75) null,planTypeId LONG,companyId LONG,groupId LONG,childGroupId LONG,MBCategoryId LONG,scenarioId VARCHAR(75) null,topicId VARCHAR(75) null,votes INTEGER,createDate DATE null,publishDate DATE null,userId LONG,userName VARCHAR(75) null,userScreenName VARCHAR(75) null,modifiedDate DATE null)";
    public static final String TABLE_SQL_DROP = "drop table Plan";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.com.ext.portlet.plans.model.Plan"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.com.ext.portlet.plans.model.Plan"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.com.ext.portlet.plans.model.Plan"));
    private Long _planId;
    private String _name;
    private String _content;
    private String _shortcontent;
    private Long _planTypeId;
    private Long _companyId;
    private Long _groupId;
    private Long _childGroupId;
    private Long _MBCategoryId;
    private String _scenarioId;
    private String _topicId;
    private Integer _votes;
    private Date _createDate;
    private Date _publishDate;
    private Long _userId;
    private String _userName;
    private String _userScreenName;
    private Date _modifiedDate;

    public PlanModelImpl() {
    }

    public static Plan toModel(PlanSoap soapModel) {
        Plan model = new PlanImpl();

        model.setPlanId(soapModel.getPlanId());
        model.setName(soapModel.getName());
        model.setContent(soapModel.getContent());
        model.setShortcontent(soapModel.getShortcontent());
        model.setPlanTypeId(soapModel.getPlanTypeId());
        model.setCompanyId(soapModel.getCompanyId());
        model.setGroupId(soapModel.getGroupId());
        model.setChildGroupId(soapModel.getChildGroupId());
        model.setMBCategoryId(soapModel.getMBCategoryId());
        model.setScenarioId(soapModel.getScenarioId());
        model.setTopicId(soapModel.getTopicId());
        model.setVotes(soapModel.getVotes());
        model.setCreateDate(soapModel.getCreateDate());
        model.setPublishDate(soapModel.getPublishDate());
        model.setUserId(soapModel.getUserId());
        model.setUserName(soapModel.getUserName());
        model.setUserScreenName(soapModel.getUserScreenName());
        model.setModifiedDate(soapModel.getModifiedDate());

        return model;
    }

    public static List<Plan> toModels(PlanSoap[] soapModels) {
        List<Plan> models = new ArrayList<Plan>(soapModels.length);

        for (PlanSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public Long getPrimaryKey() {
        return _planId;
    }

    public void setPrimaryKey(Long pk) {
        setPlanId(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return _planId;
    }

    public Long getPlanId() {
        return _planId;
    }

    public void setPlanId(Long planId) {
        _planId = planId;
    }

    public String getName() {
        return GetterUtil.getString(_name);
    }

    public void setName(String name) {
        _name = name;
    }

    public String getContent() {
        return GetterUtil.getString(_content);
    }

    public void setContent(String content) {
        _content = content;
    }

    public String getShortcontent() {
        return GetterUtil.getString(_shortcontent);
    }

    public void setShortcontent(String shortcontent) {
        _shortcontent = shortcontent;
    }

    public Long getPlanTypeId() {
        return _planTypeId;
    }

    public void setPlanTypeId(Long planTypeId) {
        _planTypeId = planTypeId;
    }

    public Long getCompanyId() {
        return _companyId;
    }

    public void setCompanyId(Long companyId) {
        _companyId = companyId;
    }

    public Long getGroupId() {
        return _groupId;
    }

    public void setGroupId(Long groupId) {
        _groupId = groupId;
    }

    public Long getChildGroupId() {
        return _childGroupId;
    }

    public void setChildGroupId(Long childGroupId) {
        _childGroupId = childGroupId;
    }

    public Long getMBCategoryId() {
        return _MBCategoryId;
    }

    public void setMBCategoryId(Long MBCategoryId) {
        _MBCategoryId = MBCategoryId;
    }

    public String getScenarioId() {
        return GetterUtil.getString(_scenarioId);
    }

    public void setScenarioId(String scenarioId) {
        _scenarioId = scenarioId;
    }

    public String getTopicId() {
        return GetterUtil.getString(_topicId);
    }

    public void setTopicId(String topicId) {
        _topicId = topicId;
    }

    public Integer getVotes() {
        return _votes;
    }

    public void setVotes(Integer votes) {
        _votes = votes;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public Date getPublishDate() {
        return _publishDate;
    }

    public void setPublishDate(Date publishDate) {
        _publishDate = publishDate;
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }

    public String getUserName() {
        return GetterUtil.getString(_userName);
    }

    public void setUserName(String userName) {
        _userName = userName;
    }

    public String getUserScreenName() {
        return GetterUtil.getString(_userScreenName);
    }

    public void setUserScreenName(String userScreenName) {
        _userScreenName = userScreenName;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public Plan toEscapedModel() {
        if (isEscapedModel()) {
            return (Plan) this;
        } else {
            Plan model = new PlanImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setPlanId(getPlanId());
            model.setName(HtmlUtil.escape(getName()));
            model.setContent(HtmlUtil.escape(getContent()));
            model.setShortcontent(HtmlUtil.escape(getShortcontent()));
            model.setPlanTypeId(getPlanTypeId());
            model.setCompanyId(getCompanyId());
            model.setGroupId(getGroupId());
            model.setChildGroupId(getChildGroupId());
            model.setMBCategoryId(getMBCategoryId());
            model.setScenarioId(HtmlUtil.escape(getScenarioId()));
            model.setTopicId(HtmlUtil.escape(getTopicId()));
            model.setVotes(getVotes());
            model.setCreateDate(getCreateDate());
            model.setPublishDate(getPublishDate());
            model.setUserId(getUserId());
            model.setUserName(HtmlUtil.escape(getUserName()));
            model.setUserScreenName(HtmlUtil.escape(getUserScreenName()));
            model.setModifiedDate(getModifiedDate());

            model = (Plan) Proxy.newProxyInstance(Plan.class.getClassLoader(),
                    new Class[] { Plan.class }, new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public Object clone() {
        PlanImpl clone = new PlanImpl();

        clone.setPlanId(getPlanId());
        clone.setName(getName());
        clone.setContent(getContent());
        clone.setShortcontent(getShortcontent());
        clone.setPlanTypeId(getPlanTypeId());
        clone.setCompanyId(getCompanyId());
        clone.setGroupId(getGroupId());
        clone.setChildGroupId(getChildGroupId());
        clone.setMBCategoryId(getMBCategoryId());
        clone.setScenarioId(getScenarioId());
        clone.setTopicId(getTopicId());
        clone.setVotes(getVotes());
        clone.setCreateDate(getCreateDate());
        clone.setPublishDate(getPublishDate());
        clone.setUserId(getUserId());
        clone.setUserName(getUserName());
        clone.setUserScreenName(getUserScreenName());
        clone.setModifiedDate(getModifiedDate());

        return clone;
    }

    public int compareTo(Plan plan) {
        int value = 0;

        value = getName().toLowerCase().compareTo(plan.getName().toLowerCase());

        if (value != 0) {
            return value;
        }

        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        Plan plan = null;

        try {
            plan = (Plan) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long pk = plan.getPrimaryKey();

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

        sb.append("{planId=");
        sb.append(getPlanId());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", content=");
        sb.append(getContent());
        sb.append(", shortcontent=");
        sb.append(getShortcontent());
        sb.append(", planTypeId=");
        sb.append(getPlanTypeId());
        sb.append(", companyId=");
        sb.append(getCompanyId());
        sb.append(", groupId=");
        sb.append(getGroupId());
        sb.append(", childGroupId=");
        sb.append(getChildGroupId());
        sb.append(", MBCategoryId=");
        sb.append(getMBCategoryId());
        sb.append(", scenarioId=");
        sb.append(getScenarioId());
        sb.append(", topicId=");
        sb.append(getTopicId());
        sb.append(", votes=");
        sb.append(getVotes());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append(", publishDate=");
        sb.append(getPublishDate());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", userName=");
        sb.append(getUserName());
        sb.append(", userScreenName=");
        sb.append(getUserScreenName());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.Plan");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>planId</column-name><column-value><![CDATA[");
        sb.append(getPlanId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>content</column-name><column-value><![CDATA[");
        sb.append(getContent());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>shortcontent</column-name><column-value><![CDATA[");
        sb.append(getShortcontent());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planTypeId</column-name><column-value><![CDATA[");
        sb.append(getPlanTypeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyId</column-name><column-value><![CDATA[");
        sb.append(getCompanyId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>groupId</column-name><column-value><![CDATA[");
        sb.append(getGroupId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>childGroupId</column-name><column-value><![CDATA[");
        sb.append(getChildGroupId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>MBCategoryId</column-name><column-value><![CDATA[");
        sb.append(getMBCategoryId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>scenarioId</column-name><column-value><![CDATA[");
        sb.append(getScenarioId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>topicId</column-name><column-value><![CDATA[");
        sb.append(getTopicId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>votes</column-name><column-value><![CDATA[");
        sb.append(getVotes());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>publishDate</column-name><column-value><![CDATA[");
        sb.append(getPublishDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userName</column-name><column-value><![CDATA[");
        sb.append(getUserName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userScreenName</column-name><column-value><![CDATA[");
        sb.append(getUserScreenName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
