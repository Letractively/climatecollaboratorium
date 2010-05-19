/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.service.http;

import com.ext.portlet.mass_messaging.model.MessagingMessageConversion;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.List;


/**
 * <a href="MessagingMessageConversionJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.mass_messaging.service.http.MessagingMessageConversionServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.mass_messaging.service.http.MessagingMessageConversionServiceJSON
 *
 */
public class MessagingMessageConversionJSONSerializer {
    public static JSONObject toJSONObject(MessagingMessageConversion model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("conversionId", model.getConversionId());
        jsonObj.put("conversionTypeId", model.getConversionTypeId());
        jsonObj.put("messageId", model.getMessageId());
        jsonObj.put("ipAddress", model.getIpAddress());
        jsonObj.put("extraData", model.getExtraData());
        jsonObj.put("extraData2", model.getExtraData2());

        Date createDate = model.getCreateDate();

        String createDateJSON = StringPool.BLANK;

        if (createDate != null) {
            createDateJSON = String.valueOf(createDate.getTime());
        }

        jsonObj.put("createDate", createDateJSON);

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversion[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (MessagingMessageConversion model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.mass_messaging.model.MessagingMessageConversion[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (MessagingMessageConversion[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.mass_messaging.model.MessagingMessageConversion> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (MessagingMessageConversion model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
