Feature: Service Registration
  As a user
  I want to register a new service
  So that it can be stored and retrieved later

  Scenario: Registering a new service
    Given I have service registration data with description
    When I register the service
    Then the service with ID should be created successfully
