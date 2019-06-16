# BDD in Action: Advanced BDD Test Automation workshop

In this workshop we will be writing executable specifications using Cucumber, WebDriver and Serenity BDD. You wi

## Prerequisite overview

For this workshop, you will need the tools mentioned below, as well as a reasonable understanding of Java programming. 
No knowledge of Cucumber is required.

* [JDK 1.8](https://www.oracle.com/technetwork/java/javase/downloads) or higher
* [Maven](http://maven.apache.org) 3.3 or higher
* [Git](https://git-scm.com) and a [Github](https://github.com) account
* [IntelliJ Community Edition](https://www.jetbrains.com/idea/) or [Eclipse](https://www.eclipse.org)
* [Google Chrome](https://www.google.com/chrome/)
* [NodeJS](http://nodejs.org) (if you want to run the application locally)

## Prerequisite details

### Maven
The sample project will use Maven 3.6.0 (https://maven.apache.org) (any version higher than 3.3 should work.

### Gradle
The project will also run with Gradle. You can use the Gradle Wrapper to make sure you are using the right version of Gradle (see below).

### JDK
You will need a version of the Java JDK (8 or higher). The demo will be done using OpenJDK 12.

### IntelliJ or Eclipse

You will also need a Java IDE. You can use IntelliJ or Eclipse. We recommend IntelliJ - you can download the Community edition from here: https://www.jetbrains.com/idea/download. 

For IntelliJ, make sure the [Cucumber for Java](https://plugins.jetbrains.com/plugin/7212-cucumber-for-java) plugin is installed and enabled. For Eclipse, you will need the [Cucumber for Eclipse Plugin](https://cucumber.github.io/cucumber-eclipse/)

### Chrome
We will be running tests in Chrome, so make sure you have the latest version of Chrome installed (Version 74.0.3729.169 or higher)

## Getting started
Clone or download the starter project from https://github.com/serenity-bdd/serenity-cucumber4-starter

```
git clone https://github.com/serenity-dojo/bdd-test-automation-workshop
```

Run the initial tests to make sure everything works:
```
mvn clean verify
```
Or if you prefer to use Gradle:
```
./gradlew clean test
```


Now load the project into your IDE.

### Running the TodoMVC application locally

We will be writing tests for the [TodoMVC](http://todomvc.com) application. 
You can run your tests either against the publicly available site, or by running the application locally. 
If you want to run the application locally, install [NodeJS](http://nodejs.org) on your machine and then install the [http-server](https://www.npmjs.com/package/http-server) package, e.g.
```
$ npm install http-server -g
```
Then, to run the demo application, run the following from the project root directory:
```
$ http-server todomvc
```

### Running the tests

You can run the test suite by running the [CucumberTestSuite](src/test/java/todo/CucumberTestSuite.java) class in your IDE, or by running `mvn verify` from the command line.

## Lesson 1

The first Cucumber scenario we will work on can be found in the [site_title_and_credits.feature](src/test/resources/features/layout/site_title_and_credits.feature) feature file.
The scenario is a simple one:
```gherkin
  Scenario: The application credits should appear in the footer
    When Todd opens the Todo Application
    Then he should see the credits in the footer
```

The first step has already been implemented for you. 
If you open the [StepDefinitions](src/test/java/todo/stepdefinitions/StepDefinitions.java) class, you can see how it works.

The first thing you will see in this class is a `@Before` method, which gets executed at the start of each scenario:
```java
    @Before
    public void prepareTests() {
        setTheStage(new OnlineCast());
    }
```
In Screenplay tests, we describe _actors_ who perform _tasks_. This method is responsible for preparing the actors who will take part in the tests. We do this by assigning a _cast_ to a _stage_.
A cast is a bit like a factory for actors. An `OnlineCast` will provide actors who are equiped with a WebDriver instance.

The first step definition method is shown below:
```java
    @Given("(.*) (?:opens|has opened) the Todo Application")
    public void opens_the_Todo_Application(String actorName) {
        theActorCalled(actorName).attemptsTo(
                Open.browserOn().the(TodoHomePage.class)
        );
    }
```

This method does two things. First of all, it invokes an actor with a name specified in the scenario. 
Screenplay scenarios can involve one or more actors, each identified by a different name.

Secondly, it opens the browser on the application home page. 
The home page (for the React implementation of the application) is represented by the [TodoReactHomePage](src/test/java/todo/pageobjects/TodoReactHomePage.java) class, which is shown below:

```java
@DefaultUrl("http://todomvc.com/examples/react/#")
public class TodoReact extends PageObject {

    public static Target FOOTER = Target.the(site_layout)
                                        .located(By.cssSelector(site_layout));
}
```

This Page Object uses the `@DefaultUrl` annotation to determine what page it should open when the `Open.browserOn()` task is performed. 
The `FOOTER` field is a locator that knows where to find the page footer. We will be using this field shortly.

Now that you have an idea of how the code works, it's time to implement the second step definition method.
Go back to the [StepDefinitions](src/test/java/todo/stepdefinitions/StepDefinitions.java) and look at the second step definiton method:


```java
    @Then("s?he should see the credits in the footer")
    public void he_should_see_in_the_footer() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }
```

To implement this step, we will use the `FOOTER` locator we saw in the page object earlier to check that the page footer contains the words "Part of TodoMVC".
To do this, we replace the existing code of this method with the following:

```java
    @Then(site_layout)
    public void he_should_see_in_the_footer() {
        OnStage.withCurrentActor(
                Ensure.that(TodoReact.FOOTER).text().contains("Part of TodoMVC")
        );
    }
```

Let's break this down. The `OnStage.withCurrentActor()` method finds the currently active actor (the last one to perform an action in the test). 
This method takes a list of business tasks and checks that the actor is to perform. 
Each of these tasks or checks is represented by an object that implements the _Performable_ interface, which model user interactions with the application in a domain-readable manner.

In this case, we use the `Ensure` class to check that the footer contains the text "Part of TodoMVC". 
The `Ensure.that()` method accepts, among other things, a locator that identifies an element on the page. The locator can either use the standard Selenium `By`is  class, 
or the Serenity `Target` class. This second option lets you combine a `By` locator (or a CSS or XPath expression) with a readable label that will appear in the reports when this element is used.
An example is shown here:

```java
    public static Target FOOTER = Target.the("footer section")
                                        .located(By.cssSelector("footer.info"));
```

The `Ensure.that()` method returns a fluent builder that shows you the available assertions for the type of parameter you provide. 
In this case, the `text()` method opens up access to a range of assertions about the text value of this element. 

## Lesson 2 - check the site title

Next, we will implement the other pending scenario in the [site_title_and_credits.feature](src/test/resources/features/site_layout/site_title_and_credits.feature) feature file:

```gherkin
  Scenario: The page title should be shown
    When Todd opens the Todo Application
    Then the page title should include "TodoMVC"
```

The last step has no step definition. Use IntelliJ to generate a step definition method in the StepDefinitions class.
The generated code should look something like this:

```java
    @Then("the page title should include {string}")
    public void thePageTitleShouldInclude(String expectedTitle) {
        
    }
```

We will need a Page Object to retreive the browser title from, 
so we will declare a field of type `TodoReactHomePage` and add it to the `StepDefinitions` class:
```java
    private TodoReactHomePage todoReactHomePage;
```

Now, in the new step definition method, use the `withCurrentActor()` and `Ensure.that()` methods to check that the page title contains the expected value:

```java
        withCurrentActor(
                Ensure.that(todoReactHomePage).title().contains(expectedTitle)
        );
```

## Lesson 3 - checking the prompt message

The next scenario we will work on can be found in the [adding_new_todos.feature](src/test/resources/features/creating_todos/adding_new_todos.feature) feature file.

```gherkin
  Scenario: User should be assisted when adding todo items for the first time
    Given Trudy has not entered any todo items
    Then the application should suggest how to add them
```

In this scenario, we check that the "What needs to be done?" message in the input box is correctly displayed:

![Serenity Test Summary](docs/serenity-dashboard.png "Logo Title Text 1")

First, generate the step definition methods from IntelliJ. 
The first step just opens the home page; we can repeat the code from the earlier step definition, or simply call the method directly like this:

```gherkin
    @Given("{actor} has not entered any todo items")
    public void has_not_entered_any_todo_items(Actor actor) {
        opens_the_Todo_Application(actor);
    }
```

The second step checks the prompt message. 
Before going any further, see if you can inspect the TodoMVC application page to see what locator you could use to retrieve the placeholder text.
It is in fact stored in the `placeholder` attribute of the input field.
Inspect the page again and find a locator you can use to find the input field. 
Now, add a new `Target` field to the `TodoReactHomePage` class to allow Serenity to locate this input field.
Here is one possibility:

```java
    public static final Target NEW_TODO = Target.the("New todo field")
                                                .locatedBy(".new-todo");

```

Next, use the `Ensure.that()` and `attribute()` methods to check the value of the `placeholder` attribute:

```java
    @Then("the application should suggest how to add them")
    public void the_application_should_suggest_how_to_add_them() {
        withCurrentActor(
                Ensure.that(TodoReactHomePage.NEW_TODO)
                      .attribute("placeholder")
                      .isEqualTo("What needs to be done?")
        );
    }
```

## Lesson 4 - adding a single item

The next scenario to implement involves adding a single item to the list, and checking the contents of the list.

```gherkin
  Scenario: Adding a single todo item
    Given Trudy has not entered any todo items
    When she adds "Walk the dog"
    Then her todo list should contain:
      | Walk the dog |
```

The first step has already been implemented in the previous lesson. 
For the second step, we need to enter a value into the `NEW_TODO` element we identified earlier. 

We will do this by modelling the user interaction as a Screenplay Task class, called `AddATodoItem`. 
The step definition code will look like this:

```java
    @When("she adds {string}")
    public void she_adds(String todoItem) {
        withCurrentActor(
                AddATodoItem.called(todoItem)
        );
    }
```

Create a new `AddATodoItem` class with a single static method, `called()`, like the following:

```java
public class AddATodoItem {
    
    public static Performable called(String thingToDo) {
        return Task.where("{0} adds a todo item called: " + thingToDo,
                Type.theValue(thingToDo)
                        .into(TodoReactHomePage.NEW_TODO)
                        .thenHit(Keys.ENTER)
                );
    }
    
}
```

This class uses the `Task,where()` method, which is a simplified way to create a Screenplay task on-the-fly.
The `Task.where()` method takes two parameters: 
 - The name of the task, as it will appear in the reports,
 - A list of Screenplay `Performable`s
 
In this case, we use the built-in `Type` performable to enter a value into the `NewTodo` page and then to hit Enter.

The final step needs to check the contents of the todo list. 
Inspect the page to see if you can figure out a locator that will fetch the list of all the todo item text values. 
Here is one possibility:

```java
    public static final Target TODO_LIST_ITEMS = Target.the("Todo list items")
                                                       .locatedBy(".todo-list label");
```

Using this `Target`, we can check the contents of the todo list, using the `Ensure.that(...).textValues()` methods:

```java
    @Then("his/her todo list should contain:")
    public void the_todo_list_should_contain(List<String> expectedItems) {
        withCurrentActor(
                Ensure.that(TodoReactHomePage.TODO_LIST_ITEMS)
                        .textValues()
                        .containsElementsFrom(expectedItems)
        );
    }
```

## Lesson 5 - Starting with a list that already contains some items

In the next lesson, we will implement a scenario that starts with a list that already contains entries:

```gherkin
  Scenario: Adding todo items to an existing list
    Given Trudy has a todo list containing
      | Feed the cat |
    When she adds "Walk the dog"
    Then her todo list should contain:
      | Feed the cat |
      | Walk the dog |

```

We will do this by building on the `AddATodoItem` class. 
The `Given` step will open the browser on the home page and add a list of todo items.
We could code it like this:

```java
    @Given("{actor} has a todo list containing")
    public void has_a_list_containing(Actor actor, List<String> expectedItems) {
        actor.attemptsTo(
                Open.browserOn().the(TodoReactHomePage.class),
                AddTodoItems.from(expectedItems)
        );
    }
```

The `AddTodoItems` class should look like this:

```java

public class AddTodoItems implements Performable {
    public static Performable from(List<String> expectedItems) {  
        return new AddTodoItems(expectedItems);
    }

    private List<String> expectedItems;

    AddTodoItems() {}

    private AddTodoItems(List<String> expectedItems) {
        this.expectedItems = expectedItems;
    }


    @Override
    @Step("{0} adds #expectedItems to the todo list")
    public <T extends Actor> void performAs(T actor) {
        expectedItems.forEach(
                item -> actor.attemptsTo(AddATodoItem.called(item))
        );
    }
}
```

The `from()` method is a factory method that creates a new instance of the `AddTodoItems` class with the list of expected todo items.
Serenity also needs a default constructor for technical reasons. 
The actual work happens in the `performAs()` method: here we perform the `AddATodoItem` task for each item in the list.
The `@Step` annotation determines the text that will appear in the Serenity reports when this task is executed.

## Lesson 6 - Completing tasks

In lesson 6, we look at a new feature,  [completing_todos.feature](src/test/resources/features/completing_todos/completing_todos.feature) feature file.

```gherkin
  Scenario: Completed todos should no longer appear in the active todo list
    Given Trudy has a todo list containing
      | Feed the cat |
      | Walk the dog |
    When she completes "Walk the dog"
    Then the todo item called "Walk the dog" should be marked as completed
```

The first step, _When she completes "Walk the dog"_, will use a new Screenplay task class called `CompleteTodoItem`:

```java
    @When("he/she completes {string}")
    public void sheCompletes(String completedTodo) {
        withCurrentActor(
                CompleteTodoItem.called(completedTodo)
        );
    }
```

This task class can use the `Task.where()` method that we saw previously, to click on the red cross which appears when you hover the mouse over an item.

```java
public class CompleteTodoItem {
    public static Performable called(String itemToComplete) {
        return Task.where("{0} completes the '" + itemToComplete  + "' task",
                Click.on(TodoReactHomePage.completeCheckboxFor(itemToComplete))
        );
    }
}
```

The tricky thing here is to locate the red cross, since it will be different for each todo item. 
To do this we can use a parameterised XPath expression, as shown here:

```java
    private static final Target COMPLETE_ITEM_ICON 
        = Target.the("complete checkbox for {0}")
                .locatedBy("//ul[@class='todo-list']//li[contains(.,'{0}')]//input[@type='checkbox']");
```

Next, we need to implement the `completeCheckboxFor()` method, which will instantiate a Target with a specific parameter value:

```java
    public static Target completeCheckboxFor(String itemToComplete) {
        return COMPLETE_ITEM_ICON.of(itemToComplete);
    }
```

The next step checks that the todo item appears in the completed state. 
This can be determined by looking for the `completed` css class, like this:

```java
    @Then("the todo item called {string} should be marked as completed")
    public void theTodoItemCalledShouldBeMarkedAsCompleted(String todoItem) {
        // LESSON 6
        withCurrentActor(
                Ensure.that(LISTED_TODO_ITEM.of(todoItem)).attribute("class")
                        .isEqualTo("completed")
        );
    }

```

## Lesson 7

## Lesson 8

## Lesson 9

## Lesson 10

```gherkin
  Scenario: Adding a single todo item
    Given Trudy has not entered any todo items
    When she adds "Walk the dog"
    Then her todo list should contain:
      | Walk the dog |
    And the remaining item count should show "1 item left"
```


## Lesson 11

## Lesson 12

## Want to learn more?
For more information about Serenity BDD, you can read the [**Serenity BDD Book**](https://serenity-bdd.github.io/theserenitybook/latest/index.html), the official online Serenity documentation source. Other sources include:
* **[Byte-sized Serenity BDD](https://www.youtube.com/channel/UCav6-dPEUiLbnu-rgpy7_bw/featured)** - tips and tricks about Serenity BDD
* [**Serenity BDD Blog**](https://johnfergusonsmart.com/category/serenity-bdd/) - regular articles about Serenity BDD
* [**The Serenity BDD Dojo**](https://serenitydojo.teachable.com) - Online training on Serenity BDD and on test automation and BDD in general. 
