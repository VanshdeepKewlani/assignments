/**
 * 
 */
package com.wallethub.assignments.assignment2.managers;

import org.openqa.selenium.WebDriver;
import com.wallethub.assignments.assignment2.pageObjects.*;
import com.wallethub.assignments.utilities.CommonLibrary;

/**
 * @author VanshdeepK
 *
 */
public class PageObjectManager {

	WebDriver driver;
	CommonLibrary commonFuncs;
	LoginPage loginPage;
	TestInsuranceCompanyPage testInsuranceCompanyPage;
	ConfirmReviewPage confirmPage;
	
	public PageObjectManager(WebDriver driver){
		this.driver = driver;
	}
	
	public CommonLibrary getCommonLibraryFunctions() {
		return (commonFuncs == null) ? commonFuncs = new CommonLibrary(driver) : commonFuncs;
	}
	
	public LoginPage getLoginPage() {
		return (loginPage == null) ? loginPage = new LoginPage(driver) : loginPage;
	}
	
	public TestInsuranceCompanyPage getTestInsuranceCompanyPage() {
		return (testInsuranceCompanyPage == null) ? testInsuranceCompanyPage = new TestInsuranceCompanyPage(driver) : testInsuranceCompanyPage;
	}
	
	public ConfirmReviewPage getConfirmationPage() {
		return (confirmPage == null) ? confirmPage = new ConfirmReviewPage(driver) : confirmPage;
	}
	
}
