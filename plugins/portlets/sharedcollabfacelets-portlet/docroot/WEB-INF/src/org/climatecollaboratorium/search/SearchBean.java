package org.climatecollaboratorium.search;

import java.util.ArrayList;
import java.util.List;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.StringQueryImpl;

public class SearchBean {
    private String searchPhrase = "companyId:10112";
    private final static int RESULTS_PER_PAGE = 20;
    
    public int getLength() throws SearchException {
        Hits hits = SearchEngineUtil.search(10112L, new StringQueryImpl(searchPhrase), 0, RESULTS_PER_PAGE);
        //hits.getDocs().
        return hits.getLength();
    }
    
    public List<SearchResultItem> getSearchResultItems() throws SearchException {
        Hits hits = SearchEngineUtil.search(10112L, new StringQueryImpl("companyId:10112"), 0, RESULTS_PER_PAGE);
        
        List<SearchResultItem> sri = new ArrayList<SearchResultItem>();
        for (Document doc: hits.getDocs()) {
            sri.add(new SearchResultItem(doc));
        }
        return sri;
    }

    public void setSearchPhrase(String searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    public String getSearchPhrase() {
        return searchPhrase;
    }

}
