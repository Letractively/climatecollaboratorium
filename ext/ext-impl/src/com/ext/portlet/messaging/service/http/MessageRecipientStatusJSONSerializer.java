package com.ext.portlet.messaging.service.http;

import com.ext.portlet.messaging.model.MessageRecipientStatus;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="MessageRecipientStatusJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.messaging.service.http.MessageRecipientStatusServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.messaging.service.http.MessageRecipientStatusServiceJSON
 *
 */
public class MessageRecipientStatusJSONSerializer {
    public static JSONObject toJSONObject(MessageRecipientStatus model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("messageRecipientId", model.getMessageRecipientId());
        jsonObj.put("messageId", model.getMessageId());
        jsonObj.put("userId", model.getUserId());
        jsonObj.put("opened", model.getOpened());
        jsonObj.put("archived", model.getArchived());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.messaging.model.MessageRecipientStatus[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (MessageRecipientStatus model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.messaging.model.MessageRecipientStatus[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (MessageRecipientStatus[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.messaging.model.MessageRecipientStatus> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (MessageRecipientStatus model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
