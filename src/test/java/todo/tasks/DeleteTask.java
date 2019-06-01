package todo.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.actions.MoveMouse;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.waits.WaitUntil;
import todo.pageobjects.TodoReact;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class DeleteTask implements Performable {
    private String taskName;

    private DeleteTask(String taskName) {
        this.taskName = taskName;
    }

    public DeleteTask() {}

    public static Performable called(String taskName) {
        return new DeleteTask(taskName);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(TodoReact.ITEM_LABEL.of(taskName)),
                JavaScriptClick.on(TodoReact.DELETE_BUTTON.of(taskName))
        );
    }
}
