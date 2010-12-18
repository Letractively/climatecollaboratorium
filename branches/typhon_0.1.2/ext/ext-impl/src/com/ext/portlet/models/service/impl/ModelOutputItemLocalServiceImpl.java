/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.service.impl;

import com.ext.portlet.models.NoSuchModelOutputItemException;
import com.ext.portlet.models.model.ModelOutputItem;
import com.ext.portlet.models.service.base.ModelOutputItemLocalServiceBaseImpl;
import com.liferay.portal.SystemException;
import mit.simulation.climate.client.MetaData;


public class ModelOutputItemLocalServiceImpl
    extends ModelOutputItemLocalServiceBaseImpl {


    public ModelOutputItem getOutputItem(MetaData md) throws SystemException, NoSuchModelOutputItemException {
        return modelOutputItemPersistence.findByModelOutputId(md.getId());
    }

}
