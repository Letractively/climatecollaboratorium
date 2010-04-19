package com.ext.portlet.plans.service.http;

import com.ext.portlet.plans.model.PlanColumnSettings;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="PlanColumnSettingsJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.plans.service.http.PlanColumnSettingsServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.http.PlanColumnSettingsServiceJSON
 *
 */
public class PlanColumnSettingsJSONSerializer {
    public static JSONObject toJSONObject(PlanColumnSettings model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("planColumnSettingsId", model.getPlanColumnSettingsId());
        jsonObj.put("columnName", model.getColumnName());
        jsonObj.put("planUserSettingsId", model.getPlanUserSettingsId());
        jsonObj.put("visible", model.getVisible());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlanColumnSettings[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanColumnSettings model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlanColumnSettings[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanColumnSettings[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.plans.model.PlanColumnSettings> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanColumnSettings model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
