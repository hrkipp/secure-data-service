@sandbox
@RALLY_US2212
Feature: User authenticates against the Simple Sandbox IDP
 
 Background: Realm selector is set up to reflect one sandbox tenancies and database has redirect links
Given I have an open web browser

Scenario: Use Mock IDP to log in as IT Administrator
  Given I navigate to databrowser home page
  Then I will be redirected to realm selector web page
  When I select the "Sandbox Environment" realm
  Then I was redirected to the "Simple" IDP Login page
  When I enter the credentials "testdeveloper" "testdeveloper1234" for the Simple IDP
    And I want to imitate the user "cgray" who is a "IT Administrator"
    And I click Login
    And I wait for 5 second
  Then I should be redirected to the databrowser web page
  Then I should see the name "Charles Gray" on the page
  # Verify role not blank if custom role not specified
When I navigate to databrowsers "/entities/system/session/debug" page
Then I should see my roles as "IT Administrator"
And I should see my rights include "READ_PUBLIC"
And I should see my rights include "READ_GENERAL"
And I should see my rights include "AGGREGATE_READ"

Scenario: Use Mock IDP to log in as IL Aggregate Viewer
  Given I navigate to databrowser home page
  Then I will be redirected to realm selector web page
  When I select the "Sandbox Environment" realm
  Then I was redirected to the "Simple" IDP Login page
  When I enter the credentials "testdeveloper" "testdeveloper1234" for the Simple IDP
    And I want to imitate the user "cgray" who is a "Aggregate Viewer"
    And I click Login
    And I wait for 5 second
  Then I get message that I am not authorized to use the Databrowser
  Then I should see the name "Charles Gray" on the page

Scenario: Deny logging in to non-sandbox NY Realm
  Given I navigate to databrowser home page
  Then I will be redirected to realm selector web page
  When I select the "Sandbox Environment" realm
  Then I was redirected to the "Simple" IDP Login page
  When I enter the credentials "testdeveloper" "testdeveloper1234" for the Simple IDP
    And I want to imitate the user "eengland" who is a "Educator"
    And I click Login
  Then I am denied from accessing the databrowser
