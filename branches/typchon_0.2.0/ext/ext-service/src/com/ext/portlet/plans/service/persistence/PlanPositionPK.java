package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;


public class PlanPositionPK implements Comparable<PlanPositionPK>, Serializable {
    public Long planId;
    public Long positionId;

    public PlanPositionPK() {
    }

    public PlanPositionPK(Long planId, Long positionId) {
        this.planId = planId;
        this.positionId = positionId;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public int compareTo(PlanPositionPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        value = planId.compareTo(pk.planId);

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

        PlanPositionPK pk = null;

        try {
            pk = (PlanPositionPK) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        if ((planId.equals(pk.planId)) && (positionId.equals(pk.positionId))) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (planId.toString() + positionId.toString()).hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("planId");
        sb.append(StringPool.EQUAL);
        sb.append(planId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("positionId");
        sb.append(StringPool.EQUAL);
        sb.append(positionId);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
