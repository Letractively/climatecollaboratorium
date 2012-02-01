package com.ext.portlet.surveys.service.http;

import com.ext.portlet.surveys.model.Survey;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="SurveyJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.surveys.service.http.SurveyServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.surveys.service.http.SurveyServiceJSON
 *
 */
public class SurveyJSONSerializer {
    public static JSONObject toJSONObject(Survey model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("eventName", model.getEventName());
        jsonObj.put("description", model.getDescription());
        jsonObj.put("url", model.getUrl());
        jsonObj.put("type", model.getType());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.surveys.model.Survey[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (Survey model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.surveys.model.Survey[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (Survey[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.surveys.model.Survey> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (Survey model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
