package todo.actions.layout;

import net.serenitybdd.core.steps.UIInteractionSteps;

/**
 * Actions related to querying overall screen layout and look and feel.
 */
public class LayoutActions extends UIInteractionSteps {
    public String footer() { return $(FooterSection.FOOTER).getTextContent(); }
}
