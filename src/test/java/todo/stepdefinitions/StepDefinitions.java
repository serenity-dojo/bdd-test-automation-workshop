package todo.stepdefinitions;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnlineCast;
import todo.pageobjects.TodoReactHomePage;

import static net.serenitybdd.screenplay.actors.OnStage.setTheStage;

public class StepDefinitions {

    @Before
    public void prepareTests() {
        setTheStage(new OnlineCast());
    }

    @Given("{actor} opens the Todo Application")
    public void opens_the_Todo_Application(Actor actor) {
        actor.attemptsTo(
                Open.browserOn().the(TodoReactHomePage.class)
        );
    }

    @Then("he/she should see the credits in the footer")
    public void he_should_see_in_the_footer() {
        throw new cucumber.api.PendingException();
    }
}






















