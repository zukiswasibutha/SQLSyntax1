Feature: Delete an employee record from the API

  Scenario: Verify the Delete api for employees
    Given I retrieve the employees records entity
    When I delete an individual employee record
    Then I delete to receive the record with status
