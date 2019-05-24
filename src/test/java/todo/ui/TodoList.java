package todo.ui;

import net.serenitybdd.screenplay.targets.Target;

public class TodoList {

    public static Target TODO_ITEMS = Target.the("todo items")
            .locatedBy(".todo-list li label");
}
