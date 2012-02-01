package com.ext.portlet.messaging.service.http;

import com.ext.portlet.messaging.model.MessagingUserPreferences;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="MessagingUserPreferencesJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.messaging.service.http.MessagingUserPreferencesServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.messaging.service.http.MessagingUserPreferencesServiceJSON
 *
 */
public class MessagingUserPreferencesJSONSerializer {
    public static JSONObject toJSONObject(MessagingUserPreferences model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("messagingPreferencesId", model.getMessagingPreferencesId());
        jsonObj.put("userId", model.getUserId());
        jsonObj.put("emailOnSend", model.getEmailOnSend());
        jsonObj.put("emailOnReceipt", model.getEmailOnReceipt());
        jsonObj.put("emailOnActivity", model.getEmailOnActivity());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.messaging.model.MessagingUserPreferences[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (MessagingUserPreferences model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.messaging.model.MessagingUserPreferences[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (MessagingUserPreferences[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.messaging.model.MessagingUserPreferences> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (MessagingUserPreferences model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
