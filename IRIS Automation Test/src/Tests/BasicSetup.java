package Tests;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasicSetup {

	public static WebDriver driver;
	File folder;

	@BeforeSuite(enabled = true)
	public void launchBrowser() {
		
		initiateDriver();
	}
	
	@AfterSuite(enabled = false)
	public void closeBrowser() {
		driver.quit();
	}
	
	 public WebDriver getdriver(){
		 if(driver==null) {
			 initiateDriver();
		 }
		 return driver;
	 }
		    
		
	 
	 private void initiateDriver() {
		 
		try {
			 WebDriverManager.chromedriver().setup();
			if (driver == null) {
					driver = new ChromeDriver();
					driver.manage().window().maximize();
		      }
			 
		}catch (Exception e) {
			e.printStackTrace();

		}
		 
	 }
	 
	 
}
