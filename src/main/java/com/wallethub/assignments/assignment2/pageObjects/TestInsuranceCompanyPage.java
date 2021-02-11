/**
 * 
 */
package com.wallethub.assignments.assignment2.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.wallethub.assignments.utilities.CommonLibrary;

/**
 * @author VanshdeepK
 *
 */
public class TestInsuranceCompanyPage {
	private static final Logger LOG = LoggerFactory.getLogger(TestInsuranceCompanyPage.class);
	WebDriver driver;
	CommonLibrary commonFuncs;

	By lstImgStars = By.cssSelector(".rv.review-action svg");
	By lstImgStarsHighlighted = By.cssSelector(".rv.review-action svg g path[stroke='#4ae0e1']");
	By parentDropdownPolicy = By.cssSelector("ng-dropdown.wrev-drp");
	By childrenDropdownPolicy = By.cssSelector("ng-dropdown.wrev-drp li");
	By txtWriteReview = By.xpath("//textarea[contains(@placeholder, 'Write your review')]");
	By lblReviewCharCount = By.cssSelector(".wrev-user-input-count span.bold-font");
	By btnSubmit = By.xpath("//div[text()='Submit']");
	By lstImgStarsPosted = By.cssSelector("article:nth-child(1).rvtab-item-user svg path[stroke='#4ae0e1']");
	By lblReviewPosted = By.cssSelector(".rvtac-ci-cexp");

	public TestInsuranceCompanyPage(WebDriver driver) {
		this.driver = driver;
		commonFuncs = new CommonLibrary(driver);
		LOG.info("Loaded TestInsuranceCompanyPage");
	}

	public void selectStars(int numberOfStars) {
		List<WebElement> elemStars = driver.findElements(lstImgStars);
		WebElement elemStarPos = elemStars.get(numberOfStars - 1);
		// Instantiate Action Class
		Actions actions = new Actions(driver);
		// Mouse hover Star at specified position
		actions.moveToElement(elemStarPos).perform();
		System.out.println("Done Mouse hover on the Star at specified position");
		// Click on the star at specific position
		commonFuncs.clickDirectly(elemStarPos);

		int numOfHighlightedStars = driver.findElements(lstImgStarsHighlighted).size();

		if (numOfHighlightedStars == numberOfStars) {
			System.out.println(numberOfStars + "Stars are highlighted");
		} else {
			System.out.println("No of stars highlighted are not 4 and instead are " + numOfHighlightedStars);
		}

	}

	public void writeReview(String policy, String reviewText) throws Exception {
		commonFuncs.selectValueInDropDownIndirectly(parentDropdownPolicy, childrenDropdownPolicy, policy);
		commonFuncs.typeDirectly(txtWriteReview, reviewText);

		int countReviewText = reviewText.length();

		WebElement lblCount = driver.findElement(lblReviewCharCount);
		int expectedCount = Integer.parseInt(lblCount.getText());

		if (countReviewText < 200) {
			System.err.println("Review has to be of minimum 200 characters");
			throw new Exception("AutomationException: Review has to be of minimum 200 characters");
		} else {
			Assert.assertEquals(countReviewText, expectedCount);
			Assert.assertTrue(lblCount.getAttribute("class").contains("color-aqua"));
			commonFuncs.clickDirectly(btnSubmit);
			commonFuncs.waitFor(2000);
		}

	}

	public void verifyPostedReview(int numOfStars, String reviewText) {
		Assert.assertEquals(driver.findElements(lstImgStarsPosted).size(), numOfStars,
				"The number of stars displayed on Insurance Home page do not match the number of Stars selected by user");

		Assert.assertEquals(driver.findElement(lblReviewPosted).getText(), reviewText,
				"The review text on Insurance Home page does not match with the review text posted by user");
	}

}
