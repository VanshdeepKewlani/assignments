/**
 * 
 */
package com.wallethub.assignments.assignment2.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import com.wallethub.assignments.utilities.CommonLibrary;

/**
 * @author VanshdeepK
 *
 */
public class ConfirmReviewPage {
	private static final Logger LOG = LoggerFactory.getLogger(ConfirmReviewPage.class);
	WebDriver driver;
	CommonLibrary commonFuncs;

	By header = By.className("rvc-header");
	By lstImgStars = By.cssSelector("review-star svg");
	By lstImgStarsHighlighted = By.cssSelector("review-star svg g path[stroke='#4ae0e1']");
	By lblReviewText = By.className("rvc-body-middle");
	By btnContinue = By.cssSelector(".btn.rvc-continue-btn");

	public ConfirmReviewPage(WebDriver driver) {
		this.driver = driver;
		commonFuncs = new CommonLibrary(driver);
		LOG.info("Loaded ConfirmReviewPage");
	}

	/** This method is used to confirm the review details user has posted before submitting permanently. It acts as a check before submission
	 * 
	 * @param numberOfHighlightedStars Number of Stars Highlighted during review creation
	 * @param postedReviewText Review text entered during review creation
	 */
	public void verifyReviewConfirmation(int numberOfHighlightedStars, String postedReviewText) {
		commonFuncs.waitFor(10000);
		Assert.assertEquals(commonFuncs.verifyWindowTitle("WalletHub - Review Confirmation"), "",
				"Window title is not of Confirmation Page");
		Assert.assertTrue(driver.findElement(header).getText().contains("Your review has been posted."),
				"User has not landed on Confirmation page");

		int numOfHighlightedStars = driver.findElements(lstImgStarsHighlighted).size();

		// Verifying whether the number of highlighted stars are same as
		// selected by user
		Assert.assertEquals(numOfHighlightedStars, numberOfHighlightedStars,
				"Count of highlighted stars in Confirmation page is different from the count of stars highlighted by user in Insurance Home page");

		Assert.assertEquals(driver.findElement(lblReviewText).getText(), postedReviewText,
				"Review text on confirmation page does not match the review text posted by user");
	}

	public void continueFromConfirmationPage() {
		commonFuncs.clickDirectly(btnContinue);
		commonFuncs.waitFor(1000);
	}
}
