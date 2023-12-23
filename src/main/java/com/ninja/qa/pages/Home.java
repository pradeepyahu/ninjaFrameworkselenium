package com.ninja.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home {
	
	WebDriver driver;
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	private WebElement myAccountMenu;
	
	@FindBy(linkText="Login")
	private WebElement login;
	
	@FindBy(linkText="Register")
	private WebElement Register;
	
	public Home(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	public void clickAccountMenu() {
		myAccountMenu.click();
	}
	
	public LoginPage clickLogin() {
		login.click();
		return new LoginPage(driver);
	}
	
	public void click_register() {
		Register.click();
	}
	

}
