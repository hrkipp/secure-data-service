Feature: Acceptance test cases proving access to student or no access to student determines access to related student data.

Scenario Outline: Validate access to student related data based on accessor's context.
	
    Given I am logged in using "cgray" "cgray1234" to realm "IL"
    And I navigate to GET <URI OF CONTEXT BASED ENTITY>
    Then I should receive a return code of 200
    Given I am logged in using "stweed" "token-only-no-password" to realm "IL"
    And I navigate to GET <URI OF CONTEXT BASED ENTITY>
    Then I should receive a return code of 403
    Given I am logged in using "sbantu" "sbantu1234" to realm "IL"
    And I navigate to GET <URI OF CONTEXT BASED ENTITY>
    Then I should receive a return code of 200
	Given I am logged in using "akopel" "akopel1234" to realm "IL"
    And I navigate to GET <URI OF CONTEXT BASED ENTITY>
    Then I should receive a return code of 403
    Examples:
    | URI OF CONTEXT BASED ENTITY                                                       |
    | "/v1/studentAcademicRecords/56afc8d4-6c91-48f9-8a11-de527c1131b7"                 |
    | "/v1/attendances/4beb72d4-0f76-4071-92b4-61982dba7a7b"                            |
    | "/v1/courseTranscripts/36aeeabf-ee9b-46e6-8039-13320bf12346"                      |
    | "/v1/studentAssessments/e5e13e61-01aa-066b-efe0-710f7a011115"                     |
    | "/v1/disciplineActions/9e26de6c-225b-9f67-9201-8113ad50a03b"                      |
    | "/v1/studentDisciplineIncidentAssociations/20120613-8d5a-c796-76e3-d77d5d497e6c"  |
    | "/v1/disciplineIncidents/0e26de79-7efa-5e67-9201-5113ad50a03b"                    |
    | "/v1/reportCards/20120613-fd6c-4d3a-8a84-97bd8dd829b7"                            |
    | "/v1/studentParentAssociations/c5aa1969-492a-5150-8479-71bfc4d87984"              |
    | "/v1/parents/9b8f7237-ce8e-4dff-98cf-66535880987b"                                |
    | "/v1/studentSchoolAssociations/9cd50bcb-b39a-4d8a-b866-8766c79d6965"              |
    | "/v1/studentCohortAssociations/dd5e5b41-30fb-40e5-a968-afe7ae32fce3"              |
    | "/v1/studentProgramAssociations/51f8bb11-04ac-c03a-9bc8-4e91fcabcd73"             |
    | "/v1/studentCompetencies/3a2ea9f8-9acf-11e1-add5-68a86d83461b"                    |
	
