package mit.simulation.climate.model.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.log4j.Logger;

import mit.simulation.climate.model.MetaData;


@XmlType(name="metadata")
public class MetaDataJAXBDelegate implements MetaData {

    private static Logger log = Logger.getLogger(MetaDataJAXBDelegate.class);

    MetaData src;

    public MetaDataJAXBDelegate() {

    }

    public MetaDataJAXBDelegate(MetaData src) {
        if (src == null) {
            log.warn("Creating a delegate with null metadata");
        }
        this.src = src;
    }


    @XmlElement(name="description")
    public String getDescription() {
        return src.getDescription();
    }


    @XmlElement(name="id")
    public Long getId() {
        return src.getId();
    }


    @XmlElement(name="internalname")
    public String getInternalName() {
        return src.getInternalName();
    }

    @XmlElement(name="name")
    public String getName() {
        return src.getName();
    }

    @XmlElement(name="profile")
    @XmlJavaTypeAdapter(ArrayAdapter.Adapter.class)
    public Class<Object>[] getProfile() {
        return src.getProfile();
    }

    @XmlAttribute(name="varcontext")
    public VarContext getVarContext() {
        return src.getVarContext();
    }

    @XmlAttribute(name="vartype")
    public VarType getVarType() {
        log.debug("Getting vartype for "+src);
        return src.getVarType();
    }

    @XmlElement(name="units")
    @XmlJavaTypeAdapter(ArrayAdapter.Adapter.class)
    public String[] getUnits() {
        return src.getUnits();
    }


    @XmlElement(name="defaults")
    @XmlJavaTypeAdapter(ArrayAdapter.Adapter.class)
    public String[] getDefault() {
        return src.getDefault();
    }

    @XmlElement(name="maxes")
    @XmlJavaTypeAdapter(ArrayAdapter.Adapter.class)
    public String[] getMax() {
        return src.getMax();
    }


    @XmlElement(name="mins")
    @XmlJavaTypeAdapter(ArrayAdapter.Adapter.class)
    public String[] getMin() {
        return src.getMin();
    }

    @XmlElement(name="labels")
    @XmlJavaTypeAdapter(ArrayAdapter.Adapter.class)
    public String[] getLabels() {
        return src.getLabels();
    }

    @XmlElement(name="external")
    public String getExternalInfo() {
        return src.getExternalInfo();
    }



    @XmlAttribute(name="index")
    public boolean isIndex() {
        return src.isIndex();
    }

    @XmlElement(name="indexingmetadata")
    @XmlJavaTypeAdapter(JaxbReference.Adapter.class)
    public MetaData getIndexingMetaData() {
        return src.getIndexingMetaData();
    }

    public boolean isInRange(String value) {
        return false;
    }

    @Override
    public boolean isInRange(String[] values) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @XmlElement(name="categories")
    @XmlJavaTypeAdapter(ArrayAdapter.Adapter.class)
    public String[] getCategories() {
        return src.getCategories();
    }



    public void setIsIndex(boolean b) {
        // TODO Auto-generated method stub

    }


    public void setLabels(String[] l) {
        //do nothing
    }

    public void setDescription(String desc) {
    //	src.setDescription(desc);
    }

    public void setInternalName(String name) {
    //	src.setInternalName(name);
    }

    public void setName(String name) {
    //	src.setName(name);
    }

    public void setProfile(Class<Object>[] profile) {
        //src.setProfile(profile);
    }


    public void setUnits(String[] units) {
    //	src.setUnits(units);
    }

    public static class Adapter extends XmlAdapter<MetaDataJAXBDelegate,MetaData> {

        @Override
        public MetaDataJAXBDelegate marshal(MetaData v) throws Exception {
            return new MetaDataJAXBDelegate(v);
        }

        @Override
        public MetaData unmarshal(MetaDataJAXBDelegate v) throws Exception {
            return v;
        }

    }

    @Override
    public void setDefault(String[] n) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setMax(String[] n) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setMin(String[] n) {
        // TODO Auto-generated method stub

    }


    @Override
    public void setExternalInfo(String info) {
        // TODO Auto-generated method stub

    }



    @Override
    public void setCategories(String[] s) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setVarContext(VarContext t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setVarType(VarType t) {
        // TODO Auto-generated method stub

    }



    @Override
    public void setIndexingMetaData(MetaData md) {
        // TODO Auto-generated method stub

    }







}
