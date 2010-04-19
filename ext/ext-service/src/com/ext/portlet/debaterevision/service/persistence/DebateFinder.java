package com.ext.portlet.debaterevision.service.persistence;

public interface DebateFinder {
    public com.ext.portlet.debaterevision.model.Debate getMostRecentVersion(
        long debateId);

    public int getNumberOfComments(long debateId);

    public com.ext.portlet.debaterevision.model.DebateComment getMostRecentComment(
        long debateId);

    public java.util.List<com.ext.portlet.debaterevision.model.Debate> getAllRecentActiveDebates();
}
