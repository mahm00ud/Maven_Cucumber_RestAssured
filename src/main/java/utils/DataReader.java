package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataReader {
	private static File file;
	private static FileInputStream inputStream;
	private static XSSFWorkbook wb;
	private static XSSFSheet sheet;
	private static XSSFCell cell;
	private static XSSFRow row;
	
	
	public void setExcelFile() {

		try {
			file = new File("src\\test\\resources\\TestData.xlsx");
			inputStream = new FileInputStream(file);
			wb = new XSSFWorkbook(inputStream);
			sheet = wb.getSheet("ValidData");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getCellData(int rowNumber) {
		setExcelFile();
		cell = sheet.getRow(rowNumber - 1).getCell(1);
		return cell.getStringCellValue();
	}

	// Creating an array of the excel data
	public static FileInputStream GetFileInputStream(String path) {
		String filepath = path;
		File srcFile = new File(filepath);

		try {
			inputStream = new FileInputStream(srcFile);
		} catch (FileNotFoundException e) {
			System.out.println("Testdata file can't be found on this path" + filepath);
			System.exit(0);
		}
		return inputStream;
	}

	public static String[][] getExcelData(String sheetName) {

		inputStream = GetFileInputStream("src\\test\\resources\\TestData.xlsx");
		try {
			wb = new XSSFWorkbook(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sheet = wb.getSheet(sheetName);

		int rowNum = sheet.getLastRowNum() + 1;
		int columnNum = sheet.getRow(0).getLastCellNum();

		String[][] arrayExcelData = new String[rowNum][columnNum];

		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < columnNum; j++) {
				row = sheet.getRow(i);
				if (row.getCell(j) == null) {
					arrayExcelData[i][j] = "";
				} else {
					arrayExcelData[i][j] = row.getCell(j).toString();
				}
			}
		}

		try {
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayExcelData;
	}

	public static String getExcelValueByAttribute(String sheetName, String ExcelAttribute) throws IOException {
		String cellValue = null;
		String[][] array = getExcelData(sheetName);
		for (int i = 0; i < array.length; i++) {
			String attribute = array[i][0];
			if (attribute.equalsIgnoreCase(ExcelAttribute)) {
				cellValue = array[i][1];
				break;
			}
		}
		return cellValue;
	}
}
