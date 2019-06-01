Feature: Updating todos

  Scenario: Should be able to update the name of a task
    Given Trudy has a todo list containing
      | Feed the cat |
      | Walk the dog |
    When she updates "Walk the dog" to "Walk Fido"
    Then her todo list should contain:
      | Feed the cat |
      | Walk Fido |
