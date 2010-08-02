package com.ext.portlet.discussions.service.http;

import com.ext.portlet.discussions.model.DiscussionCategoryGroup;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="DiscussionCategoryGroupJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.discussions.service.http.DiscussionCategoryGroupServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.discussions.service.http.DiscussionCategoryGroupServiceJSON
 *
 */
public class DiscussionCategoryGroupJSONSerializer {
    public static JSONObject toJSONObject(DiscussionCategoryGroup model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("id", model.getId());
        jsonObj.put("description", model.getDescription());
        jsonObj.put("url", model.getUrl());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.discussions.model.DiscussionCategoryGroup[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DiscussionCategoryGroup model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.discussions.model.DiscussionCategoryGroup[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DiscussionCategoryGroup[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.discussions.model.DiscussionCategoryGroup> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DiscussionCategoryGroup model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
