package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.turorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base {

	WebDriver driver;
	SearchPage sp;
	
	public SearchTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		driver = initializeBrowser(prop.getProperty("browser"));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifySearchWithValidProduct() {
		
		HomePage hp = new HomePage(driver);
		hp.searchBox(dataProp.getProperty("validItem"));
		sp = hp.clickSearch();
		
		Assert.assertTrue(sp.productDisplayed());
	}
	
	@Test(priority = 2)
	public void verifySerachWithInValidProduct() {
		
		HomePage hp = new HomePage(driver);
		hp.searchBox(dataProp.getProperty("invalidItem"));
		sp = hp.clickSearch();
		
		Assert.assertEquals(sp.noProductMsg(), dataProp.getProperty("noProductmsg"));
	}
}
