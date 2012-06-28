package com.ext.portlet.plans.service.http;

import com.ext.portlet.plans.model.PlanSectionDefinition;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="PlanSectionDefinitionJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.plans.service.http.PlanSectionDefinitionServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.http.PlanSectionDefinitionServiceJSON
 *
 */
public class PlanSectionDefinitionJSONSerializer {
    public static JSONObject toJSONObject(PlanSectionDefinition model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("id", model.getId());
        jsonObj.put("adminTitle", model.getAdminTitle());
        jsonObj.put("title", model.getTitle());
        jsonObj.put("defaultText", model.getDefaultText());
        jsonObj.put("helpText", model.getHelpText());
        jsonObj.put("characterLimit", model.getCharacterLimit());
        jsonObj.put("focusAreaId", model.getFocusAreaId());
        jsonObj.put("locked", model.getLocked());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlanSectionDefinition[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanSectionDefinition model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.plans.model.PlanSectionDefinition[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanSectionDefinition[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.plans.model.PlanSectionDefinition> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (PlanSectionDefinition model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
