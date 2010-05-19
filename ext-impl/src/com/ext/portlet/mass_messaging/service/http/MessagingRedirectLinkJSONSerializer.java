/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging.service.http;

import com.ext.portlet.mass_messaging.model.MessagingRedirectLink;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.List;


/**
 * <a href="MessagingRedirectLinkJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.mass_messaging.service.http.MessagingRedirectLinkServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.mass_messaging.service.http.MessagingRedirectLinkServiceJSON
 *
 */
public class MessagingRedirectLinkJSONSerializer {
    public static JSONObject toJSONObject(MessagingRedirectLink model) {
        JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

        jsonObj.put("redirectId", model.getRedirectId());
        jsonObj.put("link", model.getLink());
        jsonObj.put("messageId", model.getMessageId());

        Date createDate = model.getCreateDate();

        String createDateJSON = StringPool.BLANK;

        if (createDate != null) {
            createDateJSON = String.valueOf(createDate.getTime());
        }

        jsonObj.put("createDate", createDateJSON);

        return jsonObj;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.mass_messaging.model.MessagingRedirectLink[] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (MessagingRedirectLink model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        com.ext.portlet.mass_messaging.model.MessagingRedirectLink[][] models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (MessagingRedirectLink[] model : models) {
            jsonArray.put(toJSONArray(model));
        }

        return jsonArray;
    }

    public static JSONArray toJSONArray(
        List<com.ext.portlet.mass_messaging.model.MessagingRedirectLink> models) {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        for (MessagingRedirectLink model : models) {
            jsonArray.put(toJSONObject(model));
        }

        return jsonArray;
    }
}
