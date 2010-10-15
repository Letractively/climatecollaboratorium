package com.ext.portlet.discussions.service.http;

import com.ext.portlet.discussions.model.DiscussionMessage;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.List;


/**
 * <a href="DiscussionMessageJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.discussions.service.http.DiscussionMessageServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.discussions.service.http.DiscussionMessageServiceJSON
 *
 */
public class DiscussionMessageJSONSerializer {
    public static JSONObject toJSONObject(DiscussionMessage model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("pk", model.getPk());
        jsonObj.put("messageId", model.getMessageId());
        jsonObj.put("subject", model.getSubject());
        jsonObj.put("body", model.getBody());
        jsonObj.put("threadId", model.getThreadId());
        jsonObj.put("categoryId", model.getCategoryId());
        jsonObj.put("categoryGroupId", model.getCategoryGroupId());
        jsonObj.put("authorId", model.getAuthorId());

        Date createDate = model.getCreateDate();

        String createDateJSON = StringPool.BLANK;

        if (createDate != null) {
            createDateJSON = String.valueOf(createDate.getTime());
        }

        jsonObj.put("createDate", createDateJSON);
        jsonObj.put("version", model.getVersion());

        Date deleted = model.getDeleted();

        String deletedJSON = StringPool.BLANK;

        if (deleted != null) {
            deletedJSON = String.valueOf(deleted.getTime());
        }

        jsonObj.put("deleted", deletedJSON);
        jsonObj.put("responsesCount", model.getResponsesCount());

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
        com.ext.portlet.discussions.model.DiscussionMessage[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DiscussionMessage model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.discussions.model.DiscussionMessage[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DiscussionMessage[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.discussions.model.DiscussionMessage> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (DiscussionMessage model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
