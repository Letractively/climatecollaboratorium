package excel_model.parser;

import cayenne_classes.InputParamDAO;
import excel_model.persistence.ServerInput;

public class InputParse extends ServerInput{

	private String units;
	private String value;
	private String min;
	private String max;
	private String type;
	private String label;
	private String context;
	private String datatype;
	private String external;
	private String category;

	/*
	 * create InputParse from dao
	 */
	public InputParse(InputParamDAO dao){
		super(dao);
	}

	/*
	 * create new InputParse
	 */
	public InputParse(String name, String intName, String value, 
			String label, String descr, String units, String context,
			String type, String datatype, String external,
			String min, String max, String category,
			Integer row, Integer col){
		super(row, col, name, descr, intName);
		setLabel(label);
		setContext(context);
		setDatatype(datatype);
		setExternal(external);
		setCategory(category);
		setUnits(units);
		setValue(value);
		setMin(min);
		setMax(max);
		setType(type);
	}
	
	public String getUnits(){
		return this.units;
	}
	public void setUnits(String s){
		this.units = s;
	}
	
	public String getValue(){
		return this.value;
	}
	public void setValue(String s){
		this.value = s;
	}

	public String getMin(){
		return this.min;
	}
	public void setMin(String s){
		this.min = s;
	}

	public String getMax(){
		return this.max;
	}
	public void setMax(String s){
		this.max = s;
	}
	
	public String getType(){
		return this.type;
	}
	public void setType(String s){
		this.type = s;
	}
	
	public String getLabel(){
		return this.label;
	}
	public void setLabel(String s){
		this.label = s;
	}
	
	public String getContext(){
		return this.context;
	}
	public void setContext(String s){
		this.context = s;
	}
	
	public String getDatatype(){
		return this.datatype;
	}
	public void setDatatype(String s){
		this.datatype = s;
	}
	
	public String getExternal(){
		return this.external;
	}
	public void setExternal(String s) {
		this.external = s;
	}
	
	public String getCategory(){
		return this.category;
	}
	public void setCategory(String s){
		this.category = s;
	}

}
