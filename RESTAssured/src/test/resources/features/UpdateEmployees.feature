Feature: Put data in an employees record from the API

  Scenario: Verify the Put api for employees
    Given I retrieve the employees records entity
    When I update an individual employee record
    Then I receive the record with what status
