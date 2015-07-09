package com.lohika.example.pages;

import org.openqa.selenium.WebDriver;

public class SelectFlightPage extends InternalPage {

	public SelectFlightPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isOnThisPage() {
		infoFrame.switchToThis();
		return infoFrame.reserveFlightsButton.isDisplayed();
	}
	
	public void selectFlight() {
		infoFrame.switchToThis();
		
		infoFrame.outboundFlights.get(1).click();
		
		infoFrame.continueToReserveFlights();
	}
}
