package excel_model;

import excel_model.Model;

public interface Output {

	public void setRow(int row);
	public int getRow();

	public void setCol(int col);
	public int getCol();

	public void setNumValues(int count);
	public int getNumValues();

	public String getName();
	public void setName(String name);
	
	public String getType();
	public void setType(String type);
	
	public int getNumColumns();
	public void setNumColumns(int num);
	
	public int getNumRows();
	public void setNumRows(int num);
	
	public String getDescription();
	public void setDescription(String descr);
	
	public String getInternalName();
	public void setInternalName(String name);

}
