/**
 * 
 */
package com.expressdemo.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.expressdemo.utility.Base;


/**
 * @author NJY
 *
 */
public class LoginPageObject {

	
	WebDriver driver;
	public LoginPageObject(WebDriver driver){
		this.driver=driver;
	}
	
	@FindBy(how=How.NAME,using="txtUsername")
	WebElement username;
	
	
	@FindBy(how=How.ID,using="txtPassword")
	WebElement password;
	
	@FindBy(how=How.ID,using="btnLogin")
	WebElement loginbtn;
	
	public void loginEnterPrise(String uid,String pass) throws InterruptedException{
		Base.highLightElement(driver, username);
		username.clear();
		username.sendKeys(uid);
		Base.highLightElement(driver, password);
		password.clear();
		password.sendKeys(pass);
		Base.highLightElement(driver, loginbtn);
		loginbtn.click();
		Thread.sleep(3000);
	}
}
