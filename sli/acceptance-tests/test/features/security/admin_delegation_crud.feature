@smoke @RALLY_US134 @RALLY_US5156
Feature: Admin delegation CRUD

  Scenario: District administrator creating admin delegation
    Given I am logged in using "sunsetadmin" "sunsetadmin1234" to realm "SLI"
    When I POST a new admin delegation
    Then I should receive a return code of 201

  Scenario: State administrator without explicit delegation still allowed to update to application authorization
    And I am logged in using "iladmin" "iladmin1234" to realm "SLI"
    When I do not have access to app authorizations for district "IL-SUNSET"
    Then I should update app authorizations for district "IL-SUNSET"
    And I should receive a return code of 204

 Scenario: State administrator without explicit delegation still allowed to update to application authorization without a security event
    Given the sli securityEvent collection is empty
    And I am logged in using "iladmin" "iladmin1234" to realm "SLI"
    When I do not have access to app authorizations for district "IL-SUNSET"
    Then I should update app authorizations for district "IL-SUNSET"
    And I should receive a return code of 204
     And I should see a count of "0" in the security event collection

  Scenario: District administrator updating admin delegation
    Given I am logged in using "sunsetadmin" "sunsetadmin1234" to realm "SLI"
    And I have a valid admin delegation entity
    And I change "appApprovalEnabled" to true
    When I PUT to admin delegation
    Then I should receive a return code of 204

  Scenario: State administrator with access updating application authorizations
    Given the sli securityEvent collection is empty
    And I am logged in using "iladmin" "iladmin1234" to realm "SLI"
    When I have access to app authorizations for district "IL-SUNSET"
    Then I should update app authorizations for district "IL-SUNSET"
    And I should receive a return code of 204

  Scenario: State administrator with access updating one application authorization
    And I am logged in using "iladmin" "iladmin1234" to realm "SLI"
    When I have access to app authorizations for district "IL-SUNSET"
    Then I should update one app authorization for district "IL-SUNSET"
    And I should receive a return code of 204

  Scenario: State administrator with access updating application authorizations again
    Given I am logged in using "iladmin" "iladmin1234" to realm "SLI"
    When I have access to app authorizations for district "IL-SUNSET"
    Then I should also update app authorizations for district "IL-SUNSET"
    And I should receive a return code of 204

  Scenario: State administrator seeing delegations they have access to
    Given I am logged in using "iladmin" "iladmin1234" to realm "SLI"
    When I have access to app authorizations for district "IL-SUNSET"
    Then I should get my delegations
    And I should see that "appApprovalEnabled" is "true" for district "IL-SUNSET's ID"

#US5464
  Scenario: State administrator revoking access to edOrg data
    And I am logged in using "iladmin" "iladmin1234" to realm "SLI"
    Then I should revoke all app authorizations for district "IL"
    And I should receive a return code of 204
    And a security event "EdOrg data access has been revoked!" should be created for these targetEdOrgs
      | targetEdOrg                |
      | IL                         |


#US5464
  Scenario: State administrator granting access to edOrg data
    And I am logged in using "iladmin" "iladmin1234" to realm "SLI"
    Then I should grant all app authorizations for district "IL"
    And I should receive a return code of 204
    And a security event "Application granted access to EdOrg data!" should be created for these targetEdOrgs
      | targetEdOrg                |
      | IL                         |


#US5464
  Scenario: LEA administrator revoking access to edOrg data
    And I am logged in using "sunsetadmin" "sunsetadmin1234" to realm "SLI"
    Then I should revoke all app authorizations for district "IL-SUNSET"
    And I should receive a return code of 204
    And a security event "EdOrg data access has been revoked!" should be created for these targetEdOrgs ONLY
      | targetEdOrg                |
      | IL-SUNSET                  |


#US5464
  Scenario: LEA administrator granting access to edOrg data
    And I am logged in using "sunsetadmin" "sunsetadmin1234" to realm "SLI"
    Then I should grant all app authorizations for district "IL-SUNSET"
    And I should receive a return code of 204
    And a security event "Application granted access to EdOrg data!" should be created for these targetEdOrgs ONLY
      | targetEdOrg                |
      | IL-SUNSET                  |


#US5464
  Scenario: District administrator can grant access to edOrg data only if LEA adminstrator has delegated  AppApproval
    #LEA administrator disables delegation
    Given I am logged in using "sunsetadmin" "sunsetadmin1234" to realm "SLI"
    And the sli securityEvent collection is empty
    And I have a valid admin delegation entity
    And I change "appApprovalEnabled" to false
    When I PUT to admin delegation
    Then I should receive a return code of 204
    And a security event "LEA's delegation is disabled!" should be created for these targetEdOrgs ONLY
      | targetEdOrg                |
      | fakeab32-b493-999b-a6f3-sliedorg1234|
      | fakeab32-b493-999b-a6f3-sliedorg1234|
      | fakeab32-b493-999b-a6f3-sliedorg1234|
    

    # SEA administrator tries to do AppAproval for non delegated LEA and succeeds
    When I am logged in using "iladmin" "iladmin1234" to realm "SLI"
    Then I should grant all app authorizations for district "IL-SUNSET"
    And I should receive a return code of 204

    #LEA administrator enables delegation
    Given I am logged in using "sunsetadmin" "sunsetadmin1234" to realm "SLI"
    And I have a valid admin delegation entity
    And I change "appApprovalEnabled" to true
    When I PUT to admin delegation
    Then I should receive a return code of 204
    And a security event "LEA's delegation is enabled!" should be created for these targetEdOrgs ONLY
      | targetEdOrg                |
      | fakeab32-b493-999b-a6f3-sliedorg1234|
      | fakeab32-b493-999b-a6f3-sliedorg1234|
      | fakeab32-b493-999b-a6f3-sliedorg1234|
    
    

    #SEA administrator tries to do AppAproval for non delegated LEA and succeeds, causes SecurityEvent to be logged
    And I am logged in using "iladmin" "iladmin1234" to realm "SLI"
    Then I should revoke all app authorizations for district "IL-SUNSET"
    And I should receive a return code of 204
    And a security event "EdOrg data access has been revoked!" should be created for these targetEdOrgs ONLY
      | targetEdOrg                |
      | IL-SUNSET                  |

    #SEA administrator tries to do AppAproval for non delegated LEA and succeeds, causes SecurityEvent to be logged
    Then I should grant all app authorizations for district "IL-SUNSET"
    And I should receive a return code of 204
    And a security event "Application granted access to EdOrg data!" should be created for these targetEdOrgs ONLY
      | targetEdOrg                |
      | IL-SUNSET                  |
