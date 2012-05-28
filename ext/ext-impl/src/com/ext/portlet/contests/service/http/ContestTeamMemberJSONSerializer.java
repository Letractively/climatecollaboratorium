package com.ext.portlet.contests.service.http;

import com.ext.portlet.contests.model.ContestTeamMember;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="ContestTeamMemberJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.contests.service.http.ContestTeamMemberServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.service.http.ContestTeamMemberServiceJSON
 *
 */
public class ContestTeamMemberJSONSerializer {
    public static JSONObject toJSONObject(ContestTeamMember model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("id", model.getId());
        jsonObj.put("contestId", model.getContestId());
        jsonObj.put("userId", model.getUserId());
        jsonObj.put("role", model.getRole());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.contests.model.ContestTeamMember[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ContestTeamMember model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.contests.model.ContestTeamMember[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ContestTeamMember[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.contests.model.ContestTeamMember> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ContestTeamMember model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
