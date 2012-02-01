package com.ext.portlet.debates.service.http;

import com.ext.portlet.debates.model.DebateDiscussionMap;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="DebateDiscussionMapJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.debates.service.http.DebateDiscussionMapServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debates.service.http.DebateDiscussionMapServiceJSON
 *
 */
public class DebateDiscussionMapJSONSerializer {
    public static JSONObject toJSONObject(DebateDiscussionMap model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("debateMessageId", model.getDebateMessageId());
        jsonObj.put("discussionThreadId", model.getDiscussionThreadId());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.debates.model.DebateDiscussionMap[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateDiscussionMap model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.debates.model.DebateDiscussionMap[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateDiscussionMap[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.debates.model.DebateDiscussionMap> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateDiscussionMap model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
