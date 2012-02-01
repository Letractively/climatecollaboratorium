package com.ext.portlet.debaterevision.service.persistence;

public class DebateFinderUtil {
    private static DebateFinder _finder;

    public static com.ext.portlet.debaterevision.model.Debate getMostRecentVersion(
        long debateId) {
        return getFinder().getMostRecentVersion(debateId);
    }

    public static int getNumberOfComments(long debateId) {
        return getFinder().getNumberOfComments(debateId);
    }

    public static com.ext.portlet.debaterevision.model.DebateComment getMostRecentComment(
        long debateId) {
        return getFinder().getMostRecentComment(debateId);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.Debate> getAllRecentActiveDebates() {
        return getFinder().getAllRecentActiveDebates();
    }

    public static DebateFinder getFinder() {
        return _finder;
    }

    public void setFinder(DebateFinder finder) {
        _finder = finder;
    }
}
