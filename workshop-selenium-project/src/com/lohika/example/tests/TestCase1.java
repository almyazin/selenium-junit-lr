package com.lohika.example.tests;

import java.io.File;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;

public class TestCase1 {
	private static WebDriver driver;
	private static String baseUrl;
	private boolean acceptNextAlert = true;
	private static StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass
	public static void setUp() throws Exception {
		FirefoxProfile pf = new FirefoxProfile(new File("c:/Users/myazin/AppData/Roaming/Mozilla/Firefox/Profiles/5kl2afme.default"));
		driver = new FirefoxDriver(pf);
		driver.manage().window().maximize();
		/*System.setProperty("webdriver.ie.driver", "c:/IEDriverServer_x32/IEDriverServer.exe");
		driver = new InternetExplorerDriver();*/
		//baseUrl = "http://localhost:1080/";
		baseUrl = "http://myd-vm07489.hpswlabs.adapps.hp.com:1080/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testUntitled() throws Exception {
		goToMainPage();
		login("jojo", "bean");
		// Thread.sleep(2000);

		goToFindFlights();
		// System.out.println(driver.findElements(By.tagName("frame")));
		// driver.findElement(By.xpath("//a[1]/img")).click();

		logout();
	}
	
	@Test
	public void testBookFlight() throws Exception {
		goToMainPage();
		login("jojo", "bean");
		
		goToFindFlights();
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("body").switchTo().frame("info");
		new Select(driver.findElement(By.name("depart")))
				.selectByVisibleText("Denver");
		new Select(driver.findElement(By.name("arrive")))
				.selectByVisibleText("Frankfurt");
		// ERROR: Caught exception [Error: Dom locators are not implemented
		// yet!]
		// ERROR: Caught exception [Error: Dom locators are not implemented
		// yet!]
		Thread.sleep(2000);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		System.out.println(jsExecutor.executeScript("return self.name"));
		System.out.println(driver.findElements(By.tagName("input")));
		System.out.println(driver.findElement(
				By.xpath("/html/body/blockquote/form/table/tbody/tr[7]/td/input")).getTagName());
		System.out.println(driver.findElement(By.xpath("/html/body/blockquote/form/table/tbody/tr[7]/td/input")).getAttribute("name"));
		//driver.findElement(By.xpath("//input[@name='findFlights']")).click();
		WebElement cntButton = driver.findElement(By.xpath("/html/body/blockquote/form/table/tbody/tr[7]/td/input"));
		Actions actions = new Actions(driver);
		actions.moveToElement(cntButton).clickAndHold().release().perform();
		//actions.clickAndHold().perform();
		//actions.release().perform();
		
		//actions.moveToElement(driver.findElement(By.name("arrive"))).clickAndHold().perform();
		
		/*driver.findElement(By.xpath("//input[@name='outboundFlight'][1]")).click();
		driver.findElement(By.name("reserveFlights")).click();
		driver.findElement(By.name("creditCard")).clear();
		driver.findElement(By.name("creditCard")).sendKeys("12345678");
		driver.findElement(By.name("expDate")).clear();
		driver.findElement(By.name("expDate")).sendKeys("08/15");
		driver.findElement(By.name("buyFlights")).click();*/
	}

	private void logout() {
		// logout
		// Thread.sleep(2000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("body").switchTo().frame("navbar");
		// System.out.println(jsExecutor.executeScript("return self.name"));
		driver.findElement(By.xpath("//img[@alt='SignOff Button']")).click();
	}

	private void login(String username, String password) {
		// login
		driver.switchTo().frame("body").switchTo().frame("navbar");
		driver.findElement(By.xpath("//input[@name = 'username']")).clear();
		driver.findElement(By.xpath("//input[@name = 'username']")).sendKeys(
				username);
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("login")).click();
	}

	private void goToMainPage() {
		// open main page
		driver.get(baseUrl + "/WebTours/");
	}
	
	private void goToFindFlights() {
		// Find flights
		driver.switchTo().defaultContent();
		driver.switchTo().frame("body").switchTo().frame("navbar");
		/*
		 * JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		 * System.out.println(jsExecutor.executeScript("return self.name"));
		 * System.out.println(driver.findElements(By.tagName("a")));
		 */
		driver.findElement(By.xpath("//img[@alt='Search Flights Button']"))
				.click();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		//driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
