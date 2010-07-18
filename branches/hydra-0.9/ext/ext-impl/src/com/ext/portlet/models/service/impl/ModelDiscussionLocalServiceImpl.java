/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.service.impl;

import java.util.List;

import com.ext.portlet.models.model.ModelDiscussion;
import com.ext.portlet.models.service.base.ModelDiscussionLocalServiceBaseImpl;
import com.ext.portlet.models.service.persistence.ModelDiscussionPersistenceImpl;
import com.liferay.portal.SystemException;


public class ModelDiscussionLocalServiceImpl
    extends ModelDiscussionLocalServiceBaseImpl {


	public List<ModelDiscussion> findByModelId(Long modelId) throws SystemException {
		return modelDiscussionPersistence.findByModelId(modelId);
	}

}
