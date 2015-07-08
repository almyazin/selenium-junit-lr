package com.lohika.example.pages;

import org.openqa.selenium.WebDriver;

public abstract class Page {
	public WebDriver driver;
	
	public boolean isOnThisPage() {
		return true;
	}
	
	protected abstract void tryToOpen();

}
