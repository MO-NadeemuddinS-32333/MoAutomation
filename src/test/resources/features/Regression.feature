
@tag
Feature: Regression tests

  Background: Application is launched
    Given the application is launched
    And the user is logged in

  Scenario: To check Portfolio bottom bar menu
    Given User clicks on home tab
    When User clicks on portfolio menu bottom bar
    Then Portfolio page should be displayed
