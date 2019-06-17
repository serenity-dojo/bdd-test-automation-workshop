package todo.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import todo.pageobjects.TodoReactHomePage;

public class CompleteTodoItem {
    public static Performable called(String itemToComplete) {
        return Task.where("{0} completes the '" + itemToComplete  + "' task",
                Click.on(TodoReactHomePage.completeCheckboxFor(itemToComplete))
        );
    }
}
