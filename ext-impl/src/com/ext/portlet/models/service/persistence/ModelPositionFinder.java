/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.service.persistence;

import java.util.List;

import com.ext.portlet.models.model.ModelPosition;
import com.liferay.portal.SystemException;

public class ModelPositionFinder {
	  public static String FIND_BY_MODELID =
		  ModelPositionFinder.class.getName() + ".findByModelId";
	  
	    public static String FIND_BY_POSITIONID =
	    	ModelPositionFinder.class.getName() + ".findByPositionId";
	  
	    public static List<ModelPosition> findByModelId(long companyId, int ageMin, int ageMax)
	      throws SystemException {
	  
	      return null;
	    }
	  
	    public static List<ModelPosition> findByPositionId(
	        long companyId, int ageMin, int ageMax, int begin, int end)
	      throws SystemException {
	  
	      return null;
	    }
	  
}
