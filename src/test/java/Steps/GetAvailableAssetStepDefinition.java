package Steps;

import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class GetAvailableAssetStepDefinition {
	
	
	public static Response response_getAvailableAsset, responseAssetInfo;
	public static JSONArray assetData;
	public static JSONObject assetObj;
	public static String AssetId, AssetValue;
	
	public class openAssetSteps{
		
		@Step
		public void getAvailableAssetsInformation() throws Throwable{
			
			//System.out.println(paramValue.createUserObject);
			
			response_getAvailableAsset								=							SerenityRest.given().when()
																										.contentType(ContentType.JSON)
																										.queryParam("brandId", CreateProfileStepDefinition.getBrandId.get("brandId").toString())
																										.queryParam("type", "8xx")
																										.queryParam("limit", "10")
																										.accept(ContentType.JSON)
																										.get("/getAvailableAsset");
																										
			
		}
		@Step
		public void validateTheStatusCode(){
					
			Assert.assertEquals("Not getting the proper Status Code", 200, response_getAvailableAsset.getStatusCode());
		}
		@Step
		public void getParticularAssetInformationToMap(){
			
			assetData 												= 						 new JSONArray(response_getAvailableAsset.asString());
			
			Random rand												=						 new Random();
			
			int assetNum = rand.nextInt(assetData.length()-1) + 1;
			
			assetObj 												=						 assetData.getJSONObject(assetNum);
			
			System.out.println(assetObj);
			
			AssetId 												=						String.valueOf(assetObj.get("assetId"));
			
			AssetValue 												=						String.valueOf(assetObj.get("assetValue"));
			
			responseAssetInfo 										=						SerenityRest.given()
																									.accept(ContentType.JSON)
																									.contentType(ContentType.JSON)
																									.queryParam("assetValue",AssetValue)
																									.get("/getAvailableInfoByAsset");
			
		}
		
		public void assetId(){
			
			AssetId 												=						String.valueOf(assetObj.get("assetId"));

		}
				
		
	}
	
	@Steps
	openAssetSteps assetSteps;
	
	@Given("^call the getAvailableAsset service with the type as EightXX$")
	public void call_the_getAvailableAsset_service_with_the_type_as_EightXX() throws Throwable {
		
		assetSteps.getAvailableAssetsInformation();
	}
	
	@Then("^validate the statusCode and check the response status$")
	public void validate_the_statusCode_and_check_the_response_status() throws Exception {
	   
		assetSteps.validateTheStatusCode();
		
	}
	
	@Then("^get random asset$")
	public void get_random_asset() throws Exception {
		
		assetSteps.getParticularAssetInformationToMap();
	   
	}
	
	



}
