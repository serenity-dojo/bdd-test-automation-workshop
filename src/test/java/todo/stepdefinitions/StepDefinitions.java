package todo.stepdefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import todo.actions.addtodo.AddNewTodoActions;
import todo.actions.completetodos.CompleteTodoActions;
import todo.actions.delete.DeleteItem;
import todo.actions.filter.FilterItemsActions;
import todo.actions.layout.LayoutActions;
import todo.actions.navigation.NavigateActions;
import todo.actions.todolist.TodoListActions;
import todo.actions.update.UpdateItem;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StepDefinitions {

    @Steps
    NavigateActions navigate;

    @Steps
    LayoutActions pageLayout;

    @Given("(.*) opens the Todo Application")
    public void opens_the_Todo_Application(String actor) {
        navigate.toTheTodoMVCApplication();
    }

    @Then("he/she should see the credits in the footer")
    public void he_should_see_in_the_footer() {
        assertThat(pageLayout.footer()).contains("TodoMVC");
    }

    // SAMPLE SOLUTIONS

    //
    // LESSON 2
    //
    @Then("the page title should include {string}")
    public void thePageTitleShouldInclude(String expectedTitle) {
        assertThat(pageLayout.getTitle()).contains(expectedTitle);
    }

    //
    // LESSON 3
    //
    @Steps
    AddNewTodoActions addTodo;

    @Given("(.*) has not entered any todo items")
    public void has_not_entered_any_todo_items(String actor) {
        navigate.toTheTodoMVCApplication();
    }

    @Then("the application should suggest how to add them")
    public void the_application_should_suggest_how_to_add_them() {
        assertThat(addTodo.getPrompt()).isEqualTo("What needs to be done?");
    }

    //
    // LESSON 4
    //

    @Steps
    TodoListActions todoItems;

    @When("she adds {string}")
    public void she_adds(String todoItem) {
        addTodo.itemCalled(todoItem);
    }

    @Then("his/her todo list should contain:")
    public void the_todo_list_should_contain(List<String> expectedItems) {
        assertThat(todoItems.currentItems()).containsAll(expectedItems);
    }

    //
    // LESSON 5
    //
    @Given("(.*) has a todo list containing")
    public void has_a_list_containing(String actor, List<String> expectedItems) {
        navigate.toTheTodoMVCApplication();
        addTodo.itemsCalled(expectedItems);
    }

    //
    // LESSON 6
    //

    @Steps
    CompleteTodoActions completeTodo;

    @When("he/she completes {string}")
    public void sheCompletes(String completedTodo) {
        completeTodo.itemCalled(completedTodo);
    }

    @Then("the todo item called {string} should be marked as completed")
    public void theTodoItemCalledShouldBeMarkedAsCompleted(String todoItem) {
        // LESSON 6
        assertThat(completeTodo.isCompleted(todoItem)).isTrue();
    }

    //
    // LESSON 8
    //

    @Steps
    FilterItemsActions filterItems;

    @When("he/she filters the list to show {word} tasks")
    public void filtersBy(String filter) {
        filterItems.by(filter);
    }

    //
    // LESSON 9
    //
    @Steps
    DeleteItem deleteItem;

    @When("he/she deletes {string}")
    public void deletesItem(String todoItem) {
        deleteItem.called(todoItem);
    }

    //
    // LESSON 10
    //
    @And("the number of items left should be {int}")
    public void theNumberOfItemsLeftShouldBe(Integer itemsLeft) {
        assertThat(todoItems.numberOfItemsLeft()).isEqualTo(itemsLeft);
    }

    //
    // LESSON 11
    //
    @And("the remaining item count should show {string}")
    public void theRemainingItemCountShouldShow(String remainingItemCountText) {
        assertThat(todoItems.numberOfItemsLeftMessage()).isEqualTo(remainingItemCountText);
    }

    //
    // LESSON 12
    //
    @Steps
    UpdateItem updateItem;

    @When("she updates {string} to {string}")
    public void sheUpdatesTo(String currentItemName, String newItemName) {
        updateItem.from(currentItemName).to(newItemName);
    }

}






















