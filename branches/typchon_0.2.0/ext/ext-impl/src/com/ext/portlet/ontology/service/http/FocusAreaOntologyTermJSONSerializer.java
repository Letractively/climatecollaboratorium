package com.ext.portlet.ontology.service.http;

import com.ext.portlet.ontology.model.FocusAreaOntologyTerm;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="FocusAreaOntologyTermJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.ontology.service.http.FocusAreaOntologyTermServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.ontology.service.http.FocusAreaOntologyTermServiceJSON
 *
 */
public class FocusAreaOntologyTermJSONSerializer {
    public static JSONObject toJSONObject(FocusAreaOntologyTerm model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("focusAreaId", model.getFocusAreaId());
        jsonObj.put("ontologyTermId", model.getOntologyTermId());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.ontology.model.FocusAreaOntologyTerm[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (FocusAreaOntologyTerm model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.ontology.model.FocusAreaOntologyTerm[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (FocusAreaOntologyTerm[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.ontology.model.FocusAreaOntologyTerm> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (FocusAreaOntologyTerm model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
