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
        jsonObj.put("ContestDescription", model.getContestDescription());

        Date PlanTypeId = model.getPlanTypeId();

        String PlanTypeIdJSON = StringPool.BLANK;

        if (PlanTypeId != null) {
            PlanTypeIdJSON = String.valueOf(PlanTypeId.getTime());
        }

        jsonObj.put("PlanTypeId", PlanTypeIdJSON);

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
