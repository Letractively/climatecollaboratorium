package com.ext.auth.service.http;

import com.ext.auth.model.AuthMapping;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="AuthMappingJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.auth.service.http.AuthMappingServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.auth.service.http.AuthMappingServiceJSON
 *
 */
public class AuthMappingJSONSerializer {
    public static JSONObject toJSONObject(AuthMapping model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("identifier", model.getIdentifier());
        jsonObj.put("userId", model.getUserId());

        return jsonObj;
    }

    public static JSONArray toJSONArray(com.ext.auth.model.AuthMapping[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (AuthMapping model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.auth.model.AuthMapping[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (AuthMapping[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.auth.model.AuthMapping> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (AuthMapping model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
