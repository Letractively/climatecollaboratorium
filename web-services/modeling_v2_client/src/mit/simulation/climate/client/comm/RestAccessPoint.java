package mit.simulation.climate.client.comm;

import java.net.InetAddress;

/**
 * @author: jintrone
 * @date: May 19, 2010
 */
public interface RestAccessPoint {

     public String create(InetAddress base, int port, String... params);
}
