package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;


public class PlansFilterPositionPK implements Comparable<PlansFilterPositionPK>,
    Serializable {
    public Long userId;
    public Long planTypeId;
    public Long positionId;

    public PlansFilterPositionPK() {
    }

    public PlansFilterPositionPK(Long userId, Long planTypeId, Long positionId) {
        this.userId = userId;
        this.planTypeId = planTypeId;
        this.positionId = positionId;
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

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public int compareTo(PlansFilterPositionPK pk) {
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

        value = positionId.compareTo(pk.positionId);

        if (value != 0) {
            return value;
        }

        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlansFilterPositionPK pk = null;

        try {
            pk = (PlansFilterPositionPK) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        if ((userId.equals(pk.userId)) && (planTypeId.equals(pk.planTypeId)) &&
                (positionId.equals(pk.positionId))) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (userId.toString() + planTypeId.toString() +
        positionId.toString()).hashCode();
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

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("positionId");
        sb.append(StringPool.EQUAL);
        sb.append(positionId);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
