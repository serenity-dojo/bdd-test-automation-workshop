package todo.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;

@DefaultUrl("http://todomvc.com/examples/react/#")
public class TodoReact extends PageObject {

    public static final Target FOOTER = Target.the("site_layout section")
                                              .located(By.cssSelector("site_layout.info"));
}
