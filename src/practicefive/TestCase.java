package practicefive;

import java.awt.List;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;
import practicefour.Wait;

public class TestCase {
	
	private WebDriver ffwb = null;
	private FirefoxProfile firefoxprofile = null;
	private String projectpath = System.getProperty("user.dir");
	private Wait wait = null;
	
	@BeforeClass
	public void startFirefox(){
		File firebug = new File(projectpath+"/tool/firebug-2.0.18-fx.xpi");
		File firePath = new File(projectpath+"/tool/firepath-0.9.7-fx.xpi");
		firefoxprofile = new FirefoxProfile();
		try{
			firefoxprofile.addExtension(firebug);
			firefoxprofile.addExtension(firePath);
			firefoxprofile.setPreference("webdriver.accpt.untrusted.certs","true");
			firefoxprofile.setPreference("ectension.firebug.currentVersion","2.0.18");
			firefoxprofile.setPreference("network.proxy.type",0);
			firefoxprofile.setPreference("network.proxy.http","10.1.1.0");
			firefoxprofile.setPreference("network.proxy.http_port",3128);
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void changeiFrame(){
		ffwb.get("http://jqueryui.com/slider/");
		wait.waitforElementPresent(JQUERYHOME);
		ffwb.switchTo().frame(ffwb.findElement(By.xpath(SLIDERIFRAME)));
		Point initialPoint = ffwb.findElement(By.xpath(SLEDER)).getLocation();
		System.out.println(initialPoint);
		
		Actions dragger = new Actions(ffwb);
		dragger.dragAndDropBy(ffwb.findElement(By.xpath(SILDER)).initialPoint.getX()+599,initialPoint.getY()).build().perform();
		initialPoint = ffwb.findElement(By.xpath(SLIDER)).getLocation();
		System.out.println(initialPoint);
		wait.waitFor(5000);
		
		ffwb.switchTo().defaultContent();
		ffwb.findElement(By.xpath(DRAGGABLE)).click();
		wait.waitFor(3000);
		
		
	}
	
	@Test
	public void selectItemFromDropDownList(){
		Select city = new Select(ffwb.findElement(By.xpath("//select[@id=city]")));
		List<WebElement> allcitys = city.getOptions();
		for(WebElement eachcity:allcitys)
			System.out.println(eachcity.getText());
	}
	
	@Test
	public void changeWindows(){
		Switch switchW = new Switch(ffwb);
		switchW.toSpecificWindow("Web Browser Autmomation");
	}
	
	@Test
	public void mouseRightclickContext(){
		Actions actions = new Actions(ffwb);
		actions.contextClick(ffwb.findElement(By.xpath("//div"))).build().perform();
		wait.waitFor(2000);
		ffwb.findElement(By.xpath("//div")).click();
		wait.waitFor(2000);
		Assert.assertEquals(ffwb.findElement(By.xpath("//div")).isDisplayed(),true);
		
	}
	
	@Test
	public void drayAndDrop(){
		
	}
	
	@AfterClass
	public void releaseFFDriver(){
		ffwb.quit();
	}
	
}
