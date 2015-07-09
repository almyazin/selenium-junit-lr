package com.lohika.example.pages;

import org.openqa.selenium.WebDriver;

public class FindFlightsPage extends InternalPage {

	public FindFlightsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isOnThisPage() {
		infoFrame.switchToThis();
		return infoFrame.destForm.isDisplayed();
	}
	
	public void fillFindFlightForm() {
		
		infoFrame.selectDepartureCity("Denver");
		infoFrame.selectArrivalCity("Frankfurt");
		
		infoFrame.setNumPassengers("2");
		infoFrame.setSeatPref("Aisle");
		infoFrame.setSeatType("First");
		
		infoFrame.continueToSelectFlights();
	}
	
	public void expandDepartureList() {
		infoFrame.switchToThis();
		infoFrame.expandDepartureList();
	}
	
	public void collapseDepartureList() {
		infoFrame.switchToThis();
		infoFrame.collapseDepartureList();
	}
	
	public void expandArrivalList() {
		infoFrame.switchToThis();
		infoFrame.expandArrivalList();
	}
	
	public void collapseArrivalList() {
		infoFrame.switchToThis();
		infoFrame.collapseArrivalList();
	}

}
