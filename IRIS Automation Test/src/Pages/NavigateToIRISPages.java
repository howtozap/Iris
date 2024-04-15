package Pages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigateToIRISPages {
	String myDealsLink_xpath = ".//*[contains(text(),'My Deals')]/parent::li";
	String pendDealsLink_xpath = ".//*[contains(text(),'Pend Deals')]//parent::li";
	String allIncidentsLink_xpath = ".//*[contains(text(),'All Incidents')]/parent::li";
	String activeDealLink_xpath = ".//*[contains(text(),'Active Deals (Readonly)')]//parent::li";
	String archiveDeal_xpath = ".//*[contains(text(),'Archive Deals (Readonly)')]//parent::li";
	String declinesAndCancelation_xpath = ".//*[contains(text(),'Declines and Cancellations')]//parent::li";
	String assesments_xpath = ".//*[contains(text(),'Assessment')]//parent::li";
	String verification_xpath = ".//*[text()= 'Verification']//parent::li";
	String faceToFaceVerification_xpath = ".//*[text()= 'Face To Face Verification']//parent::li";
	String documentValidation_xpath = ".//*[text()= 'Document Validation']//parent::li";
	String contractGeneration_xpath = ".//*[text()= 'Contract Generation']//parent::li";
	String contractValidation_xpath = ".//*[text()= 'Contract Validation']//parent::li";
	String awaitingPayout_xpath = ".//*[text()= 'Awaiting Payout']//parent::li";
	String managerOverride_xpath = ".//*[text()= 'Manager Override']//parent::li";
	String statusSearchBox_css = "#grdArchiveDeals_DXFREditorcol4_I";
	String firstDeal_css = "#grdArchiveDeals_tccell0_0";
	String applicationStageLink = "Application";
	String ApplicantTab_ID = "rndPnlApplicant_RPHT";
	String VehicleDetailsTab_Xpath = "//a[@id='tbApplicatioDetailsTab_T1T']";
	String VehicleDetailsText_ID = "rndPnlVehicleDetails_RPHT";
	String CreditDecisionTab_ID = "tbSummaryScreen_AT0T";
	String incidentnumber = "spnIncidentNum";
	String ValidationTab_ID = "rndPnlDocumentValidation_RPHT";
	String VerificationTab_ID = "rndPnlKYC_RPHT";
	String InvoiceTab_ID = "rndPnlInvoice_RPHT";
	String ContractTab_ID = "tbContractConfirmation_AT0";
	String MydealPanel = "grdInbox_grouppanel";
	String PenddealPanel = "grdPendDeals_grouppanel";
	String InboxPanel = "grdGlobalDeals_grouppanel";
	String ArchivedealPanel = "grdArchiveDeals_grouppanel";
	String DataImportLog_xpath = "//*[@id=\"QuickClientAction\"]/a";
	String DecisionSummary_xpath = "//*[@id=\"btnSummary\"]";
	String DecisionSummarytitle_xpath = "//*[@id=\"popSummaryView_PWH-1T\"]";
	String CloseDecisionSummaryPopup = "btnCancelSummaryPopUp_CD";
	String IntegrationStatus_xpath = "//*[@id=\"integrationstatusId\"]";
	String IntegrationStatustitle_xpath = "//*[@id=\"popIntegrationList_PWH-1T\"]";
	String CloseIntegrationStatusPopup = "btnCancel_CD";

	public void NavigateToMyDeals(WebDriver driver) throws InterruptedException {
		Thread.sleep(10000);
		WebDriverWait wait1 = new WebDriverWait(driver, 180);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(myDealsLink_xpath)));
		WebElement myDeals = driver.findElement(By.xpath(myDealsLink_xpath));
		myDeals.click();
		new WebDriverWait(driver, 100)
		.until(ExpectedConditions.visibilityOfElementLocated(By.id(MydealPanel)));
		Thread.sleep(5000);
	}

	public void NavigateToPendDeals(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		WebDriverWait wait1 = new WebDriverWait(driver, 180);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pendDealsLink_xpath)));
		WebElement pendDeals = driver.findElement(By.xpath(pendDealsLink_xpath));
		pendDeals.click();
		new WebDriverWait(driver, 100)
		.until(ExpectedConditions.visibilityOfElementLocated(By.id(PenddealPanel)));
		Thread.sleep(5000);
	}

	public void NavigateToAllIncidents(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		WebDriverWait wait1 = new WebDriverWait(driver, 180);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(allIncidentsLink_xpath)));
		WebElement allIncidentsLink = driver.findElement(By.xpath(allIncidentsLink_xpath));
		allIncidentsLink.click();
		new WebDriverWait(driver, 100)
		.until(ExpectedConditions.visibilityOfElementLocated(By.id(InboxPanel)));
		Thread.sleep(5000);
	}

	public void NavigateToActiveDeals(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		WebDriverWait wait1 = new WebDriverWait(driver, 180);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(activeDealLink_xpath)));
		WebElement activeDealLink = driver.findElement(By.xpath(activeDealLink_xpath));
		activeDealLink.click();
		new WebDriverWait(driver, 100)
		.until(ExpectedConditions.visibilityOfElementLocated(By.id(InboxPanel)));
		Thread.sleep(5000);
	}

	public void NavigateToArchiveDeals(WebDriver driver) throws InterruptedException {
		Thread.sleep(10000);
		WebDriverWait wait1 = new WebDriverWait(driver, 180);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(archiveDeal_xpath)));
		WebElement archiveDeals = driver.findElement(By.xpath(archiveDeal_xpath));
		archiveDeals.click();
		new WebDriverWait(driver, 100)
		.until(ExpectedConditions.visibilityOfElementLocated(By.id(ArchivedealPanel)));
		Thread.sleep(5000);
	}

	public void NavigateToDeclineAndCancelation(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		WebDriverWait wait1 = new WebDriverWait(driver, 180);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(declinesAndCancelation_xpath)));
		WebElement declinesAndCancelation = driver.findElement(By.xpath(declinesAndCancelation_xpath));
		declinesAndCancelation.click();
		new WebDriverWait(driver, 100)
		.until(ExpectedConditions.visibilityOfElementLocated(By.id(InboxPanel)));
		Thread.sleep(5000);
	}

	public void NavigateToAssesment(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		WebDriverWait wait1 = new WebDriverWait(driver, 180);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(assesments_xpath)));
		WebElement assesments = driver.findElement(By.xpath(assesments_xpath));
		assesments.click();
		new WebDriverWait(driver, 100)
		.until(ExpectedConditions.visibilityOfElementLocated(By.id(InboxPanel)));
		Thread.sleep(5000);
	}

	public void NavigateToVerification(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		WebDriverWait wait1 = new WebDriverWait(driver, 180);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(verification_xpath)));
		WebElement verification = driver.findElement(By.xpath(verification_xpath));
		verification.click();
		new WebDriverWait(driver, 100)
		.until(ExpectedConditions.visibilityOfElementLocated(By.id(InboxPanel)));
		Thread.sleep(5000);
	}

	public void NavigateToFaceToFaceVerification(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		WebDriverWait wait1 = new WebDriverWait(driver, 180);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(faceToFaceVerification_xpath)));
		WebElement faceToFaceVerification = driver.findElement(By.xpath(faceToFaceVerification_xpath));
		faceToFaceVerification.click();
		new WebDriverWait(driver, 100)
		.until(ExpectedConditions.visibilityOfElementLocated(By.id(InboxPanel)));
		Thread.sleep(5000);
	}

	public void NavigateToDocumentValidation(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		WebDriverWait wait1 = new WebDriverWait(driver, 180);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(documentValidation_xpath)));
		WebElement documentValidation = driver.findElement(By.xpath(documentValidation_xpath));
		documentValidation.click();
		new WebDriverWait(driver, 100)
		.until(ExpectedConditions.visibilityOfElementLocated(By.id(InboxPanel)));
		Thread.sleep(5000);
	}
	
	public void NavigateToContractGeneration(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		WebDriverWait wait1 = new WebDriverWait(driver, 180);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(contractGeneration_xpath)));
		WebElement documentValidation = driver.findElement(By.xpath(contractGeneration_xpath));
		documentValidation.click();
		new WebDriverWait(driver, 100)
		.until(ExpectedConditions.visibilityOfElementLocated(By.id(InboxPanel)));
		Thread.sleep(5000);
	}
	
	public void NavigateToContractValidation(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		WebDriverWait wait1 = new WebDriverWait(driver, 180);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(contractValidation_xpath)));
		WebElement documentValidation = driver.findElement(By.xpath(contractValidation_xpath));
		documentValidation.click();
		new WebDriverWait(driver, 100)
		.until(ExpectedConditions.visibilityOfElementLocated(By.id(InboxPanel)));
		Thread.sleep(5000);
	}
	
	public void NavigateToAwaitingPayout(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		WebDriverWait wait1 = new WebDriverWait(driver, 180);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(awaitingPayout_xpath)));
		WebElement documentValidation = driver.findElement(By.xpath(awaitingPayout_xpath));
		documentValidation.click();
		new WebDriverWait(driver, 100)
		.until(ExpectedConditions.visibilityOfElementLocated(By.id(InboxPanel)));
		Thread.sleep(5000);
	}
	
	public void NavigateToManagerOverride(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		WebDriverWait wait1 = new WebDriverWait(driver, 180);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(managerOverride_xpath)));
		WebElement documentValidation = driver.findElement(By.xpath(managerOverride_xpath));
		documentValidation.click();
		new WebDriverWait(driver, 100)
		.until(ExpectedConditions.visibilityOfElementLocated(By.id(InboxPanel)));
		Thread.sleep(5000);
	}

	public void SearchTheStatus(WebDriver driver) throws InterruptedException {
		WebDriverWait wait1 = new WebDriverWait(driver, 180);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(statusSearchBox_css)));
		WebElement searchBox = driver.findElement(By.cssSelector(statusSearchBox_css));
		String searchBoxText = searchBox.getText();
		System.out.println(searchBoxText);
		if (!(searchBoxText.equalsIgnoreCase("Payout"))) {
			searchBox.clear();
			searchBox.sendKeys("Payout");
			Thread.sleep(1000);
			searchBox.sendKeys(Keys.ENTER);
		}

		Thread.sleep(15000);

	}

	public void ClickOnFirstDealInTable(WebDriver driver) throws InterruptedException {
		WebDriverWait wait1 = new WebDriverWait(driver, 180);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(firstDeal_css)));
		WebElement firstDeal = driver.findElement(By.cssSelector(firstDeal_css));
		firstDeal.click();
		Thread.sleep(10000);
	}

	public void ClickOnApplicationsPage(WebDriver driver) throws InterruptedException {
		WebDriverWait wait1 = new WebDriverWait(driver, 180);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(incidentnumber)));
		WebElement applicationsLink = driver.findElement(By.linkText(applicationStageLink));
		applicationsLink.click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(ApplicantTab_ID)));
		Thread.sleep(7000);
		driver.findElement(By.xpath(VehicleDetailsTab_Xpath)).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(VehicleDetailsText_ID)));
		Thread.sleep(7000);
	}

	public void ClickOnCreditDecission(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		WebDriverWait wait1 = new WebDriverWait(driver, 180);
		WebElement creditDecissionLink = driver.findElement(By.linkText("Credit Decision"));
		creditDecissionLink.click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(CreditDecisionTab_ID)));
		Thread.sleep(5000);
	}

	public void ClickOnValidation(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		WebDriverWait wait1 = new WebDriverWait(driver, 180);
		WebElement validatioinLink = driver.findElement(By.linkText("Validation"));
		validatioinLink.click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(ValidationTab_ID)));
		Thread.sleep(5000);
	}

	public void ClickOnVerification(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		WebDriverWait wait1 = new WebDriverWait(driver, 180);
		WebElement verificationLink = driver.findElement(By.linkText("Verification"));
		verificationLink.click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(VerificationTab_ID)));
		Thread.sleep(5000);
	}

	public void ClickOnInvoice(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		WebDriverWait wait1 = new WebDriverWait(driver, 180);
		WebElement invoiceLink = driver.findElement(By.linkText("Invoice"));
		invoiceLink.click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(InvoiceTab_ID)));
		Thread.sleep(5000);
	}

	public void ClickOnContract(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		WebDriverWait wait1 = new WebDriverWait(driver, 180);
		WebElement contractLink = driver.findElement(By.linkText("Contract"));
		contractLink.click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(ContractTab_ID)));
		Thread.sleep(5000);
	}
	
	public void ClickOnDataImportLog(WebDriver driver) throws InterruptedException {
		Thread.sleep(5000);
		WebDriverWait wait1 = new WebDriverWait(driver, 180);
		String oldTab = driver.getWindowHandle();
		WebElement contractLink = driver.findElement(By.xpath(DataImportLog_xpath));
		contractLink.click();
		Thread.sleep(15000);
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		newTab.remove(oldTab);
		// change focus to new tab
		driver.switchTo().window(newTab.get(0));
		driver.close();
		// change focus back to old tab
		driver.switchTo().window(oldTab);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DataImportLog_xpath)));
		Thread.sleep(5000);
	}
	
	public void ClickOnDecisionSummary(WebDriver driver) throws InterruptedException {
		Thread.sleep(5000);
		WebDriverWait wait1 = new WebDriverWait(driver, 180);
		WebElement contractLink = driver.findElement(By.xpath(DecisionSummary_xpath));
		contractLink.click();
		Thread.sleep(7000);
		driver.switchTo().activeElement();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DecisionSummarytitle_xpath)));
		Thread.sleep(10000);
		driver.findElement(By.id(CloseDecisionSummaryPopup)).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DecisionSummary_xpath)));
		Thread.sleep(5000);
	}
	
	public void ClickOnIntegrationStatus(WebDriver driver) throws InterruptedException {
		Thread.sleep(5000);
		WebDriverWait wait1 = new WebDriverWait(driver, 180);
		WebElement contractLink = driver.findElement(By.xpath(IntegrationStatus_xpath));
		contractLink.click();
		Thread.sleep(7000);
		driver.switchTo().activeElement();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(IntegrationStatustitle_xpath)));
		Thread.sleep(10000);
		driver.findElement(By.id(CloseIntegrationStatusPopup)).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(IntegrationStatus_xpath)));
		Thread.sleep(5000);
	}
	
	public String CheckVersion(WebDriver driver) throws InterruptedException {
		
		Thread.sleep(5000);
		String Version=driver.findElement(By.id("tdBlueFooterInfo")).getText();
		return Version;
		
	}

}
