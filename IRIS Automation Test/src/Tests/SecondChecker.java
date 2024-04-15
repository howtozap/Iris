package Tests;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pages.IRISHomePage;
import Pages.IRISLoginPage;
import Pages.SecondCheckerScreen;

//Pre requisite- deal should be in your mydeal inbox
public class SecondChecker extends BasicSetup {

	public static WebDriver driver;
	//static String IncidentNumber = "123138";
	String InformationpopupOK = "btnCustomMessageClose_CD";
	String SecondCheckerTab = "rndPnlSecondChecker_RPHT";
	String SignedContractRecDocDropDown = "cmbdocSCD_rcv_B-1";
	String SignedContractRecYesDD = "cmbdocSCD_rcv_DDD_L_LBI1T0";
	String SignedContractChkDropDown = "cmbdocSCD_chk_B-1";
	String SignedContractChkYesDD = "cmbdocSCD_chk_DDD_L_LBI1T0";
	String InvoiceRecDropDown = "cmbdocInvoice_rcv_B-1";
	String InvoiceRecYesDD = "cmbdocInvoice_rcv_DDD_L_LBI1T0";
	String InvoiceChkDropDown = "cmbdocInvoice_chk_B-1";
	String InvoiceChkYesDD = "cmbdocInvoice_chk_DDD_L_LBI1T0";
	String DICRecDropDown = "cmbdocDIC_rcv_B-1";
	String DICRecYesDD = "cmbdocDIC_rcv_DDD_L_LBI2T0";
	String DICChkDropDown = "cmbdocDIC_chk_B-1";
	String DICChkYesDD = "cmbdocDIC_chk_DDD_L_LBI2T0";
	String WarrantyRecDropDown = "cmbdocWarrantyInvoice_rcv_B-1";
	String WarrantyRecYesDD = "cmbdocWarrantyInvoice_rcv_DDD_L_LBI2T0";
	String WarrantyChkDropDown = "cmbdocWarrantyInvoice_chk_B-1";
	String WarrantyChkYesDD = "cmbdocWarrantyInvoice_chk_DDD_L_LBI2T0";
	String EnatisRecDropDown = "cmbdoceNatis_rcv_B-1";
	String EnatisRecYesDD = "cmbdoceNatis_rcv_DDD_L_LBI2T0";
	String EnatisChkDropDown = "cmbdoceNatis_chk_B-1";
	String EnatisYesDD = "cmbdoceNatis_chk_DDD_L_LBI2T0";
	String ReleaseNotesRecDropDown = "cmbdocReleaseNotes_rcv_B-1";
	String ReleaseNotesRecYesDD = "cmbdocReleaseNotes_rcv_DDD_L_LBI1T0";
	String ReleaseNotesChkDropDown = "cmbdocReleaseNotes_chk_B-1";
	String ReleaseNotesChkYesDD = "cmbdocReleaseNotes_chk_DDD_L_LBI1T0";
	String ProofofAddressRecDropDown = "cmbdocPOA_rcv_B-1";
	String ProofofAddressRecYesDD = "cmbdocPOA_rcv_DDD_L_LBI1T0";
	String ProofofAddressChkDropDown = "cmbdocPOA_chk_B-1";
	String ProofofAddressChkYesDD = "cmbdocPOA_chk_DDD_L_LBI1T0";
	String Addcomment = "txtCommentsSecondChecker";
	String SaveButton = "btnDocSave_CD";
	String Confirmpopup_ID = "confirmText";
	String ConfirmPopupYES_BTN_ID = "btnConfirmOK_CD";
	String Informationpopup = "customMessageText";
	String InformationpopupOKBTN_ID = "btnCustomMessageClose_CD";
	String ActivateDealButton = "btnActivateDeal_CD";
	String InitiatePayout = "btnRegVehicle_CD";
	String WaitforInboxScreen = "nbClientSideEvents_I0i12_";
	String AddNotesButton = "btnNotes";
	String NotesPopup = "popCntNotes_PWH-1T";
	String NotesCategoryDD = "cmbNoteCategory_B-1";
	String PayoutNotesDD = "cmbNoteCategory_DDD_L_LBI6T0";
	String AddNote = "txtNoteDesc";
	String SaveNote = "btnSaveNote_CD";
	IRISLoginPage iRISLoginPage = new IRISLoginPage();
	IRISHomePage iRISHomePage = new IRISHomePage();

	SecondCheckerScreen secondCheckerScreen = new SecondCheckerScreen();

	
	@BeforeClass(enabled = true)
	public void login() throws InterruptedException, IOException {

		 iRISLoginPage.NavigateToOptima(driver);
		 iRISLoginPage.LoginToOptima(driver);
	}
	
	@BeforeTest(enabled = true)
	public void setup() throws InterruptedException, IOException {
		SecondChecker secondChecker = new SecondChecker();
	}

	public SecondChecker() {
		driver = getdriver();
	}

	@Test(priority = 9)
	public void secondCheckerChecking() throws InterruptedException, IOException {
		new WebDriverWait(driver, 180)
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("nbClientSideEvents_I0i2_")));
		
		int flag[] = iRISHomePage.checkDealinMyDeals(driver);

		if (flag[1] == 0) {

			iRISHomePage.clickOnAllIncidentInbox(driver);

			iRISHomePage.searchDealthroughIncidentNumber(driver);

			iRISHomePage.clickOnIncidentLink(driver);

			if(flag[0]==0) {
				
				WebElement retriveDealbtn = driver.findElement(By.xpath("//*[@id='btnRetrieveDealAllIncident']"));
				Thread.sleep(8000);
				retriveDealbtn.click();

				WebElement okbtn = driver.findElement(By.id(InformationpopupOK));
				new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.id(InformationpopupOK)));

				okbtn.click(); 
				
			}
			
			else {
				
				iRISHomePage.retriveDeal(driver);
				
			}
		Thread.sleep(8000);
		}

		else {
			System.out.println("Incident is present in My Deals");
		}

		iRISHomePage.clickOnMyDeals(driver);

		WebDriverWait wait1 = new WebDriverWait(driver, 100);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(SecondCheckerTab)));

		// Signed Contract Document

		driver.findElement(By.id(SignedContractRecDocDropDown)).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(SignedContractRecYesDD)));
		driver.findElement(By.id(SignedContractRecYesDD)).click();

		wait1.until(ExpectedConditions.elementToBeClickable(By.id(SignedContractChkDropDown)));

		driver.findElement(By.id(SignedContractChkDropDown)).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(SignedContractChkYesDD)));
		driver.findElement(By.id(SignedContractChkYesDD)).click();

		// Invoice

		driver.findElement(By.id(InvoiceRecDropDown)).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(InvoiceRecYesDD)));
		driver.findElement(By.id(InvoiceRecYesDD)).click();

		wait1.until(ExpectedConditions.elementToBeClickable(By.id(InvoiceChkDropDown)));

		driver.findElement(By.id(InvoiceChkDropDown)).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(InvoiceChkYesDD)));
		driver.findElement(By.id(InvoiceChkYesDD)).click();

		// DIC Invoice

		driver.findElement(By.id(DICRecDropDown)).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(DICRecYesDD)));
		driver.findElement(By.id(DICRecYesDD)).click();

		wait1.until(ExpectedConditions.elementToBeClickable(By.id(DICChkDropDown)));

		driver.findElement(By.id(DICChkDropDown)).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(DICChkYesDD)));
		driver.findElement(By.id(DICChkYesDD)).click();

		// Warranty Invoice

		driver.findElement(By.id(WarrantyRecDropDown)).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(WarrantyRecYesDD)));
		driver.findElement(By.id(WarrantyRecYesDD)).click();

		wait1.until(ExpectedConditions.elementToBeClickable(By.id(WarrantyChkDropDown)));

		driver.findElement(By.id(WarrantyChkDropDown)).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(WarrantyChkYesDD)));
		driver.findElement(By.id(WarrantyChkYesDD)).click();

		// eNATIS Document

		driver.findElement(By.id(EnatisRecDropDown)).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(EnatisRecYesDD)));
		driver.findElement(By.id(EnatisRecYesDD)).click();

		wait1.until(ExpectedConditions.elementToBeClickable(By.id(EnatisChkDropDown)));

		driver.findElement(By.id(EnatisChkDropDown)).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(EnatisYesDD)));
		driver.findElement(By.id(EnatisYesDD)).click();

		// Release Notes

		driver.findElement(By.id(ReleaseNotesRecDropDown)).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(ReleaseNotesRecYesDD)));
		driver.findElement(By.id(ReleaseNotesRecYesDD)).click();

		wait1.until(ExpectedConditions.elementToBeClickable(By.id(ReleaseNotesChkDropDown)));

		driver.findElement(By.id(ReleaseNotesChkDropDown)).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(ReleaseNotesChkYesDD)));
		driver.findElement(By.id(ReleaseNotesChkYesDD)).click();

		// Proof of Address

		driver.findElement(By.id(ProofofAddressRecDropDown)).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(ProofofAddressRecYesDD)));
		driver.findElement(By.id(ProofofAddressRecYesDD)).click();

		wait1.until(ExpectedConditions.elementToBeClickable(By.id(ProofofAddressChkDropDown)));

		driver.findElement(By.id(ProofofAddressChkDropDown)).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(ProofofAddressChkYesDD)));
		driver.findElement(By.id(ProofofAddressChkYesDD)).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 900)");
		
        WebElement comment=driver.findElement(By.id(Addcomment));
        Thread.sleep(1000);
        comment.clear();
        Thread.sleep(1000);

        comment.sendKeys("test");

		// Click on save button

		WebElement saveButton = driver.findElement(By.id(SaveButton));
		saveButton.click();

		// click on Yes button
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(Confirmpopup_ID)));

		assertTrue(driver.findElement(By.id(Confirmpopup_ID)).isDisplayed(),
				"Are you sure you want to save the information?");
		Thread.sleep(1000);
		WebElement yesBtn = driver.findElement(By.id(ConfirmPopupYES_BTN_ID));
		yesBtn.click();

		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(Informationpopup)));

		assertTrue(driver.findElement(By.id(Informationpopup)).isDisplayed(), "Information saved successfully.");

		WebElement yesBtn1 = driver.findElement(By.id(InformationpopupOKBTN_ID));
		yesBtn1.click();

		// }

		// Click on activate deal

		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(ActivateDealButton)));

		WebElement activateDealButton = driver.findElement(By.id(ActivateDealButton));
		activateDealButton.click();

		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(Confirmpopup_ID)));

		assertTrue(driver.findElement(By.id(Confirmpopup_ID)).isDisplayed(), "Are you sure you want to activate deal?");

		WebElement yesBtn2 = driver.findElement(By.id(ConfirmPopupYES_BTN_ID));
		yesBtn2.click();

		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(Informationpopup)));
		
		String message=driver.findElement(By.id(Informationpopup)).getText();
		
		System.out.println("Activate Deal Validation Message: "+message);

		driver.findElement(By.id(InformationpopupOKBTN_ID)).click();
		
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(Informationpopup)));

		driver.findElement(By.id(InformationpopupOKBTN_ID)).click();

		// click on initiate payout
		Thread.sleep(10000);
		wait1.until(ExpectedConditions.elementToBeClickable(By.id(InitiatePayout)));

		driver.findElement(By.id(InitiatePayout)).click();

		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(Informationpopup)));

		assertTrue(driver.findElement(By.id(Informationpopup)).isDisplayed(),
				"Payout Process Initiated Successfully.");

		driver.findElement(By.id(InformationpopupOKBTN_ID)).click();

		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(WaitforInboxScreen)));

		// Payout Note

		iRISHomePage.clickOnAllIncidentInbox(driver);
		iRISHomePage.searchDealthroughIncidentNumber(driver);
		
		Thread.sleep(2000);

		WebElement searchedlink = driver.findElement(By.xpath("//*[@id='grdGlobalDeals_tccell0_0']/a"));
		Thread.sleep(2000);
		new WebDriverWait(driver,30).until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(searchedlink)));		
		searchedlink.click();
		
		Thread.sleep(5000);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(AddNotesButton)));

		driver.findElement(By.id(AddNotesButton)).click();

		driver.switchTo().activeElement();

		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(NotesPopup)));

		// click on note category dropdown

		driver.findElement(By.id(NotesCategoryDD)).click();

		// select payout note option
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(PayoutNotesDD)));

		driver.findElement(By.id(PayoutNotesDD)).click();

		// write note

		WebElement note = driver.findElement(By.id(AddNote));
		note.sendKeys("Dear Cutomer, your deal is activated now. Thanks");

		// click on save button

		driver.findElement(By.id(SaveNote)).click();

		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(Informationpopup)));

		assertTrue(driver.findElement(By.id(Informationpopup)).isDisplayed(), "Note saved successfully.");

		// click on ok button
		driver.findElement(By.id(InformationpopupOKBTN_ID)).click();

		System.out.print("Payout is done");

	}
}
