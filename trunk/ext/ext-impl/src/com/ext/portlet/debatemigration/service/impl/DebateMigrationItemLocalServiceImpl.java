package com.ext.portlet.debatemigration.service.impl;

import com.ext.portlet.debatemigration.model.DebateMigrationItem;
import com.ext.portlet.debatemigration.service.base.DebateMigrationItemLocalServiceBaseImpl;


public class DebateMigrationItemLocalServiceImpl
    extends DebateMigrationItemLocalServiceBaseImpl {


    public DebateMigrationItem findItemMigration(Long migrationId, Long mbmessageid) {
        try {
            return debateMigrationItemPersistence.findByDebateMigrationItem(migrationId,mbmessageid);
        } catch (Exception e) {
            return null;
        }
    }
}
