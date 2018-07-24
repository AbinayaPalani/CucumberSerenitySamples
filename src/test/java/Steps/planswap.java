package Steps;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class planswap {
	
	
	
	public Map<String, Object> planswapObject 			=			new LinkedHashMap<String, Object>();
	JSONObject plans, plansAndAddons, plansObj, voiceObject, ivrObject;
	String voiceId, ivrId, requestPlanSwap;
	JSONArray voicePlans, ivrPlans;
	Response randPlans, responseSwapPlans, responseSelectedPlansAndAddons, responsePlanSwapHistory;
	Gson gson 										=  new Gson();
	
	String BrandId = "5a6e67a6-8bfd-45f5-a774-3462cb0c4e4c";
	String AccountPin = "90e4f4e4-e83d-4a5e-9ae3-60e676c1cb96";
	
	public class StepPlanSwap{

		
		@Test
		@Step
		public void getRandomPlansToUpdate() {
			
			
			randPlans							=						SerenityRest.given()
																			.when()
																			.accept(ContentType.JSON)
																			.contentType(ContentType.JSON)
																			.queryParam("brandId", BrandId)
																			.get("/v1/getStaticPlansAndAddons");
			
			plansAndAddons						=						new JSONObject(randPlans.asString());
			plansObj							=						plansAndAddons.getJSONObject("data");
			plans								=						plansObj.getJSONObject("plans");
			voicePlans							=						plans.getJSONArray("voicePlan");
			ivrPlans							=						plans.getJSONArray("ivrPlan");
					
			Random rand							=							new Random();
			 
			int num1						    =							rand.nextInt(voicePlans.length() -1) + 1;
			
			voiceObject							=							voicePlans.getJSONObject(num1);
			
			voiceId								=							voiceObject.getString("id");
			
			System.out.println(voiceId);
			
			int num2						    =							rand.nextInt(ivrPlans.length() -1) + 1;
			
			ivrObject							=							ivrPlans.getJSONObject(num2);
			
			ivrId								=							ivrObject.getString("id");
			
			System.out.println(ivrId);
			 		
			
		}
		@Test
		@Step
		public void requestObjectForPlanSwap() {
			
			planswapObject.put("uniquePin", AccountPin);
			//planswapObject.put("userId", );
			planswapObject.put("brandId", BrandId);
			planswapObject.put("voicePlanId", voiceId);
			planswapObject.put("ivrPlanId", ivrId);
			planswapObject.put("addedAddonsId", "");
			
									
			requestPlanSwap		=		gson.toJson(planswapObject, LinkedHashMap.class);
			
			System.out.println(requestPlanSwap);
			
		
		}
		@Test
		@Step
		public void updateSwapPlan() {
			
			responseSwapPlans					=					SerenityRest.given()
																			.when()
																			.accept(ContentType.JSON)
																			.contentType(ContentType.JSON)
																			.body(requestPlanSwap)
																			.put("/updateSelectedPlansAndAddons");
			
			System.out.println(responseSwapPlans.asString());
			
			
			Assert.assertEquals(200, responseSwapPlans.getStatusCode());
			
			
			
		}
		
		@Test
		@Step
		public void checkTheSelectedPlansAndAddonsThroughGetService() {
			
			
			responseSelectedPlansAndAddons		=				SerenityRest.given()
																		.when()
																		.accept(ContentType.JSON)
																		.contentType(ContentType.JSON)
																		//.queryParam("userId", CreateProfileStepDefinition.resJson_createUser.get("userId"))
																		.queryParam("uniquePin", AccountPin)
																		.queryParam("brandId", BrandId)
																		.get("/v1/getSelectedPlansAndAddons");
																		
																		
			
		}
		
		@Test
		@Step
		public void checkThePlanSwapHistory() {
			
			
			responsePlanSwapHistory			=				SerenityRest.given()
																		.when()
																		.accept(ContentType.JSON)
																		.contentType(ContentType.JSON)
																		.queryParam("accountPin", AccountPin)
																		.queryParam("brandId", BrandId)
																		.get("/planSwapHistory");
																		
																		
			
		}
		
	}
	
	
	@Steps
	StepPlanSwap swapSteps;
	
	@Given("^Get the plan swap object$")
	public void get_the_plan_swap_object() throws Exception {
	    
		swapSteps.getRandomPlansToUpdate();
	}


	@When("^call the updateSelectedPlansAndAddons service$")
	public void call_the_updateSelectedPlansAndAddons_service() throws Exception {
	    
		
		swapSteps.requestObjectForPlanSwap();
		swapSteps.updateSwapPlan();
	}

	@When("^Check with relevant get services to verify the current plans and existing plan details$")
	public void check_with_relevant_get_services_to_verify_the_current_plans_and_existing_plan_details() throws Exception {
	    
		swapSteps.checkTheSelectedPlansAndAddonsThroughGetService();
		swapSteps.checkThePlanSwapHistory();
		
	}


	

}
