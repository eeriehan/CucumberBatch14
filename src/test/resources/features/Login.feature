Feature: Login functionality

  @sprint3 @regression @tc1101
  Scenario: Valid admin login
    Given user is navigated to HRMS application
    When user enters valid username and valid password
    And user clicks on login button
    Then user is successfully logged in

  @regression @tc1102
  Scenario: Valid ess login
    Given user is navigated to HRMS application
    When user enters ess username and ess password
    And user clicks on login button
    Then user is successfully logged in

  @tc1103
  Scenario: Invalid admin login
    Given user is navigated to HRMS application
    When user enters invalid username and password
    And user clicks on login button
    Then error message displayed