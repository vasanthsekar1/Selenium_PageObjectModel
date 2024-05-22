package com.qa.web.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Util {
	public static void waitForPageToLoad(int sec) {
		try {
			TimeUnit.SECONDS.sleep(sec);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void explicitWaitForElementToLoad(WebDriver driver,String locator) {
		WebDriverWait wait = new WebDriverWait (driver, 30);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator)));

	}
}
