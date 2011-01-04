package org.climatecollaboratorium.userprofile;

import java.io.IOException;
import java.util.Map;

import javax.faces.event.ActionEvent;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

public class UserProfileBean {
    private UserWrapper currentUser; 
    private User wrappedUser;
    private final static String USER_ID_PARAM = "userId";
    private final static Log _log = LogFactoryUtil.getLog(UserProfileBean.class);
    private boolean viewingOwnProfile = false;
    private boolean editing = false;
    
    public UserProfileBean() {
        
        System.out.println("Parameters map: " + Helper.getUrlParametersMap());
        Map<String, String> parameters = Helper.getUrlParametersMap();
        
        if (parameters.containsKey(USER_ID_PARAM)) {
            try {
                Long userId = Long.parseLong(parameters.get(USER_ID_PARAM));
                wrappedUser = UserLocalServiceUtil.getUser(userId);
                currentUser = new UserWrapper(wrappedUser);
            }
            catch (NumberFormatException e) {
                _log.error("Can't parse user id: " + parameters.get(USER_ID_PARAM), e);
            }
            catch (SystemException e) {
                _log.error("Can't load user with id " + parameters.get(USER_ID_PARAM), e);
            }
            catch (PortalException e) {
                _log.error("Can't load user with id " + parameters.get(USER_ID_PARAM), e);
            }
            
        }
        
        if (wrappedUser != null && 
                Helper.isUserLoggedIn() && 
                wrappedUser.getUserId() == Helper.getLiferayUser().getUserId()) {
            // user is logged in and is viewing his own profile
            viewingOwnProfile = true;
        }
        
    }
    
    public boolean isInitialized() {
        return currentUser != null;
    }
    
    
    public UserWrapper getCurrentUser() {
        return currentUser;
    }

    public boolean isViewingOwnProfile() {
        return viewingOwnProfile;
    }

    public boolean isEditing() {
        return editing;
    }
    
    public void toggleEditing(ActionEvent e) throws PortalException, SystemException {
        if (editing) {
            currentUser.cancelChanges();
        }
        editing = !editing;
    }
    
    public void updateUser(ActionEvent e) throws Exception {
        currentUser.persistChanges();
        editing = !editing;
    }

}
