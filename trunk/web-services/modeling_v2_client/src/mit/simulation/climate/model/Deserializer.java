package mit.simulation.climate.model;

import java.io.InputStream;

/**
 * @author: jintrone
 * @date: May 19, 2010
 */
public interface Deserializer<T> {
    public T deserialize(InputStream stream); 
}
