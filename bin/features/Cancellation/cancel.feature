#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Cancellation process

 I need to check the process of cancellation 

  @tag1
  Scenario: Testing a cancellation functionality, with the POST request Body of Endpoint
    Given Get the proper object to process the cancellation
    When Call the cancellation service
    Then verify the status Code 
    And validate the response of cancellation
    Then get the user details then check the user status, according to cancellation STATUS shoule be `Cancel`
    Then validate the order object, check whether order changed to finsihed
    But If status is not changed to cancel, failed to process the cancel
    But If order is not changed to finished status, need to check the monthly order of the customer

  
