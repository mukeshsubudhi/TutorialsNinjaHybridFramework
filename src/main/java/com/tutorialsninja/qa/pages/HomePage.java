package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;

	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccDropMenu;
	
	@FindBy(linkText="Login")
	private WebElement logIn;
	
	@FindBy(linkText="Register")
	WebElement register;
	
	@FindBy(name="search")
	WebElement searchtab;
	
	@FindBy(xpath="//div[@id='search']/descendant::button")
	WebElement searchbutton;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnMyacc() {
		myAccDropMenu.click();
	}
	
	public LoginPage clickOnLogin() {
		logIn.click();
		return new LoginPage(driver);
	}
	
	public RegisterPage clickOnRegister() {
		register.click();
		return new RegisterPage(driver);
	}
	
	public void searchBox(String item) {
		searchtab.sendKeys(item);
	}
	
	public SearchPage clickSearch() {
		searchbutton.click();
		return new SearchPage(driver);
	}
}
