package Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import Pages.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Test extends HomePage{
	WebDriver driver;
	HomePage homepage;
	
@BeforeTest	
public void init() {
	WebDriverManager webdrivermanager=WebDriverManager.chromedriver();
	webdrivermanager.setup();
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://www.w3schools.com/");
	homepage=PageFactory.initElements(driver,HomePage.class);
	
	
	
	}
@org.testng.annotations.Test
public void Test1() {
	
	HomePage.clickTutorialsLink();
	HomePage.clickLearnHtmlLink();
	System.out.println(homepage.getLearnHtmlLinkText());
}
//Closing Browser
@AfterTest
public void teardown() {
	driver.quit();
}
}
