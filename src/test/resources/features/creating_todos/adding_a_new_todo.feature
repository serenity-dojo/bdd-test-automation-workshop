Feature: Adding a new todo

  Scenario: User should be assisted when adding todo items for the first time
    Given Trudy has not entered any todo items
    Then the application should suggest how to add them


  Scenario: Adding a single todo item
    Given Trudy has not entered any todo items
    When she adds "Buy some milk"
    Then her todo list should contain:
      | Buy some milk |