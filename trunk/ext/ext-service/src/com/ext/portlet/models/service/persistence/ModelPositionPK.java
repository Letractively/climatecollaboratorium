/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.service.persistence;

import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;


public class ModelPositionPK implements Comparable<ModelPositionPK>,
    Serializable {
    public Long modelId;
    public Long positionId;

    public ModelPositionPK() {
    }

    public ModelPositionPK(Long modelId, Long positionId) {
        this.modelId = modelId;
        this.positionId = positionId;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public int compareTo(ModelPositionPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        value = modelId.compareTo(pk.modelId);

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

        ModelPositionPK pk = null;

        try {
            pk = (ModelPositionPK) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        if ((modelId.equals(pk.modelId)) && (positionId.equals(pk.positionId))) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (modelId.toString() + positionId.toString()).hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("modelId");
        sb.append(StringPool.EQUAL);
        sb.append(modelId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("positionId");
        sb.append(StringPool.EQUAL);
        sb.append(positionId);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
