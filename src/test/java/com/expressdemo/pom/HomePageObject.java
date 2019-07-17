/**
 * 
 */
package com.expressdemo.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * @author NJY
 *
 */
public class HomePageObject {

	
WebDriver driver;
	
	public HomePageObject(WebDriver driver) {
		this.driver=driver;
	}
	
	@FindBy(how=How.XPATH,using="//span[text()='Leave']")
	WebElement leave;
	
	@FindBy(how=How.XPATH,using="//span[text()='Apply']")
	WebElement apply;
	
	//@FindBy(how=How.XPATH,using="//ul[@class='dropdown-content select-dropdown']/li/span")
	//List<WebElement> dropdown;
	
	
	public void applyLeave() throws InterruptedException{
		leave.click();
		apply.click();
		Thread.sleep(2000);
		
		/*List<WebElement> drop = dropdown;
		System.out.println("Inside leave");
		for(WebElement el:drop){
			String select = el.getAttribute("innerHTML");
			/*if(select.equals(leaveSelect)){
				el.click();
				break;
			}
			System.out.println("Leaves: ===="+select);
		}*/
		
	}
}
