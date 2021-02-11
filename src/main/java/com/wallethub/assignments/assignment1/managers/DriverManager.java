/**
 * 
 */
package com.wallethub.assignments.assignment1.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author VanshdeepK
 *
 */
public class DriverManager {
	
	private static final Logger LOG = LoggerFactory.getLogger(DriverManager.class);
	private final String confFile = "src/test/resources/assignment1/testautomation.properties";

	private WebDriver driver;
	private String Url;
	private String implicitlyWait;
	private String conditionalWait;
	private String browser = "chrome";
	private String username;
	private String password;
	private String feed;
	public final int DEFAULT_WAIT = 20;

	private static DriverManager obj = null;
	{
		loadSystemProperties();

		// initialize the URL
		Url = System.getProperty("testautomation.url");
		

		// initialize which browser to use
		browser = System.getProperty("testautomation.browser");
		

		// initialize how much waiting time needs to be applied for all elements
		// on page
		implicitlyWait = System.getProperty("testautomation.implicitlyWait");
		

		// initialize how much waiting time needs to be applied for waiting
		// condition on an apllied before any action is taken on it
		conditionalWait = System.getProperty("testautomation.conditionalWait");
		

		// user name to be used for login
		username = System.getProperty("testautomation.userName");
		

		// password to be used for login
		password = System.getProperty("testautomation.password");
		
		// feed message to be posted
		feed = System.getProperty("testautomation.feed");
		

	}

	private DriverManager() {
	}

	public static DriverManager getInstance() {
		if (obj == null) {
			obj = new DriverManager();
			LOG.info("Loaded DriverManager");
		}
		return obj;
	}

	private void loadSystemProperties() {
		FileInputStream fileInput = null;
		try {
			File file = new File(confFile);
			fileInput = new FileInputStream(file);
			Properties sysProperties = new Properties();
			sysProperties.load(fileInput);

			Enumeration<Object> keys = sysProperties.keys();
			while (keys.hasMoreElements()) {
				String key = (String) keys.nextElement();
				String value = sysProperties.getProperty(key);
				System.setProperty(key, value);
			}

		} catch (IOException io) {
			LOG.error(io.toString());
		} catch (NullPointerException npe) {
			LOG.error(npe.toString());
		} finally {
			if (fileInput != null) {
				try {
					fileInput.close();
				} catch (IOException e) {
					LOG.error(e.toString());
				}
			}
		}
	}

	public String getURL(){
		if ("".equals(Url) || Url == null) {
			LOG.error("The URL was not specified in " + confFile);
			System.exit(0);
		}
		return Url;
	}
	
	public String getBrowser(){
		if ("".equals(browser) || browser == null) {
			LOG.error("The browser was not specified in " + confFile);
			System.exit(0);
		}
		return browser;
	}
	
	public int getImplicitlyWait() {
		if (implicitlyWait == null || implicitlyWait.isEmpty()) {
			LOG.error("Implicit Wait was not specified or is empty in " + confFile);
			return 30;
		} else {
			try {
				return Integer.parseInt(implicitlyWait);
			} catch (NumberFormatException e) {
				throw new RuntimeException("Not able to parse value : " + implicitlyWait + " in to Integer");
			}
		}
	}
	
	public int getConditionalWait() {
		if (conditionalWait == null || conditionalWait.isEmpty()) {
			LOG.error("Conditional Wait was not specified or is empty in " + confFile);
			return 30;
		} else {
			try {
				return Integer.parseInt(conditionalWait);
			} catch (NumberFormatException e) {
				throw new RuntimeException("Not able to parse value : " + conditionalWait + " in to Integer");
			}
		}
	}
	
	public String getUserName(){
		if ("".equals(username) || username == null) {
			LOG.error("The username was not specified in " + confFile);
			System.exit(0);
		}
		return username;
	}
	
	public String getPassword(){
		if ("".equals(password) || password == null) {
			LOG.error("The password was not specified in " + confFile);
			System.exit(0);
		}
		return password;
	}
	
	public String getFeed(){
		if ("".equals(feed) || feed == null) {
			LOG.error("The feed was not specified in " + confFile);
			System.exit(0);
		}
		return feed;
	}
	
	/**
	 * @param driver
	 *            the driver to set
	 */
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * @return the driver
	 */
	public WebDriver getDriver() {
		return driver;
	}
	
	public WebDriver initDriver(){
		HashMap<String, Object> Prefs = new HashMap<String, Object>();
		switch (browser) {
		case "chrome":
		case "Chrome":
			ChromeOptions opsChrome = new ChromeOptions();
			opsChrome.addArguments("--disable-notifications");
			opsChrome.setExperimentalOption("prefs", Prefs);
			opsChrome.setExperimentalOption("useAutomationExtension", false);
			opsChrome.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
			opsChrome.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			opsChrome.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
			
//			DesiredCapabilities cap = DesiredCapabilities.chrome();
//			cap.setJavascriptEnabled(true);
//			cap.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
//			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//			cap.setCapability(ChromeOptions.CAPABILITY, opsChrome);
			
			System.setProperty("webdriver.chrome.driver", "lib\\chromedriver.exe");
			
			driver = new ChromeDriver(opsChrome);
    		driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			
			break;
		case "firefox":
		case "Firefox":
			break;
		case "edge":
		case "Edge":
			break;
		case "ie":
		case "iexplore":
		case "Internet Explorer":
			break;
		}

		driver.manage().timeouts().setScriptTimeout(DEFAULT_WAIT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(getImplicitlyWait(), TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(getURL());
		
		return driver;
	}
	
	public void closeDriver() {
		if (driver != null) {
			try {
				driver.quit();
				driver = null;
			} catch (NoSuchMethodError | NoSuchSessionException | SessionNotCreatedException ex) {
				LOG.error("An error occured while closing the driver: ", ex);
			}
		}
	}
	
	public boolean isFirefox() {
		if (driver == null)
			return false;

		return "firefox".equals(getBrowser().toLowerCase());
	}

	public boolean isChrome() {
		if (driver == null)
			return false;

		return "chrome".equals(getBrowser().toLowerCase());
	}

	public boolean isIE() {
		if (driver == null)
			return false;

		return "ie".equals(getBrowser().toLowerCase());
	}

	public boolean isEdge() {
		if (driver == null)
			return false;

		return "edge".equals(getBrowser().toLowerCase());
	}
}
