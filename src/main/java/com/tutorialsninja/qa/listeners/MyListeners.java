package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReport;
import com.tutorialsninja.qa.utils.Utilities;

public class MyListeners implements ITestListener {
	ExtentReports repo;
	ExtentTest test;
	String testname;

	public void onStart(ITestContext context) {
		repo = ExtentReport.generateReport();
	}

	public void onTestStart(ITestResult result) {
		testname = result.getName();
		test = repo.createTest(testname);
		test.log(Status.INFO, testname + " Started executing");
	}

	public void onTestSuccess(ITestResult result) {

		test = repo.createTest(testname);
		test.log(Status.PASS, testname + " got Executed");
	}

	public void onTestFailure(ITestResult result) {

		WebDriver driver = null;

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}

		String destinationScreenshotPath = Utilities.captureScreenshot(driver, result.getName());

		test.addScreenCaptureFromPath(destinationScreenshotPath);
		test = repo.createTest(testname);
		test.log(Status.FAIL, testname + " got Failed");
		System.out.println(result.getThrowable());
	}

	public void onTestSkipped(ITestResult result) {

		test = repo.createTest(testname);
		test.log(Status.SKIP, testname + " Skipped");
		System.out.println(result.getThrowable());
	}

	public void onFinish(ITestContext context) {
		repo.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
