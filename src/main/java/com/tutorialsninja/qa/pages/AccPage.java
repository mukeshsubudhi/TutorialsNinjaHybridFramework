package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccPage {

	WebDriver driver;
	
	@FindBy(linkText="Edit your account information")
	private WebElement editAcc;
	
	public AccPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean getDisplayAccInfoStatus() {
		boolean displaystatus=editAcc.isDisplayed();
		return displaystatus;
	}
}
