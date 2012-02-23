package com.ext.portlet.plans.service.http;

import com.ext.portlet.plans.model.PlanTemplateSection;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="PlanTemplateSectionJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.plans.service.http.PlanTemplateSectionServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.http.PlanTemplateSectionServiceJSON
 *
 */
public class PlanTemplateSectionJSONSerializer {
    public static JSONObject toJSONObject(PlanTemplateSection model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("planTemplateId", model.getPlanTemplateId());
        jsonObj.put("planSectionId", model.getPlanSectionId());
        jsonObj.put("weight", model.getWeight());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlanTemplateSection[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanTemplateSection model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlanTemplateSection[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanTemplateSection[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.plans.model.PlanTemplateSection> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanTemplateSection model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
