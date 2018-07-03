package Steps;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Assert;

import com.google.gson.Gson;

import Steps.CreateProfileStepDefinition;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class CreatePlansAddonsStepDefinition {
	
	public Map<String, Object> createPlans								=					new LinkedHashMap<String, Object>();
	Date todayDate 														=					new Date();
	Gson gson 															=							new Gson();
	public static Response response_createPlansAndAddons, responseSelectedPlansAndAddons;
	public static String createPlansObject;
	public static JSONObject responseCreatePlansForCustomer, responseSelectedPlans, SelectedPlansWholeData, ivrObject, voiceObject;
	
	
	public class CreatePlanSteps{
		
		
		
		@Step
		public void createObjectForPlansAndAddons(){
			
			
			createPlans.put("accountPin", CreateProfileStepDefinition.accountPin);
			createPlans.put("brandId", CreateProfileStepDefinition.brandId);
			createPlans.put("voicePlanId", GetPlansAndAddonsStepDefinition.voicePlanId);
			createPlans.put("ivrPlanId", GetPlansAndAddonsStepDefinition.ivrPlanId);
			createPlans.put("addOnIds","");
			createPlans.put("assetId", GetAvailableAssetStepDefinition.AssetId);
			createPlans.put("activeSinceDate",todayDate.getTime());
			createPlans.put("dateCreated", todayDate.getTime());
			
			if(!createPlans.isEmpty()){
				
				createPlansObject										=								gson.toJson(createPlans, LinkedHashMap.class);
				
			}
		
		}
		
		
		@Step
		public void callTheServiceToLinkPlansAndAsset(){
			
			 
			 response_createPlansAndAddons					=							SerenityRest.given()
					 																		.when()
					 																		.contentType(ContentType.JSON)
					 																		.content(createPlansObject)
					 																		.accept(ContentType.JSON)
					 																		.post("/createPlansAndAddons");
		}
		
		@Step
		public void assertTheResponseCodeAndStatus(){
			
			 Assert.assertEquals("Not getting the proper status code, please check the service",200, response_createPlansAndAddons.getStatusCode());

			
			responseCreatePlansForCustomer					=						new JSONObject(response_createPlansAndAddons.asString());
			 
			 
			 Assert.assertEquals("Failed to create a plans and addons for the customer, check the problem",true, responseCreatePlansForCustomer.get("success"));
			 
			 
			
		}
		
		@Step
		public void checkOrderStatusThroughGetService(){
			
			
			 responseSelectedPlansAndAddons					=						SerenityRest.given()
																							.when()
																							.contentType(ContentType.JSON)
																							.queryParam("userId", CreateProfileStepDefinition.userId)
																							.queryParam("uniquePin", CreateProfileStepDefinition.accountPin)
																							.queryParam("brandId", CreateProfileStepDefinition.getBrandId.get("brandId").toString())
																							.accept(ContentType.JSON)
																							.get("/v1/getSelectedPlansAndAddons");

			 responseSelectedPlans							=					    new JSONObject(responseSelectedPlansAndAddons.asString());
			 
			 SelectedPlansWholeData							=						responseSelectedPlans.getJSONObject("data");
			 
			 ivrObject										=						SelectedPlansWholeData.getJSONObject("ivrPlan");
			 
			 voiceObject									=						SelectedPlansWholeData.getJSONObject("voicePlan");
			 
			 
			 Assert.assertEquals("Voice PlanId is not equal to given data with selected plan id", GetPlansAndAddonsStepDefinition.voiceId, voiceObject.get("id"));
					 
			 Assert.assertEquals("IVR PlanId is not equal to given data with selected plan id", GetPlansAndAddonsStepDefinition.ivrId, ivrObject.get("id"));
			 
			 
			
		}
		
	}
	
	
	@Steps
	CreatePlanSteps linkPlanStep;
	
	@Given("^get the proper data object to assign the plans for the customer$")
	public void get_the_proper_data_object_to_assign_the_plans_for_the_customer() throws Exception {
		
		linkPlanStep.createObjectForPlansAndAddons();
		
	}

	@When("^call the createPlansAndAddons service$")
	public void call_the_createPlansAndAddons_service() throws Exception {
	   
		linkPlanStep.callTheServiceToLinkPlansAndAsset();
	}

	@When("^check the statusCode and validate the response whether plan get assigned or not$")
	public void check_the_statusCode_and_validate_the_response_whether_plan_get_assigned_or_not() throws Exception {

		linkPlanStep.assertTheResponseCodeAndStatus();
	}



	@Then("^once plan assigned to customer and check through getSelectedPlansAndAddons service$")
	public void once_plan_assigned_to_customer_and_check_through_getSelectedPlansAndAddons_service() throws Exception {
	   
		linkPlanStep.checkOrderStatusThroughGetService();
	}




}
