package todo.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.annotations.Step;
import todo.pageobjects.TodoReact;

public class CompleteTodoItem implements Performable {
    private String itemToComplete;

    public CompleteTodoItem() {
    }

    public CompleteTodoItem(String itemToComplete) {
        this.itemToComplete = itemToComplete;
    }

    public static Performable called(String itemToComplete) {
        return new CompleteTodoItem(itemToComplete);
    }

    @Override
    @Step("{0} marks '#itemToComplete' as complete")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(TodoReact.completeCheckboxFor(itemToComplete))
        );
    }
}
