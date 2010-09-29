/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans.attributes;

import com.ext.portlet.community.tags.ClimateUserTag;
import com.ext.portlet.models.CollaboratoriumModelingService;
import com.ext.portlet.models.ui.*;
import com.ext.portlet.plans.model.PlanItem;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import mit.simulation.climate.client.Scenario;
import mit.simulation.climate.client.Tuple;
import mit.simulation.climate.client.TupleStatus;
import mit.simulation.climate.client.Variable;
import mit.simulation.climate.client.model.impl.ClientScenario;
import mit.simulation.climate.client.model.impl.ClientVariable;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;


/**
 * @author: jintrone
 * @date: Mar 1, 2010
 */
public class AttributeFunctionFactory {


    private static Log _log = LogFactoryUtil.getLog(AttributeFunctionFactory.class);


    public <T extends Number> AttributeFunction<T> getMaxValueFunction(final String variableId, final Class<T> resultType) {

        return new ScenarioAttributeFunction<T>() {
            public T _process(String scenarioId) throws SystemException {
                Variable data = getVariableFromInternalName(scenarioId, variableId);

                T result = null;
                for (Tuple ent : data.getValue()) {
                    T candidate = null;
                    try {
                        candidate = parseStringAsType(ent.getValues()[1], resultType);
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

        return new ScenarioAttributeFunction<T>() {
            public T _process(String scenarioId) throws SystemException {
                Variable data = getVariableFromInternalName(scenarioId, variableId);

                T result = null;
                for (Tuple ent : data.getValue()) {
                    T candidate = null;
                    try {
                        candidate = parseStringAsType(ent.getValues()[1], resultType);
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

        return new ScenarioAttributeFunction<T>() {
            public T _process(String scenarioId) throws SystemException {
                Variable v = getVariableFromInternalName(scenarioId, variableId);

                String val = v.getValue().get(0).getValues()[1];
                try {
                    return parseStringAsType(val, resultType);
                } catch (UnsupportedTypeOperation unsupportedTypeOperation) {
                    throw (new SystemException(unsupportedTypeOperation));
                }
            }


            @Override
            public T process(PlanItem plan) throws SystemException {
                throw new SystemException("This attribute function can be used to fetch scenario values only");
            }

        };
    }

    public <T> AttributeFunction<T> getLastValueFunction(final String variableId, final Class<T> resultType) {

        return new ScenarioAttributeFunction<T>() {
            public T _process(String scenarioId) throws SystemException {
                 Variable v = getVariableFromInternalName(scenarioId, variableId);

                List<Tuple> tuples = v.getValue();
                if (tuples.size() == 0) {
                    return null;
                }
                String val = null;
                if (tuples.get(tuples.size() - 1).getValues().length > 1) {
                    val = tuples.get(tuples.size() - 1).getValues()[1];
                }
                try {
                    return parseStringAsType(val, resultType);
                } catch (UnsupportedTypeOperation unsupportedTypeOperation) {
                    throw (new SystemException(unsupportedTypeOperation));
                }
            }



        };
    }




    public Variable getVariableFromInternalName(String scenarioId, String name) throws SystemException {

        ClientScenario s = null;
        try {
            s = (ClientScenario) CollaboratoriumModelingService.repository().getScenario(Long.parseLong(scenarioId));
        } catch (IOException e) {
            throw new SystemException(e);
        }
        return s.getVariableForInternalname(name);
    }

    private static <T> T parseStringAsType(String input, Class<T> type) throws UnsupportedTypeOperation {
        if (input == null || input.equals("@ERROR") || input.equals("@RANGE")) {
            return null;
        }
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
    
    public <T extends Number> AttributeFunction<T> getMinFromLastValuesFunction(final String[] variableNames, final Class<T> resultType) {
        return new ScenarioAttributeFunction<T>() {
            @Override
            public T _process(String scenarioId) throws SystemException {
                T result = null;
                ClientScenario s = null;
                try {
                    s = (ClientScenario) CollaboratoriumModelingService.repository().getScenario(Long.parseLong(scenarioId));
                } catch (IOException e) {
                    throw new SystemException(e);
                }


                for(String variableName: variableNames) {
                    ClientVariable v = (ClientVariable) s.getVariableForInternalname(variableName);
                    if (!v.hasError(TupleStatus.INVALID)) {
                        T candidate = getLastValueFunction(variableName, resultType).process(scenarioId);
                        result = result == null || result.doubleValue() > candidate.doubleValue() ? candidate : result;
                    }
                }
                return result;
            }


        };
    }
    
    public <T extends Number> AttributeFunction<T> getMaxFromLastValuesFunction(final String[] variableNames, final Class<T> resultType) {
        return new ScenarioAttributeFunction<T>() {
            @Override 
            public T _process(String scenarioId) throws SystemException {
                T result = null;
                ClientScenario s = null;
                try {
                    s = (ClientScenario) CollaboratoriumModelingService.repository().getScenario(Long.parseLong(scenarioId));
                } catch (IOException e) {
                    throw new SystemException(e);
                }
                int outputsWithoutErrors = 0;
                for(String variableName: variableNames) {
                   ClientVariable v = (ClientVariable) s.getVariableForInternalname(variableName);
                    if (!v.hasError(TupleStatus.INVALID)) {
                        T candidate = getLastValueFunction(variableName, resultType).process(scenarioId);
                        result = candidate != null && (result == null || result.doubleValue() < candidate.doubleValue()) ? candidate : result;
                        outputsWithoutErrors++;
                    }
                }
                if (outputsWithoutErrors == 1) {
                    // value will be shown as a min, it doesn't need to be shown as a max value
                    return null;
                }
                return result;
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
                    Method getter = PlanItem.class.getMethod(getterMethod);
                    return (T)getter.invoke(plan);
                }
                catch (Throwable e) {
                    throw new SystemException("Illegal property: " + propertyName, e);
                }
            }
            
        };
        
    }

    public static abstract class ScenarioAttributeFunction<T> implements AttributeFunction<T> {

       public boolean isFromPlan() {
           return false;
       }

        public boolean isFromScenario() {
            return true;
        }

        public final T process(String scenarioId) throws SystemException{
            if (scenarioId==null) {
                return null;
            } else {
                return _process(scenarioId);
            }
        }

        public abstract T _process(String scenarioId) throws SystemException;


            public T process(PlanItem plan) throws SystemException {
                return process(plan.getScenarioId()==null?null:plan.getScenarioId().toString());
            }
    }
    

    public AttributeFunction<String> getIndexedOutputErrors(final String outputName) {
        return new ScenarioAttributeFunction<String>() {

            
            @Override
            public String _process(String scenarioId) throws SystemException {
                try {
                    Scenario scenario = CollaboratoriumModelingService.repository().getScenario(Long.parseLong(scenarioId));
                    ModelDisplay display = null;
                    try {
                        ModelUIFactory.getInstance().getDisplay(scenario);
                    } catch (IllegalUIConfigurationException e) {
                        _log.error(e);
                        return null;
                    }

                    StringBuilder errors = new StringBuilder();
                    for (ModelOutputDisplayItem item: display.getOutputs()) {
                        if (item.getName().trim().equals(outputName)) {
                            if (item.getDisplayItemType().equals(ModelOutputDisplayItemType.INDEXED)) {
                                ModelOutputIndexedDisplayItem indexedItem = (ModelOutputIndexedDisplayItem) item;
                                if (indexedItem.getSeriesWithInvalidError().size() > 0) {
                                    String msg = indexedItem.getInvalidErrorBehavior().getMessage();
                                    if (msg == null) {
                                        continue;
                                    }
                                    StringBuilder outputs = new StringBuilder();
                                    for (int i = 0; i < indexedItem.getSeriesWithInvalidError().size(); i++) {
                                        ModelOutputSeriesDisplayItem serieItem = indexedItem.getSeriesWithInvalidError().get(i);
                                        outputs.append(serieItem.getName());
                                        if (i < indexedItem.getSeriesWithInvalidError().size() - 1) {
                                            outputs.append(",");
                                        }
                                    }
                                    errors.append(msg.replaceAll("%outputs", outputs.toString()));
                                    errors.append("\n");
                                }
                                if (indexedItem.getSeriesWithOutOfRangeError().size() > 0) {
                                    String msg = indexedItem.getOutOfRangeErrorBehavior().getMessage();
                                    if (msg == null) {
                                        continue;
                                    }
                                    StringBuilder outputs = new StringBuilder();
                                    for (int i = 0; i < indexedItem.getSeriesWithOutOfRangeError().size() ; i++) {
                                        ModelOutputSeriesDisplayItem serieItem = indexedItem.getSeriesWithOutOfRangeError().get(i);
                                        outputs.append(serieItem.getName());
                                        if (i < indexedItem.getSeriesWithOutOfRangeError().size() - 1) {
                                            outputs.append(", ");
                                        }
                                    }
                                    errors.append(msg.replaceAll("%outputs", outputs.toString()));
                                    errors.append("\n");
                                }
                            }
                        }
                    }
                    return errors.toString();
                    
                } catch (NumberFormatException e) {
                    throw new SystemException(e);
                } catch (IOException e) {
                    throw new SystemException(e);
                }
            }
            

        };
    }
    

        



    public static void main(String[] args) throws SystemException {
        AttributeFunctionFactory factory = new AttributeFunctionFactory();
        System.err.println(factory.getMaxValueFunction("242", Double.class).process("2900"));
        System.err.println(factory.getMinValueFunction("242", Double.class).process("2900"));
        System.err.println(factory.getFirstValueFunction("242", Double.class).process("2900"));
        System.err.println(factory.getLastValueFunction("242", Double.class).process("2900"));

    }




}