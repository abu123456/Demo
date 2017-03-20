package practicetwo;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class launchChrome {
	private WebDriver chromewb = null;
	private DesiredCapabilities caps = null;
	private String projectpath = System.getProperty("user.dir");
	
	@BeforeClass
	public void startChrome(){
		System.setProperty("webdriver.chrome.driver", projectpath+"/tool/chromedriver.exe");
		caps = DesiredCapabilities.chrome();
		caps.setCapability("chrome.switches", Arrays.asList("--start-maximized"));//浏览器窗口最大化
		caps.setCapability("chrome.switches",Arrays.asList("--proxy-server=http://your-proxy-domain:4443"));//设置代理
		chromewb = new ChromeDriver(caps);
	}
	
	@Test
	public void searchOnBaidu(){
		chromewb.get("http://www.baidu.com");
	}
	
	@AfterClass
	public void releaseChromeDriver(){
		chromewb.quit();
	}

}
