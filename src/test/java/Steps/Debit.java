package Steps;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Assert;
import static org.hamcrest.Matchers.is;
import com.google.gson.Gson;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class Debit {
	
	
	public Map<String, Object> debitObj 		=			new LinkedHashMap<String, Object>();
	Gson gson									=			new Gson();
	
	String requestDebit;
	Response responseDebit, responseOwingBalance;
	
	public class DebitStepDefinition{
		
		@Step
		public void getProperCreditObject() {
			
			debitObj.put("accountPin", CreateProfileStepDefinition.accountPin);
			debitObj.put("brandId", CreateProfileStepDefinition.getBrandId.get("brandId"));
			debitObj.put("debitAmount", "1");
			debitObj.put("approvedBy", "Abinaya");
			debitObj.put("reason", "Testing the debit functionality using serenity framewwork with cucumber and rest assured");
			debitObj.put("description", "Serenity Test by cucumber and rest assured");
			debitObj.put("processDate", System.currentTimeMillis());
			
			requestDebit			=				gson.toJson(debitObj, LinkedHashMap.class);
			
		}
		
		@Step
		public void callingDebitService() {
			
			
			responseDebit 						=						SerenityRest.given()
																				.when()
																				.contentType(ContentType.JSON)
																				.accept(ContentType.JSON)
																				.body(requestDebit)
																				.post("/debitAdjustment");
			
			
			Assert.assertEquals("There is problem in Status Code", 200, responseDebit.getStatusCode());
			
			JSONObject debitResponse			 =			 new JSONObject(responseDebit.asString());
			
			Assert.assertThat("Failing to process the debit functionality, check the problem", debitResponse.get("success"), is(true));
			
			
		}
		
		
		@Step
		public void checkOwingBalanceAfterProcessTheDebit() {
				
			responseOwingBalance 				=				SerenityRest.given()
																	.when()
																	.contentType(ContentType.JSON)
																	.accept(ContentType.JSON)
																	.pathParam("accountPin", "")
																	.pathParam("brandId", "")
																	.get("getLatestInvoiceDetails/accountPin/{accountPin}/brandId/{brandId}");
			
		}
		
		
	}
	
	
	@Given("^Get the proper data$")
	public void get_the_proper_data() throws Exception {
		
	}


	@When("^call the debit service$")
	public void call_the_debit_service() throws Exception {
	}

	@Then("^validate the debit functionality through get service$")
	public void validate_the_debit_functionality_through_get_service() throws Exception {
	}

}
