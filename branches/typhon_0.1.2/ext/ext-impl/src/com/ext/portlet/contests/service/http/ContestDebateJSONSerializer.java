package com.ext.portlet.contests.service.http;

import com.ext.portlet.contests.model.ContestDebate;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="ContestDebateJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.contests.service.http.ContestDebateServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.service.http.ContestDebateServiceJSON
 *
 */
public class ContestDebateJSONSerializer {
    public static JSONObject toJSONObject(ContestDebate model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("id", model.getId());
        jsonObj.put("debateId", model.getDebateId());
        jsonObj.put("ContestPK", model.getContestPK());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.contests.model.ContestDebate[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ContestDebate model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.contests.model.ContestDebate[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ContestDebate[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.contests.model.ContestDebate> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ContestDebate model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
