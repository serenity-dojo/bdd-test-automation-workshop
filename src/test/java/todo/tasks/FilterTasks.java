package todo.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import todo.pageobjects.TodoReact;

public class FilterTasks {
    public static Performable by(String filter) {
        return Task.where("{0} filters tasks by '" + filter + "'",
                Click.on(TodoReact.FILTER.of(filter)));
    }
}
