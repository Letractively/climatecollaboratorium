package com.ext.portlet.plans.service.http;

import com.ext.portlet.plans.model.PlanType;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="PlanTypeJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.plans.service.http.PlanTypeServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.http.PlanTypeServiceJSON
 *
 */
public class PlanTypeJSONSerializer {
    public static JSONObject toJSONObject(PlanType model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("planTypeId", model.getPlanTypeId());
        jsonObj.put("name", model.getName());
        jsonObj.put("description", model.getDescription());
        jsonObj.put("modelId", model.getModelId());
        jsonObj.put("modelTypeName", model.getModelTypeName());
        jsonObj.put("published", model.getPublished());
        jsonObj.put("publishedCounterpartId", model.getPublishedCounterpartId());
        jsonObj.put("isDefault", model.getIsDefault());
        jsonObj.put("defaultModelId", model.getDefaultModelId());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlanType[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanType model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlanType[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanType[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.plans.model.PlanType> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanType model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
