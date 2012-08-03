package org.climatecollaboratorium.contests;

import javax.portlet.PortletURL;

import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.service.ContestLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.DocumentSummary;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.SearchException;

public class Indexer implements com.liferay.portal.kernel.search.Indexer {
	public static final String PORTLET_ID = "contests";
    private final long defaultCompanyId = 10112;
	
    private static Log _log = LogFactoryUtil.getLog(Indexer.class);
    
	public static void updateEntry(long companyId, Contest contest) throws SearchException, SystemException {

		Document doc = getEntryDocument(contest);

		SearchEngineUtil.updateDocument(companyId, doc.get(Field.UID), doc);
	}
	
	public static Document getEntryDocument(Contest c) {
		Document doc = new DocumentImpl();

		//doc.addUID(PORTLET_ID, c.getContestPK());

		doc.addModifiedDate(c.getUpdated());

		doc.addKeyword(Field.PORTLET_ID, PORTLET_ID);
		doc.addKeyword(Field.GROUP_ID, c.getGroupId());

		doc.addText(Field.TITLE, c.getContestShortName());
        doc.addText(Field.CONTENT, c.getContestName() + "\n" + c.getContestDescription());

		doc.addKeyword(Field.ENTRY_CLASS_NAME, Contest.class.getName());
		doc.addKeyword(Field.ENTRY_CLASS_PK, c.getContestPK());
		
		return doc;
	}
	
	
	private static final String[] _CLASS_NAMES = new String[] {
		Contest.class.getName()
	};
	@Override
	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	public DocumentSummary getDocumentSummary(Document doc,
			PortletURL portletURL) {
	    String title = doc.get(Field.TITLE);
		String url = doc.get(Field.URL);

		return new DocumentSummary(title, url, portletURL);
	}

	@Override
	public void reIndex(String className, long classPK) throws SearchException {

		try {
			updateEntry(defaultCompanyId, ContestLocalServiceUtil.getContest(classPK));
		} catch (SystemException e) {
			_log.error("Exception thrown when trying to reindex contest with id: " + classPK);
		} catch (PortalException e) {
			_log.error("Exception thrown when trying to reindex contest with id: " + classPK);
		}
	}

	@Override
	public void reIndex(String[] ids) throws SearchException {
		// reindex everything
		try {
			for (Contest c: ContestLocalServiceUtil.getContests(0, 1000)) {
				updateEntry(defaultCompanyId, c);
			}
		} catch (SystemException e) {
			_log.error("Exception thrown when trying to reindex contests");
		}
		
	}

}
