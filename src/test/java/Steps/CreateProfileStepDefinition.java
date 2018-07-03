package Steps;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import static org.hamcrest.Matchers.is;
import com.google.gson.Gson;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import utlis.DataReader;

public class CreateProfileStepDefinition {
	
    Random random 												=					new Random();
	DataReader data												=					new DataReader();
	char[] chars												= 					"abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
    StringBuilder sb1											= 					new StringBuilder();
	char[] accountNum 											=					"0123456789".toCharArray();
    UUID uuid;
    StringBuilder sb2											=					new StringBuilder();
    public static String JsoninfoProfileWithoutPaymentData, accountPin, brandId, userId;
	Gson gson 															=					new Gson();
	public static JSONObject getBrandId, resJson_createUser, resJson_getUserInfo;
	public static Response response_profileInfo, response_getUserInfoById, response_getUserInfoByAccount, response_getUserInfoByAccount_v1;

	
	
	public class ProfileCreation {
		
		public Map<String, Object> createUserObject 					= 					new LinkedHashMap<String, Object>(); 
		public Map<String, Object> billingContactCreateUser 			= 					new LinkedHashMap<String, Object>();


        @Step
      	public void createUserData() throws Throwable{
        		
        		
        		
        		data.createUserSheet();
        		
        		System.out.println(data.brandIds.size());
        		
        		for(int index = 0; index < data.brandIds.size(); ++index){
        			
        			   for (int j = 0; j < 6; j++) {
        			        char c = chars[random.nextInt(chars.length)];
        			        sb1.append(c);
        			    }
        			    for (int j = 0; j < 10; j++) {
        			        char c = accountNum[random.nextInt(accountNum.length)];
        			        sb2.append(c);
        			    }
        			    uuid = UUID.randomUUID();
        				
        			
        			
        			System.out.println(data.Amounts.get(index) != null);
        			
        			
        		
        			//if(data.Amounts.get(index) == null){
        	
        				createUserObject.put("firstName", "Testing");
        				createUserObject.put("lastName", "Jbilling");
        				createUserObject.put("userEmail", sb1.toString()+"_test@mail.com");
        				createUserObject.put("brandId", data.brandIds.get(index));
        				createUserObject.put("uniquePin", uuid.toString());
        				createUserObject.put("serviceAgreement", "90 Days");
        				if(RestAssured.baseURI.equalsIgnoreCase("http://staging.jbilling.a-cti.com:8081")){
        					createUserObject.put("linkToCRM", "https://dist-sourcetest.appspot.com/crm#account/"+uuid.toString());
        				}
        				if(RestAssured.baseURI.equalsIgnoreCase("http://access.jbilling.a-cti.com:8081")){
        					createUserObject.put("linkToCRM", "https://my.distributedsource.com/crm#account/"+uuid.toString());
        				}
        				if(RestAssured.baseURI.equalsIgnoreCase("http://access.jbilling.a-cti.com")){
        					createUserObject.put("linkToCRM", "https://my.distributedsource.com/crm#account/"+uuid.toString());
        				}
        				createUserObject.put("accountNumber", sb2.toString());
        				createUserObject.put("setUpFee", 0);
        				createUserObject.put("address", "Addr1");
        				createUserObject.put("address2", "Addr2");
        				createUserObject.put("city", "OR");
        				createUserObject.put("state", "Portland");
        				createUserObject.put("country", "US");
        				createUserObject.put("zip", "98343");
        				createUserObject.put("organizationName", "TEST"+sb1.toString());
        				createUserObject.put("phoneNumber", "0123456789");
        				createUserObject.put("paymentType", data.PaymentTypeCreateUser.get(index));
        					if( data.PaymentTypeCreateUser.get(index)!=null){
        						if(data.PaymentTypeCreateUser.get(index).equalsIgnoreCase("cc") ){
        							createUserObject.put("nameOnCard", "Abinaya");
        							createUserObject.put("cardNumber", "4111111111111111");
        							createUserObject.put("expDate", "09/2020");
        							createUserObject.put("cardType", "Visa");
        							createUserObject.put("transactionID", "gvhvh3456");
        						}
        						if(data.PaymentTypeCreateUser.get(index).equalsIgnoreCase("ach")){
        							createUserObject.put("routingNumber", "091000019");
        							createUserObject.put("bankAccountNumber", "98387847312");
        							createUserObject.put("customerName", "Abinaya");
        						}
        					}
        				
        				createUserObject.put("agentPin", "8943");
        				createUserObject.put("agentName", "Test_Ab");
        				billingContactCreateUser.put("First Name", "Abi");
        				billingContactCreateUser.put("Last Name", "naya");
        				billingContactCreateUser.put("Phone Number", "87673467376");
        				billingContactCreateUser.put("Email", "testing@billing.com");
        				createUserObject.put("billingContact1", billingContactCreateUser);
        				}
        		
        		
        		
	        		if(!createUserObject.isEmpty()){
	        			JsoninfoProfileWithoutPaymentData				=							gson.toJson(createUserObject, LinkedHashMap.class);
	        			
	        			System.out.println("!------------------------------*****-------------------------------!");
	        			
	        			System.out.println("User Object for creation : ");
	        			
	        			System.out.println(JsoninfoProfileWithoutPaymentData);
	        		}
        		}
        
        
        	@Step
        	public void profileCreationWithCC() throws Throwable{
        		
        		
        		if(!JsoninfoProfileWithoutPaymentData.isEmpty()){ 
       			 getBrandId 										= 							new JSONObject(JsoninfoProfileWithoutPaymentData);	
       			 
       			 System.out.println("!-------------------------------------------------------------------!");
       			 
       			 System.out.println("Brand Id : "+getBrandId.toString());
       	 	
       			 	if(getBrandId.get("paymentType").equals("cc")){
       			 		
       			 		response_profileInfo 							   = 							SerenityRest.given().when()
       	 																							.contentType(ContentType.JSON)
       	 																							.accept(ContentType.JSON)
       	 																							.content(JsoninfoProfileWithoutPaymentData)
       	 																							.post("/createUser");
       			 	}
        		}
        	}
        
	        @Step
	        public void profileCreationWithACH() throws Throwable{
	        	
	        	
	        	if(!JsoninfoProfileWithoutPaymentData.isEmpty()){
	   			 getBrandId 										= 							new JSONObject(JsoninfoProfileWithoutPaymentData);	

	   		 	if(getBrandId.get("paymentType").equals("ach")){

	   		 	getBrandId 										= 							new JSONObject(JsoninfoProfileWithoutPaymentData);	
	   			response_profileInfo 							= 							SerenityRest.given().when()
	   																							.contentType(ContentType.JSON)
	   																							.accept(ContentType.JSON)
	   																							.content(JsoninfoProfileWithoutPaymentData)
	   																							.post("/createUser");
	   		 		}
	        	}
	        	
	        }
	        
	        @Step
	        public void profileCreation() throws Exception{
	        	
	        	
	        	if(!JsoninfoProfileWithoutPaymentData.isEmpty()){ 
	   			 getBrandId 										= 							new JSONObject(JsoninfoProfileWithoutPaymentData);	
	   			 
	   			 System.out.println("!-------------------------------------------------------------------!");
	   			 
	   			 System.out.println("Brand Id : "+getBrandId.toString());
	   	 	
	   			 	if(!getBrandId.get("paymentType").equals("cc") && !getBrandId.get("paymentType").equals("ach")){
	   	 		response_profileInfo 							   = 							SerenityRest.given().when()
	   	 																							.contentType(ContentType.JSON)
	   	 																							.accept(ContentType.JSON)
	   	 																							.content(JsoninfoProfileWithoutPaymentData)
	   	 																							.post("/createUser");
	   	 		
	   	 	 		}
	        	}
	        }
	        @Step
	        public void checkStatusCodeAs_200AndValidateTheResponse(){
	        	
	        	if(response_profileInfo != null){
	    			
	    			resJson_createUser 							= 							new JSONObject(response_profileInfo.asString());	
	    			
	    			//System.out.println(getBrandId.get("brandId"));	
	    			response_profileInfo.then().body("status", is(resJson_createUser.get("status")));
	    			
	    			Assert.assertThat("Failed to create a user, check the problem", resJson_createUser.get("status") , is("success"));
	    		 }
	        }
	        
	        
	        @Step
	        public void checkUserGetServices(){
	        	System.out.println(resJson_createUser.get("userId"));
	   		 
	   		 System.out.println(getBrandId.get("brandId"));
	   		 
	   		 response_getUserInfoById 						=							SerenityRest.given().contentType(ContentType.JSON)
	   																						.queryParam("userId", resJson_createUser.get("userId"))
	   																						.queryParam("brandId",getBrandId.get("brandId"))
	   																						.accept(ContentType.JSON)
	   																						.get("/getUserById");

	   		JSONObject resJson_getUserInfoById				=							new JSONObject(response_getUserInfoById.asString());
	   		
	   		Assert.assertEquals("Not getting the user information", resJson_getUserInfoById.get("status"), "success");
	   		
	   		JSONObject userObjGetAccountPin_UserId	 =    						resJson_getUserInfoById.getJSONObject("user");
	   				
	   		JSONArray primaryContactGetAccountPin_UserId	=					userObjGetAccountPin_UserId.getJSONArray("primaryContact");
	   		
	   		System.out.println(primaryContactGetAccountPin_UserId.length());
	   		
	   		
	   		for(int metaFieldIndex = 1; metaFieldIndex < primaryContactGetAccountPin_UserId.length(); metaFieldIndex++){
	   		
	   		JSONObject primaryContacts 						 = 						primaryContactGetAccountPin_UserId.getJSONObject(metaFieldIndex);
	   		
	   			
	   			if(primaryContacts.get("fieldName").equals("Account PIN")){
	   			
	   				accountPin 											=						String.valueOf(primaryContacts.get("stringValue"));
	   				System.out.println("From get service the AccountPin : "+accountPin);
	   				break;
	   			}
	   		} 
	   		
	   		
	   		response_getUserInfoByAccount					 =							SerenityRest.given().contentType(ContentType.JSON)
	   																						.queryParam("brandId", getBrandId.get("brandId"))
	   																						.queryParam("accountPin", accountPin.toString())
	   																						.accept(ContentType.JSON)
	   																						.get("/getUserByAccountPin");
	   		
	   		System.out.println("Test purpose - Brand Id : "+getBrandId.get("brandId"));
		 
	   		System.out.println("Test purpose - Account Pin : "+accountPin.toString());
		 
	   		response_getUserInfoByAccount_v1					=						SerenityRest.given().contentType(ContentType.JSON)
																							.pathParam("accountPin", accountPin.toString())
																							.pathParam("brandId", getBrandId.get("brandId"))
																							.accept(ContentType.JSON)
																							.get("/v1/getUserByAccountPin/accountPin/{accountPin}/brandId/{brandId}");	 		 
	 
	        }
	        
	        
	        @Step
	        public void assertTheResponseStatusAsTrue(){
	        	
	        	resJson_getUserInfo 							=					  new JSONObject(response_getUserInfoByAccount_v1.asString());
	        	
	        	Assert.assertEquals( true ,Boolean.valueOf(resJson_getUserInfo.getString("success")));
	        	
	        }
	        
	        
	        public void paramData(){
	        	
	        	accountPin									=							accountPin.toString();
	        	brandId										=							getBrandId.get("brandId").toString();
	        	userId										=							resJson_createUser.get("userId").toString();
	        }
	        
        	
        }

	
	
	
	
	
	
	
	@Steps
	ProfileCreation createData;
	
	@Given("^get the proper data to create a user$")
	public void get_the_proper_data_to_create_a_user() throws Throwable {
	    
		createData.createUserData();
		
		
	}

	@When("^call the service to create a user with given data$")
	public void call_the_service_to_create_a_user_with_given_data() throws Exception {
		
		createData.profileCreation();
	    
	}

	@Then("^verify the response and status code$")
	public void verify_the_response_and_status_code() throws Exception {
		
		createData.checkStatusCodeAs_200AndValidateTheResponse();
		
	}

	@When("^check the user information with relevant get services$")
	public void check_the_user_information_with_relevant_get_services() throws Throwable {
		
		createData.checkUserGetServices();
		
	}

	@When("^verify the response of get services$")
	public void verify_the_response_of_get_services() throws Exception {
		
		createData.assertTheResponseStatusAsTrue();
		
	}


	@When("^call the create user service to create a user with cc$")
	public void call_the_create_user_service_to_create_a_user_with_cc() throws Throwable {
		
		createData.profileCreationWithCC();
		
		
	}

	@Given("^call the create user service to create a user with ach$")
	public void call_the_create_user_service_to_create_a_user_with_ach() throws Throwable {
		
		createData.profileCreationWithACH();
	}


	

}
