package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Managing todo completion")
public class CompletingTodosTests extends BaseTest {

    @Test
    void shouldCompleteTodo() {
        String todo = "Wrap birthday gift";
        todoPage.addTodo(todo);
        todoPage.completeTodo(todo);

        assertTrue(todoPage.isTodoCompleted(todo),
                "Expected todo to be completed: " + todo);
    }

    @Test
    void shouldMarkTodoAsIncomplete() {
        String todo = "Wrap birthday gift";
        todoPage.addTodo(todo);

        assertFalse(todoPage.isTodoCompleted(todo),
                "The todo: '" + todo + "' is already complete and shouldn't be.");

        todoPage.completeTodo(todo);
        todoPage.uncompleteTodo(todo);

        assertFalse(todoPage.isTodoCompleted(todo),
                "Expected todo to be incomplete: " + todo);
    }

    @Test
    void shouldCompleteAllTodos() {
        String firstTodo = "Wrap birthday gift";
        String secondTodo = "Order water filters";
        String thirdTodo = "Book MOT";

        todoPage.addTodo(firstTodo);
        todoPage.addTodo(secondTodo);
        todoPage.addTodo(thirdTodo);

        todoPage.completeTodo(firstTodo);
        todoPage.toggleAllTodoCompletion();

        for (String todo: todoPage.getTodoTexts()) {
            assertTrue(todoPage.isTodoCompleted(todo),
                    "Expected todo '" + todo + "' to be completed");
        }
    }

    @Test
    void shouldMarkAllTodosIncomplete() {
        String firstTodo = "Wrap birthday gift";
        String secondTodo = "Order water filters";
        String thirdTodo = "Book MOT";

        todoPage.addTodo(firstTodo);
        todoPage.addTodo(secondTodo);
        todoPage.addTodo(thirdTodo);

        todoPage.toggleAllTodoCompletion();
        todoPage.toggleAllTodoCompletion();

        for (String todo : todoPage.getTodoTexts()) {
            assertFalse(todoPage.isTodoCompleted(todo),
                    "Expected todo '" + todo + "' to be incomplete");
        }
    }
}
