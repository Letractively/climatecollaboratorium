package com.ext.portlet.models.service.http;

import com.ext.portlet.models.model.ModelCategory;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="ModelCategoryJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.models.service.http.ModelCategoryServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.service.http.ModelCategoryServiceJSON
 *
 */
public class ModelCategoryJSONSerializer {
    public static JSONObject toJSONObject(ModelCategory model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("modelCategoryPK", model.getModelCategoryPK());
        jsonObj.put("modelCategoryName", model.getModelCategoryName());
        jsonObj.put("modelCategoryDescription",
            model.getModelCategoryDescription());
        jsonObj.put("modelCategoryDisplayWeight",
            model.getModelCategoryDisplayWeight());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.models.model.ModelCategory[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ModelCategory model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.models.model.ModelCategory[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ModelCategory[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.models.model.ModelCategory> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ModelCategory model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
