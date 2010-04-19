package mit.simulation.climate.model.persistence;


import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

import org.apache.cayenne.CayenneDataObject;

public abstract class ServerObject <T extends CayenneDataObject> {

	public final T dao;


	public ServerObject() {
		Class<T> cls = null;
		try {
			ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
			cls = (Class<T>) type.getActualTypeArguments()[0];
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao =  (T)ServerRepository.instance().createDAO(cls);
	}

	public ServerObject(T dao) {
		this.dao = dao;
	}

	public T getDataObject() {
		return dao;
	}
	
	public int hashCode() {
		return (dao.hashCode()*7)+13;
	}



}
