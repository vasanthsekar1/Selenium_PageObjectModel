package com.qa.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrangeHrmLoginPage {

	@FindBy(xpath = "//*[@name='username']")
	private WebElement username;
	@FindBy(xpath = "//*[@name='password']")
	private WebElement password;
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement loginButton;

	// Action Methods

	public void setUserName(String usernameText) {
		username.sendKeys(usernameText);
	}

	public void setPassword(String passwordText) {
		password.sendKeys(passwordText);
	}

	public void clickLoginButton() {
		loginButton.click();
	}
}
