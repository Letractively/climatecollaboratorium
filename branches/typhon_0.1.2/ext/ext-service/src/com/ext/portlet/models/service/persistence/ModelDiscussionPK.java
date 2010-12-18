/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.service.persistence;

import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;


public class ModelDiscussionPK implements Comparable<ModelDiscussionPK>,
    Serializable {
    public Long modelId;
    public Long categoryId;

    public ModelDiscussionPK() {
    }

    public ModelDiscussionPK(Long modelId, Long categoryId) {
        this.modelId = modelId;
        this.categoryId = categoryId;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public int compareTo(ModelDiscussionPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        value = modelId.compareTo(pk.modelId);

        if (value != 0) {
            return value;
        }

        value = categoryId.compareTo(pk.categoryId);

        if (value != 0) {
            return value;
        }

        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        ModelDiscussionPK pk = null;

        try {
            pk = (ModelDiscussionPK) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        if ((modelId.equals(pk.modelId)) && (categoryId.equals(pk.categoryId))) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (modelId.toString() + categoryId.toString()).hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("modelId");
        sb.append(StringPool.EQUAL);
        sb.append(modelId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("categoryId");
        sb.append(StringPool.EQUAL);
        sb.append(categoryId);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
