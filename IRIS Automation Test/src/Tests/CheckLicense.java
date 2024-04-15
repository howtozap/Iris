package Tests;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class CheckLicense {
	
	String filename;
	
	@Test(priority=1)
	public void downloadPDF(WebDriver driver) throws InterruptedException, IOException {
		
		Thread.sleep(5000);
		String oldTab = driver.getWindowHandle();
		driver.findElement(By.id("tbInvoiceDetailsTababc_T6T")).click();
		Thread.sleep(7000);
		WebElement searchBox1 = driver.findElement(By.id("grdDocuments_DXFREditorcol0_I"));
		searchBox1.sendKeys("Podium");
		Thread.sleep(8000);
		searchBox1.sendKeys(Keys.ENTER);
		Thread.sleep(8000);
		filename=driver.findElement(By.xpath("//*[@id='grdDocuments_DXDataRow0']/td[2]")).getText();
		System.out.println(filename);
		driver.findElement(By.xpath("//*[@id='grdDocuments_DXDataRow0']/td[4]")).click();
		Thread.sleep(10000);
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		newTab.remove(oldTab);
		// change focus to new tab
		driver.switchTo().window(newTab.get(0));
		Thread.sleep(10000);
		Runtime.getRuntime().exec("D:\\Optima\\filedownload.exe");
		Thread.sleep(30000);
		driver.close();
		driver.switchTo().window(oldTab);
		Thread.sleep(5000);
		driver.findElement(By.id("tbInvoiceDetailsTababc_T0T")).click();
		Thread.sleep(8000);
		
	}
	
	@Test
	public void readPDF() throws IOException {
		
		File file = new File("C:\\Users\\RiddhiT\\Downloads\\"+filename+"");
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream fileParse = new BufferedInputStream(fis);
		PDDocument document = null;
		
		document = PDDocument.load(fileParse);
		String PDFContent = new PDFTextStripper().getText(document);
		if(PDFContent.contains("This application was created using the trial version of the XtraReports.")) {
			
			System.out.println("This is trial version");
			
		}		
		
	}

}
