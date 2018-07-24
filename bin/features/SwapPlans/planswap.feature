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

Feature: Update the plans swap to the customer
  I want to test the plans swap functionality

  @planSwap
  Scenario: Testing a plan swap, with the requestBody of PUT endpoint
    Given Get the plan swap object
    When call the updateSelectedPlansAndAddons service
    And Check with relevant get services to verify the current plans and existing plan details
    