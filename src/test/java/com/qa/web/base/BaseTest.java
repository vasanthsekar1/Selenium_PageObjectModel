package com.qa.web.base;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public ExtentTest logger = null;
	public ExtentReports report = null;
	protected WebDriver driver = null;
	protected SoftAssert softAssert = null;
	public ExtentSparkReporter htmlReporter;

	@BeforeSuite
	public void before() {
		htmlReporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "/test-output/Reports/ExtentReport.html");
		report = new ExtentReports();
		report.attachReporter(htmlReporter);

	}

	public void reportInit(String testContextName, String testMethodName) {

		logger = report.createTest(testMethodName);

	}

	@AfterMethod(alwaysRun = true)
	public void takeScreenshot(ITestResult result) {
		if (result != null) {
			TakesScreenshot ts = null;
			File source = null;
			String screenshotPath = null;
			if (ITestResult.FAILURE == result.getStatus()) {
				try {
					ts = (TakesScreenshot) driver;
					source = ts.getScreenshotAs(OutputType.FILE);
					screenshotPath = System.getenv("user.dir") + "/test-output/Reports/Screenshot" + result.getName()
							+ "_" + System.currentTimeMillis() + ".png";
					FileUtils.copyFile(source, new File(screenshotPath));
					logger.addScreenCaptureFromPath(screenshotPath);
					logger.log(Status.INFO, result.getThrowable());
					logger.log(Status.FAIL, result.getName() + " testcase failed");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

			} else if (ITestResult.SUCCESS == result.getStatus()) {
				logger.log(Status.PASS, result.getName() + " testcase passed");
			}
		}

	}

	@AfterSuite
	public void afterSuite() {
		if (report != null) {
			report.flush();

		}
	}

	@BeforeMethod
	public void setupBrowser(ITestContext context) {
		String browser = context.getCurrentXmlTest().getParameter("Browser");
		String url = context.getCurrentXmlTest().getParameter("url");
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browser.equalsIgnoreCase("safari")) {
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
		}
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
