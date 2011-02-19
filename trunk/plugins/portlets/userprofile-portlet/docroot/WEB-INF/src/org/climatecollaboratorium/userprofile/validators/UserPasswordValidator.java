package org.climatecollaboratorium.userprofile.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.climatecollaboratorium.userprofile.UserWrapper;

import com.liferay.portal.PwdEncryptorException;
import com.liferay.portal.model.User;
import com.liferay.portal.security.pwd.PwdEncryptor;
import com.liferay.portal.service.UserLocalServiceUtil;

public class UserPasswordValidator implements Validator {

    @Override
    public void validate(FacesContext arg0, UIComponent arg1, Object value) throws ValidatorException {
        
        String type = arg1.getAttributes().get("type").toString();
        
        String currentPassword = ((UIInput) arg1.getParent().findComponent("currentPasswordInput")).getValue().toString();
        String newPassword = ((UIInput) arg1.getParent().findComponent("newPasswordInput")).getValue().toString();
        String newPasswordRetype = ((UIInput) arg1.getParent().findComponent("newPasswordRetypeInput")).getValue().toString();
        
        User currentUser = ((UserWrapper) arg1.getAttributes().get("currentUser")).getWrapped();
        
        FacesMessage errorMsg = new FacesMessage();
        errorMsg.setSeverity(FacesMessage.SEVERITY_ERROR);
        boolean isError = false;
        
        if (type.equals("current")) {
            try {
                if (! PwdEncryptor.encrypt(value.toString()).equals(currentUser.getPassword())) {
                    isError = true;
                    errorMsg.setDetail("Invalid password");
                    errorMsg.setSummary("Invalid password");
                }
            } catch (PwdEncryptorException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                isError = true;
                errorMsg.setDetail("Invalid password");
                errorMsg.setSummary("Invalid password");
            }
        }
        else if (type.equals("newRetype")) {
                if (! value.equals(newPassword)) {
                    errorMsg.setDetail("Passwords don't match");
                    errorMsg.setSummary("Passwords don't match");
                    isError = true;
                }
                if (value.toString().trim().length() > 0 && currentPassword.length() < 0) {
                    errorMsg.setDetail("You need to provide current password");
                    errorMsg.setSummary("You need to provide current password");
                    isError = true;
                }
            }
            
        
        if (isError) {
            throw new ValidatorException(errorMsg);
        }
    }

}
