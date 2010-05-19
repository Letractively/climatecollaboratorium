package com.ext.portlet.debatemigration.service.http;

import com.ext.portlet.debatemigration.model.DebateMigration;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.List;


/**
 * <a href="DebateMigrationJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.debatemigration.service.http.DebateMigrationServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debatemigration.service.http.DebateMigrationServiceJSON
 *
 */
public class DebateMigrationJSONSerializer {
    public static JSONObject toJSONObject(DebateMigration model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("debateMigrationPK", model.getDebateMigrationPK());

        Date migrationDate = model.getMigrationDate();

        String migrationDateJSON = StringPool.BLANK;

        if (migrationDate != null) {
            migrationDateJSON = String.valueOf(migrationDate.getTime());
        }

        jsonObj.put("migrationDate", migrationDateJSON);

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.debatemigration.model.DebateMigration[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateMigration model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.debatemigration.model.DebateMigration[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateMigration[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.debatemigration.model.DebateMigration> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateMigration model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
