package com.ext.portlet.plans.service.http;

import com.ext.portlet.plans.model.PlanPropertyFilter;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="PlanPropertyFilterJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.plans.service.http.PlanPropertyFilterServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.http.PlanPropertyFilterServiceJSON
 *
 */
public class PlanPropertyFilterJSONSerializer {
    public static JSONObject toJSONObject(PlanPropertyFilter model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("planPropertyFilterId", model.getPlanPropertyFilterId());
        jsonObj.put("propertyName", model.getPropertyName());
        jsonObj.put("planUserSettingsId", model.getPlanUserSettingsId());
        jsonObj.put("value", model.getValue());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlanPropertyFilter[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanPropertyFilter model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlanPropertyFilter[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanPropertyFilter[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.plans.model.PlanPropertyFilter> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanPropertyFilter model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
