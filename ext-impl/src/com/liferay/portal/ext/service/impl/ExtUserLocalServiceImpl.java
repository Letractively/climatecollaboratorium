/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.liferay.portal.ext.service.impl;

import com.liferay.portal.ContactFirstNameException;
import com.liferay.portal.ContactLastNameException;
import com.liferay.portal.DuplicateUserEmailAddressException;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.PasswordPolicy;
import com.liferay.portal.model.User;
import com.liferay.portal.security.pwd.PwdToolkitUtil;
import com.liferay.portal.service.impl.UserLocalServiceImpl;

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
	
	

}
