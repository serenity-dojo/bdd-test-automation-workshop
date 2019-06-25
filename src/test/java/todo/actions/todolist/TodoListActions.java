package todo.actions.todolist;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.steps.UIInteractionSteps;
import todo.pageobjects.TodoList;

import java.util.List;
import java.util.stream.Collectors;

import static todo.pageobjects.TodoList.TODO_ITEMS;

public class TodoListActions extends UIInteractionSteps {
    public List<String> currentItems() {
        return findAll(TODO_ITEMS)
                .stream()
                .map(WebElementFacade::getTextContent)
                .collect(Collectors.toList());
    }

    public Integer numberOfItemsLeft() {
        return Integer.parseInt($(TodoList.NUMBER_OF_ITEMS_LEFT).getText());
    }
}
