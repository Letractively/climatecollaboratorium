package com.ext.utils.service.http;

import com.ext.utils.model.UserForgotPasswordRequest;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.List;


/**
 * <a href="UserForgotPasswordRequestJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.utils.service.http.UserForgotPasswordRequestServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.utils.service.http.UserForgotPasswordRequestServiceJSON
 *
 */
public class UserForgotPasswordRequestJSONSerializer {
    public static JSONObject toJSONObject(UserForgotPasswordRequest model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("token", model.getToken());
        jsonObj.put("userId", model.getUserId());

        Date created = model.getCreated();

        String createdJSON = StringPool.BLANK;

        if (created != null) {
            createdJSON = String.valueOf(created.getTime());
        }

        jsonObj.put("created", createdJSON);

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.utils.model.UserForgotPasswordRequest[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (UserForgotPasswordRequest model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.utils.model.UserForgotPasswordRequest[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (UserForgotPasswordRequest[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.utils.model.UserForgotPasswordRequest> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (UserForgotPasswordRequest model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
