# TodoMVC Automation 2

## About

This project is a UI automation test suite for the React implementation of the TodoMVC application. It uses Selenium WebDriver with the Page Object Model (POM) to test both individual application features and end-to-end user flows.

The framework has been refactored to separate responsibilities into dedicated classes and uses an abstract base class to provide shared functionality and improve maintainability.

## Application Under Test

[TodoMVC React Application](https://todomvc.com/examples/react/dist/)

## Test Structure

- Abstract Base Class: `BaseTest` – Provides shared setup and common functionality for test classes
- Page Object: `TodoMVCReactPage` – Encapsulates interactions with the TodoMVC React application
- Feature Tests: `AddingTodosTests`, `EditingTodosTests`, `CompletingTodosTests`, `DeletingTodosTests`, `ClearingCompletedTodosTests`, `FilteringTodosTests`, `StatusBarTests` – Validate individual application features
- User Flow Tests: `UserFlowTests` – Validate end-to-end user journeys

## Running the Tests

1. Clone the repository.
2. Navigate to the project root directory.
3. Run the test suite using the Gradle wrapper.

Mac/Linux:
```bash
./gradlew test
```

Windows:
```cmd
gradlew.bat test
```

Alternatively, tests can be run directly from IntelliJ IDEA using the JUnit test runner.

## Technologies

- Java
- Selenium WebDriver
- JUnit 5
- IntelliJ IDEA
- Gradle

## Future Improvements

- Expand test coverage for additional TodoMVC features and edge cases
- Run the test suite across multiple browsers
- Introduce a common page interface to support multiple TodoMVC implementations




