package com.ext.portlet.debaterevision.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <a href="DebateItemSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.debaterevision.service.http.DebateItemServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.service.http.DebateItemServiceSoap
 *
 */
public class DebateItemSoap implements Serializable {
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

    public DebateItemSoap() {
    }

    public static DebateItemSoap toSoapModel(DebateItem model) {
        DebateItemSoap soapModel = new DebateItemSoap();

        soapModel.setDebateItemPK(model.getDebateItemPK());
        soapModel.setDebateItemId(model.getDebateItemId());
        soapModel.setDebateItemParentId(model.getDebateItemParentId());
        soapModel.setDebateId(model.getDebateId());
        soapModel.setDebateSummary(model.getDebateSummary());
        soapModel.setDebateDetail(model.getDebateDetail());
        soapModel.setDebatePostType(model.getDebatePostType());
        soapModel.setAuthorId(model.getAuthorId());
        soapModel.setWeight(model.getWeight());
        soapModel.setItemVersion(model.getItemVersion());
        soapModel.setTreeVersion(model.getTreeVersion());
        soapModel.setUpdated(model.getUpdated());
        soapModel.setStatus(model.getStatus());

        return soapModel;
    }

    public static DebateItemSoap[] toSoapModels(DebateItem[] models) {
        DebateItemSoap[] soapModels = new DebateItemSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static DebateItemSoap[][] toSoapModels(DebateItem[][] models) {
        DebateItemSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new DebateItemSoap[models.length][models[0].length];
        } else {
            soapModels = new DebateItemSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static DebateItemSoap[] toSoapModels(List<DebateItem> models) {
        List<DebateItemSoap> soapModels = new ArrayList<DebateItemSoap>(models.size());

        for (DebateItem model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new DebateItemSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _debateItemPK;
    }

    public void setPrimaryKey(Long pk) {
        setDebateItemPK(pk);
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
        return _debateSummary;
    }

    public void setDebateSummary(String debateSummary) {
        _debateSummary = debateSummary;
    }

    public String getDebateDetail() {
        return _debateDetail;
    }

    public void setDebateDetail(String debateDetail) {
        _debateDetail = debateDetail;
    }

    public String getDebatePostType() {
        return _debatePostType;
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
        return _status;
    }

    public void setStatus(String status) {
        _status = status;
    }
}
