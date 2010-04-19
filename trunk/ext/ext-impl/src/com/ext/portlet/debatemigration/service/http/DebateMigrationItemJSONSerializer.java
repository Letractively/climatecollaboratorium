package com.ext.portlet.debatemigration.service.http;

import com.ext.portlet.debatemigration.model.DebateMigrationItem;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="DebateMigrationItemJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.debatemigration.service.http.DebateMigrationItemServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debatemigration.service.http.DebateMigrationItemServiceJSON
 *
 */
public class DebateMigrationItemJSONSerializer {
    public static JSONObject toJSONObject(DebateMigrationItem model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("debateMigrationItemPK", model.getDebateMigrationItemPK());
        jsonObj.put("debateMigrationId", model.getDebateMigrationId());
        jsonObj.put("oldMBMessageId", model.getOldMBMessageId());
        jsonObj.put("newDebateItemId", model.getNewDebateItemId());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.debatemigration.model.DebateMigrationItem[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateMigrationItem model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.debatemigration.model.DebateMigrationItem[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateMigrationItem[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.debatemigration.model.DebateMigrationItem> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateMigrationItem model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
