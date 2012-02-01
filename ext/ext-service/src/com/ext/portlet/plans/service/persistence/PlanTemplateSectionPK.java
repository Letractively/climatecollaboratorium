package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;


public class PlanTemplateSectionPK implements Comparable<PlanTemplateSectionPK>,
    Serializable {
    public Long id;
    public Long planSectionId;

    public PlanTemplateSectionPK() {
    }

    public PlanTemplateSectionPK(Long id, Long planSectionId) {
        this.id = id;
        this.planSectionId = planSectionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlanSectionId() {
        return planSectionId;
    }

    public void setPlanSectionId(Long planSectionId) {
        this.planSectionId = planSectionId;
    }

    public int compareTo(PlanTemplateSectionPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        value = id.compareTo(pk.id);

        if (value != 0) {
            return value;
        }

        value = planSectionId.compareTo(pk.planSectionId);

        if (value != 0) {
            return value;
        }

        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlanTemplateSectionPK pk = null;

        try {
            pk = (PlanTemplateSectionPK) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        if ((id.equals(pk.id)) && (planSectionId.equals(pk.planSectionId))) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (id.toString() + planSectionId.toString()).hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("id");
        sb.append(StringPool.EQUAL);
        sb.append(id);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("planSectionId");
        sb.append(StringPool.EQUAL);
        sb.append(planSectionId);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
