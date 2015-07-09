package com.lohika.example.pages.frames;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.lohika.example.pages.FramePage;

public class LeftNavigationFrame extends FramePage{

	public LeftNavigationFrame(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//img[@alt='Search Flights Button']")
	public WebElement flightsButton;

	@FindBy(xpath = "//img[@alt='Itinerary Button']")
	public WebElement itineraryButton;

	@FindBy(xpath = "//img[@alt='Home Button']")
	public WebElement homeButton;
	
	@FindBy(xpath = "//img[@alt='SignOff Button']")
	public WebElement signOffButton;
	
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
