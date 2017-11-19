package config;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
 
public class ActionKeywords {
 
		public static WebDriver driver;
 
	public static void openBrowser(){		
		driver=new FirefoxDriver();
		}
 
	public static void navigate(){	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(Constants.URL);
		}
 
	public static void input_Displayname(){
		driver.findElement(By.id("display-name")).sendKeys(Constants.DisplayName); 
		}
 
	public static void input_EmailAddress(){
		driver.findElement(By.id("email")).sendKeys(Constants.EmailAddress);
		}
	public static void input_Password(){
		driver.findElement(By.id("password")).sendKeys(Constants.Password);
		}
	
	public static void click_Login(){
		driver.findElement(By.id("submit-button")).click();
		}
 
	public static void waitFor() throws Exception{
		Thread.sleep(5000);
		}
 
	public static void closeBrowser(){
			driver.quit();
		}
 
	}