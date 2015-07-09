package com.lohika.example.pages;

import org.openqa.selenium.WebDriver;

public class ItineraryPage extends InternalPage {

	public ItineraryPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isOnThisPage() {
		infoFrame.switchToThis();
		return infoFrame.itineraryCaption.isDisplayed();
	}
	
	public boolean areThereScheduledFlights() {
		infoFrame.switchToThis();
		return infoFrame.scheduledFlightsCheckboxes.size() > 0;
	}

	public void cancelAllFlights() {
		infoFrame.cancelAllFlights();
	}

}
