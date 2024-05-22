package com.qa.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrangeHrmHomePage {

	@FindBy(css = ".oxd-topbar-header>div>span>h6")
	private WebElement dashboardHeading;

	// Action Methods

	public boolean checkDashboardHeadingIsDisplayed() {
		return dashboardHeading.isDisplayed();
	}

	public String getDashboardHeadingText() {
		return dashboardHeading.getText().trim();
	}
}
