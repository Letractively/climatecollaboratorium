package org.climatecollaboratorium.contact.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.portlet.PortletSession;

import com.liferay.portal.util.WebKeys;

public class CaptchaValidator implements Validator {

    @Override
    public void validate(FacesContext ctx, UIComponent component, Object value) throws ValidatorException {
        PortletSession session = (PortletSession) ctx.getExternalContext().getSession(true);
        Object captchaCorrectValue = session.getAttribute(WebKeys.CAPTCHA_TEXT, PortletSession.APPLICATION_SCOPE);
        
        if (!value.equals(captchaCorrectValue)) {
            FacesMessage fm = new FacesMessage();
            fm.setSummary("Provided value is invalid");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(fm);
        }
    }

}
