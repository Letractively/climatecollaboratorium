package com.ext.portlet.debaterevision.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="DebateItemReferenceSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.debaterevision.service.http.DebateItemReferenceServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.service.http.DebateItemReferenceServiceSoap
 *
 */
public class DebateItemReferenceSoap implements Serializable {
    private Long _debateItemReferencePK;
    private Long _debateItemId;
    private Long _debateId;
    private Long _itemVersion;
    private String _status;
    private String _text;
    private String _url;

    public DebateItemReferenceSoap() {
    }

    public static DebateItemReferenceSoap toSoapModel(DebateItemReference model) {
        DebateItemReferenceSoap soapModel = new DebateItemReferenceSoap();

        soapModel.setDebateItemReferencePK(model.getDebateItemReferencePK());
        soapModel.setDebateItemId(model.getDebateItemId());
        soapModel.setDebateId(model.getDebateId());
        soapModel.setItemVersion(model.getItemVersion());
        soapModel.setStatus(model.getStatus());
        soapModel.setText(model.getText());
        soapModel.setUrl(model.getUrl());

        return soapModel;
    }

    public static DebateItemReferenceSoap[] toSoapModels(
        DebateItemReference[] models) {
        DebateItemReferenceSoap[] soapModels = new DebateItemReferenceSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static DebateItemReferenceSoap[][] toSoapModels(
        DebateItemReference[][] models) {
        DebateItemReferenceSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new DebateItemReferenceSoap[models.length][models[0].length];
        } else {
            soapModels = new DebateItemReferenceSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static DebateItemReferenceSoap[] toSoapModels(
        List<DebateItemReference> models) {
        List<DebateItemReferenceSoap> soapModels = new ArrayList<DebateItemReferenceSoap>(models.size());

        for (DebateItemReference model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new DebateItemReferenceSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _debateItemReferencePK;
    }

    public void setPrimaryKey(Long pk) {
        setDebateItemReferencePK(pk);
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
        return _status;
    }

    public void setStatus(String status) {
        _status = status;
    }

    public String getText() {
        return _text;
    }

    public void setText(String text) {
        _text = text;
    }

    public String getUrl() {
        return _url;
    }

    public void setUrl(String url) {
        _url = url;
    }
}
