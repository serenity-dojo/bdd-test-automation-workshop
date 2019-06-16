package todo.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.MoveMouse;

import java.util.List;

public class AddTodoItems implements Performable {
    private List<String> expectedItems;

    public AddTodoItems() {
    }

    public AddTodoItems(List<String> expectedItems) {
        this.expectedItems = expectedItems;
    }

    public static Performable from(List<String> expectedItems) {
        return new AddTodoItems(expectedItems);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        expectedItems.forEach(
                item -> actor.attemptsTo(AddATodoItem.called(item))
        );
    }
}
