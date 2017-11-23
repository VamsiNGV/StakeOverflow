package utility;
 
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import config.Constants;


    public class ExcelUtils {
                private static XSSFSheet ExcelWSheet;
                private static XSSFWorkbook ExcelWBook;
                private static XSSFCell Cell;
 
            //This method is to set the File path and to open the Excel file
            //Pass Excel Path and SheetName as Arguments to this method
            public static void setExcelFile(String Path) throws Exception {
	                FileInputStream ExcelFile = new FileInputStream(Path);
	                ExcelWBook = new XSSFWorkbook(ExcelFile);
	                
                   }
 
            //This method is to read the test data from the Excel cell
            //In this we are passing parameters/arguments as Row Num and Col Num
            public static String getCellData(int RowNum, int ColNum, String SheetName) throws Exception{
            	ExcelWSheet = ExcelWBook.getSheet(SheetName);
            	try
            	{
            	  Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
                  String CellData = Cell.getStringCellValue();
                  return CellData;
            	}
            	catch(Exception e) {
            		return"";
            	}
            	}
            
            public static int getRowCount(String SheetName) {
            	ExcelWSheet = ExcelWBook.getSheet(SheetName);
            	int number=ExcelWSheet.getLastRowNum()+1;
    			return number;
            }
            
            public static int getRowContains(String sTestCaseName, int colNum,String SheetName) throws Exception{
        		int i;	
        		ExcelWSheet = ExcelWBook.getSheet(SheetName);
        			int rowCount = ExcelUtils.getRowCount(SheetName);
        			for (i=0 ; i<rowCount; i++){
        				if  (ExcelUtils.getCellData(i,colNum,SheetName).equalsIgnoreCase(sTestCaseName)){
        					break;
        				}
        			}
        			return i;
        			}
            
            public static int getTestStepsCount(String SheetName, String sTestCaseID, int iTestCaseStart) throws Exception{
        		for(int i=iTestCaseStart;i<=ExcelUtils.getRowCount(SheetName);i++){
        			if(!sTestCaseID.equals(ExcelUtils.getCellData(i, Constants.Col_TestCaseID, SheetName))){
        				int number = i;
        				return number;
        			}
        		}
        		ExcelWSheet = ExcelWBook.getSheet(SheetName);
        		int number=ExcelWSheet.getLastRowNum()+1;
        		return number;
            }
 
}