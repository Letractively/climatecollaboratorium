package com.ext.portlet.ontology.service.persistence;

import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;


public class CategoryOntologyTermPK implements Comparable<CategoryOntologyTermPK>,
    Serializable {
    public Long categoryId;
    public Long ontologyTerm;

    public CategoryOntologyTermPK() {
    }

    public CategoryOntologyTermPK(Long categoryId, Long ontologyTerm) {
        this.categoryId = categoryId;
        this.ontologyTerm = ontologyTerm;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getOntologyTerm() {
        return ontologyTerm;
    }

    public void setOntologyTerm(Long ontologyTerm) {
        this.ontologyTerm = ontologyTerm;
    }

    public int compareTo(CategoryOntologyTermPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        value = categoryId.compareTo(pk.categoryId);

        if (value != 0) {
            return value;
        }

        value = ontologyTerm.compareTo(pk.ontologyTerm);

        if (value != 0) {
            return value;
        }

        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        CategoryOntologyTermPK pk = null;

        try {
            pk = (CategoryOntologyTermPK) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        if ((categoryId.equals(pk.categoryId)) &&
                (ontologyTerm.equals(pk.ontologyTerm))) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (categoryId.toString() + ontologyTerm.toString()).hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("categoryId");
        sb.append(StringPool.EQUAL);
        sb.append(categoryId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("ontologyTerm");
        sb.append(StringPool.EQUAL);
        sb.append(ontologyTerm);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
