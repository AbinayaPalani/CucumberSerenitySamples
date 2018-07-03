Feature: Map the plans and Addons to the customer
	This feature is to link the plans to the customer

@MapPlans
Scenario: Testing a mapping the plans information, with the requestBody of POST EndPoint
Given get the proper data object to assign the plans for the customer
When call the createPlansAndAddons service
And check the statusCode and validate the response whether plan get assigned or not
