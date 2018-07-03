package Steps;

import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class GetPlansAndAddonsStepDefinition {
	
	public static Response response_getPlansAndAddons, responseVoicePlanId, responseIvrPlanId;
	public static String ivrPlanId, voicePlanId;
	public static JSONObject plansAndAddons, plansData, Plans, ivrPlanObj, voicePlanObj;
	public static JSONArray ivrPlans, voicePlans;
	
	
	public class GetPlansSteps{
		
		@Step
		public void getPlansAndAddonsInformation(){
			
			//paramValue.paramData();
			
			response_getPlansAndAddons							=							SerenityRest.given().contentType(ContentType.JSON)
																									.queryParam("brandId", CreateProfileStepDefinition.getBrandId.get("brandId").toString())
																									.accept(ContentType.JSON)
																									.get("/v1/getStaticPlansAndAddons");

		}
		
		@Step
		public void assertTheStatusCodeAndResponseStatus(){
			
			plansAndAddons										=							new JSONObject(response_getPlansAndAddons.asString());

			Assert.assertEquals("Not getting the proper status code", 200, response_getPlansAndAddons.getStatusCode());
			
			Assert.assertEquals("There is a problem with getting plans and addons","success", plansAndAddons.get("status"));
			
		}
		
		@Step
		public void pickRandomPlansInformation(){
			
			//paramValue.paramData();

			
			
			System.out.println(plansAndAddons);
			 
			 
			 plansData									  		=							plansAndAddons.getJSONObject("data");
			 
			 Plans												=							plansData.getJSONObject("plans");
			 
			 ivrPlans											=							Plans.getJSONArray("ivrPlan");
			 
			 System.out.println(ivrPlans);
			 
			 Random rand										=							new Random();
			 
			 int num1											=							rand.nextInt(ivrPlans.length() -1) + 1;
			 
			 System.out.println("Random : "+num1);
			 
			 ivrPlanObj											=							ivrPlans.getJSONObject(num1);
			 
			 System.out.println("IVR Object Data"+ ivrPlanObj);
			 
			 System.out.println(ivrPlanObj.get("id"));
			 
			 ivrPlanId											=							String.valueOf(ivrPlanObj.get("id"));
			 
			 System.out.println("IVR Plan Id: "+ivrPlanId);
			 	 
			 voicePlans											=							Plans.getJSONArray("voicePlan");
			 
			 System.out.println(voicePlans);
			 
			 int num2											=							rand.nextInt(voicePlans.length() - 1) + 1;
			 
			 voicePlanObj										=							voicePlans.getJSONObject(num2);
			 
			 voicePlanId										=							String.valueOf(voicePlanObj.get("id"));
			 
			 System.out.println(voicePlanId);
			 
			 responseVoicePlanId								=							SerenityRest.given()
																								.accept(ContentType.JSON)
																								.queryParam("brandId", CreateProfileStepDefinition.getBrandId.get("brandId").toString())
																								.queryParam("planId", voicePlanId)
																								.contentType(ContentType.JSON)
																								.get("/getPlanDetails");

			 responseIvrPlanId									=							SerenityRest.given()
																								.accept(ContentType.JSON)
																								.queryParam("brandId", CreateProfileStepDefinition.getBrandId.get("brandId").toString())
																								.queryParam("planId", ivrPlanId)
																								.contentType(ContentType.JSON)
																								.get("/getPlanDetails");

			 
		}
		
		
		
	}

	@Steps
	GetPlansSteps pickPlanStep;
	
	@Given("^call the getStaticPlansAndAddons service with the relavent brandId$")
	public void call_the_getStaticPlansAndAddons_service_with_the_relavent_brandId() throws Exception {
	   
		pickPlanStep.getPlansAndAddonsInformation();
	}

	@When("^validate the statusCode and response from it$")
	public void validate_the_statusCode_and_response_from_it() throws Exception {
	  
		pickPlanStep.assertTheStatusCodeAndResponseStatus();
	}

	@Then("^get random plans$")
	public void get_random_plans() throws Exception {
		
		pickPlanStep.pickRandomPlansInformation();
	    
	}



	
}
