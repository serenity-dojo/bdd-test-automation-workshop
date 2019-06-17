package todo.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.MoveMouse;
import net.thucydides.core.annotations.Step;

import java.util.List;

public class AddTodoItems implements Performable {

    public static Performable from(List<String> expectedItems) {
        return new AddTodoItems(expectedItems);
    }

    private List<String> expectedItems;

    AddTodoItems() {}

    private AddTodoItems(List<String> expectedItems) {
        this.expectedItems = expectedItems;
    }

    @Override
    @Step("{0} adds #expectedItems to the todo list")
    public <T extends Actor> void performAs(T actor) {
        expectedItems.forEach(
                item -> actor.attemptsTo(AddATodoItem.called(item))
        );
    }
}
