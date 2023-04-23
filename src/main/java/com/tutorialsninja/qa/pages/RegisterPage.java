package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;
	
	@FindBy(id="input-firstname")
	private WebElement firstName;
	
	@FindBy(id="input-lastname")
	private WebElement lastName;
	
	@FindBy(id="input-email")
	private WebElement email;
	
	@FindBy(id="input-telephone")
	private WebElement telephone;
	
	@FindBy(id="input-password")
	private WebElement password;
	
	@FindBy(id="input-confirm")
	private WebElement confirmPass;
	
	@FindBy(name="agree")
	private WebElement agree;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement submit;
	
	@FindBy(xpath="/html/body/div[2]/div/div/form/fieldset[2]/div[2]/div/div")
	WebElement passerrormsg;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	WebElement emailerrormsg;
	
	public RegisterPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterFName(String fname) {
		firstName.sendKeys(fname);
	}
	public void enterLName(String lname) {
		lastName.sendKeys(lname);
	}
	public void enterEmailId(String emailId) {
		email.sendKeys(emailId);
	}
	public void enterPhnNum(String PhnNum) {
		telephone.sendKeys(PhnNum);
	}
	public void enterPass(String pass) {
		password.sendKeys(pass);
	}
	public void enterCPass(String cpass) {
		confirmPass.sendKeys(cpass);
	}
	public void clickAgree() {
		agree.click();
	}
	public void clickContinue() {
		submit.click();
	}
	public String retrivePassErrorMsg() {
		String act = passerrormsg.getText();
		return act;
	}
	public String retriveEmailErrorMsg() {
		String act = emailerrormsg.getText();
		return act;
	}
	
	public void register(String fname,String lname,String emailId,String PhnNum,String pass,String cpass) {
		firstName.sendKeys(fname);
		lastName.sendKeys(lname);
		email.sendKeys(emailId);
		telephone.sendKeys(PhnNum);
		password.sendKeys(pass);
		confirmPass.sendKeys(cpass);
		agree.click();
		submit.click();
	}
}
