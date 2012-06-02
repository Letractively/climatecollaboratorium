package com.ext.portlet.plans.service.http;

import com.ext.portlet.plans.model.PlanDescription;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.List;


/**
 * <a href="PlanDescriptionJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.plans.service.http.PlanDescriptionServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.http.PlanDescriptionServiceJSON
 *
 */
public class PlanDescriptionJSONSerializer {
    public static JSONObject toJSONObject(PlanDescription model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("id", model.getId());
        jsonObj.put("planId", model.getPlanId());
        jsonObj.put("name", model.getName());
        jsonObj.put("description", model.getDescription());
        jsonObj.put("version", model.getVersion());
        jsonObj.put("planVersion", model.getPlanVersion());

        Date created = model.getCreated();

        String createdJSON = StringPool.BLANK;

        if (created != null) {
            createdJSON = String.valueOf(created.getTime());
        }

        jsonObj.put("created", createdJSON);
        jsonObj.put("updateAuthorId", model.getUpdateAuthorId());
        jsonObj.put("image", model.getImage());
        jsonObj.put("pitch", model.getPitch());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlanDescription[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanDescription model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlanDescription[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanDescription[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.plans.model.PlanDescription> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanDescription model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
