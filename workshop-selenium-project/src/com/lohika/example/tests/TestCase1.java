package com.lohika.example.tests;

import java.io.File;
import java.util.List;
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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;
import com.lohika.example.pages.LoginPage;

public class TestCase1 {
	private static WebDriver driver;
	private static Wait<WebDriver> wait;
	private static String baseUrl;
	private boolean acceptNextAlert = true;
	private static StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass
	public static void setUp() throws Exception {
		//FirefoxProfile pf = new FirefoxProfile(new File("c:/Users/myazin/AppData/Roaming/Mozilla/Firefox/Profiles/5kl2afme.default"));
		//driver = new FirefoxDriver(pf);
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		/*System.setProperty("webdriver.ie.driver", "c:/IEDriverServer_x32/IEDriverServer.exe");
		driver = new InternetExplorerDriver();*/
		//baseUrl = "http://localhost:1080/";
		wait = new WebDriverWait(driver, 5);
		baseUrl = "http://myd-vm07489.hpswlabs.adapps.hp.com:1080/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testUntitled() throws Exception {
		goToMainPage();
		loginAs("jojo", "bean");
		// Thread.sleep(2000);

		goToFindFlights();
		// System.out.println(driver.findElements(By.tagName("frame")));
		// driver.findElement(By.xpath("//a[1]/img")).click();

		logout();
	}
	
	@Test
	public void testBookFlight() throws Exception {
		goToMainPage();
		loginAs("jojo", "bean");
		
		goToFindFlights();
		
		assertTrue(isOnDestinationSelectionPage());
		
		fillFindFlightForm();
		
		selectFlight();
		
		fillPaymentDetails();
		
		//pressBookAnother();
		
		goToItinerary();
		
		if (thereAreScheduledFlights()) {
			System.out.println("There are scheduled flights. Removing...");
			pressCancelCheckedFlights();
			pressCancelAllFlights();
		}
		
	}
	
	private boolean isOnDestinationSelectionPage() {
		switchToFrameInfo();
		
		return driver.findElements(By.xpath("//form[@name = 'DestForm']")).size() > 0;
	}
	
	private boolean thereAreScheduledFlights()
	{
		switchToFrameInfo();
		
		ExpectedCondition<Boolean> x = new ExpectedCondition<Boolean>() {
			
			@Override
			public Boolean apply(WebDriver driver) {
				System.out.println("Searching...");
				return driver.findElement(By.xpath("//td/descendant-or-self::*[contains(text(), 'Flight Transaction Summary')]")) != null;
			}
		};
		
		return wait.until(x);
		
	}

	private void goToItinerary() {
		switchToFrameNavbar();
		
		driver.findElement(By.xpath("//img[@alt='Itinerary Button']")).click();
	}
	
	private void pressCancelCheckedFlights() {
		switchToFrameInfo();
		
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//input[@name = 'removeFlights']"))).clickAndHold().release().perform();
	}
	
	private void pressCancelAllFlights() {
		switchToFrameInfo();
		
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//input[@name = 'removeAllFlights']"))).clickAndHold().release().perform();
	}

	private void pressBookAnother() {
		switchToFrameInfo();
		
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//input[@name = 'Book Another']"))).clickAndHold().release().perform();
	}

	private void fillPaymentDetails() {
		switchToFrameInfo();
		driver.findElement(By.name("firstName")).clear();
		driver.findElement(By.name("firstName")).sendKeys("Jojo");
		driver.findElement(By.name("lastName")).clear();
		driver.findElement(By.name("lastName")).sendKeys("Bean");
		driver.findElement(By.name("address1")).clear();
		driver.findElement(By.name("address1")).sendKeys("Here str.");
		driver.findElement(By.name("address2")).clear();
		driver.findElement(By.name("address2")).sendKeys("Odessa/Od/789123");
		driver.findElement(By.name("pass1")).clear();
		driver.findElement(By.name("pass1")).sendKeys("Bean, Jojo");
		driver.findElement(By.name("creditCard")).clear();
		driver.findElement(By.name("creditCard")).sendKeys("12345678");
		driver.findElement(By.name("expDate")).clear();
		driver.findElement(By.name("expDate")).sendKeys("08/15");
		
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//input[@name = 'buyFlights']"))).clickAndHold().release().perform();
		//driver.findElement(By.name("buyFlights")).click();
	}

	private void selectFlight() {
		switchToFrameInfo();
		
		List<WebElement> obFlights = driver.findElements(By.xpath("//input[@name='outboundFlight']"));
		obFlights.get(1).click();
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//input[@name='reserveFlights']"))).clickAndHold().release().perform();
		//driver.findElement(By.name("reserveFlights")).click();
	}

	private void fillFindFlightForm() throws InterruptedException {
		switchToFrameInfo();
		
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.name("arrive"))).clickAndHold().perform();
		actions.moveToElement(driver.findElement(By.name("arrive"))).release().click().perform();
		
		new Select(driver.findElement(By.name("depart")))
				.selectByVisibleText("Denver");
		new Select(driver.findElement(By.name("arrive")))
				.selectByVisibleText("Frankfurt");
		
		driver.findElement(By.xpath("//input[@name = 'numPassengers']")).clear();
		driver.findElement(By.xpath("//input[@name = 'numPassengers']")).sendKeys("2");
		driver.findElement(By.xpath("//input[@type = 'radio' and @name = 'seatPref' and @value = 'Aisle']")).click();
		driver.findElement(By.xpath("//input[@type = 'radio' and @name = 'seatType' and @value = 'First']")).click();
		
		/*Thread.sleep(2000);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		System.out.println(jsExecutor.executeScript("return self.name"));
		System.out.println(driver.findElements(By.tagName("input")));
		System.out.println(driver.findElement(
				By.xpath("/html/body/blockquote/form/table/tbody/tr[7]/td/input")).getTagName());
		System.out.println(driver.findElement(By.xpath("/html/body/blockquote/form/table/tbody/tr[7]/td/input")).getAttribute("name"));*/
		//driver.findElement(By.xpath("//input[@name='findFlights']")).click();
		//WebElement cntButton = driver.findElement(By.xpath("/html/body/blockquote/form/table/tbody/tr[7]/td/input"));
		//Actions actions = new Actions(driver);

		actions.moveToElement(driver.findElement(By.xpath("//input[@name='findFlights']"))).clickAndHold().release().perform();
	}

	private void switchToFrameInfo() {
		driver.switchTo().defaultContent();
		driver.switchTo().frame("body").switchTo().frame("info");
	}

	private void logout() {
		switchToFrameNavbar();
		// System.out.println(jsExecutor.executeScript("return self.name"));
		driver.findElement(By.xpath("//img[@alt='SignOff Button']")).click();
	}

	private void loginAs(String username, String password) {
		// login
		//driver.switchTo().frame("body").switchTo().frame("navbar");
		switchToFrameNavbar();
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
		switchToFrameNavbar();
		/*
		 * JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		 * System.out.println(jsExecutor.executeScript("return self.name"));
		 * System.out.println(driver.findElements(By.tagName("a")));
		 */
		driver.findElement(By.xpath("//img[@alt='Search Flights Button']"))
				.click();
	}

	private void switchToFrameNavbar() {
		driver.switchTo().defaultContent();
		driver.switchTo().frame("body").switchTo().frame("navbar");
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
	
	@Test
	public void testLoginUsingPOM() {
		goToMainPage();
		LoginPage lp = new LoginPage(driver);
		lp.loginFrame.userNameField.clear();
		lp.loginFrame.userNameField.sendKeys("jojo");
		lp.loginFrame.passwordField.clear();
		lp.loginFrame.passwordField.sendKeys("bean");
		lp.loginFrame.loginButton.click();
	}
}
