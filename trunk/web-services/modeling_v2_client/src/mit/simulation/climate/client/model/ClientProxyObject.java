package mit.simulation.climate.client.model;

import mit.simulation.climate.client.HasId;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author: jintrone
 * @date: May 18, 2010
 */
public class ClientProxyObject<T> implements InvocationHandler {


    private T loadedObject = null;
    private ClientJaxbReference ref;
    private Long refid;
    private Class<T> clz;

    public ClientProxyObject(ClientJaxbReference ref) {

      this.clz = ClientRepository.type(ref);
      this.ref = ref;
      this.refid = Long.parseLong(ref.id);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("getId".equals(method.getName())) {
            return refid;

            //This is so dramatically bad it makes my teeth hurt.  Why is this here? I'll tell you.
            //Somehow, somewhere, JAXB is calling hashcode on the object during unmarshalling - which in turn
            //would cause the proxy object to try to get the actual object, thus defeating the purpose of delaying
            //reference resolution until after everything has been unmarshalled.  So, the horribly incorrect quick
            //swap.  Just don't try to stick the proxy in a map (or a set for that matter)!  Which you shouldn't be doing anyways.
            
        } else if ("hashCode".equals(method.getName())) {
            if (loadedObject!=null) {
                return loadedObject.hashCode();
            } else {
                return proxiedHashCode();
            }
        } else if ("equals".equals(method.getName())) {
            Object o = args[0];
            return (o.getClass().isAssignableFrom(clz) && ((HasId)o).getId().equals(refid));
        } else {
            T object = getObject();
            return method.invoke(object, args);
        }

    }

    public int proxiedHashCode() {
        return ref.hashCode() * 13 +7;
    }

    private synchronized T getObject()
    {
      if(null == loadedObject)
      {
           ClientRepository repo = ClientRepository.instance();
        if (repo ==null) {
            throw new RuntimeException("Client repository must be initialized prior to unmarshalling");
        }
        loadedObject = repo.resolveReference(ref, clz);
      }
      return loadedObject;
    }
}
