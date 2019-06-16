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

The first Cucumber scenario we will work on can be found in the [site_credits.feature](src/test/resources/features/footer/site_credits.feature) feature file.
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
The home page (for the React implementation of the application) is represented by the [TodoReact](src/test/java/todo/pageobjects/TodoReact.java) class, which is shown below:

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

Now go back to the [StepDefinitions](src/test/java/todo/stepdefinitions/StepDefinitions.java) and look at the second step definiton method:


```java
    @Then("he/she should see the credits in the footer")
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
                Ensure.that(TodoReact.FOOTER)
                      .text()
                      .contains("Part of TodoMVC")
        );
    }
```

Let's break this down. 
The `OnStage.withCurrentActor()` method finds the currently active actor (the last one to perform an action in the test). 
This method takes a list of business tasks and checks that the actor is to perform. 
Each of these tasks or checks is represented by an object that implements the _Performable_ interface, which model user interactions with the application in a domain-readable manner.

In this case, we use the `Ensure` class to check that the footer contains the text "Part of TodoMVC". 
The `Ensure.that()` method accepts, among other things, a locator that identifies an element on the page. 
The locator can either use the standard Selenium `By`is  class, or the Serenity `Target` class. 
This second option lets you combine a `By` locator (or a CSS or XPath expression) with a readable label that will appear in the reports when this element is used.
An example is shown here:

```java
    public static Target FOOTER = Target.the("footer section")
                                        .located(By.cssSelector("footer.info"));
```

The `Ensure.that()` method returns a fluent builder that shows you the available assertions for the type of parameter you provide. 
In this case, the `text()` method opens up access to a range of assertions about the text value of this element. 

## Lesson 2

```gherkin
  Scenario: User should be assisted when adding todo items for the first time
    Given Trudy has not entered any todo items
    Then the application should suggest how to add them
```

## Lesson 3

## Lesson 4

## Lesson 5

## Lesson 6

## Lesson 7

## Lesson 8

## Lesson 9

## Lesson 10

## Lesson 11

## Lesson 12

## Want to learn more?
For more information about Serenity BDD, you can read the [**Serenity BDD Book**](https://serenity-bdd.github.io/theserenitybook/latest/index.html), the official online Serenity documentation source. Other sources include:
* **[Byte-sized Serenity BDD](https://www.youtube.com/channel/UCav6-dPEUiLbnu-rgpy7_bw/featured)** - tips and tricks about Serenity BDD
* [**Serenity BDD Blog**](https://johnfergusonsmart.com/category/serenity-bdd/) - regular articles about Serenity BDD
* [**The Serenity BDD Dojo**](https://serenitydojo.teachable.com) - Online training on Serenity BDD and on test automation and BDD in general. 
