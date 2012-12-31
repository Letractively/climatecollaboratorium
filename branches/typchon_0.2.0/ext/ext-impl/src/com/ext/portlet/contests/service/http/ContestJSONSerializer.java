package com.ext.portlet.contests.service.http;

import com.ext.portlet.contests.model.Contest;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.List;


/**
 * <a href="ContestJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.contests.service.http.ContestServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.service.http.ContestServiceJSON
 *
 */
public class ContestJSONSerializer {
    public static JSONObject toJSONObject(Contest model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("ContestPK", model.getContestPK());
        jsonObj.put("ContestName", model.getContestName());
        jsonObj.put("ContestShortName", model.getContestShortName());
        jsonObj.put("ContestDescription", model.getContestDescription());
        jsonObj.put("ContestModelDescription",
            model.getContestModelDescription());
        jsonObj.put("ContestPositionsDescription",
            model.getContestPositionsDescription());
        jsonObj.put("defaultPlanDescription", model.getDefaultPlanDescription());
        jsonObj.put("PlanTypeId", model.getPlanTypeId());

        Date created = model.getCreated();

        String createdJSON = StringPool.BLANK;

        if (created != null) {
            createdJSON = String.valueOf(created.getTime());
        }

        jsonObj.put("created", createdJSON);

        Date updated = model.getUpdated();

        String updatedJSON = StringPool.BLANK;

        if (updated != null) {
            updatedJSON = String.valueOf(updated.getTime());
        }

        jsonObj.put("updated", updatedJSON);
        jsonObj.put("authorId", model.getAuthorId());
        jsonObj.put("contestActive", model.getContestActive());
        jsonObj.put("planTemplateId", model.getPlanTemplateId());
        jsonObj.put("focusAreaId", model.getFocusAreaId());
        jsonObj.put("contestLogoId", model.getContestLogoId());
        jsonObj.put("featured", model.getFeatured());
        jsonObj.put("plansOpenByDefault", model.getPlansOpenByDefault());
        jsonObj.put("flag", model.getFlag());
        jsonObj.put("flagText", model.getFlagText());
        jsonObj.put("flagTooltip", model.getFlagTooltip());
        jsonObj.put("groupId", model.getGroupId());
        jsonObj.put("discussionGroupId", model.getDiscussionGroupId());
        jsonObj.put("weight", model.getWeight());
        jsonObj.put("resourcesUrl", model.getResourcesUrl());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.contests.model.Contest[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (Contest model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.contests.model.Contest[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (Contest[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.contests.model.Contest> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (Contest model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
