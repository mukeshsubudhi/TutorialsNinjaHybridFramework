package com.tutorialsninja.qa.testcases;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.turorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base {
	RegisterPage rp;
	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {

		driver = initializeBrowser("chrome");
		HomePage hp = new HomePage(driver);
		hp.clickOnMyacc();
		rp = hp.clickOnRegister();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority = 1)
	public void verifyRegisteringWithMandatoryFields() {
		
		rp.register(dataProp.getProperty("fname"), dataProp.getProperty("lname"),
				Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("tnum"),
				dataProp.getProperty("ipPass"), dataProp.getProperty("ipPass"));
		
		AccSuccessPage as = new AccSuccessPage(driver);
		Assert.assertEquals(as.retriveAccSuessMsg(), dataProp.getProperty("susmsg"));	
	}
	
	@Test(priority = 2)
	public void verifyRegisteringWithDifferentPassword() {
		
		rp.register(dataProp.getProperty("fname"), dataProp.getProperty("lname"),
				Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("tnum"),
				dataProp.getProperty("ipPass"), "muku@123");
		
		Assert.assertEquals(rp.retrivePassErrorMsg(), "Password confirmation does not match password!");
	}
	
	@Test(priority = 3)
	public void verifyRegisteringWithExistingEmail() {
		
		rp.register(dataProp.getProperty("fname"), dataProp.getProperty("lname"),
				"abc123@gmail.com", dataProp.getProperty("tnum"),
				dataProp.getProperty("ipPass"), dataProp.getProperty("ipPass"));
		
		Assert.assertEquals(rp.retriveEmailErrorMsg(), "Warning: E-Mail Address is already registered!");
	}
}
