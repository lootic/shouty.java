Feature: Shout timeout
  Scenario: Default timeout is 5 minutes
    Given Lucy is at 0, 0
    When Lucy shouts
    Then shout at 0, 0 from Lucy times out in 5 minutes

  Scenario: Shout should be heard within timeout
    Given Lucy is at 0, 0
    And Sean is at 0, 0
    When Lucy shouts with timeout 20 minutes
    Then Sean should hear Lucy

  Scenario: Shout should not be heard after timeout
    Given Lucy is at 0, 0
    And Sean is at 0, 0
    When Lucy shouts with timeout 20 minutes
    And 30 minutes passes
    Then Sean should not hear Lucy
