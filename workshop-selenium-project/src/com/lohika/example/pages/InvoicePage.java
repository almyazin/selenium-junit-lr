package com.lohika.example.pages;

import org.openqa.selenium.WebDriver;

public class InvoicePage extends InternalPage {

	public InvoicePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isOnThisPage() {
		infoFrame.switchToThis();
		return infoFrame.invoiceCaption.isDisplayed();
	}

}
