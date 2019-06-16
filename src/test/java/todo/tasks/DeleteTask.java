package todo.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import todo.pageobjects.TodoReactHomePage;

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
                Click.on(TodoReactHomePage.ITEM_LABEL.of(taskName)),
                JavaScriptClick.on(TodoReactHomePage.DELETE_BUTTON.of(taskName))
        );
    }
}
