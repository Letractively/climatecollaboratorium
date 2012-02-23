package com.ext.portlet.ontology.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="CategorySoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.ontology.service.http.CategoryServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.ontology.service.http.CategoryServiceSoap
 *
 */
public class CategorySoap implements Serializable {
    private Long _id;
    private String _name;

    public CategorySoap() {
    }

    public static CategorySoap toSoapModel(Category model) {
        CategorySoap soapModel = new CategorySoap();

        soapModel.setId(model.getId());
        soapModel.setName(model.getName());

        return soapModel;
    }

    public static CategorySoap[] toSoapModels(Category[] models) {
        CategorySoap[] soapModels = new CategorySoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static CategorySoap[][] toSoapModels(Category[][] models) {
        CategorySoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new CategorySoap[models.length][models[0].length];
        } else {
            soapModels = new CategorySoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static CategorySoap[] toSoapModels(List<Category> models) {
        List<CategorySoap> soapModels = new ArrayList<CategorySoap>(models.size());

        for (Category model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new CategorySoap[soapModels.size()]);
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

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }
}
