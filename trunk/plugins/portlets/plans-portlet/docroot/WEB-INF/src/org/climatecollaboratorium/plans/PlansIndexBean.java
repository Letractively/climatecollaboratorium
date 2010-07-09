package org.climatecollaboratorium.plans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;

import org.climatecollaboratorium.plans.utils.DataPage;
import org.climatecollaboratorium.plans.utils.PagedListDataModel;

import com.ext.portlet.plans.NoSuchPlanVoteException;
import com.ext.portlet.plans.PlanLocalServiceHelper;
import com.ext.portlet.plans.PlanConstants.Attribute;
import com.ext.portlet.plans.PlanConstants.Columns;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.model.PlanVote;
import com.ext.portlet.plans.model.PlansUserSettings;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTypeLocalServiceUtil;
import com.ext.portlet.plans.service.PlanVoteLocalServiceUtil;
import com.ext.portlet.plans.service.PlansUserSettingsLocalServiceUtil;
import com.icesoft.faces.component.datapaginator.DataPaginator;
import com.icesoft.faces.component.datapaginator.PaginatorActionEvent;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

public class PlansIndexBean {

    private List<Columns> columns;
    
    private String sortColumn = "NAME";
    private boolean sortAscending = true;
    private boolean updatePlansList = true;
    private int pageSize = 50;
    // Current items in ui
    private List uiCustomerBeans = new ArrayList(pageSize);

    private PagedListDataModel plansDataModel;

    private PlanType planType;

    private ConfigureColumnsBean columnsConfiguration;

    private FilterPlansBean filterPlansBean;
    private PlansUserSettings plansUserSettings;

    private Long userVote;

    private boolean findUserVote;
    

    private List<PlanIndexItemWrapper> plans = new ArrayList<PlanIndexItemWrapper>();

    private DataPaginator dataPaginator;
    
    public PlansIndexBean() throws SystemException, PortalException {
        planType = PlanTypeLocalServiceUtil.getDefaultPlanType();
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        
        dataPaginator = new DataPaginator();
        refresh();
    }

    public List<PlanIndexItemWrapper> getPlans() throws SystemException, PortalException {
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        if (updatePlansList) {
            plans.clear();
            updatePlansList = false;
            Columns sortCol = Columns.valueOf(sortColumn);

            String sortAttribute = Attribute.NAME.name();
            if (sortCol.isSortable()) {
                sortAttribute = sortCol.getSortAttribute().name();
            }
            for(PlanItem plan: PlanItemLocalServiceUtil.getPlans(ectx.getSessionMap(), ectx.getRequestMap(), planType, 0, 100, sortAttribute, sortAscending ? "ASC" : "DESC")) {
                plans.add(new PlanIndexItemWrapper(plan, this));
            }
        }
        return plans;
    }
    
    public List<Columns> getColumns() {
        return columns;
    }
    

    public DataPaginator getDataPaginator() { 
        return dataPaginator; 
    }
    public void setDataPaginator(DataPaginator dataPaginator) { 
        this.dataPaginator = dataPaginator; 
    }

    

    public String getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
        updatePlansList = true;
    }

    public boolean isSortAscending() {
        return sortAscending;
    }

    public void setSortAscending(boolean sortAscending) {
        this.sortAscending = sortAscending;
        updatePlansList = true;
    }
    


    /**
     * Bound to DataTable value in the ui.
     */
    public DataModel getData() {
        
        if(plansDataModel == null){
            plansDataModel = new LocalDataModel(pageSize);
        }
        return plansDataModel;
    }
    
    public void filtersUpdate() {
        updatePlansList = true;
        //plansDataModel.setDirtyData();
    }

    /**
     * This is where the Customer data is retrieved from the database and
     * returned as a list of CustomerBean objects for display in the UI.
     * @throws SystemException 
     * @throws PortalException 
     */
    private DataPage getDataPage(int startRow, int pageSize) throws PortalException, SystemException {
        
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        // this is bad! FIXME
        
        int endIndex = startRow + pageSize;
        // Calculate indices to be displayed in the ui.
        
        
        plans.clear();
        
        Columns sortCol = Columns.valueOf(sortColumn);

        String sortAttribute = Attribute.NAME.name();
        if (sortCol.isSortable()) {
            sortAttribute = sortCol.getSortAttribute().name();
        }
        for (PlanItem item: PlanItemLocalServiceUtil.getPlans(ectx.getSessionMap(), ectx.getRequestMap(), planType, startRow, endIndex, sortAttribute, sortAscending ? "ASC" : "DESC")) {
            plans.add(new PlanIndexItemWrapper(item, this));
        }
        // FIXME this isn't correct, appropriate value should be calculated.
        int totalNumberPlans = PlanItemLocalServiceUtil.getPlans(ectx.getSessionMap(), ectx.getRequestMap(), planType, 0, Integer.MAX_VALUE, sortAttribute, sortAscending ? "ASC" : "DESC").size();

        plansDataModel.setDirtyData(false);

        // This is required when using Hibernate JPA.  If the EntityManager is not
        // cleared or closed objects are cached and stale objects will show up
        // in the table.
        // This way, the detached objects are reread from the database.
        // This call is not required with TopLink JPA, which uses a Query Hint
        // to clear the l2 cache in CustomerDAO.

        return new DataPage(totalNumberPlans,startRow,plans);
    }

    private class LocalDataModel extends PagedListDataModel {
        public LocalDataModel(int pageSize) {
            super(pageSize);
        }

        public DataPage fetchPage(int startRow, int pageSize) {
            // call enclosing managed bean method to fetch the data
            try {
                return getDataPage(startRow, pageSize);
            } catch (PortalException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SystemException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
    }

    public PlanType getPlanType() {
        return planType;
    }
    
    public ConfigureColumnsBean getColumnsConfiguration() throws SystemException, PortalException {
        if (columnsConfiguration == null) {
            columnsConfiguration = new ConfigureColumnsBean(this);
        }
        return columnsConfiguration;
    }
    
    public FilterPlansBean getFilterPlansBean() throws PortalException, SystemException {
        if (filterPlansBean == null) {
            filterPlansBean = new FilterPlansBean(this);
        }
        return filterPlansBean;
    }

    private void columnsUpdate() throws PortalException, SystemException {
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        plansUserSettings = PlansUserSettingsLocalServiceUtil.getPlanUserSettings(ectx.getSessionMap(), ectx.getRequestMap(), planType);
        columns = new ArrayList<Columns>();
        for (Columns col: Columns.getPlanTypeColumns(planType)) {
            System.out.println(col.getName() + col.getUserSetting(plansUserSettings));
            if (col.getUserSetting(plansUserSettings)) {
                columns.add(col);
            }
        }
        updatePlansList = true;
    }
    
    public void usePublishedPlans(ActionEvent e) throws PortalException, SystemException {
        if (!planType.getPublished()) {
            planType = PlanTypeLocalServiceUtil.getPlanType(planType.getPublishedCounterpartId());
            refresh();
        }
    }
    
    public void useUnPublishedPlans(ActionEvent e) throws PortalException, SystemException {
        if (planType.getPublished()) {
            planType = PlanTypeLocalServiceUtil.getPlanType(planType.getPublishedCounterpartId());
            refresh();
        }
    }

    public void refresh() throws PortalException, SystemException {
        updatePlansList = true;
        filterPlansBean = null;
        columnsConfiguration = null;
        columnsUpdate();
        getPlans();
    }
    
    public int getVotesCount() throws SystemException {
        return PlanVoteLocalServiceUtil.getPlanVotesCount();
    }
    
    public int getPlansCount() {
        return plans.size();
    }
    
    public void findUserVote(ActionEvent e) throws PortalException, SystemException {
        findUserVote = true;
        refresh();
    }
    
    public boolean isFindUserVote() {
        if (findUserVote) {
            findUserVote = false;
            return true;
        }
        return false;
    }
    
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    public boolean getHasUserVoted() throws PortalException, SystemException {
        if (Helper.isUserLoggedIn()) {
            try {
                PlanVote vote = PlanVoteLocalServiceUtil.getPlanVote(Helper.getLiferayUser().getUserId());
                return vote != null;
            }
            catch (NoSuchPlanVoteException e) {
            }
        }
        return false; 
    }

}
