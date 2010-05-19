package com.ext.portlet.debaterevision.service.http;

import com.ext.portlet.debaterevision.model.DebateCategoryMap;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="DebateCategoryMapJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.debaterevision.service.http.DebateCategoryMapServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.service.http.DebateCategoryMapServiceJSON
 *
 */
public class DebateCategoryMapJSONSerializer {
    public static JSONObject toJSONObject(DebateCategoryMap model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("debateCategoryMapPK", model.getDebateCategoryMapPK());
        jsonObj.put("debateCategoryPK", model.getDebateCategoryPK());
        jsonObj.put("debateId", model.getDebateId());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.debaterevision.model.DebateCategoryMap[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateCategoryMap model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.debaterevision.model.DebateCategoryMap[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateCategoryMap[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.debaterevision.model.DebateCategoryMap> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateCategoryMap model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
