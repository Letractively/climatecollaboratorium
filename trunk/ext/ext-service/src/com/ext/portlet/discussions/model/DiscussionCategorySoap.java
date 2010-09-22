package com.ext.portlet.discussions.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <a href="DiscussionCategorySoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.discussions.service.http.DiscussionCategoryServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.discussions.service.http.DiscussionCategoryServiceSoap
 *
 */
public class DiscussionCategorySoap implements Serializable {
    private Long _pk;
    private Long _categoryId;
    private Long _categoryGroupId;
    private Long _authorId;
    private String _name;
    private String _description;
    private Date _createDate;
    private Date _deleted;
    private Integer _threadsCount;
    private Date _lastActivityDate;
    private Long _lastActivityAuthorId;

    public DiscussionCategorySoap() {
    }

    public static DiscussionCategorySoap toSoapModel(DiscussionCategory model) {
        DiscussionCategorySoap soapModel = new DiscussionCategorySoap();

        soapModel.setPk(model.getPk());
        soapModel.setCategoryId(model.getCategoryId());
        soapModel.setCategoryGroupId(model.getCategoryGroupId());
        soapModel.setAuthorId(model.getAuthorId());
        soapModel.setName(model.getName());
        soapModel.setDescription(model.getDescription());
        soapModel.setCreateDate(model.getCreateDate());
        soapModel.setDeleted(model.getDeleted());
        soapModel.setThreadsCount(model.getThreadsCount());
        soapModel.setLastActivityDate(model.getLastActivityDate());
        soapModel.setLastActivityAuthorId(model.getLastActivityAuthorId());

        return soapModel;
    }

    public static DiscussionCategorySoap[] toSoapModels(
        DiscussionCategory[] models) {
        DiscussionCategorySoap[] soapModels = new DiscussionCategorySoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static DiscussionCategorySoap[][] toSoapModels(
        DiscussionCategory[][] models) {
        DiscussionCategorySoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new DiscussionCategorySoap[models.length][models[0].length];
        } else {
            soapModels = new DiscussionCategorySoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static DiscussionCategorySoap[] toSoapModels(
        List<DiscussionCategory> models) {
        List<DiscussionCategorySoap> soapModels = new ArrayList<DiscussionCategorySoap>(models.size());

        for (DiscussionCategory model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new DiscussionCategorySoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _pk;
    }

    public void setPrimaryKey(Long pk) {
        setPk(pk);
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
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getDescription() {
        return _description;
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
}