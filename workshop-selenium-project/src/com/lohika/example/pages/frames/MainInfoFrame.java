package com.lohika.example.pages.frames;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.lohika.example.pages.FramePage;

public class MainInfoFrame extends FramePage {
	public MainInfoFrame(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//blockquote[starts-with(text(), 'Welcome') and /*[contains(., 'to the Web Tours reservation pages.')]]")
	public WebElement welcomeMessage;
	
	// Find flights form
	
	@FindBy(name= "DestForm")
	public WebElement destForm;
	
	@FindBy(name = "depart")
	public WebElement departureList;
	
	@FindBy(name = "arrive")
	public WebElement arrivalList;
	
	@FindBy(xpath = "//input[@name = 'numPassengers']")
	public WebElement numPassengers;
	
	@FindBy(xpath = "//input[@type = 'radio' and @name = 'seatPref' and @value = 'Aisle']")
	public WebElement seatPrefAisle;
	
	@FindBy(xpath = "//input[@type = 'radio' and @name = 'seatPref' and @value = 'Window']")
	public WebElement seatPrefWindow;
	
	@FindBy(xpath = "//input[@type = 'radio' and @name = 'seatPref' and @value = 'None']")
	public WebElement seatPrefNone;
	
	@FindBy(xpath = "//input[@type = 'radio' and @name = 'seatType' and @value = 'First']")
	public WebElement seatTypeFirst;
	
	@FindBy(xpath = "//input[@type = 'radio' and @name = 'seatType' and @value = 'Business']")
	public WebElement seatTypeBusiness;
	
	@FindBy(xpath = "//input[@type = 'radio' and @name = 'seatType' and @value = 'Coach']")
	public WebElement seatTypeCoach;
	
	@FindBy(xpath = "//input[@name='findFlights']")
	public WebElement findFlightsButton;
	
	@FindBy(name = "roundtrip")
	public WebElement roundtrip;
	
	// Select flights form
	
	@FindBy(xpath = "//input[@name='outboundFlight']")
	public List<WebElement> outboundFlights;
	
	@FindBy(xpath = "//input[@name='reserveFlights']")
	public WebElement reserveFlightsButton;
	
	// Payment details form
	
	@FindBy(name = "firstName")
	public WebElement firstName;
	
	@FindBy(name = "lastName")
	public WebElement lastName;
	
	@FindBy(name = "address1")
	public WebElement address1;
	
	@FindBy(name = "address2")
	public WebElement address2;
	
	@FindBy(xpath = "//input[starts-with(@name, 'pass')]")
	public List<WebElement> passangersNames;
	
	@FindBy(name = "creditCard")
	public WebElement creditCardNumber;
	
	@FindBy(name = "expDate")
	public WebElement creditCardexpDate;
	
	@FindBy(xpath = "//input[@name = 'buyFlights']")
	public WebElement buyFlightsButton;
	
	// Invoice frame
	
	@FindBy(xpath = "//input[@name = 'Book Another']")
	public WebElement bookAnotherButton;
	
	@FindBy(xpath = "//h1//b[text() = 'Invoice']")
	public WebElement invoiceCaption;
	
	// Itinerary frame
	
	@FindBy(xpath = "//input[@name = 'removeFlights']")
	public WebElement cancelCheckedFlightsButton;
	
	@FindBy(xpath = "//input[@name = 'removeAllFlights']")
	public WebElement cancelAllFlightsButton;
	
	@FindBy(xpath = "//h1//b[text() = 'Itinerary']")
	public WebElement itineraryCaption;
	
	@FindBy(xpath = "//form//input[@type='checkbox']")
	public List<WebElement> scheduledFlightsCheckboxes;
	
	
	@Override
	protected void tryToOpen() {
		switchToThis();
	}

	@Override
	public void switchToThis() {
		driver.switchTo().defaultContent();
		driver.switchTo().frame("body").switchTo().frame("info");
	}

	public void expandDepartureList() {
		switchToThis();
		actions.moveToElement(departureList).clickAndHold().perform();
	}

	public void collapseDepartureList() {
		switchToThis();
		actions.moveToElement(departureList).release().click().perform();
	}
	
	public void expandArrivalList() {
		switchToThis();
		actions.moveToElement(arrivalList).clickAndHold().perform();
	}

	public void collapseArrivalList() {
		switchToThis();
		actions.moveToElement(arrivalList).release().click().perform();
	}

	public void selectDepartureCity(String city) {
		switchToThis();
		new Select(departureList).selectByVisibleText(city);
	}

	public void selectArrivalCity(String city) {
		switchToThis();
		new Select(arrivalList).selectByVisibleText(city);
	}

	public void setNumPassengers(String num) {
		switchToThis();
		numPassengers.clear();
		numPassengers.sendKeys(num);
	}

	public void setSeatPref(String seat) {
		switchToThis();
		
		switch (seat) {
		case "Aisle":
			seatPrefAisle.click();
			break;
		case "Window":
			seatPrefWindow.click();
			break;
		case "None":
			seatPrefNone.click();
			break;
		default:
			break;
		}
	}

	public void setSeatType(String type) {
		switchToThis();
		
		switch (type) {
		case "First":
			seatTypeFirst.click();
			break;
		case "Business":
			seatTypeBusiness.click();
			break;
		case "Coach":
			seatTypeCoach.click();
			break;
		default:
			break;
		}
	}

	public void continueToSelectFlights() {
		switchToThis();
		actions.moveToElement(findFlightsButton).clickAndHold().release().perform();
	}

	public void continueToReserveFlights() {
		switchToThis();
		actions.moveToElement(reserveFlightsButton).clickAndHold().release().perform();
	}

	public void setFirstName(String val) {
		switchToThis();
		firstName.clear();
		firstName.sendKeys(val);
	}

	public void setLastName(String val) {
		switchToThis();
		lastName.clear();
		lastName.sendKeys(val);
	}

	public void setStreet(String val) {
		switchToThis();
		address1.clear();
		address1.sendKeys(val);
	}

	public void setCityState(String val) {
		switchToThis();
		address2.clear();
		address2.sendKeys(val);
	}

	public void setCreditCardNumber(String val) {
		switchToThis();
		creditCardNumber.clear();
		creditCardNumber.sendKeys(val);
	}

	public void setCreditCardExpDate(String val) {
		switchToThis();
		creditCardexpDate.clear();
		creditCardexpDate.sendKeys(val);
	}

	public void setPassangersNames(String[] passNames) {
		int i = 0;
		
		switchToThis();
		
		for (WebElement passenger : passangersNames) {
			if (i >= passNames.length) {
				i = 0;
			}
			passenger.clear();
			passenger.sendKeys(passNames[i++]);
		}
	}

	public void buyFlights() {
		switchToThis();
		actions.moveToElement(buyFlightsButton).clickAndHold().release().perform();
	}
	
	public void pressBookAnother() {
		switchToThis();
		
		actions.moveToElement(bookAnotherButton).clickAndHold().release().perform();
	}
	
	public void cancelAllFlights() {
		switchToThis();
		actions.moveToElement(cancelAllFlightsButton).clickAndHold().release().perform();
	}
	
	public void cancelCheckedFlights() {
		switchToThis();
		actions.moveToElement(cancelCheckedFlightsButton).clickAndHold().release().perform();
	}
}
