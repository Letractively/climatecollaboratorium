package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;


public class PlanRelatedPK implements Comparable<PlanRelatedPK>, Serializable {
    public Long sectionId;
    public Long relatedPlanId;

    public PlanRelatedPK() {
    }

    public PlanRelatedPK(Long sectionId, Long relatedPlanId) {
        this.sectionId = sectionId;
        this.relatedPlanId = relatedPlanId;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public Long getRelatedPlanId() {
        return relatedPlanId;
    }

    public void setRelatedPlanId(Long relatedPlanId) {
        this.relatedPlanId = relatedPlanId;
    }

    public int compareTo(PlanRelatedPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        value = sectionId.compareTo(pk.sectionId);

        if (value != 0) {
            return value;
        }

        value = relatedPlanId.compareTo(pk.relatedPlanId);

        if (value != 0) {
            return value;
        }

        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlanRelatedPK pk = null;

        try {
            pk = (PlanRelatedPK) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        if ((sectionId.equals(pk.sectionId)) &&
                (relatedPlanId.equals(pk.relatedPlanId))) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (sectionId.toString() + relatedPlanId.toString()).hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("sectionId");
        sb.append(StringPool.EQUAL);
        sb.append(sectionId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("relatedPlanId");
        sb.append(StringPool.EQUAL);
        sb.append(relatedPlanId);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
