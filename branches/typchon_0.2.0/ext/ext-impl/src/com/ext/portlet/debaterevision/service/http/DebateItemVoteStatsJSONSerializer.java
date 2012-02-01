package com.ext.portlet.debaterevision.service.http;

import com.ext.portlet.debaterevision.model.DebateItemVoteStats;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="DebateItemVoteStatsJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.debaterevision.service.http.DebateItemVoteStatsServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.service.http.DebateItemVoteStatsServiceJSON
 *
 */
public class DebateItemVoteStatsJSONSerializer {
    public static JSONObject toJSONObject(DebateItemVoteStats model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("debateItemVotesStats", model.getDebateItemVotesStats());
        jsonObj.put("debateItemId", model.getDebateItemId());
        jsonObj.put("votesCount", model.getVotesCount());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.debaterevision.model.DebateItemVoteStats[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateItemVoteStats model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.debaterevision.model.DebateItemVoteStats[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateItemVoteStats[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.debaterevision.model.DebateItemVoteStats> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateItemVoteStats model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
