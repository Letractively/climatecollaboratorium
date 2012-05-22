package com.ext.portlet.ontology.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="FocusAreaSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.ontology.service.http.FocusAreaServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.ontology.service.http.FocusAreaServiceSoap
 *
 */
public class FocusAreaSoap implements Serializable {
    private Long _id;
    private String _name;

    public FocusAreaSoap() {
    }

    public static FocusAreaSoap toSoapModel(FocusArea model) {
        FocusAreaSoap soapModel = new FocusAreaSoap();

        soapModel.setId(model.getId());
        soapModel.setName(model.getName());

        return soapModel;
    }

    public static FocusAreaSoap[] toSoapModels(FocusArea[] models) {
        FocusAreaSoap[] soapModels = new FocusAreaSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static FocusAreaSoap[][] toSoapModels(FocusArea[][] models) {
        FocusAreaSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new FocusAreaSoap[models.length][models[0].length];
        } else {
            soapModels = new FocusAreaSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static FocusAreaSoap[] toSoapModels(List<FocusArea> models) {
        List<FocusAreaSoap> soapModels = new ArrayList<FocusAreaSoap>(models.size());

        for (FocusArea model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new FocusAreaSoap[soapModels.size()]);
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