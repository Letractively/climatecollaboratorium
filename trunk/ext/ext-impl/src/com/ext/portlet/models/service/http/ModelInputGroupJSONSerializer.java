package com.ext.portlet.models.service.http;

import com.ext.portlet.models.model.ModelInputGroup;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="ModelInputGroupJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.models.service.http.ModelInputGroupServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.service.http.ModelInputGroupServiceJSON
 *
 */
public class ModelInputGroupJSONSerializer {
    public static JSONObject toJSONObject(ModelInputGroup model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("modelInputGroupPK", model.getModelInputGroupPK());
        jsonObj.put("modelId", model.getModelId());
        jsonObj.put("nameAndDescriptionMetaDataId",
            model.getNameAndDescriptionMetaDataId());
        jsonObj.put("name", model.getName());
        jsonObj.put("description", model.getDescription());
        jsonObj.put("displayItemOrder", model.getDisplayItemOrder());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.models.model.ModelInputGroup[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ModelInputGroup model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.models.model.ModelInputGroup[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ModelInputGroup[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.models.model.ModelInputGroup> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ModelInputGroup model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
