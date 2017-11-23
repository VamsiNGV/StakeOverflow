package executionEngine;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;

import config.ActionKeywords;
import config.Constants;
import utility.ExcelUtils;

public class DriverScript {

	public static Properties OR;
	public static ActionKeywords actionKeywords;
	public static String sActionKeyword;
	public static String sPageObject;
	public static Method method[];
	
	public DriverScript()
	{
	actionKeywords = new ActionKeywords();
	method = actionKeywords.getClass().getMethods();
	}

	public static void main(String[] Args) throws Exception
	{
			
		ExcelUtils.setExcelFile(Constants.Path_TestData);
		
		//Declaring String variable for storing Object Repository Path
		String Path_OR = Constants.Path_OR;
		//Creating file system object for Object Repository path
		FileInputStream fs = new FileInputStream(Path_OR); 
		//Creating an object of properties
		OR = new Properties(System.getProperties());
		//Loading all the properties  from Object Repository property file in to OR object
		OR.load(fs);
		
		DriverScript startEngine = new DriverScript();
		startEngine.execute_TestCase();
		
		
		
	}

	private void execute_TestCase() throws Exception {
		int iTotalTestCases = ExcelUtils.getRowCount(Constants.Sheet_TestCases);
		for(int iTestcase=1;iTestcase<=iTotalTestCases;iTestcase++){
			//This is to get the Test case name from the Test Cases sheet
			String sTestCaseID = ExcelUtils.getCellData(iTestcase, Constants.Col_TestCaseID, Constants.Sheet_TestCases); 
			//This is to get the value of the Run Mode column for the current test case
			String sRunMode = ExcelUtils.getCellData(iTestcase, Constants.Col_Runmode,Constants.Sheet_TestCases);
			//This is the condition statement on RunMode value
			if (sRunMode.equals("Yes")){
				//Only if the value of Run Mode is 'Yes', this part of code will execute
				int iTestStep = ExcelUtils.getRowContains(sTestCaseID, Constants.Col_TestCaseID, Constants.Sheet_TestSteps);
				int iTestLastStep = ExcelUtils.getTestStepsCount(Constants.Sheet_TestSteps, sTestCaseID, iTestStep);
				//This loop will execute number of times equal to Total number of test steps
				for (;iTestStep<=iTestLastStep;iTestStep++){
		    		sActionKeyword = ExcelUtils.getCellData(iTestStep, Constants.Col_ActionKeyword,Constants.Sheet_TestSteps);
		    		sPageObject = ExcelUtils.getCellData(iTestStep, Constants.Col_PageObject, Constants.Sheet_TestSteps);
		    		execute_Action();
		    			}
					}
    			}
		
	}

	private static void execute_Action() throws Exception {
		for(int i=0;i<method.length;i++)
		{
			if(method[i].getName().equals(sActionKeyword));
			{
				method[i].invoke(actionKeywords, sPageObject);
				
				break;
			}
		}
		
	}
}
