package com.qa.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class OrangeHrmHomePage {

	@FindBy(css = ".oxd-topbar-header>div>span>h6")
	private WebElement dashboardHeading;

	// Action Methods

	public boolean checkDashboardHeadingIsDisplayed(ExtentTest logger) {
		logger.log(Status.INFO, "checkDashboardHeadingIsDisplayed");
		return dashboardHeading.isDisplayed();
	}

	public String getDashboardHeadingText(ExtentTest logger) {
		logger.log(Status.INFO, "getDashboardHeadingText");
		return dashboardHeading.getText().trim();
	}
}
