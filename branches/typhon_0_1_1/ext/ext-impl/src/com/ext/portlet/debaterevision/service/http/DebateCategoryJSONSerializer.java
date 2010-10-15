package com.ext.portlet.debaterevision.service.http;

import com.ext.portlet.debaterevision.model.DebateCategory;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.List;


/**
 * <a href="DebateCategoryJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.debaterevision.service.http.DebateCategoryServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.service.http.DebateCategoryServiceJSON
 *
 */
public class DebateCategoryJSONSerializer {
    public static JSONObject toJSONObject(DebateCategory model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("debateCategoryPK", model.getDebateCategoryPK());
        jsonObj.put("authorId", model.getAuthorId());
        jsonObj.put("title", model.getTitle());
        jsonObj.put("brandingContentId", model.getBrandingContentId());
        jsonObj.put("description", model.getDescription());
        jsonObj.put("parentCategory", model.getParentCategory());

        Date updated = model.getUpdated();

        String updatedJSON = StringPool.BLANK;

        if (updated != null) {
            updatedJSON = String.valueOf(updated.getTime());
        }

        jsonObj.put("updated", updatedJSON);

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.debaterevision.model.DebateCategory[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateCategory model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.debaterevision.model.DebateCategory[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateCategory[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.debaterevision.model.DebateCategory> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateCategory model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
