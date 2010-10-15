package com.ext.portlet.models.service.http;

import com.ext.portlet.models.model.ModelInputItem;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="ModelInputItemJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.models.service.http.ModelInputItemServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.service.http.ModelInputItemServiceJSON
 *
 */
public class ModelInputItemJSONSerializer {
    public static JSONObject toJSONObject(ModelInputItem model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("modelInputItemPK", model.getModelInputItemPK());
        jsonObj.put("modelId", model.getModelId());
        jsonObj.put("modelInputItemID", model.getModelInputItemID());
        jsonObj.put("modelGroupId", model.getModelGroupId());
        jsonObj.put("displayItemOrder", model.getDisplayItemOrder());
        jsonObj.put("type", model.getType());
        jsonObj.put("properties", model.getProperties());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.models.model.ModelInputItem[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ModelInputItem model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.models.model.ModelInputItem[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ModelInputItem[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.models.model.ModelInputItem> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ModelInputItem model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
