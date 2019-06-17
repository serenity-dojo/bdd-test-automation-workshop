package todo.questions;

import net.serenitybdd.screenplay.Question;
import todo.pageobjects.TodoStatus;

import static todo.pageobjects.TodoReactHomePage.LISTED_TODO_ITEM;

public class ItemStatusQuestions {

    public static Question<TodoStatus> statusOf(String todoItem) {
        return Question.about("todo status").answeredBy(
                actor -> {
                    if (LISTED_TODO_ITEM.of(todoItem).resolveFor(actor).getAttribute("class").equals("completed"))
                        return TodoStatus.COMPLETED;
                    else
                        return TodoStatus.TODO;
                }
        );
    }
}
