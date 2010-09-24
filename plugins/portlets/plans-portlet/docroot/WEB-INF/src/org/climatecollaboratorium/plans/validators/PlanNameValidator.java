package org.climatecollaboratorium.plans.validators;

import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;

import com.liferay.portal.SystemException;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class PlanNameValidator implements Validator {

    @Override
    public void validate(FacesContext ctx, UIComponent comp, Object obj) throws ValidatorException {
        String name = (String) obj;
        String planNameAttribute = comp.getAttributes().get("planName").toString();
        try {
            if (name.trim().length() == 0) {
                FacesMessage fm = new FacesMessage();
                fm.setSummary("Plan name can't be empty.");
                fm.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(fm);
            }
            if (planNameAttribute.equals(name)) {
                // if name hasn't changed, don't do anything
                return;
            }
            else if (! PlanItemLocalServiceUtil.isNameAvailable(name)) {
                FacesMessage fm = new FacesMessage();
                fm.setSummary("Plan with name \"" + name + "\" already existis, choose different name.");
                fm.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(fm);
            }
        } catch (SystemException e) {
            FacesMessage fm = new FacesMessage();
            fm.setSummary("There was an error with plan name validation, please try again.");
            throw new ValidatorException(fm, e);
        }

    }

}