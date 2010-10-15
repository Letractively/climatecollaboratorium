package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;


public class PlanPositionItemPK implements Comparable<PlanPositionItemPK>,
    Serializable {
    public Long planPositionsId;
    public Long positionId;

    public PlanPositionItemPK() {
    }

    public PlanPositionItemPK(Long planPositionsId, Long positionId) {
        this.planPositionsId = planPositionsId;
        this.positionId = positionId;
    }

    public Long getPlanPositionsId() {
        return planPositionsId;
    }

    public void setPlanPositionsId(Long planPositionsId) {
        this.planPositionsId = planPositionsId;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public int compareTo(PlanPositionItemPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        value = planPositionsId.compareTo(pk.planPositionsId);

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

        PlanPositionItemPK pk = null;

        try {
            pk = (PlanPositionItemPK) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        if ((planPositionsId.equals(pk.planPositionsId)) &&
                (positionId.equals(pk.positionId))) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (planPositionsId.toString() + positionId.toString()).hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("planPositionsId");
        sb.append(StringPool.EQUAL);
        sb.append(planPositionsId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("positionId");
        sb.append(StringPool.EQUAL);
        sb.append(positionId);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
