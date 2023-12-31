package com.ninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	@FindBy(id = "input-email")
	private WebElement emailTextbox;

	@FindBy(id = "input-password")
	private WebElement passwordTextbox;

	@FindBy(xpath = "//input[@value='Login']")
	private WebElement loginButton;

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement user_not_valid;

	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void enter_email(String email) {
		emailTextbox.sendKeys(email);
	}

	public void enter_password(String password) {
		passwordTextbox.sendKeys(password);
	}

	public void click_login_button() {
		loginButton.click();
	}

	public String verify_login_failure_message() {
		return user_not_valid.getText();
	}
}
