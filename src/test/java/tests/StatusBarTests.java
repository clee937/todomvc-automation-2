package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Status bar")
public class StatusBarTests extends BaseTest {

    @Test
    void shouldShowRemainingTodoCount() {
        String todo = "Wash car";

        todoPage.addTodo(todo);

        assertTrue(todoPage.isTodoCountVisible(),
                "Expected todo count to be visible");
    }

    @Test
    void shouldNotShowRemainingTodoCountWhenNoTodosExist() {
        assertFalse(todoPage.isTodoCountVisible(),
                "Expected todo count not to be visible when no todos exist");
    }

    @ParameterizedTest
    @CsvSource({
            "1, 0, 1",
            "3, 1, 2"
    })
    void shouldDisplayCorrectRemainingTodoCount(int totalTodos, int completedTodos, int expectedRemaining) {
        for (int i = 1; i <= totalTodos; i++) {
            todoPage.addTodo("Todo " + i);
        }

        for (int i = 1; i <= completedTodos; i++) {
            todoPage.completeTodo("Todo " + i);
        }

        assertEquals(expectedRemaining, todoPage.getRemainingTodoCount(),
                "Expected remaining todo count to be " + expectedRemaining);
    }

    // Verify the status bar remains visible and displays 0 remaining todos when all todos are completed.
    // This differs from having no todos, where the footer is hidden.
    @Test
    void shouldDisplayZeroRemainingTodoCountWhenAllTodosCompleted() {
        String firstTodo = "Wash car";
        String secondTodo = "Wash hair";

        todoPage.addTodo(firstTodo);
        todoPage.addTodo(secondTodo);

        todoPage.completeTodo(firstTodo);
        todoPage.completeTodo(secondTodo);

        assertEquals(0, todoPage.getRemainingTodoCount(),
                "Expected remaining todo count to be 0");
    }
}
