package excel_model.persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import cayenne_classes.ExcelModelDAO;
import cayenne_classes.InputParamDAO;
import cayenne_classes.OutputParamDAO;
import excel_model.Input;
import excel_model.Model;
import excel_model.Output;

public class ServerModel extends ServerObject<ExcelModelDAO> implements Model{

	Logger logger = Logger.getLogger(ServerModel.class);

	private HSSFWorkbook workbook;
	private ServerFactory factory;

	private List<Output> outputList = new ArrayList<Output>();
	private List<Input> inputList = new ArrayList<Input>();

	public ServerModel(ExcelModelDAO dao, ServerFactory factory) {

		super(dao);
		this.factory = factory;
		populate();
	}
	/**
	 * Set ourselves up with pre-populated lists from the data.
	 */
	private void populate() {
		for (InputParamDAO idao:dao.getToInputParam()) {
			addInput(factory.get(idao));
		}
		for (OutputParamDAO odao:dao.getToOutputParams()) {
			addOutput(factory.get(odao));
		}
	}

	public ServerModel(String name, String path, int worksheet, ServerFactory factory) {
		this.setName(name);
		this.setPath(path);
		this.setWorksheetIndex(worksheet);
		this.factory = factory;
	}

	@Override
	public void addInput(Input i) {
		//NOTE: we could be a lot smarter here, but this should handle anticipated use cases
		inputList.add(i);
		if (i instanceof ServerInput) {
			InputParamDAO idao = ((ServerInput)i).dao;
			if (!dao.getToInputParam().contains(idao)) {
				dao.addToToInputParam(idao);
			}
		}
	}

	@Override
	public void addOutput(Output o) {
		//NOTE: we could be a lot smarter here, but this should handle anticipated use cases
		outputList.add(o);
		if (o instanceof ServerOutput) {
			OutputParamDAO odao = ((ServerOutput)o).dao;
			if (!dao.getToOutputParams().contains(odao)) {
				dao.addToToOutputParams(odao);
			}
		}
	}

	public HSSFWorkbook getWorkbook() throws IOException {
		if (workbook == null) {
			try {
	 		FileInputStream stream = new FileInputStream(getPath());
			POIFSFileSystem fileSystem = new POIFSFileSystem(stream);
			workbook = new HSSFWorkbook (fileSystem);
			} catch (IOException e) {
				logger.error("Error accessing workbook", e);
				throw(e);
			}
		}
		return workbook;
	}

	public HSSFSheet getWorksheet() throws IOException {
		return getWorkbook().getSheetAt(getWorksheetIndex());
	}




	@Override
	public List<Input> getInputs() {
		return Collections.unmodifiableList(inputList);
	}

	@Override
	public List<Output> getOutputs() {
		return Collections.unmodifiableList(outputList);
	}

	@Override
	public String getName() {
		return dao.getName();
	}



	@Override
	public String getPath() {
		return dao.getPath();
	}



	@Override
	public void setName(String s) {
		dao.setName(s);

	}

	@Override
	public Integer getId() {
		return dao.getId();
	}



	@Override
	public void setPath(String s) {
		dao.setPath(s);
	}

	@Override
	public int getWorksheetIndex() {
		return dao.getWorksheet();
	}

	@Override
	public void setWorksheetIndex(int i) {
		dao.setWorksheet(i);
	}

}
