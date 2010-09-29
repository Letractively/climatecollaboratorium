package mit.simulation.climate.client.comm;

import mit.simulation.climate.client.HasId;
import mit.simulation.climate.client.model.jaxb.ClientJaxbReference;

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

                 
        } else if ("hashCode".equals(method.getName())) {
            if (loadedObject!=null) {
                return loadedObject.hashCode();
            } else {
                return proxiedHashCode();
            }
        } else if ("equals".equals(method.getName())) {
            if (loadedObject!=null) {
                return loadedObject.equals(args[0]);
            } else {
                return proxiedEquals(args[0]);
            }


        } else {
            T object = getObject();
            return method.invoke(object, args);
        }

    }

    public int proxiedHashCode() {
        return (clz.hashCode() * refid.hashCode())%13;
    }

    public boolean proxiedEquals(Object o) {
        return (clz.isInstance(o) && ((HasId)o).getId().equals(refid));
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