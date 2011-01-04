package org.climatecollaboratorium.userprofile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;

import com.ext.portlet.community.action.CommunityConstants;
import com.ext.portlet.plans.model.PlanFan;
import com.ext.portlet.plans.service.PlanFanLocalServiceUtil;
import com.icesoft.faces.component.inputfile.InputFile;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ImageServletTokenUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.UserServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import com.liferay.util.servlet.UploadException;

public class UserWrapper {

    private User user; 
    private String about;
    private String realName;
    private String firstName;
    private String lastName;
    private String email;
    private List<SupportedPlanBean> supportedPlans = new ArrayList<SupportedPlanBean>();
    private List<UserActivityBean> userActivities = new ArrayList<UserActivityBean>();
    private int maxActivitiesCount = 10;
    private String screenName;
    private File newUserProfile;
    private String filteredAbout;
    
    private final static Log _log = LogFactoryUtil.getLog(UserWrapper.class);
    
    public UserWrapper(User user) throws PortalException, SystemException {
        init(user);
    }
    
    private void init(User user) throws PortalException, SystemException {
        this.user = user;
        about = HtmlUtil.escape(ExpandoValueLocalServiceUtil.getData(User.class.getName(), CommunityConstants.EXPANDO, 
                CommunityConstants.BIO, user.getUserId(), StringPool.BLANK));
        filteredAbout = Helper.filterLineBreaks(about);
        
        realName = getName(user.getFullName(), user.getScreenName());
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmailAddress();
        screenName = user.getScreenName();
        
        for (PlanFan supportedPlanInfo : PlanFanLocalServiceUtil.getPlanFansForUser(user.getUserId())) {
            supportedPlans.add(new SupportedPlanBean(supportedPlanInfo));
        }
        
        for (SocialActivity activity: SocialActivityLocalServiceUtil.getUserActivities(user.getUserId(), 0, maxActivitiesCount)) {;//userActivitiesCount - maxActivitiesCount, userActivitiesCount)) {
            userActivities.add(new UserActivityBean(activity));
        }
    }
    
    public String getAbout() {
        return about;
    }
    
    public void setAbout(String about) {
        this.about = about;
    }
    
    public String getScreenName() {
        return screenName;
    }
    
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
    
    public String getPortrait() {
        return Helper.getThemeDisplay().getPathImage() + 
            "/user_" + 
            (user.getFemale() ? "female" : "male") + 
            "_portrait?img_id=" + 
            user.getPortraitId() + 
            "&t=" + 
            ImageServletTokenUtil.getToken(user.getPortraitId());
    }
    
    public String getRealName() {
        return realName;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public Date getJoinDate() {
        return user.getCreateDate();
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public List<SupportedPlanBean> getSupportedPlans() {
        return supportedPlans;
    }
    
    public List<UserActivityBean> getActivities() {
        return userActivities;
    }
    
    private String getName(String name, String defaultName) {
        if (name == null || name.trim().equals("")) {
            return defaultName;
        }
        return name;
    }
    
    public void uploadPortrait(ActionEvent e) {
        InputFile inputFile = (InputFile) e.getSource();
        if (inputFile.getStatus() == InputFile.INVALID) {
            _log.error("There was an error when uploading file", inputFile.getFileInfo().getException());
            return;
        }
        newUserProfile = inputFile.getFile();
        
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void persistChanges() throws Exception {
        boolean changed = false;
        if (! user.getScreenName().equals(screenName)) {
            user.setScreenName(screenName);
            changed = true;
        }
        if (! user.getEmailAddress().equals(email)) {
            user.setEmailAddress(email);
            changed = true;
        }
        if (! user.getFirstName().equals(firstName)) {
            user.setFirstName(firstName);
            changed = true;
        }
        if (! user.getLastName().equals(lastName)) {
            user.setLastName(lastName);
            changed = true;
        }
        if (newUserProfile != null) {
            byte[] bytes = FileUtil.getBytes(newUserProfile);

            if ((bytes != null) && (bytes.length > 0)) {
                /* we need to set permission checker for liferay */
                PermissionChecker permissionChecker =
                    PermissionCheckerFactoryUtil.create(user, true);

                PermissionThreadLocal.setPermissionChecker(permissionChecker);
                UserServiceUtil.updatePortrait(user.getUserId(), bytes);
            }
            changed = true;
        }

        String existingBio = ExpandoValueLocalServiceUtil.getData(
                User.class.getName(), CommunityConstants.EXPANDO,
                CommunityConstants.BIO, user.getUserId(),
                StringPool.BLANK);
        if (! existingBio.equals(about)) {
            ExpandoValueLocalServiceUtil.addValue(User.class.getName(), CommunityConstants.EXPANDO, 
                    CommunityConstants.BIO, user.getUserId(), about);
        }
        
        if (changed) {
            UserLocalServiceUtil.updateUser(user);
        }
        init(user);
    }
    
    public void cancelChanges() throws PortalException, SystemException {
        init(user);
    }
    
    public String getFilteredAbout() {
        return filteredAbout;
    }
}