/**
 * 
 */
package com.wallethub.assignments.utilities;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Files;
import com.wallethub.assignments.assignment1.managers.DriverManager;


/**
 * @author VanshdeepK
 *
 */
public class CommonLibrary {
	private static final Logger LOG = LoggerFactory.getLogger(CommonLibrary.class);
	WebDriver driver;
	JavascriptExecutor js;

	public CommonLibrary(WebDriver driver) {
		this.driver = driver;
		js = (JavascriptExecutor) driver;
		LOG.info("Loaded CommonLibrary");
	}

	/**
	 * This method compares the Title of the page with expected Title
	 * 
	 * @param String[]
	 *            expectedTitles Passing expected Titles of page
	 * @return String - If empty, it means the expected and actual Title match
	 *         for page. Otherwise, it returns the title of page currently
	 *         displayed
	 */
	public String verifyWindowTitle(String[] expectedTitles) {
		String pageTitle = getWindowTitle();
		for (String s : expectedTitles) {
			if (pageTitle.equals(s))
				return "";
		}

		// return pageTitle.equals(expectedTitle) ? "" : pageTitle;
		return pageTitle;
	}

	/**
	 * This method compares the Title of the page with expected Title
	 * 
	 * @param String
	 *            expectedTitle Title Expected in Window
	 * @return String - If empty, it means the expected and actual Title match
	 *         for page. Otherwise, it returns the title of page currently
	 *         displayed
	 */
	public String verifyWindowTitle(String expectedTitle) {
		String pageTitle = getWindowTitle();

		return pageTitle.equals(expectedTitle) ? "" : pageTitle;

	}

	/**
	 * This method returns the title of the current page
	 * 
	 * @return pageTitle String format
	 */
	public String getWindowTitle() {
		return driver.getTitle();
	}

	/**
	 * This method is used to click on button, link etc. by passing specific
	 * waiting time. In case of inability to do so in first time, it is retried
	 * multiple times
	 * 
	 * @param element
	 *            Element to be clicked upon
	 * @param waitTime
	 *            conditional wait time for button click
	 */
	public void clickDirectly(WebElement element, int waitTime) {
		new WebDriverWait(driver, waitTime).until(ExpectedConditions.elementToBeClickable(element));
		try {
			element.click();
		} catch (StaleElementReferenceException e) {
			int i = 0;
			while (i < 5) {
				try {
					element.click();
					break;
				} catch (StaleElementReferenceException e1) {
					System.out.println(e.getMessage());
				}
			}
			i++;
		}
	}

	/**
	 * This method is used to click on button, link etc. by passing specific
	 * waiting time. In case of inability to do so in first time, it is retried
	 * multiple times
	 * 
	 * @param elementLocator
	 *            Locator Strategy for element to be clicked upon
	 * @param waitTime
	 *            conditional wait time for button click
	 */
	public void clickDirectly(By elementLocator, int waitTime) {
		new WebDriverWait(driver, waitTime).until(ExpectedConditions.elementToBeClickable(elementLocator));
		try {
			driver.findElement(elementLocator).click();
		} catch (StaleElementReferenceException e) {
			int i = 0;
			while (i < 5) {
				try {
					driver.findElement(elementLocator).click();
					break;
				} catch (StaleElementReferenceException e1) {
					System.out.println(e.getMessage());
				}
			}
			i++;
		}
	}

	/**
	 * This method is used to click on button, link etc
	 * 
	 * @param elementLocator
	 *            Locator Strategy for element to be clicked upon
	 */
	public void clickDirectly(By elementLocator) {
		int waitTime = DriverManager.getInstance().getConditionalWait();
		clickDirectly(elementLocator, waitTime);
	}

	/**
	 * This method is used to click on button, link etc
	 * 
	 * @param element
	 *            Element to be clicked upon
	 */
	public void clickDirectly(WebElement element) {
		int waitTime = DriverManager.getInstance().getConditionalWait();
		clickDirectly(element, waitTime);
	}

	/**
	 * This method returns the top most element from the list of elements in DOM
	 * 
	 * @param lstElements
	 *            Locator strategy to get list of elements
	 * @return WebElement first element is returned
	 */
	public WebElement getFirstElementOutOfListOfElements(By lstElements) {
		List<WebElement> list = driver.findElements(lstElements);
		return list.get(0);
	}

	/**
	 * This method returns the element at specified position from the list of
	 * elements in DOM
	 * 
	 * @param lstElements
	 *            Locator strategy to get list of elements
	 * @param pos
	 *            position of element to be retrieved
	 * @return WebElement element at the pos position in list of elements
	 */
	public WebElement getNthElementOutOfListOfElements(By lstElements, int pos) {
		List<WebElement> list = driver.findElements(lstElements);
		return list.get(pos + 1);
	}

	/**
	 * This method is used to click on button, link etc. via Javascript by
	 * passing specific waiting time. In case of inability to do so in first
	 * time, it is retried multiple times
	 * 
	 * @param elementLocator
	 *            Locator Strategy for element to be clicked upon
	 * @param waitTime
	 *            conditional wait time for button click
	 */
	public void clickUsingJs(By elementLocator, int waitTime) {
		try {
			Thread.sleep(1000);
			new WebDriverWait(driver, waitTime).until(ExpectedConditions.elementToBeClickable(elementLocator));
			driver.findElement(elementLocator).click();
		} catch (InterruptedException e) {
			LOG.error(e.toString());
		} catch (WebDriverException we) {
			LOG.error(we.toString());
			js.executeScript("arguments[0].click()", driver.findElement(elementLocator));
		}
	}

	/**
	 * This method is used to click on button, link etc via Javascript
	 * 
	 * @param elementLocator
	 *            Locator Strategy for element to be clicked upon
	 */
	public void clickUsingJs(By elementLocator) {
		int waitTime = DriverManager.getInstance().getConditionalWait();
		clickUsingJs(elementLocator, waitTime);
	}

	/**
	 * This method is used to type on input controls like textbox.
	 * 
	 * @param elementLocator
	 *            Locator Strategy for element to be type text on
	 * @param text
	 *            text to be typed in inputbox
	 */
	public void typeDirectly(By elementLocator, String text) {
		new WebDriverWait(driver, DriverManager.getInstance().getConditionalWait())
				.until(ExpectedConditions.elementToBeClickable(elementLocator));
		driver.findElement(elementLocator).sendKeys(text);
	}

	/**
	 * This method is used to type on input controls like textbox by passing
	 * specific waiting time. In case of inability to do so in first time, it is
	 * retried multiple times
	 * 
	 * @param elementLocator
	 *            Locator Strategy for element to be type text on
	 * @param text
	 *            text to be typed in inputbox
	 * @param waitTime
	 *            conditional wait time for element to be in state to type
	 */
	public void typeDirectly(By elementLocator, String text, int waitTime) {
		new WebDriverWait(driver, waitTime).until(ExpectedConditions.elementToBeClickable(elementLocator));
		driver.findElement(elementLocator).sendKeys(text);
	}

	/**
	 * This method is used to type on input controls like textbox via Javascript
	 * by passing specific waiting time. In case of inability to do so in first
	 * time, it is retried multiple times
	 * 
	 * @param elementLocator
	 *            Locator Strategy for element to be type text on
	 * @param text
	 *            text to be typed in inputbox
	 * @param waitTime
	 *            conditional wait time for element to be in state to type
	 */
	public void typeUsingJs(By elementLocator, String text, int waitTime) {
		try {
			// Thread.sleep(1000);
			new WebDriverWait(driver, waitTime).until(ExpectedConditions.elementToBeClickable(elementLocator));
			driver.findElement(elementLocator).sendKeys(text);
			// } catch (InterruptedException e) {
			// LOG.error(e.toString());
		} catch (WebDriverException we) {
			LOG.error(we.toString());
			js.executeScript("arguments[0].text='" + text + "'", driver.findElement(elementLocator));
		}
	}

	/**
	 * This method is used to type on input controls like textbox via Javascript
	 * 
	 * @param elementLocator
	 *            Locator Strategy for element to be typed on
	 * @param text
	 *            text to be typed in textbox
	 */
	public void typeUsingJs(By elementLocator, String text) {
		int waitTime = DriverManager.getInstance().getConditionalWait();
		typeUsingJs(elementLocator, text, waitTime);
	}

	/**
	 * This method is used to handle dropdowns that are non-Select elements
	 * 
	 * @param parent
	 *            Locator strategy of parent element
	 * @param child
	 *            Locator strategy of element to be accessed inside the parent
	 *            element
	 * @param value
	 *            String value to be selected in dropdown
	 * @return boolean value stating whether selection successful or not
	 */
	public boolean selectValueInDropDownIndirectly(By parent, By child, String value) {

		new WebDriverWait(driver, DriverManager.getInstance().getConditionalWait())
				.until(ExpectedConditions.elementToBeClickable(parent));

		WebElement parentElement = driver.findElement(parent);

		boolean matchFound = false;

		parentElement.click();

		new WebDriverWait(driver, DriverManager.getInstance().getConditionalWait())
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(child));

		List<WebElement> childElements = parentElement.findElements(child);

		for (WebElement elem : childElements) {
			if (elem.getText().equalsIgnoreCase(value)) {
				System.out.println("Location of element is " + elem.getLocation());
				elem.click();

				return matchFound = true;
			}
		}

		parentElement = null;
		childElements = null;

		return matchFound;
	}

	/**
	 * This method is used to move scroll bar to the top
	 * 
	 */
	public void scrollToTop() {
		js.executeScript("window.scrollTo(0,0)");
	}

	/**
	 * This method is used to move scroll bar to the bottom
	 * 
	 */
	public void scrollToBottom() {
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	/**
	 * This method is used to scroll by x and y coordinates
	 * 
	 * @param X
	 *            integer value for scrolling through X axis
	 * @param Y
	 *            integer value for scrolling through Y axis
	 */
	public void scrollByCoordinates(int X, int Y) {
		js.executeScript("window.scrollTo(" + X + ", " + Y + ")");
	}

	/**
	 * This method is used to scroll by Y axis based on relative position of
	 * element
	 * 
	 * @param locator
	 *            Locator strategy for element
	 * @param relativeY
	 *            relative position to scroll by Y axis
	 */
	public void scrollingVerticallyByRelativeCoordinates(By locator, int relativeY) {
		Point p = driver.findElement(locator).getLocation();
		scrollByCoordinates(0, (p.getY() - relativeY));
	}

	public void waitFor(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void captureScreenshot(String TestName, WebDriver driver) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		sdf.format(timestamp);
		
		String screenshotName = TestName + sdf.format(timestamp);
		System.out.println("screenshot name is " + screenshotName);
		 
		// This takes a screenshot from the driver and save it to the specified
		// location
		File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// Building up the destination path for the screenshot to save
		File destinationPath = new File(System.getProperty("user.dir") + "/screenshots/" + screenshotName + ".png");

		// Copy taken screenshot from source location to destination
		// location
		try {
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
