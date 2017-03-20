package practicetwo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class launchIE {
	private WebDriver iewb = null;
	private DesiredCapabilities caps = null;
	private String projectpath = System.getProperty("user.dir");// 动态获取项目路径
	
	@BeforeClass
	public void startIE(){
		System.setProperty("Webdriver.ie.driver", projectpath+"/tool/IEDriverServer64.exe");
		caps = DesiredCapabilities.internetExplorer();
		//caps.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS,false);
		caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);//以免报浏览器保护模式的错误
		caps.setCapability(InternetExplorerDriver.IE_SWITCHES,"-private");//开启浏览器private模式
		caps.setCapability("ignoreZoomSetting",true);
		iewb = new InternetExplorerDriver(caps);
	}
	
	@Test
	public void searchOnbaidu(){
		iewb.manage().window().maximize();//浏览器最大化设置
		iewb.get("http://www.baidu.com");
		try{
			Thread.sleep(5000);
		} catch (InterruptedException e){
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void releaseIEDriver(){
		iewb.quit();
	}
}


