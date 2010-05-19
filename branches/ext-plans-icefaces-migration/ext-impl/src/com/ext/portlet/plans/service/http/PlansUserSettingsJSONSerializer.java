package com.ext.portlet.plans.service.http;

import com.ext.portlet.plans.model.PlansUserSettings;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="PlansUserSettingsJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.plans.service.http.PlansUserSettingsServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.http.PlansUserSettingsServiceJSON
 *
 */
public class PlansUserSettingsJSONSerializer {
    public static JSONObject toJSONObject(PlansUserSettings model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("planUserSettingsId", model.getPlanUserSettingsId());
        jsonObj.put("userId", model.getUserId());
        jsonObj.put("planTypeId", model.getPlanTypeId());
        jsonObj.put("sortColumn", model.getSortColumn());
        jsonObj.put("sortDirection", model.getSortDirection());
        jsonObj.put("filterEnabled", model.getFilterEnabled());
        jsonObj.put("filterPositionsAll", model.getFilterPositionsAll());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlansUserSettings[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlansUserSettings model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlansUserSettings[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlansUserSettings[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.plans.model.PlansUserSettings> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlansUserSettings model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
