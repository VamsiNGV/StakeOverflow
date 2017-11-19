package executionEngine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import config.ActionKeywords;
import config.Constants;
import utility.ExcelUtils;

public class DriverScript {

	public static ActionKeywords actionKeywords;
	public static String sActionKeyword;
	
	public static Method method[];
	
	public DriverScript()
	{
	actionKeywords = new ActionKeywords();
	method = actionKeywords.getClass().getMethods();
	}

	public static void main(String[] Args) throws Exception
	{
		String sPath = Constants.Path_TestData;
		
		ExcelUtils.setExcelFile(sPath, Constants.Sheet_TestSteps);
		
		for(int iRow=1;iRow<=9;iRow++)
		{			
			sActionKeyword = ExcelUtils.getCellData(iRow,3);
			execute_Action();
		}
	}

	private static void execute_Action() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for(int i=0;i<method.length;i++)
		{
			if(method[i].getName().equals(sActionKeyword));
			{
				method[i].invoke("actionKeywords");
				
				break;
			}
		}
		
	}
}
