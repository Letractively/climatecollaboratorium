package com.ext.portlet.discussions.service.http;

import com.ext.portlet.discussions.model.DiscussionCategory;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.List;


/**
 * <a href="DiscussionCategoryJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.discussions.service.http.DiscussionCategoryServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.discussions.service.http.DiscussionCategoryServiceJSON
 *
 */
public class DiscussionCategoryJSONSerializer {
    public static JSONObject toJSONObject(DiscussionCategory model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("pk", model.getPk());
        jsonObj.put("categoryId", model.getCategoryId());
        jsonObj.put("categoryGroupId", model.getCategoryGroupId());
        jsonObj.put("authorId", model.getAuthorId());
        jsonObj.put("name", model.getName());
        jsonObj.put("description", model.getDescription());

        Date createDate = model.getCreateDate();

        String createDateJSON = StringPool.BLANK;

        if (createDate != null) {
            createDateJSON = String.valueOf(createDate.getTime());
        }

        jsonObj.put("createDate", createDateJSON);

        Date deleted = model.getDeleted();

        String deletedJSON = StringPool.BLANK;

        if (deleted != null) {
            deletedJSON = String.valueOf(deleted.getTime());
        }

        jsonObj.put("deleted", deletedJSON);
        jsonObj.put("threadsCount", model.getThreadsCount());

        Date lastActivityDate = model.getLastActivityDate();

        String lastActivityDateJSON = StringPool.BLANK;

        if (lastActivityDate != null) {
            lastActivityDateJSON = String.valueOf(lastActivityDate.getTime());
        }

        jsonObj.put("lastActivityDate", lastActivityDateJSON);
        jsonObj.put("lastActivityAuthorId", model.getLastActivityAuthorId());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.discussions.model.DiscussionCategory[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DiscussionCategory model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.discussions.model.DiscussionCategory[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DiscussionCategory[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.discussions.model.DiscussionCategory> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DiscussionCategory model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
