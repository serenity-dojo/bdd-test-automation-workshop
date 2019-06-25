package todo.actions.addtodo;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.Keys;

import java.util.List;

/**
 * Actions related to adding a new item to the list
 */
public class AddNewTodoActions extends UIInteractionSteps {

    @Step("add a todo called '{0}'")
    public void itemCalled(String todoItem) {
        $(TodoForm.NEW_TODO_FIELD).sendKeys(todoItem, Keys.ENTER);
    }

    @Step("add todo items: {0}")
    public void itemsCalled(List<String> todoItems) {
        todoItems.forEach(this::itemCalled);
    }

    public String getPrompt() {
        return $(TodoForm.NEW_TODO_FIELD).getAttribute("placeholder");
    }
}
