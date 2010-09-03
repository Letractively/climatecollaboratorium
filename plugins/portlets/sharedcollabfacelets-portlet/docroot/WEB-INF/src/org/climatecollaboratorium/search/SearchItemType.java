package org.climatecollaboratorium.search;

public enum SearchItemType {
    UNKNOWN(null, null),
    JOURNAL_ARTICLE("entryClassName", "com.liferay.portlet.journal.model.JournalArticle"),
    BLOGS_ENTRY("entryClassName", "com.liferay.portlet.wiki.model.WikiPage"),
    USER("active", "true"),
    WIKI("entryClassName", "com.liferay.portlet.wiki.model.WikiPage");
    
    private final String fieldName;
    private final String fieldValue;
    SearchItemType(String fieldName, String fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
