package Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	/*private static XSSFSheet excelWSheet;
	private static XSSFWorkbook excelWBook;
	private static XSSFCell cell;
	
	public Map<String,Map<String,String>> cacheAllExcelData(String xlsPath){
		Map<String,Map<String,String>> excelDataMap=null;
		String sheetName=System.getenv("ENVNAME");
		if(sheetName==null || "".equals(sheetName)) {
			sheetName="Sheet1";
		}
		excelDataMap=getAllExcelDataMap(getColumnArray(xlsPath,sheetName),getTableArray(xlsPath,sheetName));
		return excelDataMap;
		
	}

	private Object[][] getTableArray(String xlsPath, String sheetName) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Object[][] getColumnArray(String filePath, String sheetName) {
		String[][] columnArray=null;
		int ci;
		int totalRows;
		int totalCols;
		try {
			FileInputStream excelFile=new FileInputStream(filePath);
			excelWBook=new XSSFWorkbook(excelFile);
			excelWSheet=excelWBook.getSheet(sheetName);
			if(excelWSheet==null) {
				excelWSheet=excelWBook.getSheet("Sheet1");
				
			}
			totalRows=excelWSheet.getPhysicalNumberOfRows()-1;
			totalCols=excelWBook.getRow(0).getPhysicalNumberOfCells()-1;
			columnArray=new String[totalRows][totalCols+1];
			ci=0;
			for(int j=0;j<totalCols;j++) {
				if(getCellData(ci,j).trim(0!="")) {
					columnArray[ci][j]=getCellData(ci,j);
					
				}
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("Could not read the Excel Sheet: "+sheetName);
		}
		catch(IOException e) {
			System.out.println("Could not read the Excel Sheet: "+sheetName);
			
		}
		return (columnArray);
	}

	private static Map<String,Map<String,String>> getAllExcelDataMap(Object[][] columnArray, Object[][] testDataArray){
		Map<String,Map<String,String>> dataMap=new HashMap<String,Map<String,String>>();
		int testMethodIndex=0;
		for(testMethodIndex=0;testMethodIndex<columnArray.length;testMethodIndex++) {
			if("TestMethod".equals(columnArray[0][testMethodIndex])) {
				break;
			}
		}
		if(testDataArray!=null) {
			for(int i=0;i<testDataArray.length;i++) {
				Map<String,String> rowMap=new HashMap<String,String>();
				for(int j=0;j<columnArray[0].length;j++) {
					rowMap.put((String)columnArray[0][j].toString(),testDataArray[i][j].toString());
					
				}
				dataMap.put((String)testDataArray[i][testMethodIndex],rowMap);
			}
		}
		return dataMap;
	}
*/
}
