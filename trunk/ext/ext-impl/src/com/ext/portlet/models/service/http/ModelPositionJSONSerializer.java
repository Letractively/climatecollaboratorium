package com.ext.portlet.models.service.http;

import com.ext.portlet.models.model.ModelPosition;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="ModelPositionJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.models.service.http.ModelPositionServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.service.http.ModelPositionServiceJSON
 *
 */
public class ModelPositionJSONSerializer {
    public static JSONObject toJSONObject(ModelPosition model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("id", model.getId());
        jsonObj.put("positionId", model.getPositionId());
        jsonObj.put("modelId", model.getModelId());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.models.model.ModelPosition[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ModelPosition model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.models.model.ModelPosition[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ModelPosition[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.models.model.ModelPosition> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ModelPosition model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
