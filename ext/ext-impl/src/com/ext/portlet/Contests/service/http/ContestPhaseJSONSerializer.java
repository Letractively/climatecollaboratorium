package com.ext.portlet.contests.service.http;

import com.ext.portlet.contests.model.ContestPhase;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.List;


/**
 * <a href="ContestPhaseJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.contests.service.http.ContestPhaseServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.service.http.ContestPhaseServiceJSON
 *
 */
public class ContestPhaseJSONSerializer {
    public static JSONObject toJSONObject(ContestPhase model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("ContestPhasePK", model.getContestPhasePK());
        jsonObj.put("ContestPK", model.getContestPK());
        jsonObj.put("ContestPhaseName", model.getContestPhaseName());
        jsonObj.put("ContestPhaseDescription",
            model.getContestPhaseDescription());
        jsonObj.put("ContestPhaseStatus", model.getContestPhaseStatus());

        Date PhaseStartDate = model.getPhaseStartDate();

        String PhaseStartDateJSON = StringPool.BLANK;

        if (PhaseStartDate != null) {
            PhaseStartDateJSON = String.valueOf(PhaseStartDate.getTime());
        }

        jsonObj.put("PhaseStartDate", PhaseStartDateJSON);
        jsonObj.put("PhaseEndDate", model.getPhaseEndDate());
        jsonObj.put("nextStatus", model.getNextStatus());

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
        com.ext.portlet.contests.model.ContestPhase[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ContestPhase model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.contests.model.ContestPhase[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ContestPhase[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.contests.model.ContestPhase> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ContestPhase model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
