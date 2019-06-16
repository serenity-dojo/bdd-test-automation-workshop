package todo.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;

@DefaultUrl("http://todomvc.com/examples/react/#")
public class TodoReactHomePage extends PageObject {

    public static final Target FOOTER = Target.the("footer section")
                                              .located(By.cssSelector("footer.info"));

    public static final Target NEW_TODO = Target.the("New todo field")
                                                .locatedBy(".new-todo");

    public static final Target TODO_LIST_ITEMS = Target.the("Todo list items")
                                                       .locatedBy(".todo-list label");

    private static final Target COMPLETE_ITEM_ICON = Target.the("complete checkbox for {0}")
            .locatedBy("//ul[@class='todo-list']//li[contains(.,'{0}')]//input[@type='checkbox']");

    public static Target completeCheckboxFor(String itemToComplete) {
        return COMPLETE_ITEM_ICON.of(itemToComplete);
    }

    public static final By EDITED_TODO_ITEM = By.cssSelector(".edit");

    public static final Target LISTED_TODO_ITEM = Target.the("todo item called '{0}'")
            .locatedBy("//ul[@class='todo-list']//li[contains(.,'{0}')]");

    public static final Target ITEMS_LEFT = Target.the("number of items left")
            .locatedBy(".todo-count strong");

    public static final Target ITEMS_LEFT_MESSAGE = Target.the("number of items left")
            .locatedBy(".todo-count");

    public static final Target FILTER = Target.the("{0} filter").locatedBy("//a[.='{0}']");

    public static final Target ITEM_LABEL = Target.the("{0} item")
            .locatedBy("//li[contains(.,'{0}')]//label");

    public static final Target DELETE_BUTTON = Target.the("{0} delete button")
            .locatedBy("//li[contains(.,'{0}')]//button[@class='destroy']");
}