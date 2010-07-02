package org.climatecollaboratorium.plans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ext.portlet.plans.PlanConstants.Columns;
import com.ext.portlet.plans.model.PlanItem;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

public class PlanIndexItemWrapper {
    private PlanItem wrapped;
    private Map<Columns, Object> columnValues; 

    public PlanIndexItemWrapper(PlanItem wrapped, List<Columns> columns ) throws SystemException, PortalException {
        this.wrapped = wrapped;
        columnValues = new HashMap<Columns, Object>();
        
        for (Columns col: columns) {
            columnValues.put(col, col.getValue(wrapped));
        }
    }
    
    
    public Map<Columns, Object> getColumnValues() {
        return columnValues;
    }
    
    public Long getPlanId() {
        return wrapped.getPlanId();
    }
}
