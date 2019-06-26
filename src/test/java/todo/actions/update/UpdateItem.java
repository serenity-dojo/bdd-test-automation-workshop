package todo.actions.update;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.Keys;
import todo.pageobjects.TodoList;

import java.util.Arrays;

public class UpdateItem extends UIInteractionSteps {
    private String currentItemName;

    public UpdateItem from(String currentItemName) {
        this.currentItemName = currentItemName;
        return this;
    }

    @Step("Update item #currentItemName to {0}")
    public void to(String newItemName) {
        withAction().doubleClick($(TodoList.itemLabelFor(currentItemName)))
                .sendKeys(backspacesToDelete(currentItemName))
                .sendKeys(newItemName)
                .sendKeys(Keys.ENTER)
                .perform();
    }

    private Keys[] backspacesToDelete(String currentTaskName) {
        Keys[] backspaces = new Keys[currentTaskName.length()];
        Arrays.fill(backspaces, Keys.BACK_SPACE);
        return backspaces;
    }
}
