package practicetwo.launch;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Browsers {
	public WebDriver driver = null;
	private FirefoxProfile firefoxprofile = null;
	private static DesiredCapabilities caps = null;
	private String projectpath = "D:\\Selenium\\trunk";
	
	public Browsers(BrowsersType browserstype){
	
		switch(browserstype){
			case firefox:
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
				driver = new FirefoxDriver(firefoxprofile);
				driver.manage().window().maximize();
				break;
			case ie:
				System.setProperty("webdriver.id.driver", projectpath+"/tool/IEDriver64.exe");
				caps = DesiredCapabilities.internetExplorer();
				caps.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS,false);
				caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);//以免报浏览器保护模式的错误
				caps.setCapability(InternetExplorerDriver.IE_SWITCHES,"-private");//开启浏览器private模式
				caps.setCapability("ignoreZoomSetting",true);
				driver = new InternetExplorerDriver(caps);
				driver.manage().window().maximize();
				break;
			case chrome:
				System.setProperty("webdriver.chrome.driver", projectpath+"/tool/chromedriver.exe");
				caps = DesiredCapabilities.chrome();
				caps.setCapability("chrome.switches", Arrays.asList("--start-maximized"));//浏览器窗口最大化
				caps.setCapability("chrome.switches",Arrays.asList("--proxy-server=http://your-proxy-domain:4443"));//设置代理
				driver = new ChromeDriver(caps);
				driver.manage().window().maximize();
				break;
			
			}
	}
}
