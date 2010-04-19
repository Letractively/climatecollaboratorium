package mit.simulation.climate.model.persistence;

import java.util.List;

import org.apache.log4j.Logger;

import mit.simulation.climate.dao.MetaDataDAO;
import mit.simulation.climate.dao.MetaDataDefaultsDAO;
import mit.simulation.climate.model.MetaData;

public class ServerMetaData extends ServerObject<MetaDataDAO> implements MetaData {

	Logger log = Logger.getLogger(ServerMetaData.class);

	public ServerMetaData(MetaDataDAO dao) {
		super(dao);
	}

	public ServerMetaData(String printname, String internalname, String description, Type t, String[] labels, String[] units, Class<Number>[] profile, Number[] min, Number[] max, Number[] defaults, String external) {
		super();
		setName(printname);
		setInternalName(internalname);
		setDescription(description);
		setType(t);
		setLabels(labels);
		setProfile(profile);
		setUnits(units);
		if (min!=null) {
			setMin(min);
		}
		if (max!=null) {
			setMax(max);
		}
		if (defaults!=null) {
			setDefault(defaults);
		}
		
		if (external!=null) {
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
	public Class<Number>[] getProfile() {

		String[] sprofile = dao.getProfile().split(";");
		Class<Number>[] result = new Class[sprofile.length];
		int i = 0;
		for (String s:sprofile) {
			try {
				result[i++] = (Class<Number>)Class.forName(s);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public void setProfile(Class<Number>[] profile) {
		String result = "";
		for (Class c:profile) {
			if (result.length() > 0) result+=";";
			result+=c.getCanonicalName();
			MetaDataDefaultsDAO defaultsdao = ServerRepository.instance().createDAO(MetaDataDefaultsDAO.class);
			dao.addToMetaToDefaults(defaultsdao);
		}
		dao.setProfile(result);
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
		for (String l:labels) {
			slabels+=(sep+l);
			sep=";";
		}
		dao.setLabels(slabels);

	}


	@Override
	public String[] getUnits() {
		String[] units = dao.getUnits().split(";");
		return units;
	}

	@Override
	public void setUnits(String[] units) {
		String slabels = "";
		String sep = "";
		for (String l:units) {
			slabels+=(sep+l);
			sep=";";
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
	public Type getType() {
		return Type.valueOf(dao.getType());
	}

	@Override
	public void setType(Type t) {
		dao.setType(t.toString());

	}

	public String toString() {
		return getName()+":"+dao.getProfile();
	}

	@Override
	public Number[] getDefault() {

		List<MetaDataDefaultsDAO> defdaos = dao.getMetaToDefaults();
		Number[] result = new Number[defdaos.size()];
		int idx = 0;
		Class<? extends Number>[] profile = getProfile();
		for (MetaDataDefaultsDAO defdao:defdaos) {
			result[idx] = Utils.formatNumber(profile[idx++], defdao.getDefault());
		}
		return result;
	}

	@Override
	public Number[] getMax() {
		List<MetaDataDefaultsDAO> defdaos = dao.getMetaToDefaults();
		Number[] result = new Number[defdaos.size()];
		int idx = 0;
		Class<? extends Number>[] profile = getProfile();
		for (MetaDataDefaultsDAO defdao:defdaos) {
			result[idx] = Utils.formatNumber(profile[idx++], defdao.getMax());
		}
		return result;
	}

	@Override
	public Number[] getMin() {
		List<MetaDataDefaultsDAO> defdaos = dao.getMetaToDefaults();
		Number[] result = new Number[defdaos.size()];
		int idx = 0;
		Class<? extends Number>[] profile = getProfile();
		for (MetaDataDefaultsDAO defdao:defdaos) {
			result[idx] = Utils.formatNumber(profile[idx++], defdao.getMin());
		}
		return result;
	}

	@Override
	public void setDefault(Number[] n) {
		List<MetaDataDefaultsDAO> defdaos = dao.getMetaToDefaults();
		if (defdaos.size() != n.length) {
			log.error("Proposed defaults do not correspond to underlying metadata");
		}
		int idx = 0;
		Class<? extends Number>[] profile = getProfile();
		for (MetaDataDefaultsDAO defdao:defdaos) {
			defdao.setDefault(n[idx++].doubleValue());
		}

	}

	@Override
	public void setMax(Number[] n) {
		List<MetaDataDefaultsDAO> defdaos = dao.getMetaToDefaults();
		if (defdaos.size() != n.length) {
			log.error("Proposed max does not correspond to underlying metadata");
		}
		int idx = 0;
		Class<? extends Number>[] profile = getProfile();
		for (MetaDataDefaultsDAO defdao:defdaos) {
			defdao.setMax(n[idx++].doubleValue());
		}
	}

	@Override
	public void setMin(Number[] n) {
		List<MetaDataDefaultsDAO> defdaos = dao.getMetaToDefaults();
		if (defdaos.size() != n.length) {
			log.error("Proposed min does not correspond to underlying metadata");
		}
		int idx = 0;
		Class<? extends Number>[] profile = getProfile();
		for (MetaDataDefaultsDAO defdao:defdaos) {
			defdao.setMin(n[idx++].doubleValue());
		}
	}



	public boolean equals(Object o) {
		return ((o instanceof ServerMetaData) && dao.equals(((ServerMetaData)o).dao));
	}


}
