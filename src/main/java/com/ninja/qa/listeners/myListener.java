package com.ninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ninja.qa.utils.ExtentReporter;
import com.ninja.qa.utils.Utilities;

public class myListener implements ITestListener {

	public static ExtentReports extentReports;
	public static ExtentTest extentTest;
	//
	String testName;

	@Override
	public void onTestStart(ITestResult result) {
		testName = result.getName();

		extentTest = extentReports.createTest(testName);
		extentTest.log(Status.INFO, "Execution Started ");

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		extentTest.log(Status.PASS, "Execution Success ");

	}

	@Override
	public void onTestFailure(ITestResult result) {

		WebDriver driver = null;

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (IllegalArgumentException e) {

			e.printStackTrace();
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		} catch (NoSuchFieldException e) {

			e.printStackTrace();
		} catch (SecurityException e) {

			e.printStackTrace();
		}

		String destinationScreenShotPath = Utilities.captureScreenShot(driver, testName);
		extentTest.addScreenCaptureFromPath(destinationScreenShotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName + "Execution Failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName + "Execution Skipped");
	}

	@Override
	public void onStart(ITestContext context) {
		extentReports = ExtentReporter.generateReport();
		System.out.println("Execution ninja testcase started ");
	}

	@Override
	public void onFinish(ITestContext context) {

		extentReports.flush();
		String reportPath = System.getProperty("user.dir") + "//test-output//Extent_Reports//ExtentReport.html";
		File extentPath = new File(reportPath);
		try {
			Desktop.getDesktop().browse(extentPath.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
