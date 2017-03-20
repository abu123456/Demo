package practicetwo.launch;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestBrowser {
	private WebDriver driver;
	//private APIObject testlinkapi;
	private String caseName;
	@BeforeClass
	public void beforeClass(){
		String url = "http://localhost/testlink/testlink-1.9.3/lib/api/xmlrpc.php";
		String devKey = "30c180b8b6e68be02a032152cc0e94a4";
		String projectName = "TerrySeleniumTraining";
		String tl = "WebdriverLesson";
		String buildName = "build001";
		String platform = "IE";
		
	//	testlinkapi = new APIOject(url,devKey,projectName,tl,buildName,platform);
		Browsers browser = new Browsers(BrowsersType.firefox);
		driver = browser.driver;
	}
	@Test
	public void start(){
		System.out.println("¿ªÊ¼");
	}
	
	@AfterClass
	public void quit(){
		driver.quit();
	}
}
