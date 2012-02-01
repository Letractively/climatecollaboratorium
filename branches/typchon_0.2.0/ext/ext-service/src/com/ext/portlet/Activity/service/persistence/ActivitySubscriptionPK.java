package com.ext.portlet.Activity.service.persistence;

import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;


public class ActivitySubscriptionPK implements Comparable<ActivitySubscriptionPK>,
    Serializable {
    public Long classNameId;
    public Long classPK;
    public Integer type;
    public String extraData;
    public Long receiverId;

    public ActivitySubscriptionPK() {
    }

    public ActivitySubscriptionPK(Long classNameId, Long classPK, Integer type,
        String extraData, Long receiverId) {
        this.classNameId = classNameId;
        this.classPK = classPK;
        this.type = type;
        this.extraData = extraData;
        this.receiverId = receiverId;
    }

    public Long getClassNameId() {
        return classNameId;
    }

    public void setClassNameId(Long classNameId) {
        this.classNameId = classNameId;
    }

    public Long getClassPK() {
        return classPK;
    }

    public void setClassPK(Long classPK) {
        this.classPK = classPK;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getExtraData() {
        return extraData;
    }

    public void setExtraData(String extraData) {
        this.extraData = extraData;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public int compareTo(ActivitySubscriptionPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        value = classNameId.compareTo(pk.classNameId);

        if (value != 0) {
            return value;
        }

        value = classPK.compareTo(pk.classPK);

        if (value != 0) {
            return value;
        }

        value = type.compareTo(pk.type);

        if (value != 0) {
            return value;
        }

        value = extraData.compareTo(pk.extraData);

        if (value != 0) {
            return value;
        }

        value = receiverId.compareTo(pk.receiverId);

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

        if ((classNameId.equals(pk.classNameId)) &&
                (classPK.equals(pk.classPK)) && (type.equals(pk.type)) &&
                (extraData.equals(pk.extraData)) &&
                (receiverId.equals(pk.receiverId))) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (classNameId.toString() + classPK.toString() + type.toString() +
        String.valueOf(extraData) + receiverId.toString()).hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("classNameId");
        sb.append(StringPool.EQUAL);
        sb.append(classNameId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("classPK");
        sb.append(StringPool.EQUAL);
        sb.append(classPK);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("type");
        sb.append(StringPool.EQUAL);
        sb.append(type);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("extraData");
        sb.append(StringPool.EQUAL);
        sb.append(extraData);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("receiverId");
        sb.append(StringPool.EQUAL);
        sb.append(receiverId);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
