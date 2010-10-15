package com.ext.portlet.debatemigration.service.http;

import com.ext.portlet.debatemigration.model.DebateMigrationDebate;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="DebateMigrationDebateJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.debatemigration.service.http.DebateMigrationDebateServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debatemigration.service.http.DebateMigrationDebateServiceJSON
 *
 */
public class DebateMigrationDebateJSONSerializer {
    public static JSONObject toJSONObject(DebateMigrationDebate model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("debateMigrationDebatePK",
            model.getDebateMigrationDebatePK());
        jsonObj.put("debateMigrationId", model.getDebateMigrationId());
        jsonObj.put("oldMBCategoryId", model.getOldMBCategoryId());
        jsonObj.put("newDebateId", model.getNewDebateId());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.debatemigration.model.DebateMigrationDebate[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateMigrationDebate model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.debatemigration.model.DebateMigrationDebate[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateMigrationDebate[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.debatemigration.model.DebateMigrationDebate> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateMigrationDebate model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
