package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;


public class PlanVotePK implements Comparable<PlanVotePK>, Serializable {
    public Long userId;
    public Long contestId;

    public PlanVotePK() {
    }

    public PlanVotePK(Long userId, Long contestId) {
        this.userId = userId;
        this.contestId = contestId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public int compareTo(PlanVotePK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        value = userId.compareTo(pk.userId);

        if (value != 0) {
            return value;
        }

        value = contestId.compareTo(pk.contestId);

        if (value != 0) {
            return value;
        }

        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlanVotePK pk = null;

        try {
            pk = (PlanVotePK) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        if ((userId.equals(pk.userId)) && (contestId.equals(pk.contestId))) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (userId.toString() + contestId.toString()).hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("userId");
        sb.append(StringPool.EQUAL);
        sb.append(userId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("contestId");
        sb.append(StringPool.EQUAL);
        sb.append(contestId);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
