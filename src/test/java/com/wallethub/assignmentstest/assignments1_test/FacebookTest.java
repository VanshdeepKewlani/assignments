package com.wallethub.assignmentstest.assignments1_test;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.wallethub.assignments.assignment1.context.TestContext;
import com.wallethub.assignments.assignment1.managers.DriverManager;
import com.wallethub.assignments.assignment1.pageObjects.HomePage;
import com.wallethub.assignments.assignment1.pageObjects.LoginPage;
import com.wallethub.assignments.utilities.CommonLibrary;

public class FacebookTest {
	private static final Logger LOG = LoggerFactory.getLogger(FacebookTest.class);
	private TestContext testContext;
	CommonLibrary commonFuncs;
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;


	public FacebookTest() {
		testContext = new TestContext();
		driver = (WebDriver) testContext.getDriverManager().initDriver();
		loginPage = testContext.getPageObjectManager().getLoginPage();
		commonFuncs = new CommonLibrary(driver);
	}

	@Test
	public void testPostCreation() {
		LOG.info("Validating whether user is able to post feed in Facebook");
		loginPage.login();
		homePage = testContext.getPageObjectManager().getHomePage();
		homePage.handleOptionalDialog();
		homePage.createPost();
		assertEquals(homePage.getPost(), DriverManager.getInstance().getFeed());
	}
	
	@AfterClass
	public void testScreenshot(){
		commonFuncs.captureScreenshot("FacebookTest", DriverManager.getInstance().getDriver());
		DriverManager.getInstance().closeDriver();
	}
	
	
}
