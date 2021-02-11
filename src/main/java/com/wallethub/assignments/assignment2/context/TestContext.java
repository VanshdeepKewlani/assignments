/**
 * 
 */
package com.wallethub.assignments.assignment2.context;

import com.wallethub.assignments.assignment2.managers.DriverManager;
import com.wallethub.assignments.assignment2.managers.PageObjectManager;
import com.wallethub.assignments.utilities.CommonLibrary;

/**
 * @author VanshdeepK
 *
 */
public class TestContext {

	private DriverManager driverManager;
	private PageObjectManager pageObjectManager;
	private ScenarioContext scenarioContext;
	private CommonLibrary commonLib;

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

	public CommonLibrary getCommonLibrary() {
		return commonLib == null ? new CommonLibrary(driverManager.getDriver()) : commonLib;
	}

	public void setDriverManager() {
		driverManager = DriverManager.getInstance();
	}

}
