package com.ext.portlet.plans.service.http;

import com.ext.portlet.plans.model.Plan;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.List;


/**
 * <a href="PlanJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.plans.service.http.PlanServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.http.PlanServiceJSON
 *
 */
public class PlanJSONSerializer {
    public static JSONObject toJSONObject(Plan model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("planId", model.getPlanId());
        jsonObj.put("name", model.getName());
        jsonObj.put("content", model.getContent());
        jsonObj.put("shortcontent", model.getShortcontent());
        jsonObj.put("planTypeId", model.getPlanTypeId());
        jsonObj.put("companyId", model.getCompanyId());
        jsonObj.put("groupId", model.getGroupId());
        jsonObj.put("childGroupId", model.getChildGroupId());
        jsonObj.put("MBCategoryId", model.getMBCategoryId());
        jsonObj.put("scenarioId", model.getScenarioId());
        jsonObj.put("topicId", model.getTopicId());
        jsonObj.put("votes", model.getVotes());

        Date createDate = model.getCreateDate();

        String createDateJSON = StringPool.BLANK;

        if (createDate != null) {
            createDateJSON = String.valueOf(createDate.getTime());
        }

        jsonObj.put("createDate", createDateJSON);

        Date publishDate = model.getPublishDate();

        String publishDateJSON = StringPool.BLANK;

        if (publishDate != null) {
            publishDateJSON = String.valueOf(publishDate.getTime());
        }

        jsonObj.put("publishDate", publishDateJSON);
        jsonObj.put("userId", model.getUserId());
        jsonObj.put("userName", model.getUserName());
        jsonObj.put("userScreenName", model.getUserScreenName());

        Date modifiedDate = model.getModifiedDate();

        String modifiedDateJSON = StringPool.BLANK;

        if (modifiedDate != null) {
            modifiedDateJSON = String.valueOf(modifiedDate.getTime());
        }

        jsonObj.put("modifiedDate", modifiedDateJSON);
        jsonObj.put("userEdited", model.getUserEdited());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.Plan[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (Plan model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.Plan[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (Plan[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.plans.model.Plan> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (Plan model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
