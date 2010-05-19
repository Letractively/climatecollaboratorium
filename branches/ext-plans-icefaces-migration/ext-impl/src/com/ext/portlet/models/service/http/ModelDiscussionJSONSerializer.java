/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.service.http;

import com.ext.portlet.models.model.ModelDiscussion;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="ModelDiscussionJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.models.service.http.ModelDiscussionServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.service.http.ModelDiscussionServiceJSON
 *
 */
public class ModelDiscussionJSONSerializer {
    public static JSONObject toJSONObject(ModelDiscussion model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("modelDiscussionId", model.getModelDiscussionId());
        jsonObj.put("modelId", model.getModelId());
        jsonObj.put("categoryId", model.getCategoryId());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.models.model.ModelDiscussion[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ModelDiscussion model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.models.model.ModelDiscussion[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ModelDiscussion[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.models.model.ModelDiscussion> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (ModelDiscussion model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
