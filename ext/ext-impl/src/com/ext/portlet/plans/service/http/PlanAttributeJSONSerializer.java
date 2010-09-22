package com.ext.portlet.plans.service.http;

import com.ext.portlet.plans.model.PlanAttribute;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="PlanAttributeJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.plans.service.http.PlanAttributeServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.http.PlanAttributeServiceJSON
 *
 */
public class PlanAttributeJSONSerializer {
    public static JSONObject toJSONObject(PlanAttribute model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("attributeId", model.getAttributeId());
        jsonObj.put("planId", model.getPlanId());
        jsonObj.put("attributeName", model.getAttributeName());
        jsonObj.put("attributeValue", model.getAttributeValue());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlanAttribute[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanAttribute model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlanAttribute[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanAttribute[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.plans.model.PlanAttribute> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanAttribute model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}