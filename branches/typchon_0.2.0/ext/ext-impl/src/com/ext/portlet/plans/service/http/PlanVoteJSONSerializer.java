package com.ext.portlet.plans.service.http;

import com.ext.portlet.plans.model.PlanVote;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.List;


/**
 * <a href="PlanVoteJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.plans.service.http.PlanVoteServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.http.PlanVoteServiceJSON
 *
 */
public class PlanVoteJSONSerializer {
    public static JSONObject toJSONObject(PlanVote model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("userId", model.getUserId());
        jsonObj.put("contestId", model.getContestId());
        jsonObj.put("planId", model.getPlanId());

        Date createDate = model.getCreateDate();

        String createDateJSON = StringPool.BLANK;

        if (createDate != null) {
            createDateJSON = String.valueOf(createDate.getTime());
        }

        jsonObj.put("createDate", createDateJSON);

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlanVote[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanVote model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlanVote[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanVote[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.plans.model.PlanVote> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanVote model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
