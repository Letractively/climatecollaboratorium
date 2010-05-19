package com.ext.portlet.plans.service.http;

import com.ext.portlet.plans.model.PlanPosition;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.List;


/**
 * <a href="PlanPositionJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.plans.service.http.PlanPositionServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.http.PlanPositionServiceJSON
 *
 */
public class PlanPositionJSONSerializer {
    public static JSONObject toJSONObject(PlanPosition model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("planId", model.getPlanId());
        jsonObj.put("positionId", model.getPositionId());
        jsonObj.put("userId", model.getUserId());
        jsonObj.put("userName", model.getUserName());

        Date createDate = model.getCreateDate();

        String createDateJSON = StringPool.BLANK;

        if (createDate != null) {
            createDateJSON = String.valueOf(createDate.getTime());
        }

        jsonObj.put("createDate", createDateJSON);

        Date modifiedDate = model.getModifiedDate();

        String modifiedDateJSON = StringPool.BLANK;

        if (modifiedDate != null) {
            modifiedDateJSON = String.valueOf(modifiedDate.getTime());
        }

        jsonObj.put("modifiedDate", modifiedDateJSON);

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlanPosition[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanPosition model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlanPosition[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanPosition[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.plans.model.PlanPosition> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanPosition model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
