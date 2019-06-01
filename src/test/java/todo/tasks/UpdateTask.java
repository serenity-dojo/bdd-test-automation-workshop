package todo.tasks;

import io.appium.java_client.PerformsActions;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.DoubleClick;
import net.serenitybdd.screenplay.actions.SendKeys;
import net.serenitybdd.screenplay.actions.PerformActions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import todo.pageobjects.TodoReact;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpdateTask implements Performable {
    private String currentTaskName;
    private String newTaskName;

    public UpdateTask(String currentTaskName, String newTaskName) {
        this.currentTaskName = currentTaskName;
        this.newTaskName = newTaskName;
    }

    public UpdateTask() {
    }

    public static UpdateTaskBuilder from(String currentTaskName) {
        return new UpdateTaskBuilder(currentTaskName);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebElement todoItem = TodoReact.ITEM_LABEL.of(currentTaskName).resolveFor(actor);

        actor.attemptsTo(
                PerformActions.with(actions -> actions
                        .doubleClick(todoItem)
                        .sendKeys(backspacesToDelete(currentTaskName))
                        .sendKeys(Keys.chord(Keys.CONTROL, "a"),
                                Keys.BACK_SPACE,
                                newTaskName,
                                Keys.ENTER)
                        .perform()
                )
        );
//        BrowseTheWeb.as(actor)
//                .withAction()
//                .doubleClick(todoItem)
//                .sendKeys(backspacesToDelete(currentTaskName))
//                .sendKeys(Keys.chord(Keys.CONTROL, "a"),
//                        Keys.BACK_SPACE,
//                        newTaskName,
//                        Keys.ENTER)
//                .perform();
    }

    private Keys[] backspacesToDelete(String currentTaskName) {
        List<Keys> keys = new ArrayList<>();
        for (int i = 0; i < currentTaskName.length(); i++) {
            keys.add(Keys.BACK_SPACE);
        }
        return keys.toArray(new Keys[]{});
    }

    public static class UpdateTaskBuilder {
        private String currentTaskName;

        public UpdateTaskBuilder(String currentTaskName) {
            this.currentTaskName = currentTaskName;
        }

        public UpdateTask to(String newTaskName) {
            return new UpdateTask(currentTaskName, newTaskName);
        }
    }
}
