package com.ext.portlet.twitter.service.http;

import com.ext.portlet.twitter.model.UserTwitterMapping;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="UserTwitterMappingJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.twitter.service.http.UserTwitterMappingServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.twitter.service.http.UserTwitterMappingServiceJSON
 *
 */
public class UserTwitterMappingJSONSerializer {
    public static JSONObject toJSONObject(UserTwitterMapping model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("twitterId", model.getTwitterId());
        jsonObj.put("userId", model.getUserId());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.twitter.model.UserTwitterMapping[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (UserTwitterMapping model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.twitter.model.UserTwitterMapping[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (UserTwitterMapping[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.twitter.model.UserTwitterMapping> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (UserTwitterMapping model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
