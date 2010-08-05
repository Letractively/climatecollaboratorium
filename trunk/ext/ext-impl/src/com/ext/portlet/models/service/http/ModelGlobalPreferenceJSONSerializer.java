package com.ext.portlet.models.service.http;

import com.ext.portlet.models.model.ModelGlobalPreference;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="ModelGlobalPreferenceJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.models.service.http.ModelGlobalPreferenceServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.service.http.ModelGlobalPreferenceServiceJSON
 *
 */
public class ModelGlobalPreferenceJSONSerializer {
    public static JSONObject toJSONObject(ModelGlobalPreference model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("modelGlobalPreferencePK",
            model.getModelGlobalPreferencePK());
        jsonObj.put("modelId", model.getModelId());
        jsonObj.put("visible", model.getVisible());
        jsonObj.put("weight", model.getWeight());
        jsonObj.put("expertEvaluationPageId", model.getExpertEvaluationPageId());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.models.model.ModelGlobalPreference[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ModelGlobalPreference model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.models.model.ModelGlobalPreference[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ModelGlobalPreference[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.models.model.ModelGlobalPreference> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ModelGlobalPreference model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
