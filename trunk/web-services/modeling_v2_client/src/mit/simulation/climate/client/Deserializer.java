package mit.simulation.climate.client;

import java.io.InputStream;

/**
 * @author: jintrone
 * @date: May 19, 2010
 */
public interface Deserializer<T> {
    public T deserialize(InputStream stream); 
}
