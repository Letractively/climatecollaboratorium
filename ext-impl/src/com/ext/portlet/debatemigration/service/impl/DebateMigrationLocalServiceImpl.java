package com.ext.portlet.debatemigration.service.impl;

import com.ext.portlet.debatemigration.model.DebateMigration;
import com.ext.portlet.debatemigration.service.base.DebateMigrationLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.SystemException;

import java.util.Date;


public class DebateMigrationLocalServiceImpl
    extends DebateMigrationLocalServiceBaseImpl {

    public DebateMigration addMigration() throws SystemException {
        long pk = CounterLocalServiceUtil.increment(DebateMigration.class.getName());
        DebateMigration migration =debateMigrationLocalService.createDebateMigration(pk);
        migration.setMigrationDate(new Date());
        debateMigrationLocalService.updateDebateMigration(migration);
        return migration;
    }

}
