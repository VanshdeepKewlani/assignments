/**
 * 
 */
package com.wallethub.assignments.assignment1.context;

import com.wallethub.assignments.assignment1.managers.DriverManager;
import com.wallethub.assignments.assignment1.managers.PageObjectManager;
import com.wallethub.assignments.utilities.CommonLibrary;

/**
 * @author VanshdeepK
 *
 */
public class TestContext {

	private DriverManager driverManager;
	private PageObjectManager pageObjectManager;
	private ScenarioContext scenarioContext;
	
	public TestContext() {
		driverManager = DriverManager.getInstance();
		scenarioContext = new ScenarioContext();
	}

	public DriverManager getDriverManager() {
		return driverManager;
	}

	public PageObjectManager getPageObjectManager() {
		return pageObjectManager == null ? new PageObjectManager(driverManager.getDriver()) : pageObjectManager;
	}

	public ScenarioContext getScenarioContext() {
		return scenarioContext;
	}

	public void setDriverManager() {
		driverManager = DriverManager.getInstance();
	}

}
