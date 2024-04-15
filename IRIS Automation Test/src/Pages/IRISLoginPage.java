package Pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IRISLoginPage 
{
	
	String username_css = "#UserName";
	String password_css = "#Password";
	String loginButton_css = ".login_signin";
	String logoutButton_css = "#btnLogoutImg";
	String yesButton_css = "#btnConfirmBreakCancel_CD";
	
	public String readInputData(String key) throws IOException {
		InputStream input = new FileInputStream("src/TestData/credentials.properties");
		Properties prop = new Properties();
		prop.load(input);
		String value = prop.getProperty(key);
		return value;
	}
	
	public void NavigateToOptima(WebDriver driver) throws IOException
	
	
	{
		String URL=readInputData("URL");
		driver.get(URL);
	}
	
	public void LoginToOptima(WebDriver driver) throws InterruptedException, IOException
	{
		Thread.sleep(5000);
		
		WebElement username = driver.findElement(By.cssSelector(username_css));
		username.clear();
		String usernameText=readInputData("IRISusernameText");
		username.sendKeys(usernameText);
		
		WebElement password = driver.findElement(By.cssSelector(password_css));
		password.clear();
		String passwordText=readInputData("IRISpasswordText");
		password.sendKeys(passwordText);
		
		WebElement loginButton = driver.findElement(By.cssSelector(loginButton_css));
		loginButton.click();
		
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("nbClientSideEvents_I0i7_")));
		
		
	}
	
	public void logoutFromOptima(WebDriver driver) throws InterruptedException
	{
		Thread.sleep(20000);
		WebElement logout = driver.findElement(By.cssSelector(logoutButton_css));
		logout.click();
		Thread.sleep(5000);
		driver.switchTo().activeElement();
		
		WebElement yesButton = driver.findElement(By.cssSelector(yesButton_css));
		yesButton.click();
		
	}

}
