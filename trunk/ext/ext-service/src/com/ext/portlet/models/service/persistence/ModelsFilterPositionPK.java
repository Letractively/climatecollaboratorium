/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.service.persistence;

import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;


public class ModelsFilterPositionPK implements Comparable<ModelsFilterPositionPK>,
    Serializable {
    public Long userId;
    public Boolean published;
    public Long positionId;

    public ModelsFilterPositionPK() {
    }

    public ModelsFilterPositionPK(Long userId, Boolean published,
        Long positionId) {
        this.userId = userId;
        this.published = published;
        this.positionId = positionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public int compareTo(ModelsFilterPositionPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        value = userId.compareTo(pk.userId);

        if (value != 0) {
            return value;
        }

        value = published.compareTo(pk.published);

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

        ModelsFilterPositionPK pk = null;

        try {
            pk = (ModelsFilterPositionPK) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        if ((userId.equals(pk.userId)) && (published.equals(pk.published)) &&
                (positionId.equals(pk.positionId))) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (userId.toString() + published.toString() +
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
        sb.append("published");
        sb.append(StringPool.EQUAL);
        sb.append(published);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("positionId");
        sb.append(StringPool.EQUAL);
        sb.append(positionId);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
