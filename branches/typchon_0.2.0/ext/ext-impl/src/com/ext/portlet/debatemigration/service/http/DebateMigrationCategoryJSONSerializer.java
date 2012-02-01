package com.ext.portlet.debatemigration.service.http;

import com.ext.portlet.debatemigration.model.DebateMigrationCategory;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="DebateMigrationCategoryJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.debatemigration.service.http.DebateMigrationCategoryServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debatemigration.service.http.DebateMigrationCategoryServiceJSON
 *
 */
public class DebateMigrationCategoryJSONSerializer {
    public static JSONObject toJSONObject(DebateMigrationCategory model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("debateMigrationCategoryPK",
            model.getDebateMigrationCategoryPK());
        jsonObj.put("debateMigrationId", model.getDebateMigrationId());
        jsonObj.put("oldMBCategoryId", model.getOldMBCategoryId());
        jsonObj.put("newCategoryId", model.getNewCategoryId());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.debatemigration.model.DebateMigrationCategory[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateMigrationCategory model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.debatemigration.model.DebateMigrationCategory[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateMigrationCategory[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.debatemigration.model.DebateMigrationCategory> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateMigrationCategory model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
