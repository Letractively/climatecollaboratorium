/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans.attributes;

import com.ext.portlet.models.CollaboratoriumModelingService;
import com.ext.portlet.models.ModelingServiceClient;
import com.ext.portlet.plans.model.PlanItem;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.util.PropsUtil;
import com.mchange.v2.codegen.bean.BeangenUtils;

import mit.simulation.climate.client.MetaData;
import mit.simulation.climate.client.Scenario;
import mit.simulation.climate.client.Simulation;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.core.ParameterNameDiscoverer;


/**
 * @author: jintrone
 * @date: Mar 1, 2010
 */
public class AttributeFunctionFactory {

    private ModelingServiceClient client = null;

    private static Log _log = LogFactoryUtil.getLog(AttributeFunctionFactory.class);

    public static AttributeFunctionFactory getFromEnvironment() {
        // FIXME
        /*
        String host = PropsUtil.get("climatecollaboratorium.model.server");
        int port = Integer.parseInt(PropsUtil.get("climatecollaboratorium.model.port"));
        */
        String host = "localhost";
        int port = 8080;
        _log.info("Modeling server from properties is "+host+":"+port);
        return new AttributeFunctionFactory(host,port);
    }


    public AttributeFunctionFactory(String hostname, int port) {
        try {
            client = new ModelingServiceClient(hostname, port);
        } catch (SystemException e) {
            throw new RuntimeException("An error occurred when creating modeling service client", e);
        }
    }

    public <T extends Number> AttributeFunction<T> getMaxValueFunction(final String variableId, final Class<T> resultType) {

        return new AttributeFunction<T>() {
            public T process(String scenarioId) throws SystemException {
                List<ModelingServiceClient.IndexedEntry<String, String>> data = null;
                data = getDataWithInternalName(scenarioId, variableId);
                T result = null;
                for (ModelingServiceClient.IndexedEntry<String, String> ent : data) {
                    T candidate = null;
                    try {
                        candidate = parseStringAsType(ent.getValue(), resultType);
                    } catch (UnsupportedTypeOperation unsupportedTypeOperation) {
                        _log.error(unsupportedTypeOperation);
                        throw (new SystemException(unsupportedTypeOperation));
                    }
                    result = result == null || candidate.doubleValue() > result.doubleValue() ? candidate : result;
                }
                return result;

            }

            @Override
            public boolean isFromPlan() {
                return false;
            }

            @Override
            public boolean isFromScenario() {
                return true;
            }

            @Override
            public T process(PlanItem plan) throws SystemException {
                return process(plan.getScenarioId().toString());
            }
        };
    }

    public <T extends Number> AttributeFunction<T> getMinValueFunction(final String variableId, final Class<T> resultType) {

        return new AttributeFunction<T>() {
            public T process(String scenarioId) throws SystemException {
                List<ModelingServiceClient.IndexedEntry<String, String>> data = null;
                data = getDataWithInternalName(scenarioId, variableId);

                T result = null;
                for (ModelingServiceClient.IndexedEntry<String, String> ent : data) {
                    T candidate = null;
                    try {
                        candidate = parseStringAsType(ent.getValue(), resultType);
                    } catch (UnsupportedTypeOperation unsupportedTypeOperation) {
                        _log.error(unsupportedTypeOperation);
                        throw (new SystemException(unsupportedTypeOperation));
                    }
                    result = result == null || candidate.doubleValue() < result.doubleValue() ? candidate : result;
                }
                return result;

            }

            @Override
            public boolean isFromPlan() {
                return false;
            }

            @Override
            public boolean isFromScenario() {
                return true;
            }

            @Override
            public T process(PlanItem plan) throws SystemException {
                return process(plan.getScenarioId().toString());
            }
        };
    }

    public <T> AttributeFunction<T> getFirstValueFunction(final String variableId, final Class<T> resultType) {

        return new AttributeFunction<T>() {
            public T process(String scenarioId) throws SystemException {
                List<ModelingServiceClient.IndexedEntry<String, String>> data = null;
                data = getDataWithInternalName(scenarioId, variableId);

                String val = data.get(0).getValue();
                try {
                    return parseStringAsType(val, resultType);
                } catch (UnsupportedTypeOperation unsupportedTypeOperation) {
                    throw (new SystemException(unsupportedTypeOperation));
                }
            }

            @Override
            public boolean isFromPlan() {
                return false;
            }

            @Override
            public boolean isFromScenario() {
                return true;
            }

            @Override
            public T process(PlanItem plan) throws SystemException {
                throw new SystemException("This attribute function can be used to fetch scenario values only");
            }

        };
    }

    public <T> AttributeFunction<T> getLastValueFunction(final String variableId, final Class<T> resultType) {

        return new AttributeFunction<T>() {
            public T process(String scenarioId) throws SystemException {
                List<ModelingServiceClient.IndexedEntry<String, String>> data = null;

                data = getDataWithInternalName(scenarioId, variableId);

                String val = data.get(data.size() - 1).getValue();
                try {
                    return parseStringAsType(val, resultType);
                } catch (UnsupportedTypeOperation unsupportedTypeOperation) {
                    throw (new SystemException(unsupportedTypeOperation));
                }
            }

            @Override
            public boolean isFromPlan() {
                return false;
            }

            @Override
            public boolean isFromScenario() {
                return true;
            }

            @Override
            public T process(PlanItem plan) throws SystemException {
                return process(plan.getScenarioId().toString());
            }

        };
    }


    public List<ModelingServiceClient.IndexedEntry<String, String>> getData(String scenarioId, String variableId) throws SystemException {
        return client.extractIndexedVariableData(client.readScenario(scenarioId), variableId);

    }

    public List<ModelingServiceClient.IndexedEntry<String, String>> getDataWithInternalName(String scenarioId, String name) throws SystemException {

        Scenario s = null;
        try {
            s = CollaboratoriumModelingService.repository().getScenario(Long.parseLong(scenarioId));
        } catch (IOException e) {
            throw new SystemException(e);
        }
        Simulation sim = s.getSimulation();
        String variableid = null;

        for (MetaData md:sim.getInputs()) {
            if (name.equals(md.getInternalName())) {
                variableid = ""+md.getId();
                break;
            }
        }


        if (variableid == null) {
            for (MetaData md:sim.getOutputs()) {
                if (name.equals(md.getInternalName())) {
                variableid = ""+md.getId();
                break;
            }
            }
        }

        return client.extractIndexedVariableData(client.readScenario(scenarioId), variableid);

    }

    private static <T> T parseStringAsType(String input, Class<T> type) throws UnsupportedTypeOperation {
        if (type == Double.class) {
            return (T) Double.valueOf(input);
        } else if (type == Float.class) {
            return (T) Float.valueOf(input);
        } else if (type == Long.class) {
            return (T) Long.valueOf(input);
        } else if (type == Integer.class) {
            return (T) Integer.valueOf(input);
        } else if (type == Short.class) {
            return (T) Short.valueOf(input);
        } else if (type == String.class) {
            return (T) input;
        } else {
            throw new UnsupportedTypeOperation("Type conversation not supported for numeric class: " + type);
        }
        

    }
    
    public <T extends Number> AttributeFunction<T> getMinFromLastValuesFunction(final String[] variableIds, final Class<T> resultType) {
        return new AttributeFunction<T>() {
            @Override
            public T process(String scenarioId) throws SystemException {
                T result = null;
                for(String variableId: variableIds) {
                    T candidate = getLastValueFunction(variableId, resultType).process(scenarioId);
                    result = result == null || result.doubleValue() > candidate.doubleValue() ? candidate : result;
                }
                return result;
            }

            @Override
            public boolean isFromScenario() {
                return true;
            }

            @Override
            public boolean isFromPlan() {
                return false;
            }

            @Override
            public T process(PlanItem plan) throws SystemException {
                return process(plan.getScenarioId().toString());
            }
        };
    }
    
    public <T extends Number> AttributeFunction<T> getMaxFromLastValuesFunction(final String[] variableIds, final Class<T> resultType) {
        return new AttributeFunction<T>() {
            @Override 
            public T process(String scenarioId) throws SystemException {
                T result = null;
                for(String variableId: variableIds) {
                    T candidate = getLastValueFunction(variableId, resultType).process(scenarioId);
                    result = result == null || result.doubleValue() < candidate.doubleValue() ? candidate : result;
                }
                return result;
            }

            @Override
            public boolean isFromScenario() {
                return true;
            }

            @Override
            public boolean isFromPlan() {
                return false;
            }

            @Override
            public T process(PlanItem plan) throws SystemException {
                return process(plan.getScenarioId().toString());
            }
        };
    }
    
    public <T> AttributeFunction<T> getPlanPropertyFunction(final String propertyName) {
        return new AttributeFunction<T>() {

            @Override
            public boolean isFromPlan() {
                return true;
            }

            @Override
            public boolean isFromScenario() {
                return false;
            }

            @Override
            public T process(String scenarioId) throws SystemException {
                throw new SystemException("This attribute function can be used to fetch value of plan property only");
            }

            @Override
            public T process(PlanItem plan) throws SystemException {
                String getterMethod = "get" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
                try {
                    return (T)BeanUtils.getProperty(plan, propertyName);
                }
                catch (Throwable e) {
                    throw new SystemException("Illegal property: " + propertyName, e);
                }
            }
            
        };
        
    }



    public static void main(String[] args) throws SystemException {
        AttributeFunctionFactory factory = new AttributeFunctionFactory("127.0.0.1", 8080);
        System.err.println(factory.getMaxValueFunction("242", Double.class).process("2900"));
        System.err.println(factory.getMinValueFunction("242", Double.class).process("2900"));
        System.err.println(factory.getFirstValueFunction("242", Double.class).process("2900"));
        System.err.println(factory.getLastValueFunction("242", Double.class).process("2900"));

    }


}
