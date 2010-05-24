package com.ext.portlet.models.service.http;

import com.ext.portlet.models.model.ModelOutputChartOrder;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="ModelOutputChartOrderJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.models.service.http.ModelOutputChartOrderServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.service.http.ModelOutputChartOrderServiceJSON
 *
 */
public class ModelOutputChartOrderJSONSerializer {
    public static JSONObject toJSONObject(ModelOutputChartOrder model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("modelOutputChartOrderPK",
            model.getModelOutputChartOrderPK());
        jsonObj.put("modelId", model.getModelId());
        jsonObj.put("modelOutputLabel", model.getModelOutputLabel());
        jsonObj.put("modelOutputChartOrder", model.getModelOutputChartOrder());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.models.model.ModelOutputChartOrder[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ModelOutputChartOrder model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.models.model.ModelOutputChartOrder[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ModelOutputChartOrder[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.models.model.ModelOutputChartOrder> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ModelOutputChartOrder model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
