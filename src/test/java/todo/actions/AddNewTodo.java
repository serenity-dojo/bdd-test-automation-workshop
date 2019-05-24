package todo.actions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Enter;
import org.openqa.selenium.Keys;
import todo.ui.NewTodoForm;

public class AddNewTodo implements Performable {


    private String newTodo;

    private AddNewTodo(String newTodo) {
        this.newTodo = newTodo;
    }

    public AddNewTodo() {
    }

    public static Performable called(String newTodo) {
        return new AddNewTodo(newTodo);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(newTodo)
                        .into(NewTodoForm.NEW_TODO)
                        .thenHit(Keys.ENTER)
        );
    }
}
