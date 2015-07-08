package com.lohika.example.pages;

import org.openqa.selenium.WebDriver;

import com.lohika.example.pages.frames.LeftLoginFrame;

public class LoginPage extends AnyPage {

	public LeftLoginFrame loginFrame;
	//private WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		//switchToFrameNavbar();
		loginFrame = MyPageFactory.getPage(driver, LeftLoginFrame.class);
	}
	
	public boolean isOnThisPage() {
		loginFrame.switchToThis();
		return loginFrame.loginButton.isDisplayed();
	}

	@Override
	public void tryToOpen() {
		MyPageFactory.getPage(driver, InternalPage.class).logout();
	}
	
	public void loginAs(String username, String password) {
		// login
		//driver.switchTo().frame("body").switchTo().frame("navbar");
		loginFrame.switchToThis();
		loginFrame.userNameField.clear();
		loginFrame.userNameField.sendKeys(username);
		loginFrame.passwordField.clear();
		loginFrame.passwordField.sendKeys(password);
		loginFrame.loginButton.click();
	}
}
