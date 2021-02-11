/**
 * 
 */
package com.wallethub.assignments.assignment1.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wallethub.assignments.assignment1.managers.DriverManager;
import com.wallethub.assignments.utilities.CommonLibrary;

/**
 * @author VanshdeepK
 *
 */
public class HomePage {

	private static final Logger LOG = LoggerFactory.getLogger(HomePage.class);
	WebDriver driver;
	CommonLibrary commonFuncs;
		
	By newDialog = By.xpath("//div[@role='dialog']"); 
	By btn1_in_newDialog = By.xpath("//div[@role='dialog']//div[@role='button']//span[text()='Next']");
	By btn2_in_newDialog = By.xpath("//div[@role='dialog']//div[@role='button']//span[text()='Get Started']");
	By btn3_in_newDialog = By.xpath("//div[@role='dialog']//div[@aria-label='Close introduction']");
	By btn_createPost = By.xpath("//div[@aria-label='Create a post' and @role='region']/div[1]/div");
	By dlgTxt_createPost = By.xpath("//div[@role='dialog']//div[@role='presentation']//div[@data-contents='true']/div[1]");
	By dlgbtn_createPost = By.xpath("//div[@role='dialog']//div[@aria-label='Post' and @role='button']");
	By txt_Feed = By.xpath("//div[@data-pagelet='FeedUnit_0']//div[@dir='auto']/div/div/div");
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		commonFuncs = new CommonLibrary(driver);
		LOG.info("Loaded HomePage");
	}
	
	public void handleOptionalDialog(){
		try{
			new WebDriverWait(driver, DriverManager.getInstance().getConditionalWait()).until(ExpectedConditions.visibilityOfElementLocated(btn_createPost));
		}
		catch(TimeoutException e){
			try{
			commonFuncs.clickDirectly(btn1_in_newDialog);
			commonFuncs.clickDirectly(btn2_in_newDialog);
			}
			catch(TimeoutException e1){ 
			    try{ 
					commonFuncs.clickDirectly(btn3_in_newDialog);	
				}
				catch(TimeoutException e2){ } 
			}
		}
	}
	
	public void createPost(){
		commonFuncs.clickDirectly(btn_createPost);
		commonFuncs.typeUsingJs(dlgTxt_createPost, DriverManager.getInstance().getFeed());
		commonFuncs.clickDirectly(dlgbtn_createPost);
		commonFuncs.waitFor(2500);
	}
	
	public String getPost(){
		commonFuncs.scrollingVerticallyByRelativeCoordinates(txt_Feed, 400);
		return driver.findElement(txt_Feed).getText(); 
	}
}
