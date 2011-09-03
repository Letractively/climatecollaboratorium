package com.ext.portlet.landingPage.service.http;

import com.ext.portlet.landingPage.model.LandingPage;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.List;


/**
 * <a href="LandingPageJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.landingPage.service.http.LandingPageServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.landingPage.service.http.LandingPageServiceJSON
 *
 */
public class LandingPageJSONSerializer {
    public static JSONObject toJSONObject(LandingPage model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("id", model.getId());
        jsonObj.put("baseUrl", model.getBaseUrl());
        jsonObj.put("targetUrl", model.getTargetUrl());

        Date updated = model.getUpdated();

        String updatedJSON = StringPool.BLANK;

        if (updated != null) {
            updatedJSON = String.valueOf(updated.getTime());
        }

        jsonObj.put("updated", updatedJSON);

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.landingPage.model.LandingPage[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (LandingPage model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.landingPage.model.LandingPage[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (LandingPage[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.landingPage.model.LandingPage> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (LandingPage model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
