package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class VerificationScreen {

	public void attemptSelection(WebDriver driver) throws InterruptedException {

		WebElement attempt1 = driver.findElement(By.id("cmbAttempt1_B-1Img"));
		attempt1.click();
		Thread.sleep(2000);

		WebElement yesOption = driver.findElement(By.id("cmbAttempt1_DDD_L_LBI1T0"));
		yesOption.click();

		Thread.sleep(4000);
		driver.switchTo().activeElement();

		WebElement yesbtn = driver.findElement(By.id("btnConfirmOK_CD"));
		yesbtn.click();

		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("rndPnlKYC_RPHT")));

	}

	public void questionsList(WebDriver driver) {

		WebElement que1 = driver.findElement(By.id("cmbKYCCorrect6_B-1"));
		que1.click();

		WebElement yes1 = driver.findElement(By.id("cmbKYCCorrect6_DDD_L_LBI1T0"));
		yes1.click();

		WebElement que2 = driver.findElement(By.id("cmbKYCCorrect3_B-1"));
		que2.click();

		WebElement yes2 = driver.findElement(By.id("cmbKYCCorrect3_DDD_L_LBI1T0"));
		yes2.click();

		WebElement que3 = driver.findElement(By.id("cmbKYCCorrect2_B-1"));
		que3.click();

		WebElement yes3 = driver.findElement(By.id("cmbKYCCorrect2_DDD_L_LBI1T0"));
		yes3.click();

		WebElement que4 = driver.findElement(By.id("cmbKYCCorrect4_B-1"));
		que4.click();

		WebElement yes4 = driver.findElement(By.id("cmbKYCCorrect4_DDD_L_LBI1T0"));
		yes4.click();

		WebElement que5 = driver.findElement(By.id("cmbKYCCorrect7_B-1"));
		que5.click();
		
		WebElement yes5= driver.findElement(By.id("cmbKYCCorrect7_DDD_L_LBI1T0"));
		yes5.click();

		WebElement que6 = driver.findElement(By.id("cmbKYCCorrect5_B-1"));
		que6.click();
		
		WebElement yes6= driver.findElement(By.id("cmbKYCCorrect5_DDD_L_LBI1T0"));
		yes6.click();

		WebElement que7 = driver.findElement(By.id("cmbKYCCorrect1_B-1"));
		que7.click();
		
		WebElement yes7= driver.findElement(By.id("cmbKYCCorrect1_DDD_L_LBI1T0"));
		yes7.click();

		WebElement que8 = driver.findElement(By.id("cmbKYCCorrect8_B-1"));
		que8.click();
		
		WebElement yes8= driver.findElement(By.id("cmbKYCCorrect8_DDD_L_LBI1T0"));
		yes8.click();

		WebElement que9 = driver.findElement(By.id("cmbKYCCorrect9_B-1"));
		que9.click();
		
		WebElement yes9= driver.findElement(By.id("cmbKYCCorrect9_DDD_L_LBI1T0"));
		yes9.click();
	}
	
	public void kycOverride(WebDriver driver) throws InterruptedException {
		Thread.sleep(5000);
		
		WebElement tick = driver.findElement(By.id("IsKYCOverrideWithoutVerification"));
		if(!(tick.getAttribute("class").contains("dxichCellSys dxeBase dxeDisabled dxeTAR"))) {
			
		WebElement checkbox= driver.findElement(By.id("IsKYCOverrideWithoutVerification_S_D"));
		checkbox.click();
		
		Select sc= new Select(driver.findElement(By.id("KYCOverrideReasonID")));
		sc.selectByValue("45");
		
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.elementToBeClickable(By.id("btnKYCOverride")));
		
		WebElement overridebtn= driver.findElement(By.id("btnKYCOverride"));
		overridebtn.click();
		
		driver.switchTo().activeElement();
		
		WebDriverWait wait2 = new WebDriverWait(driver, 60);
		wait2.until(ExpectedConditions.elementToBeClickable(By.id("btnConfirmOK_CD")));
		
		WebElement yesbtn= driver.findElement(By.id("btnConfirmOK_CD"));
		yesbtn.click();
		
		driver.switchTo().activeElement();
		
		WebDriverWait wait3 = new WebDriverWait(driver, 60);
		
		wait3.until(ExpectedConditions.visibilityOfElementLocated(By.id("customMessageText")));
		
		Assert.assertTrue(driver.findElement(By.id("customMessageText")).isDisplayed(), "KYC Override successfully.");
		
		wait3.until(ExpectedConditions.elementToBeClickable(By.id("btnCustomMessageClose_CD")));
		
		WebElement okbtn= driver.findElement(By.id("btnCustomMessageClose_CD"));
		okbtn.click();
		
		}
		
		WebElement proceedButton= driver.findElement(By.id("btnVerificationProceed_CD"));
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(proceedButton));
		proceedButton.click();
		
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.id("customMessageText")));
		
		Thread.sleep(2000);
		driver.findElement(By.id("btnCustomMessageClose_CD")).click();
		
		new WebDriverWait(driver, 30)
		.until(ExpectedConditions.visibilityOfElementLocated(By.id("nbClientSideEvents_I0i2_")));
		
	}
	
	public void xdsOverride(WebDriver driver) {
		
		WebElement tick= driver.findElement(By.id("IsXDSOverride_S_D"));
		tick.click();
		
		Select sc= new Select(driver.findElement(By.id("XDSOverrideReasonID")));
		sc.selectByValue("41");
		
		WebDriverWait wait3 = new WebDriverWait(driver, 60);
		wait3.until(ExpectedConditions.elementToBeClickable(By.id("btnXDSOverride_CD")));
		
		WebElement overridebtn= driver.findElement(By.id("btnXDSOverride_CD"));
		overridebtn.click();
		
        driver.switchTo().activeElement();
		
		WebDriverWait wait2 = new WebDriverWait(driver, 60);
		wait2.until(ExpectedConditions.elementToBeClickable(By.id("btnConfirmOK_CD")));
		
		WebElement yesbtn= driver.findElement(By.id("btnConfirmOK_CD"));
		yesbtn.click();
		
		WebDriverWait wait4 = new WebDriverWait(driver, 60);
		wait4.until(ExpectedConditions.elementToBeClickable(By.id("btnCustomMessageClose_CD")));
		
		WebElement okbtn= driver.findElement(By.id("btnCustomMessageClose_CD"));
		okbtn.click();
		
		
	}
}
