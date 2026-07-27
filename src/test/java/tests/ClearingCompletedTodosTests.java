package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DisplayName("Clearing completed todos")
public class ClearingCompletedTodosTests extends BaseTest {

    @Test
    void shouldShowClearCompletedButtonWhenCompletedTodosExist() {
        String completedTodo = "Wash car";

        todoPage.addTodo(completedTodo);
        todoPage.completeTodo(completedTodo);

        assertTrue(todoPage.isClearCompletedButtonVisible(),
                "Expected Clear completed button to be visible");
    }

    @Test
    void shouldClearAllCompletedTodos() {
        String completedTodo = "Bake cake";
        String activeTodo = "Wash car";

        todoPage.addTodo(completedTodo);
        todoPage.addTodo(activeTodo);
        todoPage.completeTodo(completedTodo);
        todoPage.clearCompletedTodos();

        List<String> todoTexts = todoPage.getTodoTexts();

        assertFalse(todoTexts.contains(completedTodo),
                "Expected completed todo '" + completedTodo + "' to be removed");
        assertTrue(todoTexts.contains(activeTodo),
                "Expected active todo '" + activeTodo + "' to remain");
        assertEquals(1, todoTexts.size(),
                "Expected only active todos to remain after clearing completed todos");
    }

    @Test
    void shouldNotShowClearCompletedButtonWhenNoCompletedTodosExist() {
        String activeTodo = "Wash car";

        todoPage.addTodo(activeTodo);

        assertFalse(todoPage.isClearCompletedButtonVisible(),
                "Expected Clear completed button not to be visible");
    }
}
