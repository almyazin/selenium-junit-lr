package com.lohika.example.pages;

import org.openqa.selenium.WebDriver;

public class PaymentDetailsPage extends InternalPage {

	public PaymentDetailsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isOnThisPage() {
		infoFrame.switchToThis();
		return infoFrame.creditCardNumber.isDisplayed();
	}
	
	public void fillPaymentDetails() {
		infoFrame.setFirstName("Jojo");
		infoFrame.setLastName("Bean");
		infoFrame.setStreet("Here str.");
		infoFrame.setCityState("Odessa/Od/789123");
		infoFrame.setPassangersNames(new String[] {"Bean, Jojo", "Pupkin, Vasya"});
		infoFrame.setCreditCardNumber("12345678");
		infoFrame.setCreditCardExpDate("08/15");
		
		infoFrame.buyFlights();
	}

}
