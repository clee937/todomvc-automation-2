package tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Editing todos")
public class EditingTodosTests extends BaseTest {

    @DisplayName("Should edit an existing todo")
    @ParameterizedTest(name = "Should edit {0} to {1}")
    @CsvSource({
            "Buy milk, Buy eggs",
            "東京, 京都",
            "a, b"
    })
    void shouldEditTodo(String originalTodo, String updatedTodo) {

        todoPage.addTodo(originalTodo);
        todoPage.editTodo(originalTodo, updatedTodo);

        List<String> todoTexts = todoPage.getTodoTexts();

        assertTrue(todoTexts.contains(updatedTodo),
                "Expected Todo list to contain updated todo: '" + updatedTodo + "' but was " + todoTexts);
        assertFalse(todoTexts.contains(originalTodo),
                "Expected Todo list not to contain original todo: '" + originalTodo + "'");
        assertEquals(1, todoTexts.size(),
                "Expected Todo list to contain only the updated todo");
    }

    @Test
    @Disabled("Known issue: Escape does not cancel editing in TodoMVC React implementation")
    void shouldCancelEditWhenEscapePressed() {
        String todo = "Wrap birthday gift";
        todoPage.addTodo(todo);

        todoPage.startEditingTodoAndPressEscape(todo);

        List<String> todoTexts = todoPage.getTodoTexts();

        assertTrue(todoTexts.contains(todo),
                "Expected Todo list to contain original todo after cancelling edit: '" + todo + "'");
        assertEquals(1, todoTexts.size(),
                "Expected Todo list to contain only the original todo");
    }
}
