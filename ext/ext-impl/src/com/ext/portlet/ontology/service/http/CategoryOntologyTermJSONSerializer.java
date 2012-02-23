package com.ext.portlet.ontology.service.http;

import com.ext.portlet.ontology.model.CategoryOntologyTerm;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="CategoryOntologyTermJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.ontology.service.http.CategoryOntologyTermServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.ontology.service.http.CategoryOntologyTermServiceJSON
 *
 */
public class CategoryOntologyTermJSONSerializer {
    public static JSONObject toJSONObject(CategoryOntologyTerm model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("categoryId", model.getCategoryId());
        jsonObj.put("ontologyTerm", model.getOntologyTerm());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.ontology.model.CategoryOntologyTerm[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (CategoryOntologyTerm model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.ontology.model.CategoryOntologyTerm[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (CategoryOntologyTerm[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.ontology.model.CategoryOntologyTerm> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (CategoryOntologyTerm model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
