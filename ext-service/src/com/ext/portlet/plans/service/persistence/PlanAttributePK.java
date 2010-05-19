/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;


public class PlanAttributePK implements Comparable<PlanAttributePK>,
    Serializable {
    public Long planId;
    public String attributeName;

    public PlanAttributePK() {
    }

    public PlanAttributePK(Long planId, String attributeName) {
        this.planId = planId;
        this.attributeName = attributeName;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public int compareTo(PlanAttributePK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        value = planId.compareTo(pk.planId);

        if (value != 0) {
            return value;
        }

        value = attributeName.compareTo(pk.attributeName);

        if (value != 0) {
            return value;
        }

        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlanAttributePK pk = null;

        try {
            pk = (PlanAttributePK) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        if ((planId.equals(pk.planId)) &&
                (attributeName.equals(pk.attributeName))) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (planId.toString() + String.valueOf(attributeName)).hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("planId");
        sb.append(StringPool.EQUAL);
        sb.append(planId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("attributeName");
        sb.append(StringPool.EQUAL);
        sb.append(attributeName);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
