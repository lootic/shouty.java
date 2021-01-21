Feature: Hear Shout

  Shouts have a range of approximately 1000m

  Scenario Outline: only hear in-range shouts
    Given Lucy is at 0, 0
    And Sean is at <Seans-location>
    When Sean shouts
    Then Lucy should hear <what-Lucy-hears>
    Examples: some simple examples
      | Seans-location | what-Lucy-hears |
      | 0, 900         | Sean            |
      | 800, 800       | nothing         |

  Scenario: Multiple shouters
    Given people are located at
      | name  | x    | y   |
      | Lucy  | 0    | 0   |
      | Sean  | 0    | 500 |
      | Oscar | 1100 | 0   |
    When Sean shouts
    And Oscar shouts
    Then Lucy should not hear Oscar
    But Lucy should hear Sean

  @SpecialTest
  Scenario: Shouter should not hear themself
    Given Sean is at 0, 0
    When Sean shouts
    Then Sean should not hear Sean

  @wip
  Scenario: Multiple shouts from one person
    Given Lucy is at 0, 0
    And Sean is at 0, 500
    When Sean shouts
    And Sean shouts
    Then Lucy should hear 2 shouts from Sean

  Scenario: Listener can not hear when moving far away after the shout
    Given Lucy is at 0, 0
    And Sean is at 0, 500
    When Lucy shouts
    And Sean is at 0, 1100
    Then Sean should not hear Lucy

  Scenario: Listener can hear when moving close after the shout
    Given Lucy is at 0, 0
    And Sean is at 0, 1100
    When Lucy shouts
    And Sean is at 0, 500
    Then Sean should hear Lucy

  Scenario: Listener can hear when shouter moving close after the shout
    Given Lucy is at 0, 0
    And Sean is at 0, 1100
    When Lucy shouts
    And Lucy is at 0, 500
    Then Sean should not hear Lucy

  Scenario: Listener can hear when shouter moving far away after the shout
    Given Lucy is at 0, 0
    And Sean is at 0, 500
    When Lucy shouts
    And Lucy is at 2000, 0
    Then Sean should hear Lucy