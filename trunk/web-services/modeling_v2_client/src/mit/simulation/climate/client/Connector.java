package mit.simulation.climate.client;


import mit.simulation.climate.client.model.RestAccessPoint;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * @author: jintrone
 * @date: May 19, 2010
 */
public class Connector <T> {

/**
 * Utility class for accessing web service
 *
 *
 * @author: jintrone
 * @date: Feb 28, 2010
 */



    private HttpClient client = null;

    private InetAddress serverAddress = null;

    private int port = 80;

    private Deserializer<T> deserializer;

    public Connector(Deserializer<T> deserializer, String hostname, int port) throws UnknownHostException {

        serverAddress = InetAddress.getByName(hostname);
        this.port = port;
        this.deserializer = deserializer;
        client = new HttpClient();
    }

    public Connector(Deserializer<T> deserializer, String hostname) throws UnknownHostException {
        this(deserializer, hostname,80);
    }

    
    /**
     * Runs a model on the server and returns the raw XML Document of the resultant scenario
     *
     * @param postparams Input parameters to be posted to the service
     * @return The raw xml of the of the scenario
     *
     */
    public T post(RestAccessPoint location, Map<String,String> postparams, String... pathparam) throws IOException {

        PostMethod post = new PostMethod(location.create(serverAddress,port,pathparam));
        //post.setFollowRedirects(true);
        if (postparams!=null && postparams.size() > 0) {
        NameValuePair[] paramList = new NameValuePair[postparams.size()];
        int i = 0;
        for (Map.Entry<String,String> ent:postparams.entrySet()) {
           paramList[i++] = new NameValuePair(ent.getKey(),ent.getValue());
        }
        post.setRequestBody(paramList);
        }
        post.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
        try {
            client.executeMethod(post);
            //Manually handle redirect, since automatic handling is not allowed
            if (post.getStatusCode() == 303) {
                return rawGet(post.getResponseHeader("location").getValue(),null);
            } else {
                return deserializer.deserialize(post.getResponseBodyAsStream());
            }

        } finally {
           post.releaseConnection();
        }

    }

    public T get(RestAccessPoint location, Map<String,String> queryparams, String... pathparam) throws IOException {
        return rawGet(location.create(serverAddress,port,pathparam),queryparams);
    }

    private T rawGet(String location, Map<String,String> queryparams) throws IOException {
        GetMethod get = new GetMethod(location);
        if (queryparams!=null && queryparams.size() > 0) {
        NameValuePair[] paramList = new NameValuePair[queryparams.size()];

        int i = 0;
        for (Map.Entry<String,String> ent:queryparams.entrySet()) {
           paramList[i++] = new NameValuePair(ent.getKey(),ent.getValue());
        }
        get.setQueryString(paramList);
        }
        get.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
        try {
            client.executeMethod(get);
            return deserializer.deserialize(get.getResponseBodyAsStream());

        } finally {
            get.releaseConnection();
        }
    }



}
