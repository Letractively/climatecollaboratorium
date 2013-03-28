package org.climatecollaboratorium.plans.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.event.ActionEvent;

import org.climatecollaboratorium.plans.admin.wrappers.ContestWrapper;

import com.ext.portlet.contests.NoSuchContestException;
import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.service.ContestLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.security.permission.AdvancedPermissionChecker;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.UserLocalServiceUtil;

public class ContestsAdmin {
    private ContestWrapper editedContest;
    
    public List<ContestWrapper> getContests() throws SystemException {
        List<ContestWrapper> ret = new ArrayList<ContestWrapper>();
        
        for (Contest contest: ContestLocalServiceUtil.getContests(0, Integer.MAX_VALUE)) {
            ret.add(new ContestWrapper(contest));
        }
        
        return ret;
    }
    
    public void editCotnestActionListener(ActionEvent e) {
        editedContest = (ContestWrapper) e.getComponent().getAttributes().get("contest");
    }
    
    public void createContestActionListener(ActionEvent e) throws PortalException, SystemException, InstantiationException, IllegalAccessException {
    	while (true) {
    		try {
    			long contestId = CounterLocalServiceUtil.increment(Contest.class.getName());
    		
    			ContestLocalServiceUtil.getContest(contestId);
    		}
    		catch (NoSuchContestException ex) {
    			break;
    		}
    		catch (Exception ex) {
    			ex.printStackTrace();
    		}
    		
    	}

		User user = UserLocalServiceUtil.getUser(10144L);
		long userId = user.getUserId();

		String name = String.valueOf(userId);

		PrincipalThreadLocal.setName(name);

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (permissionChecker == null) {
			permissionChecker = AdvancedPermissionChecker.class.newInstance();
		}

		permissionChecker.init(user, false);

		PermissionThreadLocal.setPermissionChecker(permissionChecker);
		
		Contest contest = ContestLocalServiceUtil.createNewContest(10144L, "New content name bla bla bla " + new Random().nextLong());
        editedContest = new ContestWrapper(contest);
    	
    }
    
    public String editContest() {
        return "editContest";
    }

    public void setEditedContest(ContestWrapper editedContest) {
        this.editedContest = editedContest;
    }

    public ContestWrapper getEditedContest() {
        return editedContest;
    }

}
