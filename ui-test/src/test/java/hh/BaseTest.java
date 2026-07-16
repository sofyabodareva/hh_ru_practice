package hh;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BaseTest {

    protected static final Logger logger = LogManager.getLogger(BaseTest.class);
    // Метод, который сработает до тестов
    @BeforeAll
    public static void openBrowser() {
        logger.info("Настройка конфигурации браузера");
        Configuration.baseUrl = "https://hh.ru";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "normal";
    }

    // Метод, который сработает перед каждым тестом
    @org.junit.jupiter.api.BeforeEach
    public void setup() {
        logger.info("Переход на страницу входа");
        Selenide.open("/account/login");
    }

    // Метод, который сработает после тестов 
    @AfterEach
    public void closeBrowser() {
        logger.info("Закрытие браузера");
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }
}
