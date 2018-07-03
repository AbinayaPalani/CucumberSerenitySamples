@OpenAssets
Feature: Get the freeable Asset from the Jbilling
	This feature is to get the open assets from get service

@GetOpenAssets
Scenario: Testing a get open assets, with request param of GET EndPoint
Given call the getAvailableAsset service with the type as EightXX
Then validate the statusCode and check the response status
And  get random asset 
	