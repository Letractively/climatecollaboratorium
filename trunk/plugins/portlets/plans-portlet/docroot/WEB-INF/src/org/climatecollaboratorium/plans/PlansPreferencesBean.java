package org.climatecollaboratorium.plans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.service.DebateLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

public class PlansPreferencesBean {

    private Long[] questionsArray = new Long[0];
    private final static String QUESTIONS_PREFERENCE = "QUESTIONS";

    public PlansPreferencesBean() {
        String[] questionsStr = Helper.getPortletPrefs().getValues(QUESTIONS_PREFERENCE, new String[0]);
        questionsArray = convertStringsToLongs(questionsStr);
    }

    public String submit() throws ReadOnlyException, ValidatorException, IOException, PortalException, SystemException {
        FacesMessage fm = new FacesMessage();
        
        PortletPreferences prefs = Helper.getPortletPrefs();
        String[] questionsStr = convertLongsToStrings(questionsArray);
        
        prefs.setValues(QUESTIONS_PREFERENCE, questionsStr);

        prefs.store();

        fm.setSummary("Settings saved successfully");
        fm.setSeverity(FacesMessage.SEVERITY_INFO);

        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, fm);
        return "";
    }

    public List<SelectItem> getQuestionsItems() {
        List<Debate> debates = DebateLocalServiceUtil.getDebates();
        List<SelectItem> ret = new ArrayList<SelectItem>();
        for (Debate d : debates) {
            ret.add(new SelectItem(d.getDebateId(), d.getCurrentRoot().getDebateSummary()));
        }
        return ret;
    }

    public void setQuestions(Long[] questions) {
        questionsArray = questions;
    }

    public Long[] getQuestions() {
        return questionsArray;
    }
    
    public static List<Debate> getQuestionDebates() {
        String[] questionsStr = Helper.getPortletPrefs().getValues(QUESTIONS_PREFERENCE, new String[0]);
        Long[] questionsArray = convertStringsToLongs(questionsStr);
        
        List<Debate> debates = new ArrayList<Debate>();
        for (Long debateId: questionsArray) {
            debates.add(DebateLocalServiceUtil.findLastVersion(debateId));
        }
        return debates;
    }
    
    private static Long[] convertStringsToLongs(String[] arrayStr) {
        Long[] arrayLong = new Long[arrayStr.length];
        for (int i=0; i < arrayStr.length; i++) {
            arrayLong[i] = Long.parseLong(arrayStr[i]);
        }
        return arrayLong;
    }
    
    private static String[] convertLongsToStrings(Long[] arrayLong) {
        String[] arrayStr = new String[arrayLong.length];
        for (int i=0; i < arrayLong.length; i++) {
            arrayStr[i] = arrayLong[i].toString();
        }
        return arrayStr;
    }

}
