package todo.stepdefinitions;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.waits.WaitUntil;
import todo.actions.AddNewTodo;
import todo.ui.NewTodoForm;
import todo.ui.TodoHomePage;
import todo.ui.TodoList;

import java.util.List;

import static net.serenitybdd.screenplay.actors.OnStage.*;

public class StepDefinitions {

    @Before
    public void prepareTests() {
        setTheStage(new OnlineCast());
    }

    @Given("(.*) (?:opens|has opened) the Todo Application")
    public void opens_the_Todo_Application(String actorName) {
        theActorCalled(actorName).attemptsTo(
                Open.browserOn().the(TodoHomePage.class)
        );
    }

    @Then("he should see \"(.*)\" in the footer")
    public void he_should_see_in_the_footer(String creditMessage) {
        withCurrentActor(
                Ensure.that(TodoHomePage.FOOTER)
                        .text()
                        .contains(creditMessage)
        );
    }

    @Given("(.*) has not entered any todo items")
    public void notEnteredAnyTodoItems(String actorName) {
        theActorCalled(actorName).attemptsTo(
                Open.browserOn().the(TodoHomePage.class)
        );
    }

    @Then("the application should suggest how to add them")
    public void theApplicationShouldSuggestHowToAddThem() {
        withCurrentActor(
                Ensure.that(NewTodoForm.NEW_TODO)
                        .attribute("placeholder")
                        .isEqualTo("What needs to be done?")
        );
    }


    @When("s?he adds {string}")
    public void she_adds(String newTodo) {
        withCurrentActor(
                AddNewTodo.called(newTodo)
        );
    }

    @Then("her todo list should contain:")
    public void her_todo_list_should_contain(List<String> expectedTodos) {
        withCurrentActor(
                Ensure.that(TodoList.TODO_ITEMS)
                        .textValues()
                        .containsExactlyElementsFrom(expectedTodos)
        );
    }
}






















