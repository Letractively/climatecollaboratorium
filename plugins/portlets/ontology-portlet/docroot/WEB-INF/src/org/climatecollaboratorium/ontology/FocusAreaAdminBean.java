package org.climatecollaboratorium.ontology;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.climatecollaboratorium.ontology.wrappers.FocusAreaWrapper;

import com.ext.portlet.ontology.model.FocusArea;
import com.ext.portlet.ontology.service.FocusAreaLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

public class FocusAreaAdminBean {
    private FocusAreaWrapper area;
    
    public List<FocusArea> getFocusAreas() throws SystemException {
        return FocusAreaLocalServiceUtil.getFocusAreas(0, Integer.MAX_VALUE);
    }

    public void setArea(FocusAreaWrapper area) {
        this.area = area;
    }

    public FocusAreaWrapper getArea() {
        return area;
    }
    
    public void editFocusArea(ActionEvent e) throws PortalException, SystemException {
        Long areaId = null;
        try {
            areaId = Long.parseLong(e.getComponent().getAttributes().get("areaId").toString());
        }
        catch (Exception ex) {
            //
        }
        
        area = new FocusAreaWrapper(areaId);
    }

}
