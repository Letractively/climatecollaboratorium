package com.ext.portlet.surveys.service.http;

import com.ext.portlet.surveys.model.UserSurvey;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.List;


/**
 * <a href="UserSurveyJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.surveys.service.http.UserSurveyServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.surveys.service.http.UserSurveyServiceJSON
 *
 */
public class UserSurveyJSONSerializer {
    public static JSONObject toJSONObject(UserSurvey model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("userId", model.getUserId());
        jsonObj.put("eventName", model.getEventName());

        Date createDate = model.getCreateDate();

        String createDateJSON = StringPool.BLANK;

        if (createDate != null) {
            createDateJSON = String.valueOf(createDate.getTime());
        }

        jsonObj.put("createDate", createDateJSON);

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.surveys.model.UserSurvey[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (UserSurvey model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.surveys.model.UserSurvey[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (UserSurvey[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.surveys.model.UserSurvey> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (UserSurvey model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
