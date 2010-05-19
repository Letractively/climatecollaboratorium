package com.ext.portlet.debaterevision.service.persistence;

public class DebateCategoryFinderUtil {
    private static DebateCategoryFinder _finder;

    public static java.util.List<com.ext.portlet.debaterevision.model.Debate> getCategoryDebates(
        long categoryId) {
        return getFinder().getCategoryDebates(categoryId);
    }

    public static DebateCategoryFinder getFinder() {
        return _finder;
    }

    public void setFinder(DebateCategoryFinder finder) {
        _finder = finder;
    }
}
