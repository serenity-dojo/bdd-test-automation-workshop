package todo.actions.completetodos;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import todo.pageobjects.TodoList;

public class CompleteTodoActions extends UIInteractionSteps  {
    @Step("Complete todo item called {0}")
    public void itemCalled(String completedTodo) {
        $(TodoList.completedItemCheckboxFor(completedTodo)).click();
    }

    public boolean isCompleted(String item) {
        return $(TodoList.listedTodoItemFor(item)).getAttribute("class").equals("completed");
    }
}
