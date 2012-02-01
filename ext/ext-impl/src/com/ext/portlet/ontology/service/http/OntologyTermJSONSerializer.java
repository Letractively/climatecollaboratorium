package com.ext.portlet.ontology.service.http;

import com.ext.portlet.ontology.model.OntologyTerm;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="OntologyTermJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.ontology.service.http.OntologyTermServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.ontology.service.http.OntologyTermServiceJSON
 *
 */
public class OntologyTermJSONSerializer {
    public static JSONObject toJSONObject(OntologyTerm model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("id", model.getId());
        jsonObj.put("parentId", model.getParentId());
        jsonObj.put("name", model.getName());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.ontology.model.OntologyTerm[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (OntologyTerm model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.ontology.model.OntologyTerm[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (OntologyTerm[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.ontology.model.OntologyTerm> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (OntologyTerm model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
