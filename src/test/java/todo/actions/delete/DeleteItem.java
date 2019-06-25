package todo.actions.delete;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import todo.pageobjects.TodoList;

public class DeleteItem extends UIInteractionSteps {
    @Step("Delete item called {0}")
    public void called(String todoItem) {
        $(TodoList.itemLabelFor(todoItem)).click();
        $(TodoList.deleteButtonFor(todoItem)).click();
    }
}
