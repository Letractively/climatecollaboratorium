package excel_model.persistence;

import cayenne_classes.OutputParamDAO;
import excel_model.Output;

public class ServerOutput extends ServerObject<OutputParamDAO> implements Output {



	private ServerFactory factory;
	

	public ServerOutput(OutputParamDAO dao) {
		super(dao);

	}

	public ServerOutput(int row, int col, int numvalues,
			String name, String type, String descr, String internal) {
		setRow(row);
		setCol(col);
		setName(name);
		setNumRows(numvalues);
		setNumColumns(1);
		setType(type);
		setDescription(descr);
		setInternalName(internal);
	}


	@Override
	public int getCol() {
		return dao.getColNum();
	}

	@Override
	public int getNumValues() {
		return dao.getNumRows();
	}

	@Override
	public int getRow() {
		return dao.getRowNum();
	}

	@Override
	public void setCol(int col) {
		dao.setColNum(col);
	}

	@Override
	public void setNumValues(int count) {
		dao.setNumRows(count);
	}

	@Override
	public void setRow(int row) {
		dao.setRowNum(row);
	}

	@Override
	public String getName() {
		return dao.getName();
	}

	@Override
	public void setName(String name) {
		dao.setName(name);
	}
	
	@Override
	public int getNumColumns(){
		return dao.getNumColumns();
	}
	@Override
	public void setNumColumns(int i){
		dao.setNumColumns(i);
	}
	
	@Override
	public int getNumRows(){
		return dao.getNumRows();
	}
	@Override
	public void setNumRows(int i){
		dao.setNumRows(i);
	}
	
	@Override
	public void setType(String s){
		dao.setType(s);
	}
	@Override
	public String getType(){
		return dao.getType();
	}
	
	@Override
	public String getDescription(){
		return dao.getDescription();
	}
	@Override
	public void setDescription(String s){
		dao.setDescription(s);
	}

	@Override
	public String getInternalName() {
		return dao.getInternalName();
	}

	@Override
	public void setInternalName(String name) {
		dao.setInternalName(name);
	}
}
