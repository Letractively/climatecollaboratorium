package excel_model.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.DataObjectUtils;
import org.apache.cayenne.access.DataContext;
import org.apache.cayenne.exp.ExpressionFactory;
import org.apache.cayenne.query.SelectQuery;
import org.apache.log4j.Logger;

import cayenne_classes.ExcelModelDAO;
import excel_model.parser.ModelParse;

public class ServerRepository {

	private static Logger logger = Logger.getLogger(ServerRepository.class);

	private DataContext context;

	private static ServerRepository instance = null;


	private ServerRepository(DataContext context) {
		this.context = context;
	}

	public static ServerRepository instance() {
		if (instance == null) {
			instance = new ServerRepository(DataContext.createDataContext());
		}
		return instance;
	}

	public void commit() {
		context.commitChanges();
	}

	private static DataContext getContextCandidate() {
		DataContext result = null;
		try {
		    result = DataContext.getThreadDataContext();
		}
		catch(IllegalStateException ex) {
		    result = DataContext.createDataContext();
		    DataContext.bindThreadDataContext(result);
		}
		return result;
	}

	public DataContext getContext() {
		return context;
	}

	public <T>T createDAO(Class<T> cls) {
		T result = (T)context.newObject(cls);
		return result;
	}

	public <S>S findDAO(Class<S> cls, String id) {
		S result = DataObjectUtils.objectForPK(context, cls, id);
		return result;
	}

	public ExcelModelDAO findModelById(String id) {
		return findDAO(ExcelModelDAO.class,id);
	}


	public ExcelModelDAO findModelByName(String name) {
		SelectQuery query = new SelectQuery(ExcelModelDAO.class,ExpressionFactory.matchExp("name", name));
		List result = context.performQuery(query);
		if (result != null && result.size() > 0) {
			return (ExcelModelDAO)result.get(0);
		}
		return null;
	}

}