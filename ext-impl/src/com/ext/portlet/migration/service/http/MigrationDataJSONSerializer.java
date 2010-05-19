/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.migration.service.http;

import com.ext.portlet.migration.model.MigrationData;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.List;


/**
 * <a href="MigrationDataJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.migration.service.http.MigrationDataServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.migration.service.http.MigrationDataServiceJSON
 *
 */
public class MigrationDataJSONSerializer {
    public static JSONObject toJSONObject(MigrationData model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("migrationId", model.getMigrationId());
        jsonObj.put("name", model.getName());
        jsonObj.put("xml", model.getXml());
        jsonObj.put("description", model.getDescription());
        jsonObj.put("users", model.getUsers());
        jsonObj.put("plans", model.getPlans());
        jsonObj.put("questions", model.getQuestions());
        jsonObj.put("alternatives", model.getAlternatives());
        jsonObj.put("arguments", model.getArguments());
        jsonObj.put("votes", model.getVotes());
        jsonObj.put("userId", model.getUserId());

        Date modifiedDate = model.getModifiedDate();

        String modifiedDateJSON = StringPool.BLANK;

        if (modifiedDate != null) {
            modifiedDateJSON = String.valueOf(modifiedDate.getTime());
        }

        jsonObj.put("modifiedDate", modifiedDateJSON);

        Date createdDate = model.getCreatedDate();

        String createdDateJSON = StringPool.BLANK;

        if (createdDate != null) {
            createdDateJSON = String.valueOf(createdDate.getTime());
        }

        jsonObj.put("createdDate", createdDateJSON);

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.migration.model.MigrationData[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (MigrationData model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.migration.model.MigrationData[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (MigrationData[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.migration.model.MigrationData> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (MigrationData model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
