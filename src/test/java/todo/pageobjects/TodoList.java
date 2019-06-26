package todo.pageobjects;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

import static java.lang.String.format;

public class TodoList {
    public static By TODO_ITEMS = By.cssSelector(".todo-list label");
    public static By NUMBER_OF_ITEMS_LEFT = By.cssSelector(".todo-count strong");
    public static By NUMBER_OF_ITEMS_LEFT_MESSAGE = By.cssSelector(".todo-count");

    public static By completedItemCheckboxFor(String item) {
        return By.xpath(format("//ul[@class='todo-list']//li[contains(.,'%s')]//input[@type='checkbox']", item));
    }

    public static By listedTodoItemFor(String item) {
        return By.xpath(format("//ul[@class='todo-list']//li[contains(.,'%s')]", item));
    }

    public static By filterButtonWithLabel(String filter) {
        return By.linkText(filter);
    }

    public static By itemLabelFor(String item) {
        return By.xpath(format("//li[contains(.,'%s')]//label", item));
    }

    public static By deleteButtonFor(String item) {
        return By.xpath(format("//li[contains(.,'%s')]//button[@class='destroy']", item));
    }

}
