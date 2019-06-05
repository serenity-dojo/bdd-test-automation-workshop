package todo.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.type.Type;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import todo.pageobjects.TodoReact;

public class AddATodoItem implements Performable {

    private String thingToDo;

    public AddATodoItem() {
    }

    public AddATodoItem(String thingToDo) {
        this.thingToDo = thingToDo;
    }


    public static Performable called(String thingToDo) {
        return new AddATodoItem(thingToDo);
    }

    @Step("{0} adds a new todo item called '#thingToDo'")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Type.theValue(thingToDo)
                        .into(TodoReact.NEW_TODO)
                        .thenHit(Keys.ENTER)
        );
    }
}
