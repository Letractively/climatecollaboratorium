package excel_model.persistence;

import cayenne_classes.InputParamDAO;
import excel_model.Input;
import excel_model.Model;

public class ServerInput extends ServerObject<InputParamDAO> implements Input{


	private ServerFactory factory;

	public ServerInput(InputParamDAO dao) {
		super(dao);
	}

	public ServerInput(int row, int col, String name, 
			String descr, String internal) {
		setRow(row);
		setCol(col);
		setName(name);
		setDescription(descr);
		setInternalName(internal);
	}

	@Override
	public int getCol() {
		return dao.getColNum();
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
