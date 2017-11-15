package com.test.automation.readexceldata;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadDataFromExcel {

	public String path;
	public FileInputStream excelfile;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;

	public ReadDataFromExcel(String path) {
		this.path = path;
		try {
			excelfile = new FileInputStream(path);
			workbook = new XSSFWorkbook(excelfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public String getCellData(String sheetName, String colName, int rowNum) {
		try {
			int col_Num = 0;
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			XSSFRow row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().equals(colName)) {
					col_Num = i;
					break;
				}
			}
			row = sheet.getRow(rowNum - 1);

			XSSFCell cell = row.getCell(col_Num);
			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				return cell.getStringCellValue();
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
				return "";
			} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				cell.setCellType(Cell.CELL_TYPE_STRING);
				return cell.getStringCellValue();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
