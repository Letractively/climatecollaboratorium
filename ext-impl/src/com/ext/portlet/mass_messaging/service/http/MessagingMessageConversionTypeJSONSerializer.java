/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.service.http;

import com.ext.portlet.mass_messaging.model.MessagingMessageConversionType;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;


/**
 * <a href="MessagingMessageConversionTypeJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.mass_messaging.service.http.MessagingMessageConversionTypeServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.mass_messaging.service.http.MessagingMessageConversionTypeServiceJSON
 *
 */
public class MessagingMessageConversionTypeJSONSerializer {
    public static JSONObject toJSONObject(MessagingMessageConversionType model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("typeId", model.getTypeId());
        jsonObj.put("name", model.getName());
        jsonObj.put("description", model.getDescription());

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversionType[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (MessagingMessageConversionType model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversionType[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (MessagingMessageConversionType[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.mass_messaging.model.MessagingMessageConversionType> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (MessagingMessageConversionType model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
