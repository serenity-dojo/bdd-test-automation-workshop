package todo.actions.filter;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import todo.pageobjects.TodoList;

public class FilterItemsActions extends UIInteractionSteps {
    @Step("Filter items by {0}")
    public void by(String filter) {
        $(TodoList.filterButtonWithLabel(filter)).click();
    }
}
