package todo.stepdefinitions;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.ensure.Ensure;
import todo.navigation.TodoHomePage;

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

    @Then("he should see the credits in the footer")
    public void he_should_see_in_the_footer() {
        // TODO: Complete me!
    }
}






















