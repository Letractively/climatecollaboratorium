/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans.attributes;

import com.liferay.portal.SystemException;

/**
 * @author: jintrone
 * @date: Feb 28, 2010
 */
public interface AttributeFunction<T> {

    public T process(String scenarioId) throws SystemException;
   
}
