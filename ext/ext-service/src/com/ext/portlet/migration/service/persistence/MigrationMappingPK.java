/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.migration.service.persistence;

import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;


public class MigrationMappingPK implements Comparable<MigrationMappingPK>,
    Serializable {
    public String entityName;
    public String oldId;

    public MigrationMappingPK() {
    }

    public MigrationMappingPK(String entityName, String oldId) {
        this.entityName = entityName;
        this.oldId = oldId;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getOldId() {
        return oldId;
    }

    public void setOldId(String oldId) {
        this.oldId = oldId;
    }

    public int compareTo(MigrationMappingPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        value = entityName.compareTo(pk.entityName);

        if (value != 0) {
            return value;
        }

        value = oldId.compareTo(pk.oldId);

        if (value != 0) {
            return value;
        }

        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        MigrationMappingPK pk = null;

        try {
            pk = (MigrationMappingPK) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        if ((entityName.equals(pk.entityName)) && (oldId.equals(pk.oldId))) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (String.valueOf(entityName) + String.valueOf(oldId)).hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("entityName");
        sb.append(StringPool.EQUAL);
        sb.append(entityName);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("oldId");
        sb.append(StringPool.EQUAL);
        sb.append(oldId);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
