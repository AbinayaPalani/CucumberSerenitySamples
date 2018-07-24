package Steps;

import java.util.LinkedHashMap;
import java.util.Map;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.core.SerenityReports;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class cancel {
	
	Map<String, Object> cancelObject 					=					new LinkedHashMap<String, Object>();
	
	
	
	Response responseCancel;
	
	
	
	
	public class CancelStepDefinition{
		
		@Step
		public void getProperCancelObject() {
			
			
			
			
		}
		
		
		@Step
		public void callCancellationService() {
			responseCancel							=					SerenityRest.given()
																				.when()
																				.accept(ContentType.JSON)
																				.contentType(ContentType.JSON)
																				.queryParam("accountPin", CreateProfileStepDefinition.accountPin)
																				.queryParam("brandId", CreateProfileStepDefinition.getBrandId.get("brandId"))
																				.queryParam("milliSecs", "")
																				.queryParam("reason", "Testing the cancellation process")
																				.queryParam("contactName", "Abinaya")
																				.queryParam("email", "abinaya@mail.com")
																				.queryParam("phoneNumber", "97676777787")
																				.delete("/v1/cancelAccount");
			
		}
		
		
		
		
	}
	
	
	
	@Given("^Get the proper object to process the cancellation$")
	public void get_the_proper_object_to_process_the_cancellation() throws Exception {
		
	}

	@When("^Call the cancellation service$")
	public void call_the_cancellation_service() throws Exception {
		
	}

	@Then("^verify the status Code$")
	public void verify_the_status_Code() throws Exception {
		
	}

	@Then("^validate the response of cancellation$")
	public void validate_the_response_of_cancellation() throws Exception {
		
	}

	@Then("^get the user details then check the user status, according to cancellation STATUS shoule be `Cancel`$")
	public void get_the_user_details_then_check_the_user_status_according_to_cancellation_STATUS_shoule_be_Cancel() throws Exception {
		
	}

	@Then("^validate the order object, check whether order changed to finsihed$")
	public void validate_the_order_object_check_whether_order_changed_to_finsihed() throws Exception {
		
	}

	@Then("^If status is not changed to cancel, failed to process the cancel$")
	public void if_status_is_not_changed_to_cancel_failed_to_process_the_cancel() throws Exception {
		
	}

	@Then("^If order is not changed to finished status, need to check the monthly order of the customer$")
	public void if_order_is_not_changed_to_finished_status_need_to_check_the_monthly_order_of_the_customer() throws Exception {
		
	}



}
