package config;

import java.util.concurrent.TimeUnit;
import static executionEngine.DriverScript.OR;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

 
public class ActionKeywords {
 
		public static WebDriver driver;
		@SuppressWarnings("deprecation")
	public static void openBrowser(String object, String data){		
		System.setProperty("webdriver.ie.driver", "G://Selenium3.0.1//IEDriverServer_x64_3.0.0//IEDriverServer.exe");
		
		 DesiredCapabilities dc = new DesiredCapabilities();
		driver = new InternetExplorerDriver(dc);
		}
 
	public static void navigate(String object, String data){	
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(Constants.URL);
		}
 
	public static void input(String object, String data){
		driver.findElement(By.id(OR.getProperty(object))).sendKeys(data); 
		}
 
		
	
	public static void click(String object, String data){
		
		driver.findElement(By.id(OR.getProperty(object))).click();
		}
 
	public static void waitFor(String object, String data) throws Exception{
		
		Thread.sleep(5000);
		}
 
	public static void closeBrowser(String object, String data){
		
			driver.quit();
		}
 
	}