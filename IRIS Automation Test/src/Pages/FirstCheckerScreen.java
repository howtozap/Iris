package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FirstCheckerScreen {

	String incidentNumber="124476";
	
	
	public void clickOnContractValidationInbox(WebDriver driver) {
		
		WebElement contractValidationBox= driver.findElement(By.id("nbClientSideEvents_I0i11_"));
		contractValidationBox.click();
	}
	
   public void searchDealthroughIncidentNumber(WebDriver driver) throws InterruptedException {
		
		WebElement searchBox= driver.findElement(By.id("grdGlobalDeals_DXFREditorcol0_I"));
		searchBox.sendKeys(incidentNumber);
		searchBox.sendKeys(Keys.ENTER);
		
		Thread.sleep(8000);
   }
	
}
