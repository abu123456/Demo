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
	private String projectpath = System.getProperty("user.dir");// ��̬��ȡ��Ŀ·��
	
	@BeforeClass
	public void startIE(){
		System.setProperty("Webdriver.ie.driver", projectpath+"/tool/IEDriverServer64.exe");
		caps = DesiredCapabilities.internetExplorer();
		//caps.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS,false);
		caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);//���ⱨ���������ģʽ�Ĵ���
		caps.setCapability(InternetExplorerDriver.IE_SWITCHES,"-private");//���������privateģʽ
		caps.setCapability("ignoreZoomSetting",true);
		iewb = new InternetExplorerDriver(caps);
	}
	
	@Test
	public void searchOnbaidu(){
		iewb.manage().window().maximize();//������������
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


