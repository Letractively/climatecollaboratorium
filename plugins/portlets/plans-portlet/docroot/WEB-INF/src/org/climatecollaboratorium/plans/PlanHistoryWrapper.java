/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.plans;

import com.ext.portlet.plans.model.PlanDescription;
import com.ext.portlet.plans.model.PlanModelRun;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Sep 12, 2010
 * Time: 3:19:27 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class PlanHistoryWrapper<T> {

    public abstract Date getUpdateDate();
    public abstract User getUpdateAuthor();
    public abstract long getUpdateVersion();
    public abstract T getWrapped();

    public static PlanHistoryWrapper<PlanModelRun> getWrapper(final PlanModelRun run) {
        return new PlanHistoryWrapper<PlanModelRun>() {

            @Override
            public Date getUpdateDate() {
                return run.getCreated();
            }

            @Override
            public User getUpdateAuthor() {
               try {
                   return run.getUpdateAuthor();
               } catch (SystemException e) {
                   e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
               } catch (PortalException e) {
                   e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
               }
                return null;
            }

            @Override
            public long getUpdateVersion() {
                return run.getVersion();
            }

            @Override
            public PlanModelRun getWrapped() {
               return run;
            }
        };
    }

    public static PlanHistoryWrapper<PlanDescription> getWrapper(final PlanDescription desc) {
        return new PlanHistoryWrapper<PlanDescription>() {

            @Override
            public Date getUpdateDate() {
               return desc.getCreated();
            }

            @Override
            public User getUpdateAuthor() {
                try {
                    return desc.getUpdateAuthor();
                } catch (PortalException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (SystemException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                return null;
            }

            @Override
            public long getUpdateVersion() {
               return desc.getId();
            }

            @Override
            public PlanDescription getWrapped() {
                return desc;
            }
        };
    }

   
}
