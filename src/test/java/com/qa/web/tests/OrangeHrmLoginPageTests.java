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

import com.qa.web.base.BaseTest;
import com.qa.web.pages.OrangeHrmLoginPage;
import com.qa.web.utils.ExcelUtil;
import com.relevantcodes.extentreports.LogStatus;

public class OrangeHrmLoginPageTests extends BaseTest implements IHookable {

	public OrangeHrmLoginPage orangeHrmLoginPage;

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
		logger.log(LogStatus.INFO, "Value from excel read successfully");

		return value;

	}

	public void initElement() {
		orangeHrmLoginPage = PageFactory.initElements(driver, OrangeHrmLoginPage.class);

	}

	@Test(dataProvider = "masterDataProvider", groups = { "smoke", "regression" }, enabled = true)
	public void validateLoginWithValidCredentials(Map<String, String> data) {
		orangeHrmLoginPage.setUserName(data.get("UserName"));
		orangeHrmLoginPage.setUserName(data.get("Password"));
		orangeHrmLoginPage.clickLoginButton();
	}

	@Override
	public void run(IHookCallBack callBack, ITestResult testResult) {
		reportInit(testResult.getTestContext().getName(), testResult.getMethod().getMethodName());
		initElement();
		logger.log(LogStatus.INFO, "Starting test " + testResult.getName());
		callBack.runTestMethod(testResult);

	}

}
