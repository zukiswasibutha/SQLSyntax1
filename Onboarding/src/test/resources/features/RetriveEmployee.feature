Feature: Get all employees records from the API

  Scenario: Verify the Get api for employees
    Given I retrieve the employees records entity
    When I request for an individual employee record
    When I request for a wrong individual employee record
    Then I receive the record with status