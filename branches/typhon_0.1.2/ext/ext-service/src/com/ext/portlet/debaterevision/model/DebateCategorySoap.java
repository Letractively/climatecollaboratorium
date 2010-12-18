package com.ext.portlet.debaterevision.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <a href="DebateCategorySoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.debaterevision.service.http.DebateCategoryServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.service.http.DebateCategoryServiceSoap
 *
 */
public class DebateCategorySoap implements Serializable {
    private Long _debateCategoryPK;
    private Long _authorId;
    private String _title;
    private Long _brandingContentId;
    private String _description;
    private Long _parentCategory;
    private Date _updated;

    public DebateCategorySoap() {
    }

    public static DebateCategorySoap toSoapModel(DebateCategory model) {
        DebateCategorySoap soapModel = new DebateCategorySoap();

        soapModel.setDebateCategoryPK(model.getDebateCategoryPK());
        soapModel.setAuthorId(model.getAuthorId());
        soapModel.setTitle(model.getTitle());
        soapModel.setBrandingContentId(model.getBrandingContentId());
        soapModel.setDescription(model.getDescription());
        soapModel.setParentCategory(model.getParentCategory());
        soapModel.setUpdated(model.getUpdated());

        return soapModel;
    }

    public static DebateCategorySoap[] toSoapModels(DebateCategory[] models) {
        DebateCategorySoap[] soapModels = new DebateCategorySoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static DebateCategorySoap[][] toSoapModels(DebateCategory[][] models) {
        DebateCategorySoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new DebateCategorySoap[models.length][models[0].length];
        } else {
            soapModels = new DebateCategorySoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static DebateCategorySoap[] toSoapModels(List<DebateCategory> models) {
        List<DebateCategorySoap> soapModels = new ArrayList<DebateCategorySoap>(models.size());

        for (DebateCategory model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new DebateCategorySoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _debateCategoryPK;
    }

    public void setPrimaryKey(Long pk) {
        setDebateCategoryPK(pk);
    }

    public Long getDebateCategoryPK() {
        return _debateCategoryPK;
    }

    public void setDebateCategoryPK(Long debateCategoryPK) {
        _debateCategoryPK = debateCategoryPK;
    }

    public Long getAuthorId() {
        return _authorId;
    }

    public void setAuthorId(Long authorId) {
        _authorId = authorId;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = title;
    }

    public Long getBrandingContentId() {
        return _brandingContentId;
    }

    public void setBrandingContentId(Long brandingContentId) {
        _brandingContentId = brandingContentId;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public Long getParentCategory() {
        return _parentCategory;
    }

    public void setParentCategory(Long parentCategory) {
        _parentCategory = parentCategory;
    }

    public Date getUpdated() {
        return _updated;
    }

    public void setUpdated(Date updated) {
        _updated = updated;
    }
}
