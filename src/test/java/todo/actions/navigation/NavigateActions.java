package todo.actions.navigation;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import todo.pageobjects.TodoReactHomePage;

public class NavigateActions extends UIInteractionSteps {

    TodoReactHomePage homePage;

    @Step("Navigate to the TodoMVC application")
    public void toTheTodoMVCApplication() {
        homePage.open();
    }
}
