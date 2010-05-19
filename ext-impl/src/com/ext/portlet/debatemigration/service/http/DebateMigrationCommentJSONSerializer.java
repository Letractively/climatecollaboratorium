package com.ext.portlet.debatemigration.service.http;

import com.ext.portlet.debatemigration.model.DebateMigrationComment;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="DebateMigrationCommentJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.debatemigration.service.http.DebateMigrationCommentServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debatemigration.service.http.DebateMigrationCommentServiceJSON
 *
 */
public class DebateMigrationCommentJSONSerializer {
    public static JSONObject toJSONObject(DebateMigrationComment model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("debateMigrationCommentPK",
            model.getDebateMigrationCommentPK());
        jsonObj.put("debateMigrationId", model.getDebateMigrationId());
        jsonObj.put("oldMBMessageId", model.getOldMBMessageId());
        jsonObj.put("oldMBThreadId", model.getOldMBThreadId());
        jsonObj.put("newDebateCommentId", model.getNewDebateCommentId());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.debatemigration.model.DebateMigrationComment[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateMigrationComment model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.debatemigration.model.DebateMigrationComment[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateMigrationComment[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.debatemigration.model.DebateMigrationComment> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateMigrationComment model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
