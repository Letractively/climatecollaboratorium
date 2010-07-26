package com.ext.portlet.discussions.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="DiscussionCategoryGroupSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.discussions.service.http.DiscussionCategoryGroupServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.discussions.service.http.DiscussionCategoryGroupServiceSoap
 *
 */
public class DiscussionCategoryGroupSoap implements Serializable {
    private Long _id;
    private String _description;

    public DiscussionCategoryGroupSoap() {
    }

    public static DiscussionCategoryGroupSoap toSoapModel(
        DiscussionCategoryGroup model) {
        DiscussionCategoryGroupSoap soapModel = new DiscussionCategoryGroupSoap();

        soapModel.setId(model.getId());
        soapModel.setDescription(model.getDescription());

        return soapModel;
    }

    public static DiscussionCategoryGroupSoap[] toSoapModels(
        DiscussionCategoryGroup[] models) {
        DiscussionCategoryGroupSoap[] soapModels = new DiscussionCategoryGroupSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static DiscussionCategoryGroupSoap[][] toSoapModels(
        DiscussionCategoryGroup[][] models) {
        DiscussionCategoryGroupSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new DiscussionCategoryGroupSoap[models.length][models[0].length];
        } else {
            soapModels = new DiscussionCategoryGroupSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static DiscussionCategoryGroupSoap[] toSoapModels(
        List<DiscussionCategoryGroup> models) {
        List<DiscussionCategoryGroupSoap> soapModels = new ArrayList<DiscussionCategoryGroupSoap>(models.size());

        for (DiscussionCategoryGroup model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new DiscussionCategoryGroupSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _id;
    }

    public void setPrimaryKey(Long pk) {
        setId(pk);
    }

    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        _id = id;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }
}
