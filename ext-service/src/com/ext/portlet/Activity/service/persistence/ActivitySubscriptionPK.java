/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.Activity.service.persistence;

import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;


public class ActivitySubscriptionPK implements Comparable<ActivitySubscriptionPK>,
    Serializable {
    public Long entityId;
    public Long receiverId;
    public String activitytype;
    public String portletId;

    public ActivitySubscriptionPK() {
    }

    public ActivitySubscriptionPK(Long entityId, Long receiverId,
        String activitytype, String portletId) {
        this.entityId = entityId;
        this.receiverId = receiverId;
        this.activitytype = activitytype;
        this.portletId = portletId;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getActivitytype() {
        return activitytype;
    }

    public void setActivitytype(String activitytype) {
        this.activitytype = activitytype;
    }

    public String getPortletId() {
        return portletId;
    }

    public void setPortletId(String portletId) {
        this.portletId = portletId;
    }

    public int compareTo(ActivitySubscriptionPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        value = entityId.compareTo(pk.entityId);

        if (value != 0) {
            return value;
        }

        value = receiverId.compareTo(pk.receiverId);

        if (value != 0) {
            return value;
        }

        value = activitytype.compareTo(pk.activitytype);

        if (value != 0) {
            return value;
        }

        value = portletId.compareTo(pk.portletId);

        if (value != 0) {
            return value;
        }

        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        ActivitySubscriptionPK pk = null;

        try {
            pk = (ActivitySubscriptionPK) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        if ((entityId.equals(pk.entityId)) &&
                (receiverId.equals(pk.receiverId)) &&
                (activitytype.equals(pk.activitytype)) &&
                (portletId.equals(pk.portletId))) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (entityId.toString() + receiverId.toString() +
        String.valueOf(activitytype) + String.valueOf(portletId)).hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("entityId");
        sb.append(StringPool.EQUAL);
        sb.append(entityId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("receiverId");
        sb.append(StringPool.EQUAL);
        sb.append(receiverId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("activitytype");
        sb.append(StringPool.EQUAL);
        sb.append(activitytype);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("portletId");
        sb.append(StringPool.EQUAL);
        sb.append(portletId);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
