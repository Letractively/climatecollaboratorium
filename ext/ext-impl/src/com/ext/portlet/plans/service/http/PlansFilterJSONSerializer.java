package com.ext.portlet.plans.service.http;

import com.ext.portlet.plans.model.PlansFilter;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.List;


/**
 * <a href="PlansFilterJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.plans.service.http.PlansFilterServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.http.PlansFilterServiceJSON
 *
 */
public class PlansFilterJSONSerializer {
    public static JSONObject toJSONObject(PlansFilter model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("userId", model.getUserId());
        jsonObj.put("planTypeId", model.getPlanTypeId());
        jsonObj.put("name", model.getName());
        jsonObj.put("creator", model.getCreator());
        jsonObj.put("description", model.getDescription());
        jsonObj.put("CO2From", model.getCO2From());
        jsonObj.put("CO2To", model.getCO2To());
        jsonObj.put("votesFrom", model.getVotesFrom());
        jsonObj.put("votesTo", model.getVotesTo());
        jsonObj.put("damageFrom", model.getDamageFrom());
        jsonObj.put("damageTo", model.getDamageTo());
        jsonObj.put("mitigationFrom", model.getMitigationFrom());
        jsonObj.put("mitigationTo", model.getMitigationTo());

        Date dateFrom = model.getDateFrom();

        String dateFromJSON = StringPool.BLANK;

        if (dateFrom != null) {
            dateFromJSON = String.valueOf(dateFrom.getTime());
        }

        jsonObj.put("dateFrom", dateFromJSON);

        Date dateTo = model.getDateTo();

        String dateToJSON = StringPool.BLANK;

        if (dateTo != null) {
            dateToJSON = String.valueOf(dateTo.getTime());
        }

        jsonObj.put("dateTo", dateToJSON);
        jsonObj.put("filterPositionsAll", model.getFilterPositionsAll());
        jsonObj.put("enabled", model.getEnabled());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlansFilter[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlansFilter model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlansFilter[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlansFilter[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.plans.model.PlansFilter> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlansFilter model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
