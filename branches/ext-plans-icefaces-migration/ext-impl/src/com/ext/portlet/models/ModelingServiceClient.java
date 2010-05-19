/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models;


import com.liferay.portal.SystemException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.xml.sax.InputSource;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for accessing back end modeling service
 *
 *
 * @author: jintrone
 * @date: Feb 28, 2010
 */
public class ModelingServiceClient {

    private static String INDEXED_DATA_VAL_PATTERN = "\\[([^,]+),([^\\]]+)\\]";

    private static String SCALAR_DATA_VAL_PATTERN = "\\[([^\\]]+)\\]";

    public static enum XPathExpression {
        OUTPUT_DATA("/payload/scenarios/scenario/outputs/variable/metadata[id='%s']/../values/data");

        String template;

        XPathExpression(String template) {
            this.template = template;
        }

        public String create(String... params) {
            return String.format(template,(Object[])params);
        }
    }

    public static enum RestAccessPoint {
        RUN_MODEL_URL("/simulation-servlet/rest/runsim"),
        SAVE_SCENARIO_URL("/simulation-servlet/rest/scenario"),
        READ_SCENARIO_URL("/simulation-servlet/rest/scenario");

        private String url;

        RestAccessPoint(String url) {
            this.url = url;
        }

        public String create(InetAddress base, int port, String... params) {
            StringBuffer buf = new StringBuffer("http://");
            buf.append(base.getHostName());
            buf.append(":");
            buf.append(port);
            buf.append(url);
            for (String p:params) {
                buf.append("/");
                buf.append(p);
            }
            return buf.toString();
        }
    };




    private HttpClient client = null;

    private InetAddress serverAddress = null;

    private int port = 80;

    public ModelingServiceClient(String hostname, int port) throws SystemException {
        try {
           serverAddress = InetAddress.getByName(hostname);
        } catch (UnknownHostException e) {
            throw new SystemException(e);
        }
        this.port = port;
        client = new HttpClient();
    }

    public ModelingServiceClient(String hostname) throws SystemException {
        this(hostname,80);
    }

    /**
     * Runs a model on the server and returns the raw XML Document of the resultant scenario
     *
     * @param modelId The id of the model to be run
     * @param params Input parameters to be posted to the service
     * @return The raw xml of the of the scenario
     * @throws SystemException
     */
    public String runModel(String modelId, Map<String,String> params) throws SystemException {

        PostMethod post = new PostMethod(RestAccessPoint.RUN_MODEL_URL.create(serverAddress,port,modelId));
        NameValuePair[] paramList = new NameValuePair[params.size()];
        String response = null;

        int i = 0;
        for (Map.Entry<String,String> ent:params.entrySet()) {
           paramList[i++] = new NameValuePair(ent.getKey(),ent.getValue());
        }
        post.setRequestBody(paramList);
        post.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
        try {
            client.executeMethod(post);
            byte[] responsebody = post.getResponseBody();
			response = new String(responsebody);
        } catch (HttpException e) {
            throw new SystemException(e);
        } catch (IOException e) {
            throw new SystemException(e);
        } finally {
            post.releaseConnection();
        }
        return response;
    }

    /**
     * Reads a scenario from the server and returns the raw XML Document
     *
     * @param scenarioId The id of the scenario to read
     * @return The raw xml of the of the scenario
     * @throws SystemException
     */
    public String readScenario(String scenarioId) throws SystemException {
       GetMethod get = new GetMethod(RestAccessPoint.READ_SCENARIO_URL.create(serverAddress,port,scenarioId));
        String response = null;
        try {
            client.executeMethod(get);
            byte[] responsebody = get.getResponseBody();
			response = new String(responsebody);
        } catch (HttpException e) {
            throw new SystemException(e);
        } catch (IOException e) {
            throw new SystemException(e);
        } finally {
            get.releaseConnection();
        }
        return response;
    }

    /**
     * Extract the raw text of the "data" element from a scenario document for a given piece of metadata
     *
     * @param xml The raw xml returned by the modeling service for a scenario
     * @param variableId The id of a piece of metadata associated with this scenario
     * @return The raw text of the data element contained in the variable associated with the given metadata
     * @throws SystemException
     */
    public String extractRawDataString(String xml, String variableId) throws SystemException {
        String datastring = null;
               try {
                   XPath xp = XPathFactory.newInstance().newXPath();
                   datastring =  xp.evaluate(XPathExpression.OUTPUT_DATA.create(variableId),new InputSource(new StringReader(xml)));

              } catch (XPathExpressionException e) {
                   throw new SystemException(e);
               }

        return datastring;

    }

    /**
     * Extract the data from a scenario as a list of {@link IndexedEntry} elements
     *
     * @param xml The raw xml returned by the modeling service for a scenario
     * @param variableId The id of a piece of metadata associated with this scenario
     * @return A {@link java.util.List} of {@link IndexedEntry}
     * @throws SystemException
     */
    public List<IndexedEntry<String,String>> extractIndexedVariableData(String xml, String variableId) throws SystemException {
        String datastring = extractRawDataString(xml,variableId);
        datastring = datastring.substring(1, datastring.length() - 1);
		Pattern p = Pattern.compile(INDEXED_DATA_VAL_PATTERN);
		Matcher m = p.matcher(datastring);

        List<IndexedEntry<String,String>> result = new ArrayList<IndexedEntry<String,String>>();

        while (m.find()) {
            final String idx = m.group(1);
            final String val = m.group(2);
			result.add(new IndexedEntry<String,String>() {

                public String getIndex() {
                    return idx;
                }

                public String getValue() {
                    return val;
                }


            });

		}
        return result;

    }
    /**
     * Extract the data from a scenario as a list of elements
     *
     * @param xml The raw xml returned by the modeling service for a scenario
     * @param variableId The id of a piece of metadata associated with this scenario
     * @return A {@link java.util.List} of elements
     * @throws SystemException
     */
    public List<String> extractVariableData(String xml, String variableId) throws SystemException {

        String datastring = extractRawDataString(xml,variableId);
        datastring = datastring.substring(1, datastring.length() - 1);
		Pattern p = Pattern.compile(SCALAR_DATA_VAL_PATTERN);
		Matcher m = p.matcher(datastring);

        List<String> result = new ArrayList<String>();

        while (m.find()) {
			result.add(m.group(1));
		}
        return result;

    }



    public static abstract class IndexedEntry<I,V> {

        public abstract I getIndex();
        public abstract V getValue();

        public String toString() {
            return "["+getIndex()+"="+getValue()+"]";
        }


    }

    public static void main(String[] s) throws SystemException {
        ModelingServiceClient client = new ModelingServiceClient("127.0.0.1",8080);
        System.err.println(client.extractIndexedVariableData(client.readScenario("2900"),"242"));
    }



}
