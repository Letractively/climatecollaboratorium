package com.ext.portlet.plans.service.http;

import com.ext.portlet.plans.model.PlanModelRun;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.List;


/**
 * <a href="PlanModelRunJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.plans.service.http.PlanModelRunServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.http.PlanModelRunServiceJSON
 *
 */
public class PlanModelRunJSONSerializer {
    public static JSONObject toJSONObject(PlanModelRun model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("id", model.getId());
        jsonObj.put("planId", model.getPlanId());
        jsonObj.put("scenarioId", model.getScenarioId());
        jsonObj.put("planVersion", model.getPlanVersion());
        jsonObj.put("version", model.getVersion());

        Date created = model.getCreated();

        String createdJSON = StringPool.BLANK;

        if (created != null) {
            createdJSON = String.valueOf(created.getTime());
        }

        jsonObj.put("created", createdJSON);
        jsonObj.put("updateAuthorId", model.getUpdateAuthorId());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlanModelRun[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanModelRun model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlanModelRun[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanModelRun[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.plans.model.PlanModelRun> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanModelRun model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
