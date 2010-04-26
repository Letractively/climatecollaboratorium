package excel_model;

public interface Input {



	public int getRow();
	public void setRow(int row);

	public int getCol();
	public void setCol(int col);

	public String getName();
	public void setName(String name);
	
	public String getDescription();
	public void setDescription(String descr);
	
	public String getInternalName();
	public void setInternalName(String name);

}
