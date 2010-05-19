package com.ext.portlet.debaterevision.service.http;

import com.ext.portlet.debaterevision.model.Debate;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.List;


/**
 * <a href="DebateJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.debaterevision.service.http.DebateServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.service.http.DebateServiceJSON
 *
 */
public class DebateJSONSerializer {
    public static JSONObject toJSONObject(Debate model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("debatePK", model.getDebatePK());
        jsonObj.put("debateId", model.getDebateId());
        jsonObj.put("treeVersion", model.getTreeVersion());

        Date updated = model.getUpdated();

        String updatedJSON = StringPool.BLANK;

        if (updated != null) {
            updatedJSON = String.valueOf(updated.getTime());
        }

        jsonObj.put("updated", updatedJSON);
        jsonObj.put("status", model.getStatus());
        jsonObj.put("rootCommentId", model.getRootCommentId());
        jsonObj.put("authorId", model.getAuthorId());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.debaterevision.model.Debate[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (Debate model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.debaterevision.model.Debate[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (Debate[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.debaterevision.model.Debate> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (Debate model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
