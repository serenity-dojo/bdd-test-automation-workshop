package todo.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.type.Type;
import org.openqa.selenium.Keys;
import todo.pageobjects.TodoReactHomePage;

public class AddATodoItem {
    public static Performable called(String thingToDo) {
        return Task.where("{0} adds a todo item called: " + thingToDo,
                Type.theValue(thingToDo)
                        .into(TodoReactHomePage.NEW_TODO)
                        .thenHit(Keys.ENTER)
                );
    }
}
