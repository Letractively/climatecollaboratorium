package com.ext.portlet.plans.service.http;

import com.ext.portlet.plans.model.PlansFilterPosition;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="PlansFilterPositionJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.plans.service.http.PlansFilterPositionServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.http.PlansFilterPositionServiceJSON
 *
 */
public class PlansFilterPositionJSONSerializer {
    public static JSONObject toJSONObject(PlansFilterPosition model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("userId", model.getUserId());
        jsonObj.put("planTypeId", model.getPlanTypeId());
        jsonObj.put("positionId", model.getPositionId());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlansFilterPosition[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlansFilterPosition model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlansFilterPosition[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlansFilterPosition[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.plans.model.PlansFilterPosition> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlansFilterPosition model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
