package todo.ui;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.DefaultUrl;

public class NewTodoForm {

    public static Target NEW_TODO = Target.the("New Todo field")
            .locatedBy("css:input.new-todo");
}
