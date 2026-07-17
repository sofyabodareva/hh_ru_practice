package hh;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import hh.pages.LoginPage;
import hh.pages.MainPage;

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
        Configuration.timeout = 10000;
        }

    public MainPage login(String email, String password) {
        logger.info("Переход на страницу входа и авторизация");
        Selenide.open("/account/login");
        LoginPage loginPage = new LoginPage();
        loginPage.clickSubmitButton();
        loginPage.clickEmailAuthButton();
        loginPage.fillLogin(email);
        loginPage.clickExpandPasswordButton();
        loginPage.fillPassword(password);
        return loginPage.submit();
    }

    // Метод, который сработает после тестов 
    @AfterEach
    public void closeBrowser() {
        logger.info("Закрытие браузера");
        Selenide.closeWebDriver();
    }   
}
