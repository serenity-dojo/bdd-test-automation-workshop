package todo.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import todo.pageobjects.TodoReactHomePage;

public class DeleteTask {

    public static Performable called(String taskName) {
        return Task.where("{0} deletes the '" + taskName + "' task",
                Click.on(TodoReactHomePage.ITEM_LABEL.of(taskName)),
                JavaScriptClick.on(TodoReactHomePage.DELETE_BUTTON.of(taskName))
        );
    }
}
