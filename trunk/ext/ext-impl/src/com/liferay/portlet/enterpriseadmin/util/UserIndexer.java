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

package com.liferay.portlet.enterpriseadmin.util;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.DocumentSummary;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.InitialThreadLocal;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.ContactConstants;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeIndexerUtil;
import com.liferay.portlet.tags.service.TagsEntryLocalServiceUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletURL;

/**
 * This class has comes from liferay portal core and has been modified to include join date in search results.
 *
 * @author Raymond Augé
 *
 */
public class UserIndexer implements Indexer {

	public static final String PORTLET_ID = PortletKeys.ENTERPRISE_ADMIN_USERS;

	public static void deleteUser(long companyId, long userId)
		throws SearchException {

		SearchEngineUtil.deleteDocument(companyId, getUserUID(userId));
	}

	public static Document getUserDocument(
		long companyId, long userId, String screenName, String emailAddress,
		String firstName, String middleName, String lastName, String jobTitle,
		boolean active, long[] groupIds, long[] organizationIds,
		long[] roleIds, long[] userGroupIds, String[] tagsEntries, Date joinDate,
		String[] memberCategories, String realName, ExpandoBridge expandoBridge) {

		Document doc = new DocumentImpl();

		doc.addUID(PORTLET_ID, String.valueOf(userId));

		doc.addModifiedDate();

		doc.addKeyword(Field.COMPANY_ID, companyId);
		doc.addKeyword(Field.PORTLET_ID, PORTLET_ID);
		doc.addKeyword(Field.USER_ID, userId);

		doc.addKeyword("screenName", screenName);
		doc.addKeyword("emailAddress", emailAddress);
		doc.addKeyword("firstName", firstName, true);
		doc.addKeyword("middleName", middleName, true);
		doc.addKeyword("lastName", lastName, true);
		doc.addKeyword("jobTitle", jobTitle);
		doc.addKeyword("active", active);
		doc.addKeyword("groupIds", groupIds);
		doc.addKeyword("organizationIds", organizationIds);
		doc.addKeyword(
			"ancestorOrganizationIds",
			_getAncestorOrganizationIds(userId, organizationIds));
		doc.addKeyword("roleIds", roleIds);
		doc.addKeyword("userGroupIds", userGroupIds);
		doc.addDate("joinDate", joinDate);
		doc.addKeyword("memberCategory", memberCategories);
		
		doc.addKeyword("realName", realName);

		doc.addKeyword(Field.TAGS_ENTRIES, tagsEntries);

		ExpandoBridgeIndexerUtil.addAttributes(doc, expandoBridge);

		return doc;
	}

	public static String getUserUID(long userId) {
		Document doc = new DocumentImpl();

		doc.addUID(PORTLET_ID, String.valueOf(userId));

		return doc.get(Field.UID);
	}

	public static void setEnabled(boolean enabled) {
		_enabled.set(enabled);
	}

	public static void updateUser(User user) throws SearchException {
		if (!_enabled.get()) {
			return;
		}

		try {
			if (user.isDefaultUser()) {
				return;
			}

			Contact contact = user.getContact();

			String[] tagsEntries = TagsEntryLocalServiceUtil.getEntryNames(
				User.class.getName(), user.getUserId());

			Document doc = getUserDocument(
				user.getCompanyId(), user.getUserId(), user.getScreenName(),
				user.getEmailAddress(), contact.getFirstName(),
				contact.getMiddleName(), contact.getLastName(),
				contact.getJobTitle(), user.getActive(),
				user.getGroupIds(), user.getOrganizationIds(),
				user.getRoleIds(), user.getUserGroupIds(), tagsEntries, 
				user.getCreateDate(),
				getUserCategories(user),
				(user.getFullName().trim().equals("") ? user.getScreenName() : user.getFullName()).trim(),
				
				user.getExpandoBridge());

			SearchEngineUtil.updateDocument(
				user.getCompanyId(), doc.get(Field.UID), doc);
		}
		catch (Exception e) {
			throw new SearchException(e);
		}
	}

	public static void updateUsers(long[] userIds) throws SearchException {
		for (long userId : userIds) {
			try {
				User user = UserLocalServiceUtil.getUserById(userId);

				updateUser(user);
			}
			catch (Exception e) {
				throw new SearchException(e);
			}
		}
	}

	public static void updateUsers(List<User> users) throws SearchException {
		for (User user : users) {
			updateUser(user);
		}
	}

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	public DocumentSummary getDocumentSummary(
		com.liferay.portal.kernel.search.Document doc, PortletURL portletURL) {

		// Title

		String firstName = doc.get("firstName");
		String middleName = doc.get("middleName");
		String lastName = doc.get("lastName");

		String title = ContactConstants.getFullName(
			firstName, middleName, lastName);

		// Content

		String content = null;

		// Portlet URL

		String userId = doc.get(Field.USER_ID);

		portletURL.setParameter("struts_action", "/enterprise_admin/edit_user");
		portletURL.setParameter("p_u_i_d", userId);

		return new DocumentSummary(title, content, portletURL);
	}

	public void reIndex(String className, long classPK) throws SearchException {
		try {
			UserLocalServiceUtil.reIndex(classPK);
		}
		catch (Exception e) {
			throw new SearchException(e);
		}
	}

	public void reIndex(String[] ids) throws SearchException {
		try {
			UserLocalServiceUtil.reIndex(ids);
		}
		catch (Exception e) {
			throw new SearchException(e);
		}
	}

	private static long[] _getAncestorOrganizationIds(
		long userId, long[] organizationIds) {

		List<Organization> ancestorOrganizations =
			new ArrayList<Organization>();

		for (long organizationId : organizationIds) {
			try {
				Organization organization =
					OrganizationLocalServiceUtil.getOrganization(
						organizationId);

				ancestorOrganizations.addAll(organization.getAncestors());
			}
			catch (Exception e) {
				_log.error("Error while indexing user " + userId, e);
			}
		}

		long[] ancestorOrganizationIds = new long[ancestorOrganizations.size()];

		for (int i = 0; i < ancestorOrganizations.size(); i++) {
			Organization ancestorOrganization = ancestorOrganizations.get(i);

			ancestorOrganizationIds[i] =
				ancestorOrganization.getOrganizationId();
		}

		return ancestorOrganizationIds;
	}

	private static final String[] _CLASS_NAMES = new String[] {
		User.class.getName()
	};

	private static Log _log = LogFactoryUtil.getLog(UserIndexer.class);

	private static ThreadLocal<Boolean> _enabled =
		new InitialThreadLocal<Boolean>(Boolean.TRUE);
	
    private static Map<Long, MemberCategory> roleIdToCategoryMap;
    private final static long DEFAULT_COMPANY_ID = 10112L;
	
	private static String[] getUserCategories(User user) {
	    initRoleIdToCategoryMap();
	    List<String> categories = new ArrayList<String>();
	    
	    for (Long roleId: user.getRoleIds()) {
	        if (roleIdToCategoryMap.containsKey(roleId)) {
	            categories.add(roleIdToCategoryMap.get(roleId).name());
	        }
	    }
	    String[] ret = new String[categories.size()];
	    return categories.toArray(ret);
	}
	
	private static synchronized void initRoleIdToCategoryMap() {
        if (roleIdToCategoryMap == null) {
            roleIdToCategoryMap = new HashMap<Long, MemberCategory>();

            for (MemberCategory category : MemberCategory.values()) {
                try {
                    Role role = RoleLocalServiceUtil.getRole(DEFAULT_COMPANY_ID, category.getRoleName());
                    roleIdToCategoryMap.put(role.getRoleId(), category);
                } catch (PortalException e) {
                    _log.error("Can't find role for user category " + category, e);
                } catch (SystemException e) {
                    _log.error("Can't find role for user category " + category, e);
                }
            }
        }
	}

}
