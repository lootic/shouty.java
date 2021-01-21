Feature: Hear Shout

  Shouts have a range of approximately 1000m

  Scenario: In range shout is heard
    Given Lucy is at 0, 0
    And Sean is at 0, 900
    When Sean shouts
    Then Lucy should hear Sean

  Scenario: Out of range shout is not heard
    Given Lucy is at 0, 0
    And Sean is at 800, 800
    When Sean shouts
    Then Lucy should hear nothing

  Scenario: Multiple shouters
    Given Lucy is at 0, 0
    And Sean is at 0, 500
    And Oscar is at 1100, 0
    When Sean shouts
    And Oscar shouts
    Then Lucy should not hear Oscar
    But Lucy should hear Sean

  Rule: Shouters should not hear their own shouts
    Scenario: Shouters should not hear themselves
      When Lucy shouts
      Then Lucy should hear nothing

#  Scenario Outline: only hear in-range shouts
#    Given Lucy is at 0, 0
#    And Sean is at <Seans-location>
#    When Sean shouts
#    Then Lucy should hear <what-Lucy-hears>
#    Examples: some simple examples
#      | Seans-location | what-Lucy-hears |
#      | 0, 900         | Sean            |
#      | 800, 800       | nothing         |

