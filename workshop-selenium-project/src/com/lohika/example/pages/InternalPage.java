package com.lohika.example.pages;

import com.lohika.example.pages.frames.LeftNavigationFrame;
import com.lohika.example.pages.frames.MainInfoFrame;

public class InternalPage extends AnyPage {
	
	protected LeftNavigationFrame navigationFrame;
	protected MainInfoFrame infoFrame;
	
	public InternalPage() {
		navigationFrame = MyPageFactory.getPage(driver, LeftNavigationFrame.class);
		infoFrame = MyPageFactory.getPage(driver, MainInfoFrame.class);
	}
	
	public void logout() {
		navigationFrame.switchToThis();
		navigationFrame.signOffButton.click();
	}

	@Override
	protected void tryToOpen() {
		// TODO Auto-generated method stub
		
	}
	
	
}
