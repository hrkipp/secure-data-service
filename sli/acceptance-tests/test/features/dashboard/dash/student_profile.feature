Feature:  Student Profile View

As a teacher in a school district, I want to click on a student and be directed to their profile page.

Background:
Given I have an open web browser

Scenario: View a student's profile
Given the server is in "test" mode
When I navigate to the Dashboard home page
Given I am authenticated to SLI as "lkim" password "lkim"
When I click on the Dashboard page
And I wait for "1" seconds
When I select ed org "Daybreak School District 4529"
When I select school "East Daybreak Junior High"
When I select course "8th Grade English"
When I select section "M. Jones - 8th Grade English - Sec 6"
And I wait for "2" seconds
And I click on student "Jolene Ashley"
And I view its student profile
And their name shown in profile is "Jolene Colt Ashley (Ms Jolene Juggernaut Ashley IV)"
And their id shown in proflie is "943715230"
And their grade is "Eighth grade"
And the teacher is "Dr lkim"
And the class is "M. Jones - 8th Grade English - Sec 6"
And the lozenges count is "1"
And the lozenges include "ELL"

Scenario: View a student's profile without lozenges
Given the server is in "test" mode
When I navigate to the Dashboard home page
Given I am authenticated to SLI as "lkim" password "lkim"
When I click on the Dashboard page
And I wait for "1" seconds
When I select ed org "Daybreak School District 4529"
When I select school "East Daybreak Junior High"
When I select course "8th Grade English"
When I select section "M. Jones - 8th Grade English - Sec 6"
And I wait for "2" seconds
And I click on student "Nomlanga Mccormick"
And I view its student profile
And their name shown in profile is "Nomlanga Deacon Mccormick"
And their id shown in proflie is "423037202"
And their grade is "Fourth grade"
And the teacher is "Dr lkim"
And the class is "M. Jones - 8th Grade English - Sec 6"
And the lozenges count is "0"

Scenario: View a student's profile in live mode
Given the server is in "live" mode
When I navigate to the Dashboard home page
When I select "Sunset School District 4526" and click go
When I login as "linda.kim" "linda.kim1234"
And I wait for "2" seconds
When I click on the Dashboard page
When I select ed org "Daybreak School District 4529"
When I select school "East Daybreak Junior High"
When I select course "8th Grade English"
When I select section "8th Grade English - Sec 6"
And the following students have "ELL" lozenges: "Matt Sollars;Odysseus Merrill;Hoyt Hicks;Brielle Klein;Patricia Harper"
And the following students have "FRE" lozenges: "Odysseus Merrill;Hoyt Hicks;Delilah Sims;Ursa Oconnor"
And I wait for "20" seconds
And I click on student "Kimberley Pennington"
And I view its student profile
And their name shown in profile is "Kimberley Yuli Pennington Jr"
And their id shown in proflie is "437680177"
And their grade is "Eighth grade"
And the teacher is "Mrs Linda Kim"
And the class is "8th Grade English - Sec 6"
And the lozenges count is "2"
And the lozenges include "FRE"
And there are "6" Tabs
And in Tab ID "6", there is "1" Panels
And in Tab ID "2", there is "1" Panels
And in Tab ID "3", there is "0" Panels
And in Tab ID "4", there is "0" Panels 
And in Tab ID "5", there is "0" Panels
And Tab has a title named "Overview"
And Tab has a title named "Attendance and Discipline"
And Tab has a title named "Assessments"
And Tab has a title named "Grades and Credits"
And Tab has a title named "Advanced Academics"
And Tab has a title named "ELL"
# Temp disable specific tab index tests
#And Tab "1" is titled "Overview"
#And Tab "2" is titled "Attendance and Discipline"
#And Tab "3" is titled "Assessments"
#And Tab "4" is titled "Grades and Credits"
#And Tab "5" is titled "Advanced Academics"

@wip
Scenario: View a student with other name
Given I am authenticated to SLI as "lkim" password "lkim"
When I click on the Dashboard page
And I wait for "1" seconds

	