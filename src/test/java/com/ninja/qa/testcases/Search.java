package com.ninja.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import com.ninja.base.Base;

public class Search extends Base {
	
	public Search(){
		super();
		// TODO Auto-generated constructor stub
	}
	WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		
		driver= intializeBrowser(prop.getProperty("browser"));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
	@Test
	public void verifyValidSearch() {
		
		driver.findElement(By.xpath("//input[@name='search']")).sendKeys("iPhone");
		driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
		
	}

}
