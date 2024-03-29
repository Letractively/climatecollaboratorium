package mit.simulation.climate.model.persistence;

import java.util.List;

import org.apache.log4j.Logger;

import mit.simulation.climate.dao.MetaDataDAO;
import mit.simulation.climate.dao.MetaDataDefaultsDAO;
import mit.simulation.climate.model.MetaData;

public class ServerMetaData extends ServerObject<MetaDataDAO> implements
		MetaData {

	Logger log = Logger.getLogger(ServerMetaData.class);

	public ServerMetaData(MetaDataDAO dao) {
		super(dao);
	}

	public ServerMetaData(String printname, String internalname,
			String description, VarType vtype, VarContext t, String[] labels, String[] units,
			Class<Object>[] profile, String[] min, String[] max,
			String[] categories, String[] defaults, String external) {
		super();
		MetaDataDefaultsDAO defaultsdao = ServerRepository.instance().createDAO(MetaDataDefaultsDAO.class);
		dao.setMetaToDefaults(defaultsdao);
		setName(printname);
		setInternalName(internalname);
		setDescription(description);
		setVarType(vtype);
		setVarContext(t);
		setLabels(labels);
		setProfile(profile);
		setUnits(units);
		if (min != null) {
			setMin(min);
		}
		if (max != null) {
			setMax(max);
		}
		if (defaults != null) {
			setDefault(defaults);
		}

		if (external != null) {
			setExternalInfo(external);
		}




	}

	@Override
	public String getId() {
		return dao.getId();
	}

	@Override
	public String getInternalName() {
		return dao.getInternalname();
	}

	@Override
	public void setInternalName(String name) {
		dao.setInternalname(name);
	}

	@Override
	public String getName() {
		return dao.getName();
	}

	@Override
	public void setName(String name) {
		dao.setName(name);
	}

	public void setExternalInfo(String url) {
		dao.setExternal(url);
	}

	public String getExternalInfo() {
		return dao.getExternal();
	}

	@Override
	public Class<Object>[] getProfile() {
			try {
				return new Class[] {Class.forName(dao.getProfile())};
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
//		String[] sprofile = dao.getProfile().split(";");
//		Class<Object>[] result = new Class[sprofile.length];
//		int i = 0;
//		for (String s : sprofile) {
//			try {
//				result[i++] = (Class<Object>) Class.forName(s);
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		return result;
	}

	@Override
	public void setProfile(Class<Object>[] profile) {
//		String result = "";
//		for (Class c : profile) {
//			if (result.length() > 0)
//				result += ";";
//			result += c.getCanonicalName();

//		}
		dao.setProfile(profile[0].getCanonicalName());
	}

	public String[] getLabels() {
		if (dao.getLabels() == null) {
			return null;
		}
		String[] labels = dao.getLabels().split(";");
		return labels;
	}

	public void setLabels(String[] labels) {
		String slabels = "";
		String sep = "";
		for (String l : labels) {
			slabels += (sep + l);
			sep = ";";
		}
		dao.setLabels(slabels);

	}

	@Override
	public String[] getUnits() {
		if (dao.getUnits() == null) {
			return null;
		}
		return new String[] {dao.getUnits()};
	}

	@Override
	public void setUnits(String[] units) {
		String slabels = "";
		String sep = "";
		for (String l : units) {
			slabels += (sep + l);
			sep = ";";
		}
		dao.setUnits(slabels);
	}

	@Override
	public String getDescription() {
		return dao.getDescription();
	}

	@Override
	public void setDescription(String desc) {
		dao.setDescription(desc);
	}

	@Override
	public VarContext getVarContext() {
		return VarContext.valueOf(dao.getVarcontext());
	}

	@Override
	public void setVarContext(VarContext t) {
		dao.setVarcontext(t.toString());

	}

	@Override
	public String toString() {
		return getName() + ":" + dao.getProfile();
	}

	@Override
	public String[] getDefault() {
		return dao.getMetaToDefaults().getDefault()==null?null:new String[] {dao.getMetaToDefaults().getDefault()};

	}

	@Override
	public void setDefault(String[] n) {
		dao.getMetaToDefaults().setDefault(n[0]);

	}

	@Override
	public String[] getMax() {
		return dao.getMetaToDefaults().getMax()==null?null:new String[] {dao.getMetaToDefaults().getMax()};
	}

	@Override
	public String[] getMin() {
		return dao.getMetaToDefaults().getMin()==null?null:new String[] {dao.getMetaToDefaults().getMin()};
	}


	// new edit
	@Override
	public void setMax(String[] n) {
		dao.getMetaToDefaults().setMax(n[0]);

	}

	// new edit
	@Override
	public void setMin(String[] n) {
		dao.getMetaToDefaults().setMin(n[0]);
	}

	public boolean equals(Object o) {
		return ((o instanceof ServerMetaData) && dao
				.equals(((ServerMetaData) o).dao));
	}

	@Override
	public boolean isIndex() {
		return VarContext.INDEX.equals(getVarContext());
	}



	@Override
	public String[] getCategories() {
		if (dao.getMetaToDefaults() == null) log.error("Warning - no meta data defaults for "+dao.getId());
		return dao.getMetaToDefaults().getCategories()==null?null:dao.getMetaToDefaults().getCategories().split(";");
	}

	@Override
	public VarType getVarType() {
		return MetaData.VarType.valueOf(dao.getVartype());
	}

	@Override
	public void setCategories(String[] categories) {
		StringBuffer b = new StringBuffer();
		String sep = "";
		for (String c:categories) {
			b.append(sep);
			b.append(c);
			sep = ";";
		}
		dao.getMetaToDefaults().setCategories(b.toString());

	}



	@Override
	public void setVarType(VarType t) {
		dao.setVartype(t.toString());
	}

	@Override
	public MetaData getIndexingMetaData() {
		return ServerRepository.instance().get(dao.getToIndexingId());
	}

	@Override
	public void setIndexingMetaData(MetaData md) {
		dao.setToIndexingId(((ServerMetaData)md).dao);

	}





}
