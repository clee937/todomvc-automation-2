package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.TodoMVCReactPage;

public abstract class BaseTest {
    protected WebDriver driver;
    protected TodoMVCReactPage todoPage;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        todoPage = new TodoMVCReactPage(driver);
        todoPage.open();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
