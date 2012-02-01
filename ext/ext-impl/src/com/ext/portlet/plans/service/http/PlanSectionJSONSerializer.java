package com.ext.portlet.plans.service.http;

import com.ext.portlet.plans.model.PlanSection;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="PlanSectionJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.plans.service.http.PlanSectionServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.http.PlanSectionServiceJSON
 *
 */
public class PlanSectionJSONSerializer {
    public static JSONObject toJSONObject(PlanSection model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("planSectionDefinitionId",
            model.getPlanSectionDefinitionId());
        jsonObj.put("planId", model.getPlanId());
        jsonObj.put("content", model.getContent());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlanSection[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanSection model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlanSection[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanSection[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.plans.model.PlanSection> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanSection model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
