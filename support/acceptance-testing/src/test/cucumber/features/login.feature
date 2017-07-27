@login
Feature: Login

  Scenario: Select the login dialog
    Given I am on the home page
    When I click on login
    Then I should see the login dialog

