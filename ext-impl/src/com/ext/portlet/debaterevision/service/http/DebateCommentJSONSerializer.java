package com.ext.portlet.debaterevision.service.http;

import com.ext.portlet.debaterevision.model.DebateComment;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.List;


/**
 * <a href="DebateCommentJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.debaterevision.service.http.DebateCommentServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.service.http.DebateCommentServiceJSON
 *
 */
public class DebateCommentJSONSerializer {
    public static JSONObject toJSONObject(DebateComment model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("debateCommentId", model.getDebateCommentId());
        jsonObj.put("debateItemId", model.getDebateItemId());
        jsonObj.put("debateCommentTitle", model.getDebateCommentTitle());
        jsonObj.put("debateCommentDetail", model.getDebateCommentDetail());
        jsonObj.put("itemVersion", model.getItemVersion());
        jsonObj.put("authorId", model.getAuthorId());

        Date updated = model.getUpdated();

        String updatedJSON = StringPool.BLANK;

        if (updated != null) {
            updatedJSON = String.valueOf(updated.getTime());
        }

        jsonObj.put("updated", updatedJSON);

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.debaterevision.model.DebateComment[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateComment model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.debaterevision.model.DebateComment[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateComment[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.debaterevision.model.DebateComment> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateComment model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
