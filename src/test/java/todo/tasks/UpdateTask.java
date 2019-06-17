package todo.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.PerformActions;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import todo.pageobjects.TodoReactHomePage;

import java.util.Arrays;

public class UpdateTask implements Performable {
    private String currentTaskName;
    private String newTaskName;

    private UpdateTask(String currentTaskName, String newTaskName) {
        this.currentTaskName = currentTaskName;
        this.newTaskName = newTaskName;
    }

    public UpdateTask() {
    }

    public static UpdateTaskBuilder from(String currentTaskName) {
        return new UpdateTaskBuilder(currentTaskName);
    }

    public static class UpdateTaskBuilder {
        private String currentTaskName;

        UpdateTaskBuilder(String currentTaskName) {
            this.currentTaskName = currentTaskName;
        }

        public UpdateTask to(String newTaskName) {
            return new UpdateTask(currentTaskName, newTaskName);
        }
    }


    @Override
    @Step("{0} updates the name of the '#currentTaskName' task to '#newTaskName")
    public <T extends Actor> void performAs(T actor) {
        WebElement todoItem = TodoReactHomePage.ITEM_LABEL.of(currentTaskName).resolveFor(actor);

        actor.attemptsTo(
                PerformActions.with(actions -> actions
                        .doubleClick(todoItem)
                        .sendKeys(backspacesToDelete(currentTaskName))
                        .sendKeys(newTaskName)
                        .sendKeys(Keys.ENTER)
                        .perform()
                )
        );
    }

    private Keys[] backspacesToDelete(String currentTaskName) {
        Keys[] backspaces = new Keys[currentTaskName.length()];
        Arrays.fill(backspaces, Keys.BACK_SPACE);
        return backspaces;
    }
}
