/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.liferay.portal.ext.service.impl;

import java.util.LinkedHashMap;

import com.liferay.portal.ContactFirstNameException;
import com.liferay.portal.ContactLastNameException;
import com.liferay.portal.DuplicateUserEmailAddressException;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.PasswordPolicy;
import com.liferay.portal.model.User;
import com.liferay.portal.security.pwd.PwdToolkitUtil;
import com.liferay.portal.service.impl.UserLocalServiceImpl;
import com.liferay.portlet.enterpriseadmin.util.UserIndexer;

public class ExtUserLocalServiceImpl extends UserLocalServiceImpl {

	public ExtUserLocalServiceImpl() {
		super();
	}
	
	protected void validate(
			long companyId, long userId, boolean autoPassword, String password1,
			String password2, boolean autoScreenName, String screenName,
			String emailAddress, String firstName, String lastName,
			long[] organizationIds)
		throws PortalException, SystemException {

		Company company = companyPersistence.findByPrimaryKey(companyId);

		if (company.isSystem()) {
			return;
		}

		if (!autoScreenName) {
			validateScreenName(companyId, userId, screenName);
		}

		if (!autoPassword) {
			PasswordPolicy passwordPolicy =
				passwordPolicyLocalService.getDefaultPasswordPolicy(companyId);

			PwdToolkitUtil.validate(
				companyId, 0, password1, password2, passwordPolicy);
		}

		validateEmailAddress(companyId, emailAddress);

		if (Validator.isNotNull(emailAddress)) {
			User user = userPersistence.fetchByC_EA(companyId, emailAddress);

			if (user != null) {
				throw new DuplicateUserEmailAddressException();
			}
		}

		
	}
	
	public Hits search(
            long companyId, String firstName, String middleName,
            String lastName, String screenName, String emailAddress,
            Boolean active, LinkedHashMap<String, Object> params,
            boolean andSearch, int start, int end, Sort sort)
        throws SystemException {

        try {
            BooleanQuery contextQuery = BooleanQueryFactoryUtil.create();

            contextQuery.addRequiredTerm(
                Field.PORTLET_ID, UserIndexer.PORTLET_ID);

            if (active != null) {
                contextQuery.addRequiredTerm("active", active);
            }

            BooleanQuery searchQuery = BooleanQueryFactoryUtil.create();

            addKeywordsAlternative(searchQuery, "firstName", firstName, andSearch);
            addKeywordsAlternative(searchQuery, "middleName", middleName, andSearch);
            addKeywordsAlternative(searchQuery, "lastName", lastName, andSearch);
            addKeywordsAlternative(searchQuery, "screenName", screenName, andSearch);
            addKeywordsAlternative(searchQuery, "emailAddress", emailAddress, andSearch);

            populateQuery(contextQuery, searchQuery, params, andSearch);

            BooleanQuery fullQuery = BooleanQueryFactoryUtil.create();

            fullQuery.add(contextQuery, BooleanClauseOccur.MUST);

            if (searchQuery.clauses().size() > 0) {
                fullQuery.add(searchQuery, BooleanClauseOccur.MUST);
            }

            return SearchEngineUtil.search(
                companyId, fullQuery, sort, start, end);
        }
        catch (Exception e) {
            throw new SystemException(e);
        }
    }
	
	private void addKeywordsAlternative(BooleanQuery searchQuery, String fieldName, String keywords, boolean andSearch) 
	throws ParseException {
	    if (Validator.isNotNull(keywords)) {
	        String[] keywordsArray = keywords.split("\\s");
	        for (String keyword: keywordsArray) {
	            if (keyword.trim().length() > 0) {
	                if (andSearch) {
	                    searchQuery.addRequiredTerm(fieldName, keyword, true);
	                }
	                else {
	                    searchQuery.addTerm(fieldName, keyword, true);
	                }
	            }
	        }
	    }
	}

}
