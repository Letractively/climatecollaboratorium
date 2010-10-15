package com.ext.portlet.contests;

import java.util.ArrayList;
import java.util.List;

import com.ext.portlet.contests.model.ContestPhase;
import com.ext.portlet.plans.PlanConstants.Columns;
import com.liferay.portal.SystemException;

public class ContestPhaseHelper {
    
    public static List<Columns> getPhaseColumns(ContestPhase phase) throws SystemException {
        List<Columns> columns = new ArrayList<Columns>();
        for (String column: phase.getPhaseColumns()) {
            columns.add(Columns.valueOf(column));
        }
        return columns;
    }

}
