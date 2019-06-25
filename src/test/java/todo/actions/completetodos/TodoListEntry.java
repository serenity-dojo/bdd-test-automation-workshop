package todo.actions.completetodos;

import net.serenitybdd.screenplay.targets.Target;

public class TodoListEntry {
    private static final Target COMPLETE_ITEM_CHECKBOX = Target.the("complete checkbox for {0}")
            .locatedBy("//ul[@class='todo-list']//li[contains(.,'{0}')]//input[@type='checkbox']");

}
