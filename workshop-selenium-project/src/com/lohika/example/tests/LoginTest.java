package com.lohika.example.tests;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.lohika.example.pages.HomePage;
import com.lohika.example.pages.InternalPage;
import com.lohika.example.pages.LoginPage;
import com.lohika.example.pages.MyPageFactory;

public class LoginTest {
	public WebDriver driver;
	private String baseUrl;
	
	@Before
	public void setUp() throws Exception {
		//FirefoxProfile pf = new FirefoxProfile(new File("c:/Users/myazin/AppData/Roaming/Mozilla/Firefox/Profiles/5kl2afme.default"));
		//driver = new FirefoxDriver(pf);
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		/*System.setProperty("webdriver.ie.driver", "c:/IEDriverServer_x32/IEDriverServer.exe");
		driver = new InternetExplorerDriver();*/
		//baseUrl = "http://localhost:1080/";
		//wait = new WebDriverWait(driver, 5);
		baseUrl = "http://myd-vm07489.hpswlabs.adapps.hp.com:1080/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	private void goToMainPage() {
		// open main page
		driver.get(baseUrl + "/WebTours/");
	}
	
	@Test
	public void loginTest() {
		goToMainPage();
		MyPageFactory.getPage(driver, LoginPage.class).loginAs("jojo", "bean");
		MyPageFactory.getPage(driver, InternalPage.class).goToFindFlightsPage();
		assertTrue(MyPageFactory.getPage(driver, HomePage.class).isOnThisPage());
	}
	
	
}
