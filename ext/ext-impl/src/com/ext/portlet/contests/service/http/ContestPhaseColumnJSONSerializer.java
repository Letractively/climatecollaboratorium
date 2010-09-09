package com.ext.portlet.contests.service.http;

import com.ext.portlet.contests.model.ContestPhaseColumn;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="ContestPhaseColumnJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.contests.service.http.ContestPhaseColumnServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.service.http.ContestPhaseColumnServiceJSON
 *
 */
public class ContestPhaseColumnJSONSerializer {
    public static JSONObject toJSONObject(ContestPhaseColumn model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("id", model.getId());
        jsonObj.put("ContestPhasePK", model.getContestPhasePK());
        jsonObj.put("columnName", model.getColumnName());
        jsonObj.put("columnWeight", model.getColumnWeight());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.contests.model.ContestPhaseColumn[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ContestPhaseColumn model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.contests.model.ContestPhaseColumn[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ContestPhaseColumn[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.contests.model.ContestPhaseColumn> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ContestPhaseColumn model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
