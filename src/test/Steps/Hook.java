package Steps;

import cucumber.api.java.Before;
import io.restassured.RestAssured;

public class Hook {
	
	@Before
	public static void backGroundUrl(){
		
		RestAssured.baseURI = "http://staging.jbilling.a-cti.com:8081";
		
	}

}
