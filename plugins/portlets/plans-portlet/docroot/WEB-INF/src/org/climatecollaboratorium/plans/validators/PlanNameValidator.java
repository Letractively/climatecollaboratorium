package org.climatecollaboratorium.plans.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.liferay.portal.SystemException;

public class PlanNameValidator implements Validator {

    @Override
    public void validate(FacesContext ctx, UIComponent comp, Object obj) throws ValidatorException {
        System.out.println("FacesContext: " + ctx + "\tcomponent: " + comp + "\tobj: " + obj);
        String name = (String) obj;
        try {
            if (name.trim().length() == 0) {
                FacesMessage fm = new FacesMessage();
                fm.setSummary("Plan name can't be empty.");
                fm.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(fm);
            }
            if (! PlanItemLocalServiceUtil.isNameAvailable(name)) {
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
