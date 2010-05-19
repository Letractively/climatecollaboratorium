package com.ext.portlet.plans.service.http;

import com.ext.portlet.plans.model.PlanTypeColumn;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="PlanTypeColumnJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.plans.service.http.PlanTypeColumnServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.http.PlanTypeColumnServiceJSON
 *
 */
public class PlanTypeColumnJSONSerializer {
    public static JSONObject toJSONObject(PlanTypeColumn model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("planTypeColumnId", model.getPlanTypeColumnId());
        jsonObj.put("planTypeId", model.getPlanTypeId());
        jsonObj.put("weight", model.getWeight());
        jsonObj.put("columnName", model.getColumnName());
        jsonObj.put("visibleByDefault", model.getVisibleByDefault());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlanTypeColumn[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanTypeColumn model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlanTypeColumn[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanTypeColumn[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.plans.model.PlanTypeColumn> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanTypeColumn model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
