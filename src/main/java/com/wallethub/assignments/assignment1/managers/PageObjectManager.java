/**
 * 
 */
package com.wallethub.assignments.assignment1.managers;

import org.openqa.selenium.WebDriver;

import com.wallethub.assignments.assignment1.pageObjects.HomePage;
import com.wallethub.assignments.assignment1.pageObjects.LoginPage;
import com.wallethub.assignments.utilities.CommonLibrary;

/**
 * @author VanshdeepK
 *
 */
public class PageObjectManager {

	WebDriver driver;
	CommonLibrary commonFuncs;
	LoginPage loginPage;
	HomePage homePage;
	
	public PageObjectManager(WebDriver driver){
		this.driver = driver;
	}
	
	public CommonLibrary getCommonLibraryFunctions() {
		return (commonFuncs == null) ? commonFuncs = new CommonLibrary(driver) : commonFuncs;
	}
	
	public LoginPage getLoginPage() {
		return (loginPage == null) ? loginPage = new LoginPage(driver) : loginPage;
	}
	
	public HomePage getHomePage() {
		return (homePage == null) ? homePage = new HomePage(driver) : homePage;
	}
	
}
