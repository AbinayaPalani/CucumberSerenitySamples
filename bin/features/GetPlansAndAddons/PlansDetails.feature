Feature: Get a plans and addons information
	This feature to get the plans and addons record from the relavent brand

@getPlansDetail
Scenario: Testing a getPlansAndAddons information, with the request param of GET EndPoint
Given call the getStaticPlansAndAddons service with the relavent brandId
	When validate the statusCode and response from it
Then  get random plans 
	