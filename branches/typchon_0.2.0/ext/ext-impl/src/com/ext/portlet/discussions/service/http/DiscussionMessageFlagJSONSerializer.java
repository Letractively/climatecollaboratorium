package com.ext.portlet.discussions.service.http;

import com.ext.portlet.discussions.model.DiscussionMessageFlag;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.List;


/**
 * <a href="DiscussionMessageFlagJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.discussions.service.http.DiscussionMessageFlagServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.discussions.service.http.DiscussionMessageFlagServiceJSON
 *
 */
public class DiscussionMessageFlagJSONSerializer {
    public static JSONObject toJSONObject(DiscussionMessageFlag model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("pk", model.getPk());
        jsonObj.put("messageId", model.getMessageId());
        jsonObj.put("flagType", model.getFlagType());
        jsonObj.put("data", model.getData());

        Date created = model.getCreated();

        String createdJSON = StringPool.BLANK;

        if (created != null) {
            createdJSON = String.valueOf(created.getTime());
        }

        jsonObj.put("created", createdJSON);
        jsonObj.put("userId", model.getUserId());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.discussions.model.DiscussionMessageFlag[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DiscussionMessageFlag model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.discussions.model.DiscussionMessageFlag[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DiscussionMessageFlag[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.discussions.model.DiscussionMessageFlag> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DiscussionMessageFlag model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
