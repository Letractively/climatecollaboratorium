/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.util.PropsUtil;
import com.liferay.util.portlet.PortletProps;

import mit.simulation.climate.client.Simulation;
import mit.simulation.climate.client.comm.ClientRepository;

import java.io.IOException;

/**
 * @author: jintrone
 * @date: May 21, 2010
 */
public class CollaboratoriumModelingService {

    private static ClientRepository instance;

    private static Log _log = LogFactoryUtil.getLog(CollaboratoriumModelingService.class);

    public static ClientRepository repository() throws SystemException {
        if (instance == null) {

            // try to read configuration from default location (portal-ext.properties)
            String host = PropsUtil.get("climatecollaboratorium.model.server");
            int port = 0;
            if (host != null) {
                port = Integer.parseInt(PropsUtil.get("climatecollaboratorium.model.port"));
                
            } else {
                // if configuration isn't available try to load it from portlet preferences
                host = PortletProps.get("climatecollaboratorium.model.server");
                port = Integer.parseInt(PortletProps.get("climatecollaboratorium.model.port"));
            }
             _log.info("Starting up modeling client ("+host+":"+port+")");
            try {
                instance = ClientRepository.instance(host, port);

                for (Simulation s : ClientRepository.instance().getAllSimulations()) {
                    _log.info("Loaded... " + s.getName());
                }
                _log.info("Modeling client initialized");
            } catch (IOException e) {
                _log.error(e);
                throw new SystemException("Error initializing modeling service client");
            }

        }
        return instance;
    }
}
