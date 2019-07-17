/**
 * 
 */
package com.expressdemo.utility;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author NJY
 *
 */
public class Base {

	public static WebDriver driver;
	DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy h-m-s");
    Date date;
	/*public static WebDriver startBrowser(String browsername, String url){
	
		
		if(browsername=="chrome"){
			WebDriverManager.chromedriver().setup();
			driver =  new ChromeDriver();
		}
		else if(browsername=="firefox"){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browsername=="IE"){
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}
		driver.manage().window().maximize();
	    driver.get(url);
		return driver;
		
	}*/
	
	@BeforeClass
	public void startBrowser(){
		WebDriverManager.chromedriver().setup();
		driver =  new ChromeDriver();
		driver.manage().window().maximize();
		//driver.get("http://enterprise.demo.orangehrmlive.com/symfony/web/index.php/auth/login");
		driver.get("https://orangehrm-demo-6x.orangehrmlive.com/auth/login");
	    Reporter.log("==========Browser Started===========",true);
	}
	
	@AfterClass
	public void closeBrowser(){
		driver.quit();
		Reporter.log("==========Browser Killed===========",true);
	}
	
	@AfterMethod
	public void tearDown(ITestResult testResult) throws InterruptedException{
		
		if(testResult.getStatus()==ITestResult.FAILURE){
			date = new Date();
			captureScreenShot(driver, dateFormat.format(date)+testResult.getName());
			driver.navigate().back();
			Thread.sleep(2000);
			
		}
		 
	}
	
	
	
	public static void highLightElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {

			System.out.println(e.getMessage());
		}

		js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", element);

	}
	
	public static void captureScreenShot(WebDriver driver,String screenshotname){
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;						
			File source =  ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File("./screenshots/"+screenshotname+".png"));
			
		} catch (Exception e) {
			System.out.println("Exception while taking screenshot" +e.getMessage());
		} 
	}
	
	public static boolean verifyDashBoardisPresent(){
		WebElement dashboard = driver.findElement(By.xpath("//li[text()='Dashboard']"));
		return dashboard.isDisplayed();
	}
	
	
}
