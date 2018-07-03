Feature: Create A Profile Creation Without Transaction
  I want to create a user without any transaction with relavent brand

  @CreateAUserWithoutTransaction
  Scenario: Testing a create user without transaction, with requestBody of POST EndPoint
    Given get the proper data to create a user
    When call the service to create a user with given data
    Then verify the response and status code
    When check the user information with relevant get services
    And verify the response of get services

  @CreateAUserWithoutTransactionWithCCDetails
  Scenario: Testing a create user without transaction but bind with cc detail, with requestBody of POST EndPoint
    Given get the proper data to create a user
    When call the create user service to create a user with cc
    Then verify the response and status code
    When check the user information with relevant get services
    And verify the response of get services

  @CreateAUserWithoutTransactionWithACHDetails
  Scenario: Testing a create user without transaction but bind with ach detail, with requestBody of POST EndPoint
    Given get the proper data to create a user
    And call the create user service to create a user with ach
    Then verify the response and status code
    When check the user information with relevant get services
    And verify the response of get services
