package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccSuccessPage {

	WebDriver driver;
	
	@FindBy(xpath="//*[@id=\"content\"]/h1")
	private WebElement successmsg;
	
	public AccSuccessPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String retriveAccSuessMsg() {
		String act = successmsg.getText();
		return act;
	}
}
