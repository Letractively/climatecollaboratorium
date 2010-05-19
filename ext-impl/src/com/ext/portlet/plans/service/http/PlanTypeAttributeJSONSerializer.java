package com.ext.portlet.plans.service.http;

import com.ext.portlet.plans.model.PlanTypeAttribute;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="PlanTypeAttributeJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.plans.service.http.PlanTypeAttributeServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.http.PlanTypeAttributeServiceJSON
 *
 */
public class PlanTypeAttributeJSONSerializer {
    public static JSONObject toJSONObject(PlanTypeAttribute model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("planTypeAttributeId", model.getPlanTypeAttributeId());
        jsonObj.put("planTypeId", model.getPlanTypeId());
        jsonObj.put("attributeName", model.getAttributeName());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlanTypeAttribute[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanTypeAttribute model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlanTypeAttribute[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanTypeAttribute[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.plans.model.PlanTypeAttribute> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanTypeAttribute model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
