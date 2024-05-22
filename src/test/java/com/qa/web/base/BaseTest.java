package com.qa.web.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public ExtentTest logger;
	public ExtentReports report;
	protected WebDriver driver;
	protected SoftAssert softAssert = null;

	@BeforeSuite
	public void before() {
		report = new ExtentReports("test-output/Reports/ExtentReport.html", true);
	}

	public void reportInit(String testContextName, String testMethodName) {
		logger = report.startTest(testContextName, testMethodName);
		logger.assignAuthor("Vasanth");
	}

	@AfterSuite
	public void tearDownSuite() {
		report.flush();
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
		report.endTest(logger);
		driver.quit();
	}
}
