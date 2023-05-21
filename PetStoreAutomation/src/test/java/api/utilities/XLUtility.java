package api.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {

	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle cellStyle;
	String path;

	public XLUtility(String path) {
		this.path = path;
	}

	// Get row count in excel
	public int getRowCount(String sheetName) throws IOException {

		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		String a = workbook.getSheetName(0);
		sheet = workbook.getSheet(sheetName);
		
		int rowcount = sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;
	}
	
	// Get cell count in excel
	public int getCellCount(String sheetName, int rownum) throws IOException {

		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		int cellcount = row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellcount;
	}
	
	// Get cell data
	public String getCellData(String sheetName,int rownum, int column) throws IOException
	{
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(column);
		
		DataFormatter formatter = new DataFormatter();
		String data;
		
		data = formatter.formatCellValue(cell);
		workbook.close();
		fi.close();
		
		return data;
	}

}
