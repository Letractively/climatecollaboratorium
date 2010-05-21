/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.modeling;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.util.PropsUtil;
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

            String host = PropsUtil.get("climatecollaboratorium.model.server");
            int port = Integer.parseInt(PropsUtil.get("climatecollaboratorium.model.port"));
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
