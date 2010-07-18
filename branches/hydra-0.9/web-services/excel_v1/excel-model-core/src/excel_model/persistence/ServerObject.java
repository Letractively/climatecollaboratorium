package excel_model.persistence;


import java.lang.reflect.ParameterizedType;

import org.apache.cayenne.CayenneDataObject;

public abstract class ServerObject <T extends CayenneDataObject> {

	public final T dao;


	public ServerObject() {
		Class<T> cls = null;
		try {
			Class ladder = this.getClass();
			while (ladder!= null && !(ladder.getGenericSuperclass() instanceof ParameterizedType)) {
				ladder = ladder.getSuperclass();
			}
			if (ladder == null) throw new IllegalArgumentException("Could not identify generic type");
			ParameterizedType type = (ParameterizedType) ladder.getGenericSuperclass();
			cls = (Class<T>) type.getActualTypeArguments()[0];
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao =  ServerRepository.instance().createDAO(cls);
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
