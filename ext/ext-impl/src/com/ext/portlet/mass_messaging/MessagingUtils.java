/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.mass_messaging;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;


import com.ext.portlet.mass_messaging.model.MessagingMessage;
import com.ext.portlet.mass_messaging.model.MessagingMessageConversion;
import com.ext.portlet.mass_messaging.model.MessagingMessageConversionType;
import com.ext.portlet.mass_messaging.service.MessagingMessageConversionLocalServiceUtil;
import com.ext.portlet.mass_messaging.service.MessagingMessageConversionTypeLocalServiceUtil;
import com.ext.portlet.mass_messaging.service.MessagingMessageLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.WebKeys;

public class MessagingUtils {

    private final static Log _log = LogFactoryUtil.getLog(MessagingServlet.class);
    private static final String PROPERTY_IP_ADDRESS = "ipAddress";
    private static final String PROPERTY_MESSAGE_ID = "messageId";
    
    
    public static void addConversion(Long messageId, MessagingConversionTypes typeName, PortletRequest request, 
            Object extraData, Object extraData2)
    throws SystemException, PortalException {
        addConversion(messageId, typeName, PortalUtil.getHttpServletRequest(request).getRemoteAddr(), extraData, extraData2);
    }
    
    
    public static void addConversion(Long messageId, MessagingConversionTypes typeName, HttpServletRequest request, 
            Object extraData, Object extraData2) throws SystemException, PortalException {
        addConversion(messageId, typeName, request.getRemoteAddr(), extraData, extraData2);
        
    }

    public static void addConversion(Long messageId, MessagingConversionTypes typeName, String clientIP, 
            Object extraData, Object extraData2)
            throws SystemException, PortalException {

        Date now = new Date();

        MessagingMessage message = MessagingMessageLocalServiceUtil.getMessagingMessage(messageId);

        MessagingMessageConversionType type = MessagingMessageConversionTypeLocalServiceUtil.getByName(typeName.name());

        Long conversionId = CounterLocalServiceUtil.increment(MessagingMessageConversion.class.getName());

        MessagingMessageConversion conversion = MessagingMessageConversionLocalServiceUtil
                .createMessagingMessageConversion(conversionId);

        conversion.setConversionTypeId(type.getTypeId());
        conversion.setMessageId(message.getMessageId());
        conversion.setIpAddress(clientIP);
        conversion.setCreateDate(now);

        if (extraData != null) {
            conversion.setExtraData(extraData.toString());
        }
        if (extraData2 != null) {
            conversion.setExtraData2(extraData2.toString());
        }
        MessagingMessageConversionLocalServiceUtil.addMessagingMessageConversion(conversion);

        long convCount = 1;
        if (message.getConversionCount() != null) {
            convCount += message.getConversionCount();
        }

        message.setConversionCount(convCount);
        message.setModifiedDate(now);
        MessagingMessageLocalServiceUtil.updateMessagingMessage(message);

    }

    public static void handleUserRegistered(ActionRequest actionRequest, Long messageId) {

        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
        Long userId = themeDisplay.getUserId();

        try {
            MessagingUtils.addConversion(messageId, MessagingConversionTypes.USER_REGISTERED, actionRequest, userId, null);
        } catch (Exception e) {
            // no exception should be rethrown
            _log.error("Can't add message conversion details after user registration", e);
        }

    }

    public static String createConvertionLink(Map<String, String> linkParameters, PortletRequest request) {
        StringBuilder link = new StringBuilder();
        link.append(getPortalURL(request));
        link.append(MessagingConstants.CONVERTION_PATH);
        
        for(String parameterName: linkParameters.keySet()) {
            link.append(MessagingConstants.CONVERSION_PARAMETER_DELIMITER);
            link.append(parameterName);
            link.append(MessagingConstants.CONVERSION_PARAMETER_DELIMITER);
            link.append(linkParameters.get(parameterName));
            
        }
        return link.toString();
    }
    
    public static Map<String, String> getLinkParameters(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        String parameters[] = pathInfo.split(MessagingConstants.CONVERSION_PARAMETER_DELIMITER);
        
        Map<String, String> parametersMap = new HashMap<String, String>();
        for (int i = 1; i < parameters.length; i += 2) {
            parametersMap.put(parameters[i], parameters[i+1]);
        }
        
        return parametersMap;
    }
    
    
    public static String getPortalURL(PortletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
    
    public static int countConversionsByIP(Long messageId, MessagingConversionTypes typeName) throws SystemException {
        
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MessagingMessageConversion.class);
        Criterion criterionMessageId = RestrictionsFactoryUtil.eq(PROPERTY_MESSAGE_ID, messageId);
        Projection groupByIPAddress = ProjectionFactoryUtil.groupProperty(PROPERTY_IP_ADDRESS);
        Projection count = ProjectionFactoryUtil.count(PROPERTY_IP_ADDRESS);
        
        MessagingMessageConversionType type = MessagingMessageConversionTypeLocalServiceUtil.getByName(typeName.name);
        Criterion criterionConversionType = RestrictionsFactoryUtil.eq("conversionTypeId", type.getTypeId());
        
        
        ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
        projectionList.add(groupByIPAddress);
        projectionList.add(count);
        
        dynamicQuery.add(criterionMessageId);
        dynamicQuery.add(criterionConversionType);
        dynamicQuery.setProjection(projectionList);
        
        List<Object> result = MessagingMessageConversionLocalServiceUtil.dynamicQuery(dynamicQuery);
        return result.size();
        
    }
    
    public static int countConversionsByRecipient(Long messageId) throws SystemException {
        
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MessagingMessageConversion.class);
        Criterion criterionMessageId = RestrictionsFactoryUtil.eq(PROPERTY_MESSAGE_ID, messageId);
        
        MessagingMessageConversionType type = MessagingMessageConversionTypeLocalServiceUtil.getByName(MessagingConversionTypes.EMAIL_LINK_CLICKED.name());
        Criterion criterionConversionType = RestrictionsFactoryUtil.eq("conversionTypeId", type.getTypeId());
        Projection groupByIPAddress = ProjectionFactoryUtil.groupProperty("extraData2");
        
        ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
        projectionList.add(groupByIPAddress);
        
        dynamicQuery.add(criterionMessageId);
        dynamicQuery.add(criterionConversionType);
        dynamicQuery.setProjection(projectionList);
        
        List<Object> result = MessagingMessageConversionLocalServiceUtil.dynamicQuery(dynamicQuery);
        return result.size();
    }
}
