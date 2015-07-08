package com.lohika.example.pages.frames;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.lohika.example.pages.FramePage;

public class LeftLoginFrame extends FramePage{
	
	@FindBy(xpath = "//input[@name = 'username']")
	public WebElement userNameField;
	
	@FindBy(xpath = "//input[@name = 'password']")
	public WebElement passwordField;
	
	@FindBy(name = "login")
	public WebElement loginButton;
	
	@Override
	protected void tryToOpen() {
		switchToThis();
	}

	@Override
	public void switchToThis() {
		driver.switchTo().defaultContent();
		driver.switchTo().frame("body").switchTo().frame("navbar");
	}
	

}
