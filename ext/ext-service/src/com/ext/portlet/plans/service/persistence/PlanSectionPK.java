package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;


public class PlanSectionPK implements Comparable<PlanSectionPK>, Serializable {
    public Long planSectionDefinitionId;
    public Long planId;

    public PlanSectionPK() {
    }

    public PlanSectionPK(Long planSectionDefinitionId, Long planId) {
        this.planSectionDefinitionId = planSectionDefinitionId;
        this.planId = planId;
    }

    public Long getPlanSectionDefinitionId() {
        return planSectionDefinitionId;
    }

    public void setPlanSectionDefinitionId(Long planSectionDefinitionId) {
        this.planSectionDefinitionId = planSectionDefinitionId;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public int compareTo(PlanSectionPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        value = planSectionDefinitionId.compareTo(pk.planSectionDefinitionId);

        if (value != 0) {
            return value;
        }

        value = planId.compareTo(pk.planId);

        if (value != 0) {
            return value;
        }

        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlanSectionPK pk = null;

        try {
            pk = (PlanSectionPK) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        if ((planSectionDefinitionId.equals(pk.planSectionDefinitionId)) &&
                (planId.equals(pk.planId))) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (planSectionDefinitionId.toString() + planId.toString()).hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("planSectionDefinitionId");
        sb.append(StringPool.EQUAL);
        sb.append(planSectionDefinitionId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("planId");
        sb.append(StringPool.EQUAL);
        sb.append(planId);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
