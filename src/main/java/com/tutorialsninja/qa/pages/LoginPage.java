package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;

	@FindBy(id="input-email")
	private WebElement email;
	
	@FindBy(id="input-password")
	private WebElement pass;
	
	@FindBy(xpath="//input[@type='submit']")
	private WebElement loginButton;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement warningmsg;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterEmail(String emailId) {
		email.sendKeys(emailId);
	}
	public void enterPass(String password) {
		pass.sendKeys(password);
	}
	public void clickLogin() {
		loginButton.click();
	}
	public String warningmsg() {
		String act = warningmsg.getText();
		return act;
	}
}
