package com.wallethub.assignmentstest.assignments2_test;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.wallethub.assignments.assignment1.managers.DriverManager;
import com.wallethub.assignments.assignment2.context.TestContext;
import com.wallethub.assignments.assignment2.enums.Context;
import com.wallethub.assignments.assignment2.pageObjects.ConfirmReviewPage;
import com.wallethub.assignments.assignment2.pageObjects.LoginPage;
import com.wallethub.assignments.assignment2.pageObjects.TestInsuranceCompanyPage;
import com.wallethub.assignments.utilities.CommonLibrary;

public class CreateReviewTest {

	private static final Logger LOG = LoggerFactory.getLogger(CreateReviewTest.class);
	private TestContext testContext;
	CommonLibrary commonFuncs;
	WebDriver driver;
	LoginPage loginPage;
	TestInsuranceCompanyPage testInsuranceCompanyPage;
	ConfirmReviewPage confirmationPage;

	public CreateReviewTest() {
		testContext = new TestContext();
		driver = (WebDriver) testContext.getDriverManager().initDriver();
		commonFuncs = new CommonLibrary(driver);
		loginPage = testContext.getPageObjectManager().getLoginPage();
		LOG.info("Loaded CreateReviewTest");
		
	}

	/** This Test is used to verify whether user is able to post review and then confirming it on Test Insurance Company Home page
	 * 
	 * @throws Exception Custom Exception thrown by code
	 */
	@Test
	public void addReview() throws Exception {
		loginPage.login();
		loginPage.navigateToInsurancePage();
		testInsuranceCompanyPage = testContext.getPageObjectManager().getTestInsuranceCompanyPage();
		int numberOfStars = 4;

		testInsuranceCompanyPage.selectStars(numberOfStars);

		// Saving to Context the number of stars being highlighted for future verification
		testContext.getScenarioContext().setContext(Context.NUM_OF_STARS, numberOfStars);

		String reviewText = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec qu";

		// Saving to Context the review text for future verification
		testContext.getScenarioContext().setContext(Context.REVIEWTEXT, reviewText);

		testInsuranceCompanyPage.writeReview("Health Insurance", reviewText);

		confirmationPage = testContext.getPageObjectManager().getConfirmationPage();

		int numberOfHighlightedStars = (int) testContext.getScenarioContext().getContext(Context.NUM_OF_STARS);
		String postedReviewText = (String) testContext.getScenarioContext().getContext(Context.REVIEWTEXT);

		confirmationPage.verifyReviewConfirmation(numberOfHighlightedStars, postedReviewText);
		confirmationPage.continueFromConfirmationPage();
		
		testInsuranceCompanyPage.verifyPostedReview(numberOfHighlightedStars, postedReviewText);

	}
	
	@AfterClass
	public void testScreenshot(){
		commonFuncs.captureScreenshot("CreateReviewTest", testContext.getDriverManager().getDriver());
		testContext.getDriverManager().closeDriver();
	}
}
