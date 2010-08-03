package com.ext.portlet.plans.service.http;

import com.ext.portlet.plans.model.PlanFan;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.List;


/**
 * <a href="PlanFanJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.plans.service.http.PlanFanServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.http.PlanFanServiceJSON
 *
 */
public class PlanFanJSONSerializer {
    public static JSONObject toJSONObject(PlanFan model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("id", model.getId());
        jsonObj.put("userId", model.getUserId());
        jsonObj.put("planId", model.getPlanId());

        Date created = model.getCreated();

        String createdJSON = StringPool.BLANK;

        if (created != null) {
            createdJSON = String.valueOf(created.getTime());
        }

        jsonObj.put("created", createdJSON);

        Date deleted = model.getDeleted();

        String deletedJSON = StringPool.BLANK;

        if (deleted != null) {
            deletedJSON = String.valueOf(deleted.getTime());
        }

        jsonObj.put("deleted", deletedJSON);

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlanFan[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanFan model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlanFan[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanFan[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.plans.model.PlanFan> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanFan model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
