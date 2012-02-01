/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.debatemigration.support;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * @author: jintrone
 * @date: Mar 28, 2010
 */
public class MigrationProgress {

    public static String END_UPDATED = "End value updated";
    public static String CURRENT_UPDATED = "Current value updated";
    public static String TASK_UPDATED = "Task updated";

    private float start = 0f;
    private String task = "";
    private float end = 0f;
    private float current = 0f;

    public PropertyChangeSupport support = new PropertyChangeSupport(this);


    public void setTask(String task) {
        String old = this.task;
        this.task = task;
        support.firePropertyChange(TASK_UPDATED,old,this.task);

    }

    public void setEnd(float end) {
       float old = this.end;
        this.end = end;
        support.firePropertyChange(END_UPDATED,old,end);
    }

    public void setCurrent(float current) {
       float old = this.current;
        this.current = current;
        support.firePropertyChange(CURRENT_UPDATED,old,current);
    }


    public float getPercentComplete() {
        return (end > 0f?current / end:0f) * 100f;
    }

    public String getCurrentTask() {
            return task;
    }

    public void increment() {
        setCurrent(current+1);
    }

    public void reset() {
        current = 0f;
        end = 0f;
      support.firePropertyChange(CURRENT_UPDATED,0f,0f);
    }
    

    public void addListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
