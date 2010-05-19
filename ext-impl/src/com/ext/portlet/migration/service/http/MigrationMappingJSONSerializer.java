/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.migration.service.http;

import com.ext.portlet.migration.model.MigrationMapping;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.List;


/**
 * <a href="MigrationMappingJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.migration.service.http.MigrationMappingServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.migration.service.http.MigrationMappingServiceJSON
 *
 */
public class MigrationMappingJSONSerializer {
    public static JSONObject toJSONObject(MigrationMapping model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("entityName", model.getEntityName());
        jsonObj.put("oldId", model.getOldId());
        jsonObj.put("newId", model.getNewId());
        jsonObj.put("userId", model.getUserId());

        Date createDate = model.getCreateDate();

        String createDateJSON = StringPool.BLANK;

        if (createDate != null) {
            createDateJSON = String.valueOf(createDate.getTime());
        }

        jsonObj.put("createDate", createDateJSON);

        Date modifiedDate = model.getModifiedDate();

        String modifiedDateJSON = StringPool.BLANK;

        if (modifiedDate != null) {
            modifiedDateJSON = String.valueOf(modifiedDate.getTime());
        }

        jsonObj.put("modifiedDate", modifiedDateJSON);

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.migration.model.MigrationMapping[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (MigrationMapping model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.migration.model.MigrationMapping[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (MigrationMapping[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.migration.model.MigrationMapping> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (MigrationMapping model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
