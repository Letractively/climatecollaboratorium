package org.climatecollaboratorium.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;

public class SearchResultItem {
    private SearchItemType itemType = SearchItemType.UNKNOWN;
    private Map<String, Field> fields;
    private Document doc;
    
    public class Pair {
        private String key;
        private String value;
        
        public Pair(String key, String value) {
            this.key = key;
            this.value = value;
        }
        public String getKey() {
            return key;
        }
        public void setKey(String key) {
            this.key = key;
        }
        public String getValue() {
            return value;
        }
        public void setValue(String value) {
            this.value = value;
        }
    }
    
    public SearchResultItem(Document doc) {
        this.doc = doc;
        fields = doc.getFields();
        
        if (fields.containsKey("screenName")) {
            itemType = SearchItemType.USER;
        }
        else if (fields.containsKey("entryClassName")) {
            if (fields.get("entryClassName").getValue().equals("com.liferay.portlet.wiki.model.WikiPage")) {
                itemType = SearchItemType.WIKI;
            } 
            else if (fields.get("entryClassName").getValue().equals("com.liferay.portlet.journal.model.JournalArticle")) {
                itemType = SearchItemType.JOURNAL_ARTICLE;
            }
        }
    }
    
    public List<Pair> getValues() {
        List<Pair> pairs = new ArrayList<Pair>();
        for (String fieldName: fields.keySet()) {
            pairs.add(new Pair(fieldName, fields.get(fieldName).getValue()));
        }
        
        return pairs;
    }
    
    public SearchItemType getItemType() {
        return itemType;
    }
    
    

}
