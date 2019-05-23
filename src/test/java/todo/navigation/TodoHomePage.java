package todo.navigation;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;

@DefaultUrl("http://todomvc.com/examples/react/#")
public class TodoHomePage extends PageObject {

    public static Target FOOTER = Target.the("footer section")
                                        .located(By.cssSelector("footer.info"));
}
