package Pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class HomePage {
	@FindAll({@FindBy(xpath="//*[@title=\'Tutorials\']")})
	static List<WebElement> tutorialsLinkList;
	static WebElement tutorialsLink;
	
	
	@FindAll({@FindBy(xpath="//*[@id=\'nav_tutorials\']/div/div/div[2]/a[1]")})
	static
	List<WebElement> learnHtmlLinkList;
	static WebElement learnHtmlLink;
	
	@FindAll({@FindBy(xpath="//*[@id=\'main\']/h1")})
	List<WebElement> htmlTutorialHeadingList;
	WebElement htmlTutorialHeading;
	
	
	
	
	
	public static void clickTutorialsLink() {
		tutorialsLink=listElement(tutorialsLinkList);
		tutorialsLink.click();
	}
	public static void clickLearnHtmlLink() {
		learnHtmlLink=listElement(learnHtmlLinkList);
		learnHtmlLink.click();
		
	}
	
	public String getLearnHtmlLinkText() {
		htmlTutorialHeading=listElement(htmlTutorialHeadingList);
		return htmlTutorialHeading.getText().toString();
	}

	
	public static WebElement listElement(List<WebElement> e) {
		List<WebElement> element=e;
		WebElement result = null;
		for(int i=0;i<element.size();i++) {
			try {
			if(element.get(i).isDisplayed()) {
				 result=element.get(i);
			}
			else {
				return null;
			}
			}
			catch(Exception e1) {
				return null;
			}
		}
		return result;
		
	}
}
