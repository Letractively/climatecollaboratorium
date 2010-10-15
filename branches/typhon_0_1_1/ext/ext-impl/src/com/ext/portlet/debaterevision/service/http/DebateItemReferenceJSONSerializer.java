package com.ext.portlet.debaterevision.service.http;

import com.ext.portlet.debaterevision.model.DebateItemReference;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="DebateItemReferenceJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.debaterevision.service.http.DebateItemReferenceServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.service.http.DebateItemReferenceServiceJSON
 *
 */
public class DebateItemReferenceJSONSerializer {
    public static JSONObject toJSONObject(DebateItemReference model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("debateItemReferencePK", model.getDebateItemReferencePK());
        jsonObj.put("debateItemId", model.getDebateItemId());
        jsonObj.put("debateId", model.getDebateId());
        jsonObj.put("itemVersion", model.getItemVersion());
        jsonObj.put("status", model.getStatus());
        jsonObj.put("text", model.getText());
        jsonObj.put("url", model.getUrl());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.debaterevision.model.DebateItemReference[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateItemReference model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.debaterevision.model.DebateItemReference[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateItemReference[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.debaterevision.model.DebateItemReference> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DebateItemReference model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
