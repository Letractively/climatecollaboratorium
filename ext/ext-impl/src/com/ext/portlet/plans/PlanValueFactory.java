/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.ext.portlet.plans.PlanConstants.Attribute;
import com.ext.portlet.plans.model.Plan;
import com.ext.portlet.plans.model.PlanItem;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

public interface PlanValueFactory {
	
	public String getValue(Plan plan) throws SystemException, PortalException;
    public String getValue(PlanItem plan) throws SystemException, PortalException;
	
	public static class PojoGetter implements PlanValueFactory {
		
		private String format;
		private String field;
		
		public PojoGetter(String field, String format) {
			this.field = field;
			this.format = format;
		}
		
		public String getValue(Plan plan) throws SystemException{	
			Object o;
			try {
				o = BeanUtils.getProperty(plan, field);
			} catch (IllegalAccessException e) {
				throw new SystemException(e);
			} catch (InvocationTargetException e) {
				throw new SystemException(e);
			} catch (NoSuchMethodException e) {
				throw new SystemException(e);
			}
			return String.format(format,""+o);
		}

        @Override
        public String getValue(PlanItem plan) throws SystemException, PortalException {
            // THIS IS UGLY HACK
            return (new AttributeGetter(format, Attribute.valueOf(field.toUpperCase()))).getValue(plan);
        }
		
	}
	
	public static class AttributeGetter implements PlanValueFactory {
		
		Attribute[] atts;
		String format;
		
		public AttributeGetter(String format, Attribute... atts) {
			this.format = format;
			this.atts = atts;
		}
		
		public String getValue(Plan plan) throws SystemException {
			String[] params = new String[atts.length];
			int i = 0;
			for (Attribute att:atts) {
				params[i++]=att.format(plan);
			}
			return String.format(format,(Object[])params);
		}

        @Override
        public String getValue(PlanItem plan) throws SystemException, PortalException {
            String[] params = new String[atts.length];
            int i = 0;
            for (Attribute att:atts) {
                params[i++]=att.format(plan);
            }
            return String.format(format,(Object[])params);
        }
		
	}
	
	public static class EmptyFactory implements PlanValueFactory {

		public String getValue(Plan plan) throws SystemException {
			return "<empty>";
		}

        @Override
        public String getValue(PlanItem plan) throws SystemException, PortalException {
            // TODO Auto-generated method stub
            return "<empty>";
        }
		
	}
	
	
	
}
