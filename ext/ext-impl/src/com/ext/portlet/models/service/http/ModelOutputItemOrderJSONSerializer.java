package com.ext.portlet.models.service.http;

import com.ext.portlet.models.model.ModelOutputItemOrder;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="ModelOutputItemOrderJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.models.service.http.ModelOutputItemOrderServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.service.http.ModelOutputItemOrderServiceJSON
 *
 */
public class ModelOutputItemOrderJSONSerializer {
    public static JSONObject toJSONObject(ModelOutputItemOrder model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("modelOutputItemModifierPK",
            model.getModelOutputItemModifierPK());
        jsonObj.put("modelId", model.getModelId());
        jsonObj.put("modelOutputItemId", model.getModelOutputItemId());
        jsonObj.put("modelOutputItemOrder", model.getModelOutputItemOrder());
        jsonObj.put("type", model.getType());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.models.model.ModelOutputItemOrder[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ModelOutputItemOrder model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.models.model.ModelOutputItemOrder[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ModelOutputItemOrder[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.models.model.ModelOutputItemOrder> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ModelOutputItemOrder model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
