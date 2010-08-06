package com.ext.portlet.plans.service.http;

import com.ext.portlet.plans.model.PlanItem;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.List;


/**
 * <a href="PlanItemJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.plans.service.http.PlanItemServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.http.PlanItemServiceJSON
 *
 */
public class PlanItemJSONSerializer {
    public static JSONObject toJSONObject(PlanItem model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("id", model.getId());
        jsonObj.put("planId", model.getPlanId());
        jsonObj.put("state", model.getState());

        Date updated = model.getUpdated();

        String updatedJSON = StringPool.BLANK;

        if (updated != null) {
            updatedJSON = String.valueOf(updated.getTime());
        }

        jsonObj.put("updated", updatedJSON);
        jsonObj.put("updateAuthorId", model.getUpdateAuthorId());
        jsonObj.put("updateType", model.getUpdateType());
        jsonObj.put("version", model.getVersion());
        jsonObj.put("ContestPhase", model.getContestPhase());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlanItem[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanItem model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlanItem[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanItem[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.plans.model.PlanItem> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanItem model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
