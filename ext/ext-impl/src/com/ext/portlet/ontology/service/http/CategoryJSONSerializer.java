package com.ext.portlet.ontology.service.http;

import com.ext.portlet.ontology.model.Category;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="CategoryJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.ontology.service.http.CategoryServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.ontology.service.http.CategoryServiceJSON
 *
 */
public class CategoryJSONSerializer {
    public static JSONObject toJSONObject(Category model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("id", model.getId());
        jsonObj.put("name", model.getName());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.ontology.model.Category[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (Category model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.ontology.model.Category[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (Category[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.ontology.model.Category> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (Category model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
