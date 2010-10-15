package org.climatecollaboratorium.facelets.debates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.service.DebateLocalServiceUtil;

public class DebatesDetailsBean {
    private List<Debate> debates = new ArrayList<Debate>();
    private Long[] lastInitdDebatesIds = new Long[0];

    public boolean init(Long[] debatesIds) {
        if (Arrays.equals(lastInitdDebatesIds, debatesIds)) {
            return debates.size() > 0;
        }
        lastInitdDebatesIds = debatesIds;
        debates.clear();
        
        for (Long debateId: debatesIds) {
            Debate debate = DebateLocalServiceUtil.findLastVersion(debateId);
            if (debate != null) {
                debates.add(debate);
            }
        }
        
        
        return debates.size() > 0;
    }
    
    public List<Debate> getDebates() {
        return debates;
    }
    
    

}
