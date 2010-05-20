package mit.simulation.climate.client.model;

import java.net.InetAddress;

/**
 * @author: jintrone
 * @date: May 19, 2010
 */
public interface RestAccessPoint {

     public String create(InetAddress base, int port, String... params);
}
