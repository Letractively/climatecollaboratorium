/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans.attributes;

import com.ext.portlet.models.ModelingServiceClient;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.util.PropsUtil;

import java.util.List;


/**
 * @author: jintrone
 * @date: Mar 1, 2010
 */
public class AttributeFunctionFactory {

    private ModelingServiceClient client = null;

    private static Log _log = LogFactoryUtil.getLog(AttributeFunctionFactory.class);

    public static AttributeFunctionFactory getFromEnvironment() {
        String host = PropsUtil.get("climatecollaboratorium.model.server");
        int port = Integer.parseInt(PropsUtil.get("climatecollaboratorium.model.port"));
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
                data = getData(scenarioId, variableId);
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
        };
    }

    public <T extends Number> AttributeFunction<T> getMinValueFunction(final String variableId, final Class<T> resultType) {

        return new AttributeFunction<T>() {
            public T process(String scenarioId) throws SystemException {
                List<ModelingServiceClient.IndexedEntry<String, String>> data = null;
                data = getData(scenarioId, variableId);

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
        };
    }

    public <T> AttributeFunction<T> getFirstValueFunction(final String variableId, final Class<T> resultType) {

        return new AttributeFunction<T>() {
            public T process(String scenarioId) throws SystemException {
                List<ModelingServiceClient.IndexedEntry<String, String>> data = null;
                data = getData(scenarioId, variableId);

                String val = data.get(0).getValue();
                try {
                    return parseStringAsType(val, resultType);
                } catch (UnsupportedTypeOperation unsupportedTypeOperation) {
                    throw (new SystemException(unsupportedTypeOperation));
                }
            }

        };
    }

    public <T> AttributeFunction<T> getLastValueFunction(final String variableId, final Class<T> resultType) {

        return new AttributeFunction<T>() {
            public T process(String scenarioId) throws SystemException {
                List<ModelingServiceClient.IndexedEntry<String, String>> data = null;

                data = getData(scenarioId, variableId);

                String val = data.get(data.size() - 1).getValue();
                try {
                    return parseStringAsType(val, resultType);
                } catch (UnsupportedTypeOperation unsupportedTypeOperation) {
                    throw (new SystemException(unsupportedTypeOperation));
                }
            }

        };
    }


    public List<ModelingServiceClient.IndexedEntry<String, String>> getData(String scenarioId, String variableId) throws SystemException {
        return client.extractIndexedVariableData(client.readScenario(scenarioId), variableId);

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
