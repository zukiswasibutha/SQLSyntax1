Feature: Post an employee record from the API

  Scenario: Verify the Post api for employees
    Given I retrieve the employees records entity
    When I add an individual employee record
    Then I check whether I receive the record with status