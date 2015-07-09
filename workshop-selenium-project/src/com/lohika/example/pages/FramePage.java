package com.lohika.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public abstract class FramePage extends Page {

	protected Actions actions;

	public FramePage(WebDriver driver) {
		actions = new Actions(driver);
	}
	@Override
	protected void tryToOpen() {
		// TODO Auto-generated method stub

	}
	
	protected abstract void switchToThis(); 

}
