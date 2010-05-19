package com.ext.portlet.debaterevision.service.http;

import com.ext.portlet.debaterevision.model.DebateItemVote;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="DebateItemVoteJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.debaterevision.service.http.DebateItemVoteServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.service.http.DebateItemVoteServiceJSON
 *
 */
public class DebateItemVoteJSONSerializer {
    public static JSONObject toJSONObject(DebateItemVote model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("debateItemVoteId", model.getDebateItemVoteId());
        jsonObj.put("debateItemId", model.getDebateItemId());
        jsonObj.put("userId", model.getUserId());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.debaterevision.model.DebateItemVote[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateItemVote model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.debaterevision.model.DebateItemVote[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateItemVote[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.debaterevision.model.DebateItemVote> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateItemVote model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
