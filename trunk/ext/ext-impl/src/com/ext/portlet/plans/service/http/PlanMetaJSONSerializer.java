package com.ext.portlet.plans.service.http;

import com.ext.portlet.plans.model.PlanMeta;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.List;


/**
 * <a href="PlanMetaJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.plans.service.http.PlanMetaServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.http.PlanMetaServiceJSON
 *
 */
public class PlanMetaJSONSerializer {
    public static JSONObject toJSONObject(PlanMeta model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("id", model.getId());
        jsonObj.put("planId", model.getPlanId());
        jsonObj.put("planTypeId", model.getPlanTypeId());
        jsonObj.put("planCreated", model.getPlanCreated());
        jsonObj.put("authorId", model.getAuthorId());
        jsonObj.put("votes", model.getVotes());
        jsonObj.put("planGroupId", model.getPlanGroupId());
        jsonObj.put("open", model.getOpen());
        jsonObj.put("status", model.getStatus());
        jsonObj.put("mbCategoryId", model.getMbCategoryId());
        jsonObj.put("categoryGroupId", model.getCategoryGroupId());
        jsonObj.put("version", model.getVersion());
        jsonObj.put("planVersion", model.getPlanVersion());

        Date created = model.getCreated();

        String createdJSON = StringPool.BLANK;

        if (created != null) {
            createdJSON = String.valueOf(created.getTime());
        }

        jsonObj.put("created", createdJSON);
        jsonObj.put("updateAuthorId", model.getUpdateAuthorId());
        jsonObj.put("modelId", model.getModelId());
        jsonObj.put("promoted", model.getPromoted());
        jsonObj.put("previousContestPhase", model.getPreviousContestPhase());
        jsonObj.put("contestPhase", model.getContestPhase());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlanMeta[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanMeta model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlanMeta[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanMeta[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.plans.model.PlanMeta> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanMeta model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
