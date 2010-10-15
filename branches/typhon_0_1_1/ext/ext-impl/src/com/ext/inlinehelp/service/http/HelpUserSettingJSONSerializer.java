package com.ext.inlinehelp.service.http;

import com.ext.inlinehelp.model.HelpUserSetting;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="HelpUserSettingJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.inlinehelp.service.http.HelpUserSettingServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.inlinehelp.service.http.HelpUserSettingServiceJSON
 *
 */
public class HelpUserSettingJSONSerializer {
    public static JSONObject toJSONObject(HelpUserSetting model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("HelpUserSettingId", model.getHelpUserSettingId());
        jsonObj.put("userId", model.getUserId());
        jsonObj.put("messageId", model.getMessageId());
        jsonObj.put("visible", model.getVisible());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.inlinehelp.model.HelpUserSetting[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (HelpUserSetting model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.inlinehelp.model.HelpUserSetting[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (HelpUserSetting[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.inlinehelp.model.HelpUserSetting> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (HelpUserSetting model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
