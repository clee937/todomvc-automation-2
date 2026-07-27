package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DisplayName("Deleting todos")
public class DeletingTodosTests extends BaseTest {

    @Test
    void shouldDeleteActiveTodo() {
        String todo = "Write birthday card";
        todoPage.addTodo(todo);
        todoPage.deleteTodo(todo);
        List<String> todoTextsAfterDeletion = todoPage.getTodoTexts();

        assertFalse(todoTextsAfterDeletion.contains(todo),
                "Expected Todo list not to contain deleted todo: '" + todo + "'");
        assertEquals(0, todoTextsAfterDeletion.size(),
                "Expected Todo list to contain no todos after deletion");
    }

    @Test
    void shouldDeleteOnlySelectedTodo() {
        String firstTodo = "Buy milk";
        String secondTodo = "Buy birthday card";

        todoPage.addTodo(firstTodo);
        todoPage.addTodo(secondTodo);

        todoPage.deleteTodo(firstTodo);

        List<String> todoTextsAfterDeletion = todoPage.getTodoTexts();
        assertFalse(todoTextsAfterDeletion.contains(firstTodo),
                "Expected deleted todo to be removed from the list");
        assertEquals(1, todoTextsAfterDeletion.size(),
                "Expected exactly one todo to remain after deletion");
        assertTrue(todoTextsAfterDeletion.contains(secondTodo),
                "Expected remaining todo to be '" + secondTodo + "'");
    }

    @Test
    void shouldDeleteCompletedTodo() {
        String completedTodo = "Write birthday card";

        todoPage.addTodo(completedTodo);
        todoPage.completeTodo(completedTodo);
        todoPage.deleteTodo(completedTodo);

        List<String> todoTextsAfterDeletion = todoPage.getTodoTexts();

        assertFalse(todoTextsAfterDeletion.contains(completedTodo),
                "Expected completed todo '" + completedTodo + "' to be removed");
        assertEquals(0, todoTextsAfterDeletion.size(),
                "Expected no todos to remain after deletion");
    }
}
