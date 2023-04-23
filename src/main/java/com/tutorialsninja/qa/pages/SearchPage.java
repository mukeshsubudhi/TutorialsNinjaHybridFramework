package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	WebDriver driver;
	
	@FindBy(linkText="iMac")
	WebElement validMacprod;
	
	@FindBy(xpath="//*[@id=\"content\"]/p[2]")
	WebElement invalidprodmsg;
	
	public SearchPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean productDisplayed() {
		boolean bb = validMacprod.isDisplayed();
		return bb;
	}
	
	public String noProductMsg() {
		String act = invalidprodmsg.getText();
		return act;
	}
}
