package hh;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTest {

    // Метод, который сработает до тестов
    @BeforeAll
    public static void openBrowser() {
        Configuration.baseUrl = "https://hh.ru";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    // Метод, который сработает после тестов 
    @AfterEach
    public void closeBrowser() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }
}
