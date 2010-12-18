package excel_model.parser;

import cayenne_classes.OutputParamDAO;
import excel_model.persistence.ServerOutput;

public class OutputParse extends ServerOutput {

	private String units;
	private String min;
	private String max;
	private String label;
	private String context;
	private String datatype;
	private String external;
	private String category;
	private String value;

	/*
	 * create OutputParse from dao
	 */
	public OutputParse(OutputParamDAO dao){
		super(dao);
	}

	/*
	 * create new OutputParse
	 */
	public OutputParse(String name, String intName, String value, 
			String label, String descr, String units, String context,
			String type, String datatype, String external,
			String min, String max, String category,
			int row, int col, int numVals){
		super(row,col,numVals,name,type,descr,intName);
		setUnits(units);
		setMin(min);
		setMax(max);
		setDefault(value);
		setLabel(label);
		setContext(context);
		setDatatype(datatype);
		setExternal(external);
		setCategory(category);
	}

	public String getUnits(){
		return this.units;
	}
	public void setUnits(String s){
		this.units = s;
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
	
	public String getDefault(){
		return this.value;
	}
	public void setDefault(String s){
		this.value = s;
	}
}