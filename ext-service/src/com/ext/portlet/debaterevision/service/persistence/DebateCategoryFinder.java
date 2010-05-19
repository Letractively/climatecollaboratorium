package com.ext.portlet.debaterevision.service.persistence;

public interface DebateCategoryFinder {
    public java.util.List<com.ext.portlet.debaterevision.model.Debate> getCategoryDebates(
        long categoryId);
}
