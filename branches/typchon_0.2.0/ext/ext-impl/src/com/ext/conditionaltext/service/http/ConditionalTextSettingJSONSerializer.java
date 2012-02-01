package com.ext.conditionaltext.service.http;

import com.ext.conditionaltext.model.ConditionalTextSetting;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="ConditionalTextSettingJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.conditionaltext.service.http.ConditionalTextSettingServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.conditionaltext.service.http.ConditionalTextSettingServiceJSON
 *
 */
public class ConditionalTextSettingJSONSerializer {
    public static JSONObject toJSONObject(ConditionalTextSetting model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("ConditionalTextSettingId",
            model.getConditionalTextSettingId());
        jsonObj.put("styleClass", model.getStyleClass());
        jsonObj.put("paramKey", model.getParamKey());
        jsonObj.put("paramValue", model.getParamValue());
        jsonObj.put("html", model.getHtml());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.conditionaltext.model.ConditionalTextSetting[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ConditionalTextSetting model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.conditionaltext.model.ConditionalTextSetting[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ConditionalTextSetting[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.conditionaltext.model.ConditionalTextSetting> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ConditionalTextSetting model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
