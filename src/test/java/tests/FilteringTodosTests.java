package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Filtering todos")
public class FilteringTodosTests extends BaseTest {

    @Test
    void shouldFilterCompletedTodos() {
        String completedTodo = "Bake cake";
        String activeTodo = "Wash car";

        todoPage.addTodo(completedTodo);
        todoPage.addTodo(activeTodo);

        todoPage.completeTodo(completedTodo);

        todoPage.filterByCompleted();

        List<String> todoTexts = todoPage.getTodoTexts();

        assertTrue(todoTexts.contains(completedTodo),
                "Expected completed todo '" + completedTodo + "' to be visible");
        assertFalse(todoTexts.contains(activeTodo),
                "Expected active todo '" + activeTodo + "' not to be visible");
        assertEquals(1, todoTexts.size(),
                "Expected only completed todos to be visible");
    }

    @Test
    void shouldFilterActiveTodos() {
        String completedTodo = "Wrap birthday gift";
        String activeTodo = "Wash car";

        todoPage.addTodo(completedTodo);
        todoPage.addTodo(activeTodo);

        todoPage.completeTodo(completedTodo);
        todoPage.filterByActive();

        List<String> todoTexts = todoPage.getTodoTexts();

        assertTrue(todoTexts.contains(activeTodo),
                "Expected active todo '" + activeTodo + "' to be visible");
        assertFalse(todoTexts.contains(completedTodo),
                "Expected completed todo '" + completedTodo + "' not to be visible");
        assertEquals(1, todoTexts.size(),
                "Expected only active todos to be visible");
    }

    @Test
    void shouldShowAllTodosAfterRemovingFilter() {
        String completedTodo = "Bake cake";
        String activeTodo = "Make icing";

        todoPage.addTodo(completedTodo);
        todoPage.addTodo(activeTodo);

        todoPage.completeTodo(completedTodo);

        todoPage.filterByCompleted();

        List<String> todoTexts = todoPage.getTodoTexts();

        assertEquals(1, todoTexts.size(),
                "Expected only completed todos to be visible after applying Completed filter");

        todoPage.filterByAll();

        todoTexts = todoPage.getTodoTexts();

        assertTrue(todoTexts.contains(completedTodo),
                "Expected completed todo '" + completedTodo + "' to be visible after returning to All filter");
        assertTrue(todoTexts.contains(activeTodo),
                "Expected active todo '" + activeTodo + "' to be visible after returning to All filter");
        assertEquals(2, todoTexts.size(),
                "Expected all todos to be visible after returning to All filter");
    }
}
