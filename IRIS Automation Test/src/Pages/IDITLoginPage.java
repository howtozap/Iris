package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class IDITLoginPage 
{
	
	String URL = "https://10.100.100.120/idit-web/policy_manager/asset.do";
	String username_css = "#UserName";
	String password_css = "#Password";
	String station_dropdown = "#Station";
	String usernameValue = "snasikwala";
	String passwordValue = "sabihanasikwala.10";
	String loginButton_css = "#Login";
	String userMenuButton_css = "#userMenuButton";
	String okButton_css = "#DialogOK";
	String logoffButton_css = "#LogOff";
	
	public void NavigateToIDIT(WebDriver driver)
	{
		driver.get(URL);
		driver.manage().window().maximize();
	}
	
	public void LoginToIDIT(WebDriver driver) throws InterruptedException
	{
		Thread.sleep(5000);
		WebElement username = driver.findElement(By.cssSelector(username_css));
		username.clear();
		username.sendKeys(usernameValue);
		
		WebElement password = driver.findElement(By.cssSelector(password_css));
		password.clear();
		password.sendKeys(passwordValue);
		
		Select station = new Select(driver.findElement(By.cssSelector(station_dropdown)));
		station.selectByVisibleText("IDIT");
		
		WebElement login = driver.findElement(By.cssSelector(loginButton_css));
		login.click();
		
		Thread.sleep(25000);
	}
	
	public void LogoutFromIDIT(WebDriver driver) throws InterruptedException
	{
		Thread.sleep(5000);
		
		WebElement userMenuButton = driver.findElement(By.cssSelector(userMenuButton_css));
		userMenuButton.click();
		
		Thread.sleep(1000);
		WebElement logOffLink = driver.findElement(By.cssSelector(logoffButton_css));
		logOffLink.click();
		
		Thread.sleep(1000);
		
		driver.switchTo().activeElement();
		
		WebElement okButton = driver.findElement(By.cssSelector(okButton_css));
		okButton.click();
		
	}
	
	
}
