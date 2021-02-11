package com.wallethub.assignments.assignment1.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wallethub.assignments.assignment1.managers.DriverManager;
import com.wallethub.assignments.utilities.CommonLibrary;

public class LoginPage {
	
	private static final Logger LOG = LoggerFactory.getLogger(LoginPage.class);
	WebDriver driver;
	CommonLibrary commonFuncs;
		
	By txtUserName = By.id("email");
	By txtPassword = By.id("pass");
	By btnLogin = By.name("login");
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
		commonFuncs = new CommonLibrary(driver);
		LOG.info("Loaded LoginPage");
	}
	
	public void login(){
		commonFuncs.typeDirectly(txtUserName, DriverManager.getInstance().getUserName());
		commonFuncs.typeDirectly(txtPassword, DriverManager.getInstance().getPassword());
		commonFuncs.clickDirectly(btnLogin);
	}
}
