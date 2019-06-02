package todo.stepdefinitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.ensure.Ensure;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import todo.pageobjects.TodoReact;
import todo.pageobjects.TodoStatus;
import todo.tasks.*;

import java.util.List;

import static net.serenitybdd.screenplay.actors.OnStage.*;
import static todo.pageobjects.TodoReact.LISTED_TODO_ITEM;

public class StepDefinitions {

    @Before
    public void prepareTests() {
        setTheStage(new OnlineCast());
    }

    @Given("{actor} opens the Todo Application")
    public void opens_the_Todo_Application(Actor actor) {
        actor.attemptsTo(
                Open.browserOn().the(TodoReact.class)
        );
    }

    @Then("he/she should see the credits in the footer")
    public void he_should_see_in_the_footer() {
        withCurrentActor(
                Ensure.that(TodoReact.FOOTER).text().contains("Part of TodoMVC")
        );

        theActorInTheSpotlight().should(
                seeThat(WebElementQuestion.the(TodoReact.FOOTER), WebElementStateMatchers.isVisible())
        );
    }

    // SAMPLE SOLUTIONS

    //
    // LESSON 2
    //
    @Given("{actor} has not entered any todo items")
    public void has_not_entered_any_todo_items(Actor actor) {
        actor.attemptsTo(
                Open.browserOn().the(TodoReact.class)
        );
    }

    @Then("the application should suggest how to add them")
    public void the_application_should_suggest_how_to_add_them() {
        withCurrentActor(
                Ensure.that(TodoReact.NEW_TODO).attribute("placeholder")
                        .isEqualTo("What needs to be done?")
        );
    }

    //
    // LESSON 3
    //
    @When("she adds {string}")
    public void she_adds(String todoItem) {
        withCurrentActor(
                AddATodoItem.called(todoItem)
        );
    }

    @Then("his/her todo list should contain:")
    public void the_todo_list_should_contain(List<String> expectedItems) {
        withCurrentActor(
                Ensure.that(TodoReact.TODO_LIST_ITEMS)
                        .textValues()
                        .containsElementsFrom(expectedItems)
        );
    }

    //
    // LESSON 4
    //
    @Given("{actor} has a todo list containing")
    public void has_a_list_containing(Actor actor, List<String> expectedItems) {
        actor.attemptsTo(
                Open.browserOn().the(TodoReact.class),
                AddTodoItems.from(expectedItems)
        );
    }

    //
    // LESSON 5
    //
    @When("he/she completes {string}")
    public void sheCompletes(String completedTodo) {
        withCurrentActor(
                CompleteTodoItem.called(completedTodo)
        );
    }

    @Then("the todo item called {string} should be marked as completed")
    public void theTodoItemCalledShouldBeMarkedAsCompleted(String todoItem) {
        // LESSON 5
        withCurrentActor(
                Ensure.that(LISTED_TODO_ITEM.of(todoItem)).attribute("class")
                        .isEqualTo("completed")
        );
        // LESSON 6
        withCurrentActor(
                Ensure.thatTheAnswerTo(statusOf(todoItem)).isEqualTo(TodoStatus.COMPLETED)
        );
    }

    @And("the number of items left should be {int}")
    public void theNumberOfItemsLeftShouldBe(Integer itemsLeft) {
        withCurrentActor(
                Ensure.that(TodoReact.ITEMS_LEFT).text().isEqualTo(itemsLeft.toString())
        );
    }

    //
    // LESSON 6
    //
    static Question<TodoStatus> statusOf(String todoItem) {
        return Question.about("todo status").answeredBy(
                actor -> {
                    if (LISTED_TODO_ITEM.of(todoItem).resolveFor(actor).getAttribute("class").equals("completed"))
                        return TodoStatus.COMPLETED;
                    else
                        return TodoStatus.TODO;
                }
        );
    }

    //
    // LESSON 7
    //
    @When("he/she filters the list to show {word} tasks")
    public void filtersBy(String filter) {
        withCurrentActor(
                FilterTasks.by(filter)
        );
    }

    //
    // LESSON 8
    //
    @When("he/she deletes {string}")
    public void deletesItem(String todoItem) {
        withCurrentActor(
                DeleteTask.called(todoItem)
        );
    }

    //
    // LESSON 9
    //
    @When("she updates {string} to {string}")
    public void sheUpdatesTo(String currentItemName, String newItemName) {
        withCurrentActor(
                UpdateTask.from(currentItemName).to(newItemName)
        );
    }
}






















