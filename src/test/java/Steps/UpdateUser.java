package Steps;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;


import com.google.gson.Gson;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Step;

public class UpdateUser {
	
	public Map<String, Object> updateUserObject     = 					 new LinkedHashMap<String, Object>();
	public Map<String, Object> updateUserPD			=					 new LinkedHashMap<String, Object>();
	public Map<String, Object> updateUserCD			=					 new LinkedHashMap<String, Object>();
	public Map<String, Object> updateUserAF			=					 new LinkedHashMap<String, Object>();
	public Map<String, Object> updateUserBC1		=					 new LinkedHashMap<String, Object>();
	public Map<String, Object> updateUserOInfo		=					 new LinkedHashMap<String, Object>();
	public Map<String, Object> updateUserCA			=					 new LinkedHashMap<String, Object>();
	public Map<String, Object> updateUserCC			= 					 new LinkedHashMap<String, Object>();
	char[] accountNum 								=					"0123456789".toCharArray();
	StringBuilder AccntNum							=					 new StringBuilder();
	Boolean[] flag									=					 {true, false};
	String[] Type									=					 {"Test","Internal","Standard","Custom Pricing","Franchise", "Sales Lines","Service Lines", "Dedicated"};
	Gson gson										=					 new Gson();
	
	public static String requestJsonUpdateUser;
	
	public class updateUserSteps{
		
		
		

		 
		@Step
		public void createObjectForUpdateUserDetails() {
			
			Random random = new Random();		
			  
		    for (int j = 0; j < 10; j++) {
		        char c = accountNum[random.nextInt(accountNum.length)];
		        AccntNum.append(c);
		    }
		    
		    
		   
		    
		    int flagCount = random.nextInt(flag.length - 1) + 1;
		    int randTypes = random.nextInt(Type.length - 1) + 1;	    
			updateUserObject.put("accountPin", CreateProfileStepDefinition.accountPin);
			updateUserObject.put("userId", CreateProfileStepDefinition.resJson_createUser.get("userId"));
			updateUserObject.put("brandId", CreateProfileStepDefinition.getBrandId.get("brandId"));
			updateUserPD.put("accountPin", CreateProfileStepDefinition.accountPin);
			updateUserPD.put("linkToCRM", "https://dist-sourcetest.appspot.com/crm#account/"+CreateProfileStepDefinition.accountPin);
			updateUserPD.put("accountNumber",AccntNum.toString());
			updateUserPD.put("allowAchDebit", flag[flagCount]);
			updateUserPD.put("autoPlanUpgrade", flag[flagCount]);
			updateUserCD.put("creditLimit", 3);
			updateUserCD.put("autoRechargeAmount", 3);
			updateUserCD.put("autoRechargeThreshold", 2);
			updateUserCD.put("autoRechargeMonthlyLimit", 2);
			updateUserAF.put("salesPerson", "Abinaya");
			updateUserAF.put("serviceAgreement", "90 Days");
			updateUserAF.put("status", "active");
			updateUserAF.put("industry", "test");
			updateUserAF.put("accountTypes", Type[randTypes]);
			updateUserBC1.put("firstName", "TestingFirstName");
			updateUserBC1.put("lastName", "TestingLastName");
			updateUserBC1.put("phoneNumber", "9848933432");
			updateUserBC1.put("email", "test@test.com");
			updateUserOInfo.put("invoiceDelivery", "");
			updateUserOInfo.put("paymentMethod", "cc");
			updateUserOInfo.put("financeCharge", "");
			updateUserOInfo.put("status", "active");
			updateUserOInfo.put("serviceDate", "");
			updateUserCA.put("organizationName", "Testing");
			updateUserCA.put("userName", "Abinaya");
			updateUserCA.put("address1", "103E street");
			updateUserCA.put("address2", "sambier colony");
			updateUserCA.put("city", "OR");
			updateUserCA.put("state", "Portland");
			updateUserCA.put("country", "US");
			updateUserCA.put("zip", "948594");
			updateUserCC.put("firstName", "TestingFirstName");
			updateUserCC.put("lastName", "TestingLastName");
			updateUserCC.put("address1", "1032 Street");
			updateUserCC.put("address2", "sambier colony");
			updateUserCC.put("city", "portland");
			updateUserCC.put("state", "OR");
			updateUserCC.put("country", "US");
			updateUserCC.put("zip", "9384983");
			updateUserCC.put("billingEmail", "testing@mail.com");
			
			
			
			
			requestJsonUpdateUser 						=						gson.toJson(updateUserCC, LinkedHashMap.class);
			
			
			
		}
		
	}

	
	@Given("^Get the proper data to update the user details$")
	public void get_the_proper_data_to_update_the_user_details() throws Exception {
		
		
		
		
	}


	@When("^Call the update user service$")
	public void call_the_update_user_service() throws Exception {
		
	}

	@Then("^Validate the status code and response Body from the service$")
	public void validate_the_status_code_and_response_Body_from_the_service() throws Exception {
		
	}

	@Then("^Once successfully update the user information, validate through get services$")
	public void once_successfully_update_the_user_information_validate_through_get_services() throws Exception {
		
	}


}
