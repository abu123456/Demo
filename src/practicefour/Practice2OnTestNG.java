package practicefour;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import practicetwo.launch.Browsers;
import practicetwo.launch.BrowsersType;

public class Practice2OnTestNG {
	
	protected static WebDriver ffwb = null;
	private FirefoxProfile firefoxprofile = null;
	private String projectpath = System.getProperty("user.dir");
	
	private ParseProperties data;
	private ParseProperties laocator;
	private Wait wait;
	
	@Parameters({"data","locator"})
	@BeforeClass
	public void startFirefox(@Optional("data")String da,@Optional("loc1")String loc){
		data = new ParseProperties(System.getProperty("user.dir")+da);
		locator = new ParseProperties(System.getProperty("user.dir")+loc);
		
		Browsers browser = new Browsers(BrowsersType.firefox);
		ffwb = browser.driver;
		wait = new Wait(ffwb);
		
	}
	
	@Test
	public void accessMail(){
		wait = new Wait(ffwb);
		
		ffwb.get(data.getValue("url"));
		ffwb.findElement(By.xpath(locator.getValue("username"))).clear();
		ffwb.findElement(By.xpath(locator.getValue("username"))).sendKeys(data.getValue("username"));
		ffwb.findElement(By.xpath(locator.getValue("password"))).sendKeys(data.getValue("password"));
		ffwb.findElement(By.xpath(locator.getValue("submit"))).click();
		
		
		wait.waitForElementPresent(locator.getValue("homepage"));
		
		Assert.assertEquals(ffwb.findElement(By.xpath(locator.getValue("sendbox"))).isDisplayed(),true);
		
		//wait.waitFor(1000);
	}
	
	@Test
	public void getUnReadMails(){
		int expectedRes = 40;
		String unreadmailsnum = ffwb.findElement(By.xpath(locator.getValue("inboxMailsNum"))).getText();
		String str = unreadmailsnum.substring(1, unreadmailsnum.length()-1);
		int i = Integer.valueOf(str);
		System.out.println(i);
		Assert.assertEquals((expectedRes == i),true );
	}
	@AfterClass
	public void release(){
		ffwb.quit();
	}
}
