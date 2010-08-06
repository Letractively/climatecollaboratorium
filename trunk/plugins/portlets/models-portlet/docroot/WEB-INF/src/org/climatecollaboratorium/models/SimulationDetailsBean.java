package org.climatecollaboratorium.models;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.portlet.PortletRequest;

import org.climatecollaboratorium.models.support.DebateQuestionWrapper;
import org.climatecollaboratorium.models.support.SimulationsHelper;

import mit.simulation.climate.client.Simulation;
import mit.simulation.climate.client.comm.ModelNotFoundException;
import ys.wikiparser.WikiParser;

import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.model.DebateItem;
import com.ext.portlet.debaterevision.service.DebateLocalServiceUtil;
import com.ext.portlet.models.CollaboratoriumModelingService;
import com.ext.portlet.models.ui.ModelUIFactory;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;

public class SimulationDetailsBean {

    private Long modelId;
    private Simulation simulation;
    private int selectedTab;
    private String description;
    private List<DebateQuestionWrapper> questions = new ArrayList<DebateQuestionWrapper>();
    private List<DebateQuestionWrapper> positionsQuestions = new ArrayList<DebateQuestionWrapper>();
    
    private final static Map<String, Integer> tabNameNumberMap = new HashMap<String, Integer>();
    static {
        tabNameNumberMap.put("description", 0);
        tabNameNumberMap.put("expertEval", 1);
        tabNameNumberMap.put("positions", 2);
        tabNameNumberMap.put("actionsImpacts", 3);
    }
    
    private final static Map<Integer, Boolean> tabEditEnabled = new HashMap<Integer, Boolean>();
    
    
    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) throws SystemException {
        this.modelId = modelId;
        simulation = CollaboratoriumModelingService.repository().getSimulation(modelId);
        description = simulation.getDescription();
        selectedTab = 0;
    }
    
    public void navigate(Map<String, String> parameters) {
        String tabName = parameters.get("tab");
        if (tabName != null) {
            if (tabNameNumberMap.containsKey(tabName)) {
                selectedTab = tabNameNumberMap.get(tabName);
            }            
        }
    }

    public String getExpertEvaluation() throws PortalException, SystemException {
        Long wikiPageId = ModelUIFactory.getSimulationExpertEvaluationPageId(simulation);
        if (wikiPageId == null) {
            return "";
        }
        WikiPage wikiPage = WikiPageLocalServiceUtil.getPage(wikiPageId);
        
        // replace internal links with external links
        String content = wikiPage.getContent();
        Pattern pattern = Pattern.compile("\\[\\[(http)?([^\\]^|]*)\\|?([^\\]]*)\\]\\]");
        Matcher matcher = pattern.matcher(content); 
        int lastEnd = 0;
        
        StringBuilder sb = new StringBuilder();
        PortletRequest request =  (PortletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String wikiPageUrlPrefix = String.format("http://%s:%d/web/guest/resources/-/wiki/Main/", 
                request.getServerName(), request.getServerPort());
        
        while (matcher.find()) {
            if (matcher.group(1) != null) {
                // external link... ignore
                continue;
            }
            String name = matcher.group(3) != null && matcher.group(3).length() > 0 ? matcher.group(3) : matcher.group(2);
            sb.append(content.substring(lastEnd, matcher.start()));
            sb.append("[[");
            sb.append(wikiPageUrlPrefix);
            sb.append(matcher.group(2).replaceAll("\\s", "+"));
            sb.append("|");
            sb.append(name);
            sb.append("]]");
            
            lastEnd = matcher.end();
        }
        sb.append(content.substring(lastEnd));
        
        return WikiParser.renderXHTML(sb.toString());
    }
    
    public Long getExpertEvaluationPageId() throws SystemException {
        return ModelUIFactory.getSimulationExpertEvaluationPageId(simulation);
    }
    
    public void setExpertEvaluationPageId(Long pageId) throws SystemException {
        ModelUIFactory.setSimulationExpertEvaluationPageId(simulation, pageId);
    }

    public void setSelectedTab(int selectedTab) {
        this.selectedTab = selectedTab;
    }

    public int getSelectedTab() {
        return selectedTab;
    }
    
    public void toggleEdit(ActionEvent e) {
        if (tabEditEnabled.containsKey(selectedTab)) {
            tabEditEnabled.remove(selectedTab);
        }
        else {
            tabEditEnabled.put(selectedTab, true);
        }
    }
    
    public boolean isTabEditing() {
        return tabEditEnabled.containsKey(selectedTab);
    }
    
    public void updateSimulation(ActionEvent event) throws IOException, ModelNotFoundException, SystemException {
        simulation.setDescription(description);
        SimulationsHelper.getInstance().getRepository().updateSimulation(simulation);
        toggleEdit(event);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
    public List<DebateQuestionWrapper> getAvailablePositions() throws SystemException {
        if (questions.size() == 0) {
            Set<Long> selectedPositionsIds = new HashSet<Long>(ModelUIFactory.getSimulationPositionsIds(simulation));
            for (Debate debate: DebateLocalServiceUtil.getDebates()) {
                questions.add(new DebateQuestionWrapper(debate.getCurrentRoot(), selectedPositionsIds));
            }
        }
        return questions;
    }
    
    public List<DebateQuestionWrapper> getPositionsQuestions() throws SystemException {
        if (positionsQuestions.size() == 0) {
            Set<Long> selectedPositionsIds = new HashSet<Long>(ModelUIFactory.getSimulationPositionsIds(simulation));
            for (Debate debate: DebateLocalServiceUtil.getDebates()) {
                boolean hasSelectedPosition = false;
                for (DebateItem item: debate.getCurrentRoot().getChildren()) {
                    if (selectedPositionsIds.contains(item.getDebateItemId())) {
                        hasSelectedPosition = true;
                        break;
                    }
                }
                if (hasSelectedPosition) {
                    positionsQuestions.add(new DebateQuestionWrapper(debate.getCurrentRoot(), selectedPositionsIds));
                }
            }
        }
        return positionsQuestions;
    }
    
    public void updatePositions(ActionEvent e) throws SystemException {
        List<Long> positionsIds = new ArrayList<Long>();
        for (DebateQuestionWrapper question: questions) {
            if (question.getPosition() != null) {
                positionsIds.add(question.getPosition());
            }
        }
        ModelUIFactory.setSimulationPositionsIds(simulation, positionsIds);
        toggleEdit(e);
        positionsQuestions.clear();
    }

}