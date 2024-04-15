package Pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Pages.*;
import java.time.Duration;
public class IRISHomePage {

	//public String incidentNum = "21336";//enter incident number in TestData->IncidentNumber.txt file
	//21385-optimization site

	SeritiHomePage seritiHomePage = new SeritiHomePage();
	SignioNewApplicationPage signioNewApplicationPage = new SignioNewApplicationPage();
	String MyDealsInbox_ID = "nbClientSideEvents_I0i0_";
	String InboxRibbon_ID = "grdInbox_grouppanel";
	String DuplicateDealsPopupContinueBTN_ID = "btnIncidentDuplicate_CD";
	String MainMenu_DXI1_ = "MainMenu_DXI1_";
	//String AssignedDealOKButton_ID = "btnCustomMessageClose";
	String RetrieveConfirmationYESButton_ID = "//input[@id='btnConfirmOK_I' and @value='Yes']";
	String DealAssignedOKButton_ID = "btnCustomMessageClose_CD";

	public void verificationInboxClick(WebDriver driver) {

		WebElement verificationInbox = driver.findElement(By.id("nbClientSideEvents_I0i7_"));
		verificationInbox.click();

		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("grdGlobalDeals_grouppanel")));
	}
	
	public String readInputData(String key) throws IOException {
		InputStream input = new FileInputStream("src/TestData/Application.properties");
		Properties prop = new Properties();
		prop.load(input);
		String value = prop.getProperty(key);
		return value;
	}

	public void checkIncidentInMyDeals(WebDriver driver) throws InterruptedException, IOException {
		String path1 = System.getProperty("user.dir") + "\\" + "src\\TestData\\IncidentNumber.txt";

		String IncidentID = signioNewApplicationPage.readDataFromFile(path1, driver);
		if (isElementPresent(By.name("No data to display"), driver)) {
			clickOnAllIncidentInbox(driver);

		}

		else {
			Thread.sleep(2000);
			WebElement searchBox = driver.findElement(By.id("grdInbox_DXFREditorcol0_I"));
			Thread.sleep(3000);
			searchBox.clear();
			Thread.sleep(3000);
			// WebElement searchBox =
			// driver.findElement(By.id("grdInbox_DXFREditorcol0_I"));
			searchBox.sendKeys(IncidentID);
			Thread.sleep(2000);
			searchBox.sendKeys(Keys.ENTER);
			Thread.sleep(8000);
		}

	}

	public int[] checkDealinMyDeals(WebDriver driver) throws IOException {
		String path1 = System.getProperty("user.dir") + "\\" + "src\\TestData\\IncidentNumber.txt";

		String IncidentID = signioNewApplicationPage.readDataFromFile(path1, driver);
		IncidentID=IncidentID.replaceAll("\\s", "");
		int flag[] = {0,0};
		new WebDriverWait(driver, 100)
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("grdInbox_grouppanel")));
		boolean isPresent = driver.findElements(By.xpath("//*[@id=\"grdInbox_tccell0_0\"]/a")).size() > 0;
		if (isPresent){
			flag[0] = 1;
			WebElement incidentlink = driver.findElement(By.xpath("//*[@id=\"grdInbox_tccell0_0\"]/a"));
			String incidentid = incidentlink.getText();
			System.out.println(incidentid);
			System.out.println("In file"+IncidentID);
			if (incidentid.equals(IncidentID)) {
				flag[1] = 1;
			}
		}
		return flag;
	}

	public void clickOnAllIncidentInbox(WebDriver driver) {
		WebElement allIncidentInbox = driver.findElement(By.id("nbClientSideEvents_I0i2_"));
		allIncidentInbox.click();

		new WebDriverWait(driver, 100)
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("grdGlobalDeals_grouppanel")));
	}

	public void searchDealthroughIncidentNumber(WebDriver driver) throws InterruptedException, IOException {
		String path1 = System.getProperty("user.dir") + "\\" + "src\\TestData\\IncidentNumber.txt";

		String refID = signioNewApplicationPage.readDataFromFile(path1, driver);
		Thread.sleep(8000);
		WebElement searchBox = driver.findElement(By.id("grdGlobalDeals_DXFREditorcol0_I"));
		Thread.sleep(5000);
		searchBox.clear();
		Thread.sleep(5000);
		WebElement searchBox1 = driver.findElement(By.id("grdGlobalDeals_DXFREditorcol0_I"));
		searchBox1.sendKeys(refID);
		Thread.sleep(5000);
		searchBox1.sendKeys(Keys.ENTER);
		Thread.sleep(8000);

	}

	public void searchSeritiDealThroughReferenceNumber(WebDriver driver) throws InterruptedException, IOException {
		Thread.sleep(2000);

		String path1 = System.getProperty("user.dir") + "\\" + "src\\TestData\\SeritiTransactionID.txt";

		String refID = seritiHomePage.readDataFromFile(path1, driver);

		WebElement referenceIdSearchBox = driver.findElement(By.id("grdGlobalDeals_DXFREditorcol21_I"));
		referenceIdSearchBox.clear();
		referenceIdSearchBox.sendKeys(refID);

		referenceIdSearchBox.sendKeys(Keys.ENTER);

		Thread.sleep(8000);

	}

	public void searchSignioDealThroughReferenceNumber(WebDriver driver) throws InterruptedException, IOException {
		Thread.sleep(2000);

		String path1 = System.getProperty("user.dir") + "\\" + "src\\TestData\\IncidentNumber.txt";

		String refID = signioNewApplicationPage.readDataFromFile(path1, driver);

		WebElement referenceIdSearchBox = driver.findElement(By.id("grdGlobalDeals_DXFREditorcol0_I"));
		referenceIdSearchBox.clear();
		referenceIdSearchBox.sendKeys(refID);

		referenceIdSearchBox.sendKeys(Keys.ENTER);

		Thread.sleep(8000);
	}

	public void clickOnIncidentLink(WebDriver driver) throws InterruptedException {

		WebDriverWait wait1 = new WebDriverWait(driver, 180);
		Thread.sleep(4000);

		//driver.findElement(By.id("btnRefreshCImg")).click();

		// new WebDriverWait(driver,
		// 60).until(ExpectedConditions.visibilityOfElementLocated(By.id("grdGlobalDeals_grouppanel")));

		//WebElement searchedlink = driver.findElement(By.xpath("//*[@id='grdGlobalDeals_tccell0_0']/a"));



		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement searchedlink = driver.findElement(By.xpath("//*[@id='grdGlobalDeals_tccell0_0']/a"));
		Thread.sleep(2000);
		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(searchedlink)));
		searchedlink.click();

		// driver.findElement(By.linkText(IncidentNumber)).click(); // Select Incident
		// number.

		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnRetrieveDealAllIncident")));
	}

	public void retriveDeal(WebDriver driver) throws InterruptedException {

		WebElement retriveDealbtn = driver.findElement(By.xpath("//*[@id='btnRetrieveDealAllIncident']"));

		// new WebDriverWait(driver,
		// 100).until(ExpectedConditions.elementToBeClickable(retriveDealbtn));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(retriveDealbtn)).click();
//		Thread.sleep(8000);
//		retriveDealbtn.click();

//		Thread.sleep(6000);
//
	driver.switchTo().activeElement();



//		boolean OkButton = isElementPresent(By.id(AssignedDealOKButton_ID), driver);
//		if (OkButton)										//Previous Deal is NOT assigned to user
//		{
//			new WebDriverWait(driver, 60)
//					.until(ExpectedConditions.and(ExpectedConditions.visibilityOfElementLocated(By.id(AssignedDealOKButton_ID))));
//			driver.findElement(By.id(AssignedDealOKButton_ID)).click();
//		}
//		else												//Previous Deal is assigned to user
//		{
		Thread.sleep(6000);



//		if (isElementPresent(By.id("pcConfirmDialog_PWH-1"), driver)) {
//			// Wait for the pop-up to be visible
//			new WebDriverWait(driver, 10)
//					.until(ExpectedConditions.visibilityOfElementLocated(By.id("pcConfirmDialog_PWH-1")));

			// Click the "Yes" button inside the pop-up
//			WebElement yesButton = driver.findElement(By.id("RetrieveConfirmationYESButton_ID"));
//			if (yesButton.isDisplayed() && yesButton.isEnabled()) {
//				yesButton.click();
//			} else {
//				System.out.println("Yes button is not present or not clickable");
//			}
//		} else {
//			System.out.println("Pop-up with ID pcConfirmDialog_PWH-1 is not present");
//		}


		if (isElementPresent(By.xpath("RetrieveConfirmationYESButton_ID"), driver)) {
			new WebDriverWait(driver, 60)
					.until(ExpectedConditions.and(ExpectedConditions.visibilityOfElementLocated(By.xpath(RetrieveConfirmationYESButton_ID))));
			driver.findElement(By.xpath(RetrieveConfirmationYESButton_ID)).click();
		}else {
			System.out.println("Retrive button is not present");
		}
//			Thread.sleep(3500);
//			new WebDriverWait(driver, 60)
//					.until(ExpectedConditions.and(ExpectedConditions
//							.visibilityOfElementLocated(By.id(DealAssignedOKButton_ID))));
//			driver.findElement(By.id(DealAssignedOKButton_ID)).click();

//		}

//		if (isElementPresent(By.id("btnConfirmOK_CD"), driver)) {
//			new WebDriverWait(driver, 30).until(
//					ExpectedConditions.and(ExpectedConditions.visibilityOfElementLocated(By.id("btnConfirmOK_CD")),
//							ExpectedConditions.elementToBeClickable(By.id("btnConfirmOK_CD"))));
//
//			WebElement yesbtn = driver.findElement(By.id("btnConfirmOK_CD"));
//			yesbtn.click();
//
//		}

	//	WebElement okbtn = driver.findElement(By.id("btnCustomMessageClose_CD"));
		Thread.sleep(3000);
		// WebDriverWait wait1 = new WebDriverWait(driver, 100);
		// wait1.until(ExpectedConditions.elementToBeClickable(By.id("btnCustomMessageClose_CD")));
	//	okbtn.click();

		//================
		boolean continueButton = isElementPresent(By.id("btnIncidentDuplicate_CD"), driver);
		if (continueButton) {
			new WebDriverWait(driver, 30).until(ExpectedConditions.and(
					ExpectedConditions.visibilityOfElementLocated(By.id("btnIncidentDuplicate_CD")),
					ExpectedConditions.elementToBeClickable(By.id("btnIncidentDuplicate_CD"))));

			// if (driver.findElement(By.id("btnIncidentDuplicate_CD")).isDisplayed()) {

			driver.findElement(By.id("btnIncidentDuplicate_CD")).click();
			// }
		} else {
			System.out.println("continue button is not present");
		}


		//=============
//
//		new WebDriverWait(driver, 30)
//				.until(ExpectedConditions.visibilityOfElementLocated(By.id("nbClientSideEvents_I0i0_")));

		// driver.switchTo().window(parentWindow);
	}

	public void clickOnMyDeals(WebDriver driver) throws InterruptedException {

		Thread.sleep(10000);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("blockUI")));
		//driver.findElement(By.id(MainMenu_DXI1_)).click();


//		WebElement mydealBox = driver.findElement(By.id("nbClientSideEvents_I0i0_"));
//		//Thread.sleep(2000);
//	//	WebDriverWait wait = new WebDriverWait(driver, 30);
//		wait.until(ExpectedConditions.elementToBeClickable(mydealBox));
//		mydealBox.click();

//Thread.sleep(5000);
//
//// Check visibility of "grdInbox_grouppanel" element
//if (isElementVisible(By.id("grdInbox_grouppanel"), driver)) {
//    new WebDriverWait(driver, 30)
//            .until(ExpectedConditions.visibilityOfElementLocated(By.id("grdInbox_grouppanel")));
//
//    Thread.sleep(7000);
//
//    WebElement incidentClick = driver.findElement(By.xpath("//*[@id='grdInbox_tccell0_0']/a"));
//    Thread.sleep(7000);
//    new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(incidentClick));
//    Thread.sleep(7000);
//    incidentClick.click();
//
//    boolean continueButtonPresent = isElementPresent(By.id("btnIncidentDuplicate_CD"), driver);
//
//    if (continueButtonPresent) {
//        new WebDriverWait(driver, 30)
//                .until(ExpectedConditions.and(
//                        ExpectedConditions.visibilityOfElementLocated(By.id("btnIncidentDuplicate_CD")),
//                        ExpectedConditions.elementToBeClickable(By.id("btnIncidentDuplicate_CD"))));
//
//        driver.findElement(By.id("btnIncidentDuplicate_CD")).click();
//    } else {
//        System.out.println("Continue button is not present");
//    }
//} else {
//    System.out.println("Element with id 'grdInbox_grouppanel' is not visible");
//}
//
//


		Thread.sleep(5000);

		boolean gridInbox = isElementPresent(By.id("grdInbox_grouppanel"), driver);
		if (gridInbox){
		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("grdInbox_grouppanel")));
		Thread.sleep(7000);
		WebElement incidentClick = driver.findElement(By.xpath("//*[@id='grdInbox_tccell0_0']/a"));
		Thread.sleep(7000);
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(incidentClick));
		Thread.sleep(7000);
		incidentClick.click();
	} else {
			System.out.println("Grid Inbox is not present");
		}
//
//		Thread.sleep(8000);

		boolean continueButton = isElementPresent(By.id("btnIncidentDuplicate_CD"), driver);

		if (continueButton) {
			new WebDriverWait(driver, 30).until(ExpectedConditions.and(
					ExpectedConditions.visibilityOfElementLocated(By.id("btnIncidentDuplicate_CD")),
					ExpectedConditions.elementToBeClickable(By.id("btnIncidentDuplicate_CD"))));

			// if (driver.findElement(By.id("btnIncidentDuplicate_CD")).isDisplayed()) {

			driver.findElement(By.id("btnIncidentDuplicate_CD")).click();
			// }
		} else {
			System.out.println("continue button is not present");
		}
	}

	public boolean isElementPresent(By by, WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		} finally {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	}
}
