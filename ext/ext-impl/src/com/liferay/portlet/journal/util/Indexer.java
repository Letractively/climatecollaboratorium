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

package com.liferay.portlet.journal.util;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.DocumentSummary;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.PortletPreferences;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeIndexerUtil;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;

import edu.emory.mathcs.backport.java.util.Arrays;

import java.util.Date;
import java.util.List;

import javax.portlet.PortletURL;

/**
 * <a href="Indexer.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 * @author Harry Mark
 * @author Bruno Farache
 * @author Raymond Augé
 *
 */
public class Indexer implements com.liferay.portal.kernel.search.Indexer {

	public static final String PORTLET_ID = PortletKeys.JOURNAL;

	public static void addArticle(
			long companyId, long groupId, String articleId, double version,
			String title, String description, String content, String type,
			Date displayDate, String[] tagsCategories, String[] tagsEntries,
			ExpandoBridge expandoBridge)
		throws SearchException {

		Document doc = getArticleDocument(
			companyId, groupId, articleId, version, title, description, content,
			type, displayDate, tagsCategories, tagsEntries, expandoBridge);

		SearchEngineUtil.addDocument(companyId, doc);
	}

	public static void deleteArticle(long companyId, String articleId)
		throws SearchException {

		SearchEngineUtil.deleteDocument(companyId, getArticleUID(articleId));
		SearchEngineUtil.deleteDocument(companyId, getArticleUID(articleId, true));
	}

	public static Document getArticleDocument(
		long companyId, long groupId, String articleId, double version,
		String title, String description, String content, String type,
		Date displayDate, String[] tagsCategories, String[] tagsEntries,
		ExpandoBridge expandoBridge) {

		if ((content != null) &&
			((content.indexOf("<dynamic-content") != -1) ||
			 (content.indexOf("<static-content") != -1))) {

			content = _getIndexableContent(content);

			content = StringUtil.replace(
				content, "<![CDATA[", StringPool.BLANK);
			content = StringUtil.replace(content, "]]>", StringPool.BLANK);
		}

		content = StringUtil.replace(content, "&amp;", "&");
		content = StringUtil.replace(content, "&lt;", "<");
		content = StringUtil.replace(content, "&gt;", ">");

		content = HtmlUtil.extractText(content);
		
		if (content.contains("assembl")) {
			System.out.println("Mam!" + title);
			System.out.println("stop");
			
		}

		Document doc = new DocumentImpl();

		// check if this is an most recent version of an article
		JournalArticle article;
		boolean isOld = false;
		try {
			article = JournalArticleLocalServiceUtil.getArticle(Long.parseLong(articleId));
			if (article.getVersion() != version) {
				isOld = true;
			}
		} catch (Exception e1) {
			// ignore
		} 
				
				
		if (isOld) {
			doc.addUID(PORTLET_ID, articleId, "old");
		}
		else {
			doc.addUID(PORTLET_ID, articleId);
		}

		doc.addModifiedDate(displayDate);

		doc.addKeyword(Field.COMPANY_ID, companyId);
		doc.addKeyword(Field.PORTLET_ID, PORTLET_ID);
		doc.addKeyword(Field.GROUP_ID, groupId);

		doc.addText(Field.TITLE, title);
		doc.addText(Field.CONTENT, content);
		doc.addText(Field.DESCRIPTION, description);
		doc.addKeyword(Field.TAGS_CATEGORIES, tagsCategories);
		doc.addKeyword(Field.TAGS_ENTRIES, tagsEntries);

		doc.addKeyword(Field.ENTRY_CLASS_PK, articleId);
		doc.addKeyword(Field.VERSION, version);
		doc.addKeyword(Field.TYPE, type);
		

		ExpandoBridgeIndexerUtil.addAttributes(doc, expandoBridge);
		
		try {
			Layout pageLayout = getLayoutForContent(articleId);
			
			if (pageLayout != null) {
				Layout tmp = pageLayout;
				boolean isAbout = false;
				while (tmp != null && !isAbout) {
					if (tmp.getFriendlyURL().toLowerCase().contains("about")) {
						isAbout = true;
					}
					if (tmp.getParentLayoutId() > 0) {
						try {
							tmp = LayoutLocalServiceUtil.getLayout(tmp.getGroupId(), tmp.getPrivateLayout(), tmp.getParentLayoutId());
						}
						catch (com.liferay.portal.NoSuchLayoutException e) {
							tmp = null;
						} catch (PortalException e) {
							tmp = null;
						}
					}
					else {
						tmp = null;
					}
				}
				
				if (isAbout) {
					doc.addKeyword("PAGE_URL", pageLayout.getFriendlyURL());
					doc.addKeyword(Field.ENTRY_CLASS_NAME, JournalArticle.class.getName() + ".index");
				}
			}
			else {
				doc.addKeyword(Field.ENTRY_CLASS_NAME, JournalArticle.class.getName());
			}
		} catch (SystemException e) {
			doc.addKeyword(Field.ENTRY_CLASS_NAME, JournalArticle.class.getName());
			e.printStackTrace();
		}
		System.out.println(" uid na koniec: " + doc.get(Field.UID));
		return doc;
	}

	public static String getArticleUID(String articleId) {
		return getArticleUID(articleId, false);
	}
	
	public static String getArticleUID(String articleId, boolean isOld) {
		Document doc = new DocumentImpl();

		if (isOld) {
			doc.addUID(PORTLET_ID, articleId, "old");
		}
		else {
			doc.addUID(PORTLET_ID, articleId);
		}

		return doc.get(Field.UID);
	}

	public static void updateArticle(
			long companyId, long groupId, String articleId, double version,
			String title, String description, String content, String type,
			Date displayDate, String[] tagsCategories, String[] tagsEntries,
			ExpandoBridge expandoBridge)
		throws SearchException {

		Document doc = getArticleDocument(
			companyId, groupId, articleId, version, title, description, content,
			type, displayDate, tagsCategories, tagsEntries, expandoBridge);

		SearchEngineUtil.updateDocument(companyId, doc.get(Field.UID), doc);
	}

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	public DocumentSummary getDocumentSummary(
		com.liferay.portal.kernel.search.Document doc, PortletURL portletURL) {

		// Title

		String title = doc.get(Field.TITLE);

		// Content

		String content = doc.get(Field.CONTENT);

		content = StringUtil.shorten(content, 200);

		// Portlet URL

		String groupId = doc.get("groupId");
		String articleId = doc.get(Field.ENTRY_CLASS_PK);
		String version = doc.get("version");

		portletURL.setParameter("struts_action", "/journal/edit_article");
		portletURL.setParameter("groupId", groupId);
		portletURL.setParameter("articleId", articleId);
		portletURL.setParameter("version", version);

		return new DocumentSummary(title, content, portletURL);
	}

	public void reIndex(String className, long classPK) throws SearchException {
		try {
			JournalArticleLocalServiceUtil.reIndex(classPK);
		}
		catch (Exception e) {
			throw new SearchException(e);
		}
	}

	public void reIndex(String[] ids) throws SearchException {
		try {
			JournalArticleLocalServiceUtil.reIndex(ids);
		}
		catch (Exception e) {
			throw new SearchException(e);
		}
	}

	private static String _getIndexableContent(String content) {
		try {
			StringBuilder sb = new StringBuilder();

			com.liferay.portal.kernel.xml.Document doc = SAXReaderUtil.read(
				content);

			Element root = doc.getRootElement();

			_getIndexableContent(sb, root);

			return sb.toString();
		}
		catch (Exception e) {
			e.printStackTrace();

			return content;
		}
	}

	private static void _getIndexableContent(StringBuilder sb, Element root)
		throws Exception {

		for (Element el : root.elements()) {
			String elType = el.attributeValue("type", StringPool.BLANK);

			if (elType.equals("text") || elType.equals("text_box") ||
				elType.equals("text_area")) {

				for (Element dynamicContent : el.elements("dynamic-content")) {
					String text = dynamicContent.getText();

					sb.append(text);
					sb.append(StringPool.SPACE);
				}
			}
			else if (el.getName().equals("static-content")) {
				String text = el.getText();

				sb.append(text);
				sb.append(StringPool.SPACE);
			}

			_getIndexableContent(sb, el);
		}
	}

	private static final String[] _CLASS_NAMES = new String[] {
		JournalArticle.class.getName()
	};
	
	private static Layout getLayoutForContent(String articleId) throws SystemException {
		DynamicQuery layoutQuery = DynamicQueryFactoryUtil.forClass(Layout.class, PortalClassLoaderUtil.getClassLoader());
		
		DynamicQuery preferencesQuery = DynamicQueryFactoryUtil.forClass(PortletPreferences.class, PortalClassLoaderUtil.getClassLoader());
		
		preferencesQuery.add(RestrictionsFactoryUtil.like("portletId", "56_INSTANCE%"));  
		preferencesQuery.add(RestrictionsFactoryUtil.like("preferences", "%<name>article-id</name><value>" + articleId + "</value>%"));  
		preferencesQuery.setProjection(ProjectionFactoryUtil.property("plid"));  
		
		layoutQuery.add(PropertyFactoryUtil.forName("plid").in(preferencesQuery));  
		
		//List result = PortletPreferencesLocalServiceUtil.dynamicQuery(preferencesQuery);
		
		List result = LayoutLocalServiceUtil.dynamicQuery(layoutQuery);
		
		if (result.size() > 0) {
			Layout layout = (Layout) result.get(0);
			return layout;
		}
		return null;
	}

}