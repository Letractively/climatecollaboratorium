package com.ext.portlet.ontology.model;

import com.ext.portlet.ontology.service.persistence.CategoryOntologyTermPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="CategoryOntologyTermSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.ontology.service.http.CategoryOntologyTermServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.ontology.service.http.CategoryOntologyTermServiceSoap
 *
 */
public class CategoryOntologyTermSoap implements Serializable {
    private Long _categoryId;
    private Long _ontologyTerm;

    public CategoryOntologyTermSoap() {
    }

    public static CategoryOntologyTermSoap toSoapModel(
        CategoryOntologyTerm model) {
        CategoryOntologyTermSoap soapModel = new CategoryOntologyTermSoap();

        soapModel.setCategoryId(model.getCategoryId());
        soapModel.setOntologyTerm(model.getOntologyTerm());

        return soapModel;
    }

    public static CategoryOntologyTermSoap[] toSoapModels(
        CategoryOntologyTerm[] models) {
        CategoryOntologyTermSoap[] soapModels = new CategoryOntologyTermSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static CategoryOntologyTermSoap[][] toSoapModels(
        CategoryOntologyTerm[][] models) {
        CategoryOntologyTermSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new CategoryOntologyTermSoap[models.length][models[0].length];
        } else {
            soapModels = new CategoryOntologyTermSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static CategoryOntologyTermSoap[] toSoapModels(
        List<CategoryOntologyTerm> models) {
        List<CategoryOntologyTermSoap> soapModels = new ArrayList<CategoryOntologyTermSoap>(models.size());

        for (CategoryOntologyTerm model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new CategoryOntologyTermSoap[soapModels.size()]);
    }

    public CategoryOntologyTermPK getPrimaryKey() {
        return new CategoryOntologyTermPK(_categoryId, _ontologyTerm);
    }

    public void setPrimaryKey(CategoryOntologyTermPK pk) {
        setCategoryId(pk.categoryId);
        setOntologyTerm(pk.ontologyTerm);
    }

    public Long getCategoryId() {
        return _categoryId;
    }

    public void setCategoryId(Long categoryId) {
        _categoryId = categoryId;
    }

    public Long getOntologyTerm() {
        return _ontologyTerm;
    }

    public void setOntologyTerm(Long ontologyTerm) {
        _ontologyTerm = ontologyTerm;
    }
}
