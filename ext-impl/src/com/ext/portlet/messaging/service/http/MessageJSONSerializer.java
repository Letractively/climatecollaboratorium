package com.ext.portlet.messaging.service.http;

import com.ext.portlet.messaging.model.Message;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.List;


/**
 * <a href="MessageJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.messaging.service.http.MessageServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.messaging.service.http.MessageServiceJSON
 *
 */
public class MessageJSONSerializer {
    public static JSONObject toJSONObject(Message model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("messageId", model.getMessageId());
        jsonObj.put("fromId", model.getFromId());
        jsonObj.put("repliesTo", model.getRepliesTo());

        Date createDate = model.getCreateDate();

        String createDateJSON = StringPool.BLANK;

        if (createDate != null) {
            createDateJSON = String.valueOf(createDate.getTime());
        }

        jsonObj.put("createDate", createDateJSON);
        jsonObj.put("subject", model.getSubject());
        jsonObj.put("content", model.getContent());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.messaging.model.Message[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (Message model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.messaging.model.Message[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (Message[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.messaging.model.Message> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (Message model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
