@cleanup
Feature: Deleting todos

  Scenario: Deleted todos should be removed entirely from the list
    Given Trudy has a todo list containing
      | Feed the cat |
      | Walk the dog |
      | Buy the milk |
    When she deletes "Walk the dog"
    Then her todo list should contain:
      | Feed the cat |
      | Buy the milk |
    And the number of items left should be 2