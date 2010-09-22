package com.ext.portlet.debaterevision.util;

/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import javax.portlet.PortletURL;

import com.ext.portlet.debaterevision.model.DebateComment;
import com.ext.portlet.debaterevision.model.DebateItem;
import com.ext.portlet.debaterevision.service.DebateItemLocalServiceUtil;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.DocumentSummary;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.SearchException;

/**
 * <a href="Indexer.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 * @author Bruno Farache
 * @author Raymond Augé
 *
 */
public class Indexer implements com.liferay.portal.kernel.search.Indexer {

	public static final String PORTLET_ID = "plans";

	public static void addEntry(long companyId, DebateItem debateItem) throws SearchException, SystemException {

		Document doc = getEntryDocument(companyId, debateItem);

		SearchEngineUtil.addDocument(companyId, doc);
	}

	public static void deleteEntry(long companyId, long entryId)
		throws SearchException {

		SearchEngineUtil.deleteDocument(companyId, getEntryUID(entryId));
	}

	public static Document getEntryDocument(long companyId, DebateItem debateItem) throws SystemException {

		Document doc = new DocumentImpl();

		doc.addUID(PORTLET_ID, debateItem.getDebateItemId());

		doc.addModifiedDate(debateItem.getUpdated());

		doc.addKeyword(Field.COMPANY_ID, companyId);
		doc.addKeyword(Field.PORTLET_ID, PORTLET_ID);
        doc.addKeyword("debateId", debateItem.getDebateId());
        doc.addKeyword("itemType", debateItem.getDebatePostType());

		doc.addText(Field.TITLE, debateItem.getDebateSummary());
		
		StringBuilder content = new StringBuilder();
		content.append(debateItem.getDebateDetail());
		for (DebateComment comment: debateItem.getComments()) {
		    content.append("\n");
		    content.append(comment.getDebateCommentTitle());
            content.append("\n");
            content.append(comment.getDebateCommentDetail());
		}
        doc.addText(Field.CONTENT, content.toString());

		doc.addKeyword(Field.ENTRY_CLASS_NAME, DebateItem.class.getName());
		doc.addKeyword(Field.ENTRY_CLASS_PK, debateItem.getDebateItemId());
		//doc.addText(Field.URL, Plan);
		return doc;
	}

	public static String getEntryUID(long entryId) {
		Document doc = new DocumentImpl();

		doc.addUID(PORTLET_ID, entryId);

		return doc.get(Field.UID);
	}

	public static void updateEntry(long companyId, DebateItem debateItem) throws SearchException, SystemException {

		Document doc = getEntryDocument(companyId, debateItem);

		SearchEngineUtil.updateDocument(companyId, doc.get(Field.UID), doc);
	}

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	public DocumentSummary getDocumentSummary(
		com.liferay.portal.kernel.search.Document doc, PortletURL portletURL) {

	    String title = doc.get(Field.TITLE);
		String url = doc.get(Field.URL);

		return new DocumentSummary(title, url, portletURL);
	}

	public void reIndex(String className, long classPK) throws SearchException {
		try {
		    DebateItemLocalServiceUtil.reIndex(classPK);
		}
		catch (Exception e) {
			throw new SearchException(e);
		}
	}

	public void reIndex(String[] ids) throws SearchException {
		try {
		    // read all contests and for them read all debates, index only issues present in those debates
			DebateItemLocalServiceUtil.reIndex();
		}
		catch (Exception e) {
			throw new SearchException(e);
		}
	}

	private static final String[] _CLASS_NAMES = new String[] {
		DebateItem.class.getName()
	};

}