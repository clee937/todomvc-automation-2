package tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Adding todos")
public class AddingTodosTests extends BaseTest {

    @Test
    void shouldAddTodoItem() {
        todoPage.addTodo("Buy eggs");
        assertTrue(todoPage.getTodoTexts().contains("Buy eggs"),
                "Expected Todo list to contain 'Buy eggs'");
    }

    @Test
    void shouldNotAddEmptyTodo() {
        int currentTodoCount = todoPage.getTodoCount();
        todoPage.pressEnterOnEmptyInput();
        assertEquals(currentTodoCount, todoPage.getTodoCount(),
                "Pressing Enter on an empty input should not create a new todo");
    }

    @Test
    void shouldNotAddTodoWhenPressingEnterMultipleTimesOnEmptyInput() {
        int currentTodoCount = todoPage.getTodoCount();

        todoPage.pressEnterOnEmptyInput();
        todoPage.pressEnterOnEmptyInput();
        todoPage.pressEnterOnEmptyInput();

        assertEquals(currentTodoCount, todoPage.getTodoCount(),
                "Pressing Enter on an empty input should not create a new todo");
    }

    @Test
    void shouldNotAddEmptyTodoAfterExistingTodo() {
        todoPage.addTodo("Buy eggs");

        int currentTodoCount = todoPage.getTodoCount();
        todoPage.pressEnterOnEmptyInput();

        assertEquals(currentTodoCount, todoPage.getTodoCount(),
                "Pressing Enter on an empty input should not create an additional todo");

        System.out.printf("Current count: %d, New count: %d", currentTodoCount, todoPage.getTodoCount());
    }

    //      Practising individual parameterized tests with @ValueSource. Good for mapping to requirements/spec.
    @DisplayName("Should accept single character todo text")
    @ParameterizedTest(name = "Should accept: \"{0}\"")
    @ValueSource(strings = {"a", "5", "£"})
    void shouldAcceptSingleCharacterTodoText(String todo) {
        todoPage.addTodo(todo);
        assertTrue(todoPage.getTodoTexts().contains(todo));
    }

    @DisplayName("Should accept punctuation")
    @ParameterizedTest(name = "Should accept: \"{0}\"")
    @ValueSource(strings = {"!", ".", "?"})
    void shouldAcceptPunctuation(String todo) {
        todoPage.addTodo(todo);
        assertTrue(todoPage.getTodoTexts().contains(todo),
                "Expected Todo list to contain: " + todo + ", but it contained: " + todoPage.getTodoTexts());
    }

    @DisplayName("Should accept numbers")
    @ParameterizedTest(name = "Should accept: \"{0}\"")
    @ValueSource(strings = {"0", "1", "10"})
    void shouldAcceptNumbers(String todo) {
        todoPage.addTodo(todo);
        assertTrue(todoPage.getTodoTexts().contains(todo),
                "Expected Todo list to contain: " + todo + ", but it contained: " + todoPage.getTodoTexts());
    }

    @DisplayName("Should accept accented characters")
    @ParameterizedTest(name = "Should accept: \"{0}\"")
    @ValueSource(strings = {"é", "ñ", "ô"})
    void shouldAcceptAccentedCharacters(String todo) {
        todoPage.addTodo(todo);
        assertTrue(todoPage.getTodoTexts().contains(todo),
                "Expected Todo list to contain: " + todo + ", but it contained: " + todoPage.getTodoTexts());
    }

    @DisplayName("Should accept non-Latin characters")
    @ParameterizedTest(name = "Should accept: \"{0}\"")
    @ValueSource(strings = {"東京", "مرحبا", "Γειά", "안녕"})
    void shouldAcceptNonLatinCharacters(String todo) {
        todoPage.addTodo(todo);
        assertTrue(todoPage.getTodoTexts().contains(todo),
                "Expected Todo list to contain: " + todo + ", but it contained: " + todoPage.getTodoTexts());
    }

    //      Practising data-driven parameterized test that groups several valid input types together with @CsvSource
    @DisplayName("Test input validation")
    @ParameterizedTest(name = "Should add todo: {0} - {1}")
    @CsvSource({"a, Single character",
            "!, Punctuation mark",
            "3, Number",
            "é, Accented character",
            "東京, Non-Latin characters"})
    public void shouldAcceptValidTodoText(String todo, String description) {
        todoPage.addTodo(todo);
        assertTrue(todoPage.getTodoTexts().contains(todo),
                "Expected Todo list to contain: " + todo + ", but it contained: " + todoPage.getTodoTexts());
    }

    @Test
    @Disabled("Blocked by ChromeDriver limitation: sendKeys does not support non-BMP Unicode characters")
    void shouldSupportEmoji() {
        todoPage.addTodo("\uD83D\uDE00");
        assertTrue(todoPage.getTodoTexts().contains("\uD83D\uDE00"),
                "Expected Todo list to contain '\uD83D\uDE00'");
    }
}
