package com.ext.portlet.debaterevision.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <a href="DebateCommentSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.debaterevision.service.http.DebateCommentServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.service.http.DebateCommentServiceSoap
 *
 */
public class DebateCommentSoap implements Serializable {
    private Long _debateCommentId;
    private Long _debateItemId;
    private String _debateCommentTitle;
    private String _debateCommentDetail;
    private Long _itemVersion;
    private Long _authorId;
    private Date _updated;

    public DebateCommentSoap() {
    }

    public static DebateCommentSoap toSoapModel(DebateComment model) {
        DebateCommentSoap soapModel = new DebateCommentSoap();

        soapModel.setDebateCommentId(model.getDebateCommentId());
        soapModel.setDebateItemId(model.getDebateItemId());
        soapModel.setDebateCommentTitle(model.getDebateCommentTitle());
        soapModel.setDebateCommentDetail(model.getDebateCommentDetail());
        soapModel.setItemVersion(model.getItemVersion());
        soapModel.setAuthorId(model.getAuthorId());
        soapModel.setUpdated(model.getUpdated());

        return soapModel;
    }

    public static DebateCommentSoap[] toSoapModels(DebateComment[] models) {
        DebateCommentSoap[] soapModels = new DebateCommentSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static DebateCommentSoap[][] toSoapModels(DebateComment[][] models) {
        DebateCommentSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new DebateCommentSoap[models.length][models[0].length];
        } else {
            soapModels = new DebateCommentSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static DebateCommentSoap[] toSoapModels(List<DebateComment> models) {
        List<DebateCommentSoap> soapModels = new ArrayList<DebateCommentSoap>(models.size());

        for (DebateComment model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new DebateCommentSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _debateCommentId;
    }

    public void setPrimaryKey(Long pk) {
        setDebateCommentId(pk);
    }

    public Long getDebateCommentId() {
        return _debateCommentId;
    }

    public void setDebateCommentId(Long debateCommentId) {
        _debateCommentId = debateCommentId;
    }

    public Long getDebateItemId() {
        return _debateItemId;
    }

    public void setDebateItemId(Long debateItemId) {
        _debateItemId = debateItemId;
    }

    public String getDebateCommentTitle() {
        return _debateCommentTitle;
    }

    public void setDebateCommentTitle(String debateCommentTitle) {
        _debateCommentTitle = debateCommentTitle;
    }

    public String getDebateCommentDetail() {
        return _debateCommentDetail;
    }

    public void setDebateCommentDetail(String debateCommentDetail) {
        _debateCommentDetail = debateCommentDetail;
    }

    public Long getItemVersion() {
        return _itemVersion;
    }

    public void setItemVersion(Long itemVersion) {
        _itemVersion = itemVersion;
    }

    public Long getAuthorId() {
        return _authorId;
    }

    public void setAuthorId(Long authorId) {
        _authorId = authorId;
    }

    public Date getUpdated() {
        return _updated;
    }

    public void setUpdated(Date updated) {
        _updated = updated;
    }
}
