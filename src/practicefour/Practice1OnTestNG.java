package practicefour;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Practice1OnTestNG {
	
	@Parameters({"TestDate"})
	@Test
	public void test(@Optional("aaa") String testdata){
		
		//ParseProperties pp = new ParseProperties(System.getProperty("user.dir")+testdata);
//		System.out.println(pp.getValue("url"));
//		System.out.println(pp.getValue("username"));
//		System.out.println(pp.getValue("password"));
		System.out.println(System.getProperty("user.dir") + testdata);
	}
}
