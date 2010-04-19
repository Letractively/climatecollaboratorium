/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.debatemigration.backing;

import org.climatecollaboratorium.debatemigration.support.MigrationHelper;
import org.climatecollaboratorium.debatemigration.support.MigrationProgress;

import javax.faces.context.FacesContext;
import javax.portlet.PortletRequest;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * @author: jintrone
 * @date: Mar 28, 2010
 */
public class MigrationBean {

    String task = "";
    float percentComplete = 100.0f;

    public String getTask() {
      return task;
    }

    public void setTask(String task) {
       this.task = task;
    }

    public float getPercentComplete() {
      return (Math.round(percentComplete*10))/10f;
    }

    public void setPercentComplete(float f) {
      percentComplete = f * 100.0f;
    }


    public void migrate() throws Exception {
        final MigrationProgress progress = new MigrationProgress();
        progress.addListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals(MigrationProgress.TASK_UPDATED)) {
                    setTask((String) evt.getNewValue());
                } else {
                    setPercentComplete((Float)evt.getNewValue());
                }
            }
        });

        final PortletRequest request = (PortletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        new Thread(new Runnable() {
            public void run() {
                try {
                    new MigrationHelper(progress,request).migrate();
                } catch (Exception e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }).start();


    }

}
