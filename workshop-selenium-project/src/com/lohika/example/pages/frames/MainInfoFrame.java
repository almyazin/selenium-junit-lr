package com.lohika.example.pages.frames;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.lohika.example.pages.FramePage;

public class MainInfoFrame extends FramePage {
	@FindBy(xpath = "//blockquote[starts-with(text(), 'Welcome') and /*[contains(., 'to the Web Tours reservation pages.')]]")
	public WebElement welcomeMessage;
	
	@Override
	protected void tryToOpen() {
		switchToThis();
	}

	@Override
	public void switchToThis() {
		driver.switchTo().defaultContent();
		driver.switchTo().frame("body").switchTo().frame("info");
	}
}
