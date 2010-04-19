/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans;

import com.ext.portlet.plans.model.PlanAttributeFilter;

public interface PlanFilterFactory {
	
	public StringBuilder getFilterString(String attribute, PlanAttributeFilter filter, StringBuilder builder);
	
	public static abstract class MinMaxFilter implements PlanFilterFactory {

		
		public StringBuilder getFilterString(String attribute, PlanAttributeFilter filter, StringBuilder builder) {
			if (builder == null) {
				builder = new StringBuilder();
			}
			builder.append("attributeName='"+attribute+"' and attributeValue > "+getMin(filter)+" and attributeValue <="+getMax(filter));
			return builder;
		}
		
		
		public abstract String getMin(PlanAttributeFilter filter);
		
		public abstract String getMax(PlanAttributeFilter filter);
		
	}
	
	
	public static abstract class LessThanFilter implements PlanFilterFactory {
		
		public StringBuilder getFilterString(String attribute, PlanAttributeFilter filter, StringBuilder builder) {
			if (builder == null) {
				builder = new StringBuilder();
			}
			builder.append("attributeName='"+attribute+"' and attributeValue < "+getValue(filter));
			return builder;
		}
		
		public abstract String getValue(PlanAttributeFilter filter);
		
	}
	
	public static abstract class MoreThanFilter implements PlanFilterFactory {
		
		public StringBuilder getFilterString(String attribute, PlanAttributeFilter filter, StringBuilder builder) {
			if (builder == null) {
				builder = new StringBuilder();
			}
			builder.append("attributeName='"+attribute+"' and attributeValue >= "+getValue(filter));
			
			return builder;
		}
		
		public abstract String getValue(PlanAttributeFilter filter);
		
	}


}
