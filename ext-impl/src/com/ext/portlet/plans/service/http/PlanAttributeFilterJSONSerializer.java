package com.ext.portlet.plans.service.http;

import com.ext.portlet.plans.model.PlanAttributeFilter;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="PlanAttributeFilterJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.plans.service.http.PlanAttributeFilterServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.http.PlanAttributeFilterServiceJSON
 *
 */
public class PlanAttributeFilterJSONSerializer {
    public static JSONObject toJSONObject(PlanAttributeFilter model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("planAttributeFilterId", model.getPlanAttributeFilterId());
        jsonObj.put("attributeName", model.getAttributeName());
        jsonObj.put("planUserSettingsId", model.getPlanUserSettingsId());
        jsonObj.put("max", model.getMax());
        jsonObj.put("min", model.getMin());
        jsonObj.put("stringVal", model.getStringVal());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlanAttributeFilter[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanAttributeFilter model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlanAttributeFilter[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanAttributeFilter[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.plans.model.PlanAttributeFilter> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanAttributeFilter model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
