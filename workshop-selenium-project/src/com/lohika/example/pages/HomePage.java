package com.lohika.example.pages;

public class HomePage extends InternalPage {
	
	public boolean isOnThisPage() {
		infoFrame.switchToThis();
		return infoFrame.welcomeMessage.isDisplayed();
	}
	
}
