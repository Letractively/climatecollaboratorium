package com.ext.portlet.ontology.service.http;

import com.ext.portlet.ontology.model.FocusArea;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="FocusAreaJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.ontology.service.http.FocusAreaServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.ontology.service.http.FocusAreaServiceJSON
 *
 */
public class FocusAreaJSONSerializer {
    public static JSONObject toJSONObject(FocusArea model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("id", model.getId());
        jsonObj.put("name", model.getName());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.ontology.model.FocusArea[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (FocusArea model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.ontology.model.FocusArea[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (FocusArea[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.ontology.model.FocusArea> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (FocusArea model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
