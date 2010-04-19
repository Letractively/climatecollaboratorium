package com.ext.portlet.debaterevision.service.http;

import com.ext.portlet.debaterevision.model.DebateItem;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.List;


/**
 * <a href="DebateItemJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.debaterevision.service.http.DebateItemServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.service.http.DebateItemServiceJSON
 *
 */
public class DebateItemJSONSerializer {
    public static JSONObject toJSONObject(DebateItem model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("debateItemPK", model.getDebateItemPK());
        jsonObj.put("debateItemId", model.getDebateItemId());
        jsonObj.put("debateItemParentId", model.getDebateItemParentId());
        jsonObj.put("debateId", model.getDebateId());
        jsonObj.put("debateSummary", model.getDebateSummary());
        jsonObj.put("debateDetail", model.getDebateDetail());
        jsonObj.put("debatePostType", model.getDebatePostType());
        jsonObj.put("authorId", model.getAuthorId());
        jsonObj.put("itemVersion", model.getItemVersion());
        jsonObj.put("treeVersion", model.getTreeVersion());

        Date updated = model.getUpdated();

        String updatedJSON = StringPool.BLANK;

        if (updated != null) {
            updatedJSON = String.valueOf(updated.getTime());
        }

        jsonObj.put("updated", updatedJSON);
        jsonObj.put("status", model.getStatus());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.debaterevision.model.DebateItem[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateItem model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.debaterevision.model.DebateItem[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateItem[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.debaterevision.model.DebateItem> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateItem model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
