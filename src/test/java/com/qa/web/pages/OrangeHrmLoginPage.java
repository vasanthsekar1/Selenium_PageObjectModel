package com.qa.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class OrangeHrmLoginPage {

	@FindBy(xpath = "//*[@name='username']")
	private WebElement username;
	@FindBy(xpath = "//*[@name='password']")
	private WebElement password;
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement loginButton;
	@FindBy(xpath = "//div[@role='alert']/div/p")
	private WebElement invalidLoginErrorMessage;

	// Action Methods

	public void setUserName(String usernameText, ExtentTest logger) {
		username.sendKeys(usernameText);
		logger.log(Status.INFO, "set username: " + usernameText);
	}

	public void setPassword(String passwordText, ExtentTest logger) {
		password.sendKeys(passwordText);
		logger.log(Status.INFO, "set passeord: " + passwordText);
	}

	public void clickLoginButton(ExtentTest logger) {
		loginButton.click();
		logger.log(Status.INFO, "clickLoginButton");
	}

	public boolean checkInvalidLoginErrorMessageIsDisplayed(ExtentTest logger) {
		logger.log(Status.INFO, "checkInvalidLoginErrorMessageIsDisplayed");
		return invalidLoginErrorMessage.isDisplayed();
	}

	public String getInvalidLoginErrorMessage(ExtentTest logger) {
		logger.log(Status.INFO, "getInvalidLoginErrorMessage");
		return invalidLoginErrorMessage.getText().trim();
	}
}
