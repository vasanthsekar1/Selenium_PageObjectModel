package com.qa.web.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	public static Map<String, LinkedHashMap<String, String>> readValueFromExcelAsMap(String filePath, String sheetName) {

		Workbook wb = null;
		Sheet s = null;
		try {
			wb = WorkbookFactory.create(new File(filePath));

			s = wb.getSheet(sheetName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Map<String, LinkedHashMap<String, String>> dataList = new HashMap<String, LinkedHashMap<String, String>>();

		int countOfDataSet = s.getRow(0).getPhysicalNumberOfCells();
		int rowCount = s.getPhysicalNumberOfRows();

		for (int j = 1; j < rowCount; j++) {
			LinkedHashMap<String, String> data = new LinkedHashMap<>();
			for (int i = 0; i < countOfDataSet; i++) {
				String fieldName = String.valueOf(s.getRow(0).getCell(i));
				String fieldValue = String.valueOf(s.getRow(j).getCell(i));
				data.put(fieldName, fieldValue);

			}

			dataList.put(data.get("TestMethod"), data);

		}

		return dataList;
	}
}
