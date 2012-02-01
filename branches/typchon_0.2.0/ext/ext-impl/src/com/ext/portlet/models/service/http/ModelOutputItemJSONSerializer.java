package com.ext.portlet.models.service.http;

import com.ext.portlet.models.model.ModelOutputItem;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="ModelOutputItemJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.models.service.http.ModelOutputItemServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.service.http.ModelOutputItemServiceJSON
 *
 */
public class ModelOutputItemJSONSerializer {
    public static JSONObject toJSONObject(ModelOutputItem model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("modelOutputItemModifierPK",
            model.getModelOutputItemModifierPK());
        jsonObj.put("modelId", model.getModelId());
        jsonObj.put("modelOutputItemId", model.getModelOutputItemId());
        jsonObj.put("modelOutputItemOrder", model.getModelOutputItemOrder());
        jsonObj.put("modelItemRangePolicy", model.getModelItemRangePolicy());
        jsonObj.put("modelItemRangeMessage", model.getModelItemRangeMessage());
        jsonObj.put("modelItemErrorPolicy", model.getModelItemErrorPolicy());
        jsonObj.put("modelItemErrorMessage", model.getModelItemErrorMessage());
        jsonObj.put("modelItemLabelFormat", model.getModelItemLabelFormat());
        jsonObj.put("modelItemIsVisible", model.getModelItemIsVisible());
        jsonObj.put("itemType", model.getItemType());
        jsonObj.put("relatedOutputItem", model.getRelatedOutputItem());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.models.model.ModelOutputItem[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ModelOutputItem model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.models.model.ModelOutputItem[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ModelOutputItem[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.models.model.ModelOutputItem> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ModelOutputItem model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
