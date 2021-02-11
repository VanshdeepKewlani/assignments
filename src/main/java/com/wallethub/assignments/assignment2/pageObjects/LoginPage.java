/**
 * 
 */
package com.wallethub.assignments.assignment2.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wallethub.assignments.assignment2.managers.DriverManager;
import com.wallethub.assignments.utilities.CommonLibrary;

/**
 * @author VanshdeepK
 *
 */
public class LoginPage {
	private static final Logger LOG = LoggerFactory.getLogger(LoginPage.class);
	WebDriver driver;
	CommonLibrary commonFuncs;
		
	By lnkLogin = By.xpath("//a[text()='Login']");
	By txtUserName = By.xpath("//input[@type='text' and @placeholder='Email Address']");
	By txtPassword = By.xpath("//input[@type='password']");
	By btnLogin = By.tagName("button");
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
		commonFuncs = new CommonLibrary(driver);
		LOG.info("Loaded LoginPage");
	}
	
	public void login(){
		commonFuncs.clickDirectly(lnkLogin);
		commonFuncs.typeDirectly(txtUserName, DriverManager.getInstance().getUserName());
		commonFuncs.typeDirectly(txtPassword, DriverManager.getInstance().getPassword());
		commonFuncs.clickDirectly(btnLogin);
		commonFuncs.waitFor(5000);
	}
	
	public void navigateToInsurancePage(){
		driver.get("http://wallethub.com/profile/test_insurance_company/");
		commonFuncs.waitFor(2000);
		String currentTitle = commonFuncs.verifyWindowTitle("test insurance company metatitle test");
		if(!currentTitle.isEmpty()){
			System.out.println("Current window title does not match expected window title " + "test insurance company metatitle test, instead it is " + currentTitle); 
		}
	}
}
