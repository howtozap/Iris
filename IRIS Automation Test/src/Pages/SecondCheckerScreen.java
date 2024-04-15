package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SecondCheckerScreen {

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
   
   public void retriveDeal(WebDriver driver) throws InterruptedException {
		
		WebElement retriveDealbtn= driver.findElement(By.id("btnRetrieveDeal"));
		retriveDealbtn.click();
		
		Thread.sleep(4000);
		
		driver.switchTo().activeElement();
		
		//if(driver.findElement(By.id("confirmText")).isDisplayed()) {
			
			WebElement yesbtn= driver.findElement(By.id("btnConfirmOK_CD"));
			yesbtn.click();
			
			WebDriverWait wait1 = new WebDriverWait(driver, 100);
			wait1.until(ExpectedConditions.elementToBeClickable(By.id("btnCustomMessageClose_CD")));
			
			WebElement okbtn= driver.findElement(By.id("btnCustomMessageClose_CD"));
			okbtn.click();
			
		//}
		/*
		 * else {
		 * 
		 * WebElement okbtn= driver.findElement(By.id("btnCustomMessageClose_CD"));
		 * okbtn.click(); }
		 */
		WebDriverWait wait2 = new WebDriverWait(driver, 100);
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("nbClientSideEvents_I0i0_")));
		
		//driver.switchTo().window(parentWindow);
	}
   
}
