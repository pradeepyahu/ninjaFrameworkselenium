package com.ninja.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.ninja.base.Base;
import com.ninja.qa.pages.AccountPage;
import com.ninja.qa.pages.Home;
import com.ninja.qa.pages.LoginPage;
import com.ninja.qa.utils.Utilities;

public class LoginTest extends Base {

	LoginPage login;

	public LoginTest() {
		super();
	}

	public WebDriver driver;

	@BeforeMethod
	public void setup() {

		driver = intializeBrowser(prop.getProperty("browser"));

		Home home = new Home(driver);
		home.clickAccountMenu();
		login = home.clickLogin();
	}

	@AfterMethod
	public void tearDown() {
		this.driver.close();
		// this.driver.quit();
	}

	@Test(priority = 1)
	public void verifyWithValidCreds() {

		login.enter_email(dataprop.getProperty("validUsername"));
		login.enter_password(dataprop.getProperty("invalidPwd"));
		login.click_login_button();

		AccountPage accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.LoggedInSuccessfully());

	}

	@Test(priority = 2)
	public void verifyWithValidUnameInValidPwd() {

		login.enter_email(dataprop.getProperty("validUsername"));
		login.enter_password(dataprop.getProperty("invalidPwd"));
		login.click_login_button();

		String actualMessage = login.verify_login_failure_message();
		String expectedMessage = dataprop.getProperty("userName_PWD_not_matching");

		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Warning: No match for E-Mail Address and/or Password Not displayed.");

	}

	@Test(priority = 3)
	public void verifyWithInvalidCreds() {

		login.enter_email(Utilities.getSaltString());
		login.enter_password(dataprop.getProperty("invalidPwd"));
		login.click_login_button();

		String actualMessage = login.verify_login_failure_message();
		String expectedMessage = dataprop.getProperty("userName_PWD_not_matching");

		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Warning: No match for E-Mail Address and/or Password Not displayed.");

	}

	@Test(priority = 4)
	public void verifyWithBlankCreds() {

		login.enter_email("  ");
		login.enter_password("       ");
		login.click_login_button();

		String actualMessage = login.verify_login_failure_message();
		String expectedMessage = dataprop.getProperty("userName_PWD_not_matching");

		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Warning: No match for E-Mail Address and/or Password Not displayed.");

	}

	@DataProvider(name = "invalidTestDataFromExcel")
	public Object[][] testDataSupplier() {
		Object[][] data = Utilities.readFromExcel("Login");
		return data;
	}

	@Test(priority = 5, dataProvider = "invalidTestDataFromExcel")
	public void verifyWithInvalidUnameValidPwd(String email, String password) {

		login.enter_email(email);
		login.enter_password(password);
		login.click_login_button();

		String actualMessage = login.verify_login_failure_message();
		String expectedMessage = dataprop.getProperty("userName_PWD_not_matching");
		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Warning: No match for E-Mail Address and/or Password Not displayed.");

	}

}
