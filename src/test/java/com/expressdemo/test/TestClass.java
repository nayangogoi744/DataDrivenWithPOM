/**
 * 
 */
package com.expressdemo.test;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.expressdemo.pom.HomePageObject;
import com.expressdemo.pom.LoginPageObject;
import com.expressdemo.utility.Base;
import com.expressdemo.utility.ReadExcel;

/**
 * @author NJY
 *
 */
public class TestClass extends Base{

	
	@DataProvider(name="providelogininfo")
	public Object[][] getExcelData() throws InvalidFormatException, IOException{
		return ReadExcel.getCellData("./testdata/loginInfo.xlsx", "Sheet1");
		
		}

	
	@Test(priority=1,description="This TC will perform valid login",dataProvider="providelogininfo")
	public void loginToApplication(String username,String password) throws Throwable
	{
		LoginPageObject loginpage = PageFactory.initElements(driver, LoginPageObject.class);
		loginpage.loginEnterPrise(username, password);
		boolean check = verifyDashBoardisPresent();
		Assert.assertEquals(true,check);
		Reporter.log("============Inside the Home Page============",true);
	}
	
	@Test(priority=2,description="Apply leave")
	public void applyFirstLeave() throws InterruptedException{
		HomePageObject homepage=PageFactory.initElements(driver, HomePageObject.class);
		homepage.applyLeave();
		Thread.sleep(3000);
	}
}
