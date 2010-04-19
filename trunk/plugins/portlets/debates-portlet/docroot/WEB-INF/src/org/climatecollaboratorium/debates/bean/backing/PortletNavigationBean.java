/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.debates.bean.backing;

import com.liferay.portal.SystemException;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


/**
 * @author: jintrone
 * @date: Mar 23, 2010
 */
public class PortletNavigationBean  {



    public static String CATEGORIES_VIEW = "Categories";
    public static String DETAIL_VIEW = "Detail";

    public String selected = CATEGORIES_VIEW;

    private Long debate = null;
    private Long item = null;



    private DebateDetailsBean debateDetails;

    private Long urinavDebate = -1L;
    private Long urinavItem = -1L;

    public PortletNavigationBean() throws SystemException {

    }



    public void setSelectedView(String item) {
       selected = item;
    }

    public String getSelectedView() {
        return selected;
    }

    public void setUriNavItem(String s) {
        this.urinavItem = s==null?-1L:Long.parseLong(s);
    }

    public String getUriNavItem() {
        return urinavItem+"";
    }

    public void setUriNavDebate(String s) throws SystemException {
        this.urinavDebate = s==null?-1L:Long.parseLong(s);
        if (urinavDebate>-1) setDebate(urinavDebate);
    }

    public String getUriNavDebate() {
        return urinavDebate+"";
    }

    public void navigate(ActionEvent e) throws SystemException {
//       setDebate(Long.parseLong(String.valueOf(e.getComponent().getAttributes().get("debate"))));
//       setItem(Long.parseLong(String.valueOf(e.getComponent().getAttributes().get("item"))));
    }
    
    public void navigateWithItem(ActionEvent e) throws SystemException {
        setDebate(debate);
        setItem(item);
    }

    public void viewCategories() {
      // setSelectedView(CATEGORIES_VIEW);
    }

    public void setDebate(Long debate) throws SystemException {
        this.debate = debate;
        if (debateDetails == null) {
            debateDetails = (DebateDetailsBean)FacesContext.getCurrentInstance().getApplication().getVariableResolver().resolveVariable(FacesContext.getCurrentInstance(),"debateDetails");
        }
        debateDetails.setDebateId(debate);
        if (debate!=null && debate > 0) {
            setSelectedView(DETAIL_VIEW);
        } else {
            setSelectedView(CATEGORIES_VIEW);
        }
    }

    public Long getDebate() {
        return this.debate;
    }

    public void setDebateDetailsBean(DebateDetailsBean debatedetails) {
       debateDetails = debatedetails;
    }

    public DebateDetailsBean getDebateDetails() {
        return debateDetails;
    }
    
    
    public Long getItem() {
        return item;
    }

    public void setItem(Long item) {
        this.item = item;       

        if (item > 0) {
            if (debateDetails == null) {
                debateDetails = (DebateDetailsBean)FacesContext.getCurrentInstance().getApplication().getVariableResolver().resolveVariable(FacesContext.getCurrentInstance(),"debateDetails");
            }
            debateDetails.setSelectedItemId(item);
        }
    }
    
    

    /**
     * Note that the parameterMap is a Map of form [String -> Object[]]; method
     * gets all parameters from original request.
     *
     * @param context
     * @return
     */
//    public static Map getRawParameters(FacesContext context) {
//        Map<String,Object> requests = context.getExternalContext().getRequestMap();
//        for (String requestName : requests.keySet()) {
//            if (requests.get(requestName) instanceof HttpServletRequestWrapper) {
//                ServletRequest result = ((HttpServletRequestWrapper) requests.get(requestName)).getRequest();
//                return result.getParameterMap();
//            }
//        }
//        return null;
//    }
}
