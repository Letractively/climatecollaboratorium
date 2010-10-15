package tester;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.record.formula.functions.Rows;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import excel_model.parser.ModelParse;
import excel_model.persistence.ServerRepository;

public class ModelParseTest {

	public static void main(String[] args) throws FileNotFoundException, IOException{

		String path = "C:/Users/Patrick/Desktop/jacoby-sample.xls";
		HSSFWorkbook wb = ModelParse.getWorkbook(new FileInputStream(path));
		HSSFSheet sheet = wb.getSheetAt(0);

		ModelParse.parseWorkbook(wb,"stern test",path);

		ServerRepository.instance().commit();
	}
}
