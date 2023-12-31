package com.ninja.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import com.ninja.base.Base;
import com.ninja.qa.pages.Home;
import com.ninja.qa.utils.Utilities;

public class RegisterTest extends Base {

	public RegisterTest() {
		super();
	}

	public WebDriver driver;

	@BeforeMethod
	public void setup() {
		driver = intializeBrowser(prop.getProperty("browser"));
		Home home = new Home(driver);
		home.clickAccountMenu();
		home.click_register();

	}

	@AfterMethod
	public void tearDown() {
		this.driver.close();
		// this.driver.quit();
	}

	@Test
	public void verifyRegisteMandatoryFields() {

		driver.findElement(By.id("input-firstname")).sendKeys(dataprop.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataprop.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(Utilities.getSaltString());
		driver.findElement(By.id("input-telephone")).sendKeys(dataprop.getProperty("phone"));
		driver.findElement(By.id("input-password")).sendKeys("9876123123");
		driver.findElement(By.id("input-confirm")).sendKeys("9876123123");
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		String actualMessage = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		String ExpectedMessage = dataprop.getProperty("accountCreatedMessage");

		Assert.assertEquals(actualMessage, ExpectedMessage);

	}

	@Test
	public void verifyDuplicateRegistration() {

		driver.findElement(By.id("input-firstname")).sendKeys(dataprop.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataprop.getProperty("lastName"));

		driver.findElement(By.id("input-email")).sendKeys("Pradeep9898@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys(dataprop.getProperty("phone"));

		driver.findElement(By.id("input-password")).sendKeys("9876123123");
		driver.findElement(By.id("input-confirm")).sendKeys("9876123123");
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		String actualMessage = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']"))
				.getText();
		String ExpectedMessage = dataprop.getProperty("emailExistsMessage");
		Assert.assertEquals(actualMessage, ExpectedMessage);

	}

}
