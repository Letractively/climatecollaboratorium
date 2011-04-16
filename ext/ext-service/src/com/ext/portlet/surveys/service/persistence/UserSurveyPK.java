package com.ext.portlet.surveys.service.persistence;

import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;


public class UserSurveyPK implements Comparable<UserSurveyPK>, Serializable {
    public Long userId;
    public String eventName;

    public UserSurveyPK() {
    }

    public UserSurveyPK(Long userId, String eventName) {
        this.userId = userId;
        this.eventName = eventName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int compareTo(UserSurveyPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        value = userId.compareTo(pk.userId);

        if (value != 0) {
            return value;
        }

        value = eventName.compareTo(pk.eventName);

        if (value != 0) {
            return value;
        }

        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        UserSurveyPK pk = null;

        try {
            pk = (UserSurveyPK) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        if ((userId.equals(pk.userId)) && (eventName.equals(pk.eventName))) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (userId.toString() + String.valueOf(eventName)).hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("userId");
        sb.append(StringPool.EQUAL);
        sb.append(userId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("eventName");
        sb.append(StringPool.EQUAL);
        sb.append(eventName);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
