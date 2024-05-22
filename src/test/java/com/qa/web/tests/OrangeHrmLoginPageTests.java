package com.qa.web.tests;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.qa.web.base.BaseTest;
import com.qa.web.pages.OrangeHrmHomePage;
import com.qa.web.pages.OrangeHrmLoginPage;
import com.qa.web.utils.ExcelUtil;
import com.qa.web.utils.Util;

public class OrangeHrmLoginPageTests extends BaseTest implements IHookable {

	public OrangeHrmLoginPage orangeHrmLoginPage;
	public OrangeHrmHomePage orangeHrmHomePage;

	@DataProvider(name = "masterDataProvider")
	public Object[][] getdata(Method method) {
		ArrayList<Map<String, String>> result = new ArrayList<Map<String, String>>();
		String excelPath = System.getProperty("user.dir") + "/src/test/resources/OrangeHrmLoginPageTests.xlsx";
		Map<String, LinkedHashMap<String, String>> data = ExcelUtil.readValueFromExcelAsMap(excelPath, "Sheet1");
		ArrayList<String> tcNames = new ArrayList<String>(data.keySet());
		for (String key : tcNames) {
			if (key.contains(method.getName())) {
				result.add(data.get(key));
			}
		}
		Object[][] value = new Object[result.size()][1];
		for (int i = 0; i < result.size(); i++) {
			value[i][0] = result.get(i);
		}

		return value;

	}

	public void initElement() {
		orangeHrmLoginPage = PageFactory.initElements(driver, OrangeHrmLoginPage.class);
		orangeHrmHomePage = PageFactory.initElements(driver, OrangeHrmHomePage.class);

	}

	@Test(dataProvider = "masterDataProvider", groups = { "smoke", "regression" }, enabled = true)
	public void validateLoginFeature(Map<String, String> data) {
		orangeHrmLoginPage.setUserName(data.get("UserName"), logger);
		orangeHrmLoginPage.setPassword(data.get("Password"), logger);
		orangeHrmLoginPage.clickLoginButton(logger);
		if (!data.get("TestMethod").equals("validateLoginFeatureWithValidUserNameAndValidPassword")) {
			softAssert.assertTrue(orangeHrmLoginPage.checkInvalidLoginErrorMessageIsDisplayed(logger),
					"Invalid Login Error Message not Displayed");
			softAssert.assertEquals(orangeHrmLoginPage.getInvalidLoginErrorMessage(logger), "Invalid credentials",
					"Invalid login error message mismatch");
		} else {

			softAssert.assertTrue(orangeHrmHomePage.checkDashboardHeadingIsDisplayed(logger),
					"Dashboard page heading is not displayed");
		}
		Util.waitForPageToLoad(3);
	}

	@Override
	public void run(IHookCallBack callBack, ITestResult testResult) {
		reportInit(testResult.getTestContext().getName(), testResult.getMethod().getMethodName());
		initElement();
		softAssert = new SoftAssert();
		logger.log(Status.INFO, "Starting test " + testResult.getName());
		callBack.runTestMethod(testResult);
		softAssert.assertAll();
	}

}
