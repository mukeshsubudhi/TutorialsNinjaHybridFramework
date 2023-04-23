package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.turorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base {

	WebDriver driver;
	LoginPage lp;
	
	public LoginTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {

		driver = initializeBrowser(prop.getProperty("browser"));
		HomePage hp = new HomePage(driver);
		hp.clickOnMyacc();
		lp = hp.clickOnLogin();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1, dataProvider = "supplyTestData")
	public void verifyLoginWithValidCredentials(String email, String pass) {
		
		lp.enterEmail(email);
		lp.enterPass(pass);
		lp.clickLogin();

		AccPage ap = new AccPage(driver);
		Assert.assertTrue(ap.getDisplayAccInfoStatus());
	}

	@DataProvider
	public Object[][] supplyTestData() {
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}

	@Test(priority = 2)
	public void verifyLoginWithInValidCredentials() {

		lp.enterEmail(Utilities.generateEmailWithTimeStamp());
		lp.enterPass("invalidPass");
		lp.clickLogin();

		String act = lp.warningmsg();
		Assert.assertEquals(act, dataProp.getProperty("warningMsg"));
	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {

		lp.enterEmail(Utilities.generateEmailWithTimeStamp());
		lp.enterPass("validPass");
		lp.clickLogin();

		String act = lp.warningmsg();
		Assert.assertEquals(act, dataProp.getProperty("warningMsg"));
	}

	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInvalidPassword() {

		lp.enterEmail(prop.getProperty("validEmail"));
		lp.enterPass(dataProp.getProperty("invalidPass"));
		lp.clickLogin();

		String act = lp.warningmsg();
		Assert.assertEquals(act, dataProp.getProperty("warningMsg"));
	}

	@Test(priority = 5)
	public void verifyLoginWithoutCredentials() {

		lp.enterEmail("");
		lp.enterPass("");
		lp.clickLogin();

		String act = lp.warningmsg();
		Assert.assertEquals(act, dataProp.getProperty("warningMsg"));
	}
}
