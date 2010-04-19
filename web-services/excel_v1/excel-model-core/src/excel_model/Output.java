package excel_model;

import java.util.ArrayList;

public class Output {
	
	String intName;
	ArrayList<Double> values;
	String units;
	String name;
	String description;
	double min;
	double max;
	
	public Output(String intName, ArrayList<Double> values, String units, 
			String name, String descr, Double min, Double max){
		this.intName = intName;
		this.values = values;
		this.units = units;
		this.name = name;
		this.description = descr;
		this.min = min;
		this.max = max;
	}
	
	public String getIntName(){
		return this.intName;
	}
	public void setIntName(String s){
		this.intName = s;
	}
	
	public ArrayList<Double> getValues(){
		return this.values;
	}
	public void setValues(ArrayList<Double> a){
		this.values = a;
	}
	
	public String getUnits(){
		return this.units;
	}
	public void setUnits(String s){
		this.units = s;
	}
	
	public String getName(){
		return this.name;
	}
	public void setName(String s){
		this.name = s;
	}
	
	public String getDescr(){
		return this.description;
	}
	public void setDescr(String s){
		this.description = s;
	}
	
	public double getMin(){
		return this.min;
	}
	public void setMin(Double d){
		this.min = d;
	}
	
	public double getMax(){
		return this.max;
	}
	public void setMax(Double d){
		this.max = d;
	}
}
