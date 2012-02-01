package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;


public class PlansFilterPK implements Comparable<PlansFilterPK>, Serializable {
    public Long userId;
    public Long planTypeId;

    public PlansFilterPK() {
    }

    public PlansFilterPK(Long userId, Long planTypeId) {
        this.userId = userId;
        this.planTypeId = planTypeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPlanTypeId() {
        return planTypeId;
    }

    public void setPlanTypeId(Long planTypeId) {
        this.planTypeId = planTypeId;
    }

    public int compareTo(PlansFilterPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        value = userId.compareTo(pk.userId);

        if (value != 0) {
            return value;
        }

        value = planTypeId.compareTo(pk.planTypeId);

        if (value != 0) {
            return value;
        }

        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlansFilterPK pk = null;

        try {
            pk = (PlansFilterPK) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        if ((userId.equals(pk.userId)) && (planTypeId.equals(pk.planTypeId))) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (userId.toString() + planTypeId.toString()).hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("userId");
        sb.append(StringPool.EQUAL);
        sb.append(userId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("planTypeId");
        sb.append(StringPool.EQUAL);
        sb.append(planTypeId);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
